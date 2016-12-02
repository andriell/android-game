package com.andriell.mygame.base;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Андрей on 30.11.2016.
 */

public class SpriteText implements InterfaceSpriteSetPosition {
    private Paint paint;
    private String text = "";
    private float x;
    private float y;

    public SpriteText() {
        this(new Paint(), 0F, 0F);
    }

    public SpriteText(float x, float y) {
        this(new Paint(), x, y);
    }

    public SpriteText(Paint paint, float x, float y) {
        this.x = x;
        this.y = y;
        this.paint = paint;
    }


    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean onDraw(Canvas c) {
        c.drawText(text, x, y, paint);
        return true;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    @Override
    public float getWidth() {
        return 0;
    }
}
