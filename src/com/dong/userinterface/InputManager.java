package com.dong.userinterface;

import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.BackGround;
import com.dong.gameobjects.GameWorld;

import javax.sound.sampled.Clip;
import java.awt.event.KeyEvent;

public class InputManager {
    GameWorld gameWorld;
    private int keyCode;

    public InputManager( ) {

    }

    public InputManager(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void processKeyPressed(int keyCode) {
        this.keyCode = keyCode;
        switch (keyCode) {
            case KeyEvent.VK_UP:
                gameWorld.player.goUp();
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.player.goDown();
                break;
            case KeyEvent.VK_LEFT:
                gameWorld.player.goLeft();
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.player.goRight();
                break;
            case KeyEvent.VK_ENTER:
                if (gameWorld.backGround.getState()==BackGround.START_GAME)
                    gameWorld.backGround.setState(BackGround.PLAYING_GAME);
                break;
            case KeyEvent.VK_SPACE:
                gameWorld.player.attack();
                break;

        }
    }

    public void processKeyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_LEFT:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_ENTER:

                break;
            case KeyEvent.VK_SPACE:

                break;
        }
    }
}
