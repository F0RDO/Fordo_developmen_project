package com.example.sos_project;

public class JacketLocationVO {
    private int jl_seq;
    private double jl_latitude;
    private double jl_longitude;
    private String jl_date;
    private int jacket_seq;

    public JacketLocationVO() {
    }

    public JacketLocationVO(int jl_seq, double jl_latitude, double jl_longitude, String jl_date, int jacket_seq) {
        this.jl_seq = jl_seq;
        this.jl_latitude = jl_latitude;
        this.jl_longitude = jl_longitude;
        this.jl_date = jl_date;
        this.jacket_seq = jacket_seq;
    }

    public int getJl_seq() {
        return jl_seq;
    }

    public void setJl_seq(int jl_seq) {
        this.jl_seq = jl_seq;
    }

    public double getJl_latitude() {
        return jl_latitude;
    }

    public void setJl_latitude(double jl_latitude) {
        this.jl_latitude = jl_latitude;
    }

    public double getJl_longitude() {
        return jl_longitude;
    }

    public void setJl_longitude(double jl_longitude) {
        this.jl_longitude = jl_longitude;
    }

    public String getJl_date() {
        return jl_date;
    }

    public void setJl_date(String jl_date) {
        this.jl_date = jl_date;
    }

    public int getJacket_seq() {
        return jacket_seq;
    }

    public void setJacket_seq(int jacket_seq) {
        this.jacket_seq = jacket_seq;
    }
}
