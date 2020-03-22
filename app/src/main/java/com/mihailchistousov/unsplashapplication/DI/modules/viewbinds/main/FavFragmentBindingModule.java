package com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.main;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mihailchistousov.unsplashapplication.Util.ViewModelFactory;
import com.mihailchistousov.unsplashapplication.ui.main.fav.FavAdapter;
import com.mihailchistousov.unsplashapplication.ui.main.fav.FavFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FavFragmentBindingModule {

    @Provides
    ViewModelProvider provideViewModule(FavFragment fragment, ViewModelFactory factory) {
        return new ViewModelProvider(fragment, factory);

    }

    @Provides
    FavAdapter provideAdapter(FavFragment fragment) {
        return new FavAdapter(fragment);
    }

    @Provides
    GridLayoutManager provideLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }
}
