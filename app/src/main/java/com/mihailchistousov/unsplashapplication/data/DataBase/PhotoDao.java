package com.mihailchistousov.unsplashapplication.data.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mihailchistousov.unsplashapplication.model.Photo;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PhotoDao {
    @Query("SELECT * FROM unsplash_fav_photos")
    Flowable<List<Photo>> get_all_photos();

    @Query("SELECT COUNT(*) FROM unsplash_fav_photos WHERE id = :id LIMIT 1")
    Flowable<Integer> get_photo_by_id(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add_photo(Photo photo);

    @Delete
    void delete_photo(Photo photo);

}
