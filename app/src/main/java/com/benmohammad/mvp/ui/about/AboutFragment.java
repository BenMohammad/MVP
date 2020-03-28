package com.benmohammad.mvp.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.benmohammad.mvp.R;
import com.benmohammad.mvp.di.component.ActivityComponent;
import com.benmohammad.mvp.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class AboutFragment extends BaseFragment implements AboutMvpView {

    public static final String TAG = "AboutFragment";

    @Inject
    AboutMvpPresenter<AboutMvpView> presenter;

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        ActivityComponent component = getActivityComponent();
        if(component != null) {
            component.inject(this);
            setUnbinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }
        return view;
    }

    @Override
    public void setup(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
