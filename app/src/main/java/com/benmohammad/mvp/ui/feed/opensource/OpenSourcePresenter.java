package com.benmohammad.mvp.ui.feed.opensource;

import com.androidnetworking.error.ANError;
import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.data.network.model.OpenSourceResponse;
import com.benmohammad.mvp.ui.base.BasePresenter;
import com.benmohammad.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class OpenSourcePresenter<V extends OpenSourceMvpView> extends BasePresenter<V> implements OpenSourceMvpPresenter<V> {

    @Inject
    public OpenSourcePresenter(DataManager dataManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
        .getOpenSourceApiCall()
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(new Consumer<OpenSourceResponse>() {
                       @Override
                       public void accept(OpenSourceResponse openSourceResponse) throws Exception {
                           if (openSourceResponse != null && openSourceResponse.getData() != null) {
                               getMvpView().updateRepo(openSourceResponse.getData());
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
