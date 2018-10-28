package com.home_task.saprykin.hometask.model.network;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    NetworkContract getNetworkHelper();
}
