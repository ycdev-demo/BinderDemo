package me.ycdev.android.demo.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;

import me.ycdev.android.demo.binder.test.ITestBinder;
import me.ycdev.android.demo.binder.test.TestBinderNative;
import me.ycdev.android.demo.binder.utils.AppLogger;

public class MyActivity extends ActionBarActivity {
    private static final String TAG = "MyActivity";

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AppLogger.i(TAG, "service connected: " + service);
            ITestBinder testBinder = TestBinderNative.asInterface(service);
            int result = -1;
            try {
                result = testBinder.increase(10);
            } catch (RemoteException e) {
                AppLogger.w(TAG, "failed to invoke the service", e);
            }
            AppLogger.i(TAG, "ITestBinder: " + testBinder + ", result: " + result);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        AppLogger.d(TAG, "onCreate");

        test();
    }

    private void test() {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
