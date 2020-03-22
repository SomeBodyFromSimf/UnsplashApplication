package com.mihailchistousov.unsplashapplication.base.Responsible_interfaces;

import com.mihailchistousov.unsplashapplication.model.Photo;

public interface ResponsibleFav extends Responsible {

    void onClickOnLikePhoto(Photo photo);
    void onClickOnPhoto(String id);

}
