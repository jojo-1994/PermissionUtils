package com.jjj.permission.utils;

import android.Manifest;
import android.os.Bundle;

import com.jjj.permission.PermissionCallback;
import com.jjj.permission.PermissionUtils;
import com.jjj.permissionutils.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (PermissionUtils.checkPermissions(this, NEEDED_PERMISSIONS)) {
            PermissionUtils.requestPermissions(this, NEEDED_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, new PermissionCallback() {
            @Override
            public void grantedPermissions() {

            }

            @Override
            public boolean deniedPermissions() {
                return false;
            }
        });
    }
}
