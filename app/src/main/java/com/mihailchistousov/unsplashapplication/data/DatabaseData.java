package com.mihailchistousov.unsplashapplication.data;

import com.mihailchistousov.unsplashapplication.data.DataBase.PhotoDao;
import com.mihailchistousov.unsplashapplication.model.Photo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DatabaseData {

    private final PhotoDao photoDao;
    @Inject
    public DatabaseData(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    public void save_to_db(Photo photo) {
        Completable.fromAction(() -> photoDao.add_photo(photo)).subscribeOn(Schedulers.io()).subscribe();
    }

    public void delete_from_db(Photo photo) {
        Completable.fromAction(() -> photoDao.delete_photo(photo)).subscribeOn(Schedulers.io()).subscribe();
    }

    public Flowable<Boolean> check_like_in_db(Photo photo) {
        return photoDao.get_photo_by_id(photo.getId())
                .map(integer -> integer!=0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<Photo>> get_from_DB_all_photos() {
        return photoDao.get_all_photos()
                .subscribeOn(Schedulers.io());
    }

}
