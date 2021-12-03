package com.dong.gameobjects;

import com.dong.effect.Animation;
import com.dong.effect.CacheDataLoader;
import com.dong.effect.FrameImage;
import com.dong.userinterface.GameFrame;

import javax.sound.sampled.Clip;
import java.awt.*;

public class BackGround extends GameObject {
    Animation aniCloud;
    Clip clipPlayGame,clipGameOver,clipNextLevel;
    public static final int WIN_LEVEL = 0;
    public static final int GAME_OVER = 1;
    public static final int PLAYING_GAME = 2;
    private int state;


    private long beginTimeClipPlayGame, timeClipPlayGame, currentTimeClipPlayGame;

    public BackGround(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        aniCloud = CacheDataLoader.getInstance().getAnimation("cloud");
        beginTimeClipPlayGame = System.nanoTime();
        clipPlayGame = CacheDataLoader.getInstance().getAudio("game_play");
        clipGameOver=CacheDataLoader.getInstance().getAudio("game_over");
        clipNextLevel=CacheDataLoader.getInstance().getAudio("win_level");
        setState(PLAYING_GAME);

        clipPlayGame.start();

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void update() {
        if (getGameWorld().player.getHeart()<=0)
            setState(GAME_OVER);
//        if (getGameWorld().player.getHeart()>0 && getGameWorld().countEnemy()==0)
//            setState(WIN_LEVEL);
        if (getState() == PLAYING_GAME) {
            currentTimeClipPlayGame = System.nanoTime();
            if (currentTimeClipPlayGame - beginTimeClipPlayGame >= 38000L * 1000000) {
                clipPlayGame = CacheDataLoader.getInstance().getAudio("game_play");
                clipPlayGame.start();
                beginTimeClipPlayGame = currentTimeClipPlayGame;
            }
        }
        if (getState()==GAME_OVER)
        {
            clipPlayGame.stop();
            clipGameOver.start();
        }
        if (getState()==WIN_LEVEL){
            clipPlayGame.stop();
            clipNextLevel.start();

        }

    }

    public void draw(Graphics2D g2) {
        aniCloud.update(System.nanoTime());
        aniCloud.draw(753, 622 + 136, g2);
        if (getGameWorld().player.getHeart() > 0) {
            int xHeart = 32;
            int yHeart = 650 + 30;
            for (int i = 0; i < getGameWorld().player.getHeart(); i++) {
                FrameImage frame = CacheDataLoader.getInstance().getFrameImage("heart");
                frame.draw(g2, xHeart, yHeart);
                xHeart += 64;
            }
        }
        if (getState()==GAME_OVER) {
            FrameImage frameImage = CacheDataLoader.getInstance().getFrameImage("gameover");
            frameImage.draw(g2, GameFrame.SCREEN_WIDTH / 2, GameFrame.SCREEN_HEIGHT / 2);
        }
        if (getState()==WIN_LEVEL) {
            FrameImage frameImage = CacheDataLoader.getInstance().getFrameImage("nextlevel");
            frameImage.draw(g2, GameFrame.SCREEN_WIDTH / 2, GameFrame.SCREEN_HEIGHT / 2);
        }
    }

}
