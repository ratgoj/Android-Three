package com.home_task.saprykin.hometask.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrejsaprykin on 08/10/2018.
 */

public class RepoDataModel {
    List<RepoItem> repositoriesList;

    public RepoDataModel() {
        repositoriesList = new ArrayList<>();
        setDefauldData();
    }

    public void addRepository(String repoName, String updateDate){
        if(repositoriesList != null)
            repositoriesList.add(new RepoItem(repoName, updateDate));
    }

    public void removeRepository(int repoIndex){
        if(repositoriesList !=null)
            repositoriesList.remove(repoIndex);
    }

    public void removeRepository(RepoItem repoItem){
        if(repositoriesList !=null)
            repositoriesList.remove(repoItem);
    }

    private void setDefauldData(){
        addRepository("First Repository", "07.10.2018");
        addRepository("Second Repository", "10 minutes ago");
        addRepository("Third Repository", "2 hours ago");
    }

    public List<RepoItem> getRepositoriesList() {
        return repositoriesList;
    }
}
