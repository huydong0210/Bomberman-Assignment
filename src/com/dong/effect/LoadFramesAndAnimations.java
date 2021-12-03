package com.dong.effect;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Hashtable;

public class LoadFramesAndAnimations {
    public static Hashtable<String, FrameImage> frameImages=new Hashtable<String, FrameImage>();
    public static Hashtable<String, Animation> animations=new Hashtable<String,Animation>();
    public static String originPath="data\\sprites\\";
    public static Hashtable<String, FrameImage> getFrameImages() {
        return frameImages;
    }
    public static Hashtable<String, Animation> getAnimations() {
        return animations;
    }
    public static Animation animation;
    public static long timeToNextFrame=500*1000000;
    private LoadFramesAndAnimations(){

    }
    public static void load() {


        frameImages.put("balloom_dead", loadOneFrame("balloom_dead"));

        animation = new Animation();
        animation.add(frameImages.get("balloom_dead"), timeToNextFrame);
        animations.put("balloom_dead", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("balloom_left1", loadOneFrame("balloom_left1"));
        frameImages.put("balloom_left2", loadOneFrame("balloom_left2"));
        frameImages.put("balloom_left3", loadOneFrame("balloom_left3"));

        animation = new Animation();
        animation.add(frameImages.get("balloom_left1"), timeToNextFrame);
        animation.add(frameImages.get("balloom_left2"), timeToNextFrame);
        animation.add(frameImages.get("balloom_left3"), timeToNextFrame);
        animations.put("balloom_left", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("balloom_right1", loadOneFrame("balloom_right1"));
        frameImages.put("balloom_right2", loadOneFrame("balloom_right2"));
        frameImages.put("balloom_right3", loadOneFrame("balloom_right3"));

        animation = new Animation();
        animation.add(frameImages.get("balloom_right1"), timeToNextFrame);
        animation.add(frameImages.get("balloom_right2"), timeToNextFrame);
        animation.add(frameImages.get("balloom_right3"), timeToNextFrame);
        animations.put("balloom_right", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("bomb", loadOneFrame("bomb"));
        frameImages.put("bomb_1", loadOneFrame("bomb_1"));
        frameImages.put("bomb_2", loadOneFrame("bomb_2"));

        animation = new Animation();
        animation.add(frameImages.get("bomb"), timeToNextFrame);
        animation.add(frameImages.get("bomb_1"), timeToNextFrame);
        animation.add(frameImages.get("bomb_2"), timeToNextFrame);
        animations.put("bomb", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("bomb_exploded", loadOneFrame("bomb_exploded"));
        frameImages.put("bomb_exploded1", loadOneFrame("bomb_exploded1"));
        frameImages.put("bomb_exploded2", loadOneFrame("bomb_exploded2"));

        animation = new Animation();
        animation.add(frameImages.get("bomb_exploded"), 150 * 1000000);
        animation.add(frameImages.get("bomb_exploded1"), 150 * 1000000);
        animation.add(frameImages.get("bomb_exploded2"), 150 * 1000000);
        animations.put("bomb_exploded", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("brick", loadOneFrame("brick"));

        animation = new Animation();
        animation.add(frameImages.get("brick"), timeToNextFrame);
        animations.put("brick", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("brick_exploded", loadOneFrame("brick_exploded"));
        frameImages.put("brick_exploded1", loadOneFrame("brick_exploded1"));
        frameImages.put("brick_exploded2", loadOneFrame("brick_exploded2"));

        animation = new Animation();
        animation.add(frameImages.get("brick_exploded"), 100 * 1000000);
        animation.add(frameImages.get("brick_exploded1"), 100 * 1000000);
        animation.add(frameImages.get("brick_exploded2"), 100 * 1000000);
        animations.put("brick_exploded", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("doll_dead", loadOneFrame("doll_dead"));

        animation = new Animation();
        animation.add(frameImages.get("doll_dead"), timeToNextFrame);
        animations.put(AnimationName.doll_dead,animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("doll_left1", loadOneFrame("doll_left1"));
        frameImages.put("doll_left2", loadOneFrame("doll_left2"));
        frameImages.put("doll_left3", loadOneFrame("doll_left3"));

        animation = new Animation();
        animation.add(frameImages.get("doll_left1"), timeToNextFrame);
        animation.add(frameImages.get("doll_left2"), timeToNextFrame);
        animation.add(frameImages.get("doll_left3"), timeToNextFrame);
        animations.put("doll_left", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("doll_right1", loadOneFrame("doll_right1"));
        frameImages.put("doll_right2", loadOneFrame("doll_right2"));
        frameImages.put("doll_right3", loadOneFrame("doll_right3"));

        animation = new Animation();
        animation.add(frameImages.get("doll_right1"), timeToNextFrame);
        animation.add(frameImages.get("doll_right2"), timeToNextFrame);
        animation.add(frameImages.get("doll_right3"), timeToNextFrame);
        animations.put("doll_right", animation);
/////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("explosion_horizontal_left_last", loadOneFrame("explosion_horizontal_left_last"));
        frameImages.put("explosion_horizontal_left_last1", loadOneFrame("explosion_horizontal_left_last1"));
        frameImages.put("explosion_horizontal_left_last2", loadOneFrame("explosion_horizontal_left_last2"));

        animation = new Animation();
        animation.add(frameImages.get("explosion_horizontal_left_last"), timeToNextFrame);
        animation.add(frameImages.get("explosion_horizontal_left_last1"), timeToNextFrame);
        animation.add(frameImages.get("explosion_horizontal_left_last2"), timeToNextFrame);
        animations.put("explosion_horizontal_left_last", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("explosion_horizontal_right_last", loadOneFrame("explosion_horizontal_right_last"));
        frameImages.put("explosion_horizontal_right_last1", loadOneFrame("explosion_horizontal_right_last1"));
        frameImages.put("explosion_horizontal_right_last2", loadOneFrame("explosion_horizontal_right_last2"));

        animation = new Animation();
        animation.add(frameImages.get("explosion_horizontal_right_last"), timeToNextFrame);
        animation.add(frameImages.get("explosion_horizontal_right_last1"), timeToNextFrame);
        animation.add(frameImages.get("explosion_horizontal_right_last2"), timeToNextFrame);
        animations.put("explosion_horizontal_right_last", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("explosion_horizontal", loadOneFrame("explosion_horizontal"));
        frameImages.put("explosion_horizontal1", loadOneFrame("explosion_horizontal1"));
        frameImages.put("explosion_horizontal2", loadOneFrame("explosion_horizontal2"));

        animation = new Animation();
        animation.add(frameImages.get("explosion_horizontal"), timeToNextFrame);
        animation.add(frameImages.get("explosion_horizontal1"), timeToNextFrame);
        animation.add(frameImages.get("explosion_horizontal2"), timeToNextFrame);
        animations.put("explosion_horizontal", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("explosion_vertical_down_last", loadOneFrame("explosion_vertical_down_last"));
        frameImages.put("explosion_vertical_down_last1", loadOneFrame("explosion_vertical_down_last1"));
        frameImages.put("explosion_vertical_down_last2", loadOneFrame("explosion_vertical_down_last2"));

        animation = new Animation();
        animation.add(frameImages.get("explosion_vertical_down_last"), timeToNextFrame);
        animation.add(frameImages.get("explosion_vertical_down_last1"), timeToNextFrame);
        animation.add(frameImages.get("explosion_vertical_down_last2"), timeToNextFrame);
        animations.put("explosion_vertical_down_last", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("explosion_vertical_top_last", loadOneFrame("explosion_vertical_top_last"));
        frameImages.put("explosion_vertical_top_last1", loadOneFrame("explosion_vertical_top_last1"));
        frameImages.put("explosion_vertical_top_last2", loadOneFrame("explosion_vertical_top_last2"));

        animation = new Animation();
        animation.add(frameImages.get("explosion_vertical_top_last"), timeToNextFrame);
        animation.add(frameImages.get("explosion_vertical_top_last1"), timeToNextFrame);
        animation.add(frameImages.get("explosion_vertical_top_last2"), timeToNextFrame);
        animations.put("explosion_vertical_top_last", animation);

/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("explosion_vertical", loadOneFrame("explosion_vertical"));
        frameImages.put("explosion_vertical1", loadOneFrame("explosion_vertical1"));
        frameImages.put("explosion_vertical2", loadOneFrame("explosion_vertical2"));

        animation = new Animation();
        animation.add(frameImages.get("explosion_vertical"), timeToNextFrame);
        animation.add(frameImages.get("explosion_vertical1"), timeToNextFrame);
        animation.add(frameImages.get("explosion_vertical2"), timeToNextFrame);
        animations.put("explosion_vertical", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("grass", loadOneFrame("grass"));

        animation = new Animation();
        animation.add(frameImages.get("grass"), timeToNextFrame);
        animations.put("grass", animation);

/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("kondoria_dead", loadOneFrame("kondoria_dead"));

        animation = new Animation();
        animation.add(frameImages.get("kondoria_dead"), timeToNextFrame);
        animations.put("kondoria_dead", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("kondoria_left1", loadOneFrame("kondoria_left1"));
        frameImages.put("kondoria_left2", loadOneFrame("kondoria_left2"));
        frameImages.put("kondoria_left3", loadOneFrame("kondoria_left3"));

        animation = new Animation();
        animation.add(frameImages.get("kondoria_left1"), timeToNextFrame);
        animation.add(frameImages.get("kondoria_left2"), timeToNextFrame);
        animation.add(frameImages.get("kondoria_left3"), timeToNextFrame);
        animations.put("kondoria_left", animation);

/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("kondoria_right1", loadOneFrame("kondoria_right1"));
        frameImages.put("kondoria_right2", loadOneFrame("kondoria_right2"));
        frameImages.put("kondoria_right3", loadOneFrame("kondoria_right3"));

        animation = new Animation();
        animation.add(frameImages.get("kondoria_right1"), timeToNextFrame);
        animation.add(frameImages.get("kondoria_right2"), timeToNextFrame);
        animation.add(frameImages.get("kondoria_right3"), timeToNextFrame);
        animations.put("kondoria_right", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("minvo_dead", loadOneFrame("minvo_dead"));

        animation = new Animation();
        animation.add(frameImages.get("minvo_dead"), timeToNextFrame);
        animations.put("minvo_dead", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("minvo_left1", loadOneFrame("minvo_left1"));
        frameImages.put("minvo_left2", loadOneFrame("minvo_left2"));
        frameImages.put("minvo_left3", loadOneFrame("minvo_left3"));

        animation = new Animation();
        animation.add(frameImages.get("minvo_left1"), timeToNextFrame);
        animation.add(frameImages.get("minvo_left2"), timeToNextFrame);
        animation.add(frameImages.get("minvo_left3"), timeToNextFrame);
        animations.put("minvo_left", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("minvo_right1", loadOneFrame("minvo_right1"));
        frameImages.put("minvo_right2", loadOneFrame("minvo_right2"));
        frameImages.put("minvo_right3", loadOneFrame("minvo_right3"));

        animation = new Animation();
        animation.add(frameImages.get("minvo_right1"), timeToNextFrame);
        animation.add(frameImages.get("minvo_right2"), timeToNextFrame);
        animation.add(frameImages.get("minvo_right3"), timeToNextFrame);
        animations.put("minvo_right", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("mob_dead1", loadOneFrame("mob_dead1"));
        frameImages.put("mob_dead2", loadOneFrame("mob_dead2"));
        frameImages.put("mob_dead3", loadOneFrame("mob_dead3"));

        animation = new Animation();
        animation.add(frameImages.get("mob_dead1"), timeToNextFrame);
        animation.add(frameImages.get("mob_dead2"), timeToNextFrame);
        animation.add(frameImages.get("mob_dead3"), timeToNextFrame);
        animations.put("mob_dead", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("oneal_dead", loadOneFrame("oneal_dead"));

        animation = new Animation();
        animation.add(frameImages.get("oneal_dead"), timeToNextFrame);
        animations.put("oneal_dead", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("oneal_left1", loadOneFrame("oneal_left1"));
        frameImages.put("oneal_left2", loadOneFrame("oneal_left2"));
        frameImages.put("oneal_left3", loadOneFrame("oneal_left3"));

        animation = new Animation();
        animation.add(frameImages.get("oneal_left1"), timeToNextFrame);
        animation.add(frameImages.get("oneal_left2"), timeToNextFrame);
        animation.add(frameImages.get("oneal_left3"), timeToNextFrame);
        animations.put("oneal_left", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("oneal_right1", loadOneFrame("oneal_right1"));
        frameImages.put("oneal_right2", loadOneFrame("oneal_right2"));
        frameImages.put("oneal_right3", loadOneFrame("oneal_right3"));

        animation = new Animation();
        animation.add(frameImages.get("oneal_right1"), timeToNextFrame);
        animation.add(frameImages.get("oneal_right2"), timeToNextFrame);
        animation.add(frameImages.get("oneal_right3"), timeToNextFrame);
        animations.put("oneal_right", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("player_dead1", loadOneFrame("player_dead1"));
        frameImages.put("player_dead2", loadOneFrame("player_dead2"));
        frameImages.put("player_dead3", loadOneFrame("player_dead3"));

        animation = new Animation();
        animation.add(frameImages.get("player_dead1"), 200 * 1000000);
        animation.add(frameImages.get("player_dead2"), 200 * 1000000);
        animation.add(frameImages.get("player_dead3"), 200 * 1000000);
        animations.put("player_dead", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("player_down", loadOneFrame("player_down"));
        frameImages.put("player_down_1", loadOneFrame("player_down_1"));
        frameImages.put("player_down_2", loadOneFrame("player_down_2"));

        animation = new Animation();
        animation.add(frameImages.get("player_down"), timeToNextFrame);
        animation.add(frameImages.get("player_down_1"), timeToNextFrame);
        animation.add(frameImages.get("player_down_2"), timeToNextFrame);
        animations.put("player_down", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("player_left", loadOneFrame("player_left"));
        frameImages.put("player_left_1", loadOneFrame("player_left_1"));
        frameImages.put("player_left_2", loadOneFrame("player_left_2"));

        animation = new Animation();
        animation.add(frameImages.get("player_left"), timeToNextFrame);
        animation.add(frameImages.get("player_left_1"), timeToNextFrame);
        animation.add(frameImages.get("player_left_2"), timeToNextFrame);
        animations.put("player_left", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("player_right", loadOneFrame("player_right"));
        frameImages.put("player_right_1", loadOneFrame("player_right_1"));
        frameImages.put("player_right_2", loadOneFrame("player_right_2"));

        animation = new Animation();
        animation.add(frameImages.get("player_right"), timeToNextFrame);
        animation.add(frameImages.get("player_right_1"), timeToNextFrame);
        animation.add(frameImages.get("player_right_2"), timeToNextFrame);
        animations.put("player_right", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("player_up", loadOneFrame("player_up"));
        frameImages.put("player_up_1", loadOneFrame("player_up_1"));
        frameImages.put("player_up_2", loadOneFrame("player_up_2"));

        animation = new Animation();
        animation.add(frameImages.get("player_up"), timeToNextFrame);
        animation.add(frameImages.get("player_up_1"), timeToNextFrame);
        animation.add(frameImages.get("player_up_2"), timeToNextFrame);
        animations.put("player_up", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("portal", loadOneFrame("portal"));

        animation = new Animation();
        animation.add(frameImages.get("portal"), timeToNextFrame);
        animations.put("portal", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("powerup_bombpass", loadOneFrame("powerup_bombpass"));

        animation = new Animation();
        animation.add(frameImages.get("powerup_bombpass"), timeToNextFrame);
        animations.put("powerup_bombpass", animation);
////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("powerup_bombs", loadOneFrame("powerup_bombs"));

        animation = new Animation();
        animation.add(frameImages.get("powerup_bombs"), timeToNextFrame);
        animations.put("powerup_bombs", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("powerup_detonator", loadOneFrame("powerup_detonator"));

        animation = new Animation();
        animation.add(frameImages.get("powerup_detonator"), timeToNextFrame);
        animations.put("powerup_detonator", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("powerup_flamepass", loadOneFrame("powerup_flamepass"));

        animation = new Animation();
        animation.add(frameImages.get("powerup_flamepass"), timeToNextFrame);
        animations.put("powerup_flamepass", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("powerup_flames", loadOneFrame("powerup_flames"));

        animation = new Animation();
        animation.add(frameImages.get("powerup_flames"), timeToNextFrame);
        animations.put("powerup_flames", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("powerup_speed", loadOneFrame("powerup_speed"));

        animation = new Animation();
        animation.add(frameImages.get("powerup_speed"), timeToNextFrame);
        animations.put("powerup_speed", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("powerup_wallpass", loadOneFrame("powerup_wallpass"));

        animation = new Animation();
        animation.add(frameImages.get("powerup_wallpass"), timeToNextFrame);
        animations.put("powerup_wallpass", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("wall", loadOneFrame("wall"));

        animation = new Animation();
        animation.add(frameImages.get("wall"), timeToNextFrame);
        animations.put("wall", animation);
/////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 1; i <= 26; i++) {
            String name = i + "";
            frameImages.put(name, loadOneFrameBackGround(name));
        }

        animation = new Animation();
        for (int i = 1; i <= 26; i++) {
            String name = i + "";
            animation.add(frameImages.get(name), 900 * 1000000);
        }
        animations.put("cloud", animation);
        /////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("heart",loadOneFrameBackGround("heart"));

        animation=new Animation();
//        animation.add(frameImages.get("heart"),1000*1000000);
//        animations.put("heart",animation);
        /////////////////////////////////////////////////////////////////////////////////////////
        frameImages.put("gameover",loadOneFrameBackGround("gameover"));
        frameImages.put("nextlevel",loadOneFrameBackGround("nextlevel"));
        frameImages.put("nobehurt",loadOneFrame("nobehurt"));

    }

    public static FrameImage loadOneFrame(String name)
    {
        String nameInPath=name+".png";
        String path=originPath+nameInPath;
        FrameImage frameImage=new FrameImage();
        try
        {
            frameImage=new FrameImage(name,ImageIO.read(new File(path)));
        }
        catch (Exception e)
        {
            System.out.println("Không tìm thấy đường dẫn "+ name);
        }
        return frameImage;
    }
    public static FrameImage loadOneFrameBackGround(String name)
    {
        String nameInPath=name+".png";
        String path="data\\background\\"+nameInPath;
        FrameImage frameImage=new FrameImage();
        try
        {
            frameImage=new FrameImage(name,ImageIO.read(new File(path)));
        }
        catch (Exception e)
        {
            System.out.println("Không tìm thấy đường dẫn "+ name);
        }
        return frameImage;
    }
}
