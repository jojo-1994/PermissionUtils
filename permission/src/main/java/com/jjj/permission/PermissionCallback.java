package com.jjj.permission;

/**
 * xx
 *
 * @author jiangjiaojiao
 * @since 2020-04-13
 */
public interface PermissionCallback {
    /**
     * Granted permissions
     */
    void grantedPermissions();

    /**
     * Denied permissions
     *
     * @return Is use default tips dialog if lacking permissions
     */
    boolean deniedPermissions();
}
