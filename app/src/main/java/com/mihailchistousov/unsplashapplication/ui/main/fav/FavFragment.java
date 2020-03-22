package com.mihailchistousov.unsplashapplication.ui.main.fav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mihailchistousov.unsplashapplication.base.BaseFragment;
import com.mihailchistousov.unsplashapplication.base.Responsible_interfaces.ResponsibleFav;
import com.mihailchistousov.unsplashapplication.model.Photo;

import java.util.List;

import javax.inject.Inject;

public class FavFragment extends BaseFragment implements ResponsibleFav {

    @Inject
    ViewModelProvider provider;
    @Inject
    FavAdapter fav_adapter;
    @Inject
    GridLayoutManager manager;
    @Inject
    Intent intent;
    private FavViewModel favViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater,container,savedInstanceState);
        favViewModel = provider.get(FavViewModel.class);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(fav_adapter);
        subscribePhotos();
        favViewModel.get_list_photos();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        favViewModel.get_list_photos();
    }

    public void subscribePhotos() {
        favViewModel.observe_photos().observe(getViewLifecycleOwner(), photos -> {
            fav_adapter.setPhotos(photos);
        });
    }

    @Override
    public void onClickOnLikePhoto(Photo photo) {
        favViewModel.delete_from_db(photo);
        List<Photo> photos = fav_adapter.getPhotos();
        photos.remove(photo);
        fav_adapter.setPhotos(photos);
    }


    @Override
    public void onClickOnPhoto(String id) {
        intent.putExtra("ID", id);
        intent.putExtra("isLike",true);
        startActivity(intent);
    }
}
