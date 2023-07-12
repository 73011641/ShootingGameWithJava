package Code.Item;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author SM_L
 */
public class FinishShotItem extends Item{
    
    public FinishShotItem (int x, int y){
        super(x, y);
        File f = new File("src/Image/Item/ItemFinish.png");
        try {
            img = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        i_width = img.getWidth(null);
        i_height = img.getHeight(null);
    }
    
    public void drawItem(Graphics g) {
        g.drawImage(img, x-i_width/2, y-i_height/2, null);
    }
}