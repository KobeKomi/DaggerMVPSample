package com.komi.dragger.model;

import android.os.SystemClock;
import android.util.Log;


public class Model implements IModel {


    @Override
    public void getDataFromNet() {
        // 4.model层获取数据
        SystemClock.sleep(200);
        Log.i("Model", "getDataFromNet");
    }
}
