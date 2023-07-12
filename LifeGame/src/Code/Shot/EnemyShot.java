package Code.Shot;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import shootingspaceship.Shot;

public class EnemyShot extends Shot {
    Image img = null;
    public EnemyShot(int x, int y, String fileroad){
        super(x,y);
        File f = new File(fileroad);
        try {
            img = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        s_width = img.getWidth(null);
        s_height = img.getHeight(null);
    }

    public void moveShot(int speed) {
        x_pos -= speed;
    }
    
    @Override
    public void drawShot(Graphics g) {
        if (!alive) {
            return;
        }
        g.drawImage(img, x_pos - s_width/2, y_pos - s_height/2, null);
    }
     public EnemyShot generateShot() {
        EnemyShot eshot = new EnemyShot((int)x_pos, (int)y_pos, "src/Image/Shot/S1Mobileshot.png");
        return eshot;
    }
}