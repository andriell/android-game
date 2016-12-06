package com.andriell.mygame.base;

import android.graphics.Canvas;

/**
 * Created by Андрей on 06.12.2016.
 */

public class SpriteAnimationLimited extends SpriteAnimation {
    private long timeLimit = 3000;
    private long timeEnd = 0;

    @Override
    public boolean onDraw(Canvas c) {
        if (timeEnd == 0) {
            timeEnd = System.currentTimeMillis() + timeLimit;
        }
        return timeEnd > System.currentTimeMillis() && super.onDraw(c);
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }
}
