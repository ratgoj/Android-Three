package com.home_task.saprykin.hometask.model.realm.repos;

import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.network.NetworkHelper;
import com.home_task.saprykin.hometask.model.realm.models.RealmRepoModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import io.realm.RealmResults;

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
                repoModelObserver.onError(e);
            }
        });
    }

    @Override
    public List<RepoModel> findUserRepos(String userLogin) {
        List<RepoModel> result = null;
        RealmResults<RealmRepoModel> realmResults = Realm.getDefaultInstance().where(RealmRepoModel.class).equalTo("repoUserLogin", userLogin).findAll();
        if (!realmResults.isEmpty()) {
            List<RealmRepoModel> realmRepoModels = Realm.getDefaultInstance().copyFromRealm(realmResults);
            result = new ArrayList<>();
            for (RealmRepoModel realmModel : realmRepoModels) {
                result.add(new RepoModel(realmModel.getRepoFullName(), realmModel.getRepoUrlPath()));
            }
        }
        return result;
    }

    @Override
    public void deleteUserRepos(String userLogin) {
        realmInstance.where(RealmRepoModel.class).equalTo("repoUserLogin", userLogin).findAll().deleteAllFromRealm();
    }
}
