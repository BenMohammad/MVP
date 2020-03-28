package com.benmohammad.mvp.ui.feed.opensource;

import android.graphics.Path;
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
import com.benmohammad.mvp.data.network.model.OpenSourceResponse;
import com.benmohammad.mvp.di.component.ActivityComponent;
import com.benmohammad.mvp.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenSourceFragment extends BaseFragment implements OpenSourceMvpView, OpenSourceAdapter.Callback {

    private static final String TAG = "OpenSourceFragment";

    @Inject
    OpenSourceMvpPresenter<OpenSourceMvpView> presenter;

    @Inject
    OpenSourceAdapter adapter;

    @Inject
    LinearLayoutManager manager;

    @BindView(R.id.repo_recycler_view)
    RecyclerView recyclerView;

    public static OpenSourceFragment newInstance() {
        Bundle args = new Bundle();
        OpenSourceFragment fragment = new OpenSourceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_source, container, false);

        ActivityComponent component = getActivityComponent();
        if(component != null) {
            component.inject(this);
            setUnbinder(ButterKnife.bind(this, view));
            presenter.onAttach(this);
            adapter.setCallback(this);
        }
        return view;
    }

    @Override
    public void setup(View view) {
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRepoEmptyViewRetryClick() {

    }

    @Override
    public void updateRepo(List<OpenSourceResponse.Repo> repoList) {
        adapter.addItems(repoList);
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }
}
