package com.mihailchistousov.unsplashapplication.ui.main.fav;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.mihailchistousov.unsplashapplication.data.Repository;
import com.mihailchistousov.unsplashapplication.model.Photo;

import java.util.List;

import javax.inject.Inject;

public class FavViewModel extends ViewModel {

    private final Repository repository;
    private MediatorLiveData<List<Photo>> photos = new MediatorLiveData<>();

    @Inject
    public FavViewModel(Repository repository) {
        this.repository = repository;

    }

    public void get_list_photos() {
        final LiveData<List<Photo>> source = LiveDataReactiveStreams.fromPublisher(
            repository.get_from_DB_all_photos()
        );
        photos.addSource(source, photos -> {
            this.photos.setValue(photos);
            this.photos.removeSource(source);
        });

    }

    public LiveData<List<Photo>> observe_photos() {
        return photos;
    }

    public void delete_from_db(Photo photo) {
        repository.delete_from_db(photo);
    }
}