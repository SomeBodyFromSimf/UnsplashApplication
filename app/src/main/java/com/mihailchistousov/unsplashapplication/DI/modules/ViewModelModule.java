package com.mihailchistousov.unsplashapplication.DI.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mihailchistousov.unsplashapplication.DI.Utils.ViewModelKey;
import com.mihailchistousov.unsplashapplication.Util.ViewModelFactory;
import com.mihailchistousov.unsplashapplication.ui.detail.PhotoActivityViewModel;
import com.mihailchistousov.unsplashapplication.ui.main.fav.FavViewModel;
import com.mihailchistousov.unsplashapplication.ui.main.photo.PhotoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PhotoViewModel.class)
    abstract ViewModel bindPhotoViewModel(PhotoViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavViewModel.class)
    abstract ViewModel bindFavViewModel(FavViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PhotoActivityViewModel.class)
    abstract ViewModel bindPhotoActivityViewModel(PhotoActivityViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
