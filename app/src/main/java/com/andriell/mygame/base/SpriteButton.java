package com.andriell.mygame.base;

import android.graphics.Bitmap;
import android.view.MotionEvent;

/**
 * Created by Андрей on 01.12.2016.
 */

public class SpriteButton extends SpriteBitmap implements InterfaceSpriteTouchListener {
    protected Bitmap bitmapNormal = null;
    protected Bitmap bitmapPressed = null;
    protected DownListener downListener;
    protected UpListener upListener;

    public SpriteButton() {
        this(null, null, 0F, 0F);
    }

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
        this.bitmap = bitmapNormal;
        this.x = x;
        this.y = y;
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
        } else if (e.getAction() == MotionEvent.ACTION_UP || e.getAction() == MotionEvent.ACTION_CANCEL) {
            if (upListener != null) {
                return upListener.onUp(e);
            }
            return true;
        }
        bitmap = bitmapNormal;

        return true;
    }

    public DownListener getDownListener() {
        return downListener;
    }

    public void setDownListener(DownListener downListener) {
        this.downListener = downListener;
    }

    public Bitmap getBitmapNormal() {
        return bitmapNormal;
    }

    public void setBitmapNormal(Bitmap bitmapNormal) {
        this.bitmapNormal = bitmapNormal;
    }

    public Bitmap getBitmapPressed() {
        return bitmapPressed;
    }

    public void setBitmapPressed(Bitmap bitmapPressed) {
        this.bitmapPressed = bitmapPressed;
    }

    public UpListener getUpListener() {
        return upListener;
    }

    public void setUpListener(UpListener upListener) {
        this.upListener = upListener;
    }

    public static interface DownListener {
        public boolean onDown(MotionEvent e);
    }

    public static interface UpListener {
        public boolean onUp(MotionEvent e);
    }
}
