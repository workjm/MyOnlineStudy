package com.workjm.myonlinestudy.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.Map;

/**
 * Created by workjm on 2016/3/13.
 * Net request encapsulation
 */

public class Net {
    public static boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static void get(String url, Callback callback, Object tag) {
        OkHttpUtils.get().url(url).tag(tag)
                .build().execute(callback);
    }

    public static void get(String url, Callback callback, Map<String, String> params, Object tag, long time) {
        OkHttpUtils.get().url(url).params(params).tag(tag).build().connTimeOut(time).execute(callback);
    }

    public static void cancelTag(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);
    }
}
