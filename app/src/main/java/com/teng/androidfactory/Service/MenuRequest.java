package com.teng.androidfactory.Service;

import com.teng.androidfactory.api.ApiServer;
import com.teng.androidfactory.model.MenuModel;
import com.teng.androidfactory.subscriber.HttpResultFunc;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by teng on 16/8/12.
 */
public class MenuRequest {
    MenuService menuService;
    public MenuRequest() {
         menuService = ApiServer.getInstance().create(MenuService.class);
    }

    public  void getMenuList(Subscriber<MenuModel> subscriber , String menu , String pn , String rn , String key ){

        Observable observable = menuService.getMenuList(menu, pn, rn, key)
                .map(new HttpResultFunc<MenuModel>());

        ApiServer.toSubscribe(observable, subscriber);

    }
}
