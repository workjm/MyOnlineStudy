package com.workjm.myonlinestudy.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by workjm on 2016/3/13.
 * Use Glide lib load image into imageView
 */
public class ImageLoader {

    public static void load(Context context, String url, ImageView view) {
        load(context, url, view, 300);
    }

    public static void load(Context context, String url, ImageView view, int animationTime) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade(animationTime)
                .into(view);
    }

    public static void load(Context context, int resourceId, ImageView view) {
        load(context, resourceId, view, 300);
    }

    public static void load(Context context, int resourceId, ImageView view, int animationTime) {
        Glide.with(context)
                .load(resourceId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade(animationTime)
                .into(view);
    }

    public static void loadWithHighPriority(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(view);
    }

    public static void load(Context context, String url, int animationId, ImageView view) {
        Glide.with(context)
                .load(url)
                .animate(animationId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }
}
