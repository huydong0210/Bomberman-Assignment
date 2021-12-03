package com.dong.gameobjects.particularobject.characters.enemy;

import com.dong.effect.Animation;
import com.dong.effect.AnimationName;
import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.GameWorld;

import java.awt.*;

public class Doll extends Enemy{

    Animation aniDead, aniLeft, aniRight;
    public Doll(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);
        setState(ALIVE);
        setSpeedX(0);
        setSpeedY(0);

        aniDead= CacheDataLoader.getInstance().getAnimation(AnimationName.doll_dead);
        aniDead.add(CacheDataLoader.getInstance().getFrameImage("grass"), 700 * 1000000);
        aniLeft=CacheDataLoader.getInstance().getAnimation(AnimationName.doll_left);
        aniRight=CacheDataLoader.getInstance().getAnimation(AnimationName.doll_right);
    }

    public void goLeft() {
        stop();
        setDirection(LEFT_DIR);
        setSpeedX(-2);

    }

    @Override
    public void goRight() {
        stop();
        setDirection(RIGHT_DIR);
        setSpeedX(2);

    }

    @Override
    public void goDown() {
        stop();
        setDirection(DOWN_DIR);
        setSpeedY(2);

    }

    @Override
    public void goUp() {
        stop();
        setDirection(UP_DIR);
        setSpeedY(-2);

    }

    @Override
    public void stop() {
        setDirection(STOP_DIR);
        setSpeedX(0);
        setSpeedY(0);

    }

    public void update() {
        super.update();
    }

    public void draw(Graphics2D g2) {
        if (getState() == ALIVE) {
            switch (getDirection()) {
                case STOP_DIR:
                    aniLeft.update(System.nanoTime());
                    aniLeft.draw((int) getPosX(), (int) getPosY(), g2);
                    break;
                case RIGHT_DIR:
                    aniRight.update(System.nanoTime());
                    aniRight.draw((int) getPosX(), (int) getPosY(), g2);
                    break;
                case UP_DIR:
                    aniRight.update(System.nanoTime());
                    aniRight.draw((int) getPosX(), (int) getPosY(), g2);
                    break;
                case DOWN_DIR:
                    aniLeft.update(System.nanoTime());
                    aniLeft.draw((int) getPosX(), (int) getPosY(), g2);
                    break;
                case LEFT_DIR:
                    aniLeft.update(System.nanoTime());
                    aniLeft.draw((int) getPosX(), (int) getPosY(), g2);
                    break;
            }
        }
        if (getState() == HURT) {
            aniDead.update(System.nanoTime());
            aniDead.draw((int) getPosX(), (int) getPosY(), g2);
            stop();
            if (aniDead.isLastFrame())
                setState(DEATH);
        }
        if (getState() == DEATH) {
            Balloom balloom=new Balloom(getPosX(),getPosY(),getWidth(),getHeight(),getGameWorld());
            getGameWorld().addObject(balloom);
            getGameWorld().remove(this);
        }
    }
}
