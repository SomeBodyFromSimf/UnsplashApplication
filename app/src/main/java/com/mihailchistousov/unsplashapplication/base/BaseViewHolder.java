package com.mihailchistousov.unsplashapplication.base;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mihailchistousov.unsplashapplication.R;
import com.mihailchistousov.unsplashapplication.model.Photo;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.photo_image)
    public ImageView photo_image;
    @BindView(R.id.like_image)
    public ImageView like_image;

    protected Photo photo;
    public Photo getPhoto() {
        return photo;
    }

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        photo_image.setOnClickListener(this::onClickOnPhoto);
        like_image.setOnClickListener(this::onClickOnLikePhoto);
    }

    protected abstract void onClickOnLikePhoto(View view);

    protected abstract void onClickOnPhoto(View view);

    public abstract void setCurrent_photo(Photo photo);

}
