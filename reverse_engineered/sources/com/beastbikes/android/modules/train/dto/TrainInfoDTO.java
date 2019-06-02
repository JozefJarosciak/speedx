package com.beastbikes.android.modules.train.dto;

import com.beastbikes.android.utils.C2555d;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import io.rong.imlib.statistics.UserData;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;
import org.json.JSONObject;

public class TrainInfoDTO implements Serializable {
    public static final int TRAIN_TYPE_LONG = 2;
    public static final int TRAIN_TYPE_SINGLE = 1;
    private double atl;
    private String bgPicture;
    private int courseId;
    private TreeMap<String, TreeMap<String, CourseDTO>> courses = new TreeMap();
    private long created;
    private double ctl;
    private String desc;
    private String enDesc;
    private String enName;
    private boolean isComplete;
    private boolean isFinish;
    private String name;
    private String picture;
    private int trainId;

    public TrainInfoDTO(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.trainId = jSONObject.optInt("training_id");
            this.name = jSONObject.optString("name");
            this.enName = jSONObject.optString("en_name");
            this.desc = jSONObject.optString("description");
            this.enDesc = jSONObject.optString("en_description");
            this.picture = jSONObject.optString(UserData.PICTURE_KEY);
            this.bgPicture = jSONObject.optString("bg_picture");
            this.courseId = jSONObject.optInt("course_id");
            this.atl = jSONObject.optDouble("expectaion_atl");
            this.ctl = jSONObject.optDouble("expectaion_ctl");
            this.isComplete = jSONObject.optBoolean("is_complete");
            this.isFinish = jSONObject.optBoolean("is_finish");
            Date h = C2555d.m12820h(jSONObject.optString(MapboxEvent.ATTRIBUTE_CREATED));
            if (h != null) {
                this.created = h.getTime();
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("courses");
            if (optJSONObject != null) {
                Iterator keys = optJSONObject.keys();
                if (keys != null) {
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject(str);
                        if (optJSONObject2 != null) {
                            Iterator keys2 = optJSONObject2.keys();
                            if (keys2 != null) {
                                TreeMap treeMap = new TreeMap();
                                while (keys2.hasNext()) {
                                    String str2 = (String) keys2.next();
                                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject(str2);
                                    if (optJSONObject3 != null) {
                                        CourseDTO courseDTO = new CourseDTO(optJSONObject3);
                                        courseDTO.setTrainCourseTime(getTime(this.created, Integer.parseInt(str), str2));
                                        treeMap.put(str2, courseDTO);
                                    }
                                }
                                this.courses.put(str, treeMap);
                            }
                        }
                    }
                }
            }
        }
    }

    public int getTrainId() {
        return this.trainId;
    }

    public void setTrainId(int i) {
        this.trainId = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getEnName() {
        return this.enName;
    }

    public void setEnName(String str) {
        this.enName = str;
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

    public long getCreated() {
        return this.created;
    }

    public void setCreated(long j) {
        this.created = j;
    }

    public TreeMap<String, TreeMap<String, CourseDTO>> getCourses() {
        return this.courses;
    }

    public void setCourses(TreeMap<String, TreeMap<String, CourseDTO>> treeMap) {
        this.courses = treeMap;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public String getBgPicture() {
        return this.bgPicture;
    }

    public void setBgPicture(String str) {
        this.bgPicture = str;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseId(int i) {
        this.courseId = i;
    }

    public double getAtl() {
        return this.atl;
    }

    public void setAtl(double d) {
        this.atl = d;
    }

    public double getCtl() {
        return this.ctl;
    }

    public void setCtl(double d) {
        this.ctl = d;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public void setComplete(boolean z) {
        this.isComplete = z;
    }

    public boolean isFinish() {
        return this.isFinish;
    }

    public void setFinish(boolean z) {
        this.isFinish = z;
    }

    private long getTime(long j, int i, String str) {
        int i2 = 7;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i3 = instance.get(7);
        if (i3 - 1 != 0) {
            i2 = i3 - 1;
        }
        return (((((long) ((((i - 1) * 7) - i2) + Integer.parseInt(str))) * 24) * 3600) * 1000) + j;
    }
}
