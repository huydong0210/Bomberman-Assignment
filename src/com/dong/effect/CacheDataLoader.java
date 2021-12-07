package com.dong.effect;

import sun.applet.AppletAudioClip;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.URL;
import java.util.Hashtable;

public class CacheDataLoader {
    private static CacheDataLoader instance;

    private Hashtable<String, FrameImage> frameImages = new Hashtable<String, FrameImage>();
    private Hashtable<String, Animation> animations = new Hashtable<String, Animation>();
    private Hashtable<String, AudioInputStream> sounds=new Hashtable<String, AudioInputStream>();
    private int level=1;


    String physicalMap[][];


    private CacheDataLoader() {

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void loadData() {
        loadFrame();
        loadAnimation();
        loadPhysicalMap(level);
    }
    public void loadLevel(){
        level++;
        loadPhysicalMap(level);
    }
    public Clip getAudio(String name){
        Clip clip=null;
        File file=new File("data\\sounds\\"+name+".wav");
        try {
            AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(file);
            clip=AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (Exception e)
        {

        }
        return clip;
    }
    public static CacheDataLoader getInstance() {
        if (instance == null)
            instance = new CacheDataLoader();
        return instance;
    }

    public void loadFrame() {
        frameImages = LoadFramesAndAnimations.frameImages;
    }

    public void loadAnimation() {
        animations = LoadFramesAndAnimations.animations;
    }

    public String[][] loadPhysicalMap(int level) {
        String path = "data\\levels\\Level" + level + ".txt";
        System.out.println(path);
        try {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line1 = br.readLine();
            String a[] = line1.split(" ");

            int row=Integer.parseInt(a[1]);
            int collum=Integer.parseInt(a[2]);
            physicalMap=new String[row][collum];
            for (int i=0;i<row;i++) {
                String line= br.readLine();
                String element[]=line.split("");
                for (int j=0;j<collum;j++)
                {
                    physicalMap[i][j]=element[j]+"";
                }
            }
            br.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
            System.out.println("Không đọc được level" + level);
        }

        return physicalMap;
    }

    public FrameImage getFrameImage(String name) {
        FrameImage frameImage = new FrameImage(frameImages.get(name));
        return frameImage;
    }

    public Animation getAnimation(String name) {
        Animation animation = new Animation(animations.get(name));
        return animation;
    }

    public String[][] getPhysicalMap() {
        return physicalMap;
    }
}
