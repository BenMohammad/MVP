package com.benmohammad.mvp.data.network;

import com.benmohammad.mvp.data.network.model.BlogResponse;
import com.benmohammad.mvp.data.network.model.LoginRequest;
import com.benmohammad.mvp.data.network.model.LoginResponse;
import com.benmohammad.mvp.data.network.model.LogoutResponse;
import com.benmohammad.mvp.data.network.model.OpenSourceResponse;

import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();
    Single<LoginResponse> doGoogleLoginCall(LoginRequest.GoogleLoginRequest request);
    Single<LoginResponse> doFacebookLoginCall(LoginRequest.FacebookLoginRequest request);
    Single<LoginResponse> doServerLoginCall(LoginRequest.ServerLoginRequest request);
    Single<LogoutResponse> doLogoutApiCall();
    Single<BlogResponse> getBlogApiCall();
    Single<OpenSourceResponse> getOpenSourceApiCall();
}
