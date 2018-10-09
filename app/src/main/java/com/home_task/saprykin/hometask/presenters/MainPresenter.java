package com.home_task.saprykin.hometask.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.views.fragments.ProfileFragment;
import com.home_task.saprykin.hometask.views.fragments.RepositoriesFragment;
import com.home_task.saprykin.hometask.presenters.interfaces.FragmentContainerView;

@InjectViewState
public class MainPresenter extends MvpPresenter<FragmentContainerView> {
    ProfileFragment profileFragment;
    RepositoriesFragment repositoriesFragment;
    private int currentScreenId = -1;

    public MainPresenter() {
    }

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

    private void setCurrentScreenId(int currentScreenId) {
        this.currentScreenId = currentScreenId;
    }

    public void setDefaultScreen() {
        if (currentScreenId == -1)
            getViewState().setDefaultFragment();
        else
            setCurrentFragment(currentScreenId);
    }

    public void setProfileFragment(ProfileFragment profileFragment) {
        this.profileFragment = profileFragment;
    }

    public void setRepositoriesFragment(RepositoriesFragment repositoriesFragment) {
        this.repositoriesFragment = repositoriesFragment;
    }
}
