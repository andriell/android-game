package com.andriell.mygame;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

import com.andriell.mygame.base.Animation;
import com.andriell.mygame.base.GameActivity;
import com.andriell.mygame.base.InterfaceSpriteMaterial;
import com.andriell.mygame.base.SpriteColor;
import com.andriell.mygame.base.DrawSprite;
import com.andriell.mygame.game.Bullet;
import com.andriell.mygame.game.Count;
import com.andriell.mygame.game.Monster;
import com.andriell.mygame.game.Player;

import java.util.Random;

public class MainActivity extends GameActivity {

    Bitmap bitmapPlayer;
    Bitmap bitmapBullet;
    Animation animationMonster;
    Player player;
    DrawSprite drawSprite;
    Random rnd = new Random();
    Handler handler = new Handler();

    Count count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bitmapPlayer = createBitmapP(R.drawable.player, 0.12F);
        bitmapBullet = createBitmapP(R.drawable.bullet, 0.035F);

        animationMonster = createAnimationP(new int[] {R.drawable.monster1, R.drawable.monster2}, new int[]{100, 100}, 0.15F);

        player = new Player(bitmapPlayer, 100, displaySize.y / 2);

        drawSprite = new DrawSprite(this, 2) {
            public boolean onTouchEvent(MotionEvent e)
            {
                super.onTouchEvent(e);
                if(e.getAction() != MotionEvent.ACTION_DOWN) {
                    return true;
                }

                Log.i("SpriteView", "onTouchEvent");
                float x = e.getX() - player.getCenterX();
                float y = e.getY() - player.getCenterY();
                float z = (float) Math.sqrt(x * x + y * y);
                float speedX = (10f * x) / z;
                float speedY = (10f * y) / z;

                addSprite(1, new Bullet(bitmapBullet, player.getCenterX(), player.getCenterY(), speedX, speedY));
                return true;
            }
        };
        drawSprite.addSprite(0, new SpriteColor(Color.WHITE));

        drawSprite.addSprite(1, player);

        count = new Count(displaySize.x / 2, 30);
        drawSprite.addSprite(1, count);

        setContentView(drawSprite);
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRun) {
                    drawSprite.addSprite(1, new Monster(animationMonster, displaySize.x - 1, rnd.nextInt((int) (displaySize.y - animationMonster.getHeight())), -3, 0) {
                        @Override
                        public boolean onCollision(InterfaceSpriteMaterial sprite) {
                            boolean r = super.onCollision(sprite);
                            if (!r) {
                                count.count();
                            }
                            return r;
                        }
                    });
                }
                handler.postDelayed(this, rnd.nextInt(10000));
            }
        });
    }
}
