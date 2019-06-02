package com.mapbox.mapboxsdk.annotations;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.mapbox.mapboxsdk.exceptions.InvalidMarkerPositionException;
import com.mapbox.mapboxsdk.geometry.LatLng;

public class MarkerViewOptions extends BaseMarkerViewOptions<MarkerView, MarkerViewOptions> {
    public static final Creator<MarkerViewOptions> CREATOR = new C41861();
    private MarkerView marker = new MarkerView();

    /* renamed from: com.mapbox.mapboxsdk.annotations.MarkerViewOptions$1 */
    static class C41861 implements Creator<MarkerViewOptions> {
        C41861() {
        }

        public MarkerViewOptions createFromParcel(Parcel parcel) {
            return new MarkerViewOptions(parcel);
        }

        public MarkerViewOptions[] newArray(int i) {
            return new MarkerViewOptions[i];
        }
    }

    protected MarkerViewOptions(Parcel parcel) {
        boolean z = true;
        position((LatLng) parcel.readParcelable(LatLng.class.getClassLoader()));
        snippet(parcel.readString());
        title(parcel.readString());
        flat(parcel.readByte() != (byte) 0);
        anchor(parcel.readFloat(), parcel.readFloat());
        infoWindowAnchor(parcel.readFloat(), parcel.readFloat());
        rotation(parcel.readFloat());
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        visible(z);
        alpha(parcel.readFloat());
        if (parcel.readByte() != (byte) 0) {
            icon(new Icon(parcel.readString(), (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader())));
        }
    }

    public MarkerViewOptions getThis() {
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeParcelable(getPosition(), i);
        parcel.writeString(getSnippet());
        parcel.writeString(getTitle());
        parcel.writeByte((byte) (isFlat() ? 1 : 0));
        parcel.writeFloat(getAnchorU());
        parcel.writeFloat(getAnchorV());
        parcel.writeFloat(getInfoWindowAnchorU());
        parcel.writeFloat(getInfoWindowAnchorV());
        parcel.writeFloat(getRotation());
        if (isVisible()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeFloat(this.alpha);
        Icon icon = getIcon();
        if (icon == null) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        if (icon != null) {
            parcel.writeString(getIcon().getId());
            parcel.writeParcelable(getIcon().getBitmap(), i);
        }
    }

    public MarkerView getMarker() {
        if (this.position == null) {
            throw new InvalidMarkerPositionException();
        }
        this.marker.setPosition(this.position);
        this.marker.setSnippet(this.snippet);
        this.marker.setTitle(this.title);
        this.marker.setIcon(this.icon);
        this.marker.setFlat(this.flat);
        this.marker.setAnchor(this.anchorU, this.anchorV);
        this.marker.setInfoWindowAnchor(this.infoWindowAnchorU, this.infoWindowAnchorV);
        this.marker.setRotation(this.rotation);
        this.marker.setVisible(this.visible);
        this.marker.setAlpha(this.alpha);
        return this.marker;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MarkerViewOptions markerViewOptions = (MarkerViewOptions) obj;
        if (this.marker != null) {
            return this.marker.equals(markerViewOptions.marker);
        }
        if (markerViewOptions.marker != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.marker != null ? this.marker.hashCode() : 0;
    }
}
