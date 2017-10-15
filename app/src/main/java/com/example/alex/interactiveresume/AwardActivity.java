package com.example.alex.interactiveresume;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class AwardActivity extends AppCompatActivity {

    RelativeLayout toolbar;
    LinearLayout awardContainer;
    ScrollView scroller;
    ImageView back;
    float lastY, containerY, containerY2;
    boolean initialSet = false;
    boolean activityLoaded = false;

    float multiplier(float y, float initialY, float finalY) {
        if(y < initialY) {
            return 0.4f;
        }
        float m = (0.3f-0.5f)/(finalY-initialY);
        float b = 0.5f - (m*initialY);
        return (m*y)+b;
    }

    float multiplier2(float y, float initialY, float finalY) {
        if(y > initialY) {
            return 1f;
        }
        float dy = initialY - finalY;
        float multi = (y - finalY)/dy;
        if(multi > 0.1f) {
            return multi;
        }
        else return 0.1f;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.award_layout);

        //yes
        toolbar = (RelativeLayout) findViewById(R.id.award_toolbar);
        awardContainer = (LinearLayout) findViewById(R.id.award_container);
        scroller = (ScrollView) findViewById(R.id.award_scrollview);
        scroller.setY(MainActivity.SCREEN_HEIGHT-50);
        back = (ImageView) findViewById(R.id.award_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, R.anim.slide_out_end);
            }
        });

        /*awardContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(activityLoaded) {
                    int action = event.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_DOWN :
                            if(v.getAnimation() != null) {
                                v.getAnimation().cancel();
                            }
                            if(!initialSet) {
                                initialSet = true;
                                containerY = v.getY();
                                containerY2 = ((MainActivity.SCREEN_HEIGHT-containerY)*0.4f)+containerY;
                            }
                            lastY = event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE :
                            float y = event.getRawY();
                            float dy = y - lastY;
                            if(dy > 0) {
                                v.setY(v.getY() + (dy*multiplier(v.getY(), containerY, containerY2)));
                            }
                            else {
                                v.setY(v.getY() + (dy*0.4f));
                            }
                            lastY = y;
                            break;
                        case MotionEvent.ACTION_UP :
                            if(v.getY() != containerY) {
                                v.animate().y(containerY).setDuration(500).setInterpolator(new DecelerateInterpolator(2)).start();
                            }
                            break;
                        case MotionEvent.ACTION_CANCEL :
                            if(v.getY() != containerY) {
                                v.animate().y(containerY).setDuration(500).setInterpolator(new DecelerateInterpolator(2)).start();
                            }
                            break;
                    }
                    return true;
                }
                else return false;
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        toolbar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                Animator revealToolbar = ViewAnimationUtils
                        .createCircularReveal(toolbar, toolbar.getWidth()/2, toolbar.getHeight()/2, 0, toolbar.getWidth()/2);
                revealToolbar.setDuration(500);
                revealToolbar.setInterpolator(new DecelerateInterpolator());
                revealToolbar.start();
            }
        });

        scroller.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                float y = toolbar.getY() + toolbar.getHeight();
                scroller.animate().y(y).setDuration(300).setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                        .setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                activityLoaded = true;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }).start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, R.anim.slide_out_end);
    }
}
