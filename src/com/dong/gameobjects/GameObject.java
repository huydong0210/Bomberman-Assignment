package com.dong.gameobjects;

public abstract class GameObject {
    private float posX;
    private float posY;

    private GameWorld gameWorld;
    public GameObject(float posX, float posY, GameWorld gameWorld) {
        this.posX = posX;
        this.posY = posY;
        this.gameWorld=gameWorld;
    }
    public GameWorld getGameWorld() {
        return this.gameWorld;
    }

    public abstract void update();
    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }


    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }



}
