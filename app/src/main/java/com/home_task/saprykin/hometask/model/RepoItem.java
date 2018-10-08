package com.home_task.saprykin.hometask.model;

/**
 * Created by andrejsaprykin on 08/10/2018.
 */

public class RepoItem {
    private String repoName;
    private String repoUpdateDate;

    public RepoItem(String repoName, String repoUpdateDate) {
        this.repoName = repoName;
        this.repoUpdateDate = repoUpdateDate;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoUpdateDate() {
        return repoUpdateDate;
    }

    public void setRepoUpdateDate(String repoUpdateDate) {
        this.repoUpdateDate = repoUpdateDate;
    }
}
