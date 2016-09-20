package com.teng.androidfactory.function.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teng.androidfactory.R;
import com.teng.androidfactory.function.view.moveSwipeItem.ItemTouchHelperAdapter;
import com.teng.androidfactory.function.view.moveSwipeItem.ItemTouchHelperViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by teng on 16/9/20.
 */
public class DragItemAdapter extends RecyclerView.Adapter<DragItemAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private Context context;
    private List<String> mData;

    public DragItemAdapter(Context context, List<String> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.textView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mData, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {

    }

    @Override
    public boolean canDrag(int position) {
        return true;
    }

    @Override
    public boolean canSwipe(int position) {
        return false;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
