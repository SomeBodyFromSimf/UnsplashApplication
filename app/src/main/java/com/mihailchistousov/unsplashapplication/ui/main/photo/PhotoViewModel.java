package com.mihailchistousov.unsplashapplication.ui.main.photo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.mihailchistousov.unsplashapplication.R;
import com.mihailchistousov.unsplashapplication.data.Network.Resource;
import com.mihailchistousov.unsplashapplication.data.Repository;
import com.mihailchistousov.unsplashapplication.model.Photo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PhotoViewModel extends ViewModel {

    private final Repository repository;
    private final Context context;

    private MediatorLiveData<Resource<List<Photo>>> photos = new MediatorLiveData<>();
    @Inject
    public PhotoViewModel(Repository repository, Context context) {
        this.context = context;
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void get_list_photos() {
        List<Photo> listPhotos = new ArrayList<>();
        photos.setValue(Resource.loading((List<Photo>)null));
        repository.get_photos_from_API()
                .onErrorReturn(throwable -> {
                    Photo photo = new Photo();
                    photo.setId("-1");
                    List<Photo> photos = new ArrayList<>();
                    photos.add(photo);
                    return photos;
                })
                .map(photos -> {
                    if(photos.get(0).getId() == "-1") {
                        return Resource.error(context.getResources().getString(R.string.error_load_photos),null);
                    }
                    return Resource.success(photos);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    if(!listPhotos.isEmpty()) {
                        photos.setValue(Resource.success(listPhotos));
                    }
                    else photos.setValue(Resource.error(context.getResources().getString(R.string.error_load_photos),null));
                })
                .subscribe(resource -> {
                    if(resource.data != null)
                        listPhotos.addAll((Collection<? extends Photo>) resource.data);
                });
    }

    public LiveData<Resource<List<Photo>>> observe_photos() {
        return photos;
    }

    public void save_to_db(Photo photo) {
        repository.save_to_db(photo);
    }

    public void delete_from_db(Photo photo) {
        repository.delete_from_db(photo);
    }

    public Flowable<Boolean> find_photo_in_db(Photo photo) {
        return repository.check_like_in_db(photo);
    }
}