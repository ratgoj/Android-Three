package com.home_task.saprykin.hometask.model.network;

import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;

import java.util.List;

import javax.inject.Named;

import dagger.Component;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by andrejsaprykin on 24/10/2018.
 */

@Component(modules = {ModuleNetwork.class})
public interface NetworkApiComponent {
    Observable<UserGitHub> getUser();

    Flowable<List<RepoModel>> getRepos();

    void injectLogin(@Named("login") String userLogin);
}
