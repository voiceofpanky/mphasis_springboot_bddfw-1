package com.mphasis.qe.pojo;

public class User {
    private String userName;
    private String userJob;

    public User(String userName, String userJob){
        this.userName = userName;
        this.userJob = userJob;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getUserJob(){
        return this.userJob;
    }

}
