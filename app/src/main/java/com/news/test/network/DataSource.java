/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.network;


import com.news.test.network.model.Facts;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface DataSource {

    Observable<Facts> getFactsFromApi();

    Observable<Facts> getFacts();

    Observable<Facts> getFactsFromDb();

    Completable insertFactsIntoDb(Facts facts);
}
