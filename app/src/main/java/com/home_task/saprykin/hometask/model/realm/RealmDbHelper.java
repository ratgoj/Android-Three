package com.home_task.saprykin.hometask.model.realm;

import android.util.Log;

import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;
import com.home_task.saprykin.hometask.model.network.NetworkHelper;
import com.home_task.saprykin.hometask.model.realm.models.RealmProfileModel;
import com.home_task.saprykin.hometask.model.realm.models.RealmRepoModel;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.single.SingleObserveOn;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by andrejsaprykin on 21/10/2018.
 */

public class RealmDbHelper {
    private static final String REALM_HELPER_TAG = "Realm Db";
    private static RealmDbHelper instance;

    private RealmDbHelper() {
    }

    public static RealmDbHelper getInstance() {
        if (instance == null)
            instance = new RealmDbHelper();
        return instance;
    }

    public void loadUserData(String userLogin, Observer<UserGitHub> userGitHubObserver) {

        NetworkHelper.getInstance().getUser(userLogin).subscribe(new Observer<UserGitHub>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserGitHub userGitHub) {
                Realm.getDefaultInstance().beginTransaction();
                RealmProfileModel profileModel = Realm.getDefaultInstance().createObject(RealmProfileModel.class);
                profileModel.setUserId(userGitHub.getUserId());
                profileModel.setUserName(userGitHub.getUserName());
                profileModel.setUserLogin(userGitHub.getUserLogin());
                profileModel.setUserAvatarUrl(userGitHub.getUserAvatar());
                profileModel.setUserCreationDate(userGitHub.getUserCreationDate());
                Realm.getDefaultInstance().commitTransaction();
                userGitHubObserver.onNext(userGitHub);
            }

            @Override
            public void onError(Throwable e) {
                if (Realm.getDefaultInstance().isInTransaction())
                    Realm.getDefaultInstance().cancelTransaction();
                userGitHubObserver.onError(e);
            }

            @Override
            public void onComplete() {
                Realm.getDefaultInstance().close();
                userGitHubObserver.onComplete();
            }
        });
    }

    private UserGitHub findUser(String userLogin) {
        UserGitHub userProfile = null;
        RealmResults<RealmProfileModel> userResult = Realm.getDefaultInstance().where(RealmProfileModel.class).equalTo("userLogin", userLogin).findAll();
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

    public void deleteUser(String userLogin) {
        Realm.getDefaultInstance().where(RealmProfileModel.class).equalTo("userLogin", userLogin).findAll().deleteAllFromRealm();
    }

    public Observable<UserGitHub> getUser(String userLogin) {
        return new Observable<UserGitHub>() {
            @Override
            protected void subscribeActual(Observer<? super UserGitHub> observer) {
                observer.onNext(findUser(userLogin));
            }
        };
    }

    public void loadUserRepos(String userLogin, List<RepoModel> repos) {
        if(repos != null && !repos.isEmpty()){
            Realm realm = Realm.getDefaultInstance();
            for (RepoModel model : repos) {
                realm.beginTransaction();
                RealmRepoModel realmRepoModel = realm.createObject(RealmRepoModel.class);
                realmRepoModel.setRepoFullName(model.getRepoName());
                realmRepoModel.setRepoUrlPath(model.getUrlPath());
                realmRepoModel.setRepoUserLogin(userLogin);
                realm.commitTransaction();
            }
            realm.close();
        }
    }

    public List<RepoModel> findUserRepos(String userLogin) {
        List<RepoModel> result = null;
        RealmResults<RealmRepoModel> realmResults = Realm.getDefaultInstance().where(RealmRepoModel.class).equalTo("repoUserLogin", userLogin).findAll();
        if (!realmResults.isEmpty()) {
            List<RealmRepoModel> realmRepoModels = Realm.getDefaultInstance().copyFromRealm(realmResults);
            result = new ArrayList<>();
            for (RealmRepoModel realmModel : realmRepoModels) {
                result.add(new RepoModel(realmModel.getRepoFullName(), realmModel.getRepoUrlPath()));
            }
        }
        return result;
    }
}
