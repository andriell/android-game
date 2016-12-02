package com.andriell.mygame.base;

import android.graphics.Bitmap;

/**
 * Created by Андрей on 02.12.2016.
 */

public class Animation {
    protected Bitmap[] bitMap;
    protected int[] timeMap;
    protected long startTime = 0L;
    protected float speed = 1F;

    public Animation(Bitmap bitMap) {
        this.bitMap = new Bitmap[1];
        this.bitMap[0] = bitMap;
    }

    public Animation(Bitmap[] bitMap, int[] timeMap) {
        this(bitMap, timeMap, 1F);
    }

    public Animation(Bitmap[] bitMap, int[] timeMap, float speed) {
        if (bitMap == null || bitMap.length == 0) {
            return;
        }
        if (timeMap == null || timeMap.length == 0) {
            this.bitMap = new Bitmap[1];
            this.bitMap[0] = bitMap[0];
            return;
        }
        if (bitMap.length != timeMap.length) {
            this.bitMap = new Bitmap[1];
            this.bitMap[0] = bitMap[0];
            return;
        }
        this.bitMap = bitMap;
        this.timeMap = timeMap;
        this.speed = speed;
    }

    public Bitmap getBitmap() {
        if (bitMap.length == 1) {
            return bitMap[0];
        }
        if (startTime == 0L) {
            startTime = System.currentTimeMillis();
            return bitMap[0];
        }
        long t = System.currentTimeMillis() - startTime;
        for (int i = 0; i < bitMap.length; i++) {
            t -= timeMap[i] * speed;
            if (t <= 0) {
                return bitMap[i];
            }
        }
        startTime = System.currentTimeMillis();
        return bitMap[0];
    }

    public float getWidth() {
        return bitMap[0].getWidth();
    }

    public float getHeight() {
        return bitMap[0].getHeight();
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
