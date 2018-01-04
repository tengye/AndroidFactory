package com.teng.androidfactory.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.teng.androidfactory.IBook;

/**
 * Created by teng on 18/1/4.
 */

public class AidlService extends Service {

    String bookName = "book name";

    IBook.Stub mStub = new IBook.Stub() {
        @Override
        public String queryBook(int bookNo) throws RemoteException {
            return bookName;
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
