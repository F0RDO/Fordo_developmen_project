package com.example.sos_project;

import java.io.Serializable;

public class tbl_user implements Serializable {
    private String user_id;
    private String user_pw;
    private String user_nick;
    private String user_type;
    private String user_joindate;

    public tbl_user() {
    }

    public tbl_user(String user_id, String user_pw, String user_nick, String user_type, String user_joindate) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_nick = user_nick;
        this.user_type = user_type;
        this.user_joindate = user_joindate;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_joindate() {
        return user_joindate;
    }

    public void setUser_joindate(String user_joindate) {
        this.user_joindate = user_joindate;
    }
}
