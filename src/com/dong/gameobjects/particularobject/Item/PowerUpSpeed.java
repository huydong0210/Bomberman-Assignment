package com.dong.gameobjects.particularobject.Item;

import com.dong.effect.Animation;
import com.dong.effect.AnimationName;
import com.dong.effect.CacheDataLoader;
import com.dong.gameobjects.GameWorld;
import com.dong.gameobjects.particularobject.Brick;
import com.dong.gameobjects.particularobject.ParticularObject;
import com.dong.gameobjects.particularobject.characters.Player;

import java.awt.*;

public class PowerUpSpeed extends Item {
    Animation aniPowerUpSpeed;


    public PowerUpSpeed(float posX, float posY, float width, float height, GameWorld gameWorld) {
        super(posX, posY, width, height, gameWorld);
        aniPowerUpSpeed = CacheDataLoader.getInstance().getAnimation(AnimationName.powerup_speed);
        this.setState(DEATH);
        setCheckUsed(false);
    }

    @Override
    public void update() {
        super.update();
        if (isCheckUsed()==true)
        {
            getGameWorld().player.setExtraSpeed(3);
            setCheckUsed(false);
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        if (this.getState() == ALIVE) {
            aniPowerUpSpeed.update(System.nanoTime());
            aniPowerUpSpeed.draw((int) getPosX(), (int) getPosY(), g2);
        }
    }
}
