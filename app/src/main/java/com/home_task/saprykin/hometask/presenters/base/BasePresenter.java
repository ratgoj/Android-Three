package com.home_task.saprykin.hometask.presenters.base;

import com.arellomobile.mvp.MvpPresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.base.BaseView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by andrejsaprykin on 14/10/2018.
 */

public class BasePresenter<View extends BaseView, T> extends MvpPresenter<View> implements Observer<T> {

    public BasePresenter() {
        super();
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {
        getViewState().hideLoading();
        getViewState().showError(t.getMessage());
    }

    @Override
    public void onComplete() {
        getViewState().hideLoading();
    }
}
