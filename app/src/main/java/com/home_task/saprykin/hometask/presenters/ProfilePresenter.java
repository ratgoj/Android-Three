package com.home_task.saprykin.hometask.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.model.ProfileDataModel;
import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;
import com.home_task.saprykin.hometask.model.network.NetworkHelper;
import com.home_task.saprykin.hometask.presenters.base.BasePresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.ProfileView;

import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileView, UserGitHub> {
    ProfileDataModel profileDataModel;

    public ProfilePresenter(long maxEmittedItems) {
        super(maxEmittedItems);
    }

    public void setProfileInfo() {
        loadProfileFromNet();
    }

    private void loadProfileFromNet() {
        getViewState().showLoading();
        NetworkHelper.getInstance().getUser("ratgoj").subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
    }

    @Override
    public void onNext(UserGitHub userGitHub) {
        super.onNext(userGitHub);
        getViewState().setText(R.id.profile_date_text, userGitHub.getUserCreationDate().substring(0,10));
        getViewState().setText(R.id.profile_nick_name_text, "@" + userGitHub.getUserLogin());
        String userName = userGitHub.getUserName();
        if (userName != null)
            getViewState().setText(R.id.profile_full_name_text, userGitHub.getUserName());
        else
            getViewState().setText(R.id.profile_full_name_text, userGitHub.getUserLogin());
        String userAvatarUrl = userGitHub.getUserAvatar();
        if (userAvatarUrl != null && !userAvatarUrl.isEmpty())
            getViewState().setImage(userAvatarUrl);
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
