package com.andriell.mygame;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.andriell.mygame.base.Animation;
import com.andriell.mygame.base.InterfaceSpriteMaterial;
import com.andriell.mygame.base.SpriteColor;
import com.andriell.mygame.base.DrawSprite;
import com.andriell.mygame.game.Bullet;
import com.andriell.mygame.game.Count;
import com.andriell.mygame.game.Monster;
import com.andriell.mygame.game.Player;

import java.util.Random;

public class MainActivity extends Activity {

    Bitmap bitmapPlayer;
    Bitmap bitmapBullet;
    Animation animationMonster;
    Player player;
    DrawSprite drawSprite;
    Random rnd = new Random();
    Handler handler = new Handler();
    Point displaySize = new Point();
    Count count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // если хотим, чтобы приложение постоянно имело портретную ориентацию
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // если хотим, чтобы приложение было полноэкранным
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // и без заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        bitmapPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        bitmapPlayer = Bitmap.createScaledBitmap(bitmapPlayer, bitmapPlayer.getWidth() / 4, bitmapPlayer.getHeight() / 4, true);

        bitmapBullet = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);
        bitmapBullet = Bitmap.createScaledBitmap(bitmapBullet, bitmapBullet.getWidth() / 16, bitmapBullet.getHeight() / 16, true);

        Bitmap monster1 = BitmapFactory.decodeResource(getResources(), R.drawable.monster1);
        monster1 = Bitmap.createScaledBitmap(monster1, (int) (monster1.getWidth() / 3), (int) (monster1.getHeight() / 3), true);
        Bitmap monster2 = BitmapFactory.decodeResource(getResources(), R.drawable.monster2);
        monster2 = Bitmap.createScaledBitmap(monster2, (int) (monster2.getWidth() / 3), (int) (monster2.getHeight() / 3), true);

        animationMonster = new Animation(new Bitmap[]{monster1, monster2}, new int[]{100, 100});

        getWindowManager().getDefaultDisplay().getSize(displaySize);

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
                handler.postDelayed(this, rnd.nextInt(10000));
            }
        });
    }
}
