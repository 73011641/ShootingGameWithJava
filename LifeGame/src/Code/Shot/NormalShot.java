package Code.Shot;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import shootingspaceship.Shot;

public class NormalShot extends Shot {
    Image img = null;
    public NormalShot(int x, int y){
        super(x,y);
        File f = new File("src/Image/Shot/NormalShot.png");
        try {
            img = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        s_width = img.getWidth(null);
        s_height = img.getHeight(null);
    }
    
    @Override
    public void drawShot(Graphics g) {
        if (!alive) {
            return;
        }
        g.drawImage(img, x_pos - s_width/2 , y_pos - s_height/2, null);
    }
}
