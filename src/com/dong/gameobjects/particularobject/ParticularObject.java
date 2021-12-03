package com.dong.gameobjects.particularobject;

import com.dong.gameobjects.GameObject;
import com.dong.gameobjects.GameWorld;

import java.awt.*;

public abstract class ParticularObject extends GameObject {

    private float width;
    private float height;


    public static final int ALIVE = 4;
    public static final int DEATH = 5;
    public static final int NOBEHURT = 6;
    public static final int HURT = 7;

    public static final int size = 48;

    private int state;

    public Rectangle getBoundForCollisionWithMap() { // lay hinh chu nhat bao
        Rectangle rectangle = new Rectangle();
        rectangle.x = (int) (getPosX() - getWidth() / 2);
        rectangle.y = (int) (getPosY() - getHeight() / 2);
        rectangle.width = (int) getWidth();
        rectangle.height = (int) getHeight();
        return rectangle;
    }

    public ParticularObject(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.width = width;
        this.height = height;
    }

    public ParticularObject(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public abstract void draw(Graphics2D g2);
}
