/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.base;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.news.test.R;
import com.news.test.application.NetworkStatusReceiver;
import com.news.test.ui.navigator.AppNavigator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


/**
 * Base class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    public AppNavigator mAppNavigator;

    private Unbinder mUnBinder;

    @BindView(R.id.progress_bar)
    View mProgressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(getLayoutId());

        mUnBinder = ButterKnife.bind(this);
    }


    /**
     * Get the fragment container id
     */
    public int getContainerId() {
        return R.id.frag_container;
    }

    /**
     * Get the layout id of the base activity
     */
    protected int getLayoutId() {
        return R.layout.activity_base;
    }

    /**
     * Get the Appnavigator for UI navigation
     * @return {@link com.news.test.ui.navigator.AppNavigator}
     */
    protected AppNavigator getAppNavigator() {
        return mAppNavigator;
    }


    public void showProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if(mAppNavigator != null) {
            mAppNavigator.dismissDialog();
        }
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
