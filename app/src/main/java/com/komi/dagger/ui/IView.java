package com.komi.dagger.ui;

/**
 * view的根接口，所有的view都要实现这个接口，定义一些通用的数据操作
 */
public interface IView {

    void setData(Integer result);  // 设置数据，显示到界面

    void showDialog();          // 显示下载进度对话框

    void setDialogProgress(Integer value);

    void cancelDialog();
}
