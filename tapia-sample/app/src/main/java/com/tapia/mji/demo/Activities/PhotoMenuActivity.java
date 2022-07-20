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
    static final int KEY_BOARD = 4;
    static final int ROTATION = 5;
    static final int CONTROL = 6;

    @Override
    public ArrayList<MenuItem> setMenuList() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(TALK, "Talk"));
        menuItems.add(new MenuItem(SLEEP, "Sleep"));//add item with an id to easily find it.
        menuItems.add(new MenuItem(TAKE_PHOTO, "Take \n photo"));
        //Can also adjust the size of the text to make it fit in the bubble
        menuItems.add(new MenuItem(SHOW_PHOTO, "Show \nmy photo", 40));
        menuItems.add(new MenuItem(KEY_BOARD, "Keyboard", 40));//<<追加します。
        menuItems.add(new MenuItem(ROTATION, "Rotation", 40));
        menuItems.add(new MenuItem(CONTROL, "Control", 40));
        return menuItems;
    }

    @Override
    public void onItemClick(MenuItem item) {
        switch (item.id) {
            case TAKE_PHOTO:
                startActivity(new Intent(TapiaApp.getAppContext(), PhotoTakeActivity.class));
                break;
            case SHOW_PHOTO:
                startActivity(new Intent(TapiaApp.getAppContext(), PhotoShowActivity.class));
                break;
            case TALK:
                startActivity(new Intent(TapiaApp.getAppContext(), TalkActivity.class));
                break;
            case SLEEP:
                finish();
                break;
            case KEY_BOARD:
                startActivity(new Intent(TapiaApp.getAppContext(), TestJapaneseKeyBoard.class));//<<追加します。
                break;
            case ROTATION:
                startActivity(new Intent(TapiaApp.getAppContext(), RotationActivity.class));
                break;
            case CONTROL:
                startActivity(new Intent(TapiaApp.getAppContext(), ControlActivity.class));
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
