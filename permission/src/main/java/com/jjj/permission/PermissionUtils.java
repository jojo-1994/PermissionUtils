package com.jjj.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * xx
 *
 * @author jiangjiaojiao
 * @since 2020-07-20
 */
public class PermissionUtils {
    protected static final int ACTION_REQUEST_PERMISSIONS = 0x001;

    /**
     * 检查是否获取权限
     *
     * @param activity
     * @param neededPermissions
     * @return false: 缺少权限
     */
    public static boolean checkPermissions(Activity activity, String[] neededPermissions) {
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted = allGranted &&
                    (ContextCompat.checkSelfPermission(activity, neededPermission) ==
                            PackageManager.PERMISSION_GRANTED);
        }
        return allGranted;
    }

    public static boolean checkPermissions(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 是否存在 【权限被拒，不再提醒】的权限
     *
     * @param activity
     * @param neededPermissions
     * @return
     */
    public static boolean isSuccessRequestPermissions(Activity activity, String[] neededPermissions) {
        for (String neededPermission : neededPermissions) {
            if (ContextCompat.checkSelfPermission(activity, neededPermission) !=
                    PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, neededPermission)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 请求权限
     *
     * @param activity
     * @param neededPermissions
     */
    public static void requestPermissions(Activity activity, String[] neededPermissions) {
        ActivityCompat.requestPermissions(activity, neededPermissions, ACTION_REQUEST_PERMISSIONS);
    }


    /**
     * 检查是否有权限被设置过"不再提示"，如果是，申请权限；否则，去系统的应用设置界面；
     *
     * @param activity
     * @param neededPermissions
     */
    public static void checkAndRequestPermissions(Activity activity, String[] neededPermissions) {
        if (PermissionUtils.isSuccessRequestPermissions(activity, neededPermissions)) {
            PermissionUtils.requestPermissions(activity, neededPermissions);
        } else {
            PermissionUtils.goSettingPage(activity);
        }
    }

    /**
     * 【权限被拒，不再提醒】需要去设置-权限管理中重新授权
     *
     * @param context
     */
    public static void goSettingPage(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getApplicationContext().getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
