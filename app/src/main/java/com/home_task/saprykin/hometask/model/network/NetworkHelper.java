package com.home_task.saprykin.hometask.model.network;

import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andrejsaprykin on 17/10/2018.
 */

public class NetworkHelper implements NetworkContract {

    NetworkApiRequest networkApiRequest;

    @Inject
    public NetworkHelper(NetworkService networkService) {
        networkApiRequest =
                networkService
                        .createService(NetworkApiRequest.class);
    }

    @Override
    public Observable<UserGitHub> getUser(String user) {
        return networkApiRequest.getUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<RepoModel>> getRepos(String userLogin) {
        return networkApiRequest.getRepos(userLogin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
