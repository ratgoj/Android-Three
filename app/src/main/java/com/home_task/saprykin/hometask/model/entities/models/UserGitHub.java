package com.home_task.saprykin.hometask.model.entities.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.Nullable;

/**
 * Created by andrejsaprykin on 16/10/2018.
 */

public class UserGitHub {

    @SerializedName("login")
    @Expose
    private String userLogin;

    @SerializedName("name")
    @Expose
    private String userName;

    @SerializedName("avatar_url")
    @Expose
    private String userAvatar;

    @SerializedName("created_at")
    private String userCreationDate;

    @Nullable
    public String getUserLogin() {
        return userLogin;
    }

    @Nullable
    public String getUserName() {
        return userName;
    }

    @Nullable
    public String getUserAvatar() {
        return userAvatar;
    }

    @Nullable
    public String getUserCreationDate() {
        return userCreationDate;
    }
}
