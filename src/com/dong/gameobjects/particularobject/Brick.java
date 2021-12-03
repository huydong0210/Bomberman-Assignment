package com.dong.gameobjects.particularobject;

import com.dong.effect.Animation;
import com.dong.effect.AnimationName;
import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.GameObject;
import com.dong.gameobjects.GameWorld;

import java.awt.*;

public class Brick extends ParticularObject {

    Animation aniBrick, aniBrick_exploded;

    public Brick(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        aniBrick = CacheDataLoader.getInstance().getAnimation(AnimationName.brick);
        aniBrick_exploded = CacheDataLoader.getInstance().getAnimation(AnimationName.brick_exploded);
    }

    @Override
    public void update() {



    }

    public void draw(Graphics2D g2) {
        if (getState() == ALIVE) {
            aniBrick.update(System.nanoTime());
            aniBrick.draw((int) getPosX(), (int) getPosY(), g2);
        }
        if (getState() == HURT) {
            aniBrick_exploded.update(System.nanoTime());
            aniBrick_exploded.draw((int) getPosX(), (int) getPosY(), g2);
            if (aniBrick_exploded.isLastFrame()) {
                int a = (int) (getPosX() - getWidth()) / size;
                    int b = (int) (getPosY() - getHeight()) / size;
                    if (getGameWorld().physicalMap.physMap[b][a].equals("0"))  {
                        getGameWorld().physicalMap.physMap[b][a] = " ";
                }
                this.setState(DEATH);
                getGameWorld().remove(this);
            }

        }
    }
}
