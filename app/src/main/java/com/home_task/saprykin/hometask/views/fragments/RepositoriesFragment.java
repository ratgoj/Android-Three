package com.home_task.saprykin.hometask.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.model.RepoItem;
import com.home_task.saprykin.hometask.presenters.RepoPresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.RepositoryVew;
import com.home_task.saprykin.hometask.views.adapters.RepositoriesAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepositoriesFragment extends MvpAppCompatFragment implements RepositoryVew {
    private static final String REPO_TAG = "Repository";

    private View repoView;
    private EditText searchRepo;
    private RecyclerView repoRecyclerView;
    private RecyclerView.LayoutManager repoLayoutManager;
    private RepositoriesAdapter repoRecyclerAdapter;

    @InjectPresenter
    RepoPresenter presenter;

    public RepositoriesFragment() {
        // Required empty public constructo
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        repoView = inflater.inflate(R.layout.fragment_repositories, container, false);

        initView(repoView);

        return repoView;
    }

    private void initView(View repoView) {
        repoLayoutManager = new LinearLayoutManager(getActivity());
        repoRecyclerView = repoView.findViewById(R.id.repo_list);
        repoRecyclerView.setLayoutManager(repoLayoutManager);
        repoRecyclerAdapter = new RepositoriesAdapter(presenter.getRepositoryList(), new RepositoriesAdapter.PositionClickListener() {
            @Override
            public void onPositionClick(int position) {
                onRepoItemClick(position);
            }
        });

        repoRecyclerView.setAdapter(repoRecyclerAdapter);

        searchRepo = repoView.findViewById(R.id.repo_search);
        TextWatcher searchWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.searchRepo(s.toString());
            }
        };
        searchRepo.addTextChangedListener(searchWatcher);
    }

    public void onRepoItemClick(int position) {
        Log.d(REPO_TAG, "Click on Position: " + position);
    }

    @Override
    public void updateRepoList(List<RepoItem> newRepoList) {
        repoRecyclerAdapter.updateData(newRepoList);
    }

    @Override
    public void setSearchText(String text) {
        searchRepo.setText(text);
    }
}
