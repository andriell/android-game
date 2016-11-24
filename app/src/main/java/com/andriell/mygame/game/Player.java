package com.andriell.mygame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.andriell.mygame.base.SpriteBitmap;
import com.andriell.mygame.base.SpriteRunner;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class Player extends SpriteBitmap {
    private int centerX;
    private int centerY;


    public Player(Bitmap bitmap, int x, int y) {
        super(bitmap, x, y);
        centerX = x + bitmap.getWidth() / 2;
        centerY = y + bitmap.getHeight() / 2;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }
}