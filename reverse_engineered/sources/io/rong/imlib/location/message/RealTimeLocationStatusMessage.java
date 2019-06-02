package io.rong.imlib.location.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 0, value = "RC:RL")
public class RealTimeLocationStatusMessage extends MessageContent {
    public static final Creator<RealTimeLocationStatusMessage> CREATOR = new RealTimeLocationStatusMessage$1();
    private double latitude = 0.0d;
    private double longitude = 0.0d;

    public RealTimeLocationStatusMessage(byte[] bArr) {
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            if (jSONObject.has("latitude")) {
                this.latitude = jSONObject.optDouble("latitude");
            }
            if (jSONObject.has("longitude")) {
                this.longitude = jSONObject.optDouble("longitude");
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
    }

    public static RealTimeLocationStatusMessage obtain(double d, double d2) {
        RealTimeLocationStatusMessage realTimeLocationStatusMessage = new RealTimeLocationStatusMessage();
        realTimeLocationStatusMessage.latitude = d;
        realTimeLocationStatusMessage.longitude = d2;
        return realTimeLocationStatusMessage;
    }

    public RealTimeLocationStatusMessage(Parcel parcel) {
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("latitude", this.latitude);
            jSONObject.put("longitude", this.longitude);
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
        return jSONObject.toString().getBytes();
    }
}
