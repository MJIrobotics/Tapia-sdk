package com.tapia.mji.demo.Actions;

import com.tapia.mji.tapialib.Actions.Action;

/**
 * Created by Sami on 07-Jul-16.
 */
public abstract class MyAction extends Action {
    protected MyAction(OnActionListener onActionListener){
        super(onActionListener);
    }
    enum MyEntity{
        LOCATION,
        AGE,
        DURATION,
        DATETIME,
        CONTACT,
        ORDINAL,//first , second third etc
        NUMBER,
        DISTANCE,
        TEMPERATURE,
    }

    public enum MyActionType implements ActionType {
        CONVERSATION,
        FORECAST_WEATHER,
        FORECAST_WEEK,
        PLAY_MUSIC,
        PLAY_MUSIC_LOCAL,
        GIVE_NEWS,
        GIVE_NEWS_TYPE,
        TAKE_PHOTO,
        SHOW_PHOTO,
        CALL_PHONE,
        REMIND,
        REMIND_DISPLAY,
        GET_UP,
        CONNECT_DEVICE,
        TURN_ON_SOCKET,
        TURN_OFF_SOCKET,
        ROTATE,
        GIVE_TIME,
        GIVE_DATE,
        BYE,
        WIFI_SETTING,
        BLOOD_PRESSURE_RECORD,
        BLOOD_PRESSURE_TAKE,
        START,
        IOT_SETTING,
        IOT_CONTROL,
        POSITIVE_ANSWER,
        NEGATIVE_ANSWER,
        ASK_REPEAT,
        VOLUME_UP,
        VOLUME_DOWN,
        VOLUME_MAX,
        VOLUME_MIN,
        VOLUME_DEFAULT,
        SHUTDOWN,
        CONTACT_LIST,
        SET_ALARM,
        CHECK_ALARM,
        DEL_ALARM,
        ADD_USER,
        DEL_USER,
        ADD_CONTACT,
        BACK,
        AGAIN,
        CANCEL,
        DISPLAY_REQUESTS,
        CALL_CONTACT,
        STOP,
        CONTINUE,
        TRANSLATE,
        COMPUTER_VISION,
        FACE_DEMO,
        MIO_DEMO,
        BIRTHDAY_ACTION,
        FORTUNE_TELLING,
        TURN_ON_LIGHT,
        TURN_OFF_LIGHT,
        ROCK_PAPER_SCISSORS,
        QUIZ,
        EVENT,
        WEEK_WEATHER,
        TV_MODE_CS,
        TV_MODE_BS,
        TV_MODE_TV,
        TV_VOLUME_MUTE,
        TV_VOLUME_DOWN,
        TV_VOLUME_UP,
        TV_CHANNEL_DOWN,
        TV_CHANNEL_UP,
        TV_TURN_OFF,
        TV_TURN_ON,
        ACIR
    }
}
