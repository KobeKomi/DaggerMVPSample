package com.komi.dagger.becompare;

import com.komi.dagger.entity.Apple;
import com.komi.dagger.entity.AppleInfo;
import com.komi.dagger.entity.Fruit;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Komi on 2016/8/3.
 */

@Module
public class NewModule {

    @Provides
    public Fruit provideHFSApple(AppleInfo appleInfo){
        return new Apple(appleInfo);
    }


}
