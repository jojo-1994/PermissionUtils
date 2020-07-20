package com.jjj.permission;

/**
 * xx
 *
 * @author jiangjiaojiao
 * @since 2020-04-13
 */
public interface BasePermissionCallback {
    /**
     * Granted permissions
     */
    void grantedPermissions();

    /**
     * Denied permissions
     */
    void deniedPermissions();

    /**
     * @return Is use default tips dialog if lacking permissions
     */
    boolean isUseDefaultTipsDialog();

    void showCustomTipsDialog();

    void hideCustomTipsDialog();
}
