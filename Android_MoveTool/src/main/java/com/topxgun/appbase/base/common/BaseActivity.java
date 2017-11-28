package com.topxgun.appbase.base.common;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class BaseActivity extends AppCompatActivity implements OnClickListener {

    protected final static String TAG = "BaseActivity";

    protected Context mContext;
    /**
     * 当前界面是否可见
     */
    protected boolean isShow = false;

    public boolean isShow(){
        return isShow;
    }
    /**
     * Activity切换是否有动画
     */
    protected boolean hasSwitchAnim = true;
    /**
     * Activity是否关闭
     */
    protected boolean isFinished = false;

    public boolean isFinished() {
        return isFinished;
    }

    protected Toolbar mToolbar;

    protected Handler mHandler = new MyHandler(this);

    protected void handleMessaged(Message msg) {

    }

    /**
     * 避免Handler引起的内存泄露
     */
    public static class MyHandler extends Handler {
        private final WeakReference<BaseActivity> mActivity;

        public MyHandler(BaseActivity activity) {

            mActivity = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity baseActivity = mActivity.get();
            if (baseActivity == null) {
                return;
            }
            if (!baseActivity.isFinished()) {
                baseActivity.handleMessaged(msg);
            } else {
                removeMessages(msg.what);
                Log.e(TAG, "handleMessage方法中----->：msg.obj为：" + msg.obj);
                Log.e(TAG, "handleMessage方法中-------->：acty为：" + baseActivity.getClass().getSimpleName());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
        isFinished = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isShow = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isShow = true;
    }


    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        Log.i(TAG, "BaseActivity中：onRestoreInstanceState方法-------------->" + bundle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "BaseActivity中：onRestoreInstanceState方法-------------->" + outState);
    }

    @Override
    protected void onDestroy() {
        isFinished = true;
        mContext = null;
        mTag = null;
        if (mTags != null) {
            mTags.clear();
            mTags = null;
        }
        super.onDestroy();
        System.gc();
    }

    @Override
    public void finish() {
        finish(true);
    }

    /**
     * 进入此acty是否有动画
     */
    protected boolean hasSwitchAnim() {
        return hasSwitchAnim;
    }

    /**
     * 进入此acty是否有动画
     */
    protected boolean hasSwitchAnim(Boolean hasSwitchAnim) {
        return this.hasSwitchAnim = hasSwitchAnim;
    }

    /**
     * 设置切换动画
     */
    protected void setSwitchAnim(int enterAnim, int exitAnim) {
        if (hasSwitchAnim()) {
            overridePendingTransition(enterAnim, exitAnim);
        }
    }

    public void finish(boolean hasAnim) {
            isFinished = true;
            // 删除所有消息
            mHandler.removeCallbacksAndMessages(null);
            super.finish();
    }

    private Object mTag;
    /**
     * SparseArray性能比HashMap性能高但不能被序列化，有些item标记后无法在acty页面间来回传值
     * http://blog.csdn.net/xyz_fly/article/details/7931943
     */
    // private SparseArray<Object> mTags;
    private HashMap<Integer, Object> mTags;

    public Object getTag() {
        return mTag;
    }

    public void setTag(Object tag) {
        mTag = tag;
    }

    public Object getTag(int key) {
        return (mTags == null) ? null : mTags.get(key);
    }

    public void setTag(int key, Object tag) {
        if (mTags == null) {
            // mTags = new SparseArray<Object>();
            mTags = new HashMap<Integer, Object>();
        }
        mTags.put(key, tag);
    }

    @Override
    public void onClick(View v) {

    }

}