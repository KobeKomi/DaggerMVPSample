package com.komi.dagger.model;

import android.os.SystemClock;
import android.util.Log;


public class Model implements IModel {


    public long sleepTime;


    @Override
    public void getDataFromNet() {
        // 4.model层获取数据
        SystemClock.sleep(200);
        Log.i("Model", "getDataFromNet");
    }
}
