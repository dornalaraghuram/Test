/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.rxbus;

import io.reactivex.Observable;

public interface RxBus {

    void send(final Object event);

    Observable<Object> toObservable();

    boolean hasObservers();
}