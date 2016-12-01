package com.andriell.mygame.base;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class DrawSprite extends DrawView {
    private AbstractSet<InterfaceSprite>[] sprites;
    private AbstractSet<InterfaceSpriteMaterial> spritesMaterial;
    private AbstractSet<InterfaceSpriteCollisionListener> spritesCollision;
    private AbstractSet<InterfaceSpriteTouchListener> spritesTouchListener;
    private AbstractSet<InterfaceSprite>[] spritesNew;

    public DrawSprite(Context context, int zSize) {
        super(context);
        spritesMaterial = new CopyOnWriteArraySet<InterfaceSpriteMaterial>();
        spritesCollision = new CopyOnWriteArraySet<InterfaceSpriteCollisionListener>();
        spritesTouchListener = new CopyOnWriteArraySet<InterfaceSpriteTouchListener>();
        sprites = new CopyOnWriteArraySet[zSize];
        spritesNew = new CopyOnWriteArraySet[zSize];
        for (int i = 0; i < zSize; i++) {
            sprites[i] = new CopyOnWriteArraySet<InterfaceSprite>();
            spritesNew[i] = new CopyOnWriteArraySet<InterfaceSprite>();
        }
    }

    public void addSprite(int z, InterfaceSprite sprite) {
        if (z < 0 || z >= sprites.length) {
            return;
        }
        spritesNew[z].add(sprite);
    }

    @Override
    protected void myDraw(Canvas canvas) {
        Iterator<InterfaceSprite> iterator;
        InterfaceSprite s;
        synchronized (this) {
            //<editor-fold desc="Рисуем старые спрайты">
            for (int i = 0; i < sprites.length; i++) {
                try {
                    iterator = sprites[i].iterator();
                    while (iterator.hasNext()) {
                        s = iterator.next();
                        if (!s.onDraw(canvas)) {
                            Log.i("SpriteView", "InterfaceSprite remove");
                            if (s instanceof InterfaceSpriteCollisionListener) {
                                spritesCollision.remove((InterfaceSpriteCollisionListener) s);
                            } else if (s instanceof InterfaceSpriteMaterial) {
                                spritesMaterial.remove((InterfaceSpriteMaterial) s);
                            }
                            if (s instanceof InterfaceSpriteTouchListener) {
                                spritesTouchListener.remove((InterfaceSpriteTouchListener) s);
                            }
                            sprites[i].remove(s);
                        }
                    }
                } catch (Exception e) {
                    Log.e("SpriteView", "InterfaceSprite iterator error", e);
                }
            }
            //</editor-fold>

            try {
                for (InterfaceSpriteCollisionListener collisionListener : spritesCollision) {
                    for (InterfaceSpriteMaterial material : spritesMaterial) {
                        if (collisionListener.equals(material)) {
                            continue;
                        }
                        // 1x2 < 2x1 || 1x1 > 2x2
                        if ((collisionListener.getX() + collisionListener.getWidth() < material.getX() || collisionListener.getX() > material.getX() + material.getWidth())) {
                            continue;
                        }
                        // 1y2 < 2y1 || 1y1 > 2y2
                        if ((collisionListener.getY() + collisionListener.getHeight() < material.getY() || collisionListener.getY() > material.getY() + material.getHeight())) {
                            continue;
                        }
                        if (!collisionListener.onCollision(material)) {
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("SpriteView", "InterfaceSpriteCollisionListener error", e);
            }

            //<editor-fold desc="Добавляем новые спрайты">
            for (int i = 0; i < sprites.length; i++) {
                iterator = spritesNew[i].iterator();
                while (iterator.hasNext()) {
                    s = iterator.next();
                    sprites[i].add(s);
                    if (s instanceof InterfaceSpriteCollisionListener) {
                        spritesCollision.add((InterfaceSpriteCollisionListener) s);
                    } else if (s instanceof InterfaceSpriteMaterial) {
                        spritesMaterial.add((InterfaceSpriteMaterial) s);
                    }
                    if (s instanceof InterfaceSpriteTouchListener) {
                        spritesTouchListener.add((InterfaceSpriteTouchListener) s);
                    }
                }
                spritesNew[i].clear();
            }
            //</editor-fold>
        }
    }

    public boolean onTouchEvent(MotionEvent e) {
        for (InterfaceSpriteTouchListener touchListener : spritesTouchListener) {
            if (!touchListener.onTouchEvent(e)) {
                return false;
            }
        }
        return true;
    }
}
