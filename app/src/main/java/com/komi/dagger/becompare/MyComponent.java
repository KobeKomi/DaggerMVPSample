package com.komi.dagger.becompare;


import dagger.Component;


@Component(modules = {NewModule.class})
public interface MyComponent {

    void inject(NewMainActivity activity);

}
