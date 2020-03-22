package com.mihailchistousov.unsplashapplication.base.Responsible_interfaces;

import com.mihailchistousov.unsplashapplication.model.Photo;
import com.mihailchistousov.unsplashapplication.ui.main.photo.PhotoAdapter;

public interface ResponsiblePhoto extends Responsible{
    void onClickOnLikePhoto(PhotoAdapter.ViewHolder viewHolder);
    void onClickOnPhoto(Photo photo, boolean isLike);
    void checkInDB(PhotoAdapter.ViewHolder viewHolder);
}
