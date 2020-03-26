package com.benmohammad.mvp.data.prefs;

import com.benmohammad.mvp.data.DataManager;

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();
    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);
    Long getCurrentUserId();
    void setCurrentUserId(Long userId);
    String getCurrentUserName();
    void setCurrentUserName(String userName);
    String getCurrentUserEmail();
    void setCurrentUserEmail(String email);
    String getCurrentUserProfilePic();
    void setCurrentUserProfilePic(String profilePicUrl);
    String getAccessToken();
    void setAccessToken(String accessToken);
}
