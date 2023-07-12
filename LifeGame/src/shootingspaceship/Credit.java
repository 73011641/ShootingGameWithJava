/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootingspaceship;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author 김애리
 */
public class Credit extends JPanel{
    
    
    public static int cnt4 = 0;
    public javax.swing.Timer startTimer4;
    public boolean end = false;
    
    private ImageIcon credit = new ImageIcon("src/Image/Background/엔딩 크레딧1.gif");
    private Image Credit = credit.getImage();
    
    private ImageIcon credit2 = new ImageIcon("src/Image/Background/엔딩 크레딧2.gif");
    private Image Credit2 = credit2.getImage();
    
    public class timeeee implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
             {
                 cnt4 += 1;
                 System.out.println(cnt4);
            }
        }
    
    public Credit()
    {
        startTimer4 = new javax.swing.Timer(1000, new timeeee());
                
        setPreferredSize(new Dimension(1100, 600));
        setVisible(true);
    }
    
    public void paintComponent(Graphics g)
        {   
                
                super.paintComponent(g);
                g.drawImage(Credit, 0, 0, 1100, 600, this);
                startTimer4.start();
                
                if(cnt4 >= 28 && cnt4 < 38)
                {
                    g.drawImage(Credit2, 0, 0, 1100, 600, this);
                    System.out.println("a");
                }
                if(cnt4 >= 38)
                {
                    end = true;
                    System.out.println("b");
                    startTimer4.stop();
                }
        }
}
