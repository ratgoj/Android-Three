package com.home_task.saprykin.hometask.views.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.presenters.interfaces.base.BaseView;

/**
 * Created by andrejsaprykin on 13/10/2018.
 */

public abstract class BaseFragment extends MvpAppCompatFragment implements BaseView {

    protected View currentFragmentView;
    protected int layout = R.layout.base_fragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentFragmentView = inflater.inflate(layout, container, false);
        initView();
        return currentFragmentView;
    }

    protected abstract void initView();

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorText) {
        Snackbar sb = Snackbar.make(currentFragmentView, errorText, Snackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
        sb.show();
    }
}
