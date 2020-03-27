package com.benmohammad.mvp.ui.feed.blogs;

import com.benmohammad.mvp.data.network.model.BlogResponse;
import com.benmohammad.mvp.ui.base.MvpView;

import java.util.List;

public interface BlogMvpView extends MvpView {

    void updateBlog(List<BlogResponse.Blog> blogList);
}
