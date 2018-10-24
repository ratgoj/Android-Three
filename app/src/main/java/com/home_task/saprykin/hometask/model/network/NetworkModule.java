package com.home_task.saprykin.hometask.model.network;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    NetworkService networkService;

    public NetworkModule() {
        this.networkService = new NetworkService();
    }

    @Provides
    public NetworkService getNetworkService(){
        return networkService;
    }

    @Provides
    public NetworkContract getNetworkContract() {
        return new NetworkHelper(networkService);
    }
}
