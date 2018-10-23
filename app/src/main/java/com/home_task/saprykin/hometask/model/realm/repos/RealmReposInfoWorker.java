package com.home_task.saprykin.hometask.model.realm.repos;

import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.network.NetworkHelper;
import com.home_task.saprykin.hometask.model.realm.models.RealmRepoModel;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

/**
 * Created by andrejsaprykin on 23/10/2018.
 */

public class RealmReposInfoWorker implements ReposInfoData {
    Realm realmInstance;
    NetworkHelper networkHelper;

    public RealmReposInfoWorker() {
        this.realmInstance = Realm.getDefaultInstance();
        this.networkHelper = NetworkHelper.getInstance();
    }

    @Override
    public void inputUserRepos(String userLogin, SingleObserver<List<RepoModel>> repoModelObserver) {
        networkHelper.getRepos(userLogin).toList().subscribe(new SingleObserver<List<List<RepoModel>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<List<RepoModel>> lists) {
                List<RepoModel> resultList = lists.get(0);
                for (RepoModel model : resultList) {
                    realmInstance.beginTransaction();
                    RealmRepoModel realmRepoModel = realmInstance.createObject(RealmRepoModel.class);
                    realmRepoModel.setRepoFullName(model.getRepoName());
                    realmRepoModel.setRepoUrlPath(model.getUrlPath());
                    realmRepoModel.setRepoUserLogin(userLogin);
                    realmInstance.commitTransaction();
                }
                realmInstance.close();
                repoModelObserver.onSuccess(resultList);
            }

            @Override
            public void onError(Throwable e) {
                if (realmInstance.isInTransaction())
                    realmInstance.cancelTransaction();
            }
        });
    }

    @Override
    public List<RepoModel> findUserRepos(String userLogin) {
        return null;
    }

    @Override
    public void deleteUserRepos(String userLogin) {

    }
}
