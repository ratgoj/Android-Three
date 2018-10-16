package com.home_task.saprykin.hometask.presenters.interfaces;

import com.home_task.saprykin.hometask.presenters.interfaces.base.BaseView;

public interface ProfileView extends BaseView {
    void setText(int viewId, String currentText);
    void setImage();
}
