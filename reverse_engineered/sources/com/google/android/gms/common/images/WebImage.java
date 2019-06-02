package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.mapbox.mapboxsdk.style.layers.Property;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class WebImage extends AbstractSafeParcelable {
    public static final Creator<WebImage> CREATOR = new zzb();
    private final int mVersionCode;
    private final Uri wD;
    private final int zzaie;
    private final int zzaif;

    WebImage(int i, Uri uri, int i2, int i3) {
        this.mVersionCode = i;
        this.wD = uri;
        this.zzaie = i2;
        this.zzaif = i3;
    }

    public WebImage(Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }

    public WebImage(Uri uri, int i, int i2) throws IllegalArgumentException {
        this(1, uri, i, i2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(JSONObject jSONObject) throws IllegalArgumentException {
        this(zzn(jSONObject), jSONObject.optInt(Property.ICON_TEXT_FIT_WIDTH, 0), jSONObject.optInt(Property.ICON_TEXT_FIT_HEIGHT, 0));
    }

    private static Uri zzn(JSONObject jSONObject) {
        Uri uri = null;
        if (jSONObject.has("url")) {
            try {
                uri = Uri.parse(jSONObject.getString("url"));
            } catch (JSONException e) {
            }
        }
        return uri;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) obj;
        return zzaa.equal(this.wD, webImage.wD) && this.zzaie == webImage.zzaie && this.zzaif == webImage.zzaif;
    }

    public int getHeight() {
        return this.zzaif;
    }

    public Uri getUrl() {
        return this.wD;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int getWidth() {
        return this.zzaie;
    }

    public int hashCode() {
        return zzaa.hashCode(this.wD, Integer.valueOf(this.zzaie), Integer.valueOf(this.zzaif));
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.wD.toString());
            jSONObject.put(Property.ICON_TEXT_FIT_WIDTH, this.zzaie);
            jSONObject.put(Property.ICON_TEXT_FIT_HEIGHT, this.zzaif);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public String toString() {
        return String.format(Locale.US, "Image %dx%d %s", new Object[]{Integer.valueOf(this.zzaie), Integer.valueOf(this.zzaif), this.wD.toString()});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
