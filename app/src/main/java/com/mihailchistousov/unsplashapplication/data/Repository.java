package com.mihailchistousov.unsplashapplication.data;

import com.mihailchistousov.unsplashapplication.data.DataBase.PhotoDao;
import com.mihailchistousov.unsplashapplication.data.Network.UnsplashAPI;
import com.mihailchistousov.unsplashapplication.model.Photo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Singleton
public class Repository {


    private final RetrofitData retrofit;
    private final DatabaseData database;

    //Initialize method`s
    @Inject
    public Repository(RetrofitData retrofit, DatabaseData database) {
        this.retrofit = retrofit;
        this.database = database;
    }

    //Retrofit method`s
    public Observable<List<Photo>> get_photos_from_API() {
        return retrofit.get_photos_from_API();
    }

    public Flowable<Photo> get_from_API_current_photo(String id) {
        return retrofit.get_from_API_current_photo(id);
    }


    //Room method`s
    public void save_to_db(Photo photo) {
        database.save_to_db(photo);
    }

    public void delete_from_db(Photo photo) {
        database.delete_from_db(photo);
    }

    public Flowable<Boolean> check_like_in_db(Photo photo) {
        return database.check_like_in_db(photo);
    }

    public Flowable<List<Photo>> get_from_DB_all_photos() {
        return database.get_from_DB_all_photos();
    }
}
