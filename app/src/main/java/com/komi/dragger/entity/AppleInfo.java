package com.komi.dragger.entity;

import javax.inject.Inject;


public class AppleInfo {

    String name;
    String color;
    /**
     * 如果该构造方法被@Inject标注，那么就可以被component在使用module初始化实例的时候使用
     */
    @Inject
    public AppleInfo() {
        name = "红富士";
        color= "红色";
    }
}
