package com.teng.androidfactory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.teng.androidfactory.function.adapter.DragItemAdapter;
import com.teng.androidfactory.function.view.moveSwipeItem.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by teng on 16/9/13.
 */
public class DragItemActivity extends FragmentActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_item);
        ButterKnife.bind(this);

        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }

        DragItemAdapter adapter = new DragItemAdapter(this , list);
        recycler.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        recycler.setAdapter(adapter);


        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recycler);


    }

}
