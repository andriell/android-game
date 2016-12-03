package com.andriell.mygame.base;

import android.graphics.Bitmap;
import android.view.MotionEvent;

/**
 * Created by Андрей on 03.12.2016.
 */

public class SpriteButtonClear extends SpriteResize implements InterfaceSpriteTouchListener {
    protected InterfaceSpriteButtonDownListener downListener;
    protected InterfaceSpriteButtonUpListener upListener;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            if (downListener != null) {
                return downListener.onDown(e);
            }
            return true;
        } else if (e.getAction() == MotionEvent.ACTION_UP || e.getAction() == MotionEvent.ACTION_CANCEL) {
            if (upListener != null) {
                return upListener.onUp(e);
            }
            return true;
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
