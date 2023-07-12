package Code.Player;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author SM_L
 */
public class PersonStateDraw {

    private int heartX = 0;
    private int heartY = 7;
    private int bulletX = 0;
    private int bulletY = 35;
    Image imgHr = null;
    Image imgHb = null;
    Image imgB = null;
    Image imgUI = null;
    public Font font;
    public PersonStateDraw() {
        File f = new File("src/Image/Player/playerHeartRed.png");
        try {
            imgHr = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        f = new File("src/Image/Player/playerHeartBlack.png");
        try {
            imgHb = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        f = new File("src/Image/Player/playerFshot.png");
        try {
            imgB = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        f = new File("src/Image/Background/UI.png");
        try {
            imgUI = ImageIO.read(f);
        } catch (IOException ex) {
            System.exit(1);
        }
        font = new Font("나눔고딕", Font.BOLD, 20);
    }

    public void drawState(Graphics g, Person p, int score) {
        g.drawImage(imgUI, 0, 0, null);
        for (int i = 0; i < p.getHp(); ++i) {
            g.drawImage(imgHr, (heartX+i)*23, heartY, null);
        }
        for (int i = p.getMaxHp()-1; p.getHp() <= i; --i) {
            g.drawImage(imgHb, (heartX+i)*23, heartY, null);
        }
        for (int i = 0; i < p.getFshot(); ++i) {
            g.drawImage(imgB, (bulletX+i)*23, bulletY, null);
        }
        g.setFont(font);
        String sc = String.valueOf(score);
        g.drawString(sc, 930, 22);
        g.drawString("원", 1065, 22);
    }

}