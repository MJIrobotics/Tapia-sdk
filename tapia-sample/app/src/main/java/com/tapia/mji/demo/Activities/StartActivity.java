package com.tapia.mji.demo.Activities;


import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.Languages.Language;
import com.tapia.mji.tapialib.TapiaApp;
import com.tapia.mji.tapialib.Utils.TapiaAnimation;
import com.tapia.mji.tapialib.Utils.TapiaAudio;
import com.tapia.mji.tapialib.Utils.TapiaRobot;
import com.tapia.mji.tapialib.Utils.TapiaRobotManager;

import java.io.IOException;

/**
 * Created by Sami on 30-Jun-16.
 */
public class StartActivity extends TapiaActivity {

    TapiaAnimation tapiaAnimation;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TapiaAudio.setVolume(this, TapiaAudio.getCurrent(), false);
        TapiaApp.setCurrentLanguage(Language.LanguageID.JAPANESE);
        //TapiaApp.setCurrentLanguage(Language.LanguageID.ENGLISH_US);

        TapiaApp.ENABLE_ROBOT_FEATURE = true;

        // Add Tapia power button listener
        TapiaRobotManager manager = TapiaRobot.getManager(getApplicationContext());
        manager.setPowerButtonPushedListener(() -> {
            Toast.makeText(this, R.string.push_power_button, Toast.LENGTH_LONG).show();
        });

        startActivity(new Intent(activity, SleepActivity.class));
        WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(getApplicationContext());
        try {
            myWallpaperManager.setResource(R.raw.anim00188);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        finish();
    }
}
