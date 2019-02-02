/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.network;

import com.news.test.network.model.Facts;

import javax.inject.Inject;

import io.reactivex.Observable;


public class Repository implements DataSource {

    private final ApiService mApiService;

    @Inject
    public Repository(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<Facts> getFacts() {
        return mApiService.getFacts();
    }
}
