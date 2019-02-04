/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.application;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.news.test.util.NetworkUtils;

/**
 * Receiver class for network connection
 */
public class NetworkStatusReceiver extends BroadcastReceiver {

    private ConnectionCallback mCallback;

    public interface ConnectionCallback {
        void onConnected();

        void onDisConnected();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            boolean hasNetwork = !intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            if (NetworkUtils.isNetworkAvailable(context) && hasNetwork) {
                mCallback.onConnected();
            } else {
                mCallback.onDisConnected();
            }
        }
    }

    public void setConnectionCallback(ConnectionCallback callback) {
        mCallback = callback;
    }
}
