package com.home_task.saprykin.hometask.presenters;

import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;
import com.home_task.saprykin.hometask.model.realm.profile.RealmUserProfileWorker;
import com.home_task.saprykin.hometask.model.realm.profile.UsersProfilesData;
import com.home_task.saprykin.hometask.presenters.base.BasePresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.ProfileView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import io.reactivex.Observable;
import io.realm.Realm;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

/**
 * Created by andrejsaprykin on 28/10/2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ProfilePresenter.class, BasePresenter.class,UsersProfilesData.class, RealmUserProfileWorker.class, ProfileView.class, Realm.class})
public class ProfilePresenterTest {

    @Mock
    private ProfileView view;

    @Mock
    Realm mockRelm;

    @Mock
    RealmUserProfileWorker mockRealmUserProfileWorker;

    @Mock
    Observable<UserGitHub> mockObservable;

    private ProfilePresenter testPresenter;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Realm.class);
        PowerMockito.when(Realm.getDefaultInstance()).thenReturn(mockRelm);
        PowerMockito.whenNew(RealmUserProfileWorker.class).withAnyArguments().thenReturn(mockRealmUserProfileWorker);
        testPresenter = PowerMockito.spy(new ProfilePresenter());
    }

    @Test
    public void attachView() throws Exception {
        testPresenter.attachView(view);

        boolean isAttached = testPresenter.getAttachedViews().contains(view);

        assertTrue(isAttached);
    }

    @Test
    public void setProfileInfo() throws Exception {
        PowerMockito.doReturn(mockObservable).when(mockRealmUserProfileWorker).getUser(anyString());

        testPresenter.setProfileInfo();

        PowerMockito.verifyPrivate(testPresenter, times(1)).invoke("loadProfileFromDb", anyString());
    }

    @Test
    public void findProfileInfo() throws Exception {
        String testUserLogin = "fake";
        PowerMockito.doReturn(mockObservable).when(mockRealmUserProfileWorker).getUser(anyString());

        testPresenter.findProfileInfo(testUserLogin);

        PowerMockito.verifyPrivate(testPresenter, times(1)).invoke("loadProfileFromDb", testUserLogin);
    }


    @Test
    public void onNext() throws Exception {
        UserGitHub testProfile = new UserGitHub();
        testProfile.setUserCreationDate("28.10.2018");
        testProfile.setUserAvatar("https://fake_path");
        testProfile.setUserLogin("fake_login");
        testProfile.setUserName("Some Fake Name");
        testPresenter.attachView(view);

        testPresenter.onNext(testProfile);

        PowerMockito.verifyPrivate(view, times(1)).invoke("setProfileDateCreation", anyString());
        PowerMockito.verifyPrivate(view, times(1)).invoke("setProfileNick", testProfile.getUserLogin());
        PowerMockito.verifyPrivate(view, times(1)).invoke("setProfileFullName", testProfile.getUserName());
        PowerMockito.verifyPrivate(view, times(1)).invoke("setImage", testProfile.getUserAvatar());
    }

    @Test
    public void onNextNullData() throws Exception {
        PowerMockito.doNothing().when(mockRealmUserProfileWorker).inputUserData(anyString(), any());

        testPresenter.onNext(null);

        PowerMockito.verifyPrivate(testPresenter, times(1)).invoke("loadProfileFromNet", anyString());
    }


    @Test
    public void onComplete() throws Exception {
        testPresenter.attachView(view);

        testPresenter.onComplete();

        PowerMockito.verifyPrivate(view, times(2)).invoke("hideLoading");
    }

}