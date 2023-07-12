package shootingspaceship;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author 김애리
 */
public class Stage extends JPanel {

    public static int cnt2 = 0;
    public javax.swing.Timer startTimer2;
//    private int stage;

    public boolean next = false;
    //S1
    private ArrayList<Image> startImg;
    private ImageIcon s;
    private Image img;
//    private ImageIcon s1 = new ImageIcon("src/Image/Background/S1.gif");
//    private Image S1 = s1.getImage();
//    //S2
//    private ImageIcon s2 = new ImageIcon("src/Image/Background/S2.gif");
//    private Image S2 = s2.getImage();
//    //S3
//    private ImageIcon s3 = new ImageIcon("src/Image/Background/S3.gif");
//    private Image S3 = s3.getImage();
//    //S4
//    private ImageIcon s4 = new ImageIcon("src/Image/Background/S4.gif");
//    private Image S4 = s4.getImage();
//    //S5
//    private ImageIcon s5 = new ImageIcon("src/Image/Background/S5.gif");
//    private Image S5 = s5.getImage();

    public class timee implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            cnt2 += 1;
        }
    }

    public Stage() {
        startImg = new ArrayList();
        for(int i = 1; i<=5; ++i) {
            s = new ImageIcon("src/Image/Background/S"+i+".gif");
            img = s.getImage();
            startImg.add(img);
        }
        startTimer2 = new javax.swing.Timer(1000, new timee());
        

        setPreferredSize(new Dimension(1100, 600));
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        //S1
        super.paintComponent(g);
        if (Shootingspaceship.stage == 1) {
            g.drawImage(startImg.get(0), 0, 0, 1100, 600, this);
            startTimer2.start();
            if (cnt2 == 6) {
                next = true;
                startTimer2.stop();
                cnt2 = 0;
            }
        }
        else if (Shootingspaceship.stage == 2 && next == false) {
            g.drawImage(startImg.get(1), 0, 0, 1100, 600, this);
            if (cnt2 == 6) {
                next = true;
                startTimer2.stop();
                cnt2 = 0;
            }
        }

        else if (Shootingspaceship.stage == 3 && next == false) {
            g.drawImage(startImg.get(2), 0, 0, 1100, 600, this);
            if (cnt2 == 7) {
                next = true;
                startTimer2.stop();
                cnt2 = 0;
            }
        }
        else if (Shootingspaceship.stage == 4 && next == false) {
            g.drawImage(startImg.get(3), 0, 0, 1100, 600, this);
            if (cnt2 == 8) {
                next = true;
                startTimer2.stop();
                cnt2 = 0;
            }
        }
        else if (Shootingspaceship.stage == 5 && next == false) {
            g.drawImage(startImg.get(4), 0, 0, 1100, 600, this);
            if (cnt2 == 6) {
                next = true;
                startTimer2.stop();
                cnt2 = 0;
            }
        }
    }

    boolean next() {
        return next;
    }

}