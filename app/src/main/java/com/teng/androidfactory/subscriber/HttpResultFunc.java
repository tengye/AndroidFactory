package com.teng.androidfactory.subscriber;

/**
 * Created by teng on 16/8/12.
 */

import com.teng.androidfactory.api.ApiException;
import com.teng.androidfactory.model.HttpResult;

import rx.functions.Func1;

/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getResultcode() > 400) {
            throw new ApiException(100);
        }
        return httpResult.getResult();
    }
}

