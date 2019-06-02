package com.beastbikes.android.ble.dto;

import android.text.TextUtils;
import java.io.Serializable;

public class NavigationLocation implements Serializable {
    private String address;
    private double latitude;
    private double longitude;
    private String name;
    private String placeId;
    private String reference;
    private boolean success;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
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

    public String getPlaceId() {
        return this.placeId;
    }

    public void setPlaceId(String str) {
        this.placeId = str;
    }

    public String getReference() {
        return this.reference;
    }

    public void setReference(String str) {
        this.reference = str;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        NavigationLocation navigationLocation = (NavigationLocation) obj;
        if (TextUtils.equals(navigationLocation.getName(), this.name) && TextUtils.equals(navigationLocation.getAddress(), this.address)) {
            return true;
        }
        return super.equals(obj);
    }
}
