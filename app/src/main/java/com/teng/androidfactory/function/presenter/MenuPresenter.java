package com.teng.androidfactory.function.presenter;

import com.teng.androidfactory.common.base.basePresenter.BasePresenter;
import com.teng.androidfactory.function.model.HttpResult;
import com.teng.androidfactory.function.model.MenuModel;
import com.teng.androidfactory.function.view.MainView;

import java.util.Map;

import rx.Observable;

/**
 * Created by teng on 16/8/22.
 */
public class MenuPresenter extends BasePresenter{
    private MainView mMainView;

    public MenuPresenter(MainView mainView) {
        mMainView = mainView;
    }

    @Override
    protected void onAllSuccess(Object o) {
        HttpResult<MenuModel> entity = (HttpResult<MenuModel>) o;
        if (null != entity.getResult() && entity.getResult().getData().size() > 0) {
            if (mode == RequestMode.FRIST) {
                mMainView.showFinishDates(entity.getResult().getData());
            } else if (mode == RequestMode.LOAD_MORE) {
                mMainView.loadMoreFinish(entity.getResult().getData());
            } else if (mode == RequestMode.REFRESH) {
                mMainView.showRefreshFinish(entity.getResult().getData());
            }
        } else {
            if (mode == RequestMode.LOAD_MORE) {
                mMainView.hasNoMoreDate();
            } else {
                mMainView.showEmptyView(null);
            }
        }
    }

    @Override
    protected void onFail() {
        if(mode == RequestMode.FRIST){
            mMainView.showNetError();
        }else{
            mMainView.showToastError();
        }
    }


    @Override
    protected Observable getObservable(Map<String, String> params) {
        return getService().getMenuList(params.get("menu") , params.get("pn") , params.get("rn") , "bfea5cd2bb0e4f51142f72a2f0c6303f");
    }

}
