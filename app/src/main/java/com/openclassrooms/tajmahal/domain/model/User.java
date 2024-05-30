package com.openclassrooms.tajmahal.domain.model;

public class User {
    private String userName;
    private String pictureUrl;

    public User(String userName, String pictureUrl) {
        this.userName = userName;
        this.pictureUrl = pictureUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
