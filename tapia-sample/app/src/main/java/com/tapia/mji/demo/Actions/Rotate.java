package com.tapia.mji.demo.Actions;

import com.tapia.mji.tapialib.Utils.TapiaRobotManager;

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
     * @param direction the orientation of the rotation
     */

    public void setOrientation(TapiaRobotManager.Direction direction) {
        onRotateListener.direction = direction;
    }

    /**
     * Accessor for orientation
     *
     * @return the orientation of the rotation
     */
    public TapiaRobotManager.Direction getOrientation() {
        return onRotateListener.direction;
    }

    /**
     * OnActionListener extension for Rotate action.
     */
    static public abstract class OnRotateListener implements MyAction.OnActionListener {
        int degree;

        TapiaRobotManager.Direction direction;

        @Override
        public void onAction() {
            onRotate(direction, degree);
        }

        abstract public void onRotate(TapiaRobotManager.Direction direction, int degree);
    }
}
