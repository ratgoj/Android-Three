package com.home_task.saprykin.hometask.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.home_task.saprykin.hometask.BuildConfig;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andrejsaprykin on 17/10/2018.
 */

public class NetworkService {
    private Gson gson = new GsonBuilder().setDateFormat("dd-mm-yyyy").create();

    @Inject
    public NetworkService() {
    }

    public <S> S createService(Class<S> serviceClass) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient()).build().create(serviceClass);
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
}
