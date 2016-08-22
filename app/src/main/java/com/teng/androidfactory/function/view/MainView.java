package com.teng.androidfactory.function.view;


import com.teng.androidfactory.common.base.baseView.BaseView;

import java.util.List;

public interface MainView<T> extends BaseView {

    void showFinishDates(List<T> dates);
}
