package Code.Enemy;

import Code.Shot.EnemyShot;
import shootingspaceship.Enemy;

import javax.swing.*;
import java.util.ArrayList;

public class S4EnemyCigarette extends Enemy { //Enemy상속

    public S4EnemyCigarette(int x, int y, float delta_x, float delta_y, int max_x, int max_y, float delta_y_inc) {
        super(x, y, delta_x, delta_y, max_x, max_y, delta_y_inc);
        hp = 7;
        max_hp = 7;
        
        EnemyImg = new ArrayList();
        for (int i = 1; i <= 3; ++i) {
            icon = new ImageIcon("src/Image/Enemy/S4EnemyCigarette" + i + ".png");
            img = icon.getImage();
            EnemyImg.add(img);
        }
        e_height = img.getHeight(null);
        e_width = img.getWidth(null);
    }

    public EnemyShot generateShot() {
        EnemyShot eshot = new EnemyShot((int) x_pos, (int) y_pos, "src/Image/Shot/S4Cigaretteshot.png");
        return eshot;
    }
}
