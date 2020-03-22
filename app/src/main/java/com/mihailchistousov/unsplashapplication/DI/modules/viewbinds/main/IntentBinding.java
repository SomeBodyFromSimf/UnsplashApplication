package com.mihailchistousov.unsplashapplication.DI.modules.viewbinds.main;

import android.content.Context;
import android.content.Intent;

import com.mihailchistousov.unsplashapplication.ui.detail.PhotoActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class IntentBinding {
    @Provides
    Intent provideIntent(Context context) {
        return new Intent(context, PhotoActivity.class);
    }
}
