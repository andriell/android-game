package com.andriell.mygame.game2;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;

import com.andriell.mygame.R;
import com.andriell.mygame.base.Animation;
import com.andriell.mygame.base.DrawSprite;
import com.andriell.mygame.base.GameActivity;
import com.andriell.mygame.base.InterfaceSpriteCollisionListener;
import com.andriell.mygame.base.InterfaceSpriteCollisionTarget;
import com.andriell.mygame.base.SpriteAnimation;
import com.andriell.mygame.base.SpriteButtonBitmap;
import com.andriell.mygame.base.SpriteColor;
import com.andriell.mygame.base.SpriteRunnerAnimation;

public class MainActivity extends GameActivity {
    DrawSprite drawSprite;
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        drawSprite = new DrawSprite(this, 3);
        drawSprite.addSprite(0, new SpriteColor(Color.GREEN));
        player = new Player();
        setPositionCenter(player);
        drawSprite.addSprite(1, player);

        SpriteButtonBitmap button = createButtonP();

        setContentView(drawSprite);
    }

    class ToRight implements SpriteButtonBitmap.DownListener, SpriteButtonBitmap.UpListener {
        @Override
        public boolean onDown(MotionEvent e) {
            player.toRight();
            return false;
        }

        @Override
        public boolean onUp(MotionEvent e) {
            player.toNormal();
            return false;
        }
    }

    class ToLeft implements SpriteButtonBitmap.DownListener, SpriteButtonBitmap.UpListener {
        @Override
        public boolean onDown(MotionEvent e) {
            player.toLeft();
            return false;
        }

        @Override
        public boolean onUp(MotionEvent e) {
            player.toNormal();
            return false;
        }
    }



    class Fireball extends SpriteAnimation implements InterfaceSpriteCollisionTarget {
        public Fireball() {
            setAnimation(createAnimationP(new int[] {R.drawable.fireball1_0, R.drawable.fireball1_1}, new int[] {100, 100}, 0F, 0.1F));
        }
    }

    class Rocket extends SpriteAnimation implements InterfaceSpriteCollisionTarget {
        public Rocket() {
            setAnimation(createAnimationP(new int[] {R.drawable.rocket_1_0, R.drawable.rocket_1_1}, new int[] {100, 100}, 0F, 0.1F));
        }
    }

    class Fly extends SpriteRunnerAnimation implements InterfaceSpriteCollisionListener {
        public Fly() {
            setAnimation(createAnimationP(new int[] {R.drawable.fly_1_0, R.drawable.fly_1_1}, new int[] {100, 100}, 0F, 0.1F));
        }

        @Override
        public boolean onCollision(InterfaceSpriteCollisionTarget sprite) {
            return false;
        }
    }

    class Player extends SpriteRunnerAnimation implements InterfaceSpriteCollisionListener {
        Animation animationNormal;
        Animation animationLeft;
        Animation animationRight;

        public Player() {
            animationNormal = createAnimationP(new int[] {R.drawable.player_1_0}, null, 0F, 0.1F);
            animationLeft = createAnimationP(new int[] {R.drawable.player_1_l}, null, 0F, 0.1F);
            animationRight = createAnimationP(new int[] {R.drawable.player_1_r}, null, 0F, 0.1F);
            setAnimation(animationNormal);
        }

        public void toLeft() {
            setSpeedX(xP(0.01F));
            setAnimation(animationLeft);
        }

        public void toRight() {
            setSpeedX(xP(-0.01F));
            setAnimation(animationRight);
        }

        public void toNormal() {
            setSpeedX(0F);
            setAnimation(animationNormal);
        }

        @Override
        public boolean onCollision(InterfaceSpriteCollisionTarget sprite) {
            return false;
        }
    }
}
