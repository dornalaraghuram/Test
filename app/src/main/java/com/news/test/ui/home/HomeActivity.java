/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.home;

import android.os.Bundle;

import com.news.test.ui.base.BaseActivity;

import dagger.android.AndroidInjection;

/**
 * Main application launcher screen
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
        getAppNavigator().launchHomeScreen();
    }

}
