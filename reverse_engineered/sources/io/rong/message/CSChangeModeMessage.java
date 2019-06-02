package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 0, value = "RC:CSCha")
public class CSChangeModeMessage extends MessageContent {
    public static final Creator<CSChangeModeMessage> CREATOR = new CSChangeModeMessage$1();
    private static final String TAG = "CSChangeModeMessage";
    private String groupid;
    private String pid;
    private String sid;
    private String uid;

    public CSChangeModeMessage(byte[] bArr) {
    }

    public static CSChangeModeMessage obtain(String str, String str2, String str3, String str4) {
        CSChangeModeMessage cSChangeModeMessage = new CSChangeModeMessage();
        cSChangeModeMessage.sid = str;
        cSChangeModeMessage.uid = str2;
        cSChangeModeMessage.pid = str3;
        cSChangeModeMessage.groupid = str4;
        return cSChangeModeMessage;
    }

    public CSChangeModeMessage(Parcel parcel) {
        this.sid = ParcelUtils.readFromParcel(parcel);
        this.uid = ParcelUtils.readFromParcel(parcel);
        this.pid = ParcelUtils.readFromParcel(parcel);
        this.groupid = ParcelUtils.readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.sid);
        ParcelUtils.writeToParcel(parcel, this.uid);
        ParcelUtils.writeToParcel(parcel, this.pid);
        ParcelUtils.writeToParcel(parcel, this.groupid);
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", this.uid);
            jSONObject.put("sid", this.sid);
            jSONObject.put("pid", this.pid);
            if (!TextUtils.isEmpty(this.groupid)) {
                jSONObject.put("groupid", this.groupid);
            }
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
