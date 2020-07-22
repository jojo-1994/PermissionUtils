package com.jjj.permission.utils;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jjj.permission.CustomPermissionCallback;
import com.jjj.permission.PermissionManager;
import com.jjj.permissionutils.R;

/**
 * xx
 *
 * @author jiangjiaojiao
 * @since 2020-07-20
 */
public class CustomDialogActivity extends AppCompatActivity {
    private static String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    private PermissionManager mPermissionManager;
    private CustomPopupwindow mPermissionPopupwindow;
    private Button mStatusButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_activity);
        mStatusButton = findViewById(R.id.status_button);

        mPermissionPopupwindow = new CustomPopupwindow();
        mPermissionManager = new PermissionManager(this, NEEDED_PERMISSIONS, new CustomPermissionCallback() {

            @Override
            public void grantedPermissions() {
                //初始化工作
                init();
                mStatusButton.setText("未获取权限");
            }

            @Override
            public void deniedPermissions() {
                super.deniedPermissions();
                mStatusButton.setText("未获取权限");
            }

            @Override
            public void showCustomTipsDialog() {
                mPermissionPopupwindow.showCustomTipsPopupWindow(CustomDialogActivity.this, NEEDED_PERMISSIONS);
            }

            @Override
            public void hideCustomTipsDialog() {
                mPermissionPopupwindow.hideCustomTipsPopupWindow();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPermissionManager.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionManager.onRequestPermissionsResult(requestCode);
    }

    private void init() {
    }
}
