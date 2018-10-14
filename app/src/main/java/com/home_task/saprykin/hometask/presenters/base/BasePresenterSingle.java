package com.home_task.saprykin.hometask.presenters.base;

import com.arellomobile.mvp.MvpPresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.base.BaseView;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by andrejsaprykin on 13/10/2018.
 */

public class BasePresenterSingle<View extends BaseView, T> extends MvpPresenter<View> implements SingleObserver<T> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(Throwable e) {
        getViewState().showError(e.getMessage());
    }
}
