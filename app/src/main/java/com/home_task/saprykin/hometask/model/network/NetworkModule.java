package com.home_task.saprykin.hometask.model.network;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
    @Inject
    NetworkService networkService;

    @Inject
    NetworkApiRequest networkApiRequest;

    public NetworkModule() {
    }

    @Provides
    public NetworkApiRequest getNetworkApiRequest() {
        return networkService.createService(NetworkApiRequest.class);
    }

    @Provides
    public NetworkContract getNetworkContract() {
        return new NetworkHelper(networkApiRequest);
    }
}
