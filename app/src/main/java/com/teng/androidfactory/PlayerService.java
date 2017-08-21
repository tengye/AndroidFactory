package com.teng.androidfactory;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by teng on 17/8/9.
 */

public class PlayerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    void method(){
        // 供绑定service的类调用的方法
    }

    class MyBinder extends Binder implements IMyBinder{

        @Override
        public void invokeMethodMyService() {
            method();
        }
    }

    public interface IMyBinder{
        void invokeMethodMyService();
    }

}
