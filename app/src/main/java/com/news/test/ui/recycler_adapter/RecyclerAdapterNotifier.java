/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.recycler_adapter;

import android.os.Bundle;

import com.news.test.rxbus.RxBus;


/**
 * Contains methods to notify the adapter.
 *
 * @author Aleksandar Gotev
 */
public interface RecyclerAdapterNotifier {
    void sendEvent(RecyclerAdapterViewHolder holder, Bundle data);

    String getPageId();

    RxBus getRxBus();

}
