/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootingspaceship;

import java.awt.Graphics;
import java.awt.Color;
import Code.Shot.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author wgpak
 */
public class Enemy{

    public int state = 0;
    public int stage_authority; //보스에서 사용하는 스테이지를 넘길 수 있는 권한(1일때 스테이지가 넘어간다)
    protected int e_height;
    protected int e_width;
    protected float x_pos;
    protected float y_pos;
    protected float delta_x;
    protected float delta_y;
    protected int max_x;
    protected int max_y;
    protected float delta_y_inc;
    protected int collision_distance; //충돌 거리 반경
    //S3적 이랑 S4적 x축 충돌 반경때문에 hp 일단 protected로 변경 원래 private 였음
    protected int hp; // 적의 HP
    protected int max_hp;
    public int count = 0;
    public boolean alive; // HP 구현을 위한 필드
    /**
     * 이미지 그리는 변수*
     */
    protected ArrayList<Image> EnemyImg; //이미지 배열
    protected ImageIcon icon; //이미지 로딩
    protected Image img; //이미지 객체

    public Enemy(int x, int y, float delta_x, float delta_y, int max_x, int max_y, float delta_y_inc) {
        x_pos = x;
        y_pos = y;
        this.delta_x = delta_x;
        this.delta_y = delta_y;
        this.max_x = max_x;
        this.max_y = max_y;
        this.delta_y_inc = delta_y_inc;
        this.alive = true;
    }

    public void move() {
        x_pos -= delta_x; // +=좌->우 / -=우->좌 적 움직임
        y_pos += delta_y; // +=하->상 / -=상->하 적 움직임

        if (y_pos + e_height/2 > max_y) { //아래쪽 벽에 닿았을 때
            y_pos = max_y - e_height/2;
            delta_y = -delta_y;
        } else if (y_pos < e_height) { //위쪽 벽에 닿았을 때
            y_pos = e_height;
            delta_y = -delta_y;
        }
    }
//  isCollideWithShot Enemy가 총알과 충돌했는지 여부 검사

    public boolean isCollidedWithShot(ArrayList pShots) { //Shot 객체 배열을 파라미터로 받는다.
        Iterator it = pShots.iterator();
        while (it.hasNext()) {
            Shot ps = (Shot) it.next();
            if (ps instanceof FinishShot) {
                if (-(e_height / 2 + 175) <= (y_pos - ps.getY()) && (y_pos - ps.getY() <= e_height / 2 + 175)) {
                    if (-(e_height / 2 + 175) <= (x_pos - ps.getX()) && (x_pos - ps.getX() <= (e_height / 2 + 175))) {
                        if (ps.getAlive()) // shot이 존재하면
                        {
                            hp -= hp*1/100;
                            return true;
                        }
                    }
                }
            }
            if ((-(e_height / 2) <= (y_pos - ps.getY())) && ((y_pos - ps.getY()) <= (e_height / 2))) {
                if ((-(e_width / 2) <= (x_pos - ps.getX())) && ((x_pos - ps.getX()) <= (e_width / 2))) {
                    if (ps.getAlive()) { // shot이 존재하면
                        hp -= 1; //shot.getDamage;
                        ps.collided(); // 해당 총알객체 메소드 호출 => alive = false;
                        return true; // 충돌했으므로 true 리턴
                    }
                }
            }
        }
        return false; //충돌안했으니 false 리턴
    }

    public boolean isCollidedWithPlayer(Player player) {
        if (-e_height / 2 <= (y_pos - player.getY()) && (y_pos - player.getY() <= e_height / 2)) {
            if (-e_width <= (x_pos - player.getX()) && (x_pos - player.getX() <= e_width)) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        if ((int) ((max_hp / 2) + 1) < hp) {
            g.drawImage(EnemyImg.get(0), (int) x_pos - (e_width / 2), (int) y_pos - (e_height / 2), null);
        } else if ((int) max_hp / 3 < hp && hp <= (int) (max_hp / 2 + 1)) {
            g.drawImage(EnemyImg.get(1), (int) x_pos - (e_width / 2), (int) y_pos - (e_height / 2), null);
        } else if (hp <= (int) max_hp / 3) {
            g.drawImage(EnemyImg.get(2), (int) x_pos - (e_width / 2), (int) y_pos - (e_height / 2), null);
        }
    }

    public boolean getAlive() { //살아있는지 상태를 구함
        return alive;
    }

    public void setAlive(boolean value) { //alive의 상태를 지정
        this.alive = value;
    }

    public int getHp() { //Hp를 받아오고
        return hp;
    }

    public boolean pass() {
        if (x_pos < 0)// 왼쪽 벽을 통과했을 때
        {
            return true;
        } else {
            return false;
        }
    }

    public EnemyShot generateShot() {
        EnemyShot eshot = new EnemyShot((int) x_pos, (int) y_pos, "src/Image/Shot/S1MobileShot.png");
        return eshot;
    }

    public BossShot333 generateShotB3(int delta_y){
        return null;
    }

    public BossShotAdd generateShotBA(int delta_y){
        return null;
    }

    public BossShotLaser generateShotBL(){
        return null;
    }

    public BossShotMisile generateShotBM(int delta_y){
        return null;
    }
}
