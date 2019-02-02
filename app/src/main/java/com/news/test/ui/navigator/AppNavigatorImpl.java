/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.navigator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.news.test.constants.BundleConstants;
import com.news.test.injection.scope.ActivityScope;
import com.news.test.injection.scope.ContainerId;
import com.news.test.ui.base.BaseActivity;
import com.news.test.ui.home.HomeFragment;

import javax.inject.Inject;

/**
 * Navigate between components (activity / fragment)
 */
@ActivityScope
public class AppNavigatorImpl implements AppNavigator {


    private final BaseActivity mActivity;
    private final int mContainerId;

    @Inject
    public AppNavigatorImpl(BaseActivity context, @ContainerId int containerId) {
        mActivity = context;
        mContainerId = containerId;
    }


    @Override
    public void loadFragment(int containerId, Fragment fragment) {
        replaceFragment(containerId, fragment);
    }

    @Override
    public void launchHomeScreen() {
        replaceFragment(mContainerId, HomeFragment.getInstance());
    }

    private void startActivity(@NonNull Class<? extends Activity> activityClass, Bundle args, Integer requestCode) {
        Intent intent = new Intent();
        intent.putExtra(BundleConstants.EXTRA_ARG, args);
        if (requestCode != null) {
            mActivity.startActivityForResult(intent, requestCode);
        } else {
            mActivity.startActivity(intent);
        }
    }

    private void addFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment, String backStackTag) {
        addFragmentInternal(mActivity.getSupportFragmentManager(), containerId, fragment, null, null, true, backStackTag);
    }

    private void addFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment) {
        addFragmentInternal(mActivity.getSupportFragmentManager(), containerId, fragment, null, null, true, null);
    }

    private final void replaceFragment(@IdRes int containerId, Fragment fragment) {
        replaceFragmentInternal(mActivity.getSupportFragmentManager(), containerId, fragment, null, null, false, null);
    }




    private final void replaceFragmentInternal(FragmentManager fm, @IdRes int containerId, Fragment fragment, String fragmentTag, Bundle args, boolean addToBackstack, String backstackTag) {
        if (mActivity.isFinishing()) return;

        if (args != null) {
            fragment.setArguments(args);
        }
        FragmentTransaction ft = fm.beginTransaction().replace(containerId, fragment, fragmentTag);
        if (addToBackstack) {
            ft.addToBackStack(backstackTag).commitAllowingStateLoss();
            fm.executePendingTransactions();
        } else {
            ft.commitAllowingStateLoss();
        }
    }

    private final void addFragmentInternal(FragmentManager fm, @IdRes int containerId, Fragment fragment, String fragmentTag, Bundle args, boolean addToBackstack, String backstackTag) {
        if (mActivity.isFinishing()) return;
        if (args != null) {
            fragment.setArguments(args);
        }
        FragmentTransaction ft = fm.beginTransaction().add(containerId, fragment, fragmentTag);
        if (addToBackstack) {
            ft.addToBackStack(backstackTag).commitAllowingStateLoss();
            fm.executePendingTransactions();
        } else {
            ft.commitAllowingStateLoss();
        }
    }
}
