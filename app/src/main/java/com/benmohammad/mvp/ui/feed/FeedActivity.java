package com.benmohammad.mvp.ui.feed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.viewpager.widget.ViewPager;

import com.benmohammad.mvp.R;
import com.benmohammad.mvp.ui.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedActivity extends BaseActivity implements FeedMvpView {

    @Inject
    FeedMvpPresenter<FeedMvpView> presenter;

    @Inject
    FeedPagerAdapter adapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.feed_view_pager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, FeedActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));
        presenter.onAttach(this);
        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        adapter.setCount(2);
        viewPager.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.blog)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.open_source)));
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if(NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)

                            .startActivities();
                } else {
                    NavUtils.navigateUpTo(this, upIntent);
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
