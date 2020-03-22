package com.mihailchistousov.unsplashapplication.DI.modules.network;

import com.mihailchistousov.unsplashapplication.data.Network.UnsplashAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class UnsplashApiModule {
    @Singleton
    @Provides
    UnsplashAPI provideUnsplashAPI(Retrofit retrofit) {
        return retrofit.create(UnsplashAPI.class);
    }

}
