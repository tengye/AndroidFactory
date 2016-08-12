package com.teng.androidfactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.teng.androidfactory.Service.MenuRequest;
import com.teng.androidfactory.api.ApiServer;
import com.teng.androidfactory.subscriber.ProgressSubscriber;
import com.teng.androidfactory.subscriber.SubscriberOnNextListener;
import com.teng.androidfactory.model.MenuModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.content) TextView content;

    private SubscriberOnNextListener getMenuListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getMenuListener = new SubscriberOnNextListener<MenuModel>() {
            @Override
            public void onNext(MenuModel menuModel) {

                content.setText(menuModel.getData().get(0).getImtro());

            }
        };

        new MenuRequest().getMenuList(new ProgressSubscriber<MenuModel>(getMenuListener ,
                MainActivity.this),
                "西红柿" , "1" , "20" ,
                "bfea5cd2bb0e4f51142f72a2f0c6303f");

    }

}
