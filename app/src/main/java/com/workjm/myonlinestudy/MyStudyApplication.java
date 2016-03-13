package com.workjm.myonlinestudy;

import android.app.Application;
import android.content.Context;

/**
 * Created by workjm on 2016/3/13.
 */
public class MyStudyApplication extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
