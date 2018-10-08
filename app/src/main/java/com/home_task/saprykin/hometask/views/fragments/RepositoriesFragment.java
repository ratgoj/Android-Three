package com.home_task.saprykin.hometask.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.presenters.RepoPresenter;
import com.home_task.saprykin.hometask.views.interfaces.RepositoryVew;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepositoriesFragment extends MvpAppCompatFragment implements RepositoryVew {
    private static final String REPO_TAG = "Repository";

    private View repoView;
    private RecyclerView repoRecyclerView;
    private RecyclerView.LayoutManager repoLayoutManager;

    @InjectPresenter
    RepoPresenter presenter;

    public RepositoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        repoView = inflater.inflate(R.layout.fragment_repositories, container, false);
        repoLayoutManager = new LinearLayoutManager(getActivity());
        repoRecyclerView = repoView.findViewById(R.id.repo_list);
        repoRecyclerView.setLayoutManager(repoLayoutManager);
        repoRecyclerView.setAdapter(presenter.getRepoRecyclerAdapter());
        return repoView;
    }

    @Override
    public void onRepoItemClick(int position) {
        Log.d(REPO_TAG, "Click on Position: " + position);
    }
}
