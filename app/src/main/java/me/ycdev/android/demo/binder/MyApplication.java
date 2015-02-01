package me.ycdev.android.demo.binder;

import android.app.Application;

import me.ycdev.android.demo.binder.utils.AppLogger;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        AppLogger.d(TAG, "#onCreate");
    }
}
