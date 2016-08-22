package com.teng.androidfactory.function.fragments;


import com.teng.androidfactory.R;
import com.teng.androidfactory.common.base.baseFragment.BaseListFragment;
import com.teng.androidfactory.common.base.baseHolder.BaseViewHolder;
import com.teng.androidfactory.function.model.MenuModel;
import com.teng.androidfactory.function.presenter.MenuPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by teng on 16/8/22.
 */
public class MenuFragment extends BaseListFragment<MenuPresenter, MenuModel.Menu> {

    @Override
    protected void fitDates(BaseViewHolder helper, MenuModel.Menu item) {
        helper.setText(R.id.text , item.getImtro())
                .setImageUrl(R.id.image , this , item.getAlbums().get(0));
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_menu;
    }

    @Override
    protected Map<String, String> getRequestParams() {

        Map<String , String> map = new HashMap<>();
        map.put("menu" , "虾");
        map.put("pn" , PAGE * 20 + 1+ "");
        map.put("rn" , 20+"");
        return map;
    }


    @Override
    protected MenuPresenter getChildPresenter() {
        return new MenuPresenter(this);
    }

//    @Override
//    protected int getContentLayout() {
//        return R.layout.fragment_menu;
//    }
}
