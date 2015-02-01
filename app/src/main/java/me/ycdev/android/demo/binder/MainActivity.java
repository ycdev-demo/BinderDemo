package me.ycdev.android.demo.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.ycdev.android.demo.binder.test.ITestBinder;
import me.ycdev.android.demo.binder.test.TestBinderNative;
import me.ycdev.android.demo.binder.utils.AppLogger;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AppLogger.i(TAG, "service connected: " + service);
            ITestBinder testBinder = TestBinderNative.asInterface(service);
            try {
                testBinder.print("test string");
            } catch (RemoteException e) {
                AppLogger.w(TAG, "failed to invoke the service", e);
            }
            AppLogger.i(TAG, "ITestBinder: " + testBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppLogger.d(TAG, "#onCreate");

        findViewById(R.id.btn_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMyActivity();
            }
        });
        test();
    }

    private void goToMyActivity() {
        Intent intent = new Intent(this, MyActivity.class);
        startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
