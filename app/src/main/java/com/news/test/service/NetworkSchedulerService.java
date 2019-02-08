/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.news.test.application.NetworkStatusReceiver;
import com.news.test.constants.NetworkConstants;
import com.news.test.util.Logger;

public class NetworkSchedulerService extends JobService implements NetworkStatusReceiver.ConnectionCallback{

    private static final String TAG = NetworkSchedulerService.class.getSimpleName();
    public static final int NETWORK_CONNECTED = 0;
    public static final int NETWORK_DISCONNECTED = 1;
    private NetworkStatusReceiver networkStatusReceiver;
    Messenger mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i(TAG, "Service created");
        networkStatusReceiver = new NetworkStatusReceiver(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i(TAG, "onStartCommand");
        if(intent != null && intent.getExtras() != null)
        mHandler = intent.getParcelableExtra(NetworkConstants.EXTRA_HANDLER);
        return START_NOT_STICKY;
    }


        @Override
    public boolean onStartJob(JobParameters jobParameters) {
            Logger.i(TAG, "onStartJob" + networkStatusReceiver);
            registerReceiver(networkStatusReceiver, new IntentFilter(NetworkConstants.CONNECTIVITY_ACTION));
            return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Logger.i(TAG, "onStopJob");
        unregisterReceiver(networkStatusReceiver);
        return true;
    }

    @Override
    public void onConnectionChanged(boolean isConnected) {
        if(mHandler != null) {
            Message msg = new Message();
            msg.what = isConnected? NETWORK_CONNECTED : NETWORK_DISCONNECTED;
            try {
                mHandler.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
