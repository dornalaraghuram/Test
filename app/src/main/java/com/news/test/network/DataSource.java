/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.network;


import android.arch.lifecycle.LiveData;

import com.news.test.db.FactsEntity;
import com.news.test.network.model.Facts;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface DataSource {

    Observable<Facts> getFactsFromApi();

    Observable<Facts> getFacts();

    Observable<Facts> getFactsFromDb();

    Completable insertFactsIntoDb(Facts facts);
}
