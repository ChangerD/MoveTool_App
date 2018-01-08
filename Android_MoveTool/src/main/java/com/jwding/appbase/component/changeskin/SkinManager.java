package com.jwding.appbase.component.changeskin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.jwding.appbase.component.plug_in.ApkParseUtil;

import java.io.File;

/**
 * Created by Jwding on 2017/12/22.
 */

public class SkinManager {

    private static final SkinManager ourInstance = new SkinManager();

    public static SkinManager getInstance() {
        return ourInstance;
    }

    Resources skinResources;

    Context context;

    //包名
    String skinPackage;

    long skinResId=0;

    private SkinManager() {

    }

    public void init(Context context) {
        this.context = context;
    }

    public void loadSkin(String path) {
        File apkFile = new File(path);
        if (!apkFile.exists()) {
            return;
        }
        skinResId=System.currentTimeMillis();
        skinPackage = ApkParseUtil.getApkPackageName(context, apkFile.getAbsolutePath());
        skinResources = ApkParseUtil.getApkResources(context, apkFile.getAbsolutePath());
    }

    public int getColor(int refId) {
        if (skinResources == null) {
            return context.getResources().getColor(refId);
        }
        String name = context.getResources().getResourceEntryName(refId);
        int id = skinResources.getIdentifier(name, "color", skinPackage);
        if (id != 0) {
            int value = skinResources.getColor(id);
            return value;
        } else {
            return context.getResources().getColor(refId);
        }
    }

    public Drawable getDrawable(int refId) {
        if (skinResources == null) {
            return ContextCompat.getDrawable(context, refId);
        }
        String name = context.getResources().getResourceEntryName(refId);
        int id = skinResources.getIdentifier(name, "drawable", skinPackage);
        if (id != 0) {
            Drawable value = skinResources.getDrawable(id);
            return value;
        } else {
            return ContextCompat.getDrawable(context, refId);
        }
    }

}
