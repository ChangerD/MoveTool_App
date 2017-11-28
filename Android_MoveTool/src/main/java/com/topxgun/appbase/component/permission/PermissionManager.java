package com.topxgun.appbase.component.permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by Jwding on 2017/8/12.
 */

public class PermissionManager {

    // **************** Android M Permission (Android 6.0权限控制代码封装)
    // *****************************************************
    private static final int PERMISSION_REQUEST_CODE = 88;
    private PermissionCallback permissionCallback;
    private AlertDialog myDialog;

    public interface PermissionCallback {

        void hasPermission();

        void noPermission();
    }

    /**
     * Android M运行时权限请求封装
     *
     * @param permissionDes      权限描述
     * @param permissionCallback 请求权限回调
     * @param permissions        请求的权限（数组类型），直接从Manifest中读取相应的值，比如Manifest.permission.WRITE_CONTACTS
     */
    public void requestPermission(Activity activity,String permissionDes, PermissionCallback permissionCallback, String... permissions) {
        if (null == permissions || permissions.length == 0)
            return;
        if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || checkPermissionGranted(activity,permissions)) {
            if (permissionCallback != null) {
                permissionCallback.hasPermission();
                //permissionCallback = null;
            }
        } else {
            // permission has not been granted.
            this.permissionCallback = permissionCallback;
            // 申请权限，并检测是否需要向用户解释为何需要啊该权限
            requestPermissionIfNeedExplain(activity,permissionDes, PERMISSION_REQUEST_CODE, permissions);
        }

    }

    /**
     * 检测是否已经授予该权限
     *
     * @param permissions
     * @return
     */
    private boolean checkPermissionGranted(Context context, String[] permissions) {
        boolean flag = true;
        for (String p : permissions) {
            if (ActivityCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 申请权限，并检测是否需要向用户解释为何需要啊该权限
     *
     * @param permissionDes
     * @param requestCode
     * @param permissions
     */
    private void requestPermissionIfNeedExplain(final Activity context, String permissionDes, final int requestCode, final String[] permissions) {
        // 是否需要向用户解释为何需要啊该权限
        if (shouldShowRequestPermissionRationale(context,permissions)) {
            // 如果用户之前拒绝过此权限，再提示一次准备授权相关权限
            if (myDialog == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("提示").setMessage(permissionDes).setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(context, permissions, requestCode);
                    }
                }).setNegativeButton("不允许", null).show();
            }
        } else {
            // Contact permissions have not been granted yet. Request them directly.
            ActivityCompat.requestPermissions(context, permissions, requestCode);
        }
    }

    /**
     * 是否需要向用户解释为何需要啊该权限
     *
     * @param permissions
     * @return
     */
    private boolean shouldShowRequestPermissionRationale(Activity context,String[] permissions) {
        boolean flag = false;
        for (String p : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, p)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (verifyPermissions(grantResults)) {
                if (permissionCallback != null) {
                    permissionCallback.hasPermission();
                    permissionCallback = null;
                }
            } else {
                if (permissionCallback != null) {
                    permissionCallback.noPermission();
                    permissionCallback = null;
                }
            }
        }
    }

    private boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if (grantResults.length < 1) {
            return false;
        }

        // Verify that each required permission has been granted, otherwise
        // return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    // ********************** END Android M Permission
    // ****************************************


}
