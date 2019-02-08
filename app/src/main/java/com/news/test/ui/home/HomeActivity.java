/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.home;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.news.test.constants.NetworkConstants;
import com.news.test.service.CustomHandler;
import com.news.test.service.NetworkSchedulerService;
import com.news.test.ui.base.BaseActivity;

import dagger.android.AndroidInjection;

/**
 * Main application launcher screen
 */
public class HomeActivity extends BaseActivity implements CustomHandler.AppReceiver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            getAppNavigator().launchHomeScreen();
        }
        scheduleJob();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void scheduleJob() {
        JobInfo myJob = new JobInfo.Builder(0, new ComponentName(this, NetworkSchedulerService.class))
                .setRequiresCharging(true)
                .setMinimumLatency(1000)
                .setOverrideDeadline(2000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            jobScheduler.schedule(myJob);
        }
    }

    @Override
    protected void onStop() {
        stopService(new Intent(this, NetworkSchedulerService.class));
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startNetworkService();
    }

    private void startNetworkService() {
        Intent startServiceIntent = new Intent(this, NetworkSchedulerService.class);
        CustomHandler handler = new CustomHandler(this);
        startServiceIntent.putExtra(NetworkConstants.EXTRA_HANDLER, new Messenger(handler));
        startService(startServiceIntent);
    }

    @Override
    public void onReceiveResult(Message message) {
        switch (message.what) {
            case NetworkSchedulerService.NETWORK_CONNECTED:
                mAppNavigator.dismissSnackMessage();
                break;
            case NetworkSchedulerService.NETWORK_DISCONNECTED:
                mAppNavigator.showNoNetworkSnackMessage();
                break;
        }
    }
}
