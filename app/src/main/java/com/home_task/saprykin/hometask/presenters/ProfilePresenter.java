package com.home_task.saprykin.hometask.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;
import com.home_task.saprykin.hometask.model.network.NetworkHelper;
import com.home_task.saprykin.hometask.model.realm.RealmDbHelper;
import com.home_task.saprykin.hometask.presenters.base.BasePresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.ProfileView;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileView, UserGitHub> {
    private String currentUserLogin = "ratgoj";

    public ProfilePresenter() {
        super();
    }

    public void setProfileInfo() {
        loadProfileFromDb(currentUserLogin);
    }

    public void findProfileInfo(String userLogin) {
        currentUserLogin = userLogin;
        loadProfileFromDb(userLogin);
    }

    private void loadProfileFromNet(String userLogin) {
        getViewState().showLoading();
        RealmDbHelper.getInstance().loadUserData(userLogin, this);
    }

    private void loadProfileFromDb(String userLogin) {
        RealmDbHelper.getInstance().getUser(userLogin).subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
    }

    @Override
    public void onNext(UserGitHub userGitHub) {
        if (userGitHub != null) {
            super.onNext(userGitHub);
            getViewState().setProfileDateCreation(userGitHub.getUserCreationDate().substring(0, 10));
            getViewState().setProfileNick(userGitHub.getUserLogin());
            String userName = userGitHub.getUserName();
            if (userName != null)
                getViewState().setProfileFullName(userGitHub.getUserName());
            else
                getViewState().setProfileFullName(userGitHub.getUserLogin());
            String userAvatarUrl = userGitHub.getUserAvatar();
            if (userAvatarUrl != null && !userAvatarUrl.isEmpty())
                getViewState().setImage(userAvatarUrl);
        } else {
            loadProfileFromNet(currentUserLogin);
        }
    }

    @Override
    public void onError(Throwable t) {
        super.onError(t);
    }

    @Override
    public void onComplete() {
        super.onComplete();
        getViewState().hideLoading();
    }
}
