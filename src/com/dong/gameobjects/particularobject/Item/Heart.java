package com.dong.gameobjects.particularobject.Item;

import com.dong.effect.Animation;
import com.dong.effect.AnimationName;
import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.GameWorld;

import java.awt.*;

public class Heart extends Item{
    Animation aniHeart;
    public Heart(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);
        aniHeart=CacheDataLoader.getInstance().getAnimation(AnimationName.powerup_detonator);
    }
    public void update(){
        super.update();
        if (isCheckUsed()==true)
        {
            getGameWorld().player.setHeart(getGameWorld().player.getHeart()+1);
            setCheckUsed(false);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (this.getState()==ALIVE)
        {
            aniHeart.update(System.nanoTime());
            aniHeart.draw((int)getPosX(),(int) getPosY(),g2);
        }
    }
}
