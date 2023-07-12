/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootingspaceship;

import java.awt.Graphics;
import java.awt.Color;

/**
 *
 * @author wgpak
 */
public class Shot {

    protected int x_pos;
    protected int y_pos;
    protected boolean alive;
    private final int radius = 3;
    public int count = 0;
    protected int s_width;
    protected int s_height;

    public Shot(int x, int y) {
        x_pos = x;
        y_pos = y;
        alive = true;
    }


    public int getY() {
        return y_pos;
    }

    public int getX() {
        return x_pos;
    }

    public void moveShot(int speed) {
        x_pos -= speed;
    }

    public void drawShot(Graphics g) {
        if (!alive) {
            return;
        }
        g.setColor(Color.yellow);
        g.fillOval(x_pos, y_pos, radius, radius);
    }

    public void collided() {
        alive = false;
    }
    
    public boolean getAlive()
    {
        return alive;
    }
}
