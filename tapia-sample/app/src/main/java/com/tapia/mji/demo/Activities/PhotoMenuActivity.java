package com.tapia.mji.demo.Activities;

import android.content.Intent;

import com.tapia.mji.tapialib.Activities.MenuActivity;
import com.tapia.mji.tapialib.TapiaApp;

import java.util.ArrayList;

/**
 * Created by Sami on 12-Dec-16.
 */

public class PhotoMenuActivity extends MenuActivity {
    static final int TALK = 3;
    static final int SLEEP = 2;
    static final int TAKE_PHOTO = 0;
    static final int SHOW_PHOTO = 1;
    static final int KeyBoard = 4;
    static final int ROTATION = 5;

    @Override
    public ArrayList<MenuItem> setMenuList() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(TALK, "TALK"));
        menuItems.add(new MenuItem(SLEEP, "Sleep"));//add item with an id to easily find it.
        menuItems.add(new MenuItem(TAKE_PHOTO, "take \n photo"));
        //Can also adjust the size of the text to make it fit in the bubble
        menuItems.add(new MenuItem(SHOW_PHOTO, "show my photo", 16));
        menuItems.add(new MenuItem(KeyBoard, "KeyBoard", 16));//<<追加します。
        menuItems.add(new MenuItem(ROTATION, "Rotation", 40));
        return menuItems;
    }

    @Override
    public void onItemClick(MenuItem item) {
        switch (item.id){
            case TAKE_PHOTO:
                startActivity(new Intent(TapiaApp.getAppContext(),PhotoTakeActivity.class));
                break;
            case SHOW_PHOTO:
                startActivity(new Intent(TapiaApp.getAppContext(),PhotoShowActivity.class));
                break;
            case TALK:
                startActivity(new Intent(TapiaApp.getAppContext(),TalkActivity.class));
                break;
            case SLEEP:
                finish();
                break;
            case KeyBoard:
                startActivity(new Intent(TapiaApp.getAppContext(),TestJapaneseKeyBoard.class));//<<追加します。
                break;
            case ROTATION:
                startActivity(new Intent(TapiaApp.getAppContext(), RotationActivity.class));
                break;
        }
    }

    @Override
    public void onExitClick() {
        finish();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}
