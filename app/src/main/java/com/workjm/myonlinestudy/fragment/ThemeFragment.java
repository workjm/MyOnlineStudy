package com.workjm.myonlinestudy.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;

import com.workjm.myonlinestudy.adapter.RecyclerViewAdapter;
import com.workjm.myonlinestudy.mvp.presenter.ThemeLoadPresenter;
import com.workjm.myonlinestudy.mvp.view.ILoadView;

/**
 * Created by junming.liang on 2016/5/4.
 */
public class ThemeFragment extends RecyclerFragment implements SwipeRefreshLayout.OnRefreshListener, ILoadView {

    private ThemeLoadPresenter themeLoadPresenter;
    private GridLayoutManager mGridLayoutManager;

    private RecyclerViewAdapter adapter;

    @Override
    protected void initViews() {
        super.initViews();
    }
    @Override
    protected void initData() {
        themeLoadPresenter = new ThemeLoadPresenter(this);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mGridLayoutManager);
        adapter = new RecyclerViewAdapter(getActivity(), themeLoadPresenter.getThemeInfos());
        recyclerView.setAdapter(adapter);


        themeLoadPresenter.updateTheme();
    }

    @Override
    public void onRefresh() {
        themeLoadPresenter.updateTheme();
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        adapter.setThemeInfos(themeLoadPresenter.getThemeInfos());
        adapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showFailedError() {
        swipeRefresh.setRefreshing(false);
    }
}
