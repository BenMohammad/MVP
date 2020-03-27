package com.benmohammad.mvp.ui.splash;

import com.benmohammad.mvp.di.PerActivity;
import com.benmohammad.mvp.ui.base.MvpPresenter;
@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
}
