package com.home_task.saprykin.hometask.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.presenters.MainPresenter;
import com.home_task.saprykin.hometask.views.fragments.ProfileFragment;
import com.home_task.saprykin.hometask.views.fragments.RepositoriesFragment;
import com.home_task.saprykin.hometask.presenters.interfaces.ContainerView;

public class MainActivity extends MvpAppCompatActivity implements ContainerView {
    private static final String SP_SCREEN_ID_KEY = "com.home_task.saprykin.hometask.screen_id_key";
    private static final int DEFAULT_START_SCREEN = -1;

    BottomNavigationView bottomNavigationView;
    ProfileFragment profileFragment;
    RepositoriesFragment repositoriesFragment;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        if (presenter.getProfileFragment() == null && presenter.getRepositoriesFragment() == null) {
            profileFragment = new ProfileFragment();
            repositoriesFragment = new RepositoriesFragment();

            presenter.setProfileFragment(profileFragment);
            presenter.setRepositoriesFragment(repositoriesFragment);
        } else {
            profileFragment = presenter.getProfileFragment();
            repositoriesFragment = presenter.getRepositoriesFragment();
        }

        presenter.setDefaultScreen();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            saveCurrentScreen(item.getItemId());
            presenter.setCurrentFragment(item.getItemId());
            return true;
        });
    }

    @Override
    public void setFragment(Fragment currentFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, currentFragment).commit();
    }

    @Override
    public void setDefaultFragment() {
        //int savedScreen = getPreferences(MODE_PRIVATE).getInt(SP_SCREEN_ID_KEY, -1);
        presenter.setCurrentFragment(DEFAULT_START_SCREEN);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorText) {
        Snackbar sb = Snackbar.make(bottomNavigationView, errorText, Snackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        sb.show();
    }

    private void saveCurrentScreen(int screenId) {
        getPreferences(MODE_PRIVATE).edit().putInt(SP_SCREEN_ID_KEY, screenId).apply();
    }
}
