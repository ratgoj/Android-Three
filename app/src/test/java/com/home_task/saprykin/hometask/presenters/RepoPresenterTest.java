package com.home_task.saprykin.hometask.presenters;

import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.realm.profile.RealmUserProfileWorker;
import com.home_task.saprykin.hometask.model.realm.profile.UsersProfilesData;
import com.home_task.saprykin.hometask.model.realm.repos.RealmReposInfoWorker;
import com.home_task.saprykin.hometask.model.realm.repos.ReposInfoData;
import com.home_task.saprykin.hometask.presenters.base.BasePresenterSingle;
import com.home_task.saprykin.hometask.presenters.interfaces.RepositoryVew;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.PermissionManager;
import io.realm.Realm;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

/**
 * Created by andrejsaprykin on 28/10/2018.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({RepoPresenter.class, BasePresenterSingle.class, ReposInfoData.class, RealmReposInfoWorker.class, RepositoryVew.class, Realm.class, Flowable.class, AndroidSchedulers.class, Schedulers.class, Scheduler.class})
public class RepoPresenterTest {

    @Mock
    RepositoryVew view;

    @Mock
    Realm mockRelm;

    @Mock
    RealmReposInfoWorker mockRealmReposInfoWorker;

    @Mock
    Flowable<RepoModel> mockFlowable;

    @Mock
    Scheduler mockScheduler;

    RepoPresenter testPresenter;

    @BeforeClass
    public static void setupClass(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(Realm.class);
        PowerMockito.when(Realm.getDefaultInstance()).thenReturn(mockRelm);
        PowerMockito.whenNew(RealmReposInfoWorker.class).withNoArguments().thenReturn(mockRealmReposInfoWorker);

        testPresenter = PowerMockito.spy(new RepoPresenter());
    }

    @Test
    public void attachView() throws Exception {
        String fakeSearch = "";
        Whitebox.setInternalState(testPresenter, "searchQuery", fakeSearch);
        testPresenter.attachView(view);

        PowerMockito.verifyPrivate(view, times(1)).invoke("setSearchText", fakeSearch);
    }

    @Test
    public void searchRepo() throws Exception {
        String fakeSearch = "fake";
        List<RepoModel> fakeRepoList = new ArrayList<>();
        fakeRepoList.add(new RepoModel("fakeFirst", "https://fake1"));
        fakeRepoList.add(new RepoModel("second", "https://second_fake2"));
        Whitebox.setInternalState(testPresenter, "repoList", fakeRepoList);

        testPresenter.searchRepo(fakeSearch);
        String testSearch = Whitebox.getInternalState(testPresenter, "searchQuery");

        assertEquals(fakeSearch, testSearch);
    }

    @Test
    public void searchRepoNullData() throws Exception {
        List<RepoModel> fakeRepoList = new ArrayList<>();
        fakeRepoList.add(new RepoModel("fakeFirst", "https://fake1"));
        fakeRepoList.add(new RepoModel("second", "https://second_fake2"));
        Whitebox.setInternalState(testPresenter, "repoList", fakeRepoList);
        testPresenter.attachView(view);

        testPresenter.searchRepo(null);

        PowerMockito.verifyPrivate(view, times(1)).invoke("updateRepoList", fakeRepoList);
    }

    @Test
    public void onSuccess() throws Exception {
        List<RepoModel> fakeRepoList = new ArrayList<>();
        fakeRepoList.add(new RepoModel("fakeFirst", "https://fake1"));
        fakeRepoList.add(new RepoModel("second", "https://second_fake2"));
        testPresenter.attachView(view);

        testPresenter.onSuccess(fakeRepoList);

        PowerMockito.verifyPrivate(view, times(2)).invoke("hideLoading");
        PowerMockito.verifyPrivate(view, times(1)).invoke("updateRepoList", fakeRepoList);
    }

    @Test
    public void loadDbData() throws Exception {
        String fakeUserLogin = "fake";
        List<RepoModel> fakeRepoList = new ArrayList<>();
        fakeRepoList.add(new RepoModel("fakeFirst", "https://fake1"));
        fakeRepoList.add(new RepoModel("second", "https://second_fake2"));
        PowerMockito.doReturn(fakeRepoList).when(mockRealmReposInfoWorker).findUserRepos(fakeUserLogin);

        testPresenter.loadDbData(fakeUserLogin);
        List<RepoModel> testList = Whitebox.getInternalState(testPresenter, "repoList");

        assertEquals(fakeRepoList, testList);
    }

    @Test
    public void loadDbNullData() throws Exception {
        String fakeUserLogin = "fake";
        List<RepoModel> fakeRepoList = null;
        PowerMockito.doReturn(fakeRepoList).when(mockRealmReposInfoWorker).findUserRepos(fakeUserLogin);
        PowerMockito.doNothing().when(testPresenter, "loadData", anyString());

        testPresenter.loadDbData(fakeUserLogin);

        PowerMockito.verifyPrivate(testPresenter, times(1)).invoke("loadData", fakeUserLogin);
    }
}