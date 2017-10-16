package com.costa.alex.interactiveresume;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

public class CustomRoot extends RelativeLayout {

    MainActivity main;

    public CustomRoot(Context context) {
        this(context, null);
    }

    public CustomRoot(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRoot(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomRoot(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setInstance(MainActivity context) {
        main = context;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (main.passToRoot) {
            main.passToRoot = false;
            main.lastY = event.getRawY();
            main.moreIndicator.animate().scaleX(1).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
            main.moreIndicator.animate().scaleY(1).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
            return true;
        }
        return false;
    }
}
