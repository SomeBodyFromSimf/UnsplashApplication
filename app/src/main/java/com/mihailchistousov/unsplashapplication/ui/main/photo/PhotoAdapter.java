package com.mihailchistousov.unsplashapplication.ui.main.photo;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mihailchistousov.unsplashapplication.R;
import com.mihailchistousov.unsplashapplication.base.BaseAdapter;
import com.mihailchistousov.unsplashapplication.base.BaseViewHolder;
import com.mihailchistousov.unsplashapplication.base.Responsible_interfaces.ResponsiblePhoto;
import com.mihailchistousov.unsplashapplication.model.Photo;

public class PhotoAdapter  extends BaseAdapter<PhotoAdapter.ViewHolder> {

    private final ResponsiblePhoto listener;

    public PhotoAdapter(ResponsiblePhoto listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent,viewType),listener);
    }

    public class ViewHolder extends BaseViewHolder {

        private final ResponsiblePhoto listener;

        private boolean like=false;
        public boolean isLike() {
            return like;
        }
        public void setLike(boolean like) {
            this.like = like;
        }


        public ViewHolder(@NonNull View itemView, ResponsiblePhoto listener) {
            super(itemView);
            this.listener = listener;
        }

        @Override
        protected void onClickOnLikePhoto(View view) {
            listener.onClickOnLikePhoto(this);
        }

        @Override
        protected void onClickOnPhoto(View view) {
            listener.onClickOnPhoto(getPhoto(),isLike());
        }

        @Override
        public void setCurrent_photo(Photo photo) {
            this.photo = photo;
            set_like_image();
            listener.checkInDB(this);
            listener.load_picture_by_glide(this.photo.getUrls().getThumb(),photo_image);
        }

        public void set_like_image() {
            like_image.setImageResource(isLike()? R.drawable.ic_favorite: R.drawable.ic_favorite_border);
        }
    }
}
