package com.andriell.mygame.base;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class SpriteColor implements InterfaceSprite {
    int color = Color.WHITE;

    public SpriteColor() {}

    public SpriteColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean onDraw(Canvas c) {
        c.drawColor(color);
        return true;
    }
}
