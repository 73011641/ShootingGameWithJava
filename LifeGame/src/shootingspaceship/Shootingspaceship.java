package shootingspaceship;

import Code.Item.FinishShotItem;
import Code.Item.HeartItem;
import Code.Item.Item;
import Code.Player.Person;
import Code.Player.PersonStateDraw;
import Code.Shot.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.*;

public class Shootingspaceship extends JPanel implements Runnable {

    public boolean thstop = false;
    public static boolean bossAlive = false; //보스 존재 정보

    private int hp = 5;
    private int hpCount = hp;
    private Thread th;
    private boolean forlaser = false;
    private Person player;
    private PersonStateDraw playerState;
    private GameSetting gameSetting;
    private ArrayList<NormalShot> pShots; //일반 총알
    private ArrayList<FinishShot> fShots;//필살기 총알
    private List<Item> items; // 아이템
    private ArrayList<EnemyShot> Eshots; // 적 총알
    private ArrayList<Shot> Bshots; // 보스 총알
    private ArrayList<BossShotLaser> Lshots; // 보스 레이저
    private List<Enemy> enemies; // 적 배열
    private final int eShotSpeed = 4;
    private final int shotSpeed = -8;
    private final int playerLeftSpeed = -4;
    private final int playerRightSpeed = 4;
    private final int playerDownSpeed = 4;
    private final int playerUpSpeed = -4;
    private final int playerShotGap = 100;
    private final int playerInfinityGap = 1000;
    private final int width = 1100;
    private final int height = 600;
    private final int playerMargin = 10;
    private final int enemyTimeGap = 1800; //unit: msec

    /*=======================================================================*/
    private final int maxEnemySize = 15;
    private static int laserSize;
    /*=======================================================================*/
    private javax.swing.Timer enemySpawnTimer;
    private javax.swing.Timer playerShotTimer;
    private javax.swing.Timer playerInfinityTimer;
    private javax.swing.Timer stageTimer;
    /*=======================================================================*/
    private boolean playerMoveLeft;
    private boolean playerMoveRight;
    private boolean playerMoveUp;
    private boolean playerMoveDown;
    private boolean playerShotKey;
    private boolean playerFinishShotKey;
    private boolean shotReady;
    private boolean infinityReady;
    private Image dbImage;
    private Graphics dbg;
    private Random rand;
    private int maxShotNum = 30; //한창에서 최대 나갈수있는 총알갯수
    /*=======================================================================*/
    public static int stage = 1;
    public static int score = 0;
    private int enemyOutCount = 0;
    private static int stageCNT = 0;

    //일시정지
    JLabel escDisplay = new JLabel();
    //배경이미지 이동
    private Background mbg;
    //사운드 생성
    public static Sound GameBgm = new Sound();

    public Shootingspaceship() {
        mbg = new Background(); //배경 이미지 이동
        GameBgm.BgmList.get(6).PlaySound(false);
        setBackground(Color.black);
        setPreferredSize(new Dimension(width, height));
        player = new Person(width / 2, (int) (height * 0.9), playerMargin, width - playerMargin, playerMargin, height - playerMargin, hp);
        playerState = new PersonStateDraw();
        gameSetting = new GameSetting(player);
        pShots = new ArrayList<NormalShot>();
        fShots = new ArrayList<FinishShot>();
        Eshots = new ArrayList<EnemyShot>();
        Bshots = new ArrayList<Shot>();
        Lshots = new ArrayList<BossShotLaser>();

        enemies = Collections.synchronizedList(new ArrayList<Enemy>());
        items = Collections.synchronizedList(new ArrayList<Item>());
        laserSize = 0;
        rand = new Random(1);
        enemySpawnTimer = new javax.swing.Timer(enemyTimeGap, new addANewEnemy());
        enemySpawnTimer.start();
        stageTimer = new javax.swing.Timer(1000, new stageActionListener());
        playerShotTimer = new javax.swing.Timer(playerShotGap, new PlayerActionListener()); // 플레이어 총알 간격 결정
        playerInfinityTimer = new javax.swing.Timer(playerInfinityGap, new PlayerActionListener()); // 충돌 시 무적 시간 결정
        shotReady = true; // 총알 간격 용
        infinityReady = true; // 무적 시간 용
        addKeyListener(new ShipControl());
        setFocusable(true);
    }

    public void start() {
        th = new Thread(this);
        th.start();
    }

    private class stageActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            stageCNT++;
        }
    }

    private class PlayerActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (shotReady == false) {
                shotReady = true;
                playerShotTimer.stop();
            } // 플레이어 총알 간격을 위한
            if (infinityReady == false) {
                infinityReady = true;
                playerInfinityTimer.stop();
            }
        }
    }

    private class addANewEnemy implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (bossAlive == false) {
                if (enemyOutCount != maxEnemySize) {
                    gameSetting.lendE(enemies);
                    if (enemies.get(0) instanceof Boss) {
                        bossAlive = true;
                        GameBgm.BgmList.get(6).stopSound();
                        GameBgm.BgmList.get(7).PlaySound(true);
                    } else {
                        enemyOutCount++;
                    }
                }
                if (enemyOutCount == maxEnemySize && enemies.isEmpty()) {
                    Eshots.clear();
                    Bshots.clear();
                    Lshots.clear();
                    enemyOutCount = 0;
                }
            }
            Item newItem;
            if (enemyOutCount == 1) {
                newItem = new HeartItem(1050, 300);
                items.add(newItem);
            }
            if (enemyOutCount == 1) {
                newItem = new FinishShotItem(1050, 400);
                items.add(newItem);
            }
        }
    }

    private class ShipControl implements KeyListener { //키 눌렀을때

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    playerMoveLeft = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    playerMoveRight = true;
                    break;
                case KeyEvent.VK_UP:
                    playerMoveUp = true;
                    break;
                case KeyEvent.VK_DOWN:
                    playerMoveDown = true;
                    break;
                case KeyEvent.VK_SPACE:
                    playerShotKey = true;
                    break;
                case KeyEvent.VK_Z:
                    playerFinishShotKey = true;
                    break;
            }
        }

        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    playerMoveLeft = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    playerMoveRight = false;
                    break;
                case KeyEvent.VK_UP:
                    playerMoveUp = false;
                    break;
                case KeyEvent.VK_DOWN:
                    playerMoveDown = false;
                    break;
                case KeyEvent.VK_SPACE:
                    playerShotKey = false;
                    break;
                case KeyEvent.VK_Z:
                    playerFinishShotKey = false;
                    break;
            }
        }

        public void keyTyped(KeyEvent e) {
        }
    }

    public void run() {
        //int c=0;
        //쓰레드란, 실제로 작업을 수행하는 주체
        //지금 수행중인 프로세서를 ()안의 지정한 값으로 변경한다. = Thread.MIN_PRIORITY
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        //샷을 발사하고, 적과 플레이어의 동작을 수행하게 하는 반복문

        while (true) {
            if (thstop == true) {
                sleeping();
            }
/*=======================================================================*/
            // 플레이어 샷
            // 샷 배열 안에 있는 샷의 동작
            for (int i = 0; i < pShots.size(); i++) {
                //샷이 있으면
                if (pShots.get(i) != null) {
                    // move shot
                    pShots.get(i).moveShot(shotSpeed);
                    if (pShots.get(i).getX() > width) { //총알의 x(가로)좌표가 화면크기를 넘어서면 shot을 없애뿐다.
                        pShots.remove(i);
                    }
                }
            }
            //필살기 샷
            for (int i = 0; i < fShots.size(); i++) {
                //샷이 있으면

                if (fShots.get(i) != null) {
                    // move shot
                    fShots.get(i).moveShot(shotSpeed);

                    if (fShots.get(i).getX() > width) { //총알의 x(가로)좌표가 화면크기를 넘어서면 shot을 없애뿐다.
                        fShots.remove(i);
                    }
                }
            }
            /*=======================================================================*/
            // 적 샷
            for (int i = 0; i < Eshots.size(); i++) {
                //샷이 있으면
                if (Eshots.get(i) != null) {
                    // move shot
                    Eshots.get(i).moveShot(eShotSpeed);
                    if (Eshots.get(i).getX() < 0) { //총알의 x(가로)좌표가 화면크기를 넘어서면 shot을 없애뿐다.
                        Eshots.remove(i);
                    }
                }
            }
            /*=======================================================================*/
            // 보스 샷
            for (int i = 0; i < Bshots.size(); i++) {
                //샷이 있으면
                if (Bshots.get(i) != null) {
                    // move shot                  
                    Bshots.get(i).moveShot(eShotSpeed);
                    if (Bshots.get(i).getX() < 0) { //총알의 x(가로)좌표가 화면크기를 넘어서면 shot을 없애뿐다.
                        Bshots.remove(i);
                    } else if (Bshots.get(i).getY() < 0 || Bshots.get(i).getY() > height) { //총알의 y(세로)좌표가 화면크기를 넘어서면 shot을 없애뿐다. 
                        Bshots.remove(i);
                    }
                }
            }
            //레이저
            for (int i = 0; i < Lshots.size(); i++) {
                if (Lshots.get(i) != null) {
                    Lshots.get(i).count++;
                    if (Lshots.get(i).count > 100) {
                        Lshots.get(i).changeShot();
                    }
                    // test if shot is out
                    if (Lshots.get(i).count > 300 || forlaser == true) {
                        forlaser = false;
                        Lshots.remove(i);
                        laserSize = 0;
                    }
                }
            }
            /*=======================================================================*/
            //플레이어 왼쪽 오른쪽 동작 수행
            if (playerMoveLeft) {
                player.moveX(playerLeftSpeed);
            }
            if (playerMoveRight) {
                player.moveX(playerRightSpeed);
            }
            if (playerMoveUp) {
                player.moveY(playerUpSpeed);
            }
            if (playerMoveDown) {
                player.moveY(playerDownSpeed);
            }
            if (playerShotKey) {
                if (shotReady == true) {
                    playerShotTimer.start();
                    pShots.add(player.generateShot());
                    GameBgm.BgmList.get(3).PlaySound(false);
                    shotReady = false;
                }
            }
            if (playerFinishShotKey) {
                if (fShots.isEmpty() && player.fshotState() == true) {
                    GameBgm.BgmList.get(2).PlaySound(false);
                    fShots.add(player.generateFShot());
                    player.fshotDown();
                    Eshots.clear();
                    Bshots.clear();
                    Lshots.clear();
                }
            }
            //플레이어
            if (player.isCollidedWithEshot(Eshots) || player.isCollidedWithEshot(Bshots) || player.isCollidedWithEshot(Lshots)) {
                GameBgm.BgmList.get(5).PlaySound(false);
                if (infinityReady) { // 충돌 시 무적
                    if (!player.hpDown()) { // 플레이어가 죽었다!
                        Eshots.clear();
                        Bshots.clear();
                        Lshots.clear();
                        if (bossAlive == true) { // 보스전일 때
                            player.playerGrowth.add(false); // 보스잡기 실패 플래그 저장
                        } else if (bossAlive == false) { // 보스전이 아닐 때
                            gameSetting.remainE(maxEnemySize - enemyOutCount); //아직 안나온 쫄들 증발시키기
                        }
                        for (int i = 0; i < enemies.size(); ++i) {
                            enemies.get(i).alive = false; //필드에 있는 적 모두 죽이기 => 이터레이터가 반납 시킴
                        }
                        player.hpSet(hpCount * 2/3); // 플레이어 체력 초기화
                        enemyOutCount = maxEnemySize; // 아직 안나온 애들(증발) 때문에 아웃카운트 덜 세어지니까 강제로 다 나온 것처럼 카운트
                    }
                    playerInfinityTimer.start();
                    infinityReady = false;
                }
            }
            /*=======================================================================*/
            /**
             * *적**
             */
            synchronized (enemies) {
                Iterator enemyList = enemies.iterator();
                //적 리스트에 적이 있다면 적 동작을 수행
                while (enemyList.hasNext()) {
                    Enemy enemy = (Enemy) enemyList.next();
                    if (enemy instanceof Boss) { //CASE 보스
                        if (enemy.getAlive()) {  // 보스가 살아있다면
                            enemy.count++;
                            enemy.move();
                            if (((Boss) enemy).state == 1) {
                                if (((Boss) enemy).count % 80 == 0) {
                                    for (int i = -2; i < 3; i++) {
                                        BossShotAdd BshotA = enemy.generateShotBA(i);
                                        Bshots.add(BshotA);
                                    }
                                }
                            } else if (((Boss) enemy).state == 2) {
                                if (enemy.count % 80 == 0) {
                                    for (int i = -1; i < 3; i++) {
                                        BossShot333 Bshot3 = enemy.generateShotB3(i);
                                        Bshots.add(Bshot3);
                                    }
                                }
                            } else if (((Boss) enemy).state == 3) {
                                forlaser = true;
                                if (enemy.count % 80 == 0) {
                                    for (int i = -1; i < 4; i++) {
                                        BossShotAdd BshotA = enemy.generateShotBA(i);
                                        Bshots.add(BshotA);
                                    }
                                    for (int i = 0; i < 2; i++) {
                                        BossShot333 Bshot3 = enemy.generateShotB3(i);
                                        Bshots.add(Bshot3);
                                    }
                                }
                            } else if (((Boss) enemy).state == 4) {
                                forlaser = false;
                                if (enemy.count % 80 == 0) {
                                    for (int i = -2; i < 3; i++) {
                                        BossShotAdd BshotA = enemy.generateShotBA(i);
                                        Bshots.add(BshotA);
                                    }
                                    int produce = 100;
                                    if (enemy.count % produce == 0 && ++laserSize <= 1) {
                                        BossShotLaser BshotL = enemy.generateShotBL();
                                        Lshots.add(BshotL);
                                    }
                                }
                            } else if (((Boss) enemy).state == 5) {
                                forlaser = false;
                                if (enemy.count % 80 == 0) {
                                    for (int i = -1; i < 3; i++) {
                                        BossShot333 Bshot3 = enemy.generateShotB3(i);
                                        Bshots.add(Bshot3);
                                    }
                                    int produce = 100;
                                    if (enemy.count % produce == 0 && ++laserSize <= 1) {
                                        BossShotLaser BshotL = enemy.generateShotBL();
                                        Lshots.add(BshotL);
                                    }
                                }
                            } else if (((Boss) enemy).state == 6) {
                                forlaser = true;
                                if (enemy.count % 80 == 0) {
                                    BossShotMisile BshotM = enemy.generateShotBM(1);
                                    Bshots.add(BshotM);
                                } else if (enemy.count % 100 == 0) {
                                    BossShotMisile BshotM = enemy.generateShotBM(-1);
                                    Bshots.add(BshotM);
                                }
                            } else if (((Boss) enemy).state == 7) {
                                forlaser = true;
                                if (enemy.count % 80 == 0) {
                                    for (int i = 0; i < 2; ++i) {
                                        BossShotMisile BshotM = enemy.generateShotBM(i);
                                        Bshots.add(BshotM);
                                    }
                                }
                                if (enemy.count % 30 == 0) {
                                    for (int i = -3; i < 4; i++) {
                                        BossShotAdd BshotA = enemy.generateShotBA(i);
                                        Bshots.add(BshotA);
                                    }
                                }
                            }
                            if (enemy.isCollidedWithShot(pShots)) // 보스가 총알과 부딪혔을 때
                            {
                                if (enemy.getHp() <= 0) { // 체력이 0 이하 --> 죽음
                                    enemy.setAlive(false);
                                    player.playerGrowth.add(true);
                                }
                            } else if (enemy.isCollidedWithShot(fShots)) { // 적이 필살기와 부딪혔을 때
                                if (enemy.getHp() <= 0) { // 체력이 0 이하 --> 죽음
                                    enemy.setAlive(false);
                                    player.playerGrowth.add(true);
                                }
                            } else if (enemy.isCollidedWithPlayer(player)) {
                                if (infinityReady) {
                                    if (!player.hpDown()) { // 플레이어가 죽었다!
                                        Eshots.clear();
                                        Bshots.clear();
                                        Lshots.clear();
                                        if (bossAlive == true) { // 보스전일 때
                                            player.playerGrowth.add(false); // 보스잡기 실패 플래그 저장
                                        } else if (bossAlive == false) { // 보스전이 아닐 때
                                            gameSetting.remainE(maxEnemySize - enemyOutCount); //아직 안나온 쫄들 증발시키기
                                        }
                                        for (int i = 0; i < enemies.size(); ++i) {
                                            enemies.get(i).alive = false; //필드에 있는 적 모두 죽이기 => 이터레이터가 반납 시킴
                                        }
                                        player.hpSet(hpCount * 2/3); // 플레이어 체력 초기화
                                        enemyOutCount = maxEnemySize; // 아직 안나온 애들(증발) 때문에 아웃카운트 덜 세어지니까 강제로 다 나온 것처럼 카운트
                                    }
                                    playerInfinityTimer.start();
                                    infinityReady = false;
                                }
                            }
                        } else { // 죽으면
                            Eshots.clear();
                            Bshots.clear();
                            Lshots.clear();
                            stageTimer.start();
                            if (stageCNT == 1) {
                                stageTimer.stop();
                                stageCNT = 0;
                                gameSetting.receiveE(enemy, enemyList); // 객체를 반납한다
                                bossAlive = false;
                                enemyOutCount = 0;
                                GameBgm.BgmList.get(7).stopSound();
                                GameBgm.BgmList.get(6).PlaySound(false);
                                if (((Boss) enemy).stage_authority == 1) {
                                    player.maxHpSet(++hpCount);
                                    stage++;
                                    pShots.clear();
                                    fShots.clear();
                                }
                            }
                        }
                    } else { // CASE 적
                        if (enemy.getAlive()) {  // 적이 살아있다면
                            enemy.count++;
                            enemy.move();
                            if (enemy.count % 80 == 0) {
                                EnemyShot Eshot = enemy.generateShot();
                                Eshots.add(Eshot);
                            }
                            if (enemy.isCollidedWithShot(pShots)) // 적이 총알과 부딪혔을 때
                            {
                                //mainBgm.playSound(false, "적맞는소리");
                                if (enemy.getHp() <= 0) { // 체력이 0 이하 -->  죽음
                                    enemy.setAlive(false);
                                    GameBgm.BgmList.get(4).PlaySound(false);
                                    score += stage * 400000;
                                }
                            } else if (enemy.isCollidedWithShot(fShots)) { // 적이 필살기와 부딪혔을 때
                                //mainBgm.playSound(false, "적맞는소리");
                                enemy.setAlive(false);
                                score += stage * 400000;
                            } else if (enemy.isCollidedWithPlayer(player)) {
                                if (infinityReady) { // 충돌 시 무적
                                    if (!player.hpDown()) { // 플레이어가 죽었다!
                                        if (bossAlive == true) { // 보스전일 때
                                            player.playerGrowth.add(false); // 보스잡기 실패 플래그 저장
                                        } else if (bossAlive == false) { // 보스전이 아닐 때
                                            gameSetting.remainE(maxEnemySize - enemyOutCount); //아직 안나온 쫄들 증발시키기
                                        }
                                        for (int i = 0; i < enemies.size(); ++i) {
                                            enemies.get(i).alive = false; //필드에 있는 적 모두 죽이기 => 이터레이터가 반납 시킴
                                        }
                                        player.hpSet(hpCount * 2/3); // 플레이어 체력 초기화
                                        enemyOutCount = maxEnemySize; // 아직 안나온 애들(증발) 때문에 아웃카운트 덜 세어지니까 강제로 다 나온 것처럼 카운트
                                    }
                                    playerInfinityTimer.start();
                                    infinityReady = false;
                                }
                            } else if (enemy.pass()) {
                                enemy.setAlive(false);
                            }
                        } else { //죽으면 객체 반납
                            gameSetting.receiveE(enemy, enemyList);
                        }
                    }
                }
            }

            synchronized (items) {
                Iterator itemList = items.iterator();
                while (itemList.hasNext()) {
                    Item item = (Item) itemList.next();
                    if (item.getAlive()) {  // 아이템이 살아있다면 (= 플레이어가 안먹었으면)
                        item.move();
                    }
                    if (player.isCollidedWithItem(items)) {
                        GameBgm.BgmList.get(0).PlaySound(false);
                    }
                }
            }
            /*=======================================================================*/

            //적을 그림
            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                // do nothing
            }

            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }

    /*=======================================================================*/
    //전체적인 그래픽을 그리기 위한 설정과 초기화, 배경 설정
    public void initImage(Graphics g) {
        //이미지가 아무것도 없을 때 이미지를 그림
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        dbg.setColor(getForeground());
        //paint (dbg);

        g.drawImage(dbImage, 0, 0, this);
    }

    /*=======================================================================*/
    //플에이어와 적 그리고 샷을 그래픽으로 구현
    public void paintComponent(Graphics g) {
        initImage(g);
        mbg.drawBG(g); //배경

        playerState.drawState(g, player, score); // UI, HP, 필살기 그리기
        player.drawPlayer(g); //플레이어 그리기

        // 적 그리기
        synchronized (enemies) {
            Iterator enemyList = enemies.iterator();
            while (enemyList.hasNext()) {
                Enemy enemy = (Enemy) enemyList.next();
                if (enemy.getAlive()) // 적이 살아있는 상태면 그림
                {
                    enemy.draw(g);
                }
            }
        }
        // 아이템 그리기
        Iterator itemList = items.iterator();
        while (itemList.hasNext()) {
            Item item = (Item) itemList.next();
            if (item.getAlive()) { // 아이템이 살아있으면 그린다.
                item.drawItem(g);
            }
        }
        /*=======================================================================*/
        /**
         * ****샷*****
         */
        // 샷 그리기
        for (int i = 0; i < pShots.size(); i++) {
            if (pShots.get(i) != null) {
                pShots.get(i).drawShot(g);
            }
        }
        // 필살기 그리기
        for (int i = 0; i < fShots.size(); i++) {
            if (fShots.get(i) != null) {
                fShots.get(i).drawShot(g);
            }
        }
        //적 샷 그리기
        for (int i = 0; i < Eshots.size(); i++) {
            if (Eshots.get(i) != null) {
                Eshots.get(i).drawShot(g);
            }
        }
        //보스 샷 그리기
        for (int i = 0; i < Bshots.size(); i++) {
            if (Bshots.get(i) != null) {
                Bshots.get(i).drawShot(g);
            }
        }
        //레이저 그리기
        for (int i = 0; i < Lshots.size(); i++) {
            if (Lshots.get(i) != null) {
                if (Lshots.get(i).count > 50) {
                    Lshots.get(i).drawShot(g);
                }
            }
        }
    }

    //쓰레드 재우기
    synchronized void sleeping() {
        try {
            wait();
        } catch (InterruptedException e) {
            return;
        }
    }

    //쓰레드 깨우기
    synchronized void wakeup() {
        notify();
        thstop = false;
    }

    //쓰레드 변수 지정
    void setThStop(boolean t) {
        this.thstop = t;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean sn = false;
        boolean game = false;

        JFrame frame = new JFrame("LG Game 인생 게임");//나오는 창 = 실행 창
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainDisplay maind = new MainDisplay(); // 메인 화면
        Manual mu = new Manual(); //설명 화면

        frame.getContentPane().add(maind);

        frame.pack();
        frame.setVisible(true);

        //메인
        mexit_Label:
        while (true) {
            System.out.print("");
            if (maind.lego() == true) {
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                sn = true;
                break mexit_Label;
            }
            //설명
            if (maind.mumu == true) {
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(mu);
                frame.getContentPane().revalidate();
                mu.repaint();
                exit_Label:
                while (true) {
                    System.out.print("");
                    if (mu.returnM == true) {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        MainDisplay maindr = new MainDisplay();
                        maind = maindr;
                        maind.mumu = false;
                        mu.returnM = false;
                        frame.getContentPane().add(maind);
                        frame.getContentPane().revalidate();
                        maind.repaint();
                        break exit_Label;
                    }
                }
            }
        }

        //스테이지1 화면
        Stage stage = new Stage();
        frame.getContentPane().add(stage);
        frame.getContentPane().revalidate();

        while (true) {
            if (sn == true) {
                System.out.print("");
                if (stage.next() == true) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();
                    game = true;
                    break;
                }
            }
        }
        Shootingspaceship ship = new Shootingspaceship();
       BossDisplay bd = new BossDisplay();
/*=======================================================================*/
        //슈팅게임
        exitG_Label:
        while (true) {
            if (game == true) {
                frame.getContentPane().add(ship);
                frame.getContentPane().revalidate();
                ship.setFocusable(true);
                ship.requestFocus();
                ship.start();
                
                
                exitBD_Label:
                while(true)
                {
                    System.out.print("");
                    if(ship.bossAlive == true)
                    {
                        ship.setThStop(true);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        break exitBD_Label;
                    }
                }
                
                frame.getContentPane().add(bd);
                frame.getContentPane().revalidate();
                
                exitRT_Label:
                while(true)
                {
                    System.out.print("");
                    if(bd.next() == true)
                    {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        frame.getContentPane().add(ship);
                        frame.getContentPane().revalidate();
                        ship.setFocusable(true);
                        ship.requestFocus();
                        ship.wakeup();
                        bd.next = false;
                        break exitRT_Label;
                    }
                }
                break exitG_Label;
            }
        }

        //스테이지2 화면
        while (true) {
            System.out.print("");
            if (ship.stage == 2) {
                ship.setThStop(true);
                stage.next = false;
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(stage);
                frame.getContentPane().revalidate();
                stage.startTimer2.start();
                break;
            }
        }

        // 게임 화면
        exitG_Label:
        while (true) {
            System.out.print("");
            if (stage.next() == true) {
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(ship);
                frame.getContentPane().revalidate();
                ship.setFocusable(true);
                ship.requestFocus();
                ship.wakeup();
                
                
                exitBD_Label:
                while(true)
                {
                    System.out.print("");
                    if(ship.bossAlive == true)
                    {
                        ship.setThStop(true);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        break exitBD_Label;
                    }
                }
                frame.getContentPane().add(bd);
                frame.getContentPane().revalidate();
                
                exitRT_Label:
                while(true)
                {
                    System.out.print("");
                    if(bd.next() == true)
                    {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        frame.getContentPane().add(ship);
                        frame.getContentPane().revalidate();
                        ship.setFocusable(true);
                        ship.requestFocus();
                        ship.wakeup();
                        bd.next = false;
                        break exitRT_Label;
                    }
                }
                break exitG_Label;
            }
        }

        //스테이지3 화면
        while (true) {
            System.out.print("");
            if (ship.stage == 3) {
                ship.setThStop(true);
                stage.next = false;
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(stage);
                frame.getContentPane().revalidate();
                stage.startTimer2.start();
                break;
            }
        }
        // 게임 화면
        exitG_Label:
        while (true) {
            System.out.print("");
            if (stage.next() == true) {
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(ship);
                frame.getContentPane().revalidate();
                ship.setFocusable(true);
                ship.requestFocus();
                ship.wakeup();
                
                
                exitBD_Label:
                while(true)
                {
                    System.out.print("");
                    if(ship.bossAlive == true)
                    {
                        ship.setThStop(true);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        break exitBD_Label;
                    }
                }
                frame.getContentPane().add(bd);
                frame.getContentPane().revalidate();
                
                exitRT_Label:
                while(true)
                {
                    System.out.print("");
                    if(bd.next() == true)
                    {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        frame.getContentPane().add(ship);
                        frame.getContentPane().revalidate();
                        ship.setFocusable(true);
                        ship.requestFocus();
                        ship.wakeup();
                        bd.next = false;
                        break exitRT_Label;
                    }
                }
                break exitG_Label;
            }
        }
        //스테이지4 화면
        while (true) {
            System.out.print("");
            if (ship.stage == 4) {
                ship.setThStop(true);
                stage.next = false;
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(stage);
                frame.getContentPane().revalidate();
                stage.startTimer2.start();
                break;
            }
        }
        // 게임 화면
        exitG_Label:
        while (true) {
            System.out.print("");
            if (stage.next() == true) {
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(ship);
                frame.getContentPane().revalidate();
                ship.setFocusable(true);
                ship.requestFocus();
                ship.wakeup();
                
                
                exitBD_Label:
                while(true)
                {
                    System.out.print("");
                    if(ship.bossAlive == true)
                    {
                        ship.setThStop(true);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        break exitBD_Label;
                    }
                }
                frame.getContentPane().add(bd);
                frame.getContentPane().revalidate();
                
                exitRT_Label:
                while(true)
                {
                    System.out.print("");
                    if(bd.next() == true)
                    {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        frame.getContentPane().add(ship);
                        frame.getContentPane().revalidate();
                        ship.setFocusable(true);
                        ship.requestFocus();
                        ship.wakeup();
                        bd.next = false;
                        break exitRT_Label;
                    }
                }
                break exitG_Label;
            }
        }
        
        
        
        
        //*********
        exitG_Label:
        while(true)
        {
            System.out.print("");
            if(ship.bossAlive == false)
            {
                exitBD_Label:
                while(true)
                {
                    System.out.print("");
                    if(ship.bossAlive == true)
                    {
                        ship.setThStop(true);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        break exitBD_Label;
                    }
                }
                frame.getContentPane().add(bd);
                frame.getContentPane().revalidate();

                exitRT_Label:
                while(true)
                {
                    System.out.print("");
                    if(bd.next() == true)
                    {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        frame.getContentPane().add(ship);
                        frame.getContentPane().revalidate();
                        ship.setFocusable(true);
                        ship.requestFocus();
                        ship.wakeup();
                        bd.next = false;
                        break exitRT_Label;
                    }
                }
                break exitG_Label;
            }
        }

        //스테이지5 화면
        while (true) {
            System.out.print("");
            if (ship.stage == 5) {
                ship.setThStop(true);
                stage.next = false;
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(stage);
                frame.getContentPane().revalidate();
                stage.startTimer2.start();
                break;
            }
        }
        // 게임 화면
        exitG_Label:
        while (true) {
            System.out.print("");
            if (stage.next() == true) {
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(ship);
                frame.getContentPane().revalidate();
                ship.setFocusable(true);
                ship.requestFocus();
                ship.wakeup();
                
                
                exitBD_Label:
                while(true)
                {
                    System.out.print("");
                    if(ship.bossAlive == true)
                    {
                        ship.setThStop(true);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        break exitBD_Label;
                    }
                }
                frame.getContentPane().add(bd);
                frame.getContentPane().revalidate();
                
                exitRT_Label:
                while(true)
                {
                    System.out.print("");
                    if(bd.next() == true)
                    {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        frame.getContentPane().add(ship);
                        frame.getContentPane().revalidate();
                        ship.setFocusable(true);
                        ship.requestFocus();
                        ship.wakeup();
                        bd.next = false;
                        break exitRT_Label;
                    }
                }
                break exitG_Label;
            }
        }
        
        
        
        
        //*********
        exitG_Label:
        while(true)
        {
            System.out.print("");
            if(ship.bossAlive == false)
            {
                exitBD_Label:
                while(true)
                {
                    System.out.print("");
                    if(ship.bossAlive == true)
                    {
                        ship.setThStop(true);
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        break exitBD_Label;
                    }
                }
                frame.getContentPane().add(bd);
                frame.getContentPane().revalidate();

                exitRT_Label:
                while(true)
                {
                    System.out.print("");
                    if(bd.next() == true)
                    {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().invalidate();
                        frame.getContentPane().add(ship);
                        frame.getContentPane().revalidate();
                        ship.setFocusable(true);
                        ship.requestFocus();
                        ship.wakeup();
                        bd.next = false;
                        break exitRT_Label;
                    }
                }
                break exitG_Label;
            }
        }


        while (true) {
            System.out.print("");
            if (ship.stage == 6) {
                System.out.print("");
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                break;
            }
        }
        ship.setThStop(true);
        //엔딩 화면
        Ending ending = new Ending();
        frame.getContentPane().add(ending);
        frame.getContentPane().revalidate();
        GameBgm.BgmList.get(6).stopSound();

        while (true) {
            ending.repaint();
            System.out.print("");
            if (ending.next() == true) {
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                break;
            }
        }

        //엔딩 크레딧
        Credit endCredit = new Credit();
        frame.getContentPane().add(endCredit);
        frame.getContentPane().revalidate();

        while (true) {
            System.out.println(endCredit.end);
            if (endCredit.end == true) {
                break;
            }
        }
        System.exit(0);
    }
}
