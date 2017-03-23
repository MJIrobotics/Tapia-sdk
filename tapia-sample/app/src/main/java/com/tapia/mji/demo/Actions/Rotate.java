package com.tapia.mji.demo.Actions;

/**
 * Created by Sami on 12-Jul-16.
 */
public class Rotate extends MyAction {
    public OnRotateListener onRotateListener;

    public Rotate(OnRotateListener rotateListener){
        super(rotateListener);
        onRotateListener = rotateListener;
        type = MyActionType.ROTATE;
    }

    public void setDegree(int degree){
        onRotateListener.degree = degree;
    }

    public int getDegree() {
        return onRotateListener.degree;
    }

    public void setOrientation(int orientation){
        onRotateListener.orientation = orientation;
    }

    public int getOrientation() {
        return onRotateListener.orientation;
    }
    static public abstract class OnRotateListener implements MyAction.OnActionListener {
        int degree;
        int orientation;
        @Override
        public void onAction() {
            onRotate(orientation,degree);
        }
        abstract public void onRotate(int orientation,int degree);
    }
}
