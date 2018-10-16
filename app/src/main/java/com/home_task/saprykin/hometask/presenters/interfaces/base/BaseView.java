package com.home_task.saprykin.hometask.presenters.interfaces.base;

import com.arellomobile.mvp.MvpView;

/**
 * Created by andrejsaprykin on 13/10/2018.
 */

public interface BaseView extends MvpView {

    void showLoading();
    void hideLoading();
    void showError(String errorText);
}
