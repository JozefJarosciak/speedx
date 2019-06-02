package io.rong.imlib.location.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 3, value = "RC:RLStart")
public class RealTimeLocationStartMessage extends MessageContent {
    public static final Creator<RealTimeLocationStartMessage> CREATOR = new RealTimeLocationStartMessage$1();
    private static final String TAG = "RealTimeLocationStartMessage";
    private String content = "";
    private String extra = "";

    public RealTimeLocationStartMessage(String str) {
        this.content = str;
    }

    public RealTimeLocationStartMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("content")) {
                this.content = jSONObject.optString("content");
            }
            if (jSONObject.has("extra")) {
                this.extra = jSONObject.getString("extra");
            }
        } catch (JSONException e2) {
            RLog.e(TAG, "JSONException " + e2.getMessage());
        }
    }

    public static RealTimeLocationStartMessage obtain(String str) {
        return new RealTimeLocationStartMessage(str);
    }

    public String getContent() {
        return this.content;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public RealTimeLocationStartMessage(Parcel parcel) {
        this.content = parcel.readString();
        this.extra = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.content);
        parcel.writeString(this.extra);
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content", this.content);
            if (this.extra != null) {
                jSONObject.put("extra", this.extra);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            return jSONObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
