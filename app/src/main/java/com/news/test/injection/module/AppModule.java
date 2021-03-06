/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.injection.module;

import android.app.Application;
import android.content.Context;

import com.news.test.network.DataSource;
import com.news.test.network.Repository;
import com.news.test.rxbus.RxBus;
import com.news.test.rxbus.RxBusImpl;

import dagger.Binds;
import dagger.Module;

@Module(includes = NetworkModule.class)
public abstract class AppModule {

    @Binds
    abstract Context provideAppContext(Application application);

    @Binds
    abstract RxBus provideRxBus(RxBusImpl rxBus);

    @Binds
    abstract DataSource provideDataSource(Repository repository);

}
