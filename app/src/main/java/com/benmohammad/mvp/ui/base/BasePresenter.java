package com.benmohammad.mvp.ui.base;

import com.androidnetworking.error.ANError;



public class BasePresenter <V extends MvpView> implements MvpPresenter {

    private static final String TAG = "BasePresenter";
    //private final DataManager dataManager;

    @Override
    public void onAttach(MvpView mvpView) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void handleApiError(ANError error) {

    }

    @Override
    public void setUserAsLoggedOut() {

    }
}
