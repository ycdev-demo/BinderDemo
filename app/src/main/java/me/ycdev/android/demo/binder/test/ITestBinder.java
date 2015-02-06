package me.ycdev.android.demo.binder.test;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface ITestBinder extends IInterface {
    static final String DESCRIPTOR = "me.ycdev.android.demo.binder.test.ITestBinder";

    static final int INCREASE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION;
    static final int PRINT_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 1;
    static final int TEST_CRASH_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 2;
    static final int TEST_CRASH2_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 3;

    public int increase(int value) throws RemoteException;
    public void print(String value) throws RemoteException;
    public String testCrash() throws RemoteException;
    public String testCrash2() throws RemoteException;
}
