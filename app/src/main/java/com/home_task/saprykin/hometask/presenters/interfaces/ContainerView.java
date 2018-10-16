package com.home_task.saprykin.hometask.presenters.interfaces;

import android.support.v4.app.Fragment;

import com.home_task.saprykin.hometask.presenters.interfaces.base.BaseView;

public interface ContainerView extends BaseView {
    void setFragment(Fragment currentFragment);
    void setDefaultFragment();
}
