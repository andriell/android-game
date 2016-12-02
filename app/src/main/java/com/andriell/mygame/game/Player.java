package com.andriell.mygame.game;

import android.graphics.Bitmap;

import com.andriell.mygame.base.SpriteBitmap;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class Player extends SpriteBitmap {
    private float centerX;
    private float centerY;


    public Player(Bitmap bitmap, float x, float y) {
        super(bitmap, x, y);
        centerX = x + bitmap.getWidth() / 2;
        centerY = y + bitmap.getHeight() / 2;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }
}