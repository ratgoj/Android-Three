package com.home_task.saprykin.hometask.views.interfaces;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;

public interface FragmentContainerView extends MvpView {
    void setFragment(Fragment currentFragment);
    void setDefaultFragment();
}
