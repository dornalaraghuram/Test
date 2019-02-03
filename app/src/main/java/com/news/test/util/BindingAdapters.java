/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.util;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.news.test.R;
import com.news.test.injection.module.GlideApp;


/**
 * Class is used for custom bindings
 */
public class BindingAdapters {

    @BindingAdapter("custom_marginTop")
    public static void setCustomTopMargin(View view, float topMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, Math.round(topMargin),
                layoutParams.rightMargin, layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("networkImgSrc")
    public static void setImageViewResource(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .thumbnail(0.5f)
                .centerInside()
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
