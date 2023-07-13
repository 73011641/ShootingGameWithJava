/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code.Shot;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import shootingspaceship.Shot;

public class BossShotAdd extends Shot {
    public int delta_y;
    Image img = null;
    public BossShotAdd(int x, int y, int delta_y, String fileroad){
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
    
    
    @Override
    public void moveShot(int speed) {
        x_pos -= speed;
        y_pos -= delta_y;
    }
    
    @Override
    public void drawShot(Graphics g) {
        if (!alive) {
            return;
        }
        g.drawImage(img, x_pos - s_width/2, y_pos - s_height/2, null);
    }
}
