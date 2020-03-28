package com.benmohammad.mvp.di.component;

import android.app.Application;
import android.content.Context;

import com.benmohammad.mvp.MvpApp;
import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.di.ApplicationContext;
import com.benmohammad.mvp.di.module.ApplicationModule;
import com.benmohammad.mvp.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
