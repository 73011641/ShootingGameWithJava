/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootingspaceship;

import Code.Player.Person;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static shootingspaceship.Shootingspaceship.score;
import static shootingspaceship.Shootingspaceship.stage;

/**
 *
 * @author 김애리
 */
public class Ending extends JPanel{
        Sound GameBgm = new Sound();
        String Endscore = String.valueOf(score);
        String a = Endscore;
        public static int cnt3 = 0;
        public static int displayNum = 0;
        public boolean next = false;
        
        public javax.swing.Timer startTimer3;
        
       
        //엔딩 로딩
        private ImageIcon endloading = new ImageIcon("src/Image/Background/로딩화면.gif");
        private Image endLoading = endloading.getImage();
        //엔딩1
        private ImageIcon end1 = new ImageIcon("src/Image/Background/엔딩1.png");
        private Image End1= end1.getImage();
        //엔딩2
        private ImageIcon end2 = new ImageIcon("src/Image/Background/엔딩2.png");
        private Image End2= end2.getImage();
        //엔딩3
        private ImageIcon end3 = new ImageIcon("src/Image/Background/엔딩3.png");
        private Image End3= end3.getImage();
        //엔딩4
        private ImageIcon end4 = new ImageIcon("src/Image/Background/엔딩4.png");
        private Image End4= end4.getImage();
        //엔딩5
        private ImageIcon end5 = new ImageIcon("src/Image/Background/엔딩5.png");
        private Image End5= end5.getImage();
        //엔딩6
        private ImageIcon end6 = new ImageIcon("src/Image/Background/엔딩6.png");
        private Image End6= end6.getImage();
        //엔딩7
        private ImageIcon end7 = new ImageIcon("src/Image/Background/엔딩7.png");
        private Image End7= end7.getImage();
        //엔딩8
        private ImageIcon end8 = new ImageIcon("src/Image/Background/엔딩8.png");
        private Image End8= end8.getImage();
        //사회환원
        private ImageIcon social = new ImageIcon("src/Image/Background/사회환원.png");
        private Image Social= social.getImage();
        
        
        //엔딩1
        private ImageIcon s1 = new ImageIcon("src/Image/Background/S1.jpg");
        private Image S1= s1.getImage();
        
        //엔딩2 해피
        private ImageIcon s2happy = new ImageIcon("src/Image/Background/S2-H.png");
        private Image S2Happy= s2happy.getImage();
        //엔딩2 새드
        private ImageIcon s2sad = new ImageIcon("src/Image/Background/S2-S.png");
        private Image S2Sad= s2sad.getImage();
        
        //엔딩3 해피
        private ImageIcon s3happy = new ImageIcon("src/Image/Background/S3-H.jpg");
        private Image S3Happy= s3happy.getImage();
        //엔딩3 새드
        private ImageIcon s3sad = new ImageIcon("src/Image/Background/S3-S.jpg");
        private Image S3Sad= s3sad.getImage();
        
        //엔딩4 해피 - 1
        private ImageIcon s41happy = new ImageIcon("src/Image/Background/S4-1H.jpg");
        private Image S41Happy= s41happy.getImage();
        //엔딩4 새드 - 1
        private ImageIcon s41sad = new ImageIcon("src/Image/Background/S4-1S.jpg");
        private Image S41Sad= s41sad.getImage();
        
        //엔딩4 해피 - 2
        private ImageIcon s42happy = new ImageIcon("src/Image/Background/S4-2H.jpg");
        private Image S42Happy= s42happy.getImage();
        //엔딩4 새드 - 2
        private ImageIcon s42sad = new ImageIcon("src/Image/Background/S4-2S.jpg");
        private Image S42Sad= s42sad.getImage();
        
        //엔딩5 해피 - 1
        private ImageIcon s51happy = new ImageIcon("src/Image/Background/S5-1H.jpg");
        private Image S51Happy= s51happy.getImage();
        //엔딩5 새드 - 1
        private ImageIcon s51sad = new ImageIcon("src/Image/Background/S5-1S.jpg");
        private Image S51Sad= s51sad.getImage();
        
        //엔딩5 해피 - 2
        private ImageIcon s52happy = new ImageIcon("src/Image/Background/S5-2H.jpg");
        private Image S52Happy= s52happy.getImage();
        //엔딩5 새드 - 2
        private ImageIcon s52sad = new ImageIcon("src/Image/Background/S5-2S.jpg");
        private Image S52Sad= s52sad.getImage();
        
        
        
        public class timeee implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
             {
                 cnt3 += 1;
            }
        }
        
        public Ending()
        {
                
                GameBgm.BgmList.get(11).PlaySound(true);
                startTimer3 = new javax.swing.Timer(1000, new Ending.timeee()); 
                setPreferredSize(new Dimension(1100, 600));
                setVisible(true);
                
        }
        
        public Image back = endLoading;
        
        public void paintComponent(Graphics g)
        {   
                super.paintComponent(g);
                g.drawImage(back, 0, 0, 1100, 600, this);
                
                //로딩화면
        
                if(displayNum == 0)
                {
                    g.drawImage(endLoading, 0, 0, 1100, 600, this);
                    startTimer3.start();
                    if(cnt3 ==5)
                    {
                        System.out.println("a");
                        displayNum++;
                        startTimer3.stop();
                        cnt3 = 0;
                    }
                }
                /*================================================================*/
                //인생은
                else if(displayNum == 1)
                {
                    back = End1;
                    g.setColor(Color.white);
                    g.setFont(new Font("맑은 고딕", Font.BOLD ,120));
                    g.drawString(MainDisplay.nameR, 110, 330);
                    repaint();
                    
                    startTimer3.start();
                    if(cnt3==3)
                    {
                        startTimer3.stop();
                        cnt3 = 0;
                        displayNum++;
                    }
                }
                
                /*================================================================*/
                //프레임창
                if(displayNum < 9 &&displayNum >= 2)
                {
                    g.drawImage(S1, 98, 65, 230, 185, this);
                    if(displayNum == 2)
                    {
                        back = End2;
                    }
                    startTimer3.start();
                    if(cnt3==3)
                    {
                        displayNum++;
                        startTimer3.stop();
                        cnt3 = 0;
                    }
                }
                if(displayNum < 9 && displayNum >= 3)
                {
                    if(displayNum == 3)
                    {
                        back = End3;
                    }
                    if(Person.playerGrowth.get(1) == true)
                    {
                        g.drawImage(S2Happy, 445, 65, 230, 185, this);
                    }
                    else
                    {
                        g.drawImage(S2Sad, 445, 65, 230, 185, this);
                    }
                    startTimer3.start();
                    if(cnt3==3)
                    {
                        displayNum++;
                        startTimer3.stop();
                        cnt3 = 0;
                    }
                }
                if(displayNum < 9 && displayNum >= 4)
                {
                    if(displayNum == 4)
                    {
                        back = End4;
                    }
                     if(Person.playerGrowth.get(2) == true)
                    {
                        g.drawImage(S3Happy, 792, 65, 230, 185, this);
                    }
                    else
                    {
                        g.drawImage(S3Sad, 792, 65, 230, 185,this);
                    }
                    startTimer3.start();
                    if(cnt3==3)
                    {
                        displayNum++;
                        startTimer3.stop();
                        cnt3 = 0;
                    }
                }
                if(displayNum < 9 && displayNum >= 5)
                {
                    if(displayNum == 5)
                    {
                        back = End5;
                    }
                    if(Person.playerGrowth.get(3) == true)
                    {
                        g.drawImage(S41Happy, 30, 310, 230, 185, this);
                    }
                    else
                    {
                        g.drawImage(S41Sad, 30, 310, 230, 185,this);
                    }
                    startTimer3.start();
                    if(cnt3==3)
                    {
                        displayNum++;
                        startTimer3.stop();
                        cnt3 = 0;
                    }
                }
                if(displayNum < 9 && displayNum >= 6)
                {
                    if(displayNum == 6)
                    {
                        back = End6;
                    }
                    if(Person.playerGrowth.get(4) == true)
                    {
                        g.drawImage(S42Happy, 299, 310, 230, 185, this);
                    }
                    else
                    {
                        g.drawImage(S42Sad, 299, 310, 230, 185,this);
                    }
                    startTimer3.start();
                    if(cnt3==3)
                    {
                        displayNum++;
                        startTimer3.stop();
                        cnt3 = 0;
                    }
                }
                if(displayNum < 9 && displayNum >= 7)
                {
                    if(displayNum == 7)
                    {
                        back = End7;
                    }
                    if(Person.playerGrowth.get(5) == true)
                    {
                        g.drawImage(S51Happy, 573, 310, 230, 185, this);
                    }
                    else
                    {
                        g.drawImage(S51Sad, 573, 310, 230, 185, this);
                    }
                    startTimer3.start();
                    if(cnt3==3)
                    {
                        displayNum++;
                        startTimer3.stop();
                        cnt3 = 0;
                    }
                }
                if(displayNum < 9 && displayNum >= 8)
                {
                    if(displayNum == 8)
                    {
                        back = End8;
                    }
                    if(Person.playerGrowth.get(6) == true)
                    {
                        g.drawImage(S52Happy, 842, 310, 230, 185, this);
                    }
                    else
                    {
                        g.drawImage(S52Sad, 842, 310, 230, 185, this);
                    }
                    
                    startTimer3.start();
                    if(cnt3==5)
                    {
                        displayNum++;
                        startTimer3.stop();
                        cnt3 = 0;
                    }
                }
                if(displayNum == 9)
                {
                    String Endscore = String.valueOf(score);
                    g.drawImage(Social, 0, 0, 1100, 600, this);
                    g.setFont(new Font("맑은 고딕", Font.BOLD ,70));
                    g.setColor(Color.white);
                    g.drawString(Endscore, 330, 430);
                     g.setFont(new Font("맑은 고딕", Font.BOLD ,65));
                    g.drawString(MainDisplay.nameR, 430, 330);

                    startTimer3.start();
                    if(cnt3==7)
                    {
                        displayNum++;
                        startTimer3.stop();
                        next = true;
                        cnt3 = 0;
                    }
                }

        }
        boolean next()
        {
            return next;
        }
}
