package com.home_task.saprykin.hometask.model.realm.repos;

import com.home_task.saprykin.hometask.model.entities.models.RepoModel;

import java.util.List;

import io.reactivex.SingleObserver;

/**
 * Created by andrejsaprykin on 23/10/2018.
 */

public interface ReposInfoData {

    void inputUserRepos(String userLogin, SingleObserver<List<RepoModel>> repoModelObserver);

    List<RepoModel> findUserRepos(String userLogin);

    void deleteUserRepos(String userLogin);
}
