package com.mihailchistousov.unsplashapplication.data;

import com.mihailchistousov.unsplashapplication.data.Network.UnsplashAPI;
import com.mihailchistousov.unsplashapplication.model.Photo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class RetrofitData {

    private final UnsplashAPI API;
    private final String KEY = "a9911b96cdcb6fe46785e02a61c5e37e88043e035d65929a8fe4849aeede9c66";

    @Inject
    public RetrofitData(UnsplashAPI API){
        this.API = API;
    }

    public Flowable<List<Photo>> get_photos_from_API() {
        return API.getPhotos(1,KEY)
                .mergeWith(API.getPhotos(2,KEY))
                .subscribeOn(Schedulers.io());
    }

    public Flowable<Photo> get_from_API_current_photo(String id) {
        return API.getCurrentPhotos(id,KEY)
                .subscribeOn(Schedulers.io());
    }

}
