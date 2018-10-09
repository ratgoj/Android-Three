package com.home_task.saprykin.hometask.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.model.ProfileDataModel;
import com.home_task.saprykin.hometask.views.interfaces.ProfileView;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {
    ProfileDataModel profileDataModel;

    public ProfilePresenter() {
        profileDataModel = new ProfileDataModel();
    }

    public void setProfileInfo() {
        getViewState().setText(R.id.profile_date_text, profileDataModel.getDateOfCreationProfile());
        getViewState().setText(R.id.profile_full_name_text, profileDataModel.getUserName());
        getViewState().setText(R.id.profile_nick_name_text, "@"+profileDataModel.getUserNick());
    }

    public void setProfileImage() {
        getViewState().setImage();
    }
}
