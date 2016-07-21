package com.workjm.myonlinestudy.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.workjm.myonlinestudy.MyStudyApplication;
import com.workjm.myonlinestudy.common.AndroidUtil;
import com.workjm.myonlinestudy.common.TDeviceInfo;
import com.workjm.myonlinestudy.mvp.bean.ThemeInfo;
import com.workjm.myonlinestudy.net.Net;
import com.workjm.myonlinestudy.utils.ServerHelper;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by junming.liang on 2016/3/30.
 */
public class ThemeModel implements DataModel {
    private static final String TAG = "ThemeModel";
    private long lastGetTime;
    public static final int GET_DURATION = 2000;

    private ArrayList<ThemeInfo> themeInfos = new ArrayList<>();

    @Override
    public void getData(final OnLoadDataListener listener) {
        if (themeInfos != null) {
            themeInfos.clear();
        }
        lastGetTime = System.currentTimeMillis();
        final Callback<String> callback = new Callback<String>() {
            @Override
            public String parseNetworkResponse(Response response) throws Exception {
                return response.body().string();
            }
            @Override
            public void onError(Call call, Exception e) {
                Log.d(TAG, "onError  e = "+e);
                if (System.currentTimeMillis() - lastGetTime < GET_DURATION) {
                    return;
                }
                listener.onFailure("load theme failed");
            }
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONArray data = jsonObj.getJSONArray("themeData");
                    ThemeInfo themeInfo = null;
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject item = (JSONObject) data.get(i);
                        Gson gson = new GsonBuilder().create();
                        themeInfo = gson.fromJson(item.toString(), ThemeInfo.class);
                        themeInfos.add(themeInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
        Net.get(url, callback, params, null, 15000);
    }


    public ArrayList<ThemeInfo> getThemeInfos() {
        return themeInfos;
    }

}
