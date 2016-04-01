package com.workjm.myonlinestudy.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by junming.liang on 2015/4/28.
 */
public class AndroidUtil {
    /**
     * Get application version name,
     * in androidmainifest.xml android:versionName
     * @param context
     * @return
     */
    public static String getApplicationVersion(Context context) {
        String name = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (info != null) {
                name = info.versionName;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
}
