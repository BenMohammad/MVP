package com.benmohammad.mvp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.benmohammad.mvp.R;
import com.benmohammad.mvp.ui.base.BaseActivity;
import com.benmohammad.mvp.ui.base.MvpView;
import com.benmohammad.mvp.ui.main.MainActivity;
import com.benmohammad.mvp.ui.main.MainPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> presenter;

    @BindView(R.id.et_email)
    EditText emailEditTExt;

    @BindView(R.id.et_password)
    EditText passwordEditText;

    public static Intent startIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(LoginActivity.this);


    }

    @OnClick(R.id.btn_server_login)
    void onServerLoginClick(View v) {
        presenter.onServerLoginClick(emailEditTExt.getText().toString(), passwordEditText.getText().toString());
    }

    @OnClick(R.id.ib_google_login)
    void onGoogleLoginClick(View v) {
        presenter.onGoogleLoginClick();
    }

    @OnClick(R.id.ib_fb_login)
    void onFacebookLoginClick(View v) {
        presenter.onFacebookLoginClick();
    }

    @Override
    public void openMainActivity() {
//        Intent intent = MainActivity.getStartIntent(LoginActivity.this);
//        startActivity(intent);
//        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}
