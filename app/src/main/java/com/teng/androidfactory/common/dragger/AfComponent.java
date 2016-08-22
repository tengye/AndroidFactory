package com.teng.androidfactory.common.dragger;

import com.teng.androidfactory.common.base.basePresenter.BasePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by teng on 16/8/22.
 */
@Singleton
@Component(modules = AfModules.class)
public interface AfComponent {

    void inject(BasePresenter basePresenter);

    final class AfComponentInitialize{
        public  static AfComponent init(){
            return DaggerAfComponent.builder().build();
        }
    }

}
