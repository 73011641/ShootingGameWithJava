package shootingspaceship;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sound {

    private String audioFile;
    private Clip clip;
    private AudioInputStream audio;
    public ArrayList<Sound> BgmList = new ArrayList<>();

    public Sound() {
        Sound Itemget = new Sound("아이템먹는소리");
        Sound enemyhit = new Sound("적맞는소리");
        Sound FinishShot = new Sound("필살기");
        Sound GenerateShot = new Sound("기본총소리");
        Sound enemyDie = new Sound("기본적죽는소리");
        Sound Playerhurt = new Sound("맞는소리");
        Sound MainBgm = new Sound("메인배경음악");
        Sound BossBgm = new Sound("보스전소리");
        Sound EnterKey = new Sound("엔터소리");
        Sound button = new Sound("버튼소리");
        Sound LogBgm = new Sound("로그인음악");
        Sound EndBgm = new Sound("엔딩음악");
        BgmList.add(Itemget);
        BgmList.add(enemyhit);
        BgmList.add(FinishShot);
        BgmList.add(GenerateShot);
        BgmList.add(enemyDie);
        BgmList.add(Playerhurt);
        BgmList.add(MainBgm);
        BgmList.add(BossBgm);
        BgmList.add(EnterKey);
        BgmList.add(button);
        BgmList.add(LogBgm);
        BgmList.add(EndBgm);
    }

// loop는 true일시 무한반복재생, false일 시 한번만 재생
    public Sound(String file) {
        this.audioFile = "src/sound/" + file + ".wav";
    }

    public void PlaySound(boolean loop) {
        try {
            audio = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(audioFile)));
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            if (loop) {
                clip.loop(-1);         //무한 반복
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        clip.stop();
        clip.close();
    }
}
