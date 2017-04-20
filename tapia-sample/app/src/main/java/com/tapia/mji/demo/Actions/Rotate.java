package com.tapia.mji.demo.Actions;

import com.tapia.mji.tapialib.Utils.TapiaRobot;

/**
 * MyAction extension for rotating tapia.
 *
 * Created by Sami on 12-Jul-16.
 */
public class Rotate extends MyAction {
    public OnRotateListener onRotateListener;

    public Rotate(OnRotateListener rotateListener){
        super(rotateListener);
        onRotateListener = rotateListener;
        type = MyActionType.ROTATE;
    }

    /**
     * Set the degree of rotation
     *
     * @param degree the degree of rotation
     */
    public void setDegree(int degree){
        onRotateListener.degree = degree;
    }


    /**
     * Accessor for the degree of rotation
     *
     * @return the degree of rotation
     */
    public int getDegree() {
        return onRotateListener.degree;
    }

    /**
     * Set the orientation of the rotation
     *
     * @param orientation the orientation of the rotation
     */
    public void setOrientation(TapiaRobot.RotateOrientation orientation){
        onRotateListener.orientation = orientation;
    }

    /**
     * Accessor for orientation
     *
     * @return the orientation of the rotation
     */
    public TapiaRobot.RotateOrientation getOrientation() {
        return onRotateListener.orientation;
    }

    /**
     * OnActionListener extension for Rotate action.
     */
    static public abstract class OnRotateListener implements MyAction.OnActionListener {
        int degree;
        TapiaRobot.RotateOrientation orientation;
        @Override
        public void onAction() {
            onRotate(orientation,degree);
        }
        abstract public void onRotate(TapiaRobot.RotateOrientation orientation, int degree);
    }
}
