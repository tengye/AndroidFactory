package com.teng.androidfactory.function.fragments;


import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.teng.androidfactory.R;
import com.teng.androidfactory.common.base.baseFragment.BaseListFragment;
import com.teng.androidfactory.common.base.baseHolder.BaseViewHolder;
import com.teng.androidfactory.common.base.basePresenter.BasePresenter;
import com.teng.androidfactory.function.model.MenuModel;
import com.teng.androidfactory.function.presenter.MenuPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by teng on 16/8/22.
 */
public class MenuFragment extends BaseListFragment<MenuPresenter, MenuModel.Menu> {

    @BindView(R.id.edit_text)
    EditText editText;

    @BindView(R.id.find)
    Button find;

    @Override
    protected void baseInitView() {
        super.baseInitView();

    }

    @Override
    protected void baseInit() {
        // 默认的参数
        params.put("menu" , "虾");
        params.put("pn" , PAGE * 20 + 1+ "");
        params.put("rn" , 20+"");
        super.baseInit();
    }

    @OnClick(R.id.find)
    public void findData(View view){

        String s = editText.getText().toString();

        if (!TextUtils.isEmpty(s)){
            PAGE = 0;
            getRequestParams(s);
            mPresenter.requestDate(getRequestParams(), BasePresenter.RequestMode.REFRESH);
        }

    }

    @Override
    protected void fitDates(BaseViewHolder helper, MenuModel.Menu item) {
        helper.setText(R.id.text , item.getImtro())
                .setImageUrl(R.id.image , this , item.getAlbums().get(0));
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_menu;
    }


    protected void getRequestParams(String menu) {
        params.clear();
        params.put("menu" , menu);
        params.put("pn" , PAGE * 20 + 1+ "");
        params.put("rn" , 20+"");
    }

    @Override
    protected MenuPresenter getChildPresenter() {
        return new MenuPresenter(this);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_menu;
    }
}
