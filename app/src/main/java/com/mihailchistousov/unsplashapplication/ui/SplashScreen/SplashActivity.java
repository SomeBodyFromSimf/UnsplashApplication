package com.mihailchistousov.unsplashapplication.ui.SplashScreen;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
