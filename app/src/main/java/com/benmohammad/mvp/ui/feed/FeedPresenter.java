package com.benmohammad.mvp.ui.feed;

import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.ui.base.BasePresenter;
import com.benmohammad.mvp.ui.base.MvpView;
import com.benmohammad.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class FeedPresenter<V extends MvpView> extends BasePresenter<V> implements FeedMvpPresenter<V> {


    private static final String TAG = "FeedPresenter";

    @Inject
    public FeedPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
