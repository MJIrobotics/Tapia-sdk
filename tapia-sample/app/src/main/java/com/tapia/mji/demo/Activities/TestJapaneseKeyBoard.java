package com.tapia.mji.demo.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.tapia.mji.tapialib.Activities.JapaneseKeyboardActivity;

/**
 * Created by KUBO-PC on 2017/12/20.
 */

public class TestJapaneseKeyBoard extends JapaneseKeyboardActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String registerName = sharedPreferences.getString("name", null);
        if (registerName != null) {
            setText(registerName);//画面へ表示
        }
    }

    @Override
    public void onEnter(String s) {
        sharedPreferences.edit().putString("name", s).apply();  //入力した文字をsharedPreferncesへ保存
        finish();
    }

    @Override
    public String setHintText() {
        return "入力";
    }
}
