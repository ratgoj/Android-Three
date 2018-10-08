package com.home_task.saprykin.hometask.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.views.interfaces.ProfileView;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {

    public ProfilePresenter() {
    }

    public void setProfileInfo() {
        getViewState().setText(R.id.profile_date_text, "23.05.2018");
        getViewState().setText(R.id.profile_full_name_text, "Andrey Saprykin");
        getViewState().setText(R.id.profile_nick_name_text, "@Helkar");
    }

    public void setProfileImage() {
        getViewState().setImage();
    }
}
