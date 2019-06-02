package com.beastbikes.android.modules.train.dto;

import java.io.Serializable;
import org.json.JSONObject;

public class TrainResultDTO implements Serializable {
    private String bgPicture;
    private String courseDesc;
    private String courseEnDesc;
    private String courseEnName;
    private int courseId;
    private String courseName;
    private int courseType;
    private double trainDayAtl;
    private double trainDayCtl;
    private double trainDayTime;
    private double trainDayTss;
    private int trainDays;
    private int trainId;

    public TrainResultDTO(JSONObject jSONObject) {
        this.trainId = jSONObject.optInt("training_id");
        this.trainDayCtl = jSONObject.optDouble("training_day_ctl");
        this.courseId = jSONObject.optInt("courseId");
        this.trainDays = jSONObject.optInt("training_days");
        this.trainDayTime = jSONObject.optDouble("training_day_time");
        this.courseType = jSONObject.optInt("course_type");
        this.trainDayAtl = jSONObject.optDouble("training_day_atl");
        this.trainDayTss = jSONObject.optDouble("training_day_tss");
        this.courseName = jSONObject.optString("name");
        this.courseEnName = jSONObject.optString("en_name");
        this.courseDesc = jSONObject.optString("description");
        this.courseEnDesc = jSONObject.optString("en_description");
        this.bgPicture = jSONObject.optString("bg_picture");
    }

    public int getTrainId() {
        return this.trainId;
    }

    public void setTrainId(int i) {
        this.trainId = i;
    }

    public double getTrainDayCtl() {
        return this.trainDayCtl;
    }

    public void setTrainDayCtl(double d) {
        this.trainDayCtl = d;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseId(int i) {
        this.courseId = i;
    }

    public int getTrainDays() {
        return this.trainDays;
    }

    public void setTrainDays(int i) {
        this.trainDays = i;
    }

    public double getTrainDayTime() {
        return this.trainDayTime;
    }

    public void setTrainDayTime(double d) {
        this.trainDayTime = d;
    }

    public int getCourseType() {
        return this.courseType;
    }

    public void setCourseType(int i) {
        this.courseType = i;
    }

    public double getTrainDayAtl() {
        return this.trainDayAtl;
    }

    public void setTrainDayAtl(double d) {
        this.trainDayAtl = d;
    }

    public double getTrainDayTss() {
        return this.trainDayTss;
    }

    public void setTrainDayTss(double d) {
        this.trainDayTss = d;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String str) {
        this.courseName = str;
    }

    public String getCourseEnName() {
        return this.courseEnName;
    }

    public void setCourseEnName(String str) {
        this.courseEnName = str;
    }

    public String getCourseDesc() {
        return this.courseDesc;
    }

    public void setCourseDesc(String str) {
        this.courseDesc = str;
    }

    public String getCourseEnDesc() {
        return this.courseEnDesc;
    }

    public void setCourseEnDesc(String str) {
        this.courseEnDesc = str;
    }

    public String getBgPicture() {
        return this.bgPicture;
    }

    public void setBgPicture(String str) {
        this.bgPicture = str;
    }
}
