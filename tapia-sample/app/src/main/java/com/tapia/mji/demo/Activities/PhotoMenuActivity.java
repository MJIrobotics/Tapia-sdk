package com.tapia.mji.demo.Activities;

import android.content.Intent;

import com.tapia.mji.tapialib.Activities.MenuActivity;
import com.tapia.mji.tapialib.TapiaApp;

import java.util.ArrayList;

/**
 * Created by Sami on 12-Dec-16.
 */

public class PhotoMenuActivity extends MenuActivity {
    static final int TAKE_PHOTO = 0;
    static final int SHOW_PHOTO = 1;


    @Override
    public ArrayList<MenuItem> setMenuList() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        //add item with an id to easily find it.
        menuItems.add(new MenuItem(TAKE_PHOTO, "take \n photo"));
        //Can also adjust the size of the text to make it fit in the bubble
        menuItems.add(new MenuItem(SHOW_PHOTO, "show my photo", 16));
        return menuItems;
    }

    @Override
    public void onItemClick(MenuItem item) {
        switch (item.id){
            case TAKE_PHOTO:
                startActivity(new Intent(TapiaApp.appContext,PhotoTakeActivity.class));
                break;
            case SHOW_PHOTO:
                startActivity(new Intent(TapiaApp.appContext,PhotoShowActivity.class));
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
