package com.benmohammad.mvp.ui.feed.blogs;

import com.androidnetworking.error.ANError;
import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.data.network.model.BlogResponse;
import com.benmohammad.mvp.ui.base.BasePresenter;
import com.benmohammad.mvp.utils.rx.SchedulerProvider;

import java.util.concurrent.ScheduledExecutorService;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class BlogPresenter<V extends BlogMvpView> extends BasePresenter<V> implements BlogMvpPresenter<V> {

    @Inject
    public BlogPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
        .getBlogApiCall()
        .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<BlogResponse>() {
                               @Override
                               public void accept(BlogResponse blogResponse) throws Exception {
                                   if (blogResponse != null && blogResponse.getData() != null) {
                                       getMvpView().updateBlog(blogResponse.getData());
                                   }
                                   getMvpView().hideLoading();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if(!isViewAttached()) {
                                       return;
                                   }
                                   getMvpView().hideLoading();

                                   if(throwable instanceof ANError) {
                                       ANError anError = (ANError) throwable;
                                       handleApiError(anError);
                                   }
                               }
                           }
                ));
    }
}
