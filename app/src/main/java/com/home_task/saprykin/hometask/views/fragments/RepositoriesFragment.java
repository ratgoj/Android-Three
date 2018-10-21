package com.home_task.saprykin.hometask.views.fragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.home_task.saprykin.hometask.AppContext;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.presenters.RepoPresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.RepositoryVew;
import com.home_task.saprykin.hometask.views.adapters.RepositoriesAdapter;
import com.home_task.saprykin.hometask.views.base.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepositoriesFragment extends BaseFragment implements RepositoryVew {
    private static final String REPO_TAG = "Repository";


    private EditText searchRepo;
    private RecyclerView repoRecyclerView;
    private RecyclerView.LayoutManager repoLayoutManager;
    private RepositoriesAdapter repoRecyclerAdapter;

    @InjectPresenter()
    RepoPresenter presenter;

    @ProvidePresenter
    RepoPresenter providePresenter() {
        return new RepoPresenter();
    }

    public RepositoriesFragment() {
        layout = R.layout.fragment_repositories;
    }

    @Override
    protected void initView() {
        repoLayoutManager = new LinearLayoutManager(getActivity());
        repoRecyclerView = currentFragmentView.findViewById(R.id.repo_list);
        repoRecyclerView.setLayoutManager(repoLayoutManager);
        repoRecyclerAdapter = new RepositoriesAdapter(presenter.getRepositoryList(), position -> onRepoItemClick(position));

        repoRecyclerView.setAdapter(repoRecyclerAdapter);

        searchRepo = currentFragmentView.findViewById(R.id.repo_search);
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
    public void updateRepoList(List<RepoModel> newRepoList) {
        repoRecyclerAdapter.updateData(newRepoList);
    }

    @Override
    public void setSearchText(String text) {
        searchRepo.setText(text);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        currentFragmentView.findViewById(R.id.repo_progress_view).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        currentFragmentView.findViewById(R.id.repo_progress_view).setVisibility(View.GONE);
    }

    private String getSavedUserLogin() {
        return  ((AppContext)getActivity().getApplicationContext()).getSavedUserLogin(getActivity().getLocalClassName());
    }

    @Override
    public void loadUserRepo() {
        presenter.loadDbData(getSavedUserLogin());
    }
}
