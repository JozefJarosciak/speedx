package com.beastbikes.android.modules.cycling.stage.dto;

import ch.qos.logback.core.CoreConstants;
import java.io.Serializable;

public class StagePointDTO implements Serializable {
    private double latitude;
    private double longitude;

    public StagePointDTO(double d, double d2) {
        this.latitude = d2;
        this.longitude = d;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public String toString() {
        return "StagePointDTO{latitude=" + this.latitude + ", longitude=" + this.longitude + CoreConstants.CURLY_RIGHT;
    }
}
