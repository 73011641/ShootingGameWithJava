package Code.Shot;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import static shootingspaceship.Shootingspaceship.stage;
import shootingspaceship.Shot;

public class FinishShot extends Shot {
    private int f_width;
    private int f_height;
    private ArrayList<Image> FinishShotImage = new ArrayList<>();
   
    public FinishShot(int x, int y) {
        super(x, y);
        Image imgFshot= null;

    for (int i = 1; i < 6; ++i) {
            imgFshot = new ImageIcon("src/Image/Shot/FinishShot" + i+ ".png").getImage();
            FinishShotImage.add(imgFshot);   
        }
    }

    @Override
    public void moveShot(int speed) {
        super.moveShot(-6); //
    }

    public void drawShot(Graphics g) {
        Image imgFshot= null;
        imgFshot = FinishShotImage.get(stage-1);
        f_width = imgFshot.getHeight(null);
        f_height = imgFshot.getWidth(null);
        
        if (!alive) {
            return;
        }
        g.drawImage(imgFshot, x_pos - (f_width / 2), y_pos - (f_height / 2), null);
    }
}
