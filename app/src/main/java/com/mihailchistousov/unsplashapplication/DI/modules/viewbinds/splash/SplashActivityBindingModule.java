package com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.splash;

import android.content.Context;
import android.content.Intent;

import com.mihailchistousov.unsplashapplication.ui.main.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityBindingModule {
    @Provides
    Intent provideIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
