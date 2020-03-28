package com.benmohammad.mvp.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.benmohammad.mvp.di.component.ActivityComponent;
import com.benmohammad.mvp.ui.main.MainActivity;

import javax.crypto.Mac;

import butterknife.Unbinder;

public abstract class BaseDialog extends DialogFragment implements DialogMvpView {

    private BaseActivity mActivity;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity) {
            BaseActivity mActivity = (BaseActivity) context;
            this.mActivity = mActivity;
            mActivity.onFragmentAttached();

        }
    }

    @Override
    public void showLoading() {
        if(mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if(mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void onError(String message) {
        if(mActivity != null) {
            mActivity.onError(message);
        }
    }

    @Override
    public void onError(int resId) {
        if(mActivity != null) {
            mActivity.onError(resId);
        }
    }

    @Override
    public void showMessage(String message) {
        if(mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    @Override
    public void showMessage(int resId) {
        if(mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if(mActivity != null) {
            mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void hideKeyboard() {
        if(mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        if(mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public ActivityComponent getActivityComponent() {
            if(mActivity != null) {
                //return mActivity.getActivityComponent();
            }

            return null;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnbinder = unBinder;
    }


    protected abstract void setUp(View view);

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        if(dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }

    public void show(FragmentManager fragmentManager, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
        if(prevFragment != null) {
            transaction.remove(prevFragment);
        }

        transaction.addToBackStack(null);
        show(transaction, tag);
    }

    @Override
    public void dismissDialog(String tag) {
        dismiss();
        getBaseActivity().onFragmentDetached(tag);
    }

    @Override
    public void onDestroy() {
        if(mUnbinder != null) {
            mUnbinder.unbind();
        }

        super.onDestroy();
    }
}
