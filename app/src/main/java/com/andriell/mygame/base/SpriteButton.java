package com.andriell.mygame.base;

import android.graphics.Bitmap;
import android.view.MotionEvent;

/**
 * Created by Андрей on 01.12.2016.
 */

public abstract class SpriteButton extends SpriteBitmap implements InterfaceSpriteTouchListener {
    Bitmap bitmapNormal = null;
    Bitmap bitmapPressed = null;

    public SpriteButton(Bitmap bitmap, int x, int y) {
        super(bitmap, x, y);
        bitmapNormal = bitmap;
    }

    public SpriteButton(Bitmap bitmapNormal, Bitmap bitmapPressed, int x, int y) {
        super(bitmapNormal, x, y);
        this.bitmapNormal = bitmapNormal;
        this.bitmapPressed = bitmapPressed;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            bitmap = bitmapPressed == null ? bitmapNormal : bitmapPressed;
            return onDown(e);
        } else {
            bitmap = bitmapNormal;
        }
        return true;
    }

    public abstract boolean onDown(MotionEvent e);
}
