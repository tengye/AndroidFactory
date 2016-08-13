package com.teng.androidfactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.teng.androidfactory.Service.MenuRequest;
import com.teng.androidfactory.adapter.MenuAdapter;
import com.teng.androidfactory.model.MenuModel;
import com.teng.androidfactory.subscriber.ProgressSubscriber;
import com.teng.androidfactory.subscriber.SubscriberOnNextListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    private List<MenuModel.Menu> menuList = new ArrayList<>();

    private SubscriberOnNextListener getMenuListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false);
        recycler.setLayoutManager(linearLayoutManager);

        final MenuAdapter adapter = new MenuAdapter(menuList , this);
        recycler.setAdapter(adapter);

        getMenuListener = new SubscriberOnNextListener<MenuModel>() {
            @Override
            public void onNext(MenuModel menuModel) {
                menuList.clear();
                menuList.addAll(menuModel.getData());
                adapter.notifyDataSetChanged();
            }
        };


        new MenuRequest().getMenuList(new ProgressSubscriber<MenuModel>(getMenuListener ,
                        MenuActivity.this),
                "虾" , "1" , "20" ,
                "bfea5cd2bb0e4f51142f72a2f0c6303f");
    }
}
