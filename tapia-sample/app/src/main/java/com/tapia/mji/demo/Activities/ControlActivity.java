package com.tapia.mji.demo.Activities;

import android.os.Bundle;

import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.Utils.TapiaRobot;
import com.tapia.mji.tapialib.Utils.TapiaRobotManager;

public class ControlActivity extends TapiaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        TapiaRobotManager robotManager = TapiaRobot.getManager(getApplicationContext());

        findViewById(R.id.homeButton).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.shutdownButton).setOnClickListener(v -> {
            robotManager.shutdownTapia();
        });

        findViewById(R.id.rebootButton).setOnClickListener(v -> {
            robotManager.rebootTapia();
        });
    }
}
