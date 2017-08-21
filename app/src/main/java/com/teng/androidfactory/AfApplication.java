package com.teng.androidfactory;

import android.app.Application;
import android.content.Context;

import com.teng.androidfactory.common.dragger.AfComponent;

/**
 * Created by teng on 16/8/22.
 */
public class AfApplication extends Application {

    private AfComponent mComponent;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mComponent = AfComponent.AfComponentInitialize.init();

//        if(BuildConfig.DEBUG){
//            if (BuildConfig.DEBUG) {
//                LayoutCast.init(this);
//            }
//        }

    }

    public static AfComponent getComponent(){
        return ((AfApplication)mContext.getApplicationContext()).mComponent;
    }

}
