/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import com.news.test.network.DataSource;
import com.news.test.rxbus.RxBus;
import com.news.test.ui.navigator.AppNavigator;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Base class for every viewmodel in the application
 */
public class BaseViewModel extends AndroidViewModel {

    private final CompositeDisposable mDisposable;
    protected AppNavigator mNavigator;
    protected RxBus mRxBus;
    protected DataSource mDataSource;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mDisposable = new CompositeDisposable();
    }

    /**Get the Navigator to navigate between fragment / activity
     *
     * @return {@link com.news.test.ui.navigator.AppNavigator}
     */
    protected AppNavigator getNavigator() {
        return mNavigator;
    }

    protected RxBus getRxBus() {
        return mRxBus;
    }

    public void setRxBus(RxBus rxBus) {
        mRxBus = rxBus;
    }

    public void setAppNavigator(AppNavigator navigator) {
        mNavigator = navigator;
    }

    public void setDataSource(DataSource dataSource) {
        mDataSource = dataSource;
    }

    protected DataSource getDataSource() {
        return mDataSource;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    /**Get the disposable to add / clear
     * @return
     */
    public CompositeDisposable getDisposable() {
        return mDisposable;
    }
}
