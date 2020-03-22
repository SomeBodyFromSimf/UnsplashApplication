package com.mihailchistousov.unsplashapplication.base;

import com.mihailchistousov.unsplashapplication.DI.ApplicationComponent;
import com.mihailchistousov.unsplashapplication.DI.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        ApplicationComponent component = DaggerApplicationComponent.builder()
                .application(this)
                .url("https://api.unsplash.com/")
                .build();
        component.inject(this);

        return component;
    }
}
