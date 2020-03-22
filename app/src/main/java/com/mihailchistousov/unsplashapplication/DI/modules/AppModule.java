package com.mihailchistousov.unsplashapplication.DI.modules;


import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.mihailchistousov.unsplashapplication.DI.modules.database.PhotoDaoModule;
import com.mihailchistousov.unsplashapplication.DI.modules.network.UnsplashApiModule;
import com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.ActivityModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        ContextModule.class,
        ActivityModule.class,
        UnsplashApiModule.class,
        PhotoDaoModule.class,
        ViewModelModule.class
})
public class AppModule {

    @Singleton
    @Provides
    RequestManager provideGlide(Context context) {
        return Glide.with(context);
    }

}
