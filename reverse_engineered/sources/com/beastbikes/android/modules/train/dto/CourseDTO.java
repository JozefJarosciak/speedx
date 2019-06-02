package com.beastbikes.android.modules.train.dto;

import io.rong.imlib.statistics.UserData;
import java.io.Serializable;
import org.json.JSONObject;

public class CourseDTO implements Serializable {
    private String bgPicture;
    private int categoryId;
    private long courseTime;
    private int cycle;
    private String desc;
    private String enDesc;
    private String enIntroduction;
    private String enName;
    private int id;
    private String introduction;
    private String name;
    private String picture;
    private String sportRouteId;
    private long trainCourseTime;
    private int tss;
    private double wattsIf;

    public CourseDTO(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.id = jSONObject.optInt("id");
            if (jSONObject.has("course_id")) {
                this.id = jSONObject.optInt("course_id");
            }
            this.enName = jSONObject.optString("en_name");
            this.name = jSONObject.optString("name");
            this.picture = jSONObject.optString(UserData.PICTURE_KEY);
            this.bgPicture = jSONObject.optString("bg_picture");
            this.desc = jSONObject.optString("description");
            this.enDesc = jSONObject.optString("en_description");
            this.courseTime = jSONObject.optLong("course_time");
            this.tss = jSONObject.optInt("tss");
            this.wattsIf = jSONObject.optDouble("watts_if");
            this.categoryId = jSONObject.optInt("category");
            this.sportRouteId = jSONObject.optString("sport_route_id");
            this.cycle = jSONObject.optInt("cycle");
            this.introduction = jSONObject.optString("introduction");
            this.enIntroduction = jSONObject.optString("en_introduction");
        }
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getEnName() {
        return this.enName;
    }

    public void setEnName(String str) {
        this.enName = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getBgPicture() {
        return this.bgPicture;
    }

    public void setBgPicture(String str) {
        this.bgPicture = str;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getEnDesc() {
        return this.enDesc;
    }

    public void setEnDesc(String str) {
        this.enDesc = str;
    }

    public long getCourseTime() {
        return this.courseTime;
    }

    public void setCourseTime(long j) {
        this.courseTime = j;
    }

    public int getTss() {
        return this.tss;
    }

    public void setTss(int i) {
        this.tss = i;
    }

    public double getWattsIf() {
        return this.wattsIf;
    }

    public void setWattsIf(double d) {
        this.wattsIf = d;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int i) {
        this.categoryId = i;
    }

    public String getSportRouteId() {
        return this.sportRouteId;
    }

    public void setSportRouteId(String str) {
        this.sportRouteId = str;
    }

    public int getCycle() {
        return this.cycle;
    }

    public void setCycle(int i) {
        this.cycle = i;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String str) {
        this.introduction = str;
    }

    public String getEnIntroduction() {
        return this.enIntroduction;
    }

    public void setEnIntroduction(String str) {
        this.enIntroduction = str;
    }

    public long getTrainCourseTime() {
        return this.trainCourseTime;
    }

    public void setTrainCourseTime(long j) {
        this.trainCourseTime = j;
    }
}
