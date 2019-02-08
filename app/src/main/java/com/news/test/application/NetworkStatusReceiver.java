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

import com.news.test.constants.NetworkConstants;
import com.news.test.service.NetworkSchedulerService;
import com.news.test.util.NetworkUtils;

/**
 * Receiver class for network connection
 */
public class NetworkStatusReceiver extends BroadcastReceiver {

    private ConnectionCallback mCallback;

    public NetworkStatusReceiver(ConnectionCallback callback) {
        mCallback = callback;
    }

    public interface ConnectionCallback {
        void onConnectionChanged(boolean isConnected);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null && intent.getAction().equals(NetworkConstants.CONNECTIVITY_ACTION)) {
            mCallback.onConnectionChanged(NetworkUtils.isNetworkAvailable(context));
        }
    }

}
