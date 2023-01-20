package com.tapia.mji.demo.Activities;

import android.os.Bundle;
import android.view.View;

import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.Animations.TapiaAnimationView;
import com.tapia.mji.tapialib.Animations.animations.AngryAnimation;
import com.tapia.mji.tapialib.Animations.animations.ConfuseAnimation;
import com.tapia.mji.tapialib.Animations.animations.CryAnimation;
import com.tapia.mji.tapialib.Animations.animations.FunnyAnimation;
import com.tapia.mji.tapialib.Animations.animations.HappyAnimation;
import com.tapia.mji.tapialib.Animations.animations.HeartAnimation;
import com.tapia.mji.tapialib.Animations.animations.PlainAnimation;
import com.tapia.mji.tapialib.Animations.animations.RestAnimation;
import com.tapia.mji.tapialib.Animations.animations.SmileAnimation;
import com.tapia.mji.tapialib.Animations.animations.TiredAnimation;

public class AnimationActivity extends TapiaActivity {
    private TapiaAnimationView animationView;
    private View blinkButton;
    private View cryingButton;
    private View happyButton;
    private View cancelButton;
    private View restButton;
    private View openEyesButton;
    private View tiredButton;
    private View funnyButton;
    private View smileButton;
    private View angryButton;
    private View confuseButton;
    private View heartButton;
    private View homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        animationView = (TapiaAnimationView) findViewById(R.id.animation_view);
        blinkButton = findViewById(R.id.blink);
        cancelButton = findViewById(R.id.cancel_button);
        restButton = findViewById(R.id.rest_button);
        openEyesButton = findViewById(R.id.open_eyes_button);
        angryButton = findViewById(R.id.angry);
        confuseButton = findViewById(R.id.confuse);
        cryingButton = findViewById(R.id.cry);
        funnyButton = findViewById(R.id.funny);
        happyButton = findViewById(R.id.happy);
        heartButton = findViewById(R.id.heart);
        smileButton = findViewById(R.id.smile);
        tiredButton = findViewById(R.id.tired);
        homeButton = findViewById(R.id.homeButton);

        PlainAnimation plain = new PlainAnimation(this);
        plain.playPlain(animationView);

        homeButton.setOnClickListener(v -> {
            finish();
        });
        blinkButton.setOnClickListener(view -> {
            new PlainAnimation(this).playBlink(animationView);
        });

        cryingButton.setOnClickListener(view -> {
            new CryAnimation(this).play(animationView);
        });


        happyButton.setOnClickListener(view -> {
            new HappyAnimation(this).play(animationView);
        });


        cancelButton.setOnClickListener(view -> {
            animationView.cancelAnimation();
        });

        restButton.setOnClickListener(view -> {
            new RestAnimation(this).playRest(animationView);
        });

        openEyesButton.setOnClickListener(view -> {
            new RestAnimation(this).playOpenEyes(animationView);
        });

        smileButton.setOnClickListener(view -> {
            new SmileAnimation(this).play(animationView);
        });


        funnyButton.setOnClickListener(view -> {
            new FunnyAnimation(this).play(animationView);
        });

        tiredButton.setOnClickListener(view -> {
            new TiredAnimation(this).play(animationView);
        });

        angryButton.setOnClickListener(view -> {
            new AngryAnimation(this).play(animationView);
        });
        confuseButton.setOnClickListener(view -> {
            new ConfuseAnimation(this).play(animationView);
        });
        heartButton.setOnClickListener(view -> {
            new HeartAnimation(this).play(animationView);
        });

    }
}
