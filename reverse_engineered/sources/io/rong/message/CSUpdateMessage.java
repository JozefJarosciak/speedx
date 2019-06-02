package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 0, value = "RC:CsUpdate")
public class CSUpdateMessage extends MessageContent {
    public static final Creator<CSUpdateMessage> CREATOR = new CSUpdateMessage$1();
    private static final String TAG = "CSUpdateMessage";
    private String serviceStatus;
    private String sid;

    public String getSid() {
        return this.sid;
    }

    public String getServiceStatus() {
        return this.serviceStatus;
    }

    public CSUpdateMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.sid = jSONObject.optString("sid");
            this.serviceStatus = jSONObject.optString("serviceStatus");
        } catch (JSONException e2) {
            RLog.e(TAG, "JSONException " + e2.getMessage());
        }
    }

    public static CSUpdateMessage obtain(String str, String str2) {
        CSUpdateMessage cSUpdateMessage = new CSUpdateMessage();
        cSUpdateMessage.sid = str;
        cSUpdateMessage.serviceStatus = str2;
        return cSUpdateMessage;
    }

    public CSUpdateMessage(Parcel parcel) {
        this.sid = ParcelUtils.readFromParcel(parcel);
        this.serviceStatus = ParcelUtils.readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.sid);
        ParcelUtils.writeToParcel(parcel, this.serviceStatus);
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sid", this.sid);
            jSONObject.put("serviceStatus", this.serviceStatus);
        } catch (JSONException e) {
            RLog.e(TAG, "JSONException " + e.getMessage());
        }
        try {
            return jSONObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
