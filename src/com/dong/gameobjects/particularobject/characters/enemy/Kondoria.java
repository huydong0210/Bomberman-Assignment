package com.dong.gameobjects.particularobject.characters.enemy;

import com.dong.effect.Animation;
import com.dong.effect.AnimationName;
import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.GameWorld;
import com.dong.gameobjects.particularobject.ParticularObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Kondoria  extends Enemy{
    Animation aniDead, aniLeft, aniRight;
    public Kondoria(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);


        setState(ALIVE);
        setSpeedX(0);
        setSpeedY(0);

        aniDead = CacheDataLoader.getInstance().getAnimation(AnimationName.kondoria_dead);
        aniDead.add(CacheDataLoader.getInstance().getFrameImage("grass"), 700 * 1000000);
        aniLeft = CacheDataLoader.getInstance().getAnimation(AnimationName.kondoria_left);
        aniRight = CacheDataLoader.getInstance().getAnimation(AnimationName.kondoria_right);
    }

    @Override
    public void goLeft() {
        stop();
        setDirection(LEFT_DIR);
        setSpeedX(-1);

    }

    @Override
    public void goRight() {
        stop();
        setDirection(RIGHT_DIR);
        setSpeedX(1);

    }

    @Override
    public void goDown() {
        stop();
        setDirection(DOWN_DIR);
        setSpeedY(1);

    }

    @Override
    public void goUp() {
        stop();
        setDirection(UP_DIR);
        setSpeedY(-1);

    }

    @Override
    public void stop() {
        setDirection(STOP_DIR);
        setSpeedX(0);
        setSpeedY(0);

    }

    public void update(){
        this.setPosX(this.getPosX()+this.getSpeedX());
        this.setPosY(this.getPosY()+this.getSpeedY());

        Rectangle rectEnemy = this.getBoundForCollisionWithMap();
        Rectangle rectPlayer = getGameWorld().player.getBoundForCollisionWithMap();
        if (rectEnemy.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false) {
            getGameWorld().player.setState(HURT);

        }
        if (getState()==HURT)
        {
            clip.start();
        }
        unlock = new ArrayList<Integer>();
        if (getState() == ALIVE) {
            String[][] phys = getGameWorld().physicalMap.physMap;
            int size = ParticularObject.size;
            unlock = new ArrayList<Integer>();
            unlock.add((Integer) LEFT_DIR);
            unlock.add((Integer) RIGHT_DIR);
            unlock.add((Integer) UP_DIR);
            unlock.add((Integer) DOWN_DIR);
            if (getDirection() == LEFT_DIR) {
                if (getGameWorld().physicalMap.haveCollisionWithLeftBrick(getBoundForCollisionWithMap()) == null) {
                    goLeft();
                    currentTime = System.nanoTime();
                    if (beginTime == 0)
                        beginTime = currentTime;
                    if (currentTime - beginTime > period && detectPlayer == false) {
                        beginTime = currentTime;
                        Random rd = new Random();
                        int i = rd.nextInt(unlock.size());
                        setDirection(unlock.get(i));
                    }
                }
                if (getGameWorld().physicalMap.haveCollisionWithLeftBrick(getBoundForCollisionWithMap()) != null) {
                    Rectangle rectLeft = getGameWorld().physicalMap.haveCollisionWithLeftBrick(getBoundForCollisionWithMap());

                    float posYCheck1 = getPosY() + getHeight() / 2 - rectLeft.y;
                    if (posYCheck1 < 30 && rectLeft.y != 0 && rectLeft.x != 0 && posYCheck1 > 0) {
                        setPosY(rectLeft.y - getHeight() / 2);
                        goLeft();
                        return;
                    }
                    float posYCheck2 = rectLeft.y + rectLeft.height - (getPosY() - getHeight() / 2);
                    if (posYCheck2 < 30 && rectLeft.y != 0 && rectLeft.x != 0 && posYCheck2 > 0) {
                        setPosY(rectLeft.y + rectLeft.height + getHeight() / 2);
                        goLeft();
                        return;
                    }
                    setPosX(rectLeft.x + rectLeft.width + getWidth() / 2);
                    stop();
                    setDirection(STOP_DIR);
                    unlock.remove((Integer) LEFT_DIR);
                }
            }
            if (getDirection() == RIGHT_DIR) {
                if (getGameWorld().physicalMap.haveCollisionWithRightBrick(getBoundForCollisionWithMap()) == null) {
                    goRight();
                    currentTime = System.nanoTime();
                    if (beginTime == 0)
                        beginTime = currentTime;
                    if (currentTime - beginTime > period && detectPlayer == false) {
                        beginTime = currentTime;
                        Random rd = new Random();
                        int i = rd.nextInt(unlock.size());
                        setDirection(unlock.get(i));
                    }
                }
                if (getGameWorld().physicalMap.haveCollisionWithRightBrick(getBoundForCollisionWithMap()) != null) {
                    Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithRightBrick(getBoundForCollisionWithMap());

                    float posYCheck1 = getPosY() + getWidth() / 2 - rectLand.y;
                    if (posYCheck1 < 30 && rectLand.x != 0 && rectLand.y != 0 && posYCheck1 > 0 && posYCheck1 > 0) {
                        setPosY(rectLand.y - rectLand.height / 2);
                        goRight();
                        return;
                    }
                    float posYCheck2 = rectLand.y + rectLand.height - (getPosY() - getHeight() / 2);
                    if (posYCheck2 < 30 && rectLand.x != 0 && rectLand.y != 0 && posYCheck2 > 0 && posYCheck2 > 0) {
                        setPosY(rectLand.y + rectLand.height + rectLand.height / 2);
                        goRight();
                        return;
                    }
                    setPosX(rectLand.x - getWidth() / 2);
                    stop();
                    setDirection(STOP_DIR);
                    unlock.remove((Integer) RIGHT_DIR);
                }
            }
            if (getDirection() == UP_DIR) {
                if (getGameWorld().physicalMap.haveCollisionWithTopBrick(getBoundForCollisionWithMap()) == null) {
                    goUp();
                    currentTime = System.nanoTime();
                    if (beginTime == 0)
                        beginTime = currentTime;
                    if (currentTime - beginTime > period && detectPlayer == false) {
                        beginTime = currentTime;
                        Random rd = new Random();
                        int i = rd.nextInt(unlock.size());
                        setDirection(unlock.get(i));
                    }
                }
                if (getGameWorld().physicalMap.haveCollisionWithTopBrick(getBoundForCollisionWithMap()) != null) {
                    Rectangle rectTop = getGameWorld().physicalMap.haveCollisionWithTopBrick(getBoundForCollisionWithMap());

                    float posXCheck1 = rectTop.x + rectTop.width - (getPosX() - getWidth() / 2);
                    if (posXCheck1 < 30 && rectTop.y != 0 && rectTop.x != 0 && posXCheck1 > 0) {
                        setPosX(rectTop.x + getWidth() + getWidth() / 2);
                        goUp();
                        return;
                    }
                    float posXCheck2 = getPosX() + getWidth() / 2 - rectTop.x;
                    if (posXCheck2 < 30 && rectTop.y != 0 && rectTop.x != 0 && posXCheck2 > 0) {
                        setPosX(rectTop.x - rectTop.width / 2);
                        goUp();
                        return;
                    }
                    setPosY(rectTop.y + rectTop.height + getHeight() / 2);
                    stop();
                    setDirection(STOP_DIR);
                    unlock.remove((Integer) UP_DIR);
                }

            }

            if (getDirection() == DOWN_DIR) {
                if (getGameWorld().physicalMap.haveCollisionWithBotBrick(getBoundForCollisionWithMap()) == null) {
                    goDown();
                    currentTime = System.nanoTime();
                    if (beginTime == 0)
                        beginTime = currentTime;
                    if (currentTime - beginTime > period && detectPlayer == false) {
                        beginTime = currentTime;
                        Random rd = new Random();
                        int i = rd.nextInt(unlock.size());
                        setDirection(unlock.get(i));
                    }
                }
                if (getGameWorld().physicalMap.haveCollisionWithBotBrick(getBoundForCollisionWithMap()) != null) {
                    Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithBotBrick(getBoundForCollisionWithMap());
                    float posXCheck1 = rectLand.x + rectLand.width - (getPosX() - getWidth() / 2);
                    if (posXCheck1 < 30 && rectLand.x != 0 && rectLand.y != 0 && posXCheck1 > 0) {
                        setPosX(rectLand.x + rectLand.width + getWidth() / 2);
                        goDown();
                        return;
                    }
                    float posXCheck2 = getPosX() + getWidth() / 2 - rectLand.x;
                    if (posXCheck2 < 30 && rectLand.x != 0 && rectLand.y != 0 && posXCheck2 > 0) {
                        setPosX(rectLand.x - rectLand.width / 2);
                        goDown();
                        return;
                    }
                    setPosY(rectLand.y - getHeight() / 2);
                    stop();
                    setDirection(STOP_DIR);
                    unlock.remove((Integer) DOWN_DIR);

                }
            }
            if (getDirection() == STOP_DIR && unlock.size() != 0 && detectPlayer == false) {
                currentTime = System.nanoTime();
                if (beginTime == 0)
                    beginTime = currentTime;
                if (currentTime - beginTime > 800 * 1000000) {
                    beginTime = currentTime;
                    Random rd = new Random();
                    int i = rd.nextInt(unlock.size());
                    setDirection(unlock.get(i));
                }
            }

        }
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
            getGameWorld().remove(this);
        }
    }
}
