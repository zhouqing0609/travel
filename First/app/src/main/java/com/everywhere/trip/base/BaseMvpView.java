package com.everywhere.trip.base;


public interface BaseMvpView {
    //显示加载loading的方法
    void showLoading();
    //隐藏加载loading的方法
    void hideLoading();
    //显示加载loading的方法
    void toastShort(String msg);
}
