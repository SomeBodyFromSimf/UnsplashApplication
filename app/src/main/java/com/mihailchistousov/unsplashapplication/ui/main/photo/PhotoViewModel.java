package com.mihailchistousov.unsplashapplication.ui.main.photo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.mihailchistousov.unsplashapplication.R;
import com.mihailchistousov.unsplashapplication.data.Network.Resource;
import com.mihailchistousov.unsplashapplication.data.Repository;
import com.mihailchistousov.unsplashapplication.model.Photo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class PhotoViewModel extends ViewModel {

    private final Repository repository;
    private final Context context;

    private MediatorLiveData<Resource<List<Photo>>> photos = new MediatorLiveData<>();
    @Inject
    public PhotoViewModel(Repository repository, Context context) {
        this.context = context;
        this.repository = repository;
    }

    public void get_list_photos() {
        photos.setValue(Resource.loading((List<Photo>)null));

        final LiveData<Resource<List<Photo>>> source = LiveDataReactiveStreams.fromPublisher(
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
        );
        photos.addSource(source, resource_photos -> {
            photos.setValue(resource_photos);
            photos.removeSource(source);
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