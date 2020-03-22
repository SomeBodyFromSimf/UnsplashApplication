package com.mihailchistousov.unsplashapplication.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "unsplash_fav_photos")
public class Photo {
    @PrimaryKey
    @NonNull
    private String id;
    private String description;
    @Embedded
    private PhotoUrl urls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoUrl getUrls() {
        return urls;
    }

    public void setUrls(PhotoUrl urls) {
        this.urls = urls;
    }

}
