package com.home_task.saprykin.hometask.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.home_task.saprykin.hometask.BuildConfig;
import com.home_task.saprykin.hometask.model.entities.models.RepoModel;
import com.home_task.saprykin.hometask.model.entities.models.UserGitHub;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andrejsaprykin on 24/10/2018.
 */

@Module
public class ModuleNetwork {


    public ModuleNetwork() {

    }

    @Provides
    public Gson gson() {
        return new GsonBuilder().setDateFormat("dd-mm-yyyy").create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    public OkHttpClient okHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    Retrofit getRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient).build();
    }

    @Provides
    Observable<UserGitHub> getUser(Retrofit retrofit) {
        return retrofit.create(NetworkApiRequest.class).getUser("fern");
    }

    @Provides
    Flowable<List<RepoModel>> getRepos(Retrofit retrofit) {
        return retrofit.create(NetworkApiRequest.class).getRepos("fern");
    }
}
