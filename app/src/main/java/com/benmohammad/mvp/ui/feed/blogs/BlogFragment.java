package com.benmohammad.mvp.ui.feed.blogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.benmohammad.mvp.R;
import com.benmohammad.mvp.data.network.model.BlogResponse;
import com.benmohammad.mvp.di.component.ActivityComponent;
import com.benmohammad.mvp.di.component.DaggerActivityComponent;
import com.benmohammad.mvp.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlogFragment extends BaseFragment implements BlogMvpView, BlogAdapter.Callback{

    private static final String TAG = "BlogAdapter";

    @Inject
    BlogMvpPresenter<BlogMvpView> presenter;

    @Inject
    BlogAdapter blogAdapter;

    @Inject
    LinearLayoutManager manager;

    @BindView(R.id.blog_recycler_view)
    RecyclerView recyclerView;


    public static BlogFragment newInstance() {
        Bundle args = new Bundle();
        BlogFragment fragment = new BlogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        ActivityComponent component = getActivityComponent();
        if(component != null) {
            component.inject(this);
            setUnbinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
            blogAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    public void setup(View view) {
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(blogAdapter);

        presenter.onViewPrepared();

    }

    @Override
    public void onBlogEmptyViewRetryClick() {

    }

    @Override
    public void updateBlog(List<BlogResponse.Blog> blogList) {
        blogAdapter.addItems(blogList);
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }
}
