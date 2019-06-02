package io.rong.message;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 3, messageHandler = LocationMessageHandler.class, value = "RC:LBSMsg")
public final class LocationMessage extends MessageContent {
    public static final Creator<LocationMessage> CREATOR = new LocationMessage$1();
    protected String extra;
    String mBase64;
    Uri mImgUri;
    double mLat;
    double mLng;
    String mPoi;

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.mBase64)) {
                jSONObject.put("content", this.mBase64);
            } else if (this.mImgUri != null) {
                jSONObject.put("content", this.mImgUri);
            }
            jSONObject.put("latitude", this.mLat);
            jSONObject.put("longitude", this.mLng);
            if (!TextUtils.isEmpty(getExtra())) {
                jSONObject.put("extra", getExtra());
            }
            if (!TextUtils.isEmpty(this.mPoi)) {
                jSONObject.put(GeocodingCriteria.TYPE_POI, this.mPoi);
            }
            if (getJSONUserInfo() != null) {
                jSONObject.putOpt("user", getJSONUserInfo());
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
        this.mBase64 = null;
        return jSONObject.toString().getBytes();
    }

    public LocationMessage(byte[] bArr) {
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            setLat(jSONObject.getDouble("latitude"));
            setLng(jSONObject.getDouble("longitude"));
            if (jSONObject.has("content")) {
                setBase64(jSONObject.optString("content"));
            }
            if (jSONObject.has("extra")) {
                setExtra(jSONObject.optString("extra"));
            }
            setPoi(jSONObject.optString(GeocodingCriteria.TYPE_POI));
            if (jSONObject.has("user")) {
                setUserInfo(parseJsonToUserInfo(jSONObject.getJSONObject("user")));
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
    }

    public static LocationMessage obtain(double d, double d2, String str, Uri uri) {
        return new LocationMessage(d, d2, str, uri);
    }

    private LocationMessage(double d, double d2, String str, Uri uri) {
        this.mLat = d;
        this.mLng = d2;
        this.mPoi = str;
        this.mImgUri = uri;
    }

    public double getLat() {
        return this.mLat;
    }

    public void setLat(double d) {
        this.mLat = d;
    }

    public double getLng() {
        return this.mLng;
    }

    public void setLng(double d) {
        this.mLng = d;
    }

    public String getPoi() {
        return this.mPoi;
    }

    public void setPoi(String str) {
        this.mPoi = str;
    }

    public String getBase64() {
        return this.mBase64;
    }

    public void setBase64(String str) {
        this.mBase64 = str;
    }

    public Uri getImgUri() {
        return this.mImgUri;
    }

    public void setImgUri(Uri uri) {
        this.mImgUri = uri;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.extra);
        ParcelUtils.writeToParcel(parcel, Double.valueOf(this.mLat));
        ParcelUtils.writeToParcel(parcel, Double.valueOf(this.mLng));
        ParcelUtils.writeToParcel(parcel, this.mPoi);
        ParcelUtils.writeToParcel(parcel, this.mImgUri);
        ParcelUtils.writeToParcel(parcel, getUserInfo());
    }

    public LocationMessage(Parcel parcel) {
        this.extra = ParcelUtils.readFromParcel(parcel);
        this.mLat = ParcelUtils.readDoubleFromParcel(parcel).doubleValue();
        this.mLng = ParcelUtils.readDoubleFromParcel(parcel).doubleValue();
        this.mPoi = ParcelUtils.readFromParcel(parcel);
        this.mImgUri = (Uri) ParcelUtils.readFromParcel(parcel, Uri.class);
        setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
    }
}
