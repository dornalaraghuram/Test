/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.navigator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.news.test.R;
import com.news.test.injection.scope.ActivityScope;
import com.news.test.injection.scope.ContainerId;
import com.news.test.network.ErrorHandler;
import com.news.test.network.model.ErrorResponse;
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
    private Snackbar mSnackBar;
    private AlertDialog mDialog;

    @Inject
    public AppNavigatorImpl(BaseActivity context, @ContainerId int containerId) {
        mActivity = context;
        mContainerId = containerId;
    }

    @Override
    public void launchHomeScreen() {
        replaceFragment(mContainerId, HomeFragment.getInstance());
    }

    @Override
    public void showErrorResponseDialog(Throwable e) {
        String errorMessage;
        if(e instanceof ErrorResponse) {
            errorMessage = mActivity.getResources().getString(ErrorHandler.getErrorMessageByCodes(((ErrorResponse) e).getStatusCode()));
        } else {
            errorMessage = e.getMessage();
        }
        showDialog(errorMessage);
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        if(mActivity != null) {
            mActivity.showProgressBar();
        }
    }

    @Override
    public void hideProgressBar() {
        if(mActivity != null) {
            mActivity.hideProgressBar();
        }
    }

    @Override
    public void showNoNetworkSnackMessage() {
        if(mSnackBar == null) {
            mSnackBar = Snackbar
                    .make(mActivity.findViewById(R.id.main_container), mActivity.getResources().getString(R.string.no_network_msg),
                            Snackbar.LENGTH_SHORT);
        }
        if(mSnackBar.isShown()) return;
        View sbView = mSnackBar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
        mSnackBar.show();
    }

    @Override
    public void dismissSnackMessage() {
        if(mSnackBar != null && mSnackBar.isShown()) {
            mSnackBar.dismiss();
        }
    }

    private void showDialog(String message) {
        createDialog(message).show();
    }

    private Dialog createDialog(String message) {
        mDialog = new AlertDialog.Builder(mActivity).setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null).create();
        return mDialog;
    }


    @Override
    public void dismissDialog() {
        if(mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
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


}
