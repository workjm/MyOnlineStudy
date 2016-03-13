package com.workjm.myonlinestudy.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.appcompat.R.anim;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.workjm.myonlinestudy.MainActivity;
import com.workjm.myonlinestudy.R;
import com.workjm.myonlinestudy.common.Dater;
import com.workjm.myonlinestudy.net.API;
import com.workjm.myonlinestudy.net.Net;
import com.workjm.myonlinestudy.utils.ImageLoader;
import com.workjm.myonlinestudy.utils.SettingsProvider;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import okhttp3.Call;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashView";
    private static final int SPLASH_DURATION = 2500;
    private static final String SPLASH = "splash";
    private ImageView splash;
    private String today;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        splash = (ImageView) findViewById(R.id.splash);
        if (SettingsProvider.getBoolean("origin_splash")) {
            ImageLoader.load(this, R.drawable.splash, splash);
            startAppDelay();
            return;
        }
        initSplash();
    }


    private void initSplash() {
        today = Dater.parseStandardDate(new Date());
        Log.d(TAG, "today = " + today);
        loadImageFile();
        if (!today.equals(SettingsProvider.getString("date"))){
            getSplash();
        }
    }


    private void getSplash() {
        Log.d(TAG, "getSplash");
        if (!Net.isOnline(this)) {
            return;
        }

        Net.get(API.SPLASH, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                startApp();
            }

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "getSplash : onResponse");
                //这里并不是UI线程，所以如果需要刷新ui，可以使用handler
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String url = jsonObject.getString("img");
                    SettingsProvider.save(SPLASH, url);
                    SettingsProvider.save("date", today);
                    Log.d(TAG, "getSplash : url = "+url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, API.TAG_SPLASH);
    }

    private void loadImageFile() {
        String url = SettingsProvider.get(SPLASH, "");
        Log.d(TAG, "url = "+url);
        if ("".equals(url)) {
            ImageLoader.load(this, R.drawable.splash, splash, SPLASH_DURATION);
        } else {
            ImageLoader.load(this, url, R.anim.scale_anim, splash);
        }
        startAppDelay();
    }

    private void startAppDelay() {
        splash.postDelayed(new Runnable() {
            @Override
            public void run() {
                startApp();
            }
        }, SPLASH_DURATION);
    }

    private void startApp() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(anim.abc_grow_fade_in_from_bottom, anim.abc_shrink_fade_out_from_bottom);
        finish();
    }

    @Override
    public void onBackPressed() {
        //disable back button when showing splash
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Net.cancelTag(API.TAG_SPLASH);
    }
}
