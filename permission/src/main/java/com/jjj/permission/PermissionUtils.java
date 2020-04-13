package com.jjj.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


/**
 * 权限请求封装类
 *
 * @author jiangjiaojiao
 * @since 2020-01-17
 */
public class PermissionUtils {
    private static final int ACTION_REQUEST_PERMISSIONS = 0x001;

    public static void requestPermissions(Activity activity, String[] neededPermissions) {
        ActivityCompat.requestPermissions(activity, neededPermissions, ACTION_REQUEST_PERMISSIONS);
    }

    public static boolean checkPermissions(Context context, String[] neededPermissions) {
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted = allGranted && (
                    ContextCompat.checkSelfPermission(context, neededPermission) ==
                            PackageManager.PERMISSION_GRANTED);
        }
        return allGranted;
    }

    public static void onRequestPermissionsResult(final Activity activity, int requestCode,
                                                  final String[] neededPermissions, PermissionCallback callback) {
        if (requestCode == ACTION_REQUEST_PERMISSIONS) {
            if (checkPermissions(activity, neededPermissions)) {
                if (callback != null) {
                    callback.grantedPermissions();
                }
                return;
            }

            if (callback != null) {
                if (!callback.deniedPermissions()) {
                    return;
                }
            }

            AlertDialog alertDialog = new AlertDialog.Builder(activity)
                    .setTitle("提醒")
                    .setMessage("权限不足会影响当前使用，请重新授权")
                    .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            activity.finish();
                        }
                    }).setPositiveButton("重新授权", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(activity, neededPermissions);
                        }
                    }).show();
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                    .setTextColor(Color.parseColor("#888888"));
        }
    }
}
