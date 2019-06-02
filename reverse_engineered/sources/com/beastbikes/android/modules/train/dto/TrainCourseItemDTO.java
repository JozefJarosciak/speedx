package com.beastbikes.android.modules.train.dto;

import com.beastbikes.android.utils.C2554c;
import java.io.Serializable;
import java.util.Calendar;

public class TrainCourseItemDTO implements Serializable {
    private CourseDTO mCourse;
    private String mDate;
    private String mWeekDay;
    private long time;

    public TrainCourseItemDTO(int i, String str, CourseDTO courseDTO, long j) {
        int i2 = 7;
        this.mCourse = courseDTO;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i3 = instance.get(7);
        if (i3 - 1 != 0) {
            i2 = i3 - 1;
        }
        long parseInt = (long) ((((i - 1) * 7) - i2) + Integer.parseInt(str));
        this.mDate = C2554c.m12790a(j, parseInt);
        this.mWeekDay = C2554c.m12792b(j, parseInt);
        this.time = (((parseInt * 24) * 3600) * 1000) + j;
    }

    public String getWeekDay() {
        return this.mWeekDay;
    }

    public void setWeekDay(String str) {
        this.mWeekDay = str;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setDate(String str) {
        this.mDate = str;
    }

    public CourseDTO getCourse() {
        return this.mCourse;
    }

    public void setCourse(CourseDTO courseDTO) {
        this.mCourse = courseDTO;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }
}
