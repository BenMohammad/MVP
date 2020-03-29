package com.benmohammad.mvp.ui.main.rating;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.benmohammad.mvp.R;
import com.benmohammad.mvp.di.component.ActivityComponent;
import com.benmohammad.mvp.ui.base.BaseDialog;
import com.benmohammad.mvp.utils.AppUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RateUsDialog extends BaseDialog implements RatingDialogMvpView {

    private static final String TAG = "RateUsDialog";

    @Inject
    RatingDialogMvpPresenter<RatingDialogMvpView> presenter;

    @BindView(R.id.rating_bar_feedback)
    RatingBar ratingBar;

    @BindView(R.id.et_message)
    EditText message;

    @BindView(R.id.view_rating_message)
    View ratingMessageView;

    @BindView(R.id.view_play_store_rating)
    View playStoreRating;

    @BindView(R.id.btn_submit)
    Button submitButton;

    public static RateUsDialog newInstance() {
        RateUsDialog fragment = new RateUsDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_rate_us, container, false);

        ActivityComponent component = getActivityComponent();
        if(component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
        }



        return view;

    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    public void openPlayStoreForRating() {
        AppUtils.openPlayStoreForApp(getContext());
    }

    @Override
    public void showPlayStoreRatingView() {
        playStoreRating.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRatingMessageView() {
        ratingMessageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSubmitButton() {
        submitButton.setVisibility(View.GONE);
    }

    @Override
    public void disableRatingStar() {
        ratingBar.setIsIndicator(false);
    }


    @Override
    protected void setUp(View view) {
        ratingMessageView.setVisibility(View.GONE);
        playStoreRating.setVisibility(View.GONE);

        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2)
                .setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0)
                .setColorFilter(ContextCompat.getColor(getContext(), R.color.shadow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1)
                .setColorFilter(ContextCompat.getColor(getContext(), R.color.shadow), PorterDuff.Mode.SRC_ATOP);

        submitButton.setOnClickListener(v -> presenter.onRatingSubmitted(ratingBar.getRating(), message.getText().toString()));
    }

    @OnClick(R.id.btn_later)
    void onLaterClick() {
        presenter.onLaterClicked();
    }

    @OnClick(R.id.btn_rate_on_play_store)
    void onPlayStoreRateClick() {
        presenter.onPlayStoreRatingClicked();
    }





    @Override
    public void dismissDialog() {
        super.dismissDialog(TAG);
    }
}
