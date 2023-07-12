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

/**
 *
 * @author 김애리
 */
public class BossShotLaser extends Shot {
    Image img = null;
    String fileroad = "src/Image/Shot/Laser.png";
    public BossShotLaser(int x, int y){
        super(x,y);
    }
    
    public void changeShot()
    {
        fileroad = "src/Image/Shot/LaserShot.png";
    }
    
    @Override
    public void drawShot(Graphics g) {
        if (!alive) {
            return;
        }
        File f = new File(fileroad);
        try {
            img = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        s_height = img.getHeight(null);
        s_width = img.getWidth(null);
        g.drawImage(img, x_pos - s_width/2, y_pos - s_height/2, null);
    }
}