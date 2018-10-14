package com.home_task.saprykin.hometask.presenters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.MvpViewState;
import com.home_task.saprykin.hometask.model.RepoDataModel;
import com.home_task.saprykin.hometask.model.RepoItem;
import com.home_task.saprykin.hometask.presenters.base.BasePresenterSingle;
import com.home_task.saprykin.hometask.presenters.interfaces.RepositoryVew;
import com.home_task.saprykin.hometask.views.adapters.RepositoriesAdapter;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andrejsaprykin on 08/10/2018.
 */
@InjectViewState
public class RepoPresenter extends BasePresenterSingle<RepositoryVew, List<RepoItem>> {
    private static final String REPO_PRESENTER_TAG = "Repository presenter";
    private RepoDataModel repoDataModel;
    private String searchQuery;

    public RepoPresenter(RepoDataModel repoModel) {
        repoDataModel = repoModel;
    }

    @Override
    public void attachView(RepositoryVew view) {
        super.attachView(view);
        getViewState().setSearchText(searchQuery);
    }

    public void searchRepo(final String repoName) {
        if (repoName != null && !repoName.isEmpty()) {
            searchQuery = repoName;
            Flowable.fromIterable(repoDataModel.getRepositoriesList())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .filter(repoItem -> {return repoItem.getRepoName().contains(repoName);})
                    .toList()
                    .subscribe(this);
        } else {
            getViewState().updateRepoList(repoDataModel.getRepositoriesList());
        }
    }


    public List<RepoItem> getRepositoryList(){
        return repoDataModel.getRepositoriesList();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        getViewState().updateRepoList(repoDataModel.getRepositoriesList());
    }

    @Override
    public void onSuccess(List<RepoItem> repoItems) {
        super.onSuccess(repoItems);
        getViewState().updateRepoList(repoItems);
    }
}
