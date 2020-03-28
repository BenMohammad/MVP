package com.benmohammad.mvp.ui.main.rating;

import com.benmohammad.mvp.ui.base.MvpPresenter;

public interface RatingDialogMvpPresenter<V extends RatingDialogMvpView> extends MvpPresenter<V> {

    void onRatingSubmitted(float rating, String message);
    void onCancelClicked();
    void onLaterClicked();
    void onPlayStoreRatingClicked();
}
