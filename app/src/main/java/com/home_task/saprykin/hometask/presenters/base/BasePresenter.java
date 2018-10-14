package com.home_task.saprykin.hometask.presenters.base;

import com.arellomobile.mvp.MvpPresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.base.BaseView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by andrejsaprykin on 14/10/2018.
 */

public class BasePresenter <View extends BaseView, T> extends MvpPresenter<View> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {
        getViewState().showError(t.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
