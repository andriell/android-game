package com.andriell.mygame.base;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class DrawSprite extends DrawView {
    private List<InterfaceSprite>[] sprites;
    private List<InterfaceSpriteMaterial>[] spritesMaterial;
    private List<InterfaceSpriteCollisionListener>[] spritesCollision;
    private List<InterfaceSprite>[] spritesNew;

    public DrawSprite(Context context, int zSize) {
        super(context);
        sprites = new ArrayList[zSize];
        spritesMaterial = new ArrayList[zSize];
        spritesCollision = new ArrayList[zSize];
        spritesNew = new ArrayList[zSize];
        for (int i = 0; i < zSize; i++) {
            sprites[i] = new ArrayList<InterfaceSprite>();
            spritesMaterial[i] = new ArrayList<InterfaceSpriteMaterial>();
            spritesCollision[i] = new ArrayList<InterfaceSpriteCollisionListener>();
            spritesNew[i] = new ArrayList<InterfaceSprite>();
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
        Iterator<InterfaceSpriteMaterial> materialIterator;
        Iterator<InterfaceSpriteCollisionListener> listenerIterator;
        InterfaceSprite s;
        InterfaceSpriteCollisionListener collisionListener;

        synchronized (this) {
            //<editor-fold desc="Рисуем старые спрайты">
            for (int i = 0; i < sprites.length; i++) {
                try {
                    iterator = sprites[i].iterator();
                    while (iterator.hasNext()) {
                        s = iterator.next();
                        if (!s.onDraw(canvas)) {
                            Log.i("SpriteView", "InterfaceSprite remove");
                            iterator.remove();
                        }
                    }
                } catch (Exception e) {
                    Log.e("SpriteView", "InterfaceSprite iterator error", e);
                }
                try {
                    listenerIterator = spritesCollision[i].iterator();
                    while (listenerIterator.hasNext()) {
                        collisionListener = listenerIterator.next();
                        testCollision(collisionListener);
                        if (!collisionListener.onDraw(canvas)) {
                            Log.i("SpriteView", "InterfaceSpriteMaterial remove");
                            listenerIterator.remove();
                        }
                    }
                } catch (Exception e) {
                    Log.e("SpriteView", "InterfaceSpriteMaterial iterator error", e);
                }
                try {
                    materialIterator = spritesMaterial[i].iterator();
                    while (materialIterator.hasNext()) {
                        s = materialIterator.next();
                        if (!s.onDraw(canvas)) {
                            Log.i("SpriteView", "InterfaceSpriteCollisionListener remove");
                            materialIterator.remove();
                        }
                    }
                } catch (Exception e) {
                    Log.e("SpriteView", "InterfaceSpriteCollisionListener iterator error", e);
                }
            }
            //</editor-fold>

            //<editor-fold desc="Добавляем новые спрайты">
            for (int i = 0; i < sprites.length; i++) {
                iterator = spritesNew[i].iterator();
                while (iterator.hasNext()) {
                    s = iterator.next();
                    if (s instanceof InterfaceSpriteCollisionListener) {
                        spritesCollision[i].add((InterfaceSpriteCollisionListener) s);
                    } else if (s instanceof InterfaceSpriteMaterial) {
                        spritesMaterial[i].add((InterfaceSpriteMaterial) s);
                    } else {
                        sprites[i].add(s);
                    }
                }
                spritesNew[i].clear();
            }
            //</editor-fold>
        }
    }

    protected void testCollision(InterfaceSpriteCollisionListener listener) {
        Iterator<InterfaceSpriteMaterial> materialIterator;
        InterfaceSpriteMaterial material;

        for (int i = 0; i < sprites.length; i++) {
            materialIterator = spritesMaterial[i].iterator();
            while (materialIterator.hasNext()) {
                material = materialIterator.next();
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
    }

    public boolean onTouchEvent(MotionEvent e)
    {
        return true;
    }
}
