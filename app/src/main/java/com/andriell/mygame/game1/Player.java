package com.andriell.mygame.game1;

import android.graphics.Bitmap;

import com.andriell.mygame.base.SpriteBitmap;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class Player extends SpriteBitmap {
    public Player(Bitmap bitmap) {
        super(bitmap);
    }

    public float getCenterX() {
        return x + bitmap.getWidth() / 2;
    }

    public float getCenterY() {
        return y + bitmap.getHeight() / 2;
    }
}