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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author 김애리
 */
public class Manual extends JPanel{
        
    
        public static int cnt5 = 0;
        public javax.swing.Timer startTimer5;
    
        boolean returnM = false;
        boolean btnImage = false;
        //설명 화면
        private ImageIcon mu = new ImageIcon("src/Image/Background/게임 설명서.png");
        private Image Mu = mu.getImage();
        
        //돌아가기 버튼 이미지
        private ImageIcon returnBtn1 = new ImageIcon("src/Image/Background/returnBtn.png");
        
        //돌아가기 버튼
        JButton returnBtn = new JButton(returnBtn1);
        
      
        
         public Manual()
        {
            
                setPreferredSize(new Dimension(1100, 600));
                setVisible(true);
                
                this.add(returnBtn);
                BtnClick bc = new BtnClick();
                returnBtn.addMouseListener(bc);
        }
        
         class BtnClick extends MouseAdapter
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                returnM = true;
                btnImage = false;
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
                btnImage = true;
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
                btnImage = false;
            }
            
        }
         public void paintComponent(Graphics g)
        {   
                super.paintComponent(g);
                g.drawImage(Mu, 0, 0, 1100, 600, this);
                
                returnBtn.setOpaque(false);
                returnBtn.setContentAreaFilled(false);
                returnBtn.setBorderPainted(false);
                returnBtn.setFocusPainted(false);
                
                if(btnImage == false)
                {
                    returnBtn.setBounds(1000, 510, 80, 80);
                }
                else if(btnImage == true)
                {
                    returnBtn.setBounds(990, 500, 90, 90);
                }
        }
}
