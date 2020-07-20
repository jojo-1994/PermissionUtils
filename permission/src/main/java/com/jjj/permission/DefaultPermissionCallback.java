package com.jjj.permission;

/**
 * xx
 *
 * @author jiangjiaojiao
 * @since 2020-07-20
 */
public abstract class DefaultPermissionCallback implements BasePermissionCallback {
    @Override
    public void deniedPermissions() {
    }

    @Override
    public boolean isUseDefaultTipsDialog() {
        return true;
    }

    @Override
    public void showCustomTipsDialog() {
    }

    @Override
    public void hideCustomTipsDialog() {

    }
}
