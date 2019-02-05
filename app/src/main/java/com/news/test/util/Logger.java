/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.util;

import android.util.Log;

import com.news.test.BuildConfig;

/**
 * Custom utility log class to display log messages
 */
public class Logger {

    private static boolean isDebug() {
        Log.i("DEBUG Enabled", "" + BuildConfig.DEBUG);
        return BuildConfig.DEBUG;
    }

    public static void i(String tag, String msg) {
        android.util.Log.i(tag, msg);
    }


}