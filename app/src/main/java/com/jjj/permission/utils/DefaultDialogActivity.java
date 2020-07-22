package com.jjj.permission.utils;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jjj.permission.DefaultPermissionCallback;
import com.jjj.permission.PermissionManager;
import com.jjj.permissionutils.R;

/**
 * xx
 *
 * @author jiangjiaojiao
 * @since 2020-07-22
 */
public class DefaultDialogActivity extends AppCompatActivity {
    private static String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO
    };
    private PermissionManager mPermissionManager;
    private Button mStatusButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_dialog_activity);
        mStatusButton = findViewById(R.id.status_button);

        mPermissionManager = new PermissionManager(this, NEEDED_PERMISSIONS,
                new DefaultPermissionCallback() {
                    @Override
                    public void grantedPermissions() {
                        //初始化工作
                        init();
                        mStatusButton.setText("已获取权限");
                    }

                    @Override
                    public void deniedPermissions() {
                        super.deniedPermissions();
                        mStatusButton.setText("未获取权限");
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("----------", "onResume");
        mPermissionManager.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("----------", "onRequestPermissionsResult");
        mPermissionManager.onRequestPermissionsResult(requestCode);
    }

    private void init() {
    }
}
