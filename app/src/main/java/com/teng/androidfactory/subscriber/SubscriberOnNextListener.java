package com.teng.androidfactory.subscriber;

/**
 *
 * @param <T>
 *
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
