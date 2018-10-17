package com.home_task.saprykin.hometask.model.network;

import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by andrejsaprykin on 17/10/2018.
 */

public class NetworkHelper {

    private static NetworkHelper instance;
    private NetworkApiRequest networkApiRequest;

    private NetworkHelper() {
        networkApiRequest = new NetworkService().createService(NetworkApiRequest.class);
    }

    public static NetworkHelper getInstance() {
        if(instance == null){
            instance = new NetworkHelper();
        }
        return instance;
    }

    public Observable<UserGitHub> getUser(String user){
        return networkApiRequest.getUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<RepoModel>> getReps(){
        return networkApiRequest.getRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
