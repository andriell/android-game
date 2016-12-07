package com.andriell.mygame.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

/**
 * Created by Андрей on 07.12.2016.
 */

public class DrawPreload extends View {
    private int backgroundColor = Color.BLACK;
    private Paint paint = new Paint();
    protected float width = 0;
    protected float height = 0;
    protected float value = 0;
    protected float maxValue = 1000;

    public DrawPreload(Context context, float width, float height) {
        super(context);
        this.width = width;
        this.height = height;

        paint = new Paint();

        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        paint.setStrokeWidth(3);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public Paint getPaint() {
        return paint;
    }

    public float getValue() {
        return value;
    }


    public void setValue(float v) {
        value = v;
        invalidate();
        Log.v("DrawPreload", Float.toString(value));
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(backgroundColor);
        canvas.drawText("Loading " + value + "/" + maxValue, width / 2, height / 2, paint);
        float rectLeft = width / 3;
        float rectTop = height / 2 + paint.getTextSize();
        canvas.drawRect(rectLeft, rectTop, rectLeft * 2, rectTop + 3, paint);
    }
}
