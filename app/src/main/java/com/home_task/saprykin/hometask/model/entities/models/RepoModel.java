package com.home_task.saprykin.hometask.model.entities.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

/**
 * Created by andrejsaprykin on 17/10/2018.
 */

public class RepoModel {

    @SerializedName("full_name")
    @Expose
    private String repoName;

    @SerializedName("html_url")
    @Expose
    private String urlPath;

    public RepoModel(String repoName, String urlPath) {
        this.repoName = repoName;
        this.urlPath = urlPath;
    }

    @Nullable
    public String getRepoName() {
        return repoName;
    }

    @Nullable
    public String getUrlPath() {
        return urlPath;
    }


}
