package com.teng.androidfactory.common.base.baseFragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.teng.androidfactory.R;
import com.teng.androidfactory.common.base.baseAdapter.RecycleViewAdapter;
import com.teng.androidfactory.common.base.baseHolder.BaseViewHolder;
import com.teng.androidfactory.common.base.basePresenter.BasePresenter;
import com.teng.androidfactory.common.refresh.ProgressStyle;
import com.teng.androidfactory.common.refresh.XRecyclerView;
import com.teng.androidfactory.function.view.MainView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class BaseListFragment<T extends BasePresenter, K> extends
        BaseFragment<T> implements
        MainView<K>, XRecyclerView.LoadingListener {

    XRecyclerView mRecyclerView;

    protected RecycleViewAdapter<K> mAdapter;

    protected Map<String, String> params = new HashMap<>();;

    protected int PAGE = 1;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_main_common;
    }

    @Override
    protected void baseInit() {
        showLoading();
        mPresenter.requestDate(getRequestParams(), BasePresenter.RequestMode.FRIST);
    }

    @Override
    protected void baseInitView() {
        mRecyclerView = (XRecyclerView) mRootView.findViewById(R.id.recycleView);
    }

    @Override
    protected Map<String, String> getRequestParams() {
//        params.put("mechanismId", "1");
//        params.put("pageNum", PAGE + "");
        return params;
    }

    @Override
    protected View getLoadingTargetView() {
        return mRecyclerView;
    }


    @Override
    public void showFinishDates(List<K> dates) {
        refreshView();
        if (mAdapter == null) {
            mAdapter = new RecycleViewAdapter<K>(getItemLayout(), dates) {

                @Override
                protected void convert(BaseViewHolder helper, K item) {
                    fitDates(helper, item);
                }
            };

            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
            mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
            mRecyclerView.setLoadingListener(this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mAdapter.setOnRecyclerViewItemChildClickListener(
                (RecycleViewAdapter adapter, View v,int position) -> onItemClick(v,position));
    }

    protected void onItemClick(View v, int position) {

    }

    @Override
    public void hasNoMoreDate() {
        mRecyclerView.setNoMore(true);
        PAGE = PAGE - 1;
    }

    protected abstract void fitDates(BaseViewHolder helper, K item);

    protected abstract int getItemLayout();

    @Override
    public void loadMoreFinish(List dates) {
        mRecyclerView.loadMoreComplete();
        mAdapter.addDataAndNotify(dates);
    }

    @Override
    public void showRefreshFinish(List score) {
        mRecyclerView.refreshComplete();
        mAdapter.setNewData(score);
    }

    @Override
    public void showToastError() {
        mRecyclerView.reset();
        PAGE = PAGE - 1;
        Toast.makeText(mContext, "网络环境不好", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        PAGE = 1;
        mPresenter.requestDate(getRequestParams(), BasePresenter.RequestMode.REFRESH);
    }

    @Override
    public void onLoadMore() {
        PAGE = PAGE + 1;
        mPresenter.requestDate(getRequestParams(), BasePresenter.RequestMode.LOAD_MORE);
    }
}
