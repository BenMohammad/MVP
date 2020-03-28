package com.benmohammad.mvp.di.component;

import com.benmohammad.mvp.di.PerActivity;
import com.benmohammad.mvp.di.module.ActivityModule;
import com.benmohammad.mvp.ui.about.AboutFragment;
import com.benmohammad.mvp.ui.feed.FeedActivity;
import com.benmohammad.mvp.ui.feed.blogs.BlogFragment;
import com.benmohammad.mvp.ui.feed.opensource.OpenSourceFragment;
import com.benmohammad.mvp.ui.login.LoginActivity;
import com.benmohammad.mvp.ui.main.MainActivity;
import com.benmohammad.mvp.ui.main.rating.RateUsDialog;
import com.benmohammad.mvp.ui.splash.SplashActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(SplashActivity activity);
    void inject(LoginActivity activity);
    void inject(FeedActivity activity);
    void inject(AboutFragment fragment);
    void inject(OpenSourceFragment fragment);
    void inject(BlogFragment fragment);
    void inject(RateUsDialog dialog);
}
