package com.dong.gameobjects;

import com.dong.effect.Animation;
import com.dong.effect.CacheDataLoader;
import com.dong.effect.FrameImage;
import com.dong.gameobjects.particularobject.Bomb;
import com.dong.gameobjects.particularobject.Brick;
import com.dong.gameobjects.particularobject.Item.*;
import com.dong.gameobjects.particularobject.ParticularObject;
import com.dong.gameobjects.particularobject.characters.Player;
import com.dong.gameobjects.particularobject.characters.enemy.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PhysicalMap extends GameObject {
    public String[][] physMap;
    private int tileSize = ParticularObject.size;

    public PhysicalMap(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.physMap = CacheDataLoader.getInstance().getPhysicalMap();
        for (int i = 0; i < physMap.length; i++)
            for (int j = 0; j < physMap[0].length; j++) {
                if (physMap[i][j].equals("p")) {
                    Player player = new Player(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    gameWorld.addObject(player);
                    gameWorld.setPlayer(player);
                }
                if (physMap[i][j].equals("1")) {
                    Balloom balloom = new Balloom(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    balloom.setState(ParticularObject.ALIVE);
                    getGameWorld().addObject(balloom);
                }
                if (physMap[i][j].equals("s")) {
                    PowerUpSpeed powerUpSpeed = new PowerUpSpeed(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    getGameWorld().addObject(powerUpSpeed);
                }
                if (physMap[i][j].equals("b")) {
                    BomItem bomItem = new BomItem(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    getGameWorld().addObject(bomItem);
                }
                if (physMap[i][j].equals("2")) {
                    Oneal oneal = new Oneal(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    getGameWorld().addObject(oneal);
                }
                if (physMap[i][j].equals("h")) {
                    Heart heart = new Heart(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    getGameWorld().addObject(heart);
                }
                if (physMap[i][j].equals("3")) {
                    Doll doll = new Doll(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    getGameWorld().addObject(doll);
                }
                if (physMap[i][j].equals("x")) {
                    Portal portal = new Portal(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    getGameWorld().addObject(portal);
                }

                if (physMap[i][j].equals("4")) {
                    Kondoria kondoria = new Kondoria(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    getGameWorld().addObject(kondoria);
                }
                if (physMap[i][j].equals("5")) {
                    Minvo minvo = new Minvo(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    getGameWorld().addObject(minvo);
                }
                if (physMap[i][j].equals("q")) {
                    BombPass bombPass = new BombPass(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    getGameWorld().addObject(bombPass);
                }
                if (physMap[i][j].equals("f")) {
                    BrickPass brickPass = new BrickPass(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, tileSize, tileSize, getGameWorld());
                    getGameWorld().addObject(brickPass);
                }

            }

    }

    public void draw(Graphics2D g2) {

        BufferedImage grass = CacheDataLoader.getInstance().getFrameImage("grass").getImage();
        for (int i = 0; i < physMap.length; i++)
            for (int j = 0; j < physMap[0].length; j++) {
                if (physMap[i][j].equals("#") == false && physMap[i][j].equals("*") == false
                        && physMap[i][j].equals("s") == false && physMap[i][j].equals("b") == false
                        && physMap[i][j].equals("h") == false
                        && physMap[i][j].equals("q") == false && physMap[i][j].equals("f") == false)
                    g2.drawImage(grass, (int) getPosX() + j * tileSize, (int) getPosY() + i * tileSize, null);
            }
        FrameImage wall = CacheDataLoader.getInstance().getFrameImage("wall");
        BufferedImage wallImage = wall.getImage();
        FrameImage brick = CacheDataLoader.getInstance().getFrameImage("brick");
        BufferedImage brickImage = brick.getImage();
        for (int i = 0; i < physMap.length; i++)
            for (int j = 0; j < physMap[0].length; j++) {
                if (physMap[i][j].equals("#")) {
                    g2.drawImage(wallImage, (int) getPosX() + j * tileSize, (int) getPosY() + i * tileSize, null);
                }
                if (physMap[i][j].equals("*") || physMap[i][j].equals("s") || physMap[i][j].equals("b")
                        || physMap[i][j].equals("h") || physMap[i][j].equals("x")
                        || physMap[i][j].equals("q")  || physMap[i][j].equals("f")) {
                    brick.draw(g2, (int) (getPosX() + j * tileSize + ParticularObject.size / 2), (int) getPosY() + i * tileSize + ParticularObject.size / 2);
                }
                if (physMap[i][j].equals("0")) {
                    Brick brick_ = new Brick(getPosX() + j * tileSize + ParticularObject.size / 2, getPosY() + i * tileSize + ParticularObject.size / 2, getGameWorld());
                    brick_.setState(ParticularObject.HURT);
                    getGameWorld().addObject(brick_);
                }

            }

    }

    @Override
    public void update() {

    }

    public Rectangle haveCollisionWithBot(Rectangle rectangle) {
        int posX1 = rectangle.x / tileSize;
        posX1 -= 2;
        int posX2 = (rectangle.x + rectangle.width) / tileSize;
        posX2 += 2;
        int posY1 = (rectangle.y + rectangle.height) / tileSize;
        posY1 -= 2;
        if (posY1 < 0) posY1 = 0;
        int posY2 = posY1 + 4;
        if (posY2 >= physMap.length)
            posY2 = physMap.length - 1;
        if (posX1 < 0) posX1 = 0;
        if (posX2 >= physMap[0].length)
            posX2 = physMap[0].length - 1;
        for (int y = posY1; y <= posY2; y++)
            for (int x = posX1; x <= posX2; x++) {
                if (physMap[y][x].equals("#") || physMap[y][x].equals("*")
                        || physMap[y][x].equals(".") || physMap[y][x].equals("s")
                        || physMap[y][x].equals("b") || physMap[y][x].equals("h")
                        || physMap[y][x].equals("x")|| physMap[y][x].equals("q")
                        || physMap[y][x].equals("f")) {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rectangle.intersects(r))// ham xu ly va cham
                    {
                        return r;
                    }
                }
            }
        return null;
    }

    public Rectangle haveCollisionWithTop(Rectangle rect) {
        int posX1 = rect.x / tileSize - 3;
        if (posX1 < 0) posX1 = 0;
        int posX2 = (rect.x + rect.width) / tileSize + 3;
        if (posX2 > physMap[0].length - 1)
            posX2 = physMap[0].length - 1;
        int posY1 = rect.y / tileSize - 3;
        if (posY1 < 0) posY1 = 0;
        int posY2 = posY1 + 5;
        if (posY2 > physMap.length - 1)
            posY2 = physMap.length - 1;
        for (int y = posY1; y <= posY2; y++)
            for (int x = posX1; x <= posX2; x++)
                if (physMap[y][x].equals("#") || physMap[y][x].equals("*") || physMap[y][x].equals(".") || physMap[y][x].equals("s")
                        || physMap[y][x].equals("b") || physMap[y][x].equals("h")
                        || physMap[y][x].equals("x") || physMap[y][x].equals("q") || physMap[y][x].equals("f")) {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r))// ham xu ly va cham
                    {
                        return r;
                    }
                }
        return null;
    }

    public Rectangle haveCollisionWithRight(Rectangle rect) {
        int posY1 = rect.y / tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / tileSize;
        posY2 += 2;

        int posX1 = (rect.x + rect.width) / tileSize - 2;
        int posX2 = posX1 + 4;
        if (posX2 >= physMap[0].length)
            posX2 = physMap[0].length - 1;
        if (posX1 < 0)
            posX1 = 0;
        if (posY1 < 0)
            posY1 = 0;
        if (posY2 >= physMap.length) posY2 = physMap.length - 1;
        for (int x = posX1; x <= posX2; x++)
            for (int y = posY1; y <= posY2; y++) {
                if (physMap[y][x].equals("#") || physMap[y][x].equals("*") || physMap[y][x].equals(".") || physMap[y][x].equals("s")
                        || physMap[y][x].equals("b") || physMap[y][x].equals("h") || physMap[y][x].equals("x") || physMap[y][x].equals("q") || physMap[y][x].equals("f")) {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r))
                        return r;
                }
            }
        return null;
    }

    public Rectangle haveCollisionWithLeft(Rectangle rect) {
        int posY1 = rect.y / tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / tileSize;
        posY2 += 2;
        int posX1 = (rect.x + rect.width) / tileSize;
        posX1 += 2;
        if (posX1 >= physMap[0].length)
            posX1 = physMap[0].length - 1;
        int posX2 = posX1 - 3;
        if (posX2 < 0) posX2 = 0;
        if (posY1 < 0) posY1 = 0;
        if (posY2 >= physMap.length) posY2 = physMap.length - 1;

        for (int x = posX1; x >= posX2; x--)
            for (int y = posY1; y <= posY2; y++)
                if (physMap[y][x].equals("#") || physMap[y][x].equals("*") || physMap[y][x].equals(".") || physMap[y][x].equals("s")
                        || physMap[y][x].equals("b") || physMap[y][x].equals("h") || physMap[y][x].equals("x")
                        || physMap[y][x].equals("q") || physMap[y][x].equals("f")) {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r)) {
                        return r;
                    }
                }
        return null;
    }






    public Rectangle haveCollisionWithBotBrick(Rectangle rectangle) {
        int posX1 = rectangle.x / tileSize;
        posX1 -= 2;
        int posX2 = (rectangle.x + rectangle.width) / tileSize;
        posX2 += 2;
        int posY1 = (rectangle.y + rectangle.height) / tileSize;
        posY1 -= 2;
        if (posY1 < 0) posY1 = 0;
        int posY2 = posY1 + 4;
        if (posY2 >= physMap.length)
            posY2 = physMap.length - 1;
        if (posX1 < 0) posX1 = 0;
        if (posX2 >= physMap[0].length)
            posX2 = physMap[0].length - 1;
        for (int y = posY1; y <= posY2; y++)
            for (int x = posX1; x <= posX2; x++) {
                if (physMap[y][x].equals("#") ) {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rectangle.intersects(r))// ham xu ly va cham
                    {
                        return r;
                    }
                }
            }
        return null;
    }

    public Rectangle haveCollisionWithTopBrick(Rectangle rect) {
        int posX1 = rect.x / tileSize - 3;
        if (posX1 < 0) posX1 = 0;
        int posX2 = (rect.x + rect.width) / tileSize + 3;
        if (posX2 > physMap[0].length - 1)
            posX2 = physMap[0].length - 1;
        int posY1 = rect.y / tileSize - 3;
        if (posY1 < 0) posY1 = 0;
        int posY2 = posY1 + 5;
        if (posY2 > physMap.length - 1)
            posY2 = physMap.length - 1;
        for (int y = posY1; y <= posY2; y++)
            for (int x = posX1; x <= posX2; x++)
                if (physMap[y][x].equals("#") ) {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r))// ham xu ly va cham
                    {
                        return r;
                    }
                }
        return null;
    }

    public Rectangle haveCollisionWithRightBrick(Rectangle rect) {
        int posY1 = rect.y / tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / tileSize;
        posY2 += 2;

        int posX1 = (rect.x + rect.width) / tileSize - 2;
        int posX2 = posX1 + 4;
        if (posX2 >= physMap[0].length)
            posX2 = physMap[0].length - 1;
        if (posX1 < 0)
            posX1 = 0;
        if (posY1 < 0)
            posY1 = 0;
        if (posY2 >= physMap.length) posY2 = physMap.length - 1;
        for (int x = posX1; x <= posX2; x++)
            for (int y = posY1; y <= posY2; y++) {
                if (physMap[y][x].equals("#") ) {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r))
                        return r;
                }
            }
        return null;
    }

    public Rectangle haveCollisionWithLeftBrick(Rectangle rect) {
        int posY1 = rect.y / tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / tileSize;
        posY2 += 2;
        int posX1 = (rect.x + rect.width) / tileSize;
        posX1 += 2;
        if (posX1 >= physMap[0].length)
            posX1 = physMap[0].length - 1;
        int posX2 = posX1 - 3;
        if (posX2 < 0) posX2 = 0;
        if (posY1 < 0) posY1 = 0;
        if (posY2 >= physMap.length) posY2 = physMap.length - 1;

        for (int x = posX1; x >= posX2; x--)
            for (int y = posY1; y <= posY2; y++)
                if (physMap[y][x].equals("#") ) {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r)) {
                        return r;
                    }
                }
        return null;
    }





}
