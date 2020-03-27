package com.benmohammad.mvp.ui.splash;

import com.benmohammad.mvp.ui.base.MvpView;

public interface SplashMvpView extends MvpView {

    void openLoginActivity();
    void openMainActivity();
    void startSyncService();
}
