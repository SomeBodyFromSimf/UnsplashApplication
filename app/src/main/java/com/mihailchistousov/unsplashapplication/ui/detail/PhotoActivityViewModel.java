package com.mihailchistousov.unsplashapplication.ui.detail;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.mihailchistousov.unsplashapplication.R;
import com.mihailchistousov.unsplashapplication.data.Network.Resource;
import com.mihailchistousov.unsplashapplication.data.Repository;
import com.mihailchistousov.unsplashapplication.model.Photo;

import javax.inject.Inject;

public class PhotoActivityViewModel extends ViewModel {

    private final Repository repository;
    private final Context context;

    private MediatorLiveData<Resource<Photo>> resource_photo = new MediatorLiveData<>();

    @Inject
    public PhotoActivityViewModel(Repository repository,Context context) {
        this.repository = repository;
        this.context = context;
    }

    public void get_from_API_current_photo(String id) {
        resource_photo.setValue(Resource.loading(null));

        final LiveData<Resource<Photo>> source = LiveDataReactiveStreams.fromPublisher(
            repository.get_from_API_current_photo(id)
                .onErrorReturn(throwable -> {
                    Photo photo = new Photo();
                    photo.setId("-1");
                    return photo;
                })
                .map(photo -> {
                    if(photo.getId() == "-1") {
                        return Resource.error(context.getResources().getString(R.string.error_load_photo),null);
                    }
                    return Resource.success(photo);
                })
        );
        resource_photo.addSource(source, photo -> {
            resource_photo.setValue(photo);
            resource_photo.removeSource(source);
        });
    }

    public LiveData<Resource<Photo>> observe_photo() {
        return resource_photo;
    }

    public void delete_from_db(Photo photo) {
        repository.delete_from_db(photo);
    }

    public void save_to_db(Photo photo) {
        repository.save_to_db(photo);
    }
}
