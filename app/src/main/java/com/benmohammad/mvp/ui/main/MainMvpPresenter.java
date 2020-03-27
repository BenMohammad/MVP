package com.benmohammad.mvp.ui.main;

import com.benmohammad.mvp.di.PerActivity;
import com.benmohammad.mvp.ui.base.MvpPresenter;



@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onDrawerOptionAboutClick();
    void onDrawerOptionLogoutClick();
    void onDrawerRateUsClick();
    void onDrawerMyFeedClick();
    void onViewInitialized();
    void onCardExhausted();
    void onNavMenuCreated();
}
