package com.coolstories.projectstories.domain;

public class User {
    private Integer userID;
    private String email;
    private String password;

    public User(Integer userID, String email, String password){
        this.userID = userID;
        this.email = email;
        this.password = password;
    }

    public Integer getUserID(){
        return userID;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }

    public void setUserID(Integer newUserID){
        userID = newUserID;
    }
    public void setEmail(String newEmail){
        email = newEmail;
    }
    public void setPassword(String newPassword){
        password = newPassword;
    }
}

