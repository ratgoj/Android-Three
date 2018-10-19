package com.home_task.saprykin.hometask.presenters.interfaces;

import com.home_task.saprykin.hometask.presenters.interfaces.base.BaseView;

public interface ProfileView extends BaseView {
    void setProfileFullName(String name);
    void setProfileNick(String nick);
    void setProfileDateCreation(String dateCreation);

    void setImage(String imageUrl);
}
