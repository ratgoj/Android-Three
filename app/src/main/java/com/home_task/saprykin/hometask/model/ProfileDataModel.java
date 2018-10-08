package com.home_task.saprykin.hometask.model;

/**
 * Created by andrejsaprykin on 08/10/2018.
 */

public class ProfileDataModel {
    private String userName;
    private String userNick;
    private String userEmail;
    private String dateOfCreationProfile;

    public ProfileDataModel() {
        userName = "Anderey Saprykin";
        userNick = "Helkar";
        userEmail = "my_email@mail.pom";
        dateOfCreationProfile = "17.05.2017";
    }

    public ProfileDataModel(String userName, String userNick, String userEmail, String dateOfCreationProfile) {
        this.userName = userName;
        this.userNick = userNick;
        this.userEmail = userEmail;
        this.dateOfCreationProfile = dateOfCreationProfile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDateOfCreationProfile() {
        return dateOfCreationProfile;
    }

    public void setDateOfCreationProfile(String dateOfCreationProfile) {
        this.dateOfCreationProfile = dateOfCreationProfile;
    }
}
