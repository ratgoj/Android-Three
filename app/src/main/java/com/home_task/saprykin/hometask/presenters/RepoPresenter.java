package com.home_task.saprykin.hometask.presenters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.home_task.saprykin.hometask.model.RepoDataModel;
import com.home_task.saprykin.hometask.model.RepoItem;
import com.home_task.saprykin.hometask.presenters.interfaces.RepositoryVew;
import com.home_task.saprykin.hometask.views.adapters.RepositoriesAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andrejsaprykin on 08/10/2018.
 */
@InjectViewState
public class RepoPresenter extends MvpPresenter<RepositoryVew> {
    private static final String REPO_PRESENTER_TAG = "Repository presenter";
    private RepoDataModel repoDataModel;
    private RepositoriesAdapter repoRecyclerAdapter;

    public RepoPresenter() {
        repoDataModel = new RepoDataModel();
        repoRecyclerAdapter = new RepositoriesAdapter(repoDataModel.getRepositoriesList(), new RepositoriesAdapter.PositionClickListener() {
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

    public void searchRepo(final String repoName) {
        if (repoName != null && !repoName.isEmpty()) {
            Observable
                    .fromIterable(repoDataModel.getRepositoriesList())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .filter(new Predicate<RepoItem>() {
                        @Override
                        public boolean test(RepoItem repoItem) throws Exception {
                            return repoItem.getRepoName().contains(repoName);
                        }
                    }).toList()
                    .subscribe(new SingleObserver<List<RepoItem>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(List<RepoItem> repoItems) {
                            repoRecyclerAdapter.updateData(repoItems);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(REPO_PRESENTER_TAG, "Error: " + e.getMessage());
                            repoRecyclerAdapter.updateData(repoDataModel.getRepositoriesList());
                        }
                    });
        } else {
            repoRecyclerAdapter.updateData(repoDataModel.getRepositoriesList());
        }
    }
}
