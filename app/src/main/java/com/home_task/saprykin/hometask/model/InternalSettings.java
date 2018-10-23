package com.home_task.saprykin.hometask.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by andrejsaprykin on 23/10/2018.
 */

public class InternalSettings {
    private final static String USER_LOGIN_KEY = "com.home_task.saprykin.hometask.user_login_key";
    private static InternalSettings ourInstance;
    private Activity activity;


    private InternalSettings(Activity activity) {
        this.activity = activity;
    }

    public static InternalSettings getInstance(Activity activity) {
        if (ourInstance == null){
            ourInstance = new InternalSettings(activity);
        }
        return ourInstance;
    }

    public void saveUserLogin( String userLogin){
        SharedPreferences sp = activity.getPreferences(Context.MODE_PRIVATE);
        sp.edit().putString(USER_LOGIN_KEY, userLogin).commit();
    }

    public String getSavedUserLogin(){
        SharedPreferences sp = activity.getPreferences(Context.MODE_PRIVATE);
        return sp.getString(USER_LOGIN_KEY, null);
    }

}
