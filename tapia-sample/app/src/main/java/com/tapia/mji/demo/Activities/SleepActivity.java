package com.tapia.mji.demo.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.Utils.TapiaAnimation;
import com.tapia.mji.tapialib.Utils.TapiaAudio;
import com.tapia.mji.tapialib.Utils.TapiaRobot;
import com.tapia.mji.tapialib.Utils.TapiaRobotManager;

/**
 * Created by Sami on 12-Jul-16.
 */
public class SleepActivity extends TapiaActivity {
    TapiaAnimation tapiaAnimation;
    TapiaRobotManager tapiaRobotManager;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eyes_layout);

        ImageView tapiaEyes = findViewById(R.id.eyes);
        TapiaAudio.setVolume(this, TapiaAudio.getCurrent(), false);
        tapiaRobotManager = TapiaRobot.getManager(this);
        tapiaAnimation = new TapiaAnimation(this, tapiaEyes);
        tapiaEyes.setOnLongClickListener(v -> {
            startActivity(new Intent(activity, PhotoMenuActivity.class));
            return false;
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        tapiaAnimation.startAnimation(TapiaAnimation.EXHAUSTED, true);
        tapiaRobotManager.rotate(TapiaRobotManager.Direction.DOWN, 200, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        tapiaAnimation.stopAnimation();
        tapiaRobotManager.rotate(TapiaRobotManager.Direction.UP, 15, null);
    }
}
