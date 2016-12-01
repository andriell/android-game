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
    private VeryFastSet<InterfaceSprite>[] sprites;
    private VeryFastSet<InterfaceSpriteMaterial> spritesMaterial;
    private VeryFastSet<InterfaceSpriteCollisionListener> spritesCollision;
    private VeryFastSet<InterfaceSpriteTouchListener> spritesTouchListener;

    public DrawSprite(Context context, int zSize) {
        super(context);
        spritesMaterial = new VeryFastSet<InterfaceSpriteMaterial>();
        spritesCollision = new VeryFastSet<InterfaceSpriteCollisionListener>();
        spritesTouchListener = new VeryFastSet<InterfaceSpriteTouchListener>();
        sprites = new VeryFastSet[zSize];
        for (int i = 0; i < zSize; i++) {
            sprites[i] = new VeryFastSet<InterfaceSprite>();
        }
    }

    public void addSprite(int z, InterfaceSprite s) {
        if (z < 0 || z >= sprites.length) {
            return;
        }
        sprites[z].add(s);
        if (s instanceof InterfaceSpriteCollisionListener) {
            spritesCollision.add((InterfaceSpriteCollisionListener) s);
        } else if (s instanceof InterfaceSpriteMaterial) {
            spritesMaterial.add((InterfaceSpriteMaterial) s);
        }
        if (s instanceof InterfaceSpriteTouchListener) {
            spritesTouchListener.add((InterfaceSpriteTouchListener) s);
        }
    }

    @Override
    protected void myDraw(Canvas canvas) {
        synchronized (this) {
            //<editor-fold desc="Обновляем коллекции">
            try {
                spritesMaterial.update();
            } catch (Exception e) {
                Log.e("SpriteView", "spritesMaterial update error", e);
            }
            try {
                spritesCollision.update();
            } catch (Exception e) {
                Log.e("SpriteView", "spritesCollision update error", e);
            }
            try {
                spritesTouchListener.update();
            } catch (Exception e) {
                Log.e("SpriteView", "spritesTouchListener update error", e);
            }
            //</editor-fold>

            //<editor-fold desc="Рисуем старые спрайты">
            for (int i = 0; i < sprites.length; i++) {
                try {
                    sprites[i].update();
                } catch (Exception e) {
                    Log.e("SpriteView", "sprites update error", e);
                }
                try {
                    Iterator<InterfaceSprite> spriteIterator = sprites[i].iterator();
                    while (spriteIterator.hasNext()) {
                        InterfaceSprite sprite = spriteIterator.next();
                        if (!sprite.onDraw(canvas)) {
                            Log.i("SpriteView", "InterfaceSprite remove");
                            if (sprite instanceof InterfaceSpriteCollisionListener) {
                                spritesCollision.remove((InterfaceSpriteCollisionListener) sprite);
                            } else if (sprite instanceof InterfaceSpriteMaterial) {
                                spritesMaterial.remove((InterfaceSpriteMaterial) sprite);
                            }
                            if (sprite instanceof InterfaceSpriteTouchListener) {
                                spritesTouchListener.remove((InterfaceSpriteTouchListener) sprite);
                            }
                            sprites[i].remove(sprite);
                        }
                    }
                } catch (Exception e) {
                    Log.e("SpriteView", "InterfaceSprite iterator error", e);
                }
            }
            //</editor-fold>
            //<editor-fold desc="Проверяем колизии">
            try {
                Iterator<InterfaceSpriteCollisionListener> iteratorCollision = spritesCollision.iterator();
                while (iteratorCollision.hasNext()) {
                    InterfaceSpriteCollisionListener listener = iteratorCollision.next();
                    Iterator<InterfaceSpriteMaterial> iteratorMaterial = spritesMaterial.iterator();
                    while (iteratorMaterial.hasNext()) {
                        InterfaceSpriteMaterial material = iteratorMaterial.next();
                        if (listener.equals(material)) {
                            continue;
                        }
                        // 1x2 < 2x1 || 1x1 > 2x2
                        if ((listener.getX() + listener.getWidth() < material.getX() || listener.getX() > material.getX() + material.getWidth())) {
                            continue;
                        }
                        // 1y2 < 2y1 || 1y1 > 2y2
                        if ((listener.getY() + listener.getHeight() < material.getY() || listener.getY() > material.getY() + material.getHeight())) {
                            continue;
                        }
                        if (!listener.onCollision(material)) {
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("SpriteView", "InterfaceSpriteCollisionListener error", e);
            }
            //</editor-fold>
        }
    }

    public boolean onTouchEvent(MotionEvent e) {
        Iterator<InterfaceSpriteTouchListener> iterator = spritesTouchListener.iterator();
        InterfaceSpriteTouchListener touchListener;
        while (iterator.hasNext()) {
            touchListener = iterator.next();
            if (!touchListener.onTouchEvent(e)) {
                return false;
            }
        }
        return true;
    }
}
