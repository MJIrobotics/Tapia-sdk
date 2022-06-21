package com.tapia.mji.demo.Actions;

import com.tapia.mji.tapialib.Actions.Action;

import java.util.Date;

/**
 * Created by Sami on 08-Jul-16.
 */
public class GiveTime extends MyAction {
    public OnGiveTimeListener onGiveTimeListener;

    public GiveTime(OnGiveTimeListener giveTimeListener) {
        super(giveTimeListener);
        onGiveTimeListener = giveTimeListener;
        type = MyActionType.GIVE_TIME;
    }

    public Date getTime() {
        return onGiveTimeListener.time;
    }

    public void setTime(Date time) {
        onGiveTimeListener.time = time;
    }


    static public abstract class OnGiveTimeListener implements Action.OnActionListener {
        protected Date time;

        @Override
        public void onAction() {
            onGiveTime(time);
        }

        abstract public void onGiveTime(Date time);
    }
}