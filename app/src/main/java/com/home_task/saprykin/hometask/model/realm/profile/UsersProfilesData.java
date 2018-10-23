package com.home_task.saprykin.hometask.model.realm.profile;

import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by andrejsaprykin on 23/10/2018.
 */

public interface UsersProfilesData {
    void inputUserData(String userLogin, Observer<UserGitHub> userGitHubObserver);

    void deleteUser(String userLogin);

    Observable<UserGitHub> getUser(String userLogin);
}
