package com.dong.gameobjects.particularobject.Item;

import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.GameWorld;
import com.dong.gameobjects.particularobject.Brick;
import com.dong.gameobjects.particularobject.ParticularObject;
import com.dong.gameobjects.particularobject.characters.Player;

import javax.sound.sampled.Clip;
import java.awt.*;

public abstract class Item extends ParticularObject {
    private boolean checkUsed;

    Clip clip;

    public boolean isCheckUsed() {
        return checkUsed;
    }

    public void setCheckUsed(boolean checkUsed) {
        this.checkUsed = checkUsed;
    }

    public Item(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, width , height, gameWorld);
        clip = CacheDataLoader.getInstance().getAudio("item");
        checkUsed=false;
        setState(DEATH);
    }

    @Override
    public void update() {


        if (isCheckUsed() == false) {
            ParticularObject object = getGameWorld().haveSamePosition(this);
            if (object instanceof Brick)
                setState(ALIVE);
            if (getState()==ALIVE)
            {
                Rectangle rectItem=new Rectangle((int ) getPosX(),(int )getPosY(),(int) getWidth(), (int ) getHeight());
                ParticularObject object1=getGameWorld().getCollisionWithRectangle(rectItem);
                if (object1 instanceof Player)
                {
                    clip.start();
                    checkUsed = true;
                    setState(DEATH);
                    getGameWorld().remove(this);
                }
            }
        }

    }
}
