package com.mihailchistousov.unsplashapplication.ui.main.photo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mihailchistousov.unsplashapplication.R;
import com.mihailchistousov.unsplashapplication.base.BaseFragment;
import com.mihailchistousov.unsplashapplication.base.Responsible_interfaces.ResponsiblePhoto;
import com.mihailchistousov.unsplashapplication.model.Photo;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class PhotoFragment extends BaseFragment implements ResponsiblePhoto {

    @Inject
    ViewModelProvider provider;
    @Inject
    PhotoAdapter photo_adapter;
    @Inject
    GridLayoutManager manager;
    @Inject
    Intent intent;
    private PhotoViewModel photoViewModel;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipe_container;

    @BindView(R.id.photo_recyclerview)
    protected RecyclerView recyclerView;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_photo;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = super.onCreateView(inflater,container,savedInstanceState);
        photoViewModel = provider.get(PhotoViewModel.class);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(photo_adapter);
        subscribePhotos();
        swipe_container.setOnRefreshListener(() -> photoViewModel.get_list_photos());
        photoViewModel.get_list_photos();
        return root;
    }

    public void subscribePhotos() {
        photoViewModel.observe_photos().observe(getViewLifecycleOwner(), photos_resource -> {
            if(photos_resource != null) {
                switch (photos_resource.status) {
                    case LOADING:
                        swipe_container.setRefreshing(true);
                        break;
                    case SUCCESS:
                        swipe_container.setRefreshing(false);
                        photo_adapter.setPhotos(photos_resource.data);
                        break;
                    case ERROR:
                        swipe_container.setRefreshing(false);
                        Toast.makeText(getActivity(),"Error: "+photos_resource.message,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }


    @Override
    public void onClickOnLikePhoto(PhotoAdapter.ViewHolder viewHolder) {
        if(viewHolder.isLike()) photoViewModel.delete_from_db(viewHolder.getPhoto());
        else photoViewModel.save_to_db(viewHolder.getPhoto());
        viewHolder.setLike(!viewHolder.isLike());
        viewHolder.set_like_image();
    }

    @Override
    public void onClickOnPhoto(Photo photo,boolean isLike) {
        intent.putExtra("ID", photo.getId());
        intent.putExtra("isLike",isLike);
        startActivity(intent);
    }

    @SuppressLint("CheckResult")
    @Override
    public void checkInDB(PhotoAdapter.ViewHolder viewHolder) {
        CompositeDisposable composite = new CompositeDisposable();

        composite.add(photoViewModel.find_photo_in_db(viewHolder.getPhoto())
                .subscribe(bool -> {
                    viewHolder.setLike(bool);
                    viewHolder.set_like_image();
                    composite.dispose();
                }));

    }


}
