package com.andriell.mygame.base;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Андрей on 03.12.2016.
 */

public class SpriteButtonClear extends SpriteResize implements InterfaceSpriteTouchListener {
    protected InterfaceSpriteButtonDownListener downListener;
    protected InterfaceSpriteButtonUpListener upListener;

    public SpriteButtonClear() {}

    public SpriteButtonClear(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public SpriteButtonClear(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            return downListener == null || downListener.onDown(e);
        } else if (e.getAction() == MotionEvent.ACTION_UP || e.getAction() == MotionEvent.ACTION_CANCEL) {
            return upListener == null || upListener.onUp(e);
        }
        return true;
    }

    public InterfaceSpriteButtonDownListener getDownListener() {
        return downListener;
    }

    public void setDownListener(InterfaceSpriteButtonDownListener downListener) {
        this.downListener = downListener;
    }

    public InterfaceSpriteButtonUpListener getUpListener() {
        return upListener;
    }

    public void setUpListener(InterfaceSpriteButtonUpListener upListener) {
        this.upListener = upListener;
    }
}
