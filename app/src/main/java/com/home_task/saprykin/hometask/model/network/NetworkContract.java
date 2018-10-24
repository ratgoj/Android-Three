package com.home_task.saprykin.hometask.model.network;

import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface NetworkContract {
    Observable<UserGitHub> getUser(String user);
    Flowable<List<RepoModel>> getRepos(String userLogin);
}
