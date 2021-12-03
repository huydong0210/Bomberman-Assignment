package com.dong.gameobjects.particularobject.characters;

import com.dong.effect.Animation;
import com.dong.effect.AnimationName;
import com.dong.effect.CacheDataLoader;
import com.dong.effect.FrameImage;
import com.dong.gameobjects.BackGround;
import com.dong.gameobjects.GameWorld;
import com.dong.gameobjects.particularobject.Item.BomItem;
import com.dong.gameobjects.particularobject.Item.PowerUpSpeed;
import com.dong.gameobjects.particularobject.ParticularObject;
import com.dong.gameobjects.particularobject.Bomb;

import javax.sound.sampled.Clip;
import java.awt.*;

public class Player extends Character {
    private int bombLimit;
    private int countBomb;
    protected Animation currentAnimation = CacheDataLoader.getInstance().getAnimation(AnimationName.player_up);
    protected Animation aniUp, aniDown, aniDead, aniRight, aniLeft, AniDead;
    public int previousDir;
    public Bomb bomb;
    private  long timeNoBeHurt, beginTimeNoBeHurt,currentTimeNoBeHurt;
    private static int extraSpeed = 0;
    float xLocation, yLocation;
    private boolean checkPowerUpSpeed, checkPowerUpFlame, checkPowerUpBombs, checkBombPass, checkBrickPass;
    Bomb detectBomb;
    Clip clipDie = null;
    boolean checkNobeHurt;
    FrameImage noBeHurtImg=CacheDataLoader.getInstance().getFrameImage("nobehurt");
    private int heart;

    public Player(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);
        xLocation = posX;
        yLocation = posY;
        this.setState(ALIVE);
        this.setDirection(STOP_DIR);
        previousDir = DOWN_DIR;
        detectBomb = null;
        checkPowerUpBombs = false;
        checkPowerUpFlame = false;
        checkPowerUpSpeed = false;
        checkBombPass=false;
        checkBrickPass=false;
        beginTimeNoBeHurt=System.nanoTime();


        heart = 3;

        bombLimit = 1;
        countBomb = 0;

        aniUp = CacheDataLoader.getInstance().getAnimation(AnimationName.player_up);
        aniDown = CacheDataLoader.getInstance().getAnimation(AnimationName.player_down);
        aniLeft = CacheDataLoader.getInstance().getAnimation(AnimationName.player_left);
        aniRight = CacheDataLoader.getInstance().getAnimation(AnimationName.player_right);
        aniDead = CacheDataLoader.getInstance().getAnimation(AnimationName.player_dead);
        clipDie = CacheDataLoader.getInstance().getAudio("bomber_die");

        timeNoBeHurt= 4000L * 1000000;
    }

    public boolean isCheckBombPass() {
        return checkBombPass;
    }

    public void setCheckBombPass(boolean checkBombPass) {
        this.checkBombPass = checkBombPass;
    }

    public boolean isCheckBrickPass() {
        return checkBrickPass;
    }

    public void setCheckBrickPass(boolean checkBrickPass) {
        this.checkBrickPass = checkBrickPass;
    }

    public static void setExtraSpeed(int extraSpeed) {
        Player.extraSpeed = extraSpeed;
    }

    public boolean isCheckNobeHurt() {
        return checkNobeHurt;
    }

    public long getBeginTimeNoBeHurt() {
        return beginTimeNoBeHurt;
    }

    public void setBeginTimeNoBeHurt(long beginTimeNoBeHurt) {
        this.beginTimeNoBeHurt = beginTimeNoBeHurt;
    }

    public void setCheckNobeHurt(boolean checkNobeHurt) {
        this.checkNobeHurt = checkNobeHurt;
    }

    public long getTimeNoBeHurt() {
        return timeNoBeHurt;
    }

    public void setTimeNoBeHurt(long timeNoBeHurt) {
        this.timeNoBeHurt = timeNoBeHurt;
    }

    public int getBombLimit() {
        return bombLimit;
    }

    public void setBombLimit(int bombLimit) {
        this.bombLimit = bombLimit;
    }

    public int getCountBomb() {
        return countBomb;
    }

    public void setCountBomb(int countBomb) {
        this.countBomb = countBomb;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    @Override
    public void goLeft() {
        if (this.getState() ==ALIVE &&getGameWorld().backGround.getState()== BackGround.PLAYING_GAME) {
            setDirection(LEFT_DIR);
            setSpeedX(-(3 + extraSpeed));
            setSpeedY(0);
        } else stop();

    }

    @Override
    public void goRight() {
        if (this.getState() ==ALIVE &&getGameWorld().backGround.getState()== BackGround.PLAYING_GAME) {
            setDirection(RIGHT_DIR);
            setSpeedX(3 + extraSpeed);
            setSpeedY(0);
        } else stop();

    }

    @Override
    public void goDown() {
        if (this.getState() ==ALIVE &&getGameWorld().backGround.getState()== BackGround.PLAYING_GAME) {
            setDirection(DOWN_DIR);
            setSpeedY(3 + extraSpeed);
            setSpeedX(0);
        } else stop();
    }

    @Override
    public void goUp() {
        if (this.getState() ==ALIVE &&getGameWorld().backGround.getState()== BackGround.PLAYING_GAME) {
            setDirection(UP_DIR);
            setSpeedY(-(3 + extraSpeed));
            setSpeedX(0);
        } else stop();
    }

    @Override
    public void stop() {
        if (this.getState() !=HURT) {
            previousDir = getDirection();
            setDirection(STOP_DIR);
            setSpeedX(0);
            setSpeedY(0);
        }
    }

    @Override
    public void update() {
        super.update();
        currentTimeNoBeHurt=System.nanoTime();
        if (currentTimeNoBeHurt-beginTimeNoBeHurt<=timeNoBeHurt)
        {
            checkNobeHurt=true;
        }
        if (currentTimeNoBeHurt-beginTimeNoBeHurt>timeNoBeHurt)
        {
            checkNobeHurt=false;
        }
        if (isCheckBrickPass())
            powerUp();
        else
            normal();


    }

    public void draw(Graphics2D g2) {
        if (checkNobeHurt==true)
        {
            noBeHurtImg.draw(g2,(int)getPosX(),(int)getPosY());
        }
        if (getState() == ALIVE) {
            if (getDirection() == DOWN_DIR) {
                aniDown.setBeginTime(System.nanoTime());
                aniDown.setCurrentFrame(aniDown.getCurrentFrame() + 1);
                aniDown.draw((int) getPosX(), (int) getPosY(), g2);
            }
            if (getDirection() == UP_DIR) {
                aniUp.setBeginTime(System.nanoTime());
                aniUp.setCurrentFrame(aniUp.getCurrentFrame() + 1);
                aniUp.draw((int) getPosX(), (int) getPosY(), g2);
            }
            if (getDirection() == RIGHT_DIR) {
                aniRight.setBeginTime(System.nanoTime());
                aniRight.setCurrentFrame(aniRight.getCurrentFrame() + 1);
                aniRight.draw((int) getPosX(), (int) getPosY(), g2);
            }
            if (getDirection() == LEFT_DIR) {
                aniLeft.setBeginTime(System.nanoTime());
                aniLeft.setCurrentFrame(aniLeft.getCurrentFrame() + 1);
                aniLeft.draw((int) getPosX(), (int) getPosY(), g2);
            }
            if (getDirection() == STOP_DIR) {
                FrameImage frameImage;
                if (previousDir == DOWN_DIR)
                    frameImage = aniDown.getFrameImages().get(0);
                else if (previousDir == UP_DIR)
                    frameImage = aniUp.getFrameImages().get(0);
                else if (previousDir == RIGHT_DIR)
                    frameImage = aniRight.getFrameImages().get(0);
                else
                    frameImage = aniLeft.getFrameImages().get(0);
                frameImage.draw(g2, (int) getPosX(), (int) getPosY());
            }

        }
        if (getState() == HURT) {
            clipDie.start();
            stop();
            aniDead.update(System.nanoTime());
            aniDead.draw((int) getPosX(), (int) getPosY(), g2);
            if (aniDead.isLastFrame()) {
                extraSpeed=0;
                bombLimit=1;
                checkBombPass=false;
                checkBrickPass=false;
                heart--;
                if (heart > 0) {
                    clipDie = CacheDataLoader.getInstance().getAudio("bomber_die");
                    setState(ALIVE);
                    beginTimeNoBeHurt=System.nanoTime();
                    setPosX(xLocation);
                    setPosY(yLocation);
                    stop();
                }
            }

        }
    }

    public void attack() {
        if (this.countBomb < bombLimit && getGameWorld().player.getState() == ALIVE) {
            int a = ParticularObject.size;
            int check1 = a / 2 - (int) getPosX() % a;
            int check2 = a / 2 - (int) getPosY() % a;
            int x = (int) getPosX() + check1;
            int y = (int) getPosY() + check2;
            bomb = new Bomb(getPosX() + check1, getPosY() + check2, getGameWorld());
            getGameWorld().particularObjectList.add(bomb);
            getGameWorld().player.detectBomb = bomb;
            this.countBomb++;
        }

    }
    public void powerUp(){
        if (getState() == ALIVE) {
            ParticularObject object = getGameWorld().getCollisionWithEnemyObject(getGameWorld().player);
            if (detectBomb != null) {
                if (Math.abs(getPosX() - detectBomb.getPosX()) >= getWidth() || Math.abs(getPosY() - detectBomb.getPosY()) >= getHeight()) {
                    int x = (int) (detectBomb.getPosX() - getWidth() / 2) / ParticularObject.size;
                    int y = (int) (detectBomb.getPosY() - getHeight() / 2) / ParticularObject.size;
                    getGameWorld().physicalMap.physMap[y][x] = ".";
                    detectBomb = null;
                }
            }
            if (getDirection() == DOWN_DIR && getGameWorld().physicalMap.haveCollisionWithBotBrick(getBoundForCollisionWithMap()) != null) {
                Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithBotBrick(getBoundForCollisionWithMap());
                float posXCheck1 = rectLand.x + rectLand.width - (getPosX() - getWidth() / 2);
                if (posXCheck1 < 30 && rectLand.x != 0 && rectLand.y != 0 && posXCheck1 > 0)
                    setPosX(rectLand.x + rectLand.width + getWidth() / 2);
                float posXCheck2 = getPosX() + getWidth() / 2 - rectLand.x;
                if (posXCheck2 < 30 && rectLand.x != 0 && rectLand.y != 0 && posXCheck2 > 0)
                    setPosX(rectLand.x - rectLand.width / 2);
                setPosY(rectLand.y - getHeight() / 2);
            }

            if (getDirection() == RIGHT_DIR && getGameWorld().physicalMap.haveCollisionWithRightBrick(getBoundForCollisionWithMap()) != null) {
                Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithRightBrick(getBoundForCollisionWithMap());
                float posYCheck1 = getPosY() + getWidth() / 2 - rectLand.y;
                if (posYCheck1 < 30 && rectLand.x != 0 && rectLand.y != 0 && posYCheck1 > 0 && posYCheck1 > 0)
                    setPosY(rectLand.y - rectLand.height / 2);
                float posYCheck2 = rectLand.y + rectLand.height - (getPosY() - getHeight() / 2);
                if (posYCheck2 < 30 && rectLand.x != 0 && rectLand.y != 0 && posYCheck2 > 0 && posYCheck2 > 0)
                    setPosY(rectLand.y + rectLand.height + rectLand.height / 2);
                setPosX(rectLand.x - getWidth() / 2);
            }
            if (getDirection() == LEFT_DIR && getGameWorld().physicalMap.haveCollisionWithLeftBrick(getBoundForCollisionWithMap()) != null) {
                Rectangle rectLeft = getGameWorld().physicalMap.haveCollisionWithLeftBrick(getBoundForCollisionWithMap());
                float posYCheck1 = getPosY() + getHeight() / 2 - rectLeft.y;
                if (posYCheck1 < 30 && rectLeft.y != 0 && rectLeft.x != 0 && posYCheck1 > 0)
                    setPosY(rectLeft.y - getHeight() / 2);
                float posYCheck2 = rectLeft.y + rectLeft.height - (getPosY() - getHeight() / 2);
                if (posYCheck2 < 30 && rectLeft.y != 0 && rectLeft.x != 0 && posYCheck2 > 0)
                    setPosY(rectLeft.y + rectLeft.height + getHeight() / 2);
                setPosX(rectLeft.x + rectLeft.width + getWidth() / 2);
            }
            if (getDirection() == UP_DIR && getGameWorld().physicalMap.haveCollisionWithTopBrick(getBoundForCollisionWithMap()) != null) {
                Rectangle rectTop = getGameWorld().physicalMap.haveCollisionWithTopBrick(getBoundForCollisionWithMap());
                float posXCheck1 = rectTop.x + rectTop.width - (getPosX() - getWidth() / 2);
                if (posXCheck1 < 30 && rectTop.y != 0 && rectTop.x != 0 && posXCheck1 > 0)
                    setPosX(rectTop.x + getWidth() + getWidth() / 2);
                float posXCheck2 = getPosX() + getWidth() / 2 - rectTop.x;
                if (posXCheck2 < 30 && rectTop.y != 0 && rectTop.x != 0 && posXCheck2 > 0)
                    setPosX(rectTop.x - rectTop.width / 2);
                setPosY(rectTop.y + rectTop.height + getHeight() / 2);
            }
        }
    }

    public void normal(){
        if (getState() == ALIVE) {
            ParticularObject object = getGameWorld().getCollisionWithEnemyObject(getGameWorld().player);
            if (detectBomb != null) {
                if (Math.abs(getPosX() - detectBomb.getPosX()) >= getWidth() || Math.abs(getPosY() - detectBomb.getPosY()) >= getHeight()) {
                    int x = (int) (detectBomb.getPosX() - getWidth() / 2) / ParticularObject.size;
                    int y = (int) (detectBomb.getPosY() - getHeight() / 2) / ParticularObject.size;
                    getGameWorld().physicalMap.physMap[y][x] = ".";
                    detectBomb = null;
                }
            }
            if (getDirection() == DOWN_DIR && getGameWorld().physicalMap.haveCollisionWithBot(getBoundForCollisionWithMap()) != null) {
                Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithBot(getBoundForCollisionWithMap());
                float posXCheck1 = rectLand.x + rectLand.width - (getPosX() - getWidth() / 2);
                if (posXCheck1 < 30 && rectLand.x != 0 && rectLand.y != 0 && posXCheck1 > 0)
                    setPosX(rectLand.x + rectLand.width + getWidth() / 2);
                float posXCheck2 = getPosX() + getWidth() / 2 - rectLand.x;
                if (posXCheck2 < 30 && rectLand.x != 0 && rectLand.y != 0 && posXCheck2 > 0)
                    setPosX(rectLand.x - rectLand.width / 2);
                setPosY(rectLand.y - getHeight() / 2);
            }

            if (getDirection() == RIGHT_DIR && getGameWorld().physicalMap.haveCollisionWithRight(getBoundForCollisionWithMap()) != null) {
                Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithRight(getBoundForCollisionWithMap());
                float posYCheck1 = getPosY() + getWidth() / 2 - rectLand.y;
                if (posYCheck1 < 30 && rectLand.x != 0 && rectLand.y != 0 && posYCheck1 > 0 && posYCheck1 > 0)
                    setPosY(rectLand.y - rectLand.height / 2);
                float posYCheck2 = rectLand.y + rectLand.height - (getPosY() - getHeight() / 2);
                if (posYCheck2 < 30 && rectLand.x != 0 && rectLand.y != 0 && posYCheck2 > 0 && posYCheck2 > 0)
                    setPosY(rectLand.y + rectLand.height + rectLand.height / 2);
                setPosX(rectLand.x - getWidth() / 2);
            }
            if (getDirection() == LEFT_DIR && getGameWorld().physicalMap.haveCollisionWithLeft(getBoundForCollisionWithMap()) != null) {
                Rectangle rectLeft = getGameWorld().physicalMap.haveCollisionWithLeft(getBoundForCollisionWithMap());
                float posYCheck1 = getPosY() + getHeight() / 2 - rectLeft.y;
                if (posYCheck1 < 30 && rectLeft.y != 0 && rectLeft.x != 0 && posYCheck1 > 0)
                    setPosY(rectLeft.y - getHeight() / 2);
                float posYCheck2 = rectLeft.y + rectLeft.height - (getPosY() - getHeight() / 2);
                if (posYCheck2 < 30 && rectLeft.y != 0 && rectLeft.x != 0 && posYCheck2 > 0)
                    setPosY(rectLeft.y + rectLeft.height + getHeight() / 2);
                setPosX(rectLeft.x + rectLeft.width + getWidth() / 2);
            }
            if (getDirection() == UP_DIR && getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionWithMap()) != null) {
                Rectangle rectTop = getGameWorld().physicalMap.haveCollisionWithTop(getBoundForCollisionWithMap());
                float posXCheck1 = rectTop.x + rectTop.width - (getPosX() - getWidth() / 2);
                if (posXCheck1 < 30 && rectTop.y != 0 && rectTop.x != 0 && posXCheck1 > 0)
                    setPosX(rectTop.x + getWidth() + getWidth() / 2);
                float posXCheck2 = getPosX() + getWidth() / 2 - rectTop.x;
                if (posXCheck2 < 30 && rectTop.y != 0 && rectTop.x != 0 && posXCheck2 > 0)
                    setPosX(rectTop.x - rectTop.width / 2);
                setPosY(rectTop.y + rectTop.height + getHeight() / 2);
            }
        }
    }
    public boolean isCheckPowerUpSpeed() {
        return checkPowerUpSpeed;
    }

    public void setCheckPowerUpSpeed(boolean checkPowerUpSpeed) {
        this.checkPowerUpSpeed = checkPowerUpSpeed;
    }

    public boolean isCheckPowerUpFlame() {
        return checkPowerUpFlame;
    }

    public void setCheckPowerUpFlame(boolean checkPowerUpFlame) {
        this.checkPowerUpFlame = checkPowerUpFlame;
    }

    public boolean isCheckPowerUpBombs() {
        return checkPowerUpBombs;
    }

    public void setCheckPowerUpBombs(boolean checkPowerUpBombs1) {
        this.checkPowerUpBombs = checkPowerUpBombs1;
    }
}

