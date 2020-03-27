package com.benmohammad.mvp.ui.feed.blogs;

import com.benmohammad.mvp.ui.base.MvpPresenter;

public interface BlogMvpPresenter<V extends BlogMvpView> extends MvpPresenter<V> {

    void onViewPrepared();
}
