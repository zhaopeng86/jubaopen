package com.minigame.info.entity;

public class UserInfor extends BaseDataEntity{
    private String username;
    private String mobilenumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    @Override
    public String getClassName() {
        return this.getClass().getName();
    }
}
