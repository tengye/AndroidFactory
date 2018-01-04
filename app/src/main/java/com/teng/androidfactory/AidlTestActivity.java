package com.teng.androidfactory;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.teng.androidfactory.binder.AidlService;

/**
 * Created by teng on 18/1/4.
 */

public class AidlTestActivity extends FragmentActivity {

    private IBook iBook;
    private BookConnection bookConnection = new BookConnection();
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotfix);
        textView = (TextView) findViewById(R.id.text);
        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, bookConnection, BIND_AUTO_CREATE);
    }


    private final class BookConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBook = IBook.Stub.asInterface(service);

            try {
                String text = iBook.queryBook(0);
                if (text != null) {
                    textView.setText(text);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iBook = null;
        }
    }
}
