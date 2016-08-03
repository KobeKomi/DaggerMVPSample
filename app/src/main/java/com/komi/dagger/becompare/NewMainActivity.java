package com.komi.dagger.becompare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.komi.dagger.R;
import com.komi.dagger.entity.Fruit;

import javax.inject.Inject;

public class NewMainActivity extends AppCompatActivity  {


    private static final String TAG = "KOMI";


    //NewMainActivity的成员变量就自动初始化成Apple实例了，
    //NewMainActivity不用关心具体用哪个Fruit的实现，
    // 也不用关心到底用什么颜色多大的苹果。假如某一天要把苹果替换成香蕉，
    // NewMainActivity的代码是完全不需要改动的。
    // 从某种意义上说，Dagger2就是一个帮你写工厂代码的工具。当然Dagger2的功能比工厂模式更加强大。
    @Inject
    public Fruit mHfsApple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMyComponent.create().inject(this);

        Log.i(TAG,"-----:"+mHfsApple.toString());
    }
}
