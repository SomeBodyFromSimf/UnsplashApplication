package com.mihailchistousov.unsplashapplication.model;

import androidx.room.ColumnInfo;

public class PhotoUrl {

    @ColumnInfo(name = "full_link")
    private String full;
    @ColumnInfo(name = "small_link")
    private String small;
    @ColumnInfo(name = "thumb_link")
    private String thumb;

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
