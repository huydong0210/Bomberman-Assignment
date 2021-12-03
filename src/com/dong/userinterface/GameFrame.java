package com.dong.userinterface;

import com.dong.effect.CacheDataLoader;
import com.dong.effect.LoadFramesAndAnimations;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public static final int SCREEN_WIDTH = 1505;
    public static final int SCREEN_HEIGHT = 665+274;
    GamePanel gamePanel;

    public GameFrame() {
        super("Bomberman");
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();// lấy tọa độ màn hình phần cứng
        this.setBounds((dimension.width - SCREEN_WIDTH) / 2, (dimension.height - SCREEN_HEIGHT) / 2, SCREEN_WIDTH, SCREEN_HEIGHT);//set vi tri hien
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// thoat bang nut close;
        //GameAudio.loadSounds();
        LoadFramesAndAnimations.load();
        CacheDataLoader.getInstance().loadData();

        gamePanel=new GamePanel();
        this.addKeyListener(gamePanel);
        add(gamePanel);

    }

    public void startGame() {
        gamePanel.startGame();

    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);

        gameFrame.startGame();
    }
}
