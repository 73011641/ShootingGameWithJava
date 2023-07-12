package shootingspaceship;

import Code.Enemy.*;
import Code.Item.*;
import Code.Shot.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class GameSetting {

    public List<Enemy> AllOfEnemy;
    public ArrayList<Item> AllOfItem;
    public ArrayList<NormalShot> AllOfPlayerShot;
    public ArrayList<EnemyShot> AllOfEnemyShot;
    public ArrayList<BossShot333> AllOfBossShot333;
    
    Random rand = new Random(1);
    private int width = 1100;
    private int height = 600;
    private final float enemyDownSpeedInc = 0.3f;
    private final int enemyMaxDownSpeed = 1;
    private final int enemyMaxHorizonSpeed = 1;
    private float downspeed = (rand.nextFloat() * enemyMaxDownSpeed) + 0.5f; //적마다 속도 랜덤으로지정
    float horspeed = rand.nextFloat() * 2 * enemyMaxHorizonSpeed - enemyMaxHorizonSpeed;
    private final int maxEnemySize = 10;
    private final int maxBossSize = 1;
    private final int maxPlayerShotSize = 20;

    public GameSetting(Player aim) {
        AllOfEnemy = Collections.synchronizedList(new ArrayList<Enemy>());
        AllOfPlayerShot = new ArrayList<NormalShot>();
        
        for(int i=0; i<15; ++i){
            AllOfEnemy.add(new S1EnemyMobile(width + 64, (int) (rand.nextFloat() * height), downspeed, horspeed, width, height, enemyDownSpeedInc));
        }
        AllOfEnemy.add(new S1BossDuck(width + 320, (int) 300, 1, 1, width, height, enemyDownSpeedInc, aim));
        /* ===================================================================== */
        /* ===================================================================== */
        for(int i=0; i<maxEnemySize; ++i){
            AllOfEnemy.add(new S2EnemyPmang(width + 64, (int) (rand.nextFloat() * height)+ 32, downspeed, horspeed, width, height, enemyDownSpeedInc));
            if(i % 2 == 1){
                AllOfEnemy.add(new S2EnemyBeans(width + 128, (int) (rand.nextFloat() * 2 * height), downspeed, horspeed, width, height, enemyDownSpeedInc));
            }
        } // 2스테이지
        AllOfEnemy.add(new S2BossWooeong(width + 320, (int) 300, 1, 1, width, height, enemyDownSpeedInc, aim)); //2보스
        /* ===================================================================== */
        /* ===================================================================== */
        for(int i=0; i<maxEnemySize; ++i){
            if(i % 2 == 1){
                AllOfEnemy.add(new S3EnemyMathstd(width + 128, (int) (rand.nextFloat() * 2 * height) + 32 , downspeed, horspeed, width, height, enemyDownSpeedInc));
            }
            AllOfEnemy.add(new S3EnemyOmr(width + 125, (int) (rand.nextFloat() * height), downspeed, horspeed, width, height, enemyDownSpeedInc));
        } // 3스테이지
        AllOfEnemy.add(new S3BossSooneung(width + 320, (int) 300, 1, 1, width, height, enemyDownSpeedInc, aim)); //3보스
        /* ===================================================================== */
        /* ===================================================================== */
        for(int i=0; i<maxEnemySize; ++i){
            if(i % 2 == 1){
                AllOfEnemy.add(new S4EnemyCigarette(width + 128, (int) (rand.nextFloat() * 2 * height) + 32, downspeed, horspeed, width, height, enemyDownSpeedInc));
            }
            AllOfEnemy.add(new S4EnemySoju(width + 64, (int) (rand.nextFloat() * height), downspeed, horspeed, width, height, enemyDownSpeedInc));
        } // 4-1스테이지
        AllOfEnemy.add(new S4BossGetjob(width + 320, (int) 300, 1, 1, width, height, enemyDownSpeedInc, aim)); //4-1보스
        /* ===================================================================== */
        /* ===================================================================== */
        for(int i=0; i<maxEnemySize; ++i){
            if(i % 2 == 1){
                AllOfEnemy.add(new S4EnemyCard(width + 128, (int) (rand.nextFloat() * height) + 64, downspeed, horspeed, width, height, enemyDownSpeedInc));
            }
            AllOfEnemy.add(new S4EnemySoju(width + 64, (int) (rand.nextFloat() * height), downspeed, horspeed, width, height, enemyDownSpeedInc));
        } // 4-2스테이지
        AllOfEnemy.add(new S4BossWedding(width + 320, (int) 300, 1, 1, width, height, enemyDownSpeedInc, aim)); //4-2보스
        /* ===================================================================== */
        /* ===================================================================== */
        for(int i=0; i<maxEnemySize; ++i){
            if(i % 2 == 1){
                AllOfEnemy.add(new S5EnemyLung(width + 250, (int) (rand.nextFloat() * 2 * height) + 32, downspeed, horspeed, width, height, enemyDownSpeedInc));
            }
            AllOfEnemy.add(new S5EnemyBone(width + 64, (int) (rand.nextFloat() * height), downspeed, horspeed, width, height, enemyDownSpeedInc));
        } // 5-1스테이지
        AllOfEnemy.add(new S5BossSick(width + 320, (int) 300, 1, 1, width, height, enemyDownSpeedInc, aim)); // 5-1보스
        /* ===================================================================== */
        /* ===================================================================== */
        for(int i=0; i<maxEnemySize; ++i){
            if(i % 2 == 1){
                AllOfEnemy.add(new S5EnemyBrain(width + 128, (int) (rand.nextFloat() * height) + 64, downspeed, horspeed, width, height, enemyDownSpeedInc));
            }
            AllOfEnemy.add(new S5EnemyBone(width + 64, (int) (rand.nextFloat() * height), downspeed, horspeed, width, height, enemyDownSpeedInc));
        } // 5-2스테이지
        AllOfEnemy.add(new S5BossDeath(width + 320, (int) 300, 1, 1, width, height, enemyDownSpeedInc, aim)); // 5-2보스
        /* ===================================================================== */
        /* ===================================================================== */        
        /* ===================================================================== */
        /* ===================================================================== */
        for(int i=0; i<maxPlayerShotSize; ++i){
            AllOfPlayerShot.add((NormalShot) aim.generateShot());
        }
    }
    
    public void lendE(List inMain) {
        inMain.add(AllOfEnemy.get(0));
        AllOfEnemy.remove(0);
    }
    
    public void receiveE(Enemy e, Iterator it) {
        AllOfEnemy.add(e);
        it.remove();
        // System.out.println(AllOfEnemy.get(AllOfEnemy.size()-1).hp);// 객체가 끝에 잘 반납되는지 테스트
    }
    
    public void remainE(int remainNumber) {
        for(int i=0; i<remainNumber; ++i){
            AllOfEnemy.add(AllOfEnemy.get(0));
            AllOfEnemy.remove(0);
        }
    }
}
