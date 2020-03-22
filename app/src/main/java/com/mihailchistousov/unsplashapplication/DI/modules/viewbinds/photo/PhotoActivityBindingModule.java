package com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.photo;

import androidx.lifecycle.ViewModelProvider;

import com.mihailchistousov.unsplashapplication.Util.ViewModelFactory;
import com.mihailchistousov.unsplashapplication.ui.detail.PhotoActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoActivityBindingModule {
    @Provides
    ViewModelProvider provideViewModule(PhotoActivity activity, ViewModelFactory factory) {
        return new ViewModelProvider(activity,factory);

    }
}
