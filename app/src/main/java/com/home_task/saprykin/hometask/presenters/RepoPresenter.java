package com.home_task.saprykin.hometask.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.network.NetworkHelper;
import com.home_task.saprykin.hometask.model.realm.RealmDbHelper;
import com.home_task.saprykin.hometask.presenters.base.BasePresenterSingle;
import com.home_task.saprykin.hometask.presenters.interfaces.RepositoryVew;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andrejsaprykin on 08/10/2018.
 */
@InjectViewState
public class RepoPresenter extends BasePresenterSingle<RepositoryVew, List<RepoModel>> {
    private static final String REPO_PRESENTER_TAG = "Repository presenter";
    private String searchQuery;
    private List<RepoModel> repoList;


    public RepoPresenter() {
        super();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void attachView(RepositoryVew view) {
        super.attachView(view);
        getViewState().setSearchText(searchQuery);
        getViewState().loadUserRepo();
    }

    public void searchRepo(final String repoName) {
        if (repoName != null && !repoName.isEmpty()) {
            searchQuery = repoName;
            Flowable.fromIterable(repoList)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .filter(repoModel -> {
                        return repoModel.getRepoName().toLowerCase().contains(repoName.toLowerCase());
                    })
                    .toList()
                    .subscribe(this);
        } else {
            getViewState().updateRepoList(repoList);
        }
    }


    public List<RepoModel> getRepositoryList() {
        return repoList;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        getViewState().updateRepoList(repoList);
    }

    @Override
    public void onSuccess(List<RepoModel> repoItems) {
        super.onSuccess(repoItems);
        getViewState().hideLoading();
        getViewState().updateRepoList(repoItems);
    }

    private void loadData(String userLogin) {
        getViewState().showLoading();
        NetworkHelper.getInstance().getRepos(userLogin).toList().subscribe(new SingleObserver<List<List<RepoModel>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<List<RepoModel>> lists) {
                RepoPresenter.this.onSuccess(lists.get(0));
                RealmDbHelper.getInstance().loadUserRepos(userLogin, lists.get(0));
            }

            @Override
            public void onError(Throwable e) {
                RepoPresenter.this.onError(e);
            }
        });
    }

    public void loadDbData(String userLogin) {
        String login = userLogin;
        if (login == null || login.isEmpty())
            login = "ratgoj";
        List<RepoModel> dbRepoResult = RealmDbHelper.getInstance().findUserRepos(login);
        if (dbRepoResult != null) {
            repoList = dbRepoResult;
            RepoPresenter.this.onSuccess(repoList);
        } else {
            loadData(login);
        }
    }
}
