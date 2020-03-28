package com.benmohammad.mvp.di.module;

import android.app.Application;
import android.content.Context;

import com.benmohammad.mvp.data.AppDataManager;
import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.data.db.AppDbHelper;
import com.benmohammad.mvp.data.db.DbHelper;
import com.benmohammad.mvp.data.network.ApiEndPoint;
import com.benmohammad.mvp.data.network.ApiHeader;
import com.benmohammad.mvp.data.network.ApiHelper;
import com.benmohammad.mvp.data.network.AppApiHelper;
import com.benmohammad.mvp.data.prefs.AppPreferencesHelper;
import com.benmohammad.mvp.data.prefs.PreferencesHelper;
import com.benmohammad.mvp.di.ApiInfo;
import com.benmohammad.mvp.di.ApplicationContext;
import com.benmohammad.mvp.di.DatabaseInfo;
import com.benmohammad.mvp.di.PreferenceInfo;
import com.benmohammad.mvp.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @DatabaseInfo
    String provideDatabase() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return ApiEndPoint.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferenceHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper apiHelper) {
        return apiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey, PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }



}
