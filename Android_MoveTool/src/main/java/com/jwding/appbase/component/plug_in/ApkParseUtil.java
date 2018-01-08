package com.jwding.appbase.component.plug_in;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Jwding on 2017/12/26.
 */

public class ApkParseUtil {

    public static Resources getApkResources(Context context, String apkPath){

        AssetManager assetManager=getApkAssetManger(apkPath);
        return new Resources(assetManager, context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());

    }

    public static String getApkPackageName(Context context,String apkPath){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo=packageManager.getPackageArchiveInfo(apkPath,PackageManager.GET_ACTIVITIES);
        if(packageInfo!=null){
            return packageInfo.packageName;
        }
        return null;
    }

    public static AssetManager getApkAssetManger(String apkPath) {
        try {
            Class assetManagerC = Class.forName("android.content.res.AssetManager");
            Method[] methods = assetManagerC.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("addAssetPath")) {
                    AssetManager assetManager = AssetManager.class.newInstance();
                    method.invoke(assetManager, apkPath);
                    return assetManager;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
