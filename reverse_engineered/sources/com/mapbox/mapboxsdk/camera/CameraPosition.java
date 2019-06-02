package com.mapbox.mapboxsdk.camera;

import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mapbox.mapboxsdk.R$styleable;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.utils.MathUtils;

public final class CameraPosition implements Parcelable {
    public static final Creator<CameraPosition> CREATOR = new CameraPosition$1();
    public final double bearing;
    public final LatLng target;
    public final double tilt;
    public final double zoom;

    public static final class Builder {
        private double bearing = -1.0d;
        private LatLng target = null;
        private double tilt = -1.0d;
        private double zoom = -1.0d;

        public Builder(CameraPosition cameraPosition) {
            if (cameraPosition != null) {
                this.bearing = cameraPosition.bearing;
                this.target = cameraPosition.target;
                this.tilt = cameraPosition.tilt;
                this.zoom = cameraPosition.zoom;
            }
        }

        public Builder(TypedArray typedArray) {
            if (typedArray != null) {
                this.bearing = (double) typedArray.getFloat(R$styleable.MapView_direction, 0.0f);
                this.target = new LatLng((double) typedArray.getFloat(R$styleable.MapView_center_latitude, 0.0f), (double) typedArray.getFloat(R$styleable.MapView_center_longitude, 0.0f));
                this.tilt = (double) typedArray.getFloat(R$styleable.MapView_tilt, 0.0f);
                this.zoom = (double) typedArray.getFloat(R$styleable.MapView_zoom, 0.0f);
            }
        }

        public Builder(CameraUpdateFactory$CameraPositionUpdate cameraUpdateFactory$CameraPositionUpdate) {
            if (cameraUpdateFactory$CameraPositionUpdate != null) {
                this.bearing = cameraUpdateFactory$CameraPositionUpdate.getBearing();
                this.target = cameraUpdateFactory$CameraPositionUpdate.getTarget();
                this.tilt = cameraUpdateFactory$CameraPositionUpdate.getTilt();
                this.zoom = cameraUpdateFactory$CameraPositionUpdate.getZoom();
            }
        }

        public Builder(CameraUpdateFactory$ZoomUpdate cameraUpdateFactory$ZoomUpdate) {
            if (cameraUpdateFactory$ZoomUpdate != null) {
                this.zoom = cameraUpdateFactory$ZoomUpdate.getZoom();
            }
        }

        public Builder(double[] dArr) {
            if (dArr != null && dArr.length == 5) {
                target(new LatLng(dArr[0], dArr[1]));
                bearing(dArr[2]);
                tilt(dArr[3]);
                zoom((double) ((float) dArr[4]));
            }
        }

        public Builder bearing(double d) {
            double d2 = d;
            while (d2 >= 360.0d) {
                d2 -= 360.0d;
            }
            while (d2 < 0.0d) {
                d2 += 360.0d;
            }
            this.bearing = d2;
            return this;
        }

        public CameraPosition build() {
            return new CameraPosition(this.target, this.zoom, this.tilt, this.bearing);
        }

        public Builder target(LatLng latLng) {
            this.target = latLng;
            return this;
        }

        public Builder tilt(double d) {
            this.tilt = (double) ((float) MathUtils.clamp(d, 0.0d, 60.0d));
            return this;
        }

        public Builder zoom(double d) {
            this.zoom = d;
            return this;
        }
    }

    CameraPosition(LatLng latLng, double d, double d2, double d3) {
        this.target = latLng;
        this.bearing = d3;
        this.tilt = d2;
        this.zoom = d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.bearing);
        parcel.writeParcelable(this.target, i);
        parcel.writeDouble(this.tilt);
        parcel.writeDouble(this.zoom);
    }

    public String toString() {
        return "Target: " + this.target + ", Zoom:" + this.zoom + ", Bearing:" + this.bearing + ", Tilt:" + this.tilt;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        if (this.target != null && !this.target.equals(cameraPosition.target)) {
            return false;
        }
        if (this.zoom != cameraPosition.zoom) {
            return false;
        }
        if (this.tilt != cameraPosition.tilt) {
            return false;
        }
        if (this.bearing != cameraPosition.bearing) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.target != null ? this.target.hashCode() : 0) + 31;
    }
}
