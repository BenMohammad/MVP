package com.benmohammad.mvp.ui.main.rating;

import com.benmohammad.mvp.ui.base.DialogMvpView;

public interface RatingDialogMvpView extends DialogMvpView {

    void openPlayStoreForRating();
    void showPlayStoreRatingView();
    void showRatingMessageView();
    void hideSubmitButton();
    void disableRatingStar();
    void dismissDialog();
}
