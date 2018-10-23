package com.home_task.saprykin.hometask;

import android.app.Application;
import android.content.SharedPreferences;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppContext extends Application {
    private final static String USER_LOGIN_KEY = "com.home_task.saprykin.hometask.user_login_key";

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration =  new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
    }

    public void saveUserLogin(String preferenceName, String userLogin){
        SharedPreferences sp = getSharedPreferences(preferenceName, MODE_PRIVATE);
        sp.edit().putString(USER_LOGIN_KEY, userLogin).commit();
    }

    public String getSavedUserLogin(String preferenceName){
        SharedPreferences sp = getSharedPreferences(preferenceName, MODE_PRIVATE);
        return sp.getString(USER_LOGIN_KEY, null);
    }
}
