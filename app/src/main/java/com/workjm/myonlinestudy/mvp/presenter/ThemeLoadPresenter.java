package com.workjm.myonlinestudy.mvp.presenter;

import android.util.Log;

import com.workjm.myonlinestudy.mvp.bean.ThemeInfo;
import com.workjm.myonlinestudy.mvp.model.OnLoadDataListener;
import com.workjm.myonlinestudy.mvp.model.ThemeModel;
import com.workjm.myonlinestudy.mvp.view.ILoadView;

import java.util.ArrayList;

/**
 * Created by junming.liang on 2016/4/26.
 */
public class ThemeLoadPresenter {
    private ILoadView mILoadView;
    private ThemeModel themeModel;

    public ThemeLoadPresenter(ILoadView view) {
        this.mILoadView = view;
        this.themeModel = new ThemeModel();
    }

    public void updateTheme() {
        mILoadView.showLoading();
        themeModel.getData(new OnLoadDataListener() {
            @Override
            public void onSuccess() {
                mILoadView.hideLoading();
            }

            @Override
            public void onFailure(String msg) {
                mILoadView.showFailedError();
            }
        });
    }

    public ArrayList<ThemeInfo> getThemeInfos() {
        return themeModel.getThemeInfos();
    }
}
