package com.benmohammad.mvp.ui.main.rating;

import com.benmohammad.mvp.R;
import com.benmohammad.mvp.data.DataManager;
import com.benmohammad.mvp.ui.base.BasePresenter;
import com.benmohammad.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class RatingDialogPresenter<V extends RatingDialogMvpView> extends BasePresenter<V> implements RatingDialogMvpPresenter<V> {

    public static final String TAG = "RatingDialogPresenter";

    private boolean isRatingSecondaryAction = false;

    @Inject
    public RatingDialogPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onRatingSubmitted(float rating, String message) {
        if(rating == 0) {
            getMvpView().showMessage(R.string.rating_not_provided_error);
        }

        if(!isRatingSecondaryAction) {
            if(rating == 5) {
                getMvpView().showPlayStoreRatingView();
                getMvpView().hideSubmitButton();
                getMvpView().disableRatingStar();
            } else {
                getMvpView().showRatingMessageView();
            }

            isRatingSecondaryAction = true;
            return;
        }

        getMvpView().showLoading();
        getMvpView().hideLoading();
        getMvpView().showMessage(R.string.rating_thanks);
        getMvpView().dismissDialog();
    }

    private void sendRatingDataToServerInBackground(float rating) {

    }

    @Override
    public void onCancelClicked() {
        getMvpView().dismissDialog();
    }

    @Override
    public void onLaterClicked() {
        getMvpView().dismissDialog();
    }

    @Override
    public void onPlayStoreRatingClicked() {
        getMvpView().openPlayStoreForRating();
        sendRatingDataToServerInBackground(5);
        getMvpView().dismissDialog();
    }
}
