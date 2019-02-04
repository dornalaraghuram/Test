/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.navigator;

import android.support.v4.app.Fragment;

public interface AppNavigator {

    void loadFragment(int containerId, Fragment fragment);

    void launchHomeScreen();

    void showErrorResponseDialog(Throwable e);

    void showProgressBar();

    void hideProgressBar();

    void showNoNetworkSnackMessage();

    void dismissSnackMessage();
}
