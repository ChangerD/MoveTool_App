package com.jwding.appbase.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Jwding on 2017/8/10.
 */

public class ViewToggleUtil {
    
    private static int offest=20;

    //控件位置
    public interface Orientation {
        int Top = 1;
        int Bottom = 2;
        int Left = 3;
        int Right = 4;
    }

    public static void closeViewByT(@NonNull final View view, int orientation, int duration,@Nullable final Runnable onComplete, final boolean visibility) {
        ObjectAnimator objectAnimator = null;
        if (orientation == Orientation.Right) {
            objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0, view.getWidth() + offest);
        } else if (orientation == Orientation.Left) {
            objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0, -view.getWidth() - offest);
        } else if (orientation == Orientation.Bottom) {
            objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0, view.getHeight() + offest);
        } else if (orientation == Orientation.Top) {
            objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0, -view.getHeight() - offest);
        }
        if (objectAnimator == null) {
            return;
        }
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(!visibility){
                    view.setVisibility(View.GONE);
                }else{
                    view.setVisibility(View.VISIBLE);
                }
                if (onComplete != null)
                    view.post(onComplete);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.setDuration(duration).start();
    }

    public static void openViewByT(@NonNull final View view, int orientation,int duration, @Nullable final Runnable onComplete,boolean visibility) {
        ObjectAnimator objectAnimator = null;
        if (orientation == Orientation.Right) {
            view.setTranslationX(view.getWidth() + offest);
            objectAnimator = ObjectAnimator.ofFloat(view, "translationX", view.getWidth() + offest, 0);
        } else if (orientation == Orientation.Left) {
            view.setTranslationX(-view.getWidth() - offest);
            objectAnimator = ObjectAnimator.ofFloat(view, "translationX", -view.getWidth() - offest, 0);
        } else if (orientation == Orientation.Bottom) {
            view.setTranslationY(view.getWidth() + offest);
            objectAnimator = ObjectAnimator.ofFloat(view, "translationY", view.getHeight() + offest, 0);
        } else if (orientation == Orientation.Top) {
            view.setTranslationY(-view.getWidth() - offest);
            objectAnimator = ObjectAnimator.ofFloat(view, "translationY", -view.getHeight() - offest, 0);
        }
        if (objectAnimator == null) {
            return;
        }
        if(!visibility){
            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
        }
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (onComplete != null)
                    view.post(onComplete);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.setDuration(duration).start();
    }

}
