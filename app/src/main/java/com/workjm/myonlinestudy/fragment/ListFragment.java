package com.workjm.myonlinestudy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.workjm.myonlinestudy.R;
import com.workjm.myonlinestudy.adapter.RecyclerViewAdapter;
import com.workjm.myonlinestudy.mvp.presenter.ThemeLoadPresenter;
import com.workjm.myonlinestudy.mvp.view.ILoadView;

public class ListFragment extends Fragment implements ILoadView{

    private RecyclerView mRecyclerView;

    private ThemeLoadPresenter themeLoadPresenter;
    RecyclerViewAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView =
                (RecyclerView) inflater.inflate(R.layout.list_fragment, container, false);
        themeLoadPresenter = new ThemeLoadPresenter(this);
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        adapter = new RecyclerViewAdapter(getActivity(), themeLoadPresenter.getThemeInfos());
        mRecyclerView.setAdapter(adapter);

        themeLoadPresenter.updateTheme();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        adapter.setThemeInfos(themeLoadPresenter.getThemeInfos());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showFailedError() {

    }
}
