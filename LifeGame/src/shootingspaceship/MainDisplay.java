/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootingspaceship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author 김애리
 */


public class MainDisplay extends JPanel
{
        Sound GameBgm = new Sound();
        public static int cnt = 0;
        private javax.swing.Timer startTimer;
        
        public static String nameR = null;
        boolean name = false;
        
        boolean go = false;
        boolean start = false;
        boolean gogo = false;
        
        boolean mumu = false;
        boolean muin = false;
        
        //메인 화면
        private ImageIcon main = new ImageIcon("src/Image/Background/Main.png");
        private Image mainImg = main.getImage();
        
        //시작 버튼 이미지
        private ImageIcon startBtn1 = new ImageIcon("src/Image/Background/StartBtn.png");
        private ImageIcon startBtn2 = new ImageIcon("src/Image/Background/StartBtn2.png");
        private ImageIcon startBtn3 = new ImageIcon("src/Image/Background/StartBtn3.png");
        
        //레츠고 버튼 이미지
        private ImageIcon goBtn1 = new ImageIcon("src/Image/Background/GoBtn.png");
        private ImageIcon goBtn2 = new ImageIcon("src/Image/Background/GoBtn2.png");
        private ImageIcon goBtn3 = new ImageIcon("src/Image/Background/GoBtn3.png");
        
        //설명 버튼 이미지
        private ImageIcon muBtn1 = new ImageIcon("src/Image/Background/mu.png");
        private ImageIcon muBtn2 = new ImageIcon("src/Image/Background/Manual.png");
        
        
        //시작 버튼
         JButton startBtn = new JButton(startBtn1);
         
         //설명 버튼
         JButton muBtn = new JButton(muBtn1);
         
         //이름 입력
         private ImageIcon nameSpace = new ImageIcon("src/Image/Background/이름 입력창.png");
         private Image nameImg = nameSpace.getImage();
         
         JTextField nameInput = new JTextField(20);
         
         //이름 화면
        private ImageIcon nameDisplay = new ImageIcon("src/Image/Background/시작합니다.png");
        private Image nameDisplayimg = nameDisplay.getImage();
        
        
        //타이머
        public class time implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
             {
                 cnt += 1;
            }
        }
    
        
        public MainDisplay()
        {
                startTimer = new javax.swing.Timer(1000, new time());
                
                //메인
                setPreferredSize(new Dimension(1100, 600));
                setVisible(true);
                
                //시작 버튼
                this.add(startBtn);
                BtnClick bc = new BtnClick();
                startBtn.addMouseListener(bc);
                
                //설명 버튼
                this.add(muBtn);
                BtnClickS bcs = new BtnClickS();
                muBtn.addMouseListener(bcs);
                
                //이름 입력창
                this.add(nameInput);
                nameInput.setVisible(false);
                
                MyKeyListener cl = new MyKeyListener();
                nameInput.addKeyListener(cl);
                GameBgm.BgmList.get(10).PlaySound(false);
               
        }
        //이름 입력창에서 입력 받기
        class MyKeyListener extends KeyAdapter
        {
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    GameBgm.BgmList.get(8).PlaySound(false);
                {
                    
                    nameR = nameInput.getText();
                    go = true;
                    startBtn.setVisible(true);
                    startBtn.setIcon(goBtn1);
                }
            }
        }
        
        public void paintComponent(Graphics g)
        {   
                //메인
                super.paintComponent(g);
                g.drawImage(mainImg, 0, 0, 1100, 600, this);
                
                //시작 버튼
                startBtn.setBounds(400, 500, 292, 46);
                startBtn.setOpaque(false);
                startBtn.setContentAreaFilled(false);
                startBtn.setBorderPainted(false);
                startBtn.setFocusPainted(false);
                
                //설명 버튼
                muBtn.setBounds(1000, 10, 80, 80);
                muBtn.setOpaque(false);
                muBtn.setContentAreaFilled(false);
                muBtn.setBorderPainted(false);
                muBtn.setFocusPainted(false);
                
                
                //이름 입력 창
                nameInput.setBounds(450,455,200, 36);
                nameInput.setHorizontalAlignment(JTextField.CENTER);
                nameInput.setFont(new Font("맑은 고딕", Font.BOLD, 18));
                nameInput.setForeground(new Color(40,21,54));
                nameInput.setBorder(null);
                
                //이름 입력
                if(name == true && start == false)
                {
                        g.drawImage(nameImg, 130, 30, 850, 550, this);
                        nameInput.setVisible(true);
                        repaint();
                        
                }
                //시작합니다
                if(start == true)
                {
                        muBtn.setVisible(false);
                        g.drawImage(nameDisplayimg, 0, 0, 1100, 600, this);
                        g.setColor(Color.white);
                        g.setFont(new Font("맑은 고딕", Font.BOLD ,100));
                        g.drawString(nameR, 180, 280);
                        repaint();
                        startTimer.start();
                        if(cnt >= 4)
                        {
                            startTimer.stop();
                            gogo = true;
                        }
                }
        }
        
        //점수 버튼
        class BtnClickS extends MouseAdapter
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                mumu = true;
                GameBgm.BgmList.get(10).stopSound();
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
                GameBgm.BgmList.get(9).PlaySound(false);
                muBtn.setIcon(muBtn2);
                muin = true;
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
                muBtn.setIcon(muBtn1);
                muin = false;
            }
        }
        
        //시작, 레츠고 버튼
        class BtnClick extends MouseAdapter
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                startBtn.setIcon(startBtn3);
                name = true;
                GameBgm.BgmList.get(0).PlaySound(false);
                if(go)
                {
                    startBtn.setIcon(goBtn3);
                    start = true;
                    startBtn.setVisible(false);
                    nameInput.setVisible(false);
                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                GameBgm.BgmList.get(9).PlaySound(false);
                startBtn.setIcon(startBtn2);
                
                if(go)
                {
                    startBtn.setIcon(goBtn2);
                    ;
                }
                if(start)
                {
                        startBtn.setVisible(false);
                        GameBgm.BgmList.get(0).PlaySound(false);
                }
            
            }
            
            @Override
            public void mouseExited(MouseEvent e)
            {
                startBtn.setIcon(startBtn1);
                if(name == true)
                {
                    startBtn.setVisible(false);
                }
                if(go)
                {
                    startBtn.setIcon(goBtn1);
                    startBtn.setVisible(true);
                    GameBgm.BgmList.get(10).stopSound();
                }
                if(start)
                {
                        startBtn.setVisible(false);
                }
            }
        }
        
        boolean lego()
        {
            return gogo;
        }
        
        public String getName()
        {
            return nameR;
        }
}




