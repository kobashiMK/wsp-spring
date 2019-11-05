package com.example.wsp.spring.model;

public class Authn {
    private String userId;
    private String passphrase;
    private String userName;
    private String userRole;

    public Authn(String userId,String passphrase,String userName,String userRole){
        this.userId = userId;
        this.passphrase = passphrase;
        this.userName = userName;
        this.userRole = userRole;
    }
    public Authn(){
        this.userId = null;
        this.passphrase = null;
        this.userName = null;
        this.userRole = null;
    }

    public String getUserId(){
        return userId;
    }
    public String getPassphrase(){
        return passphrase;
    }
    public String getUserName(){
        return userName;
    }
    public String getUserRole(){
        return userRole;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setPassphrase(String passphrase){
        this.passphrase = passphrase;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setUserRole(String userRole){
        this.userRole = userRole;
    }
}
