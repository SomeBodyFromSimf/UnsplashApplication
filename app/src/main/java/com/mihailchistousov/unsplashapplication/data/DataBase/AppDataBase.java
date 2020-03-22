package com.mihailchistousov.unsplashapplication.data.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mihailchistousov.unsplashapplication.model.Photo;


@Database(entities = {Photo.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PhotoDao photoDao();
}
