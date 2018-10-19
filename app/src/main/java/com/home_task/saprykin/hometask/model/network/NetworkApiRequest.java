package com.home_task.saprykin.hometask.model.network;

import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by andrejsaprykin on 17/10/2018.
 */

public interface NetworkApiRequest {

    @GET("/users/{user}")
    Observable<UserGitHub> getUser(@Path("user") String user);

    @GET("/users/{user}/repos")
    Flowable<List<RepoModel>> getRepos(@Path("user") String user);
}
