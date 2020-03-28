package com.benmohammad.mvp;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.di.component.ApplicationComponent;
import com.benmohammad.mvp.di.component.DaggerApplicationComponent;
import com.benmohammad.mvp.di.module.ApplicationModule;
import com.benmohammad.mvp.utils.AppLogger;

import javax.inject.Inject;

public class MvpApp extends Application {

    @Inject
    DataManager dataManager;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if(BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }






    }


    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
