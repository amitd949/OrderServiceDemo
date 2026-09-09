package com.example.demo.Auth;

public class User {
    private String userName;
    private String password;
    private String roleType;

    public User(String userName, String password, String roleType) {
        this.userName = userName;
        this.password = password;
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }


    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
