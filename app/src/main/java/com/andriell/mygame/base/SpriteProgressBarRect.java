package com.andriell.mygame.base;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Андрей on 06.12.2016.
 */

public class SpriteProgressBarRect extends SpriteResize {
    protected Paint paint = new Paint();
    protected int colorValue = Color.GREEN;
    protected int colorBackground = Color.RED;
    protected int colorBorder = Color.WHITE;
    protected int border = 2;
    protected float value = 0.5F;
    protected boolean horizontal = true;

    @Override
    public boolean onDraw(Canvas c) {
        paint.setColor(colorBorder);
        c.drawRect((int) x, (int) y, (int) (x + width), (int) (y + height), paint);
        if (horizontal) {
            paint.setColor(colorBackground);
            c.drawRect((int) (x - 1F + width * value), (int) (y + border), (int) (x  - border + width), (int) (y - border + height), paint);
            paint.setColor(colorValue);
            c.drawRect((int) (x + border), (int) (y + border), (int) (x + width * value), (int) (y - border + height), paint);
        } else {
            paint.setColor(colorBackground);
            c.drawRect((int) (x + border), (int) (y - 1F + height * value), (int) (x - border + width), (int) (y - border + height), paint);
            paint.setColor(colorValue);
            c.drawRect((int) (x + border), (int) (y + border), (int) (x - border + width), (int) (y + height * value), paint);
        }
        return true;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getColorValue() {
        return colorValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }

    public int getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(int colorBackground) {
        this.colorBackground = colorBackground;
    }

    public int getColorBorder() {
        return colorBorder;
    }

    public void setColorBorder(int colorBorder) {
        this.colorBorder = colorBorder;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        if (value > 1) {
            this.value = 1F;
        } else if (value < 0F) {
            this.value = 0F;
        } else {
            this.value = value;
        }
    }

    public void addValue(float value) {
        this.value += value;
        if (this.value > 1) {
            this.value = 1F;
        } else if (this.value < 0F) {
            this.value = 0F;
        }
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
}
