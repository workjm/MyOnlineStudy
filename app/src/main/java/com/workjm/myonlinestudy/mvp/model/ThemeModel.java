package com.workjm.myonlinestudy.mvp.model;

import android.os.Build;

import com.workjm.myonlinestudy.MyStudyApplication;
import com.workjm.myonlinestudy.common.AndroidUtil;
import com.workjm.myonlinestudy.common.TDeviceInfo;
import com.workjm.myonlinestudy.net.Net;
import com.workjm.myonlinestudy.utils.ServerHelper;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by junming.liang on 2016/3/30.
 */
public class ThemeModel implements DataModel {
    private long lastGetTime;
    public static final int GET_DURATION = 2000;

    @Override
    public void getData(final OnLoadDataListener listener) {
        lastGetTime = System.currentTimeMillis();
        final Callback<String> callback = new Callback<String>() {
            @Override
            public String parseNetworkResponse(Response response) throws Exception {
                return response.body().string();
            }
            @Override
            public void onError(Call call, Exception e) {
                if (System.currentTimeMillis() - lastGetTime < GET_DURATION) {
                    return;
                }
                listener.onFailure("load theme failed");
            }
            @Override
            public void onResponse(String response) {
                listener.onSuccess();
            }

        };
        getThemeData(callback);
    }

    private void getThemeData(Callback callback) {
        Map<String, String> params = new HashMap<>();
//        params.put(ServerHelper.THEME_PARAM, "-1");
        params.put(ServerHelper.PARAM_region, "cn");
        params.put(ServerHelper.PARAM_mediatype, "2");
        params.put("pagenum", "1");
        params.put("pageindex", "0");
        params.put(ServerHelper.IMEI_PARAM, TDeviceInfo.getImei(MyStudyApplication.context));
        params.put(ServerHelper.PARAM_language, TDeviceInfo.getLanguage(MyStudyApplication.context));
        params.put(ServerHelper.PARAM_dpi, TDeviceInfo.getDevicesDpi(MyStudyApplication.context));
        params.put(ServerHelper.PARAM_resolution, TDeviceInfo.getDevicesWidth(
                MyStudyApplication.context) + "*" + TDeviceInfo.getDevicesHeight(MyStudyApplication.context));
        params.put(ServerHelper.PRODUCTBRAND_PARAM, TDeviceInfo.getModel());
        params.put(ServerHelper.PARAM_hwmainkeys, TDeviceInfo.hasHardwareMenuKey(MyStudyApplication.context) ? "1" : "0");
        params.put(ServerHelper.PARAM_apkversion, AndroidUtil.getApplicationVersion(MyStudyApplication.context));
        params.put(ServerHelper.PARAM_templateversion, "1");
        params.put(ServerHelper.PRODUCTBRAND_PARAM, /*TDeviceInfo.getBrand()*/"sugar");
        params.put(ServerHelper.PRODUCTNAME_PARAM, TDeviceInfo.getProduct());
        params.put(ServerHelper.PRODUCTMANUFACTURER_PARAM, TDeviceInfo.getManufacturer());
        String customVersion = TDeviceInfo.getDevicesCustomVersion();
        if(customVersion != null){
            params.put(ServerHelper.CUSTOMBUILDVERSION_PARAM, customVersion);
        }
        String internalVer = TDeviceInfo.getDevicesInternalVersion();
        if(internalVer != null){
            params.put(ServerHelper.INTERNALBUILDVERSION_PARAM, internalVer);
        }

        String url = ServerHelper.ThemeUrl + "web/ThemeAction!"+ServerHelper.THEME_ATION;
        Net.post(url, callback, params, null, 15000);
    }
}
