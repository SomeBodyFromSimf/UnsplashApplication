package com.mihailchistousov.unsplashapplication.DI.modules.database;

import android.content.Context;

import androidx.room.Room;

import com.mihailchistousov.unsplashapplication.data.DataBase.AppDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {
    @Singleton
    @Provides
    AppDataBase provideAppDataBase(Context context) {
        return Room.databaseBuilder(context,AppDataBase.class,"database").build();
    }


}
