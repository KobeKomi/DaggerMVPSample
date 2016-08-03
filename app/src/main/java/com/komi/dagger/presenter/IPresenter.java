package com.komi.dagger.presenter;


import com.komi.dagger.ui.IView;

public interface IPresenter {

    void onCreate(IView view);        // 初始化数据

    void performOnClick();  // 点击事件
}
