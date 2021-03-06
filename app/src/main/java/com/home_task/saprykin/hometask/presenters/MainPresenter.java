package com.home_task.saprykin.hometask.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.presenters.interfaces.ContainerView;
import com.home_task.saprykin.hometask.views.MainActivity;
import com.home_task.saprykin.hometask.views.fragments.ProfileFragment;
import com.home_task.saprykin.hometask.views.fragments.RepositoriesFragment;

@InjectViewState
public class MainPresenter extends MvpPresenter<ContainerView> {
    ProfileFragment profileFragment;
    RepositoriesFragment repositoriesFragment;
    private int currentScreenId = -1;

    public void setCurrentFragment(int screenId) {
        switch (screenId) {
            case R.id.item_profile:
                getViewState().setFragment(profileFragment);
                setCurrentScreenId(R.id.item_profile);
                break;
            case R.id.item_repositories:
                getViewState().setFragment(repositoriesFragment);
                setCurrentScreenId(R.id.item_repositories);
                break;
            default:
                getViewState().setFragment(profileFragment);
                setCurrentScreenId(R.id.item_profile);
                break;
        }
    }

    public void setCurrentScreenId(int currentScreenId) {
        this.currentScreenId = currentScreenId;
    }

    public void setDefaultScreen() {
        if(currentScreenId != -1)
            setCurrentFragment(currentScreenId);
        else
            getViewState().setDefaultFragment();
    }

    public void setProfileFragment(ProfileFragment profileFragment) {
        this.profileFragment = profileFragment;
    }

    public void setRepositoriesFragment(RepositoriesFragment repositoriesFragment) {
        this.repositoriesFragment = repositoriesFragment;
    }

    public ProfileFragment getProfileFragment() {
        return profileFragment;
    }

    public RepositoriesFragment getRepositoriesFragment() {
        return repositoriesFragment;
    }
}
