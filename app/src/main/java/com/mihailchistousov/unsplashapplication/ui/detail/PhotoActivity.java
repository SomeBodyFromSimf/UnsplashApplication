package com.mihailchistousov.unsplashapplication.ui.detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mihailchistousov.unsplashapplication.R;
import com.mihailchistousov.unsplashapplication.model.Photo;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class PhotoActivity extends DaggerAppCompatActivity implements RequestListener<Drawable> {

    @Inject
    ViewModelProvider provider;
    @Inject
    RequestManager glide;

    private PhotoActivityViewModel photoViewModel;


    @BindView(R.id.photo)
    ImageView photo_image;
    @BindView(R.id.like_image)
    ImageView like_image;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Photo photo;

    private boolean like;

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        photoViewModel = provider.get(PhotoActivityViewModel.class);
        String id = getIntent().getStringExtra("ID");
        setLike(getIntent().getBooleanExtra("isLike",false));
        set_like_image();
        like_image.setOnClickListener(this::clickOnLikeImage);
        subscribePhotos();
        photoViewModel.get_from_API_current_photo(id);

    }

    private void set_like_image() {
        like_image.setImageResource(like? R.drawable.ic_favorite: R.drawable.ic_favorite_border);
    }

    private void clickOnLikeImage(View view) {
        if(photo == null) return;
        if(isLike()) photoViewModel.delete_from_db(photo);
        else photoViewModel.save_to_db(photo);
        setLike(!isLike());
        set_like_image();
    }

    public void subscribePhotos() {
        photoViewModel.observe_photo().observe(this, photo_resource -> {
            if(photo_resource != null) {
                switch (photo_resource.status) {
                    case LOADING:
                        showProgressBar(true);
                        break;
                    case SUCCESS:
                        photo = photo_resource.data;
                        glide.load(photo.getUrls().getFull()+"?w=360")
                                .listener(this)
                                .into(photo_image);
                        break;
                    case ERROR:
                        showProgressBar(false);
                        Toast.makeText(this,"Error: "+photo_resource.message,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showProgressBar(boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
        showProgressBar(false);
        Toast.makeText(this,getResources().getText(R.string.error_load_photo),Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
        showProgressBar(false);
        return false;
    }
}
