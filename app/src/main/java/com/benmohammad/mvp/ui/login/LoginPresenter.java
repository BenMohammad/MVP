package com.benmohammad.mvp.ui.login;

import android.provider.ContactsContract;

import com.androidnetworking.error.ANError;
import com.benmohammad.mvp.R;
import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.data.network.model.LoginRequest;
import com.benmohammad.mvp.data.network.model.LoginResponse;
import com.benmohammad.mvp.ui.base.BasePresenter;
import com.benmohammad.mvp.utils.CommonUtils;
import com.benmohammad.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }



    @Override
    public void onServerLoginClick(String email, String password) {
        if(email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if(!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }

        if(password == null|| password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }

        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
        .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email, password))
                .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(new Consumer<LoginResponse>() {
                       @Override
                       public void accept(LoginResponse loginResponse) throws Exception {
                           getDataManager().updateUserInfo(
                                   loginResponse.getAccessToken(),
                                   loginResponse.getUserId(),
                                   DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                   loginResponse.getUserName(),
                                   loginResponse.getUserEmail(),
                                   loginResponse.getGoogleProfilePicUrl());

                           if (!isViewAttached()) {
                               return;
                           }

                           getMvpView().hideLoading();
                           getMvpView().openMainActivity();
                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           if(!isViewAttached()) {
                               return;
                           }

                           getMvpView().hideLoading();

                           if(throwable instanceof ANError) {
                               ANError anError = (ANError) throwable;
                               handleApiError(anError);
                           }
                       }
                   }

        ));
    }



    @Override
    public void onGoogleLoginClick() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
        .doGoogleLoginCall(new LoginRequest.GoogleLoginRequest("test1", "test1"))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(new Consumer<LoginResponse>() {
                       @Override
                       public void accept(LoginResponse loginResponse) throws Exception {
                           getDataManager().updateUserInfo(
                                   loginResponse.getAccessToken(),
                                   loginResponse.getUserId(),
                                   DataManager.LoggedInMode.LOGGED_IN_MODE_GOOGLE,
                                   loginResponse.getUserName(),
                                   loginResponse.getUserEmail(),
                                   loginResponse.getGoogleProfilePicUrl());

                           if (!isViewAttached()) {
                               return;
                           }

                           getMvpView().hideLoading();
                           getMvpView().openMainActivity();
                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           if(!isViewAttached()) {
                               return;
                           }

                           getMvpView().hideLoading();

                           if(throwable instanceof  ANError) {
                               ANError anError = (ANError) throwable;
                               handleApiError(anError);
                           }
                       }
                   }
        ));
    }

    @Override
    public void onFacebookLoginClick() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
        .doFacebookLoginCall(new LoginRequest.FacebookLoginRequest("test£", "test3"))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(new Consumer<LoginResponse>() {
                       @Override
                       public void accept(LoginResponse loginResponse) throws Exception {
                           getDataManager().updateUserInfo(
                                   loginResponse.getAccessToken(),
                                   loginResponse.getUserId(),
                                   DataManager.LoggedInMode.LOGGED_IN_MODE_FB,
                                   loginResponse.getUserName(),
                                   loginResponse.getUserEmail(),
                                   loginResponse.getFbProfilePicUrl());

                           if (!isViewAttached()) {
                               return;
                           }

                           getMvpView().hideLoading();
                           getMvpView().openMainActivity();
                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           if(!isViewAttached()) {
                               return;
                           }

                           getMvpView().hideLoading();

                           if(throwable instanceof ANError) {
                               ANError anError = (ANError) throwable;
                               handleApiError(anError);
                           }
                       }
                   }
        ));
    }
}
