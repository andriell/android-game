package com.andriell.mygame.base;

/**
 * Created by Андрей on 08.12.2016.
 */

public class SpriteRouteLissajous extends SpriteRoute {
    int circleX = 1;
    int circleY = 1;
    public SpriteRouteLissajous(int size) {
        super(size);
    }

    @Override
    public float[] newXY(float t) {
        float[] r = new float[2];
        r[0] = (float) (getWidth() * (Math.cos(t / circleY) + 1)) / 2 + getX();
        r[1] = (float) (getHeight() * (Math.sin(t / circleX) + 1)) / 2 + getY();
        return r;
    }

    @Override
    public void setSpeed(float speed) {
        super.setSpeed(speed / 1000);
    }

    public int getCircleX() {
        return circleX;
    }

    public void setCircleX(int circleX) {
        this.circleX = circleX;
    }

    public int getCircleY() {
        return circleY;
    }

    public void setCircleY(int circleY) {
        this.circleY = circleY;
    }
}
