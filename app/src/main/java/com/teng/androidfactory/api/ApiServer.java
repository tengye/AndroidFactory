package com.teng.androidfactory.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by teng on 16/8/11.
 */
public final class ApiServer {

    private static final int DEFAULT_TIMEOUT = 5;

    private ApiServer() {

    }


    private static Retrofit instance;

    public static Retrofit getInstance() {
        synchronized (Retrofit.class) {
            if (instance == null) {
                //手动创建一个OkHttpClient并设置超时时间
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
                instance = new Retrofit.Builder()
                        .client(builder.build())
                        .baseUrl(ApiConstant.SERVER_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
                        .build();
            }
        }
        return instance;
    }


    public static <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

}
