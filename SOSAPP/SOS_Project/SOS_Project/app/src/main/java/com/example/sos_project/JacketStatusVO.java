package com.example.sos_project;

import java.io.Serializable;

public class JacketStatusVO implements Serializable {
    private String product_id;
    private String batteryStatus;
    private String connectStatus;
    private double waterPressure;
    private double waterTemperature;
    private double waterDetect;

    public JacketStatusVO() {
    }

    public JacketStatusVO(String batteryStatus, String connectStatus, double waterPressure, double waterTemperature, double waterDetect) {
        this.batteryStatus = batteryStatus;
        this.connectStatus = connectStatus;
        this.waterPressure = waterPressure;
        this.waterTemperature = waterTemperature;
        this.waterDetect = waterDetect;
    }

    public JacketStatusVO(String product_id, String batteryStatus, String connectStatus, double waterPressure, double waterTemperature, double waterDetect) {
        this.product_id = product_id;
        this.batteryStatus = batteryStatus;
        this.connectStatus = connectStatus;
        this.waterPressure = waterPressure;
        this.waterTemperature = waterTemperature;
        this.waterDetect = waterDetect;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public String getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(String connectStatus) {
        this.connectStatus = connectStatus;
    }

    public double getWaterPressure() {
        return waterPressure;
    }

    public void setWaterPressure(double waterPressure) {
        this.waterPressure = waterPressure;
    }

    public double getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public double getWaterDetect() {
        return waterDetect;
    }

    public void setWaterDetect(double waterDetect) {
        this.waterDetect = waterDetect;
    }
}
