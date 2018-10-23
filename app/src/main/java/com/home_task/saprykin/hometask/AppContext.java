package com.home_task.saprykin.hometask;

import android.app.Application;
import android.content.SharedPreferences;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppContext extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration =  new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
    }
}
