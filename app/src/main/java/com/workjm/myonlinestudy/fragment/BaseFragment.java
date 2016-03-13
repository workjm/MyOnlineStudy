package com.workjm.myonlinestudy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{
    protected View rootView;
    protected int layoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            initLayoutId();
            rootView = inflater.inflate(layoutId, container, false);
            initViews();
        }
        AlwaysInit();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void initLayoutId();

    protected void AlwaysInit() {
    }

    protected abstract void initViews();

    protected abstract void initData();


    public boolean isAlive() {
        return getActivity() != null;
    }
}
