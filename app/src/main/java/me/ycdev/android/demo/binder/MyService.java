package me.ycdev.android.demo.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import me.ycdev.android.demo.binder.test.TestBinderNative;
import me.ycdev.android.demo.binder.utils.AppLogger;

public class MyService extends Service {
    private static final String TAG = "MyService";

    private IBinder mTestBinder = new TestBinderNative();

    @Override
    public IBinder onBind(Intent intent) {
        AppLogger.i(TAG, "onBind, service binder: " + mTestBinder);
        return mTestBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppLogger.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppLogger.i(TAG, "onDestroy");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        AppLogger.i(TAG, "onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        AppLogger.i(TAG, "onUnbind: " + intent);
        return super.onUnbind(intent);
//        return true;
    }
}
