package Code.Shot;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import shootingspaceship.Shot;


//기본이 되는 보스 샷
public class BossShot333 extends Shot {
    public int delta_y;
    int rad = 180;
    Image img = null;
    public BossShot333(int x, int y, int delta_y, String fileroad){
        super(x,y);
        this.delta_y = delta_y;
        File f = new File(fileroad);
        try {
            img = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        s_height = img.getHeight(null);
        s_width = img.getWidth(null);
    }
    
    public void moveShot(int speed) {
        rad += 3;
        x_pos -= speed;
        y_pos += delta_y + (float) Math.cos(Math.toRadians(rad)) *-3;
    }

    @Override
    public void drawShot(Graphics g) {
        if (!alive) {
            return;
        }
        g.drawImage(img, x_pos - s_width/2, y_pos - s_height/2, null);
    }
}
