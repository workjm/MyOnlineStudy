package com.workjm.myonlinestudy.mvp.model;

/**
 * when themes loaded, this interface is called
 */
public interface OnLoadDataListener {
    void onSuccess();
    void onFailure(String msg);
}
