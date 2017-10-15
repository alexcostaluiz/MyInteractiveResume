package com.example.alex.interactiveresume;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

class MoreTouchListener implements ImageView.OnTouchListener {

    private MainActivity main;

    MoreTouchListener(Context context) {
        this.main = (MainActivity) context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                main.moreClick = true;
                if (!main.setInitialY) {
                    main.setInitialY = true;
                    main.headshotY = main.headshot.getY();
                    main.headshot2Y = main.headshot2.getY();
                    main.moreY = main.more.getY();
                    main.indicatorY = main.moreIndicator.getY();
                    main.bottomY = main.bottom.getY();
                    main.topY = main.top.getY();
                    main.middleY = main.middle.getY();
                    main.nameContainerY = main.nameContainer.getY();
                    main.anY = main.an.getY();
                    main.interactiveY = main.interactive.getY();
                    main.resumeY = main.resume.getY();
                    main.tileRootFinalY = (MainActivity.SCREEN_HEIGHT / 2f) - (main.tileRoot.getHeight() / 2f);
                    main.tileRoot.setY(MainActivity.SCREEN_HEIGHT + main.tileRootFinalY);
                    main.tileRootY = main.tileRoot.getY();
                }
                main.moreIndicator.animate().scaleX(1).setDuration(300).setInterpolator(new OvershootInterpolator()).start();
                main.moreIndicator.animate().scaleY(1).setDuration(300).setInterpolator(new OvershootInterpolator()).start();
                main.moreInitialX = event.getX();
                main.moreInitialY = event.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (!main.moreClick) {
                    return true;
                }
                float x = event.getX();
                float y = event.getY();
                float dx = Math.abs(x - main.moreInitialX);
                float dy = Math.abs(y - main.moreInitialY);
                if ((dy > main.touchSlop || dx > main.touchSlop) && main.moreClick) {
                    main.moreClick = false;
                    main.moreIndicator.animate().scaleX(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                    main.moreIndicator.animate().scaleY(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (!main.moreClick) {
                    return true;
                }
                main.moreClick = false;
                float x = event.getX();
                float y = event.getY();
                float dx = Math.abs(x - main.moreInitialX);
                float dy = Math.abs(y - main.moreInitialY);
                if (dy < main.touchSlop && dx < main.touchSlop) {
                    main.moreIndicator.animate().scaleX(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                    main.moreIndicator.animate().scaleY(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                    if (main.scrolled) {
                        main.scrolled = false;
                        main.headshot.animate().y(main.headshotY).setDuration(250).setStartDelay(0).setInterpolator(new DecelerateInterpolator()).start();
                        main.headshot2.animate().y(main.headshot2Y).setDuration(250).setStartDelay(0).setInterpolator(new DecelerateInterpolator()).start();
                        main.bottom.animate().y(main.bottomY).setDuration(325).setInterpolator(new DecelerateInterpolator()).start();
                        main.middle.animate().y(main.middleY).setDuration(380).setInterpolator(new DecelerateInterpolator()).start();
                        main.top.animate().y(main.topY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        main.nameContainer.animate().y(main.nameContainerY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        main.an.animate().y(main.anY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        main.interactive.animate().y(main.interactiveY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        main.resume.animate().y(main.resumeY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        main.more.animate().y(main.moreY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        main.more.animate().rotationBy(180f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                        main.moreIndicator.animate().y(main.indicatorY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        main.tileRoot.animate().y(main.tileRootY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                    } else {
                        main.scrolled = true;
                        main.headshot.animate().y(main.headshotY - MainActivity.SCREEN_HEIGHT).setDuration(300).setStartDelay(50).setInterpolator(new DecelerateInterpolator()).start();
                        main.headshot2.animate().y(main.headshot2Y - MainActivity.SCREEN_HEIGHT).setDuration(300).setStartDelay(50).setInterpolator(new DecelerateInterpolator()).start();
                        main.bottom.animate().y(main.bottomY - MainActivity.SCREEN_HEIGHT).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        main.middle.animate().y(main.middleY - MainActivity.SCREEN_HEIGHT).setDuration(300).setInterpolator(new DecelerateInterpolator()).start();
                        main.top.animate().y(main.topY - MainActivity.SCREEN_HEIGHT).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                        main.nameContainer.animate().y(main.nameContainerY - MainActivity.SCREEN_HEIGHT).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                        main.an.animate().y(main.anY - MainActivity.SCREEN_HEIGHT).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                        main.interactive.animate().y(main.interactiveY - MainActivity.SCREEN_HEIGHT).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                        main.resume.animate().y(main.resumeY - MainActivity.SCREEN_HEIGHT).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                        main.more.animate().y(main.moreY - (MainActivity.SCREEN_HEIGHT * .9f)).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                        main.more.animate().rotationBy(180f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                        main.moreIndicator.animate().y(main.indicatorY - (MainActivity.SCREEN_HEIGHT * .9f)).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                        main.tileRoot.animate().y(main.tileRootFinalY).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                    }
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                main.moreClick = false;
                if (main.moreIndicator.getScaleX() != 0) {
                    main.moreIndicator.animate().scaleX(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                    main.moreIndicator.animate().scaleY(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                }
                break;
            }
        }
        return true;
    }
}
