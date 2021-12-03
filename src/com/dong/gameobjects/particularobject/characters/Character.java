package com.dong.gameobjects.particularobject.characters;

import com.dong.gameobjects.GameWorld;
import com.dong.gameobjects.particularobject.ParticularObject;

import java.awt.*;

public abstract class Character extends ParticularObject {

    private int direction; // huong chuyen dong

    private float speedX;
    private float speedY;


    public static final int LEFT_DIR = 0;
    public static final int RIGHT_DIR = 1;
    public static final int UP_DIR = 2;
    public static final int DOWN_DIR = 3;
    public static final int STOP_DIR = 4;

    public void draw(Graphics2D g2) {

    }

    public Character(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.setWidth(width);
        this.setHeight(height);
    }


    public abstract void goLeft();

    public abstract void goRight();

    public abstract void goDown();

    public abstract void goUp();

    public abstract void stop();


    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public int getDirection() {
        return direction;
    }


    public void update() {

        this.setPosX(this.getPosX()+this.getSpeedX());
        this.setPosY(this.getPosY()+this.getSpeedY());



    }
}

