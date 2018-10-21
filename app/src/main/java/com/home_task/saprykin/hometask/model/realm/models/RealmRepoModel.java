package com.home_task.saprykin.hometask.model.realm.models;

import io.realm.RealmObject;

public class RealmRepoModel extends RealmObject {
    private String repoFullName;
    private String repoUrlPath;
    private String repoUserLogin;

    public String getRepoFullName() {
        return repoFullName;
    }

    public void setRepoFullName(String repoFullName) {
        this.repoFullName = repoFullName;
    }

    public String getRepoUrlPath() {
        return repoUrlPath;
    }

    public void setRepoUrlPath(String repoUrlPath) {
        this.repoUrlPath = repoUrlPath;
    }

    public String getRepoUserLogin() {
        return repoUserLogin;
    }

    public void setRepoUserLogin(String repoUserLogin) {
        this.repoUserLogin = repoUserLogin;
    }
}
