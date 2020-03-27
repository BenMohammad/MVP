package com.benmohammad.mvp.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.benmohammad.mvp.ui.base.BaseActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> presenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);

    }

    @Override
    public void openLoginActivity() {

    }

    @Override
    public void openMainActivity() {

    }

    @Override
    public void startSyncService() {

    }

    @Override
    protected void setUp() {

    }
}
