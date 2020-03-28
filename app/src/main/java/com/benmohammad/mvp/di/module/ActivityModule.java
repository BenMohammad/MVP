package com.benmohammad.mvp.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.benmohammad.mvp.data.network.model.BlogResponse;
import com.benmohammad.mvp.data.network.model.OpenSourceResponse;
import com.benmohammad.mvp.di.ActivityContext;
import com.benmohammad.mvp.di.PerActivity;
import com.benmohammad.mvp.ui.about.AboutMvpPresenter;
import com.benmohammad.mvp.ui.about.AboutMvpView;
import com.benmohammad.mvp.ui.about.AboutPresenter;
import com.benmohammad.mvp.ui.feed.FeedMvpPresenter;
import com.benmohammad.mvp.ui.feed.FeedMvpView;
import com.benmohammad.mvp.ui.feed.FeedPagerAdapter;
import com.benmohammad.mvp.ui.feed.FeedPresenter;
import com.benmohammad.mvp.ui.feed.blogs.BlogAdapter;
import com.benmohammad.mvp.ui.feed.blogs.BlogMvpPresenter;
import com.benmohammad.mvp.ui.feed.blogs.BlogMvpView;
import com.benmohammad.mvp.ui.feed.blogs.BlogPresenter;
import com.benmohammad.mvp.ui.feed.opensource.OpenSourceAdapter;
import com.benmohammad.mvp.ui.feed.opensource.OpenSourceMvpPresenter;
import com.benmohammad.mvp.ui.feed.opensource.OpenSourceMvpView;
import com.benmohammad.mvp.ui.feed.opensource.OpenSourcePresenter;
import com.benmohammad.mvp.ui.login.LoginMvpPresenter;
import com.benmohammad.mvp.ui.login.LoginMvpView;
import com.benmohammad.mvp.ui.login.LoginPresenter;
import com.benmohammad.mvp.ui.main.MainMvpPresenter;
import com.benmohammad.mvp.ui.main.MainMvpView;
import com.benmohammad.mvp.ui.main.MainPresenter;
import com.benmohammad.mvp.ui.main.rating.RatingDialogMvpPresenter;
import com.benmohammad.mvp.ui.main.rating.RatingDialogMvpView;
import com.benmohammad.mvp.ui.main.rating.RatingDialogPresenter;
import com.benmohammad.mvp.ui.splash.SplashActivity;
import com.benmohammad.mvp.ui.splash.SplashMvpPresenter;
import com.benmohammad.mvp.ui.splash.SplashMvpView;
import com.benmohammad.mvp.ui.splash.SplashPresenter;
import com.benmohammad.mvp.utils.rx.AppSchedulerProvider;
import com.benmohammad.mvp.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }




    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(AboutPresenter<AboutMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    RatingDialogMvpPresenter<RatingDialogMvpView> provideRatingPresenter(RatingDialogPresenter<RatingDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedMvpPresenter<FeedMvpView> provideFeedPresenter(FeedPresenter<FeedMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    OpenSourceMvpPresenter<OpenSourceMvpView> provideOpenSourcePresenter(OpenSourcePresenter<OpenSourceMvpView> presenter) {
        return presenter;
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
    }

    @Provides
    @PerActivity
    BlogMvpPresenter<BlogMvpView> provideBlogPresenter(BlogPresenter<BlogMvpView> presenter) {
        return presenter;
    }


    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    BlogAdapter provideBlogAdapter() {
        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
