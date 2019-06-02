package com.beastbikes.android.modules.train.dto;

import java.io.Serializable;

public class TrainTargetDto implements Serializable {
    private long birthDayDate;
    private String birthday;
    private int ftp;
    private int heartRate;
    private double height;
    private int sex;
    private double weight;

    public int getFtp() {
        return this.ftp;
    }

    public void setFtp(int i) {
        this.ftp = i;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double d) {
        this.height = d;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double d) {
        this.weight = d;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int i) {
        this.sex = i;
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }

    public long getBirthDayDate() {
        return this.birthDayDate;
    }

    public void setBirthDayDate(long j) {
        this.birthDayDate = j;
    }
}
