package com.example.erp.utils;

public class dtutil {
    String email;
    String password;
    String username;
    String IP;
    String sudo_password;
    int usertype;

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getSudo_password() {
        return sudo_password;
    }

    public void setSudo_password(String sudo_password) {
        this.sudo_password = sudo_password;
    }
}
