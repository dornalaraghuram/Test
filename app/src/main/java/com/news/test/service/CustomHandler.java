/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.service;


import android.os.Handler;
import android.os.Message;

public class CustomHandler extends Handler {

    private AppReceiver appReceiver;

    public CustomHandler(AppReceiver receiver) {
        appReceiver = receiver;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        appReceiver.onReceiveResult(msg);
    }


    public interface AppReceiver {
        void onReceiveResult(Message message);
    }
}
