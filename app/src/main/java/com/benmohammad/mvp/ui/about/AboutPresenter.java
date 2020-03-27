package com.benmohammad.mvp.ui.about;

import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.ui.base.BasePresenter;
import com.benmohammad.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class AboutPresenter<V extends AboutMvpView> extends BasePresenter<V> implements AboutMvpPresenter<V> {

    @Inject
    public AboutPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
