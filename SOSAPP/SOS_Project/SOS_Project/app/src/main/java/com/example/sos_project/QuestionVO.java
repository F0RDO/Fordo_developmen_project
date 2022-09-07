package com.example.sos_project;

public class QuestionVO {
    private int q_seq;
    private String q_name;
    private String q_email;
    private String q_content;
    private String q_phone;
    private String q_date;
    private String user_id;
    private String q_type;

    public QuestionVO() {
    }

    public QuestionVO(int q_seq, String q_name, String q_email, String q_content, String q_phone, String q_date, String user_id, String q_type) {
        this.q_seq = q_seq;
        this.q_name = q_name;
        this.q_email = q_email;
        this.q_content = q_content;
        this.q_phone = q_phone;
        this.q_date = q_date;
        this.user_id = user_id;
        this.q_type = q_type;
    }

    public int getQ_seq() {
        return q_seq;
    }

    public void setQ_seq(int q_seq) {
        this.q_seq = q_seq;
    }

    public String getQ_name() {
        return q_name;
    }

    public void setQ_name(String q_name) {
        this.q_name = q_name;
    }

    public String getQ_email() {
        return q_email;
    }

    public void setQ_email(String q_email) {
        this.q_email = q_email;
    }

    public String getQ_content() {
        return q_content;
    }

    public void setQ_content(String q_content) {
        this.q_content = q_content;
    }

    public String getQ_phone() {
        return q_phone;
    }

    public void setQ_phone(String q_phone) {
        this.q_phone = q_phone;
    }

    public String getQ_date() {
        return q_date;
    }

    public void setQ_date(String q_date) {
        this.q_date = q_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQ_type() {
        return q_type;
    }

    public void setQ_type(String q_type) {
        this.q_type = q_type;
    }
}
