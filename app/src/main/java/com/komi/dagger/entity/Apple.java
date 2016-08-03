package com.komi.dagger.entity;


public class Apple extends Fruit {

    public Apple(String color, String name){
        this.color = color;
        this.name=name;
    }

    public Apple(AppleInfo appleInfo){
        this.name = appleInfo.name;
        this.color = appleInfo.color;
    }
}
