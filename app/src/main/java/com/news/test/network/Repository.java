/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.network;

import com.news.test.db.AppDatabase;
import com.news.test.db.FactsEntity;
import com.news.test.db.converters.FactsEntityToFactsConverter;
import com.news.test.db.converters.FactsToEntity;
import com.news.test.db.converters.ModelConverter;
import com.news.test.injection.scope.ApplicationScope;
import com.news.test.network.model.Facts;
import com.news.test.util.Config;
import com.news.test.util.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


@ApplicationScope
public class Repository implements DataSource {

    private final ApiService mApiService;
    private final AppDatabase mAppDatabase;
    private String TAG = Repository.class.getSimpleName();

    @Inject
    public Repository(ApiService apiService, AppDatabase appDatabase) {
        mApiService = apiService;
        mAppDatabase = appDatabase;
    }

    @Override
    public Observable<Facts> getFactsFromApi() {
        return mApiService.getFacts(Config.URL_FACTS)
                .flatMap(facts -> insertFactsIntoDb(facts)
                        .andThen(Observable.fromCallable(() -> facts)));
    }

    @Override
    public Observable<Facts> getFacts() {
        return getFactsFromDb().concatWith(getFactsFromApi());
    }

    @Override
    public Observable<Facts> getFactsFromDb() {
         return mAppDatabase.factsDao().getAll()
        .flatMap(factsEntities -> Observable.fromIterable(factsEntities)
                .map(factsEntity -> {
                    ModelConverter<FactsEntity, Facts> converter = new FactsEntityToFactsConverter();
                    return converter.transform(factsEntity);                })
                .toList())
                 .flatMapObservable(Observable::fromIterable);
    }

    @Override
    public Completable insertFactsIntoDb(Facts facts) {
        Logger.i(TAG, "insertion db outside "+facts.getTitle());
        return Completable.fromAction(() -> {
            ModelConverter<Facts, FactsEntity> converter = new FactsToEntity();
            Logger.i(TAG, "insertion "+facts.getTitle());
            FactsEntity factsEntity = converter.transform(facts);
            mAppDatabase.factsDao().insert(factsEntity);
        })
                .doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.i(TAG, "insertion error "+throwable.getMessage());
            }
        });
    }


}
