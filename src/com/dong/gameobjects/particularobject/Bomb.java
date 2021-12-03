package com.dong.gameobjects.particularobject;

import com.dong.effect.*;
import com.dong.gameobjects.GameWorld;
import com.dong.gameobjects.particularobject.characters.enemy.*;

import javax.sound.sampled.Clip;
 import java.awt.*;

public class Bomb extends ParticularObject {
    Animation aniBomb, aniBombExploded, aniExplosionHorizontal_left1, aniExplosionHorizontal_left_last, aniExplosionHorizontal_right_last, aniExplosionHorizontal_right1;
    Animation aniExplosionHorizontal_left2, aniExplosionHorizontal_right2;
    Animation aniExplosionVertical_top1, aniExplosionVertical_top_last, aniExplosionVertical_down_last, aniExplosionVertical_down1;
    Animation aniExplosionVertical_top2, aniExplosionVertical_down2;
    Clip clipBombBang, clipNewBomb;


    public Bomb(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.setState(ALIVE);
        this.setWidth(ParticularObject.size);
        this.setHeight(ParticularObject.size);

        aniBomb = CacheDataLoader.getInstance().getAnimation(AnimationName.bomb);
        aniBomb.add(CacheDataLoader.getInstance().getFrameImage("grass"), 500 * 1000000);
        aniBombExploded = CacheDataLoader.getInstance().getAnimation(AnimationName.bomb_exploded);

        aniExplosionHorizontal_left1 = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_horizontal);
        aniExplosionHorizontal_left2 = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_horizontal);

        aniExplosionHorizontal_right1 = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_horizontal);
        aniExplosionHorizontal_right2 = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_horizontal);

        aniExplosionHorizontal_left_last = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_horizontal_left_last);
        aniExplosionHorizontal_right_last = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_horizontal_right_last);

        aniExplosionVertical_top1 = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_vertical);
        aniExplosionVertical_down1 = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_vertical);

        aniExplosionVertical_top2 = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_vertical);
        aniExplosionVertical_down2 = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_vertical);

        aniExplosionVertical_top_last = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_vertical_top_last);
        aniExplosionVertical_down_last = CacheDataLoader.getInstance().getAnimation(AnimationName.explosion_vertical_down_last);

        clipBombBang=CacheDataLoader.getInstance().getAudio("bomb_bang");
        clipNewBomb=CacheDataLoader.getInstance().getAudio("newbomb");
    }

    @Override
    public void update() {

        if (getState() == DEATH) {
            int x = (int) (getPosX() - getWidth() / 2) / ParticularObject.size;
            int y = (int) (getPosY() - getHeight() / 2) / ParticularObject.size;
            getGameWorld().physicalMap.physMap[y][x] = " ";
        }

    }

    public void draw(Graphics2D g2) {
        if (getGameWorld().player.isCheckBombPass())
            bombPowerUp(g2);
        else
            normalBomb(g2);
    }
    public void normalBomb(Graphics2D g2)
    {
        int x = (int) getPosX();
        int y = (int) getPosY();
        int a, b;
        int c = ParticularObject.size;
        int distance = 0;

        if (aniBomb.isLastFrame() == false) {
            clipNewBomb.start();
            aniBomb.update(System.nanoTime());
            aniBomb.draw(x, y, g2);

        } else {
            clipBombBang.start();
            String[][] phy;
            phy = getGameWorld().physicalMap.physMap;
            ParticularObject object;
            Rectangle rectPlayer = getGameWorld().player.getBoundForCollisionWithMap();
            rectPlayer.setSize((int) getWidth() - 25, (int) getHeight() - 25);
            Rectangle rectBomb;
            aniBombExploded.update(System.nanoTime());
            aniBombExploded.draw(x, y, g2);
            //nổ người
            rectBomb = new Rectangle(x - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
            if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                getGameWorld().player.setState(HURT);

            if (getGameWorld().physicalMap.physMap[(int) (y - getHeight() / 2) / c][(int) (x - getWidth() - getWidth() / 2) / c].equals("#") == false) {
                //bom no trai 1
                aniExplosionHorizontal_left1.update(System.nanoTime());
                aniExplosionHorizontal_left1.draw(x - c, y, g2);
                //nổ tường
                a = (int) (x - getWidth() - getWidth() / 2) / c;
                b = (int) (y - getHeight() / 2) / c;
                if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                    if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co speed up
                    if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";

                    }
                    // no tuong co bomItem
                    if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co heart item
                    if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co portal
                    if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co bombpass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co brick pass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                }
                //nổ người
                rectBomb = new Rectangle(x - c - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                    getGameWorld().player.setState(HURT);
                //nổ balloom
                object = getGameWorld().getCollisionWithRectangle(rectBomb);
                if (object != null && object instanceof Balloom) {
                    object.setState(HURT);
                }
                //nổ oneal
                if (object != null && object instanceof Oneal) {
                    object.setState(HURT);
                }
                //nổ doll
                if (object != null && object instanceof Doll) {
                    object.setState(HURT);
                }
                // nổ kodonrian
                if (object != null && object instanceof Kondoria) {
                    object.setState(HURT);
                }
                // nổ Minvo
                if (object != null && object instanceof Minvo) {
                    object.setState(HURT);
                }



                if (phy[(int) (y - getHeight() / 2) / c][(int) (x - getWidth() - getWidth() - getWidth() / 2) / c].equals("#") == false) {
                    aniExplosionHorizontal_left_last.update(System.nanoTime());
                    aniExplosionHorizontal_left_last.draw(x - c - c, y, g2);
                    //nổ tường
                    a = (int) (x - getWidth() - getWidth() - getWidth() / 2) / c;
                    b = (int) (y - getHeight() / 2) / c;
                    if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                        if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co speed up
                        if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";

                        }
                        // no tuong co bomItem
                        if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co heart item
                        if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co portal
                        if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co bombpass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co brick pass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                    }
                    //nổ người
                    rectBomb = new Rectangle(x - c - c - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                    if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                        getGameWorld().player.setState(HURT);
                    //nổ balloom
                    object = getGameWorld().getCollisionWithRectangle(rectBomb);
                    if (object != null && object instanceof Balloom) {
                        object.setState(HURT);
                    }
                    //nổ oneal
                    if (object != null && object instanceof Oneal) {
                        object.setState(HURT);
                    }
                    //nổ doll
                    if (object != null && object instanceof Doll) {
                        object.setState(HURT);
                    }
                    // nổ kodonrian
                    if (object != null && object instanceof Kondoria) {
                        object.setState(HURT);
                    }
                    // nổ Minvo
                    if (object != null && object instanceof Minvo) {
                        object.setState(HURT);
                    }
                }
            }


            if (phy[(int) (y - getHeight() / 2) / c][(int) (x + getWidth() - getWidth() / 2) / c].equals("#") == false) {
                aniExplosionHorizontal_right1.update(System.nanoTime());
                aniExplosionHorizontal_right1.draw(x + c, y, g2);
                //nổ tường
                a = (int) (x + getWidth() - getWidth() / 2) / c;
                b = (int) (y - getHeight() / 2) / c;
                if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                    if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co speed up
                    if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";

                    }
                    // no tuong co bomItem
                    if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co heart item
                    if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co portal
                    if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co bombpass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co brick pass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }

                }
                //nổ người
                rectBomb = new Rectangle(x + c - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                    getGameWorld().player.setState(HURT);

                //nổ balloom
                object = getGameWorld().getCollisionWithRectangle(rectBomb);
                if (object != null && object instanceof Balloom) {
                    object.setState(HURT);
                }
                //nổ oneal
                if (object != null && object instanceof Oneal) {
                    object.setState(HURT);
                }
                //nổ doll
                if (object != null && object instanceof Doll) {
                    object.setState(HURT);
                }
                // nổ kodonrian
                if (object != null && object instanceof Kondoria) {
                    object.setState(HURT);
                }
                // nổ Minvo
                if (object != null && object instanceof Minvo) {
                    object.setState(HURT);
                }


                if (phy[(int) (y - getHeight() / 2) / c][(int) (x + getWidth() + getWidth() - getWidth() / 2) / c].equals("#") == false) {
                    aniExplosionHorizontal_right_last.update(System.nanoTime());
                    aniExplosionHorizontal_right_last.draw(x + c + c, y, g2);
                    //nổ tường
                    a = (int) (x + getWidth() + getWidth() - getWidth() / 2) / c;
                    b = (int) (y - getHeight() / 2) / c;
                    if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                        if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co speed up
                        if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";

                        }
                        // no tuong co bomItem
                        if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co heart item
                        if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co portal
                        if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co bombpass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co brick pass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                    }
                    //nổ người
                    rectBomb = new Rectangle(x + c + c - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                    if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                        getGameWorld().player.setState(HURT);
                    //nổ balloom
                    object = getGameWorld().getCollisionWithRectangle(rectBomb);
                    if (object != null && object instanceof Balloom) {
                        object.setState(HURT);
                    }
                    //nổ oneal
                    if (object != null && object instanceof Oneal) {
                        object.setState(HURT);
                    }
                    //nổ doll
                    if (object != null && object instanceof Doll) {
                        object.setState(HURT);
                    }
                    // nổ kodonrian
                    if (object != null && object instanceof Kondoria) {
                        object.setState(HURT);
                    }
                    // nổ Minvo
                    if (object != null && object instanceof Minvo) {
                        object.setState(HURT);
                    }

                }
            }

            if (phy[(int) (y - getHeight() - getHeight() / 2) / c][(int) (x - getWidth() / 2) / c].equals("#") == false) {
                aniExplosionVertical_top1.update(System.nanoTime());
                aniExplosionVertical_top1.draw(x, y - c, g2);
                //nổ tường
                a = (int) (x - getWidth() / 2) / c;
                b = (int) (y - getHeight() - getHeight() / 2) / c;
                if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                    if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co speed up
                    if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";

                    }
                    // no tuong co bomItem
                    if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co heart item
                    if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co portal
                    if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co bombpass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co brick pass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                }
                //nổ người
                rectBomb = new Rectangle(x - (int) getWidth(), y - c - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                    getGameWorld().player.setState(HURT);
                //nổ balloom
                object = getGameWorld().getCollisionWithRectangle(rectBomb);
                if (object != null && object instanceof Balloom) {
                    object.setState(HURT);
                }
                //nổ oneal
                if (object != null && object instanceof Oneal) {
                    object.setState(HURT);
                }
                //nổ doll
                if (object != null && object instanceof Doll) {
                    object.setState(HURT);
                }
                // nổ kodonrian
                if (object != null && object instanceof Kondoria) {
                    object.setState(HURT);
                }
                // nổ Minvo
                if (object != null && object instanceof Minvo) {
                    object.setState(HURT);
                }

                if (phy[(int) (y - getHeight() - getHeight() - getHeight() / 2) / c][(int) (x - getWidth() / 2) / c].equals("#") == false) {
                    aniExplosionVertical_top_last.update(System.nanoTime());
                    aniExplosionVertical_top_last.draw(x, y - c - c, g2);
                    //nổ tường
                    a = (int) (x - getWidth() / 2) / c;
                    b = (int) (y - getHeight() - getHeight() - getHeight() / 2) / c;
                    if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                        if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co speed up
                        if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";

                        }
                        // no tuong co bomItem
                        if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co heart item
                        if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co portal
                        if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co bombpass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co brick pass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                    }
                    //nổ người
                    rectBomb = new Rectangle(x - (int) getWidth(), y - c - c - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                    if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                        getGameWorld().player.setState(HURT);
                    //nổ balloom
                    object = getGameWorld().getCollisionWithRectangle(rectBomb);
                    if (object != null && object instanceof Balloom) {
                        object.setState(HURT);
                    }
                    //nổ oneal
                    if (object != null && object instanceof Oneal) {
                        object.setState(HURT);
                    }
                    //nổ doll
                    if (object != null && object instanceof Doll) {
                        object.setState(HURT);
                    }
                    // nổ kodonrian
                    if (object != null && object instanceof Kondoria) {
                        object.setState(HURT);
                    }
                    // nổ Minvo
                    if (object != null && object instanceof Minvo) {
                        object.setState(HURT);
                    }


                }
            }

            if (phy[(int) (y + getHeight() - getHeight() / 2) / c][(int) (x - getWidth() / 2) / c].equals("#") == false) {
                aniExplosionVertical_down1.update(System.nanoTime());
                aniExplosionVertical_down1.draw(x, y + c, g2);
                //nổ tường
                a = (int) (x - getWidth() / 2) / c;
                b = (int) (y + getHeight() - getHeight() / 2) / c;
                if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                    if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co speed up
                    if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";

                    }
                    // no tuong co bomItem
                    if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co heart item
                    if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co portal
                    if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co bombpass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co brick pass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                }

                //nổ người
                rectBomb = new Rectangle(x - (int) getWidth(), y + c - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                    getGameWorld().player.setState(HURT);
                //nổ balloom
                object = getGameWorld().getCollisionWithRectangle(rectBomb);
                if (object != null && object instanceof Balloom) {
                    object.setState(HURT);
                }
                //nổ oneal
                if (object != null && object instanceof Oneal) {
                    object.setState(HURT);
                }
                //nổ doll
                if (object != null && object instanceof Doll) {
                    object.setState(HURT);
                }
                // nổ kodonrian
                if (object != null && object instanceof Kondoria) {
                    object.setState(HURT);
                }
                // nổ Minvo
                if (object != null && object instanceof Minvo) {
                    object.setState(HURT);
                }

                if (phy[(int) (y + getHeight() + getHeight() - getHeight() / 2) / c][(int) (x - getWidth() / 2) / c].equals("#") == false) {
                    aniExplosionVertical_down_last.update(System.nanoTime());
                    aniExplosionVertical_down_last.draw(x, y + c + c, g2);
                    //nổ tường
                    a = (int) (x - getWidth() / 2) / c;
                    b = (int) (y + getHeight() + getHeight() - getHeight() / 2) / c;
                    if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                        if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co speed up
                        if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";

                        }
                        // no tuong co bomItem
                        if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co heart item
                        if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co portal
                        if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co bombpass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co brick pass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                    }

                    //nổ người
                    rectBomb = new Rectangle(x - (int) getWidth(), y + c + c - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                    if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                        getGameWorld().player.setState(HURT);
                    //nổ balloom
                    object = getGameWorld().getCollisionWithRectangle(rectBomb);
                    if (object != null && object instanceof Balloom) {
                        object.setState(HURT);
                    }
                    //nổ oneal
                    if (object != null && object instanceof Oneal) {
                        object.setState(HURT);
                    }
                    //nổ doll
                    if (object != null && object instanceof Doll) {
                        object.setState(HURT);
                    }
                    // nổ kodonrian
                    if (object != null && object instanceof Kondoria) {
                        object.setState(HURT);
                    }
                    // nổ Minvo
                    if (object != null && object instanceof Minvo) {
                        object.setState(HURT);
                    }
                }
            }
            if (aniBombExploded.isLastFrame()) {
                getGameWorld().player.setCountBomb(getGameWorld().player.getCountBomb() - 1);
                this.setState(DEATH);
            }
        }

    }










    public void bombPowerUp(Graphics2D g2)
    {
        int x = (int) getPosX();
        int y = (int) getPosY();
        int a, b;
        int c = ParticularObject.size;
        int distance = 0;

        if (aniBomb.isLastFrame() == false) {
            clipNewBomb.start();
            aniBomb.update(System.nanoTime());
            aniBomb.draw(x, y, g2);

        } else {
            clipBombBang.start();
            String[][] phy;
            phy = getGameWorld().physicalMap.physMap;
            ParticularObject object;
            Rectangle rectPlayer = getGameWorld().player.getBoundForCollisionWithMap();
            rectPlayer.setSize((int) getWidth() - 25, (int) getHeight() - 25);
            Rectangle rectBomb;
            aniBombExploded.update(System.nanoTime());
            aniBombExploded.draw(x, y, g2);
            //nổ người
            rectBomb = new Rectangle(x - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
            if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                getGameWorld().player.setState(HURT);


            if (getGameWorld().physicalMap.physMap[(int) (y - getHeight() / 2) / c][(int) (x - getWidth() - getWidth() / 2) / c].equals("#") == false) {
                //bom no trai 1
                aniExplosionHorizontal_left1.update(System.nanoTime());
                aniExplosionHorizontal_left1.draw(x - c, y, g2);
                //nổ tường
                a = (int) (x - getWidth() - getWidth() / 2) / c;
                b = (int) (y - getHeight() / 2) / c;
                if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                    if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co speed up
                    if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";

                    }
                    // no tuong co bomItem
                    if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co heart item
                    if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co portal
                    if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co bombpass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co brick pass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                }
                //nổ người
                rectBomb = new Rectangle(x - c - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                    getGameWorld().player.setState(HURT);
                //nổ balloom
                object = getGameWorld().getCollisionWithRectangle(rectBomb);
                if (object != null && object instanceof Balloom) {
                    object.setState(HURT);
                }
                //nổ oneal
                if (object != null && object instanceof Oneal) {
                    object.setState(HURT);
                }
                //nổ doll
                if (object != null && object instanceof Doll) {
                    object.setState(HURT);
                }
                // nổ kodonrian
                if (object != null && object instanceof Kondoria) {
                    object.setState(HURT);
                }
                // nổ Minvo
                if (object != null && object instanceof Minvo) {
                    object.setState(HURT);
                }



                if (phy[(int) (y - getHeight() / 2) / c][(int) (x - getWidth() - getWidth() - getWidth() / 2) / c].equals("#") == false) {
                    aniExplosionHorizontal_left2.update(System.nanoTime());
                    aniExplosionHorizontal_left2.draw(x - c - c, y, g2);
                    //nổ tường
                    a = (int) (x - getWidth() - getWidth() - getWidth() / 2) / c;
                    b = (int) (y - getHeight() / 2) / c;
                    if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                        if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co speed up
                        if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";

                        }
                        // no tuong co bomItem
                        if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co heart item
                        if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co portal
                        if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co bombpass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co brick pass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                    }
                    //nổ người
                    rectBomb = new Rectangle(x - c - c - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                    if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                        getGameWorld().player.setState(HURT);
                    //nổ balloom
                    object = getGameWorld().getCollisionWithRectangle(rectBomb);
                    if (object != null && object instanceof Balloom) {
                        object.setState(HURT);
                    }
                    //nổ oneal
                    if (object != null && object instanceof Oneal) {
                        object.setState(HURT);
                    }
                    //nổ doll
                    if (object != null && object instanceof Doll) {
                        object.setState(HURT);
                    }
                    // nổ kodonrian
                    if (object != null && object instanceof Kondoria) {
                        object.setState(HURT);
                    }
                    // nổ Minvo
                    if (object != null && object instanceof Minvo) {
                        object.setState(HURT);
                    }

                    if (phy[(int) (y - getHeight() / 2) / c][(int) (x - getWidth() - getWidth() - getWidth() - getWidth() / 2) / c].equals("#") == false) {
                        aniExplosionHorizontal_left_last.update(System.nanoTime());
                        aniExplosionHorizontal_left_last.draw(x - c - c - c, y, g2);
                        //nổ tường
                        a = (int) (x - getWidth() - getWidth() - getWidth() - getWidth() / 2) / c;
                        b = (int) (y - getHeight() / 2) / c;
                        if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                            if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            // no tuong co speed up
                            if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";

                            }
                            // no tuong co bomItem
                            if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co heart item
                            if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co portal
                            if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            // no tuong co bombpass
                            if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co brick pass
                            if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                        }
                        //nổ người
                        rectBomb = new Rectangle(x - c - c - c - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                        if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                            getGameWorld().player.setState(HURT);

                        //nổ balloom
                        object = getGameWorld().getCollisionWithRectangle(rectBomb);
                        if (object != null && object instanceof Balloom) {
                            object.setState(HURT);
                        }
                        //nổ oneal
                        if (object != null && object instanceof Oneal) {
                            object.setState(HURT);
                        }
                        //nổ doll
                        if (object != null && object instanceof Doll) {
                            object.setState(HURT);
                        }
                        // nổ kodonrian
                        if (object != null && object instanceof Kondoria) {
                            object.setState(HURT);
                        }
                        // nổ Minvo
                        if (object != null && object instanceof Minvo) {
                            object.setState(HURT);
                        }
                    }
                }
            }


            if (phy[(int) (y - getHeight() / 2) / c][(int) (x + getWidth() - getWidth() / 2) / c].equals("#") == false) {
                aniExplosionHorizontal_right1.update(System.nanoTime());
                aniExplosionHorizontal_right1.draw(x + c, y, g2);
                //nổ tường
                a = (int) (x + getWidth() - getWidth() / 2) / c;
                b = (int) (y - getHeight() / 2) / c;
                if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                    if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co speed up
                    if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";

                    }
                    // no tuong co bomItem
                    if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co heart item
                    if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co portal
                    if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co bombpass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co brick pass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }

                }
                //nổ người
                rectBomb = new Rectangle(x + c - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                    getGameWorld().player.setState(HURT);

                //nổ balloom
                object = getGameWorld().getCollisionWithRectangle(rectBomb);
                if (object != null && object instanceof Balloom) {
                    object.setState(HURT);
                }
                //nổ oneal
                if (object != null && object instanceof Oneal) {
                    object.setState(HURT);
                }
                //nổ doll
                if (object != null && object instanceof Doll) {
                    object.setState(HURT);
                }
                // nổ kodonrian
                if (object != null && object instanceof Kondoria) {
                    object.setState(HURT);
                }
                // nổ Minvo
                if (object != null && object instanceof Minvo) {
                    object.setState(HURT);
                }


                if (phy[(int) (y - getHeight() / 2) / c][(int) (x + getWidth() + getWidth() - getWidth() / 2) / c].equals("#") == false) {
                    aniExplosionHorizontal_right2.update(System.nanoTime());
                    aniExplosionHorizontal_right2.draw(x + c + c, y, g2);
                    //nổ tường
                    a = (int) (x + getWidth() + getWidth() - getWidth() / 2) / c;
                    b = (int) (y - getHeight() / 2) / c;
                    if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                        if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co speed up
                        if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";

                        }
                        // no tuong co bomItem
                        if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co heart item
                        if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co portal
                        if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co bombpass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co brick pass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                    }
                    //nổ người
                    rectBomb = new Rectangle(x + c + c - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                    if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                        getGameWorld().player.setState(HURT);
                    //nổ balloom
                    object = getGameWorld().getCollisionWithRectangle(rectBomb);
                    if (object != null && object instanceof Balloom) {
                        object.setState(HURT);
                    }
                    //nổ oneal
                    if (object != null && object instanceof Oneal) {
                        object.setState(HURT);
                    }
                    //nổ doll
                    if (object != null && object instanceof Doll) {
                        object.setState(HURT);
                    }
                    // nổ kodonrian
                    if (object != null && object instanceof Kondoria) {
                        object.setState(HURT);
                    }
                    // nổ Minvo
                    if (object != null && object instanceof Minvo) {
                        object.setState(HURT);
                    }

                    if (phy[(int) (y - getHeight() / 2) / c][(int) (x + getWidth() + getWidth() + getWidth() - getWidth() / 2) / c].equals("#") == false) {
                        aniExplosionHorizontal_right_last.update(System.nanoTime());
                        aniExplosionHorizontal_right_last.draw(x + c + c + c, y, g2);
                        //nổ tường
                        a = (int) (x + getWidth() + getWidth() + getWidth() - getWidth() / 2) / c;
                        b = (int) (y - getHeight() / 2) / c;
                        if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                            if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            // no tuong co speed up
                            if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";

                            }
                            // no tuong co bomItem
                            if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co heart item
                            if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co portal
                            if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            // no tuong co bombpass
                            if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co brick pass
                            if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                        }
                        //nổ người
                        rectBomb = new Rectangle(x + c + c + c - (int) getWidth(), y - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                        if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                            getGameWorld().player.setState(HURT);
                        //nổ balloom
                        object = getGameWorld().getCollisionWithRectangle(rectBomb);
                        if (object != null && object instanceof Balloom) {
                            object.setState(HURT);
                        }
                        //nổ oneal
                        if (object != null && object instanceof Oneal) {
                            object.setState(HURT);
                        }
                        //nổ doll
                        if (object != null && object instanceof Doll) {
                            object.setState(HURT);
                        }
                        // nổ kodonrian
                        if (object != null && object instanceof Kondoria) {
                            object.setState(HURT);
                        }
                        // nổ Minvo
                        if (object != null && object instanceof Minvo) {
                            object.setState(HURT);
                        }
                    }
                }
            }

            if (phy[(int) (y - getHeight() - getHeight() / 2) / c][(int) (x - getWidth() / 2) / c].equals("#") == false) {
                aniExplosionVertical_top1.update(System.nanoTime());
                aniExplosionVertical_top1.draw(x, y - c, g2);
                //nổ tường
                a = (int) (x - getWidth() / 2) / c;
                b = (int) (y - getHeight() - getHeight() / 2) / c;
                if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                    if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co speed up
                    if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";

                    }
                    // no tuong co bomItem
                    if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co heart item
                    if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co portal
                    if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co bombpass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co brick pass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                }
                //nổ người
                rectBomb = new Rectangle(x - (int) getWidth(), y - c - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                    getGameWorld().player.setState(HURT);
                //nổ balloom
                object = getGameWorld().getCollisionWithRectangle(rectBomb);
                if (object != null && object instanceof Balloom) {
                    object.setState(HURT);
                }
                //nổ oneal
                if (object != null && object instanceof Oneal) {
                    object.setState(HURT);
                }
                //nổ doll
                if (object != null && object instanceof Doll) {
                    object.setState(HURT);
                }
                // nổ kodonrian
                if (object != null && object instanceof Kondoria) {
                    object.setState(HURT);
                }
                // nổ Minvo
                if (object != null && object instanceof Minvo) {
                    object.setState(HURT);
                }

                if (phy[(int) (y - getHeight() - getHeight() - getHeight() / 2) / c][(int) (x - getWidth() / 2) / c].equals("#") == false) {
                    aniExplosionVertical_top2.update(System.nanoTime());
                    aniExplosionVertical_top2.draw(x, y - c - c, g2);
                    //nổ tường
                    a = (int) (x - getWidth() / 2) / c;
                    b = (int) (y - getHeight() - getHeight() - getHeight() / 2) / c;
                    if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                        if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co speed up
                        if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";

                        }
                        // no tuong co bomItem
                        if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co heart item
                        if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co portal
                        if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co bombpass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co brick pass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                    }
                    //nổ người
                    rectBomb = new Rectangle(x - (int) getWidth(), y - c - c - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                    if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                        getGameWorld().player.setState(HURT);
                    //nổ balloom
                    object = getGameWorld().getCollisionWithRectangle(rectBomb);
                    if (object != null && object instanceof Balloom) {
                        object.setState(HURT);
                    }
                    //nổ oneal
                    if (object != null && object instanceof Oneal) {
                        object.setState(HURT);
                    }
                    //nổ doll
                    if (object != null && object instanceof Doll) {
                        object.setState(HURT);
                    }
                    // nổ kodonrian
                    if (object != null && object instanceof Kondoria) {
                        object.setState(HURT);
                    }
                    // nổ Minvo
                    if (object != null && object instanceof Minvo) {
                        object.setState(HURT);
                    }

                    if (phy[(int) (y - getHeight() - getHeight() - getHeight() - getHeight() / 2) / c][(int) (x - getWidth() / 2) / c].equals("#") == false) {
                        aniExplosionVertical_top_last.update(System.nanoTime());
                        aniExplosionVertical_top_last.draw(x, y - c - c - c, g2);
                        //nổ tường
                        a = (int) (x - getWidth() / 2) / c;
                        b = (int) (y - getHeight() - getHeight() - getHeight() - getHeight() / 2) / c;
                        if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                            if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            // no tuong co speed up
                            if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";

                            }
                            // no tuong co bomItem
                            if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co heart item
                            if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co portal
                            if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            // no tuong co bombpass
                            if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co brick pass
                            if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                        }
                        //nổ người
                        rectBomb = new Rectangle(x - (int) getWidth(), y - c - c - c - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                        if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                            getGameWorld().player.setState(HURT);
                        //nổ balloom
                        object = getGameWorld().getCollisionWithRectangle(rectBomb);
                        if (object != null && object instanceof Balloom) {
                            object.setState(HURT);
                        }
                        //nổ oneal
                        if (object != null && object instanceof Oneal) {
                            object.setState(HURT);
                        }
                        //nổ doll
                        if (object != null && object instanceof Doll) {
                            object.setState(HURT);
                        }
                        // nổ kodonrian
                        if (object != null && object instanceof Kondoria) {
                            object.setState(HURT);
                        }
                        // nổ Minvo
                        if (object != null && object instanceof Minvo) {
                            object.setState(HURT);
                        }
                    }
                }
            }

            if (phy[(int) (y + getHeight() - getHeight() / 2) / c][(int) (x - getWidth() / 2) / c].equals("#") == false) {
                aniExplosionVertical_down1.update(System.nanoTime());
                aniExplosionVertical_down1.draw(x, y + c, g2);
                //nổ tường
                a = (int) (x - getWidth() / 2) / c;
                b = (int) (y + getHeight() - getHeight() / 2) / c;
                if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                    if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co speed up
                    if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";

                    }
                    // no tuong co bomItem
                    if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co heart item
                    if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co portal
                    if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    // no tuong co bombpass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                    //no tuong co brick pass
                    if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                        getGameWorld().physicalMap.physMap[b][a] = "0";
                    }
                }

                //nổ người
                rectBomb = new Rectangle(x - (int) getWidth(), y + c - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                    getGameWorld().player.setState(HURT);
                //nổ balloom
                object = getGameWorld().getCollisionWithRectangle(rectBomb);
                if (object != null && object instanceof Balloom) {
                    object.setState(HURT);
                }
                //nổ oneal
                if (object != null && object instanceof Oneal) {
                    object.setState(HURT);
                }
                //nổ doll
                if (object != null && object instanceof Doll) {
                    object.setState(HURT);
                }
                // nổ kodonrian
                if (object != null && object instanceof Kondoria) {
                    object.setState(HURT);
                }
                // nổ Minvo
                if (object != null && object instanceof Minvo) {
                    object.setState(HURT);
                }

                if (phy[(int) (y + getHeight() + getHeight() - getHeight() / 2) / c][(int) (x - getWidth() / 2) / c].equals("#") == false) {
                    aniExplosionVertical_down2.update(System.nanoTime());
                    aniExplosionVertical_down2.draw(x, y + c + c, g2);
                    //nổ tường
                    a = (int) (x - getWidth() / 2) / c;
                    b = (int) (y + getHeight() + getHeight() - getHeight() / 2) / c;
                    if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                        if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co speed up
                        if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";

                        }
                        // no tuong co bomItem
                        if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co heart item
                        if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co portal
                        if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        // no tuong co bombpass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                        //no tuong co brick pass
                        if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                            getGameWorld().physicalMap.physMap[b][a] = "0";
                        }
                    }

                    //nổ người
                    rectBomb = new Rectangle(x - (int) getWidth(), y + c + c - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                    if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                        getGameWorld().player.setState(HURT);
                    //nổ balloom
                    object = getGameWorld().getCollisionWithRectangle(rectBomb);
                    if (object != null && object instanceof Balloom) {
                        object.setState(HURT);
                    }
                    //nổ oneal
                    if (object != null && object instanceof Oneal) {
                        object.setState(HURT);
                    }
                    //nổ doll
                    if (object != null && object instanceof Doll) {
                        object.setState(HURT);
                    }
                    // nổ kodonrian
                    if (object != null && object instanceof Kondoria) {
                        object.setState(HURT);
                    }
                    // nổ Minvo
                    if (object != null && object instanceof Minvo) {
                        object.setState(HURT);
                    }
                    if (phy[(int) (y + getHeight() + getHeight() + getHeight() - getHeight() / 2) / c][(int) (x - getWidth() / 2) / c].equals("#") == false) {

                        aniExplosionVertical_down_last.update(System.nanoTime());
                        aniExplosionVertical_down_last.draw(x, y + c + c + c, g2);
                        //nổ tường
                        a = (int) (x - getWidth() / 2) / c;
                        b = (int) (y + getHeight() + getHeight() + getHeight() - getHeight() / 2) / c;
                        if (a >= 0 && b >= 0 && a < getGameWorld().physicalMap.physMap[0].length && b < getGameWorld().physicalMap.physMap.length) {
                            if (getGameWorld().physicalMap.physMap[b][a].equals("*")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            // no tuong co speed up
                            if (getGameWorld().physicalMap.physMap[b][a].equals("s")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";

                            }
                            // no tuong co bomItem
                            if (getGameWorld().physicalMap.physMap[b][a].equals("b")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co heart item
                            if (getGameWorld().physicalMap.physMap[b][a].equals("h")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co portal
                            if (getGameWorld().physicalMap.physMap[b][a].equals("x")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            // no tuong co bombpass
                            if (getGameWorld().physicalMap.physMap[b][a].equals("q")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                            //no tuong co brick pass
                            if (getGameWorld().physicalMap.physMap[b][a].equals("f")) {
                                getGameWorld().physicalMap.physMap[b][a] = "0";
                            }
                        }
                        // no nguoi
                        rectBomb = new Rectangle(x - (int) getWidth(), y + c + c + c - (int) getHeight(), (int) getWidth() - distance, (int) getHeight() - distance);
                        if (rectBomb.intersects(rectPlayer) && getGameWorld().player.isCheckNobeHurt()==false)
                            getGameWorld().player.setState(HURT);
                        //nổ balloom
                        object = getGameWorld().getCollisionWithRectangle(rectBomb);
                        if (object != null && object instanceof Balloom) {
                            object.setState(HURT);
                        }
                        //nổ oneal
                        if (object != null && object instanceof Oneal) {
                            object.setState(HURT);
                        }
                        //nổ doll
                        if (object != null && object instanceof Doll) {
                            object.setState(HURT);
                        }
                        // nổ kodonrian
                        if (object != null && object instanceof Kondoria) {
                            object.setState(HURT);
                        }
                        // nổ Minvo
                        if (object != null && object instanceof Minvo) {
                            object.setState(HURT);
                        }
                    }
                }
            }
            if (aniBombExploded.isLastFrame()) {
                getGameWorld().player.setCountBomb(getGameWorld().player.getCountBomb() - 1);
                this.setState(DEATH);
            }
        }

    }
}
