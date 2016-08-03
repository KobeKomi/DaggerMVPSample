package com.komi.dragger.presenter;


import com.komi.dragger.ui.IView;

public interface IPresenter {

    void onCreate(IView view);        // 初始化数据

    void performOnClick();  // 点击事件
}
