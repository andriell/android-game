package com.andriell.mygame.game2;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.andriell.mygame.R;
import com.andriell.mygame.base.Animation;
import com.andriell.mygame.base.DrawSprite;
import com.andriell.mygame.base.GameActivity;
import com.andriell.mygame.base.SpriteRunnerAnimation;

public class MainActivity extends GameActivity {
    DrawSprite drawSprite = new DrawSprite(this, 2);
    Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        drawSprite.addSprite(1, player);
    }


    class Player extends SpriteRunnerAnimation {
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
    }
}
