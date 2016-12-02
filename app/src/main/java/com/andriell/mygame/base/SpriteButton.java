package com.andriell.mygame.base;

import android.graphics.Bitmap;
import android.view.MotionEvent;

/**
 * Created by Андрей on 01.12.2016.
 */

public class SpriteButton extends SpriteBitmap implements InterfaceSpriteTouchListener {
    Bitmap bitmapNormal = null;
    Bitmap bitmapPressed = null;
    DownListener downListener;

    public SpriteButton(Bitmap bitmapNormal) {
        this(bitmapNormal, null, 0F, 0F);
    }

    public SpriteButton(Bitmap bitmapNormal, float x, float y) {
        this(bitmapNormal, null, x, y);
    }

    public SpriteButton(Bitmap bitmapNormal, Bitmap bitmapPressed) {
        this(bitmapNormal, bitmapPressed, 0F, 0F);
    }

    public SpriteButton(Bitmap bitmapNormal, Bitmap bitmapPressed, float x, float y) {
        super(bitmapNormal, x, y);
        this.bitmapNormal = bitmapNormal;
        this.bitmapPressed = bitmapPressed;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            bitmap = bitmapPressed == null ? bitmapNormal : bitmapPressed;
            if (downListener != null) {
                return downListener.onDown(e);
            }
            return true;
        } else {
            bitmap = bitmapNormal;
        }
        return true;
    }

    public DownListener getDownListener() {
        return downListener;
    }

    public void setDownListener(DownListener downListener) {
        this.downListener = downListener;
    }

    public static interface DownListener {
        public boolean onDown(MotionEvent e);
    }
}
