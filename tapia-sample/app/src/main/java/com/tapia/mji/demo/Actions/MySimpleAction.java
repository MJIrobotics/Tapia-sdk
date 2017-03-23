package com.tapia.mji.demo.Actions;

import com.tapia.mji.tapialib.Actions.SimpleAction;

/**
 * Created by Sami on 21-Jul-16.
 */
public abstract class MySimpleAction extends MyAction {

    public SimpleAction.OnSimpleActionListener onSimpleActionListener;
    public MySimpleAction(SimpleAction.OnSimpleActionListener simpleActionListener, ActionType actionType){
        super(simpleActionListener);
        onSimpleActionListener = simpleActionListener;
        type = actionType;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////
    ////    All simple MyAction definition
    ////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class PlayMusicLocal extends MySimpleAction {
        public PlayMusicLocal(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.PLAY_MUSIC_LOCAL);
        }
    }

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class VolumeMin extends MySimpleAction {
        public VolumeMin(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.VOLUME_MIN);
        }
    }

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class VolumeUp extends MySimpleAction {
        public VolumeUp(SimpleAction.OnSimpleActionListener wifiSettingListener){
            super(wifiSettingListener, MyActionType.VOLUME_UP);
        }
    }

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class VolumeDown extends MySimpleAction {
        public VolumeDown(SimpleAction.OnSimpleActionListener wifiSettingListener){
            super(wifiSettingListener, MyActionType.VOLUME_DOWN);
        }
    }

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class WifiSetting extends MySimpleAction {

        public WifiSetting(SimpleAction.OnSimpleActionListener wifiSettingListener){
            super(wifiSettingListener, MyActionType.WIFI_SETTING);
        }

    }

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class VolumeMax extends MySimpleAction {
        public VolumeMax(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.VOLUME_MAX);
        }
    }

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class VolumeDefault extends MySimpleAction {
        public VolumeDefault(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.VOLUME_DEFAULT);
        }
    }

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class TakePhoto extends MySimpleAction {
        public TakePhoto(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TAKE_PHOTO);
        }
    }

    /**
     * Created by Sami on 22-Jul-16.
     */
    public static class Stop extends MySimpleAction {
        public Stop(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.STOP);
        }
    }

    /**
     * Created by Sami on 22-Jul-16.
     */
    public static class Cancel extends MySimpleAction {
        public Cancel(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.CANCEL);
        }
    }

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class ShowPhoto extends MySimpleAction {
        public ShowPhoto(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.SHOW_PHOTO);
        }
    }

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class Back extends MySimpleAction {
        public Back(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.BACK);
        }
    }

    /**
     * Created by Sami on 08-Jul-16.
     */
    public static class PositiveAnswer extends MySimpleAction {
        public PositiveAnswer(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.POSITIVE_ANSWER);
        }
    }

    /**
     * Created by Sami on 08-Jul-16.
     */
    public static class NegativeAnswer extends MySimpleAction {
        public NegativeAnswer(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.NEGATIVE_ANSWER);
        }
    }

    /**
     * Created by Sami on 12-Jul-16.
     */
    public static class Bye extends MySimpleAction {
        public Bye(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.BYE);
        }
    }

    /**
     * Created by Sami on 11-Jul-16.
     */
    public static class AskRepeat extends MySimpleAction {
        public AskRepeat(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.ASK_REPEAT);
        }
    }

    /**
     * Created by Sami on 22-Jul-16.
     */
    public static class Continue extends MySimpleAction {
        public Continue(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.CONTINUE);
        }
    }

    public static class Start extends MySimpleAction {
        public Start(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.START);
        }
    }

    public static class Again extends MySimpleAction {
        public Again(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.AGAIN);
        }
    }

    public static class ComputerVision extends MySimpleAction {
        public ComputerVision(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.COMPUTER_VISION);
        }
    }

    public static class FaceDemo extends MySimpleAction {
        public FaceDemo(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.FACE_DEMO);
        }
    }

    public static class MioDemo extends MySimpleAction {
        public MioDemo(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.MIO_DEMO);
        }
    }

    public static class FortuneTelling extends MySimpleAction {
        public FortuneTelling(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.FORTUNE_TELLING);
        }
    }

    public static class TurnOnLight extends MySimpleAction {
        public TurnOnLight(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TURN_ON_LIGHT);
        }
    }

    public static class TurnOffLight extends MySimpleAction {
        public TurnOffLight(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TURN_OFF_LIGHT);
        }
    }

    public static class RockPaperScissors extends SimpleAction {
        public RockPaperScissors(OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.ROCK_PAPER_SCISSORS);
        }
    }


    public static class TVTurnOff extends MySimpleAction {
        public TVTurnOff(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TV_TURN_OFF);
        }
    }

    public static class TVTurnOn extends MySimpleAction {
        public TVTurnOn(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TV_TURN_ON);
        }
    }

    public static class TVChannelUp extends MySimpleAction {
        public TVChannelUp(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TV_CHANNEL_UP);
        }
    }

    public static class TVChannelDown extends MySimpleAction {
        public TVChannelDown(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TV_CHANNEL_DOWN);
        }
    }

    public static class TVVolumeUp extends MySimpleAction {
        public TVVolumeUp(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TV_VOLUME_UP);
        }
    }

    public static class TVVolumeDown extends MySimpleAction {
        public TVVolumeDown(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TV_VOLUME_DOWN);
        }
    }

    public static class TVVolumeMute extends MySimpleAction {
        public TVVolumeMute(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TV_VOLUME_MUTE);
        }
    }

    public static class TVModeTV extends MySimpleAction {
        public TVModeTV(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TV_MODE_TV);
        }
    }

    public static class TVModeBS extends MySimpleAction {
        public TVModeBS(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TV_MODE_BS);
        }
    }

    public static class TVModeCS extends MySimpleAction {
        public TVModeCS(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.TV_MODE_CS);
        }
    }

    public static class Quiz extends SimpleAction {
        public Quiz(OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.QUIZ);
        }
    }

    public static class Event extends SimpleAction {
        public Event(OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.EVENT);
        }
    }


}




