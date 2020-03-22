package com.mihailchistousov.unsplashapplication.ui.main.fav;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mihailchistousov.unsplashapplication.R;
import com.mihailchistousov.unsplashapplication.base.BaseAdapter;
import com.mihailchistousov.unsplashapplication.base.BaseViewHolder;
import com.mihailchistousov.unsplashapplication.base.Responsible_interfaces.ResponsibleFav;
import com.mihailchistousov.unsplashapplication.model.Photo;

public class FavAdapter extends BaseAdapter<FavAdapter.ViewHolder> {


    private final ResponsibleFav listener;

    public FavAdapter(ResponsibleFav listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent,viewType),listener);
    }

    public class ViewHolder extends BaseViewHolder {

        private final ResponsibleFav listener;

        public ViewHolder(@NonNull View itemView, ResponsibleFav listener) {
            super(itemView);
            this.listener = listener;
        }

        @Override
        protected void onClickOnLikePhoto(View view) {
            listener.onClickOnLikePhoto(getPhoto());
        }

        @Override
        protected void onClickOnPhoto(View view) {
            listener.onClickOnPhoto(getPhoto().getId());
        }

        @Override
        public void setCurrent_photo(Photo photo) {
            this.photo = photo;
            like_image.setImageResource(R.drawable.ic_favorite);
            listener.load_picture_by_glide(this.photo.getUrls().getThumb(),photo_image);
        }

    }
}
