package com.dong.gameobjects.particularobject.characters.enemy;

import com.dong.effect.Animation;
import com.dong.effect.AnimationName;
import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.GameWorld;
import com.dong.gameobjects.particularobject.ParticularObject;

import java.awt.*;
import java.time.Year;
import java.util.Random;

public class Oneal extends Enemy {

    private int extraSpeed;

    Animation aniDead, aniLeft, aniRight;
    long beginTime, period, currentTime;

    public Oneal(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);
        extraSpeed = 0;
        setState(ALIVE);
        stop();
        detectPlayer = false;
        beginTime = 0;
        period = 1500 * 1000000;
        aniDead = CacheDataLoader.getInstance().getAnimation(AnimationName.oneal_dead);
        aniDead.add(CacheDataLoader.getInstance().getFrameImage("grass"), 700 * 1000000);
        aniLeft = CacheDataLoader.getInstance().getAnimation(AnimationName.oneal_left);
        aniRight = CacheDataLoader.getInstance().getAnimation(AnimationName.oneal_right);
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
        setSpeedX(1 + extraSpeed);

    }

    @Override
    public void goDown() {
        stop();
        setDirection(DOWN_DIR);
        setSpeedY(1 + extraSpeed);

    }

    @Override
    public void goUp() {
        stop();
        setDirection(UP_DIR);
        setSpeedY(-1 - extraSpeed);

    }

    @Override
    public void stop() {
        setDirection(STOP_DIR);
        setSpeedX(0);
        setSpeedY(0);

    }

    @Override
    public void update() {
        float xPlayer = getGameWorld().player.getPosX();
        float yPlayer = getGameWorld().player.getPosY();
        int a = ParticularObject.size;

        int tamXOGanNhatEnemy = a / 2 - (int) getPosX() % a + (int) getPosX();
        int tamYOGanNhatEnemy = a / 2 - (int) getPosY() % a + (int) getPosY();
        int tamXOGanNhatPlayer = a / 2 - (int) getPosX() % a + (int) xPlayer;
        int tamYOGanNhatPlayer = a / 2 - (int) getPosY() % a + (int) yPlayer;


        int x1 = (tamXOGanNhatEnemy - a / 2) / a;
        int y1 = (tamYOGanNhatEnemy - a / 2) / a;

        int x2 = (tamXOGanNhatPlayer - a / 2) / a;
        int y2 = (tamYOGanNhatPlayer - a / 2) / a;

        if (x1 == x2 && y1!=y2) {
            if (y1 < y2) {
                boolean checkDiDuoc = true;
                for (int y = y1; y <= y2; y++) {
                    if (getGameWorld().physicalMap.physMap[y][x1].equals(" ") == false) {
                        checkDiDuoc = false;
                        break;
                    }

                }
                if (checkDiDuoc == true) {
                    goDown();
                    detectPlayer = true;
                }
            }
            if (y1 > y2) {
                boolean checkDiDuoc = true;
                for (int y = y2; y <= y1; y++) {
                    if (getGameWorld().physicalMap.physMap[y][x1].equals(" ") == false) {
                        checkDiDuoc = false;
                        break;
                    }

                }
                if (checkDiDuoc == true) {
                    goUp();
                    detectPlayer = true;
                }
            }
        }
        if (y1 == y2 && x1!=x2) {
            if (x1 < x2) {
                boolean checkDiDuoc = true;
                for (int x = x1; x <= x2; x++) {
                    if (getGameWorld().physicalMap.physMap[y1][x].equals(" ") == false) {
                        checkDiDuoc = false;
                        break;
                    }
                }
                if (checkDiDuoc == true) {
                    goRight();
                    detectPlayer = true;
                }
            }
            if (x1 > x2) {
                boolean checkDiDuoc = true;
                for (int x = x2; x <= x1; x++) {
                    if (getGameWorld().physicalMap.physMap[y1][x].equals(" ") == false) {
                        checkDiDuoc = false;
                        break;
                    }
                }
                if (checkDiDuoc == true) {
                    goLeft();
                    detectPlayer = true;
                }
            }

        }
        if (x1 != x2 && y1 != y2) {
            detectPlayer = false;
        }
        super.update();
        currentTime = System.nanoTime();
        if (beginTime == 0)
            beginTime = currentTime;
        if (currentTime - beginTime > period) {
            Random rd = new Random();
            int random = rd.nextInt(3);
            if (random == 0)
                extraSpeed = 2;
            else
                extraSpeed = 0;
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
