package com.tapia.mji.demo.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.tapia.mji.tapialib.Actions.Action;

import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Providers.Interfaces.TTSProvider;
import com.tapia.mji.tapialib.TapiaApp;
import com.tapia.mji.tapialib.Utils.CameraHelper;
import com.tapia.mji.tapialib.Activities.TapiaActivity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sami on 08-Jul-16.
 */
public class PhotoTakeActivity extends TapiaActivity {
    Handler mHandler;
    SurfaceView previewView;
    ImageView countdownView;

    List<Action> actionListRepeat;



    static final int SAVE_MODE = 0;
    static final int REPEAT_MODE = 1;
    int mode = -1;

    @Override
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);

        ttsProvider         = TapiaApp.currentLanguage.getTTSProvider();

        countdownView = (ImageView) findViewById(R.id.countdownView);
        previewView = (SurfaceView) findViewById(R.id.previewView);

        actionListRepeat = new ArrayList<>();


        findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.yes).setVisibility(View.INVISIBLE);
                findViewById(R.id.no).setVisibility(View.INVISIBLE);
                if(mode == SAVE_MODE){
                    CameraHelper.saveLastPicture();
                    ttsProvider.say(getString(R.string.photo_dont_understand_repeat0));
                    ttsProvider.setOnSpeechCompleteListener(new TTSProvider.OnSpeechCompleteListener() {
                        @Override
                        public void onSpeechComplete() {
                            mode = REPEAT_MODE;
                            findViewById(R.id.yes).setVisibility(View.VISIBLE);
                            findViewById(R.id.no).setVisibility(View.VISIBLE);
                            ttsProvider.setOnSpeechCompleteListener(null);
                        }
                    });
                }
                else if (mode == REPEAT_MODE){
                    recreate();
                }
            }
        });
        findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                findViewById(R.id.yes).setVisibility(View.INVISIBLE);
                findViewById(R.id.no).setVisibility(View.INVISIBLE);
                if(mode == SAVE_MODE){
                    ttsProvider.say(getString(R.string.photo_ask_repeat0));
                    ttsProvider.setOnSpeechCompleteListener(new TTSProvider.OnSpeechCompleteListener() {
                        @Override
                        public void onSpeechComplete() {
                            mode = REPEAT_MODE;
                            findViewById(R.id.yes).setVisibility(View.VISIBLE);
                            findViewById(R.id.no).setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if (mode == REPEAT_MODE){
                    finish();
                }
            }
        });



        CameraHelper.startCamera(previewView.getHolder());
        mHandler = new Handler();
        ttsProvider.say(getString(R.string.photo_ready0));
        ttsProvider.setOnSpeechCompleteListener(new TTSProvider.OnSpeechCompleteListener() {
            @Override
            public void onSpeechComplete() {
                sayDelayed(getString(R.string.photo_three0), 1000);
                changedDrawableDalayed(R.drawable.take3,1000);
                sayDelayed(getString(R.string.photo_two0),   2000);
                changedDrawableDalayed(R.drawable.take2,2000);
                sayDelayed(getString(R.string.photo_one0),   3000);
                changedDrawableDalayed(R.drawable.take1,3000);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ttsProvider.say(getString(R.string.photo_cheese0));
                        ttsProvider.setOnSpeechCompleteListener(new TTSProvider.OnSpeechCompleteListener() {
                            @Override
                            public void onSpeechComplete() {
                                countdownView.setVisibility(View.GONE);
                                //take picture
                                CameraHelper.setOnPictureTokenListener(new CameraHelper.OnPictureTokenListener() {
                                    @Override
                                    public void onPictureToken(byte[] data, String errorMsg) {
                                        ttsProvider.say(getString(R.string.photo_ask_save0));
                                        ttsProvider.setOnSpeechCompleteListener(new TTSProvider.OnSpeechCompleteListener() {
                                            @Override
                                            public void onSpeechComplete() {
                                                ttsProvider.setOnSpeechCompleteListener(null);
                                                mode = SAVE_MODE;
                                                findViewById(R.id.yes).setVisibility(View.VISIBLE);
                                                findViewById(R.id.no).setVisibility(View.VISIBLE);
                                            }
                                        });

                                    }
                                });
                                CameraHelper.takePicture();
                            }
                        });
                    }
                }, 4000);
                ttsProvider.setOnSpeechCompleteListener(null);
            }
        });
    }


    void sayDelayed(final String speech, int delay){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ttsProvider.say(speech);
            }
        },delay);
    }

    void changedDrawableDalayed(final int drawableID, int delay){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                countdownView.setImageResource(drawableID);
                countdownView.invalidate();
            }
        },delay);
    }
}
