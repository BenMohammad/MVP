package com.benmohammad.mvp.ui.feed.opensource;

import com.benmohammad.mvp.data.network.model.OpenSourceResponse;
import com.benmohammad.mvp.ui.base.MvpView;

import java.util.List;

public interface OpenSourceMvpView extends MvpView {

    void updateRepo(List<OpenSourceResponse.Repo> repoList);
}
