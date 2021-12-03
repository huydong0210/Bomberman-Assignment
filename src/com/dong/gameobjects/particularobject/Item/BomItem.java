package com.dong.gameobjects.particularobject.Item;

import com.dong.effect.Animation;
import com.dong.effect.AnimationName;
import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.GameWorld;
import java.awt.*;

public class BomItem extends Item{
    Animation aniBomItem;
    public BomItem(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);
        this.setState(DEATH);
        aniBomItem= CacheDataLoader.getInstance().getAnimation(AnimationName.powerup_bombs);
        setCheckUsed(false);
    }

    public void update(){
        super.update();
        if (isCheckUsed()==true)
        {
            getGameWorld().player.setBombLimit(getGameWorld().player.getBombLimit()+1);
            setCheckUsed(false);
        }
    }
    @Override
    public void draw(Graphics2D g2) {
        if (this.getState()==ALIVE)
        {
            aniBomItem.update(System.nanoTime());
            aniBomItem.draw((int)getPosX(),(int) getPosY(),g2);
        }
    }
}
