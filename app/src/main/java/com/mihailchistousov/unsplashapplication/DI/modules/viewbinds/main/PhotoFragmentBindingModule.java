package com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.main;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mihailchistousov.unsplashapplication.Util.ViewModelFactory;
import com.mihailchistousov.unsplashapplication.ui.main.photo.PhotoAdapter;
import com.mihailchistousov.unsplashapplication.ui.main.photo.PhotoFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoFragmentBindingModule {

    @Provides
    ViewModelProvider provideViewModule(PhotoFragment fragment, ViewModelFactory factory) {
        return new ViewModelProvider(fragment,factory);

    }

    @Provides
    PhotoAdapter provideAdapter(PhotoFragment fragment) {
        return new PhotoAdapter(fragment);
    }

    @Provides
    GridLayoutManager provideLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }
}
