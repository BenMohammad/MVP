package com.benmohammad.mvp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.benmohammad.mvp.MvpApp;
import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.di.component.DaggerServiceComponent;
import com.benmohammad.mvp.di.component.ServiceComponent;
import com.benmohammad.mvp.utils.AppLogger;

import javax.inject.Inject;

public class SyncService extends Service {

    private static final String TAG = "SyncService";

    @Inject
    DataManager dataManager;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SyncService.class);
        context.startService(starter);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, SyncService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent component = DaggerServiceComponent.builder()
                .applicationComponent(((MvpApp)getApplication()).getComponent()).build();

        component.inject(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppLogger.d(TAG, "SyncService started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        AppLogger.d(TAG, "Sync stopped");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
