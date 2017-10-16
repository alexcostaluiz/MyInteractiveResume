package com.costa.alex.interactiveresume;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class AwardActivity extends AppCompatActivity {

    RelativeLayout toolbar;
    ScrollView scroller;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.award_layout);

        toolbar = (RelativeLayout) findViewById(R.id.award_toolbar);
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
                scroller.animate().y(y).setDuration(300).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, R.anim.slide_out_end);
    }
}
