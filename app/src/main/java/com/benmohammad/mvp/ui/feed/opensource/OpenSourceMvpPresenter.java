package com.benmohammad.mvp.ui.feed.opensource;

import com.benmohammad.mvp.ui.base.MvpPresenter;

public interface OpenSourceMvpPresenter<V extends OpenSourceMvpView> extends MvpPresenter<V> {

    void onViewPrepared();
}
