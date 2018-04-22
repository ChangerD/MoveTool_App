package com.jwding.appbase.component.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.topxgun.appbase.R;

/**
 * 定制Toast封装
 * JWDING
 */
public class CustomToast {

    private static Toast mToast;
    private static Toast mCustomToast;

    public static void showTopToast(Context mContext, String text) {
        showTopToast(mContext.getApplicationContext(), text, 1000);
    }

    public static void showCenterToast(Context mContext, String text) {
        showCenterToast(mContext.getApplicationContext(), text, 1000);
    }

    public static void showBottomToast(Context context, String text) {
        showBottomToast(context, text, 1000);
    }

    public static void showTopToast(Context mContext, String text, int duration) {
        showToastCustomLayout(mContext, text, duration, Gravity.TOP | Gravity.CENTER, 0, (int) dip2px(mContext, 100), 0, 0);
    }

    public static void showCenterToast(Context mContext, String text, int duration) {
        showToastCustomLayout(mContext, text, duration, Gravity.CENTER, 0, 0, 0, 0);
    }

    public static void showBottomToast(Context mContext, String text, int duration) {
        showToastCustomLayout(mContext, text, duration, Gravity.BOTTOM | Gravity.CENTER, 0, (int) dip2px(mContext, 100), 0, 0);
    }

    private static int layoutId;
    private static int messageId;
    private static boolean customized = false;
    private static Context appContext;

    public static void initCustomToast(Context mContext, int layoutid, int messageid) {
        appContext = mContext.getApplicationContext();
        layoutId = layoutid;
        messageId = messageid;
        customized = true;
    }

    public static void showCustomToast(String text, int duration) {
        if (customized) {
            showToastCustomLayout(appContext, text, duration, Gravity.CENTER, 0, 0, layoutId, messageId);
        }
    }

    public static void showToastCustomLayout(Context mContext, String text, int duration, int gravity, int xoffest, int yoffest, int layoutid, int messageid) {
        if (mContext == null || TextUtils.isEmpty(text)) {
            return;
        }
        mToast = Toast.makeText(mContext.getApplicationContext(), "", Toast.LENGTH_SHORT);
        mCustomToast = Toast.makeText(mContext.getApplicationContext(), "", Toast.LENGTH_SHORT);
        boolean isCustom = false;
        if (layoutid > 0 & messageid > 0) {
            View layout = LayoutInflater.from(mContext).inflate(layoutid, null);
            if (layout != null) {
                TextView textTV = (TextView) layout.findViewById(messageid);
                if (textTV != null) {
                    textTV.setText(text);
                    mCustomToast.setView(layout);
                    isCustom = true;
                }
            }
        }
        if (!isCustom) {
            mToast.setText(text);
            mToast.setGravity(gravity, xoffest, yoffest);
            mToast.setDuration(duration);
            mToast.show();
        } else {
            mCustomToast.setGravity(gravity, xoffest, yoffest);
            mCustomToast.setDuration(duration);
            mCustomToast.show();
        }
    }

    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }

}
