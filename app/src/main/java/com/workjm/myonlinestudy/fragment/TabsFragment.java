package com.workjm.myonlinestudy.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.workjm.myonlinestudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by workjm on 2016/3/13.
 */
public class TabsFragment extends BaseFragment{

    private static final String TAG = "TabsFragment";

    public static final String MENU_THEMES = "theme";
    public static final String MENU_WALLPAPER = "wallpaper";

    private ViewPager viewPager;
    private TabLayout mTabLayout;
    TabPagerAdapter tabPagerAdapter;
    private String menuType;
    private List<Fragment> fragments = new ArrayList<>();

    public static TabsFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        TabsFragment fragment = new TabsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_theme_tab;
    }

    @Override
    protected void AlwaysInit() {
        super.AlwaysInit();
    }

    @Override
    protected void initViews() {
        viewPager = (ViewPager)rootView.findViewById(R.id.pager);
        mTabLayout = (TabLayout)rootView.findViewById(R.id.tabs);
        initFragments();
        viewPager.setAdapter(tabPagerAdapter);
        mTabLayout.setupWithViewPager(viewPager);
        mTabLayout.setTabsFromPagerAdapter(tabPagerAdapter);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initFragments() {
        menuType = getArguments().getString("type");
        List<String> titles = new ArrayList<>();
        titles.add("onLine Themes");
        titles.add("local Themes");
        fragments.add(new ThemeFragment());
        fragments.add(new ListFragment());

        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());
        tabPagerAdapter.setFragments(fragments, titles);
    }

    @Override
    protected void initData() {

    }

    public static class TabPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private List<String> titles;

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setFragments(List<Fragment> fragments, List<String> titles) {
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
