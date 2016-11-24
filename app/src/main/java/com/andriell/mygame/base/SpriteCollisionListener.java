package com.andriell.mygame.base;

/**
 * Created by Rybalko on 24.11.2016.
 */

public interface SpriteCollisionListener extends SpriteMaterial {
    boolean onCollision(SpriteMaterial sprite);
}
