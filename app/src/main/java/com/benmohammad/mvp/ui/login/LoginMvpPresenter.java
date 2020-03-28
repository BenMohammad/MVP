package com.benmohammad.mvp.ui.login;

import com.benmohammad.mvp.di.PerActivity;
import com.benmohammad.mvp.ui.base.MvpPresenter;

@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String email, String password);
    void onGoogleLoginClick();
    void onFacebookLoginClick();

}
