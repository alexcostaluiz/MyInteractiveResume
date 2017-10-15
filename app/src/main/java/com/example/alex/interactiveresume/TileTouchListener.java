package com.example.alex.interactiveresume;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

class TileTouchListener implements RelativeLayout.OnTouchListener {

    private MainActivity main;
    private float initialZ;
    private float finalZ;
    boolean outOfBounds = false;

    TileTouchListener(Context context) {
        this.main = (MainActivity) context;
        initialZ = main.getResources().getDimension(R.dimen.tile_elevation);
        finalZ = main.getResources().getDimension(R.dimen.tile_final_elevation);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        RelativeLayout parent = (RelativeLayout) v.getParent();
        switch (action) {
            case MotionEvent.ACTION_DOWN : {
                outOfBounds = false;
                v.animate().z(finalZ).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                parent.animate().scaleX(1.05f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                parent.animate().scaleY(1.05f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                break;
            }
            case MotionEvent.ACTION_MOVE : {
                float x = event.getRawX();
                float y = event.getRawY();
                int[] bounds = new int[2];
                v.getLocationOnScreen(bounds);
                if(x < bounds[0] || x > (bounds[0]+v.getWidth()) || y < bounds[1] || y > (bounds[1]+v.getHeight())) {
                    if(!outOfBounds) {
                        outOfBounds = true;
                        main.passToRoot = true;
                        v.animate().z(initialZ).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                        parent.animate().scaleX(1f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                        parent.animate().scaleY(1f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP : {
                if(!outOfBounds) {
                    v.animate().z(initialZ).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                    parent.animate().scaleX(1f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                    parent.animate().scaleY(1f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                    switch (v.getId()) {
                        case R.id.extra_tile :
                            Intent extraIntent = new Intent(main, ExtraActivity.class);
                            Pair<View, String> extraPrimary = new Pair<>(main.findViewById(R.id.extra_primary), "extra_primary_text");
                            Pair<View, String> extraSecondary = new Pair<>(main.findViewById(R.id.extra_primary2), "extra_secondary_text");
                            Pair<View, String> extraTertiary = new Pair<>(main.findViewById(R.id.extra_secondary), "extra_tertiary_text");
                            Pair<View, String> extraQuaternary = new Pair<>(main.findViewById(R.id.extra_secondary2), "extra_quaternary_text");
                            ActivityOptions extraOptions = ActivityOptions
                                    .makeSceneTransitionAnimation(main, extraPrimary, extraSecondary, extraTertiary, extraQuaternary);
                            main.startActivity(extraIntent, extraOptions.toBundle());
                            break;
                        case R.id.award_tile :
                            Intent awardIntent = new Intent(main, AwardActivity.class);
                            Pair<View, String> awardPrimary = new Pair<>(main.findViewById(R.id.award_primary), "award_primary_text");
                            Pair<View, String> awardSecondary = new Pair<>(main.findViewById(R.id.award_primary2), "award_secondary_text");
                            ActivityOptions awardOptions = ActivityOptions
                                    .makeSceneTransitionAnimation(main, awardPrimary, awardSecondary);
                            main.startActivity(awardIntent, awardOptions.toBundle());
                            break;
                        case R.id.job_tile :
                            Intent jobIntent = new Intent(main, JobActivity.class);
                            Pair<View, String> jobPrimary = new Pair<>(main.findViewById(R.id.job_primary), "job_primary_text");
                            Pair<View, String> jobSecondary = new Pair<>(main.findViewById(R.id.job_primary2), "job_secondary_text");
                            ActivityOptions jobOptions = ActivityOptions
                                    .makeSceneTransitionAnimation(main, jobPrimary, jobSecondary);
                            main.startActivity(jobIntent, jobOptions.toBundle());
                            break;
                        case R.id.academia_tile :
                            Intent academiaIntent = new Intent(main, AcademiaActivity.class);
                            Pair<View, String> academiaPrimary = new Pair<>(main.findViewById(R.id.academia_primary), "academia_primary_text");
                            Pair<View, String> academiaSecondary = new Pair<>(main.findViewById(R.id.academia_primary2), "academia_secondary_text");
                            ActivityOptions academiaOptions = ActivityOptions
                                    .makeSceneTransitionAnimation(main, academiaPrimary, academiaSecondary);
                            main.startActivity(academiaIntent, academiaOptions.toBundle());
                            break;
                    }
                }
                break;
            }
        }
        return true;
    }
}
