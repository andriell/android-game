package com.andriell.mygame;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.andriell.mygame.base.SpriteColor;
import com.andriell.mygame.base.SpriteMaterial;
import com.andriell.mygame.base.SpriteView;
import com.andriell.mygame.game.Bullet;

public class MainActivity extends Activity {

    Bitmap bitmapPlayer;
    Bitmap bitmapBullet;
    Bitmap bitmapMonster;

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
        bitmapPlayer = Bitmap.createScaledBitmap(bitmapPlayer, bitmapPlayer.getWidth() / 2, bitmapPlayer.getHeight() / 2, true);

        bitmapBullet = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);
        bitmapBullet = Bitmap.createScaledBitmap(bitmapBullet, bitmapBullet.getWidth() / 8, bitmapBullet.getHeight() / 8, true);

        bitmapMonster = BitmapFactory.decodeResource(getResources(), R.drawable.monster);
        bitmapMonster = Bitmap.createScaledBitmap(bitmapMonster, (int) (bitmapMonster.getWidth() / 1.5), (int) (bitmapMonster.getHeight() / 1.5), true);

        SpriteView spriteView = new SpriteView(this, 2) {
            public boolean onTouchEvent(MotionEvent e)
            {
                Log.i("SpriteView", "onTouchEvent");
                addSprite(1, new Bullet(bitmapBullet, new int[] {100, 100}, new int[] {(int) e.getX(), (int) e.getY()}, 25));

                return true;
            }
        };
        spriteView.addSprite(0, new SpriteColor(Color.WHITE));
        spriteView.addSprite(1, new SpriteMaterial() {
            private int[] xyxy = {100, 100, bitmapPlayer.getHeight() + 100, bitmapPlayer.getWidth() + 100};

            @Override
            public int[] getXYXY() {
                return xyxy;
            }

            @Override
            public boolean onDraw(Canvas c) {
                c.drawBitmap(bitmapPlayer, xyxy[0], xyxy[1], null);
                return true;
            }
        });



        spriteView.addSprite(1, new SpriteMaterial() {
            private int[] hw = {bitmapMonster.getHeight(), bitmapMonster.getWidth()};
            private int[] xyxy = {600, 100, 0, 0};

            @Override
            public int[] getXYXY() {
                return xyxy;
            }

            @Override
            public boolean onDraw(Canvas c) {
                c.drawBitmap(bitmapMonster, xyxy[0], xyxy[1], null);
                return true;
            }
        });


        setContentView(spriteView);
    }




}
