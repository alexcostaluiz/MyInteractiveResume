package com.example.alex.interactiveresume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout tileRoot;
    CustomRoot root;
    RelativeLayout extra, award, job, academia, pdf;
    ImageView more, headshot, headshot2;
    boolean scrollInAction = false;
    boolean scrolled = false;
    boolean moreClick = false;
    boolean passToRoot = false;
    float lastY;
    float moreInitialY, moreInitialX;
    LinearLayout bottom, middle, top;
    FrameLayout nameContainer;
    TextView an, interactive, resume;
    View moreIndicator;

    float headshotY, headshot2Y, moreY, bottomY, middleY, topY, nameContainerY, anY,
            interactiveY, resumeY, indicatorY, tileRootY, tileRootFinalY;
    boolean setInitialY = false;
    float completeY, completeY2;
    float completePercent = 950f / 1280f, completePercent2 = 234f / 1280f;

    int touchSlop;
    ViewConfiguration vc;

    public static int SCREEN_HEIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;
        completeY = SCREEN_HEIGHT * completePercent;
        completeY2 = SCREEN_HEIGHT * completePercent2;

        extra = (RelativeLayout) findViewById(R.id.extra_tile);
        award = (RelativeLayout) findViewById(R.id.award_tile);
        job = (RelativeLayout) findViewById(R.id.job_tile);
        academia = (RelativeLayout) findViewById(R.id.academia_tile);
        pdf = (RelativeLayout) findViewById(R.id.pdf);

        moreIndicator = findViewById(R.id.more_indicator);
        more = (ImageView) findViewById(R.id.more_arrow);
        root = (CustomRoot) findViewById(R.id.root);
        headshot = (ImageView) findViewById(R.id.headshot);
        headshot2 = (ImageView) findViewById(R.id.headshot2);
        bottom = (LinearLayout) findViewById(R.id.bottom);
        middle = (LinearLayout) findViewById(R.id.middle);
        top = (LinearLayout) findViewById(R.id.top);
        nameContainer = (FrameLayout) findViewById(R.id.name_container);
        an = (TextView) findViewById(R.id.an);
        interactive = (TextView) findViewById(R.id.interactive);
        resume = (TextView) findViewById(R.id.resume);
        tileRoot = (RelativeLayout) findViewById(R.id.tile_root);
        tileRoot.setY(SCREEN_HEIGHT);

        vc = ViewConfiguration.get(MainActivity.this);
        touchSlop = vc.getScaledTouchSlop();
        more.setOnTouchListener(new MoreTouchListener(MainActivity.this));

        root.setOnTouchListener(new RootTouchListener(MainActivity.this));

        extra.setOnTouchListener(new TileTouchListener(MainActivity.this));
        award.setOnTouchListener(new TileTouchListener(MainActivity.this));
        job.setOnTouchListener(new TileTouchListener(MainActivity.this));
        academia.setOnTouchListener(new TileTouchListener(MainActivity.this));
        pdf.setOnTouchListener(new TileTouchListener(MainActivity.this));
    }

    @Override
    public void onBackPressed() {
        if (scrolled) {
            scrolled = false;
            headshot.animate().y(headshotY).setDuration(250).setStartDelay(0).setInterpolator(new DecelerateInterpolator()).start();
            headshot2.animate().y(headshot2Y).setDuration(250).setStartDelay(0).setInterpolator(new DecelerateInterpolator()).start();
            bottom.animate().y(bottomY).setDuration(325).setInterpolator(new DecelerateInterpolator()).start();
            middle.animate().y(middleY).setDuration(380).setInterpolator(new DecelerateInterpolator()).start();
            top.animate().y(topY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            nameContainer.animate().y(nameContainerY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            an.animate().y(anY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            interactive.animate().y(interactiveY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            resume.animate().y(resumeY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            more.animate().y(moreY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            more.animate().rotation(360f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
            tileRoot.animate().y(tileRootY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            moreIndicator.animate().y(indicatorY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
        }
        else {
            super.onBackPressed();
        }
    }
}
