package com.benmohammad.mvp.ui.feed;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.benmohammad.mvp.ui.feed.blogs.BlogFragment;
import com.benmohammad.mvp.ui.feed.opensource.OpenSourceFragment;

public class FeedPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public FeedPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.tabCount = 0;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return BlogFragment.newInstance();
            case 1:
                return OpenSourceFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount ;
    }

    public void setCount(int count) {
        tabCount = count;
    }
}
