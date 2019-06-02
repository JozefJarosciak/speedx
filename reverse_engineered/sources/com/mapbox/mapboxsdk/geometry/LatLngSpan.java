package com.mapbox.mapboxsdk.geometry;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;

public class LatLngSpan implements Parcelable {
    public static final Creator<LatLngSpan> CREATOR = new C41901();
    private double mLatitudeSpan;
    private double mLongitudeSpan;

    /* renamed from: com.mapbox.mapboxsdk.geometry.LatLngSpan$1 */
    static class C41901 implements Creator<LatLngSpan> {
        C41901() {
        }

        public LatLngSpan createFromParcel(Parcel parcel) {
            return new LatLngSpan(parcel);
        }

        public LatLngSpan[] newArray(int i) {
            return new LatLngSpan[i];
        }
    }

    private LatLngSpan(@NonNull Parcel parcel) {
        this.mLatitudeSpan = parcel.readDouble();
        this.mLongitudeSpan = parcel.readDouble();
    }

    public LatLngSpan(double d, double d2) {
        this.mLatitudeSpan = d;
        this.mLongitudeSpan = d2;
    }

    public double getLatitudeSpan() {
        return this.mLatitudeSpan;
    }

    public void setLatitudeSpan(double d) {
        this.mLatitudeSpan = d;
    }

    public double getLongitudeSpan() {
        return this.mLongitudeSpan;
    }

    public void setLongitudeSpan(double d) {
        this.mLongitudeSpan = d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngSpan)) {
            return false;
        }
        LatLngSpan latLngSpan = (LatLngSpan) obj;
        if (this.mLongitudeSpan == latLngSpan.getLongitudeSpan() && this.mLatitudeSpan == latLngSpan.getLatitudeSpan()) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mLatitudeSpan);
        parcel.writeDouble(this.mLongitudeSpan);
    }
}
