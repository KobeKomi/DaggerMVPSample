package com.komi.dagger.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Moudle - 依赖提供者
 * 作用：Module其实就是一个依赖的制造工厂，内部包含提供各种依赖的方法。
 *
 * 本身添加其他module：@Module(includes={ModuleA.class,ModuleB.class,ModuleC.class})
 */
@Module
public class FieldModule {

    public FieldModule(){
    }

    @Named("name")
    @Provides
    public String provideName(){
        return "yitu";
    }

    @Named("nickname")
    @Provides
    public String provideNickname(){
        return "KOMI";
    }

    @Provides
    public String providePassword(){
        return "123456789";
    }
}
