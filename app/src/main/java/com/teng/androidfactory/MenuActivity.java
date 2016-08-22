package com.teng.androidfactory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.teng.androidfactory.function.fragments.MenuFragment;
import com.teng.androidfactory.function.model.MenuModel;
import com.teng.androidfactory.function.subscriber.SubscriberOnNextListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MenuActivity extends FragmentActivity {


    private List<MenuModel.Menu> menuList = new ArrayList<>();

    private SubscriberOnNextListener getMenuListener;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        unbinder = ButterKnife.bind(this);


        getSupportFragmentManager().beginTransaction().add(R.id.container , new MenuFragment()).commit();

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false);
//        recycler.setLayoutManager(linearLayoutManager);
//
//        final MenuAdapter adapter = new MenuAdapter(menuList , this);
//        recycler.setAdapter(adapter);
//
//        getMenuListener = new SubscriberOnNextListener<MenuModel>() {
//            @Override
//            public void onNext(MenuModel menuModel) {
//                menuList.clear();
//                menuList.addAll(menuModel.getData());
//                adapter.notifyDataSetChanged();
//            }
//        };
//
//
//        new MenuRequest().getMenuList(new ProgressSubscriber<MenuModel>(getMenuListener ,
//                        MenuActivity.this),
//                "虾" , "1" , "20" ,
//                "bfea5cd2bb0e4f51142f72a2f0c6303f");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
