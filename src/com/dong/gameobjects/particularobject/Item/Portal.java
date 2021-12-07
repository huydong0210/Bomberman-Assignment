package com.dong.gameobjects.particularobject.Item;

import com.dong.effect.Animation;
import com.dong.effect.AnimationName;
import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.BackGround;
import com.dong.gameobjects.GameWorld;
import com.dong.gameobjects.particularobject.Brick;
import com.dong.gameobjects.particularobject.ParticularObject;
import com.dong.gameobjects.particularobject.characters.Player;

import java.awt.*;

public class Portal extends Item {
    Animation aniPortal;

    public Portal(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);
        aniPortal = CacheDataLoader.getInstance().getAnimation(AnimationName.portal);
    }

    @Override
    public void update() {
        if (isCheckUsed() == false) {
            ParticularObject object = getGameWorld().haveSamePosition(this);
            if (object instanceof Brick)
                setState(ALIVE);
            if (getState() == ALIVE) {
                Rectangle rectItem = new Rectangle((int) getPosX(), (int) getPosY(), (int) getWidth(), (int) getHeight());
                ParticularObject object1 = getGameWorld().getCollisionWithRectangle(rectItem);
                if (object1 instanceof Player && getGameWorld().countEnemy() == 0) {
                    clip.start();
                    setCheckUsed(true);
                    setState(DEATH);
                    getGameWorld().remove(this);
                }
            }
        }

        if (isCheckUsed() == true && getGameWorld().countEnemy() == 0) {
            if (CacheDataLoader.getInstance().getLevel() == BackGround.FINAL_LEVEL) {
                getGameWorld().getBackGround().setState(BackGround.WIN_GAME);
            } else {
                getGameWorld().setNextLevel(true);
                setCheckUsed(false);
                getGameWorld().remove(getGameWorld().player);
                getGameWorld().backGround.setState(BackGround.WIN_LEVEL);
                CacheDataLoader.getInstance().loadLevel();
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (this.getState() == ALIVE) {
            aniPortal.update(System.nanoTime());
            aniPortal.draw((int) getPosX(), (int) getPosY(), g2);
        }
    }
}
