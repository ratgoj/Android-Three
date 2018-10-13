package com.home_task.saprykin.hometask.presenters.interfaces;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.home_task.saprykin.hometask.model.RepoItem;

import java.util.List;

/**
 * Created by andrejsaprykin on 08/10/2018.
 */
public interface RepositoryVew extends MvpView {
    void updateRepoList(List<RepoItem> newRepoList);

    @StateStrategyType(SkipStrategy.class)
    void setSearchText(String text);
}
