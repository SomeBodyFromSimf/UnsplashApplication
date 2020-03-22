package com.mihailchistousov.unsplashapplication.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mihailchistousov.unsplashapplication.R;
import com.mihailchistousov.unsplashapplication.model.Photo;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter <VH extends BaseViewHolder> extends RecyclerView.Adapter<VH>  {


    private List<Photo> photos = new ArrayList<>();
    public List<Photo> getPhotos() {
        return photos;
    }

    protected View getView(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_item, parent, false);
        return view;
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setCurrent_photo(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }
}
