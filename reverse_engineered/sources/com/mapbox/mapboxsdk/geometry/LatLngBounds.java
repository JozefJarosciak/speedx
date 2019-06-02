package com.mapbox.mapboxsdk.geometry;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.exceptions.InvalidLatLngBoundsException;
import java.util.ArrayList;
import java.util.List;

public class LatLngBounds implements Parcelable {
    public static final Creator<LatLngBounds> CREATOR = new C41891();
    private final double mLatNorth;
    private final double mLatSouth;
    private final double mLonEast;
    private final double mLonWest;

    /* renamed from: com.mapbox.mapboxsdk.geometry.LatLngBounds$1 */
    static class C41891 implements Creator<LatLngBounds> {
        C41891() {
        }

        public LatLngBounds createFromParcel(Parcel parcel) {
            return LatLngBounds.readFromParcel(parcel);
        }

        public LatLngBounds[] newArray(int i) {
            return new LatLngBounds[i];
        }
    }

    public static final class Builder {
        private List<LatLng> mLatLngList = new ArrayList();

        public LatLngBounds build() {
            if (this.mLatLngList.size() >= 2) {
                return LatLngBounds.fromLatLngs(this.mLatLngList);
            }
            throw new InvalidLatLngBoundsException(this.mLatLngList.size());
        }

        public Builder includes(List<LatLng> list) {
            for (LatLng add : list) {
                this.mLatLngList.add(add);
            }
            return this;
        }

        public Builder include(@NonNull LatLng latLng) {
            this.mLatLngList.add(latLng);
            return this;
        }
    }

    LatLngBounds(double d, double d2, double d3, double d4) {
        this.mLatNorth = d;
        this.mLonEast = d2;
        this.mLatSouth = d3;
        this.mLonWest = d4;
    }

    public LatLng getCenter() {
        return new LatLng((this.mLatNorth + this.mLatSouth) / 2.0d, (this.mLonEast + this.mLonWest) / 2.0d);
    }

    public double getLatNorth() {
        return this.mLatNorth;
    }

    public double getLatSouth() {
        return this.mLatSouth;
    }

    public double getLonEast() {
        return this.mLonEast;
    }

    public double getLonWest() {
        return this.mLonWest;
    }

    public LatLngSpan getSpan() {
        return new LatLngSpan(getLatitudeSpan(), getLongitudeSpan());
    }

    public double getLatitudeSpan() {
        return Math.abs(this.mLatNorth - this.mLatSouth);
    }

    public double getLongitudeSpan() {
        return Math.abs(this.mLonEast - this.mLonWest);
    }

    public boolean isEmptySpan() {
        return getLongitudeSpan() == 0.0d || getLatitudeSpan() == 0.0d;
    }

    public String toString() {
        return "N:" + this.mLatNorth + "; E:" + this.mLonEast + "; S:" + this.mLatSouth + "; W:" + this.mLonWest;
    }

    static LatLngBounds fromLatLngs(List<? extends ILatLng> list) {
        double d = 90.0d;
        double d2 = 180.0d;
        double d3 = -90.0d;
        double d4 = -180.0d;
        for (ILatLng iLatLng : list) {
            double latitude = iLatLng.getLatitude();
            double longitude = iLatLng.getLongitude();
            d = Math.min(d, latitude);
            d2 = Math.min(d2, longitude);
            d3 = Math.max(d3, latitude);
            d4 = Math.max(d4, longitude);
        }
        return new LatLngBounds(d3, d4, d, d2);
    }

    public LatLng[] toLatLngs() {
        return new LatLng[]{new LatLng(this.mLatNorth, this.mLonEast), new LatLng(this.mLatSouth, this.mLonWest)};
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        if (this.mLatNorth == latLngBounds.getLatNorth() && this.mLatSouth == latLngBounds.getLatSouth() && this.mLonEast == latLngBounds.getLonEast() && this.mLonWest == latLngBounds.getLonWest()) {
            return true;
        }
        return false;
    }

    public boolean contains(ILatLng iLatLng) {
        double latitude = iLatLng.getLatitude();
        double longitude = iLatLng.getLongitude();
        return latitude < this.mLatNorth && latitude > this.mLatSouth && longitude < this.mLonEast && longitude > this.mLonWest;
    }

    public LatLngBounds union(LatLngBounds latLngBounds) {
        return union(latLngBounds.getLatNorth(), latLngBounds.getLonEast(), latLngBounds.getLatSouth(), latLngBounds.getLonWest());
    }

    public LatLngBounds union(double d, double d2, double d3, double d4) {
        return new LatLngBounds(this.mLatNorth < d ? d : this.mLatNorth, this.mLonEast < d2 ? d2 : this.mLonEast, this.mLatSouth > d3 ? d3 : this.mLatSouth, this.mLonWest > d4 ? d4 : this.mLonWest);
    }

    public LatLngBounds intersect(LatLngBounds latLngBounds) {
        double max = Math.max(getLonWest(), latLngBounds.getLonWest());
        double min = Math.min(getLonEast(), latLngBounds.getLonEast());
        if (min > max) {
            double max2 = Math.max(getLatSouth(), latLngBounds.getLatSouth());
            double min2 = Math.min(getLatNorth(), latLngBounds.getLatNorth());
            if (min2 > max2) {
                return new LatLngBounds(min2, min, max2, max);
            }
        }
        return null;
    }

    public LatLngBounds intersect(double d, double d2, double d3, double d4) {
        return intersect(new LatLngBounds(d, d2, d3, d4));
    }

    public int hashCode() {
        return (int) ((((this.mLatNorth + 90.0d) + ((this.mLatSouth + 90.0d) * 1000.0d)) + ((this.mLonEast + 180.0d) * 1000000.0d)) + ((this.mLonEast + 180.0d) * 1.0E9d));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mLatNorth);
        parcel.writeDouble(this.mLonEast);
        parcel.writeDouble(this.mLatSouth);
        parcel.writeDouble(this.mLonWest);
    }

    private static LatLngBounds readFromParcel(Parcel parcel) {
        return new LatLngBounds(parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble());
    }
}
