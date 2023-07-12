package Code.Player;

import Code.Item.*;
import Code.Shot.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.swing.ImageIcon;
import shootingspaceship.*;
import static shootingspaceship.Shootingspaceship.stage;

public class Person extends Player {

    private int max_hp = 0;
    private int max_finishShot = 4;
    private int player_hp = 0; // 플레이어 HP
    private int player_finishShot = 4; //플레이어 필살기
    private int p_width;
    private int p_height;
    private ArrayList<Image> playerImage = new ArrayList<>();
    private ArrayList<Image> playerWeaponImage = new ArrayList<>();
    public static ArrayList<Boolean> playerGrowth = new ArrayList<>();

    public Person(int x, int y, int min_x, int max_x, int min_y, int max_y, int player_hp) {
        super(x, y, min_x, max_x, min_y, max_y);
        this.player_hp = player_hp;
        this.max_hp = player_hp;

        Image imgP, imgW = null;

        for (int i = 1; i < 7; ++i) {
            imgP = new ImageIcon("src/Image/Player/playerImage" + i + ".png").getImage();
            imgW = new ImageIcon("src/Image/Player/playerWeapon" + i + ".png").getImage();
            playerImage.add(imgP);
            playerWeaponImage.add(imgW);
        }
    }

    public NormalShot generateShot() {
        NormalShot shot = new NormalShot(x_pos + p_width / 2, y_pos + 10);
        return shot;
    }

    public FinishShot generateFShot() {
        FinishShot shot = new FinishShot(x_pos + p_width / 2 + 175, y_pos + 10);
        return shot;
    }

    public boolean fshotState() {
        if (player_finishShot > 0) {
            return true;
        }
        return false;
    }

    public void fshotDown() {
        if (player_finishShot > 0) {
            player_finishShot--;
        }
    }

    public void fshotUp() {
        if (player_finishShot < max_finishShot) {
            ++player_finishShot;
        }
    }

    public int getFshot() {
        return player_finishShot;
    }

    public boolean hpDown() {
        if (--player_hp == 0) //hp를 감소시키고 후에 0인지 검사함
        {
            return false;
        }
        return true;
    }

    public void hpUp() {
        if (player_hp < max_hp) {
            ++player_hp;
        }
    }

    public void hpSet(int value) {
        this.player_hp = value;
    }
    
    public void maxHpSet(int value) {
        this.max_hp = value;
    }

    public int getHp() {
        return player_hp;
    }

    public int getMaxHp() {
        return max_hp;
    }

    public boolean isCollidedWithEshot(ArrayList shot) {
        Iterator it = shot.iterator();
        while (it.hasNext()) {
            Shot es = (Shot) it.next();
            if(es instanceof BossShotLaser){
                if(0 < x_pos && x_pos < 690){
                    if(250 < y_pos && y_pos < 350)
                        return true;
                }
            }
            else if (-p_height / 2 <= (y_pos - es.getY()) && ((y_pos - es.getY()) <= p_height / 2)) {
                if (-p_width / 2 <= (x_pos - es.getX()) && (x_pos - es.getX() <= p_width / 2)) {
                    if (es.getAlive()) // shot이 존재하면
                    {
                        es.collided(); // 해당 총알객체 메소드 호출 => alive = false;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCollidedWithItem(List items) {
        Iterator it = items.iterator();
        while (it.hasNext()) {
            Item tempItem = (Item) it.next();
            if (-p_height / 2 <= (y_pos - tempItem.getY()) && (y_pos - tempItem.getY() <= p_height / 2)) {
                if (-p_width / 2 <= (x_pos - tempItem.getX()) && (x_pos - tempItem.getX() <= p_width / 2)) {
                    if (tempItem.getAlive()) {
                        tempItem.setAlive(false);
                        if (tempItem instanceof HeartItem) {
                            hpUp();
                        }
                        if (tempItem instanceof FinishShotItem) {
                            fshotUp();
                        }
                        return true;
                    }
                }
            }
        }
        return false; // 아니면 false
    }

    @Override // 플레이어 이미지 구현
    public void drawPlayer(Graphics g) {
        Image imgP, imgW = null;

        imgP = playerImage.get(stage - 1);
        p_height = imgP.getHeight(null);
        p_width = imgP.getWidth(null);
        g.drawImage(imgP, x_pos - (p_width / 2), y_pos - (p_height / 2), null);
        imgW = playerWeaponImage.get(stage - 1);
        g.drawImage(imgW, x_pos + p_width / 2 - imgW.getWidth(null) / 2, y_pos + 10 - imgW.getHeight(null) / 2, null);
    }
}
