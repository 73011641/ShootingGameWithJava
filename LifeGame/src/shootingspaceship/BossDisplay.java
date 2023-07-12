package shootingspaceship;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static shootingspaceship.Stage.cnt2;

/**
 *
 * @author 김애리
 */
public class BossDisplay  extends JPanel {
    
    public static int cnt2 = 0;
    public javax.swing.Timer startTimer2;
    public boolean next = false;
    public boolean nb = false;
    //S1
    private ImageIcon s1 = new ImageIcon("src/Image/Background/s1b.gif");
    private Image S1 = s1.getImage();
    //S2
    private ImageIcon s2 = new ImageIcon("src/Image/Background/s2b.gif");
    private Image S2 = s2.getImage();
    //S3
    private ImageIcon s3 = new ImageIcon("src/Image/Background/s3b.gif");
    private Image S3 = s3.getImage();
    //S4
    private ImageIcon s4 = new ImageIcon("src/Image/Background/s4b.gif");
    private Image S4 = s4.getImage();
    //S41
    private ImageIcon s41 = new ImageIcon("src/Image/Background/s4b1.gif");
    private Image S41 = s41.getImage();
    //S5
    private ImageIcon s5 = new ImageIcon("src/Image/Background/s5b.gif");
    private Image S5 = s5.getImage();
    //S51
    private ImageIcon s51 = new ImageIcon("src/Image/Background/s5b1.gif");
    private Image S51 = s51.getImage();
    
    
     public class timee implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            cnt2 += 1;
        }
    }
    
    
    public BossDisplay() {
        startTimer2 = new javax.swing.Timer(1000, new timee());
        setPreferredSize(new Dimension(1100, 600));
        setVisible(true);
    }
    
    public void paintComponent(Graphics g) {
        //S1
        super.paintComponent(g);
        if (Shootingspaceship.stage == 1 &&Shootingspaceship.bossAlive == true) {
            g.drawImage(S1, 0, 0, 1100, 600, this);
            startTimer2.start();
            if (cnt2 == 5) {
                next = true;
                startTimer2.stop();
                cnt2 = 0;
            }
        }
        //S2
        else if (Shootingspaceship.stage == 2 && next == false && Shootingspaceship.bossAlive == true) {
            g.drawImage(S2, 0, 0, 1100, 600, this);
            startTimer2.start();
            if (cnt2 == 5) {
                next = true;
                startTimer2.stop();
                cnt2 = 0;
            }
        }
        //S3
        else if (Shootingspaceship.stage == 3 && next == false && Shootingspaceship.bossAlive == true) {
            g.drawImage(S3, 0, 0, 1100, 600, this);
            startTimer2.start();
            if (cnt2 == 5) {
                next = true;
                startTimer2.stop();
                cnt2 = 0;
            }
        }
        //S4
        else if (Shootingspaceship.stage == 4 && next == false && Shootingspaceship.bossAlive == true) {
            if(nb == false)
            {
                g.drawImage(S4, 0, 0, 1100, 600, this);
                startTimer2.start();
                if (cnt2 == 5) {
                    next = true;
                    nb = true;
                    startTimer2.stop();
                    cnt2 = 0;
                }
            }
            else if(nb == true)
            {
                g.drawImage(S41, 0, 0, 1100, 600, this);
                startTimer2.start();
                if (cnt2 == 5) {
                    next = true;
                    nb = false;
                    startTimer2.stop();
                    cnt2 = 0;
                }
            }
        }
        //S5
        else if (Shootingspaceship.stage == 5 && next == false && Shootingspaceship.bossAlive == true) {
            if(nb == false)
            {
                g.drawImage(S5, 0, 0, 1100, 600, this);
                startTimer2.start();
                if (cnt2 == 5) {
                    next = true;
                    nb = true;
                    startTimer2.stop();
                    cnt2 = 0;
                }
            }
            else if(nb == true)
            {
                g.drawImage(S51, 0, 0, 1100, 600, this);
                startTimer2.start();
                if (cnt2 == 5) {
                    next = true;
                    nb = false;
                    startTimer2.stop();
                    cnt2 = 0;
                }
            }
        }
    }

    boolean next() {
        return next;
    }

}
