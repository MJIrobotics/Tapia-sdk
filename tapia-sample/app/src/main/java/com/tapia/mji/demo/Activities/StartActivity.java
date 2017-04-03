package com.tapia.mji.demo.Activities;


import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;

import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.Languages.Language;
import com.tapia.mji.tapialib.TapiaApp;
import com.tapia.mji.tapialib.Utils.TapiaAnimation;
import com.tapia.mji.tapialib.Utils.TapiaAudio;
import com.tapia.mji.tapialib.Utils.TapiaResources;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Sami on 30-Jun-16.
 */
public class StartActivity extends TapiaActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TapiaAudio.setVolume(this, TapiaAudio.getCurrent(), false);
        TapiaApp.setCurrentLanguage(Language.LanguageID.JAPANESE);

        startActivity(new Intent(activity,SleepActivity.class));
        WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(getApplicationContext());
        try {
            myWallpaperManager.setResource(R.raw.anim00188);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //check if database voice are here for Hoya
        File hoyaDb = new File(Environment.getExternalStorageDirectory().getPath() + "/micro_h16");
        if(!hoyaDb.exists()) {
            TapiaResources.copyAssets(activity, "micro_h16", Environment.getExternalStorageDirectory().getPath());
        }
        finish();
    }
}
