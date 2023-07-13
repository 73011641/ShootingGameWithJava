package Code.Enemy;
import Code.Shot.*;
import shootingspaceship.*;

import javax.swing.*;
import java.util.ArrayList;

public class S1BossDuck extends Boss{

    public S1BossDuck(int x, int y, float delta_x, float delta_y, int max_x, int max_y, float delta_y_inc, Player aim) {
        super(x, y, delta_x, delta_y, max_x, max_y, delta_y_inc, aim);
        stage_authority = 1;
        collision_distance = 320;
        hp = 60;
        max_hp = 60;

        EnemyImg = new ArrayList();
        for(int i = 1; i <= 3; ++i){
            icon = new ImageIcon("src/Image/Enemy/S1BossDuck"+i+".png");
            img = icon.getImage();
            EnemyImg.add(img);
        }
        e_width = 320;
        e_height = 320;
    }
    
    public void move() {
        //왼쪽 옆에서 튀어나와서 모습이 완전히 나타나면 고정
        //System.out.println(hp);
        if (max_hp * 2/3 < hp ) {
            state = 1;
            x_pos -= delta_x;
            if (x_pos < 850) {
                x_pos = 850;
                //지정한 위치에 도착하면 위 아래로는 계속해서 움직임
                y_pos += delta_y;
                if (y_pos + e_height/2 > max_y) { //아래쪽 벽에 닿았을 때
                    y_pos = max_y - e_height/2;
                    delta_y = -delta_y;
                } else if (y_pos < e_height/2) { //위쪽 벽에 닿았을 때
                    y_pos = e_height/2;
                    delta_y = -delta_y;
                }
            }
        } else if (max_hp * 1/3  < hp && hp <= max_hp * 2/3) {
            x_pos -= delta_x;
            if (x_pos < 850) {
                x_pos = 850;
                if (y_pos == 300) {
                    state = 1;
                }
            }
            if (y_pos < 300) {
                y_pos += 1;
                if (y_pos > 300) {
                    y_pos = 300;
                }
            } else if (y_pos > 300) {
                y_pos -= 1;
                if (y_pos < 300) {
                    y_pos = 300;
                }
            }
        } else if (hp <= max_hp * 1/3) {
            state = 1;
            x_pos -= delta_x * 1.5f;
            y_pos += delta_y * 1.5f;
            if (x_pos + e_width/2 > max_x) { //오른쪽 벽에 닿았을 때
                x_pos = max_x - e_width/2;
                delta_x = -delta_x;
            } else if (x_pos <= 850) {
                x_pos = 850;
                delta_x = -delta_x;
            }
            if (y_pos + e_height/2> max_y) { //아래쪽 벽에 닿았을 때
                y_pos = max_y - e_height/2;
                delta_y = -delta_y;
            } else if (y_pos < e_height/2) { //위쪽 벽에 닿았을 때
                y_pos = e_height/2;
                delta_y = -delta_y;
            }
        }
    }
    
     /*상태1*/
    //구불구불 삼각함수 사용
    public BossShot333 generateShotB3(int delta_y) { // 보스의 삼각함수
        BossShot333 Bshot = new BossShot333((int)x_pos - e_width/2, (int)y_pos, delta_y, "src/Image/Shot/S1BossShot.png");
        return Bshot;
    }
    //한발 두발 세발
    public BossShotAdd generateShotBA(int delta_y) { // 보스의 애드 샷
        BossShotAdd Bshot2 = new BossShotAdd((int)x_pos - e_width/2, (int)y_pos, delta_y, "src/Image/Shot/S1BossShot.png");
        return Bshot2;
    }
}