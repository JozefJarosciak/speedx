package com.beastbikes.android.modules.train.dto;

import java.io.Serializable;
import java.util.List;

public class TrainWeekDTO implements Serializable {
    private List<TrainCourseItemDTO> courses;
    private String date;
    private boolean thisWeek;
    private String week;
    private int weekIndex;

    public String getWeek() {
        return this.week;
    }

    public void setWeek(String str) {
        this.week = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public List<TrainCourseItemDTO> getCourses() {
        return this.courses;
    }

    public void setCourses(List<TrainCourseItemDTO> list) {
        this.courses = list;
    }

    public int getWeekIndex() {
        return this.weekIndex;
    }

    public void setWeekIndex(int i) {
        this.weekIndex = i;
    }

    public boolean isThisWeek() {
        return this.thisWeek;
    }

    public void setThisWeek(boolean z) {
        this.thisWeek = z;
    }
}
