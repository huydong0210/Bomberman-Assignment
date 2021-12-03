package com.dong.gameobjects;

import com.dong.gameobjects.particularobject.Item.PowerUpSpeed;
import com.dong.gameobjects.particularobject.ParticularObject;
import com.dong.gameobjects.particularobject.characters.Player;
import com.dong.gameobjects.particularobject.characters.enemy.Balloom;
import com.dong.gameobjects.particularobject.characters.enemy.Enemy;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameWorld {
    public Player player;
    public BackGround backGround;
    public boolean nextLevel;
    public PhysicalMap physicalMap;
    public List<ParticularObject> particularObjectList;

    public GameWorld() {
        this.particularObjectList = Collections.synchronizedList(new LinkedList<ParticularObject>());
        physicalMap = new PhysicalMap(0, 0, this);
        backGround=new BackGround(0,0,this);

    }

    public boolean isNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(boolean nextLevel) {
        this.nextLevel = nextLevel;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void update() {
    }

    //    }
    public void addObject(ParticularObject particularObject) {
        synchronized (particularObjectList) {
            particularObjectList.add(particularObject);
        }
    }

    public void remove(ParticularObject particularObject) {
        synchronized (particularObjectList) {
            for (int i = 0; i < particularObjectList.size(); i++) {
                GameObject object = particularObjectList.get(i);
                if (object == particularObject)
                    particularObjectList.remove(i);
            }
        }
    }
    public ParticularObject getCollisionWithRectangle(Rectangle rectangle)
    {
        synchronized (particularObjectList)
        {
            for (int i=0;i<particularObjectList.size();i++)
            {
                ParticularObject object=particularObjectList.get(i);
                if (object.getBoundForCollisionWithMap().intersects(rectangle))
                    return object;
            }
        }
        return null;
    }
    public ParticularObject getCollisionWithEnemyObject(ParticularObject particularObject) {
        synchronized (particularObjectList) {
            if (particularObject instanceof Player) {
                for (int i = 0; i < particularObjectList.size(); i++) {
                    if (particularObjectList.get(i) instanceof Player == false) {
                        ParticularObject particularObject1 = particularObjectList.get(i);
                        if (particularObject1.getBoundForCollisionWithMap().intersects(particularObject.getBoundForCollisionWithMap()))
                            return particularObject1;
                    }
                }
            } else {
                for (int i = 0; i < particularObjectList.size(); i++) {
                    ParticularObject particularObject1 = particularObjectList.get(i);
                    if (particularObject1.getBoundForCollisionWithMap().intersects(particularObject.getBoundForCollisionWithMap()) && particularObject1!=particularObject)
                        return particularObject1;
                }
            }
        }
        return null;
    }
    public ParticularObject haveSamePosition(ParticularObject object)
    {
        synchronized (particularObjectList){
        for (int i=0;i<particularObjectList.size();i++)
            if (object!=particularObjectList.get(i))
            {
                if (particularObjectList.get(i).getPosX()==object.getPosX ()&& particularObjectList.get(i).getPosY()==object.getPosY())
                    return particularObjectList.get(i);
            }
        return null;
        }
    }

    public void updateObjects() {
        backGround.update();
        synchronized (particularObjectList) {
            for (int i = 0; i < particularObjectList.size(); i++) {
                ParticularObject object = particularObjectList.get(i);
                object.update();

            }
        }
    }
    public void draw(Graphics2D g2) {
        physicalMap.draw(g2);

        synchronized (particularObjectList) {
            for (int i = particularObjectList.size() - 1; i >= 0; i--) {
                {
                    if (particularObjectList.get(i).getState()!=ParticularObject.DEATH)
                        particularObjectList.get(i).draw(g2);
                }
            }
        }
        backGround.draw(g2);
    }
    public void removeAllDeath(){
        synchronized (particularObjectList) {
            for (int i = particularObjectList.size() - 1; i >= 0; i--) {
                {
                    if (particularObjectList.get(i).getState()==ParticularObject.DEATH)
                        particularObjectList.remove(particularObjectList.get(i));

                }
            }
        }
    }
    public int countEnemy() {
        synchronized (particularObjectList) {
            for (int i = particularObjectList.size() - 1; i >= 0; i--) {
                {
                    ParticularObject object = particularObjectList.get(i);
                    if (object instanceof Enemy)
                        return 1;
                }
            }
            return 0;
        }
    }
}
