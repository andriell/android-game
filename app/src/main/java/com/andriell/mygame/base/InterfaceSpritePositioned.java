package com.andriell.mygame.base;

/**
 * Created by Андрей on 02.12.2016.
 */

public interface InterfaceSpritePositioned extends InterfaceSprite {
    void setX(float x);
    void setY(float y);
    void setCenterX(float x);
    void setCenterY(float y);
    float getX();
    float getY();
    float getHeight();
    float getWidth();
    float getCenterX();
    float getCenterY();
}
