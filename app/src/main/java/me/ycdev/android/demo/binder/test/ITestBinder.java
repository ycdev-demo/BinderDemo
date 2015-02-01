package me.ycdev.android.demo.binder.test;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface ITestBinder extends IInterface {
    static final String DESCRIPTOR = "me.ycdev.android.demo.binder.test.ITestBinder";

    static final int INCREASE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION;
    static final int PRINT_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 1;

    public int increase(int value) throws RemoteException;
    public void print(String value) throws RemoteException;
}
