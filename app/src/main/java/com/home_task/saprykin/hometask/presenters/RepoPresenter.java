package com.home_task.saprykin.hometask.presenters;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.home_task.saprykin.hometask.model.RepoDataModel;
import com.home_task.saprykin.hometask.views.adapters.RepositoriesAdapter;
import com.home_task.saprykin.hometask.views.interfaces.RepositoryVew;

/**
 * Created by andrejsaprykin on 08/10/2018.
 */
@InjectViewState
public class RepoPresenter extends MvpPresenter<RepositoryVew> {
    private RepoDataModel repoDataModel;
    private RecyclerView.Adapter repoRecyclerAdapter;

    public RepoPresenter() {
        repoDataModel = new RepoDataModel();
        repoRecyclerAdapter = new RepositoriesAdapter(new RepoDataModel().getRepositoriesList(), new RepositoriesAdapter.PositionClickListener() {
            @Override
            public void onPositionClick(int position) {
                onItemClick(position);
            }
        });
    }

    public RecyclerView.Adapter getRepoRecyclerAdapter() {
        return repoRecyclerAdapter;
    }

    private void onItemClick(int position) {
        getViewState().onRepoItemClick(position);
    }
}
