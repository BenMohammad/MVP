package com.benmohammad.mvp.ui.base;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.benmohammad.mvp.di.component.ActivityComponent;
import com.benmohammad.mvp.utils.CommonUtils;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity activity;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) context;
            this.activity = baseActivity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    @Override
    public void hideLoading() {
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {
        if(activity != null) {
            activity.onError(message);
        }
    }

    @Override
    public void showMessage(int resId) {
        if(activity != null) {
            activity.showMessage(resId);
        }
    }

    @Override
    public void showMessage(String message) {
        if(activity != null) {
            activity.showMessage(message);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if(activity != null) {
            return activity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void onDetach() {
        activity = null;
        super.onDetach();
    }

    @Override
    public void hideKeyboard() {
        if(activity != null) {
            activity.hideKeyboard();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        if(activity != null) {
            activity.openActivityOnTokenExpire();
        }
    }

    public void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    public abstract void setup(View view);


    @Override
    public void onDestroy() {
        if(unbinder != null) {
            unbinder.unbind();
        }

        super.onDestroy();

    }

    public ActivityComponent getActivityComponent() {
        if(activity != null) {
            return activity.getActivityComponent();
        }

        return null;
    }

    @Override
    public void onError(int resId) {
        if(activity != null) {
            activity.onError(resId);
        }
    }

    public interface Callback {
        void onFragmentAttached();
        void onFragmentDetached(String tag);
    }
}
