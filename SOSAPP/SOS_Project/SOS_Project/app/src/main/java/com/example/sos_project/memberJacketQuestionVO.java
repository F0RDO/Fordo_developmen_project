package com.example.sos_project;

public class memberJacketQuestionVO {
    private String user_id;
    private String jacket_count;
    private String question_count;

    public memberJacketQuestionVO() {
    }

    public memberJacketQuestionVO(String user_id, String jacket_count, String question_count) {
        this.user_id = user_id;
        this.jacket_count = jacket_count;
        this.question_count = question_count;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getJacket_count() {
        return jacket_count;
    }

    public void setJacket_count(String jacket_count) {
        this.jacket_count = jacket_count;
    }

    public String getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(String question_count) {
        this.question_count = question_count;
    }
}
