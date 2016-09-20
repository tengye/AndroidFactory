package com.teng.androidfactory.function.view.moveSwipeItem;

/**
 * Created by teng on 16/8/2.
 */
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);

    boolean canDrag(int position);
    boolean canSwipe(int position);
}
