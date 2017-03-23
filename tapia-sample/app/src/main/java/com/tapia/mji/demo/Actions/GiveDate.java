package com.tapia.mji.demo.Actions;

import com.tapia.mji.tapialib.Actions.Action;

import java.util.Date;

/**
 * Created by Sami on 08-Jul-16.
 */
public class GiveDate extends MyAction {
    public OnGiveDateListener onGiveDateListener;
    public GiveDate(OnGiveDateListener giveDateListener){
        super(giveDateListener);
        onGiveDateListener = giveDateListener;
        type = MyActionType.GIVE_DATE;
    }

    public Date getMood(){
        return onGiveDateListener.date;
    }
    public void setDate(Date date){
        onGiveDateListener.date = date;
    }


    static public abstract class OnGiveDateListener implements Action.OnActionListener {
        protected Date date;
        @Override
        public void onAction() {
            onGiveDate(date);
        }
        abstract public void onGiveDate(Date date);
    }
}
