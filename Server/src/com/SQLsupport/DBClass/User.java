package com.SQLsupport.DBClass;

import java.io.Serializable;

public class User implements Serializable {
    private int id_user;
    private String login;
    private String password;
    private int role;

    public User(int id_user, String login, String password, int role) {
        this.id_user = id_user;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(User user) {
        this.login = user.login;
        this.password = user.password;
        this.role = user.role;
        this.id_user = user.id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }
}
