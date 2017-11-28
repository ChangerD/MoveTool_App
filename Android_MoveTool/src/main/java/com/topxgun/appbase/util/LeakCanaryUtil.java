package com.topxgun.appbase.util;

import android.content.Context;

/**
 * Created by Jwding on 2017/8/9.
 */

public class LeakCanaryUtil {

    private static LeakCanaryUtil mInstance;
    private Context mContext;

    private LeakCanaryUtil(Context context) {
        this.mContext = context;
    }

    public static LeakCanaryUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LeakCanaryUtil.class) {
                if (mInstance == null) {
                    mInstance = new LeakCanaryUtil(context);
                }
            }
        }
        return mInstance;
    }

    public void testLeak() {
    }

}
