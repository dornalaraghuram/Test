/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.navigator;

public interface AppNavigator {

    void launchHomeScreen();

    void showErrorResponseDialog(Throwable e);

    void showProgressBar();

    void hideProgressBar();

    void showNoNetworkSnackMessage();

    void dismissSnackMessage();

    void dismissDialog();
}
