package com.workjm.myonlinestudy.mvp.model;

import com.workjm.myonlinestudy.net.Net;
import com.zhy.http.okhttp.callback.Callback;

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


    }
}
