package com.csii.integralmall.activity.shippingAddress.permissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

public class PermissionsChecker {
    private final Context mContext;


    public PermissionsChecker(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    /**
     * 判断权限集合
     *
     * @param permissions 权限集合
     * @return 返回值
     */
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否缺少权限
     *
     * @param permission 单个权限
     * @return
     */
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) ==
                PackageManager.PERMISSION_GRANTED;
    }
}
