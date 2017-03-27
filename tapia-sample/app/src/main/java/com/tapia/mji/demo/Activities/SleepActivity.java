package com.tapia.mji.demo.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.RobotService;
import android.view.View;
import android.widget.ImageView;

import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.Utils.TapiaAnimation;
import com.tapia.mji.tapialib.Utils.TapiaAudio;
import com.tapia.mji.tapialib.Utils.TapiaRobot;

/**
 * Created by Sami on 12-Jul-16.
 */
public class SleepActivity extends TapiaActivity {
    TapiaAnimation tapiaAnimation;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eyes_layout);
        ImageView tapiaEyes = (ImageView) findViewById(R.id.eyes);
        TapiaAudio.setVolume(this, TapiaAudio.getCurrent(),false);
        tapiaAnimation = new TapiaAnimation(this,tapiaEyes);
        tapiaEyes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(activity,PhotoMenuActivity.class));
                return false;
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        tapiaAnimation.startAnimation(TapiaAnimation.EXHAUSTED,true);
        TapiaRobot.rotate(activity, RobotService.ORIENTATION_DOWN,200,null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        tapiaAnimation.stopAnimation();
        TapiaRobot.rotate(activity, RobotService.ORIENTATION_UP,15,null);
    }
}
