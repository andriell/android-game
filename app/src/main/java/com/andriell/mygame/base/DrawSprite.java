package com.andriell.mygame.base;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Iterator;

/**
 * Created by Rybalko on 24.11.2016.
 */

public class DrawSprite extends DrawView {
    private VeryFastSet<InterfaceSprite>[] sprites;
    private VeryFastSet<InterfaceSpriteCollisionTarget> spritesMaterial;
    private VeryFastSet<InterfaceSpriteCollisionListener> spritesCollision;
    private VeryFastSet<InterfaceSpriteTouchListener> spritesTouchListener;

    public DrawSprite(Context context, int zSize) {
        super(context);
        spritesMaterial = new VeryFastSet<InterfaceSpriteCollisionTarget>();
        spritesCollision = new VeryFastSet<InterfaceSpriteCollisionListener>();
        spritesTouchListener = new VeryFastSet<InterfaceSpriteTouchListener>();
        sprites = new VeryFastSet[zSize];
        for (int i = 0; i < zSize; i++) {
            sprites[i] = new VeryFastSet<InterfaceSprite>();
        }
    }

    public void addSprite(int z, InterfaceSprite s) {
        addSprite(z, s, true);
    }

    public void addSprite(int z, InterfaceSprite s, boolean isDraw) {
        if (z < 0 || z >= sprites.length) {
            return;
        }
        if (isDraw) {
            sprites[z].add(s);
        }
        if (s instanceof InterfaceSpriteCollisionListener) {
            spritesCollision.add((InterfaceSpriteCollisionListener) s);
        } else if (s instanceof InterfaceSpriteCollisionTarget) {
            spritesMaterial.add((InterfaceSpriteCollisionTarget) s);
        }
        if (s instanceof InterfaceSpriteTouchListener) {
            spritesTouchListener.add((InterfaceSpriteTouchListener) s);
        }
        if (s instanceof InterfaceSpriteGroup) {
            InterfaceSpritePositioned[] elements = ((InterfaceSpriteGroup) s).getSprites();
            for (InterfaceSpritePositioned element: elements) {
                addSprite(z, element, false);
            }
        }
    }

    public void removeSprite(int z, InterfaceSprite s) {
        if (z < 0 || z >= sprites.length) {
            return;
        }
        if (s instanceof InterfaceSpriteCollisionListener) {
            spritesCollision.remove((InterfaceSpriteCollisionListener) s);
        } else if (s instanceof InterfaceSpriteCollisionTarget) {
            spritesMaterial.remove((InterfaceSpriteCollisionTarget) s);
        }
        if (s instanceof InterfaceSpriteTouchListener) {
            spritesTouchListener.remove((InterfaceSpriteTouchListener) s);
        }
        if (s instanceof InterfaceSpriteGroup) {
            InterfaceSpritePositioned[] elements = ((InterfaceSpriteGroup) s).getSprites();
            for (InterfaceSpritePositioned element: elements) {
                removeSprite(z, element);
            }
        }
        sprites[z].remove(s);
    }

    @Override
    protected void myDraw(Canvas canvas) {
        synchronized (this) {
            //<editor-fold desc="Рисуем старые спрайты">
            for (int i = 0; i < sprites.length; i++) {
                try {
                    Iterator<InterfaceSprite> spriteIterator = sprites[i].iterator();
                    while (spriteIterator.hasNext()) {
                        InterfaceSprite sprite = spriteIterator.next();
                        if (!sprite.onDraw(canvas)) {
                            Log.i("SpriteView", "InterfaceSprite remove");
                            removeSprite(i, sprite);
                        }
                    }
                } catch (Exception e) {
                    Log.e("SpriteView", "InterfaceSprite iterator error", e);
                }
                try {
                    sprites[i].update();
                } catch (Exception e) {
                    Log.e("SpriteView", "sprites update error", e);
                }
            }
            //</editor-fold>

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

            //<editor-fold desc="Проверяем колизии">
            try {
                Iterator<InterfaceSpriteCollisionListener> iteratorCollision = spritesCollision.iterator();
                while (iteratorCollision.hasNext()) {
                    InterfaceSpriteCollisionListener listener = iteratorCollision.next();
                    Iterator<InterfaceSpriteCollisionTarget> iteratorMaterial = spritesMaterial.iterator();
                    while (iteratorMaterial.hasNext()) {
                        InterfaceSpriteCollisionTarget material = iteratorMaterial.next();
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
        InterfaceSpriteTouchListener listener;
        while (iterator.hasNext()) {
            listener = iterator.next();
            // x1 <= x <= x2
            // y1 <= y <= y2
            int index = e.getActionIndex();
            if (listener.getX() <= e.getX(index) && e.getX(index) <= listener.getX() + listener.getWidth() && listener.getY() <= e.getY(index) && e.getY(index) <= listener.getY() + listener.getHeight()) {
                listener.onTouchEvent(e);
            }
        }
        return true;
    }
}
