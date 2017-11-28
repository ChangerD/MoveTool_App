package com.topxgun.appbase.component.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 定制Toast封装
 * JWDING
 */
public class CustomToast {
	private static Toast mToast;
	private static Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			int time  = msg.what - 500;
			if(time > 0) {
				mToast.show();
				mHandler.sendEmptyMessageDelayed(time, 500);
			}else{
				mToast.cancel();
			}
		}
	};
	private static Runnable run = new Runnable() {
		public void run() {
			mToast.cancel();
		}
	};

	public static void showBottomToast(Context context,String text){
		showBottomToast(context,text,1000);
	}

	public static void showTopToast(Context mContext, String text) {
		showTopToast(mContext.getApplicationContext(), text, 1000);
	}

	public static void showTopToast(Context mContext, String text, int duration) {
		if (mContext == null && text != null) {
			return;
		}
		mContext = mContext.getApplicationContext();
		mHandler.removeCallbacks(run);
		if (mToast == null) {
			mToast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
		}
		mToast.setText(text);
		if (duration == Toast.LENGTH_SHORT) {
			duration = 1000;
		} else if (duration == Toast.LENGTH_LONG) {
			duration = 2000;
		}
		mToast.setGravity(Gravity.TOP | Gravity.CENTER, 0, (int)dip2px(mContext, 100));
		mToast.show();
		mHandler.removeCallbacksAndMessages(null);
		mHandler.sendEmptyMessageDelayed(duration, 500);
//		mHandler.postDelayed(run, duration);
	}

	public static void showBottomToast(Context mContext, String text, int duration) {
		if (mContext == null && text != null) {
			return;
		}
		mContext = mContext.getApplicationContext();
		mHandler.removeCallbacks(run);
		if (mToast == null) {
			mToast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
		}
		mToast.setText(text);
		if (duration == Toast.LENGTH_SHORT) {
			duration = 1000;
		} else if (duration == Toast.LENGTH_LONG) {
			duration = 2000;
		}
		mToast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, (int)dip2px(mContext, 100));
		mToast.show();
		mHandler.removeCallbacksAndMessages(null);
		mHandler.sendEmptyMessageDelayed(duration, 500);
	}

	public static void showTopToast(Context mContext, int resId) {
		showTopToast(mContext, mContext.getResources().getString(resId), 1000);
	}

	public static void showTopToast(Context mContext, int resId, int duration) {
		showTopToast(mContext, mContext.getResources().getString(resId), duration);
	}

	public static float dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return dpValue * scale;
	}

}
