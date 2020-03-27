package com.benmohammad.mvp.ui.main;

import com.benmohammad.mvp.data.db.model.Question;
import com.benmohammad.mvp.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void openLoginActivity();
    void showAboutFragment();
    void refreshQuestionnaire(List<Question> questionList);
    void reloadQuestionnaire(List<Question> questionList);
    void updateUserName(String currentUserName);
    void updateUserEmail(String currentEmail);
    void updateUserProfilePic(String currentUserProfilePic);
    void updateAppVersion();
    void showRateUsDialog();
    void openMyFeedActivity();
    void closeNavigationDrawer();
    void lockDrawer();
    void unlockDrawer();


}
