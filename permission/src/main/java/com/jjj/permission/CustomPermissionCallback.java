package com.jjj.permission;

/**
 * xx
 *
 * @author jiangjiaojiao
 * @since 2020-07-22
 */
public abstract class CustomPermissionCallback implements BasePermissionCallback {
    @Override
    public boolean isUseDefaultTipsDialog() {
        return false;
    }

    @Override
    public void deniedPermissions() {
    }
}
