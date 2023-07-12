/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootingspaceship;

//2018.11.13 - 클래스 작성, 배경 이동
//2018.12.06 - 만든 배경 스테이지 별로 넘어가게 구현, 더블 버퍼로 깜박임 잡기
/**
 *
 * @author user
 */
import java.awt.*;
import javax.swing.*;

public class Background extends JFrame {

    Image buffImage;
    Graphics buffg;

    private int frame; //frame 변수

    Image BackgroundS1 = new ImageIcon("src/Image/Background/BackgroundS1.png").getImage();
    Image BackgroundS2 = new ImageIcon("src/Image/Background/BackgroundS2.png").getImage();
    Image BackgroundS3 = new ImageIcon("src/Image/Background/BackgroundS3.png").getImage();
    Image BackgroundS4 = new ImageIcon("src/Image/Background/BackgroundS4.png").getImage();
    Image BackgroundS5 = new ImageIcon("src/Image/Background/BackgroundS5.png").getImage();
    Image BossS1 = new ImageIcon("src/Image/Background/BossS1.png").getImage();
    Image BossS2 = new ImageIcon("src/Image/Background/BossS2.png").getImage();
    Image BossS3 = new ImageIcon("src/Image/Background/BossS3.png").getImage();
    Image BossS4 = new ImageIcon("src/Image/Background/BossS4.png").getImage();
    Image BossS5 = new ImageIcon("src/Image/Background/BossS5.png").getImage();

    public Background() {
        frame = 0;
        repaint();
    }

    public void paint(Graphics g) {
        buffImage = createImage(1100, 600);
        buffg = buffImage.getGraphics();
        //   update(g);
    }

    public void update(Graphics g) {
        drawBG(g);
        g.drawImage(buffImage, 0, 0, this);
        paint(g);
    }

    //가로 1100 크기의 이미지를 왼쪽으로 이동시키고 오른쪽에 계속 나오게
    public void drawBG(Graphics buffg) {
        //buffg.clearRect(0, 0, 1100, 600);
        if (frame > -1100) {
            if (Shootingspaceship.stage == 1) {
                buffg.drawImage(BackgroundS1, frame, 0, null);
                frame -= 1;
                buffg.drawImage(BackgroundS1, frame + 1100, 0, null);
                if(Shootingspaceship.bossAlive==true){
                buffg.drawImage(BossS1, frame, 0, null);
                frame -= 1;
                buffg.drawImage(BossS1, frame + 1100, 0, null);    
            
                }
            } else if (Shootingspaceship.stage == 2) {
                buffg.drawImage(BackgroundS2, frame, 0, null);
                frame -= 1;
                buffg.drawImage(BackgroundS2, frame + 1100, 0, null);
                if(Shootingspaceship.bossAlive==true){
                buffg.drawImage(BossS2, frame, 0, null);
                frame -= 1;
                buffg.drawImage(BossS2, frame + 1100, 0, null);    
           
                }

            } else if (Shootingspaceship.stage == 3) {
                buffg.drawImage(BackgroundS3, frame, 0, null);
                frame -= 1;
                buffg.drawImage(BackgroundS3, frame + 1100, 0, null);
                if(Shootingspaceship.bossAlive==true){
                buffg.drawImage(BossS3, frame, 0, null);
                frame -= 1;
                buffg.drawImage(BossS3, frame + 1100, 0, null);    
           
                }
            } else if (Shootingspaceship.stage == 4) {
                buffg.drawImage(BackgroundS4, frame, 0, null);
                frame -= 1;
                buffg.drawImage(BackgroundS4, frame + 1100, 0, null);
                if(Shootingspaceship.bossAlive==true){
                buffg.drawImage(BossS4, frame, 0, null);
                frame -= 1;
                buffg.drawImage(BossS4, frame + 1100, 0, null);    
            
                }
            } else if (Shootingspaceship.stage == 5) {
                buffg.drawImage(BackgroundS5, frame, 0, null);
                frame -= 1;
                buffg.drawImage(BackgroundS5, frame + 1100, 0, null);
                if(Shootingspaceship.bossAlive==true){
                buffg.drawImage(BossS5, frame, 0, null);
                frame -= 1;
                buffg.drawImage(BossS5, frame + 1100, 0, null);    
                }
            }
        } else {
            frame = 0;
        }
    }
}