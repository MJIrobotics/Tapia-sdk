package com.tapia.mji.demo.Actions;

import com.tapia.mji.tapialib.Actions.SimpleAction;

/**
 * MySimpleAction class encapsulates a variety of simple actions as public inner classes.
 * <p>
 * Many new simple actions can be handled by Tpaia by creating new inner classes here.
 * <p>
 * Note: do not forget to add new actions to the enum class in MyAction.java
 * <p>
 * Created by Sami on 21-Jul-16.
 */
public abstract class MySimpleAction extends MyAction {

    public SimpleAction.OnSimpleActionListener onSimpleActionListener;

    public MySimpleAction(SimpleAction.OnSimpleActionListener simpleActionListener, ActionType actionType) {
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
     * Created by Sami on 12-Jul-16.
     */
    public static class Bye extends MySimpleAction {
        public Bye(SimpleAction.OnSimpleActionListener simpleActionListener) {
            super(simpleActionListener, MyActionType.BYE);
        }
    }

}




