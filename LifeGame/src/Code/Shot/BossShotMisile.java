package Code.Shot;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import shootingspaceship.*;


//기본이 되는 보스 샷
public class BossShotMisile extends Shot {

    public int delta_y;
    boolean near = false;
    Player aim;
    Image img = null;
    public BossShotMisile(int x, int y, int delta_y, Player aim){
        super(x,y);
        this.delta_y = delta_y;
        this.aim = aim; //유도 미사일의 목표
        File f = new File("src/Image/Shot/Misile.png");
        try {
            img = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        s_height = img.getHeight(null);
        s_width = img.getWidth(null);
    }
    
    public void moveShot(int speed) {
        float dx = x_pos - aim.getX();
        float dy = y_pos - aim.getY();
        double degree = Math.atan2(dx, dy); //tan세타 역으로
        degree = (degree * 180) / Math.PI; //각도값
        
        if( Math.abs(dx) < 150  && Math.abs(dy) < 150)
        {
            near = true;
        }
        
        if(near == true)
        {
            x_pos -= 10;
        }
        else if(near == false)
        {
            x_pos -= 2*delta_y + Math.sin(Math.toRadians(degree)) * speed *3;
            y_pos -= 3*delta_y + Math.cos(Math.toRadians(degree)) * speed *3;
        }
        
    }
    
    @Override
    public void drawShot(Graphics g) {
        if (!alive) {
            return;
        }
        g.drawImage(img, x_pos - s_width/2, y_pos - s_height/2, null);
    }
}
