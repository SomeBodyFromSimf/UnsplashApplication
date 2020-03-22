package com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.main;

import com.mihailchistousov.unsplashapplication.DI.Utils.FragmentScope;
import com.mihailchistousov.unsplashapplication.ui.main.fav.FavFragment;
import com.mihailchistousov.unsplashapplication.ui.main.info.InfoFragment;
import com.mihailchistousov.unsplashapplication.ui.main.photo.PhotoFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentsBindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = {FavFragmentBindingModule.class})
    abstract FavFragment provideFavFragment();

    @FragmentScope
    @ContributesAndroidInjector(modules = {PhotoFragmentBindingModule.class})
    abstract PhotoFragment providePhotoFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract InfoFragment provideInfoFragment();

}
