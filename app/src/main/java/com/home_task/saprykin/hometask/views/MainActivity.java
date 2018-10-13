package com.home_task.saprykin.hometask.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                presenter.setCurrentFragment(item.getItemId());
                return true;
            }
        });
    }

    @Override
    public void setFragment(Fragment currentFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, currentFragment).commit();
    }

    @Override
    public void setDefaultFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, profileFragment).commit();
    }
}
