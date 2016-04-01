package com.workjm.myonlinestudy.common;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import java.lang.reflect.Method;
import java.util.Locale;

/**
 * 工具类，获取设备相关信息
 */
public class TDeviceInfo {

    /**
     * 获取手机IMEI 号
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        String imei = null;
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(
                Context.TELEPHONY_SERVICE));
        imei = telephonyManager.getDeviceId();
        return imei;
    }


    /**
     * 获取系统语言
     * @param context
     * @return
     */
    public static String getLanguage(Context context) {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取设备品牌名
     * SystemProperties.get("ro.product.brand")
     * @return
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取设备版本
     * SystemProperties.get("ro.product.model")
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取设备版本
     * SystemProperties.get("ro.product.name"))
     */
    public static String getProduct() {
        return Build.PRODUCT;
    }

    /**
     * 获取设备版本
     * SystemProperties.get("ro.product.manufacturer")
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 判断是否存在实体按键
     * @param context
     * @return
     */
    public static boolean hasHardwareMenuKey(Context context) {
        boolean flag = ViewConfiguration.get(context).hasPermanentMenuKey();
        return flag;
    }

    /**
     * 获取设备屏幕宽度
     * @param context
     * @return
     */
    public static int getDevicesWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getRealMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取设备屏幕高度
     * @param context
     * @return
     */
    public static int getDevicesHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getRealMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取设设备DPI
     * @param context
     * @return
     */
    public static String getDevicesDpi(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getRealMetrics(dm);
        return getDensity(dm.densityDpi);
    }

    private static String getDensity(int density) {
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                return "ldpi";
            case DisplayMetrics.DENSITY_MEDIUM:
                return "mdpi";
            case DisplayMetrics.DENSITY_TV:
                return "tv";
            case DisplayMetrics.DENSITY_HIGH:
                return "hdpi";
            case DisplayMetrics.DENSITY_XHIGH:
                return "xhdpi";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "xxhdpi";
            default:
                return "unknown";
        }
    }

    /**
     * 获取设备内部版本号
     * SystemProperties.get("ro.custom.build.version")
     * @return
     */
    public static String getDevicesCustomVersion() {
        String version = null;
        try {
            Class x = Class.forName("android.os.SystemProperties");
            Method get = x.getMethod("get", String.class);
            Object value = get.invoke(x, "ro.custom.build.version");
            version = value.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取设备外部版本号
     * @return
     */
    public static String getDevicesInternalVersion() {
        String version = null;
        try {
            Class x = Class.forName("android.os.SystemProperties");
            Method get = x.getMethod("get", String.class);
            version = get.invoke(x, "ro.internal.build.version").toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return version;
    }

}
