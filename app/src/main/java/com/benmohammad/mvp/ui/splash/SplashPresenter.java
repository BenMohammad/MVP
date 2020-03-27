package com.benmohammad.mvp.ui.splash;

import com.benmohammad.mvp.R;
import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.ui.base.BasePresenter;
import com.benmohammad.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        getMvpView().startSyncService();

        getCompositeDisposable().add(getDataManager()
        .seedDatabaseQuestions()
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .concatMap(new Function<Boolean, ObservableSource<Boolean>>() {
            @Override
            public ObservableSource<Boolean> apply(Boolean aBoolean) throws Exception {
                return getDataManager().seedDatabaseOptions();
            }
        })
        .subscribe(new Consumer<Boolean>() {
                       @Override
                       public void accept(Boolean aBoolean) throws Exception {
                           if (!isViewAttached()) {
                               return;
                           }
                           decideNextActivity();
                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           if(!isViewAttached()) {
                               return;
                           }
                           getMvpView().onError(R.string.some_error);
                           decideNextActivity();
                       }
                   }

        )
        );
    }

    private void decideNextActivity() {
        if(getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getMvpView().openLoginActivity();
        } else {
            getMvpView().openMainActivity();
        }
    }
}
