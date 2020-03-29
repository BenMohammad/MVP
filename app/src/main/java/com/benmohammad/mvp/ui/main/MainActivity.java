package com.benmohammad.mvp.ui.main;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.benmohammad.mvp.BuildConfig;
import com.benmohammad.mvp.R;
import com.benmohammad.mvp.data.db.model.Question;
import com.benmohammad.mvp.ui.about.AboutFragment;
import com.benmohammad.mvp.ui.base.BaseActivity;
import com.benmohammad.mvp.ui.custom.RoundedImageView;
import com.benmohammad.mvp.ui.feed.FeedActivity;
import com.benmohammad.mvp.ui.login.LoginActivity;
import com.benmohammad.mvp.ui.main.rating.RateUsDialog;
import com.benmohammad.mvp.utils.ScreenUtils;
import com.google.android.material.navigation.NavigationView;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {


    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.tv_app_version)
    TextView appVersionTextView;

    @BindView(R.id.cards_container)
    SwipePlaceHolderView cardContainerView;

    private TextView nameTextView;
    private TextView emailTextView;
    private RoundedImageView profileImageView;
    private ActionBarDrawerToggle drawerToggle;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);
        setUp();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(drawer != null) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
        if(fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(AboutFragment.TAG);
        }
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        setUpNavMenu();
        presenter.onNavMenuCreated();
        setUpCardContainerView();
        presenter.onViewInitialized();

    }

    private void setUpCardContainerView() {
        int screenWidth = ScreenUtils.geeScreenWidth(this);
        int screenHeight = ScreenUtils.getScreenHeight(this);

        cardContainerView.getBuilder()
                .setDisplayViewCount(3)
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                .setViewWidth((int) (0.90 * screenWidth))
                .setViewHeight((int)(0.75 * screenHeight))
                .setPaddingTop(20)
                .setSwipeRotationAngle(10)
                .setRelativeScale(0.01f));

        cardContainerView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                if(count == 0) {
                    new Handler(getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            presenter.onCardExhausted();
                        }
                    },800
                            );
                }
            }
        });
    }

    private void setUpNavMenu() {
        View headerLayout = navigationView.getHeaderView(0);
        profileImageView = (RoundedImageView) headerLayout.findViewById(R.id.iv_profile_pic);
        nameTextView = (TextView) headerLayout.findViewById(R.id.tv_name);
        emailTextView = (TextView) headerLayout.findViewById(R.id.tv_email);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.nav_item_about:
                        presenter.onDrawerOptionAboutClick();
                        return true;
                    case R.id.nav_item_rate_us:
                        presenter.onDrawerRateUsClick();
                        return true;
                    case R.id.nav_item_feed:
                        presenter.onDrawerMyFeedClick();
                        return true;
                    case R.id.nav_item_logout:
                        presenter.onDrawerOptionLogoutClick();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.startIntent(this));
        finish();
    }

    @Override
    public void showAboutFragment() {
        lockDrawer();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }

    @Override
    public void refreshQuestionnaire(List<Question> questionList) {
        for(Question question : questionList) {
            if(question != null && question.getOptionList() != null && question.getOptionList().size() == 3) {
                cardContainerView.addView(new QuestionCard(question));
            }
        }
    }

    @Override
    public void reloadQuestionnaire(List<Question> questionList) {
        refreshQuestionnaire(questionList);
        ScaleAnimation animation = new ScaleAnimation(1.15f, 1, 1.15f, 1, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        cardContainerView.setAnimation(animation);
        animation.setDuration(100);
        animation.start();
    }

    @Override
    public void updateUserName(String currentUserName) {
        nameTextView.setText(currentUserName);
    }

    @Override
    public void updateUserEmail(String currentEmail) {
        emailTextView.setText(currentEmail);
    }

    @Override
    public void updateUserProfilePic(String currentUserProfilePic) {

    }

    @Override
    public void updateAppVersion() {
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        appVersionTextView.setText(version);
    }

    @Override
    public void showRateUsDialog() {
        RateUsDialog.newInstance().show(getSupportFragmentManager());
    }

    @Override
    public void openMyFeedActivity() {
        startActivity(FeedActivity.getStartIntent(this));
    }

    @Override
    public void closeNavigationDrawer() {
        if(drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void lockDrawer() {
        if(drawer != null) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    @Override
    public void unlockDrawer() {
        if(drawer != null) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        }
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(tag);
        if(fragment != null) {
            manager.beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Drawable drawable = item.getIcon();
        if(drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        switch(item.getItemId()) {
            case R.id.action_cut:
                return true;
            case R.id.action_copy:
                return true;
            case R.id.action_share:
                return true;
            case R.id.action_delete:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentAttached() {

    }
}
