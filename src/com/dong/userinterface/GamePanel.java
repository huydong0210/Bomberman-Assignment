package com.dong.userinterface;


import com.dong.effect.*;
import com.dong.gameobjects.BackGround;
import com.dong.gameobjects.GameWorld;
import com.dong.gameobjects.particularobject.Item.PowerUpSpeed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;
    private boolean isRunning;
    private InputManager inputManager;
    CacheDataLoader cacheDataLoader;

    private BufferedImage bufImage;
    private Graphics2D bufG2D;

    public GameWorld gameWorld;
    Animation animation;

    public GamePanel() {
        gameWorld = new GameWorld();
        inputManager = new InputManager(gameWorld);
        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_WIDTH, BufferedImage.TYPE_INT_ARGB);
        animation = CacheDataLoader.getInstance().getAnimation(AnimationName.bomb);


    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bufImage, 0, 0, this);

    }

    public void updateGame() {
        gameWorld.backGround.update();
        if (gameWorld.backGround.getState() == BackGround.PLAYING_GAME)
            gameWorld.updateObjects();
    }

    public void renderGame() {
        if (bufImage == null) {
            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        } else {
            bufG2D = (Graphics2D) bufImage.getGraphics();
        }
        if (gameWorld.backGround.getState() == BackGround.PLAYING_GAME) {
            if (bufG2D != null) {
                gameWorld.draw(bufG2D);
            }
        } else {
            gameWorld.backGround.draw(bufG2D);
        }
        if (gameWorld.backGround.getState() == BackGround.WIN_LEVEL) {
            gameWorld.update();
        }
    }

    public void startGame() {
        if (thread == null) {
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }

    }

    @Override
    public void run() {
        int FPS = 80;
        long period = 1000 * 1000000 / FPS; // chu ky
        long beginTime;
        long sleepTime;
        beginTime = System.nanoTime();
        while (isRunning) {
            updateGame();
            renderGame();
            repaint();
            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;
            try {
                if (sleepTime > 0)
                    Thread.sleep(sleepTime / 1000000);
                else
                    Thread.sleep(period / 2000000);
            } catch (Exception e) {
            }
            beginTime = System.nanoTime();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.processKeyPressed(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.processKeyReleased(e.getKeyCode());
    }
}

