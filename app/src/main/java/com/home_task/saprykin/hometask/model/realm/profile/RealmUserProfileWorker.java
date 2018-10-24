package com.home_task.saprykin.hometask.model.realm.profile;

import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;
import com.home_task.saprykin.hometask.model.network.DaggerNetworkComponent;
import com.home_task.saprykin.hometask.model.network.NetworkComponent;
import com.home_task.saprykin.hometask.model.network.NetworkContract;
import com.home_task.saprykin.hometask.model.network.NetworkHelper;
import com.home_task.saprykin.hometask.model.network.NetworkModule;
import com.home_task.saprykin.hometask.model.realm.models.RealmProfileModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by andrejsaprykin on 23/10/2018.
 */

public class RealmUserProfileWorker implements UsersProfilesData {
    Realm realmInstance;
    NetworkContract networkHelper;

    public RealmUserProfileWorker() {
        this.realmInstance = Realm.getDefaultInstance();
        NetworkComponent networkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();
        networkHelper =  networkComponent.getNetworkHelper();
    }

    @Override
    public void inputUserData(String userLogin, Observer<UserGitHub> userGitHubObserver) {
        Observer<UserGitHub> userInputData = new Observer<UserGitHub>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserGitHub userGitHub) {
                realmInstance.beginTransaction();
                RealmProfileModel profileModel = realmInstance.createObject(RealmProfileModel.class);
                profileModel.setUserId(userGitHub.getUserId());
                profileModel.setUserName(userGitHub.getUserName());
                profileModel.setUserLogin(userGitHub.getUserLogin());
                profileModel.setUserAvatarUrl(userGitHub.getUserAvatar());
                profileModel.setUserCreationDate(userGitHub.getUserCreationDate());
                realmInstance.commitTransaction();
                userGitHubObserver.onNext(userGitHub);
            }

            @Override
            public void onError(Throwable e) {
                if(realmInstance.isInTransaction())
                    realmInstance.cancelTransaction();
                userGitHubObserver.onError(e);
            }

            @Override
            public void onComplete() {
                realmInstance.close();
                userGitHubObserver.onComplete();
            }
        };
        this.networkHelper.getUser(userLogin).subscribe(userInputData);
    }

    @Override
    public void deleteUser(String userLogin) {
        realmInstance.where(RealmProfileModel.class).equalTo("userLogin", userLogin).findAll().deleteAllFromRealm();
    }

    @Override
    public Observable<UserGitHub> getUser(String userLogin) {
        return new Observable<UserGitHub>() {
            @Override
            protected void subscribeActual(Observer<? super UserGitHub> observer) {
                observer.onNext(findUser(userLogin));
            }
        };
    }

    private UserGitHub findUser(String userLogin) {
        UserGitHub userProfile = null;
        RealmResults<RealmProfileModel> userResult = realmInstance.where(RealmProfileModel.class).equalTo("userLogin", userLogin).findAll();
        if (!userResult.isEmpty()) {
            RealmProfileModel profileModel = userResult.last();
            userProfile = new UserGitHub();
            userProfile.setUserLogin(profileModel.getUserLogin());
            userProfile.setUserName(profileModel.getUserName());
            userProfile.setUserId(profileModel.getUserId());
            userProfile.setUserAvatar(profileModel.getUserAvatarUrl());
            userProfile.setUserCreationDate(profileModel.getUserCreationDate());
        }
        return userProfile;
    }
}
