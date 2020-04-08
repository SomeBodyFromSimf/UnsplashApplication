package com.mihailchistousov.unsplashapplication.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mihailchistousov.unsplashapplication.R;
import com.mihailchistousov.unsplashapplication.base.Responsible_interfaces.Responsible;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment implements Responsible, RequestListener<Drawable> {

    @Inject
    RequestManager glide;

    private Unbinder unbinder;

    @LayoutRes
    protected abstract int layoutRes();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    protected abstract void subscribePhotos();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void load_picture_by_glide(String url, ImageView view) {
        glide.load(url).listener(this).into(view);
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
        Toast.makeText(getContext(),getResources().getText(R.string.error_load_photo),Toast.LENGTH_SHORT).show();
        return false;
    }
    @Override
    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
        return false;
    }
}
