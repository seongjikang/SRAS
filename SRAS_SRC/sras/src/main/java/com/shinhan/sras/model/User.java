package com.shinhan.sras.model;

public class User {
    private String userId;
    private String password;
    private String userName;
    private String department;
    private String authority;


    public User(String userId, String password, String userName, String department, String authority) {
        this.userId = userId;
        this.password = password;
        this.userName =userName;
        this.department = department;
        this.authority = authority;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
