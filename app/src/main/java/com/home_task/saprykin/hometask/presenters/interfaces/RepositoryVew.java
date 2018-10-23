package com.home_task.saprykin.hometask.presenters.interfaces;


import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.presenters.interfaces.base.BaseView;

import java.util.List;

/**
 * Created by andrejsaprykin on 08/10/2018.
 */
public interface RepositoryVew extends BaseView {
    void updateRepoList(List<RepoModel> newRepoList);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setSearchText(String text);

    void loadUserRepo();
}
