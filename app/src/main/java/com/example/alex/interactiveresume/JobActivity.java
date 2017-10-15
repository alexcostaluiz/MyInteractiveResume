package com.example.alex.interactiveresume;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class JobActivity extends AppCompatActivity {

    RelativeLayout toolbar;
    LinearLayout jobContainer;
    ImageView back;
    boolean activityLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_layout);

        toolbar = (RelativeLayout) findViewById(R.id.job_toolbar);
        jobContainer = (LinearLayout) findViewById(R.id.job_container);
        back = (ImageView) findViewById(R.id.job_back);
        jobContainer.setY(MainActivity.SCREEN_HEIGHT-50);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, R.anim.slide_out_end);
            }
        });
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

        jobContainer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                float y = toolbar.getY() + toolbar.getHeight() + getResources().getDimension(R.dimen.margin);
                jobContainer.animate().y(y).setDuration(300).setStartDelay(100).setInterpolator(new DecelerateInterpolator())
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
