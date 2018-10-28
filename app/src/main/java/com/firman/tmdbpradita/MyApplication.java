package com.firman.tmdbpradita;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(this);
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
    }
}
