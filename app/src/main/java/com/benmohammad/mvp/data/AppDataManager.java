package com.benmohammad.mvp.data;

import android.content.Context;

import com.benmohammad.mvp.data.db.DbHelper;
import com.benmohammad.mvp.data.db.model.Option;
import com.benmohammad.mvp.data.db.model.Question;
import com.benmohammad.mvp.data.db.model.User;
import com.benmohammad.mvp.data.network.ApiHeader;
import com.benmohammad.mvp.data.network.ApiHelper;
import com.benmohammad.mvp.data.network.model.BlogResponse;
import com.benmohammad.mvp.data.network.model.LoginRequest;
import com.benmohammad.mvp.data.network.model.LoginResponse;
import com.benmohammad.mvp.data.network.model.LogoutResponse;
import com.benmohammad.mvp.data.network.model.OpenSourceResponse;
import com.benmohammad.mvp.data.prefs.PreferencesHelper;
import com.benmohammad.mvp.di.ApplicationContext;
import com.benmohammad.mvp.utils.AppConstants;
import com.benmohammad.mvp.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context context;
    private final DbHelper dbHelper;
    private final PreferencesHelper preferencesHelper;
    private final ApiHelper apiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
        this.preferencesHelper = preferencesHelper;
        this.apiHelper = apiHelper;
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        apiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        apiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void setUserAsLoggedOut() {

    }

    @Override
    public Observable<Boolean> seedDatabaseQuestions() {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return dbHelper.isQuestionEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty) throws Exception {
                        if(isEmpty) {
                            Type type = $Gson$Types
                                    .newParameterizedTypeWithOwner(null, List.class,
                                            Question.class);
                            List<Question> questionList = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(context,
                                            AppConstants.SEED_DATABASE_QUESTIONS),
                                    type);
                            return saveQuestionList(questionList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Boolean> seedDatabaseOptions() {
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        return dbHelper.isOptionEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty) throws Exception {
                        if(isEmpty) {
                            Type type = new TypeToken<List<Option>>() {
                            }
                            .getType();
                            List<Option> optionList = gson.fromJson(
                                    CommonUtils.loadJSONFromAsset(context,
                                            AppConstants.SEED_DATABASE_OPTIONS),
                                    type);

                            return saveOptionList(optionList);
                        }

                        return Observable.just(false);
                    }
                });
    }

    @Override
    public void updateUserInfo(String accessToken, Long userId, LoggedInMode loggedInMode, String userName, String email, String profilePicPath) {

    }

    @Override
    public Observable<Long> insertUser(User user) {
        return dbHelper.insertUser(user);
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return dbHelper.getAllUsers();
    }

    @Override
    public Observable<List<Question>> getAllQuestions() {
        return null;
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return null;
    }

    @Override
    public Observable<Boolean> isOptionEmpty() {
        return null;
    }

    @Override
    public Observable<Boolean> saveQuestion(Question question) {
        return null;
    }

    @Override
    public Observable<Boolean> saveOption(Option option) {
        return null;
    }

    @Override
    public Observable<Boolean> saveQuestionList(List<Question> questionList) {
        return null;
    }

    @Override
    public Observable<Boolean> saveOptionList(List<Option> optionList) {
        return null;
    }

    @Override
    public ApiHeader getApiHeader() {
        return apiHelper.getApiHeader();
    }

    @Override
    public Single<LoginResponse> doGoogleLoginCall(LoginRequest.GoogleLoginRequest request) {
        return null;
    }

    @Override
    public Single<LoginResponse> doFacebookLoginCall(LoginRequest.FacebookLoginRequest request) {
        return null;
    }

    @Override
    public Single<LoginResponse> doServerLoginCall(LoginRequest.ServerLoginRequest request) {
        return null;
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return null;
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return null;
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return null;
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return 0;
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {

    }

    @Override
    public Long getCurrentUserId() {
        return null;
    }

    @Override
    public void setCurrentUserId(Long userId) {

    }

    @Override
    public String getCurrentUserName() {
        return null;
    }

    @Override
    public void setCurrentUserName(String userName) {

    }

    @Override
    public String getCurrentUserEmail() {
        return null;
    }

    @Override
    public void setCurrentUserEmail(String email) {

    }

    @Override
    public String getCurrentUserProfilePic() {
        return null;
    }

    @Override
    public void setCurrentUserProfilePic(String profilePicUrl) {

    }

    @Override
    public String getAccessToken() {
        return preferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        preferencesHelper.setAccessToken(accessToken);
        apiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }
}
