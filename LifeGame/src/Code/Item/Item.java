package Code.Item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author SM_L
 */
public class Item {
    protected boolean alive;
    protected int x;
    protected int y;
    protected float delta_x = 1;
    protected int i_width;
    protected int i_height;
    Image img = null;
    
    public Item(int x, int y) {
        File f = new File("src/Image/Item/ItemTest.png");
        try {
            img = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        i_width = img.getWidth(null);
        i_height = img.getHeight(null);
        this.x = x;
        this.y = y;
        this.alive = true;
    }
    
    public boolean getAlive() { //살아있는지 상태를 구함
        return alive;
    }

    public void setAlive(boolean value) { //alive의 상태를 지정
        alive = value;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void drawItem(Graphics g) {
        g.drawImage(img, x-i_width/2, y-i_height/2, null);
    }
    
    public void move() {
        x-=delta_x; // +=좌->우 / -=우->좌 적 움직임
        if(x < 0){ //왼쪽 벽에 닿았을 때
            alive = false;
        }
    }
}