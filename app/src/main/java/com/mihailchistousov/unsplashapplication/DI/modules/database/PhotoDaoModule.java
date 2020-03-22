package com.mihailchistousov.unsplashapplication.DI.modules.database;

import com.mihailchistousov.unsplashapplication.data.DataBase.AppDataBase;
import com.mihailchistousov.unsplashapplication.data.DataBase.PhotoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = DataBaseModule.class)
public class PhotoDaoModule {
    @Singleton
    @Provides
    PhotoDao providePhotoDao(AppDataBase appDataBase) {return appDataBase.photoDao();}

}
