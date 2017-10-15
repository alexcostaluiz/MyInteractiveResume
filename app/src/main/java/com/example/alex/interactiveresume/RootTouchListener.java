package com.example.alex.interactiveresume;

import android.animation.Animator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

class RootTouchListener implements RelativeLayout.OnTouchListener {

    private MainActivity main;

    RootTouchListener(Context context) {
        this.main = (MainActivity) context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                main.lastY = event.getRawY();
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
                main.moreIndicator.animate().scaleX(1).setDuration(150).setInterpolator(new OvershootInterpolator()).start();
                main.moreIndicator.animate().scaleY(1).setDuration(150).setInterpolator(new OvershootInterpolator()).start();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                final float y = event.getRawY();
                float dy = y - main.lastY;
                if (!main.scrolled && !main.scrollInAction) {
                    if (dy < 0 || (dy > 0 && main.top.getY() < main.topY)) {
                        main.headshot.setY(main.headshot.getY() + (dy * .2f));
                        main.headshot2.setY(main.headshot2.getY() + (dy * .2f));
                        main.bottom.setY(main.bottom.getY() + dy);
                        main.middle.setY(main.middle.getY() + (dy * 1.2f));
                        main.top.setY(main.top.getY() + (dy * .8f));
                        main.nameContainer.setY(main.nameContainer.getY() + (dy * .8f));
                        main.an.setY(main.an.getY() + (dy * .8f));
                        main.interactive.setY(main.interactive.getY() + (dy * .8f));
                        main.resume.setY(main.resume.getY() + (dy * .8f));
                        main.more.setY(main.more.getY() + (dy * .8f));
                        main.moreIndicator.setY(main.moreIndicator.getY() + (dy * .8f));
                        main.tileRoot.setY(main.tileRoot.getY() + (dy * .8f));
                        if (main.more.getY() < main.completeY && !main.scrollInAction) {
                            main.scrolled = true;
                            main.scrollInAction = true;
                            main.headshot.animate().y(main.headshotY - MainActivity.SCREEN_HEIGHT).setDuration(300).setStartDelay(50).setInterpolator(new DecelerateInterpolator()).start();
                            main.headshot2.animate().y(main.headshot2Y - MainActivity.SCREEN_HEIGHT).setDuration(300).setStartDelay(50).setInterpolator(new DecelerateInterpolator()).start();
                            main.bottom.animate().y(main.bottomY - MainActivity.SCREEN_HEIGHT).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                            main.middle.animate().y(main.middleY - MainActivity.SCREEN_HEIGHT).setDuration(300).setInterpolator(new DecelerateInterpolator()).start();
                            main.top.animate().y(main.topY - MainActivity.SCREEN_HEIGHT).setDuration(400).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    main.scrollInAction = false;
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            }).start();
                            main.nameContainer.animate().y(main.nameContainerY - MainActivity.SCREEN_HEIGHT).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                            main.an.animate().y(main.anY - MainActivity.SCREEN_HEIGHT).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                            main.interactive.animate().y(main.interactiveY - MainActivity.SCREEN_HEIGHT).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                            main.resume.animate().y(main.resumeY - MainActivity.SCREEN_HEIGHT).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                            main.more.animate().y(main.moreY - (MainActivity.SCREEN_HEIGHT * .9f)).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                            main.more.animate().rotation(180f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                            main.moreIndicator.animate().y(main.indicatorY - (MainActivity.SCREEN_HEIGHT * .9f)).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                            main.tileRoot.animate().y(main.tileRootFinalY).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                            main.moreIndicator.animate().scaleX(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                            main.moreIndicator.animate().scaleY(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                        }
                    }

                } else if (main.scrolled && !main.scrollInAction) {
                    if (dy > 0 || (dy < 0 && main.top.getY() > (main.topY - MainActivity.SCREEN_HEIGHT))) {
                        main.headshot.setY(main.headshot.getY() + (dy));
                        main.headshot2.setY(main.headshot2.getY() + (dy));
                        main.bottom.setY(main.bottom.getY() + (dy * .8f));
                        main.middle.setY(main.middle.getY() + dy);
                        main.top.setY(main.top.getY() + (dy * .8f));
                        main.nameContainer.setY(main.nameContainer.getY() + (dy * .8f));
                        main.an.setY(main.an.getY() + (dy * .8f));
                        main.interactive.setY(main.interactive.getY() + (dy * .8f));
                        main.resume.setY(main.resume.getY() + (dy * .8f));
                        main.more.setY(main.more.getY() + (dy * .8f));
                        main.moreIndicator.setY(main.moreIndicator.getY() + (dy * .8f));
                        main.tileRoot.setY(main.tileRoot.getY() + (dy * .8f));
                        if (main.more.getY() > main.completeY2 && !main.scrollInAction) {
                            main.scrolled = false;
                            main.scrollInAction = true;
                            main.headshot.animate().y(main.headshotY).setDuration(250).setStartDelay(0).setInterpolator(new DecelerateInterpolator()).start();
                            main.headshot2.animate().y(main.headshot2Y).setDuration(250).setStartDelay(0).setInterpolator(new DecelerateInterpolator()).start();
                            main.bottom.animate().y(main.bottomY).setDuration(325).setInterpolator(new DecelerateInterpolator()).start();
                            main.middle.animate().y(main.middleY).setDuration(380).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    main.scrollInAction = false;
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            }).start();
                            main.top.animate().y(main.topY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                            main.nameContainer.animate().y(main.nameContainerY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                            main.an.animate().y(main.anY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                            main.interactive.animate().y(main.interactiveY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                            main.resume.animate().y(main.resumeY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                            main.more.animate().y(main.moreY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                            main.more.animate().rotation(360f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
                            main.tileRoot.animate().y(main.tileRootY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                            main.moreIndicator.animate().y(main.indicatorY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                            main.moreIndicator.animate().scaleX(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                            main.moreIndicator.animate().scaleY(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                        }
                    }
                }
                main.lastY = y;
                break;
            }
            case MotionEvent.ACTION_UP: {
                if (!main.scrollInAction) {
                    if (main.scrolled) {
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
                        main.tileRoot.animate().y(main.tileRootFinalY).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                        main.moreIndicator.animate().y(main.indicatorY - (MainActivity.SCREEN_HEIGHT * .9f)).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                        main.moreIndicator.animate().scaleX(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                        main.moreIndicator.animate().scaleY(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                    } else {
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
                        main.tileRoot.animate().y(main.tileRootY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        main.moreIndicator.animate().y(main.indicatorY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                        main.moreIndicator.animate().scaleX(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                        main.moreIndicator.animate().scaleY(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                    }
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                if (main.scrolled) {
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
                    main.tileRoot.animate().y(main.tileRootFinalY).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                    main.moreIndicator.animate().y(main.indicatorY - (MainActivity.SCREEN_HEIGHT * .9f)).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
                    main.moreIndicator.animate().scaleX(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                    main.moreIndicator.animate().scaleY(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                } else {
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
                    main.tileRoot.animate().y(main.tileRootY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                    main.moreIndicator.animate().y(main.indicatorY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
                    main.moreIndicator.animate().scaleX(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                    main.moreIndicator.animate().scaleY(0).setDuration(300).setInterpolator(new AnticipateInterpolator()).start();
                }
                break;
            }
        }
        return true;
    }
}
