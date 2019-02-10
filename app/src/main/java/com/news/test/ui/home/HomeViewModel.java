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
import android.support.annotation.NonNull;

import com.news.test.network.model.Facts;
import com.news.test.ui.base.BaseViewModel;
import com.news.test.ui.model.FactsData;
import com.news.test.util.Config;
import com.news.test.util.Logger;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    private static final String TAG = HomeViewModel.class.getSimpleName();

    private MutableLiveData<FactsData> mFactsData = new MutableLiveData<>();

    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    @Inject
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadFactsData(String url, Scheduler scheduler) {
        getDisposable().add(getDataSource().getFacts(url)
                .flatMap((Function<Facts, ObservableSource<FactsData>>) facts -> Observable.just(new FactsData(facts)))
                .firstOrError()
                .subscribeOn(url.equals(Config.URL_FACTS_TEST) ? scheduler : Schedulers.io())
                .observeOn(url.equals(Config.URL_FACTS_TEST) ? scheduler : AndroidSchedulers.mainThread())
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
                        setErrorMessage(e.getMessage());
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

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage.setValue(errorMessage);
    }
}
