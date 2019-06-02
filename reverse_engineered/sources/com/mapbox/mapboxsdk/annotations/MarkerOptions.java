package com.mapbox.mapboxsdk.annotations;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mapbox.mapboxsdk.exceptions.InvalidMarkerPositionException;
import com.mapbox.mapboxsdk.geometry.LatLng;

public final class MarkerOptions extends BaseMarkerOptions<Marker, MarkerOptions> implements Parcelable {
    public static final Creator<MarkerOptions> CREATOR = new C41851();

    /* renamed from: com.mapbox.mapboxsdk.annotations.MarkerOptions$1 */
    static class C41851 implements Creator<MarkerOptions> {
        C41851() {
        }

        public MarkerOptions createFromParcel(Parcel parcel) {
            return new MarkerOptions(parcel);
        }

        public MarkerOptions[] newArray(int i) {
            return new MarkerOptions[i];
        }
    }

    protected MarkerOptions(Parcel parcel) {
        position((LatLng) parcel.readParcelable(LatLng.class.getClassLoader()));
        snippet(parcel.readString());
        title(parcel.readString());
        if (parcel.readByte() != (byte) 0) {
            icon(new Icon(parcel.readString(), (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader())));
        }
    }

    public MarkerOptions getThis() {
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(getPosition(), i);
        parcel.writeString(getSnippet());
        parcel.writeString(getTitle());
        Icon icon = getIcon();
        parcel.writeByte((byte) (icon != null ? 1 : 0));
        if (icon != null) {
            parcel.writeString(getIcon().getId());
            parcel.writeParcelable(getIcon().getBitmap(), i);
        }
    }

    public Marker getMarker() {
        if (this.position != null) {
            return new Marker(this.position, this.icon, this.title, this.snippet);
        }
        throw new InvalidMarkerPositionException();
    }

    public LatLng getPosition() {
        return this.position;
    }

    public String getSnippet() {
        return this.snippet;
    }

    public String getTitle() {
        return this.title;
    }

    public Icon getIcon() {
        return this.icon;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r5 == 0) goto L_0x0005;
    L_0x0008:
        r2 = r4.getClass();
        r3 = r5.getClass();
        if (r2 != r3) goto L_0x0005;
    L_0x0012:
        r5 = (com.mapbox.mapboxsdk.annotations.MarkerOptions) r5;
        r2 = r4.getPosition();
        if (r2 == 0) goto L_0x0067;
    L_0x001a:
        r2 = r4.getPosition();
        r3 = r5.getPosition();
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0028:
        r2 = r4.getSnippet();
        if (r2 == 0) goto L_0x006e;
    L_0x002e:
        r2 = r4.getSnippet();
        r3 = r5.getSnippet();
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x003c:
        r2 = r4.getIcon();
        if (r2 == 0) goto L_0x0075;
    L_0x0042:
        r2 = r4.getIcon();
        r3 = r5.getIcon();
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0050:
        r2 = r4.getTitle();
        if (r2 == 0) goto L_0x007c;
    L_0x0056:
        r2 = r4.getTitle();
        r3 = r5.getTitle();
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0065;
    L_0x0064:
        r0 = r1;
    L_0x0065:
        r1 = r0;
        goto L_0x0005;
    L_0x0067:
        r2 = r5.getPosition();
        if (r2 == 0) goto L_0x0028;
    L_0x006d:
        goto L_0x0005;
    L_0x006e:
        r2 = r5.getSnippet();
        if (r2 == 0) goto L_0x003c;
    L_0x0074:
        goto L_0x0005;
    L_0x0075:
        r2 = r5.getIcon();
        if (r2 == 0) goto L_0x0050;
    L_0x007b:
        goto L_0x0005;
    L_0x007c:
        r2 = r5.getTitle();
        if (r2 != 0) goto L_0x0064;
    L_0x0082:
        goto L_0x0065;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mapbox.mapboxsdk.annotations.MarkerOptions.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (getPosition() != null) {
            hashCode = getPosition().hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = (hashCode + 31) * 31;
        if (getSnippet() != null) {
            hashCode = getSnippet().hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (getIcon() != null) {
            hashCode = getIcon().hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (getTitle() != null) {
            i = getTitle().hashCode();
        }
        return hashCode + i;
    }
}
