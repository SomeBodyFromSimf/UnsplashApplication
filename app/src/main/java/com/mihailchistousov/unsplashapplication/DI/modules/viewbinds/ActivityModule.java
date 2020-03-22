package com.mihailchistousov.unsplashapplication.DI.modules.viewbinds;


import com.mihailchistousov.unsplashapplication.DI.Utils.ActivityScope;
import com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.main.FragmentsBindingModule;
import com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.main.IntentBinding;
import com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.photo.PhotoActivityBindingModule;
import com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.splash.SplashActivityBindingModule;
import com.mihailchistousov.unsplashapplication.ui.SplashScreen.SplashActivity;
import com.mihailchistousov.unsplashapplication.ui.detail.PhotoActivity;
import com.mihailchistousov.unsplashapplication.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {FragmentsBindingModule.class, IntentBinding.class})
    abstract MainActivity bindMainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {PhotoActivityBindingModule.class})
    abstract PhotoActivity bindPhotoActivityActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {SplashActivityBindingModule.class})
    abstract SplashActivity bindSplashActivityActivity();
}
