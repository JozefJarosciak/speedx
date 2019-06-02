package com.beastbikes.android.modules.train.dto;

import java.io.Serializable;
import org.json.JSONObject;

public class CalendarDto implements Serializable {
    private double distance;
    private boolean hasTrainingCourse;
    private boolean isTraining;

    public CalendarDto(JSONObject jSONObject) {
        this.isTraining = jSONObject.optBoolean("is_training");
        this.distance = jSONObject.optDouble("distance");
        this.hasTrainingCourse = jSONObject.optBoolean("has_training_course");
    }

    public boolean isTraining() {
        return this.isTraining;
    }

    public void setTraining(boolean z) {
        this.isTraining = z;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public boolean isHasTrainingCourse() {
        return this.hasTrainingCourse;
    }

    public void setHasTrainingCourse(boolean z) {
        this.hasTrainingCourse = z;
    }
}
