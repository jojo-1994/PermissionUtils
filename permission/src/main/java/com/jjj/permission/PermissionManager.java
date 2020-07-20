package com.jjj.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;

/**
 * xx
 *
 * @author jiangjiaojiao
 * @since 2020-07-20
 */
public class PermissionManager {
    private Activity mActivity;
    private String[] mNeedPermissions;
    private BasePermissionCallback mPermissionCallback;
    private boolean isFirstRequestPermission = true;
    private AlertDialog mDefaultTipsDialog;

    public PermissionManager(Activity activity, String[] needPermissions, @NonNull BasePermissionCallback callback) {
        mActivity = activity;
        mNeedPermissions = needPermissions;
        mPermissionCallback = callback;
    }

    public void onResume() {
        if (PermissionUtils.checkPermissions(mActivity, mNeedPermissions)) {
            mPermissionCallback.grantedPermissions();
            hideTipsDialog();
        } else {
            mPermissionCallback.deniedPermissions();
            if (isFirstRequestPermission) {
                PermissionUtils.requestPermissions(mActivity, mNeedPermissions);
                isFirstRequestPermission = false;
            } else {
                showTipsDialog();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode) {
        if (requestCode == PermissionUtils.ACTION_REQUEST_PERMISSIONS &&
                !PermissionUtils.checkPermissions(mActivity, mNeedPermissions)) {
            showTipsDialog();
        }
    }

    private void showDefaultTipsDialog() {
        if (mDefaultTipsDialog == null) {
            createDefaultTipsDialog();
        }
        if (!mDefaultTipsDialog.isShowing()) {
            mDefaultTipsDialog.show();
            mDefaultTipsDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                    .setTextColor(Color.parseColor("#888888"));
        }
    }

    private void hideDefaultTipsDialog() {
        if (mDefaultTipsDialog != null && mDefaultTipsDialog.isShowing()) {
            mDefaultTipsDialog.dismiss();
        }
    }

    private void showTipsDialog() {
        if (mPermissionCallback.isUseDefaultTipsDialog()) {
            showDefaultTipsDialog();
        } else {
            mPermissionCallback.showCustomTipsDialog();
        }
    }

    private void hideTipsDialog() {
        if (mPermissionCallback.isUseDefaultTipsDialog()) {
            hideDefaultTipsDialog();
        } else {
            mPermissionCallback.hideCustomTipsDialog();
        }
    }

    private void createDefaultTipsDialog() {
        mDefaultTipsDialog = new AlertDialog.Builder(mActivity)
                .setTitle("授权提醒")
                .setMessage("权限不足会影响正常使用，请立即授权")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mActivity.finish();
                    }
                })
                .setPositiveButton("立即授权", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (PermissionUtils.isSuccessRequestPermissions(mActivity, mNeedPermissions)) {
                            PermissionUtils.requestPermissions(mActivity, mNeedPermissions);
                        } else {
                            PermissionUtils.goSettingPage(mActivity);
                        }
                    }
                })
                .setCancelable(false)
                .create();
    }
}
