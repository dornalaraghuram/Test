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

    public static void e(String tag, String msg) {
        if (isDebug()) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable e) {
        if (isDebug()) {
            android.util.Log.e(tag, msg, e);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug()) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable e) {
        if (isDebug()) {
            android.util.Log.d(tag, msg, e);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug()) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug()) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable e) {
        if (isDebug()) {
            android.util.Log.w(tag, msg, e);
        }
    }
}