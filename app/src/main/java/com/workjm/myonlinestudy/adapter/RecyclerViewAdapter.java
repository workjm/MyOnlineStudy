package com.workjm.myonlinestudy.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.workjm.myonlinestudy.R;
import com.workjm.myonlinestudy.mvp.bean.ThemeInfo;
import com.workjm.myonlinestudy.utils.ImageLoader;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    private ArrayList<ThemeInfo> themeInfos;

    public RecyclerViewAdapter(Context mContext, ArrayList<ThemeInfo> infos) {
        this.mContext = mContext;
        this.themeInfos = infos;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_theme_item, parent, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.cover);
        TextView textView = (TextView)view.findViewById(R.id.theme_name);
        return new ViewHolder(view, imageView, textView);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        final View view = holder.mView;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 20, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mContext.startActivity(new Intent(mContext, DetailActivity.class));
                    }
                });
                animator.start();*/
            }
        });

        final ImageView imageView = holder.mImageView;
        ImageLoader.load(mContext,themeInfos.get(position).getTitlepicpath(), imageView);
        final TextView textView = holder.mName;
        textView.setText(themeInfos.get(position).getShowName());

    }

    @Override
    public int getItemCount() {
        if (themeInfos == null) {
            return 0;
        }
        return themeInfos.size();
    }

    public void setThemeInfos(ArrayList<ThemeInfo> list) {
        themeInfos = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mName;
        public ViewHolder(View view, ImageView imageView, TextView textView) {
            super(view);
            this.mView = view;
            this.mImageView = imageView;
            this.mName = textView;
        }
    }
}
