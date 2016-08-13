package com.teng.androidfactory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teng.androidfactory.R;
import com.teng.androidfactory.model.MenuModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teng on 16/8/12.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<MenuModel.Menu> mData = new ArrayList<>();
    private Context context;

    public MenuAdapter(List<MenuModel.Menu> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MenuViewHolder menuViewHolder =
                new MenuViewHolder(LayoutInflater.from(context).inflate(R.layout.item_menu , parent , false));
        return menuViewHolder;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        MenuModel.Menu menu = mData.get(position);

        holder.text.setText(menu.getImtro());

        Glide.with(context).load(menu.getAlbums().get(0)).asBitmap().into(holder.image);
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{

        TextView text;
        ImageView image;

        public MenuViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            image = (ImageView) itemView.findViewById(R.id.image);
        }

    }

}
