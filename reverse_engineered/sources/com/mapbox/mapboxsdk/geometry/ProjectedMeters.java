package com.mapbox.mapboxsdk.geometry;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ProjectedMeters implements Parcelable, IProjectedMeters {
    public static final Creator<ProjectedMeters> CREATOR = new C41911();
    private double easting;
    private double northing;

    /* renamed from: com.mapbox.mapboxsdk.geometry.ProjectedMeters$1 */
    static class C41911 implements Creator<ProjectedMeters> {
        C41911() {
        }

        public ProjectedMeters createFromParcel(Parcel parcel) {
            return new ProjectedMeters(parcel);
        }

        public ProjectedMeters[] newArray(int i) {
            return new ProjectedMeters[i];
        }
    }

    public ProjectedMeters(double d, double d2) {
        this.northing = d;
        this.easting = d2;
    }

    public ProjectedMeters(ProjectedMeters projectedMeters) {
        this.northing = projectedMeters.northing;
        this.easting = projectedMeters.easting;
    }

    private ProjectedMeters(Parcel parcel) {
        this.northing = parcel.readDouble();
        this.easting = parcel.readDouble();
    }

    public double getNorthing() {
        return this.northing;
    }

    public double getEasting() {
        return this.easting;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProjectedMeters projectedMeters = (ProjectedMeters) obj;
        if (Double.compare(projectedMeters.easting, this.easting) == 0 && Double.compare(projectedMeters.northing, this.northing) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.easting);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.northing);
        return (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public String toString() {
        return "ProjectedMeters [northing=" + this.northing + ", easting=" + this.easting + "]";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.northing);
        parcel.writeDouble(this.easting);
    }
}
