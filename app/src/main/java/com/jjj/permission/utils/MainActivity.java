package com.jjj.permission.utils;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jjj.permission.DefaultPermissionCallback;
import com.jjj.permission.PermissionManager;
import com.jjj.permissionutils.R;


public class MainActivity extends AppCompatActivity {
    private static String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO
    };
    PermissionManager permissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionManager = new PermissionManager(this, NEEDED_PERMISSIONS,
                new DefaultPermissionCallback() {
                    @Override
                    public void grantedPermissions() {
                        init();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("----------", "onResume");
        permissionManager.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("----------", "onRequestPermissionsResult");
        permissionManager.onRequestPermissionsResult(requestCode);
    }

    private void init() {

    }
}
