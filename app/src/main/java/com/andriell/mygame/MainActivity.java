package com.andriell.mygame;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.andriell.mygame.base.SpriteColor;
import com.andriell.mygame.base.DrawSprite;
import com.andriell.mygame.game.Bullet;
import com.andriell.mygame.game.Monster;
import com.andriell.mygame.game.Player;

public class MainActivity extends Activity {

    Bitmap bitmapPlayer;
    Bitmap bitmapBullet;
    Bitmap bitmapMonster;
    Player player;

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

        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);

        player = new Player(bitmapPlayer, 100, displaySize.y / 2);

        DrawSprite drawSprite = new DrawSprite(this, 2) {
            public boolean onTouchEvent(MotionEvent e)
            {
                Log.i("SpriteView", "onTouchEvent");

                double angle = Math.atan((double)(player.getCenterY() - e.getY()) / (player.getCenterX() - e.getX()));
                int speedX = (int) (10 * Math.cos(angle));
                int speedY = (int) (10 * Math.sin(angle));

                addSprite(1, new Bullet(bitmapBullet, player.getCenterX(), player.getCenterY(), speedX, speedY));
                addSprite(1, new Monster(bitmapMonster, 1000, 100, -3, 0));
                return true;
            }
        };
        drawSprite.addSprite(0, new SpriteColor(Color.WHITE));

        drawSprite.addSprite(1, player);


        setContentView(drawSprite);
    }




}
