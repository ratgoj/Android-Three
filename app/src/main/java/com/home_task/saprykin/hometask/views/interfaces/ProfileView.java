package com.home_task.saprykin.hometask.views.interfaces;

import com.arellomobile.mvp.MvpView;

public interface ProfileView extends MvpView {
    void setText(int viewId, String currentText);
    void setImage();
}
