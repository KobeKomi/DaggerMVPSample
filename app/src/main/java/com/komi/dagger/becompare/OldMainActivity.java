package com.komi.dagger.becompare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.komi.dagger.R;
import com.komi.dagger.entity.Apple;
import com.komi.dagger.entity.AppleInfo;

public class OldMainActivity extends AppCompatActivity  {


    private static final String TAG = "KOMI";

    public Apple mHfsApple;
    public AppleInfo mAppleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppleInfo=new AppleInfo();
        mHfsApple=new Apple(mAppleInfo);

        Log.i(TAG,"-----:"+mHfsApple.toString());
    }
}
