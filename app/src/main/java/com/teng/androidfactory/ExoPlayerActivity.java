package com.teng.androidfactory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.teng.androidfactory.function.fragments.MenuFragment;

/**
 * Created by teng on 16/9/2.
 */
public class ExoPlayerActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);


        getSupportFragmentManager().beginTransaction().add(R.id.container , new MenuFragment()).commit();


    }
}
