package com.example.presentation.util;

import android.util.Log;

public class LogUtil {
    private static final String TAG = "TAOQI_";

    public static void i(String tag, String message) {
        Log.i(TAG + tag, message);
    }

    public static void d(String tag, String message) {
        Log.d(TAG + tag, message);
    }

    public static void w(String tag, String message) {
        Log.w(TAG + tag, message);
    }

    public static void e(String tag, String message) {
        Log.e(TAG + tag, message);
    }
}
