package com.dong.gameobjects.particularobject.Item;

import com.dong.effect.Animation;
import com.dong.effect.AnimationName;
import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.GameWorld;

import java.awt.*;

public class BrickPass extends Item{
    Animation animation;
    public BrickPass(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);
        animation= CacheDataLoader.getInstance().getAnimation(AnimationName.powerup_wallpass);
        this.setState(DEATH);
        setCheckUsed(false);
    }
    public void update(){
        super.update();
        if (isCheckUsed()==true)
        {
            getGameWorld().player.setCheckBrickPass(true);
            setCheckUsed(false);
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        if (this.getState()==ALIVE)
        {
            animation.update(System.nanoTime());
            animation.draw((int)getPosX(),(int) getPosY(),g2);
        }
    }
}
