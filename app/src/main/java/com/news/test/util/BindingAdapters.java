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
}
