package com.benmohammad.mvp.ui.main;

import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.ui.base.BasePresenter;
import com.benmohammad.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onDrawerOptionAboutClick() {

    }

    @Override
    public void onDrawerOptionLogoutClick() {

    }

    @Override
    public void onDrawerRateUsClick() {

    }

    @Override
    public void onDrawerMyFeedClick() {

    }

    @Override
    public void onViewInitialized() {

    }

    @Override
    public void onCardExhausted() {

    }

    @Override
    public void onNavMenuCreated() {

    }

}
