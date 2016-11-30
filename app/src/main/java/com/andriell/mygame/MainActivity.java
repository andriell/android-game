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

import com.andriell.mygame.base.SpriteColor;
import com.andriell.mygame.base.DrawSprite;
import com.andriell.mygame.game.Bullet;
import com.andriell.mygame.game.Monster;
import com.andriell.mygame.game.Player;

import java.util.Random;

public class MainActivity extends Activity {

    Bitmap bitmapPlayer;
    Bitmap bitmapBullet;
    Bitmap bitmapMonster;
    Player player;
    DrawSprite drawSprite;
    Random rnd = new Random();
    Handler handler = new Handler();
    Point displaySize = new Point();

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

        bitmapMonster = BitmapFactory.decodeResource(getResources(), R.drawable.monster);
        bitmapMonster = Bitmap.createScaledBitmap(bitmapMonster, (int) (bitmapMonster.getWidth() / 3), (int) (bitmapMonster.getHeight() / 3), true);

        getWindowManager().getDefaultDisplay().getSize(displaySize);

        player = new Player(bitmapPlayer, 100, displaySize.y / 2);

        drawSprite = new DrawSprite(this, 2) {
            public boolean onTouchEvent(MotionEvent e)
            {
                if(e.getAction() != MotionEvent.ACTION_DOWN) {
                    return true;
                }

                Log.i("SpriteView", "onTouchEvent");

                double angle = Math.atan((double)(player.getCenterY() - e.getY()) / (player.getCenterX() - e.getX()));
                int speedX = (int) (10 * Math.cos(angle));
                int speedY = (int) (10 * Math.sin(angle));

                addSprite(1, new Bullet(bitmapBullet, player.getCenterX(), player.getCenterY(), speedX, speedY));
                return true;
            }
        };
        drawSprite.addSprite(0, new SpriteColor(Color.WHITE));

        drawSprite.addSprite(1, player);

        setContentView(drawSprite);
        handler.post(new Runnable() {
            @Override
            public void run() {
                drawSprite.addSprite(1, new Monster(bitmapMonster, displaySize.x - bitmapMonster.getWidth(), rnd.nextInt(displaySize.y - bitmapMonster.getHeight()), -3, 0));
                handler.postDelayed(this, rnd.nextInt(1000));
            }
        });
    }
}
