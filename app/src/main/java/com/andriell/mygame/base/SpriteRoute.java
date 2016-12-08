package com.andriell.mygame.base;

import android.graphics.Canvas;
import android.util.Log;

import com.andriell.mygame.base.InterfaceSprite;
import com.andriell.mygame.base.InterfaceSpritePositioned;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Андрей on 08.12.2016.
 */

public abstract class SpriteRoute extends SpriteResize implements InterfaceSpriteGroup {
    InterfaceSpritePositioned[] sprites;
    int[] timeShift;
    int addPosition = 0;
    float speed = 1F;
    long startTime = 0L;

    public SpriteRoute(int size) {
        sprites = new InterfaceSpritePositioned[size];
        timeShift = new int[size];
    }

    public void setAddPosition(int addPosition) {
        this.addPosition = addPosition;
    }

    public void addNext(InterfaceSpritePositioned sprite, int timeShift) {
        int i = addPosition;
        addPosition++;
        int j = addPosition;
        if (sprites == null || i < 0 || i > sprites.length || j < 0 || j > sprites.length) {
            return;
        }
        sprites[j] = sprite;
        this.timeShift[j] = this.timeShift[i] + timeShift;
    }


    public void add(int i, InterfaceSpritePositioned sprite, int timeShift) {
        if (sprites == null || i < 0 || i > sprites.length) {
            return;
        }
        sprites[i] = sprite;
        this.timeShift[i] = timeShift;
    }

    public abstract float[] newXY(float t);

    @Override
    public boolean onDraw(Canvas c) {
        if (sprites == null) {
            return false;
        }
        if (startTime == 0L) {
            startTime = System.currentTimeMillis();
        }
        boolean r = false;
        long time = System.currentTimeMillis() - startTime;
        float[] xy;
        for (int i = 0; i < sprites.length; i++) {
            if (sprites[i] == null) {
                continue;
            }
            xy = newXY((time + timeShift[i]) * speed);
            //Log.i("Route", Arrays.toString(xy));
            sprites[i].setX(xy[0]);
            sprites[i].setY(xy[1]);
            if (sprites[i].onDraw(c)) {
                r = true;
            }
        }
        return r;
    }

    @Override
    public InterfaceSpritePositioned[] getSprites() {
        return sprites;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
