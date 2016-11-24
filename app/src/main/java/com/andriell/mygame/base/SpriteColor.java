package com.andriell.mygame.base;

import android.graphics.Canvas;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class SpriteColor implements InterfaceSprite {
    int color;

    public SpriteColor(int color) {
        this.color = color;
    }

    @Override
    public boolean onDraw(Canvas c) {
        c.drawColor(color);
        return true;
    }
}
