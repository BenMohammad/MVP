package com.benmohammad.mvp.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.benmohammad.mvp.MvpApp;
import com.benmohammad.mvp.di.component.ActivityComponent;
import com.benmohammad.mvp.di.component.DaggerActivityComponent;
import com.benmohammad.mvp.di.module.ActivityModule;
import com.benmohammad.mvp.utils.CommonUtils;

import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements MvpView, BaseFragment.Callback {


    private ProgressDialog dialog;
    private ActivityComponent activityComponent;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void showLoading() {
        hideLoading();
        dialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }

    public void setUnBinder(Unbinder unBinder) {
        unBinder.unbind();
    }

    @Override
    protected void onDestroy() {
        if(unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    protected abstract void setUp();
}
