/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.home;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.news.test.network.DataSource;
import com.news.test.network.model.Facts;
import com.news.test.rxbus.RxBus;
import com.news.test.ui.base.BaseViewModel;
import com.news.test.ui.model.FactsData;
import com.news.test.ui.navigator.AppNavigator;
import com.news.test.util.Logger;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    private static final String TAG = HomeViewModel.class.getSimpleName();

    private MutableLiveData<FactsData> mFactsData = new MutableLiveData<>();

    @Inject
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadFactsData() {
        getDisposable().add(getDataSource().getFacts()
                .flatMap( facts -> Observable.just(new FactsData(facts)))
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<FactsData>() {

                    @Override
                    public void onSuccess(FactsData facts) {
                        Logger.i(TAG, "onsuccess");
                        if(facts != null) {
                            setFactsData(facts);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i(TAG, "onfailure "+e.getMessage());
                        getNavigator().showErrorResponseDialog(e);
                    }

                })
        );
    }

    public MutableLiveData<FactsData> getFactsData() {
        return mFactsData;
    }

    public void setFactsData(FactsData facts) {
        mFactsData.setValue(facts);
    }

}
