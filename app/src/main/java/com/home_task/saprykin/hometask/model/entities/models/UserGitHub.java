package com.home_task.saprykin.hometask.model.entities.models;

import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

/**
 * Created by andrejsaprykin on 16/10/2018.
 */

public class UserGitHub {
    private String userLogin;
    @SerializedName("avatar_url")
    private String userAvatar;

    @Nullable
    public String getUserLogin() {
        return userLogin;
    }

    @Nullable
    public String getUserAvatar() {
        return userAvatar;
    }
}
