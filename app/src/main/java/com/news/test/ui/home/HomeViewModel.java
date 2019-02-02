/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.home;

import android.app.Application;
import android.support.annotation.NonNull;

import com.news.test.network.DataSource;
import com.news.test.network.model.Facts;
import com.news.test.rxbus.RxBus;
import com.news.test.ui.base.BaseViewModel;
import com.news.test.ui.navigator.AppNavigator;
import com.news.test.util.Logger;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    private static final String TAG = HomeViewModel.class.getSimpleName();
    private final DataSource mDataSource;

    @Inject
    public HomeViewModel(@NonNull Application application, DataSource dataSource, RxBus rxBus, AppNavigator navigator) {
        super(application);
        mDataSource = dataSource;
        mRxBus = rxBus;
        mNavigator = navigator;
    }

    public void loadFactsData() {
        getDisposable().add(mDataSource.getFacts()
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Facts>() {

                    @Override
                    public void onSuccess(Facts facts) {
                        Logger.i(TAG, "onsuccess");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i(TAG, "onfailure "+e.getMessage());
                        getNavigator().showErrorResponseDialog(e);
                    }

                })
        );
    }
}
