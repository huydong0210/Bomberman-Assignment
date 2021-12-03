package com.dong.effect;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private String name;

    private boolean isRepeated; // false thì hình sẽ không lặp lại, true thì hình sẽ chạy;

    private ArrayList<FrameImage> frameImages;

    private int currentFrame; // frame đang được vẽ

    private ArrayList<Boolean> ignoreFrames; // xet xem có phải frame bị bỏ hay không

    private ArrayList<Double> delayFrames;// đỗ trễ giữa các frame

    private long beginTime;

    private boolean drawRectFrame; // vẽ khung

    public Animation() {

        delayFrames = new ArrayList<Double>();
        beginTime = 0;
        currentFrame = 0;
        ignoreFrames = new ArrayList<Boolean>();
        frameImages = new ArrayList<FrameImage>();
        drawRectFrame = false;
        isRepeated = true;
    }

    public Animation(Animation animation) {
        beginTime = animation.beginTime;
        currentFrame = animation.currentFrame;
        drawRectFrame = animation.drawRectFrame;
        isRepeated = animation.isRepeated;

        delayFrames = new ArrayList<Double>();
        for (Double i : animation.delayFrames) {
            delayFrames.add(i);
        }
        ignoreFrames = new ArrayList<Boolean>();
        for (boolean d : animation.ignoreFrames) {
            ignoreFrames.add(d);
        }
        frameImages = new ArrayList<FrameImage>();
        for (FrameImage i : animation.frameImages) {
            frameImages.add(new FrameImage(i));
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public void setFrameImages(ArrayList<FrameImage> frameImages) {
        this.frameImages = frameImages;
    }

    public void setCurrentFrame(int currentFrame) {
        if (currentFrame >= 0 && currentFrame < frameImages.size())
            this.currentFrame = currentFrame;
        else
            this.currentFrame = 0;
    }

    public void setIgnoreFrames(ArrayList<Boolean> ignoreFrames) {
        this.ignoreFrames = ignoreFrames;
    }

    public void setDelayFrames(ArrayList<Double> delayFrames) {
        this.delayFrames = delayFrames;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public void setDrawRectFrame(boolean drawRectFrame) {
        this.drawRectFrame = drawRectFrame;
    }

    public String getName() {
        return name;
    }

    public boolean getRepeated() {
        return isRepeated;
    }

    public ArrayList<FrameImage> getFrameImages() {
        return frameImages;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public ArrayList<Boolean> getIgnoreFrames() {
        return ignoreFrames;
    }

    public ArrayList<Double> getDelayFrames() {
        return delayFrames;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public boolean getDrawRectFrame() {
        return drawRectFrame;
    }

    public boolean getIgnoreFrame(int id) {
        return ignoreFrames.get(id);
    }

    public void setIgnoreFrame(int id) {
        if (id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, true);
    }

    public void unIgnoreFrame(int id) {
        if (id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, false);
    }

    public void reset() {
        currentFrame = 0;
        beginTime = 0;
        for (int i=0;i<ignoreFrames.size();i++)
            ignoreFrames.set(i,false);
    }
    public void add(FrameImage frameImage, double timeToNextFrame)
    {
        ignoreFrames.add(false);
        frameImages.add(frameImage);
        delayFrames.add(new Double(timeToNextFrame));
    }
    public BufferedImage getCurrentImage(){
        return frameImages.get(currentFrame).getImage();
    }
    public void update(Long currentTime){
        if (beginTime==0) beginTime=currentTime;
        else
        {
            if (currentTime-beginTime>delayFrames.get(currentFrame)){
                nextFrame();
                beginTime=currentTime;
            }
        }
    }

    private void nextFrame() {
        if (currentFrame>=frameImages.size()-1){
            if (isRepeated) currentFrame=0;
        }
        else
            currentFrame++;
        if (ignoreFrames.get(currentFrame)) nextFrame();
    }
    public boolean isLastFrame(){
        if (currentFrame==frameImages.size()-1)
            return true;
        else
            return false;
    }
    public void flipAllImage(){
        for (int i=0;i<frameImages.size();i++)
        {
            BufferedImage image=frameImages.get(i).getImage();
            AffineTransform tx=AffineTransform.getScaleInstance(-1,1);
            tx.translate(-image.getWidth(),0);

            AffineTransformOp op=new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
            image=op.filter(image,null);
            frameImages.get(i).setImage(image);
        }
    }
    public void draw(int x, int y, Graphics2D g2){
        BufferedImage image=getCurrentImage();
        g2.drawImage(image,x-image.getWidth()/2,y-image.getHeight()/2,null);
        if (drawRectFrame)
            g2.drawRect(x-image.getWidth()/2,x-image.getHeight()/2,image.getWidth(),image.getHeight());
    }
}
