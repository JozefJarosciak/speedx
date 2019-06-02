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

@MessageTag(flag = 0, value = "RC:CsSp")
public class CSSuspendMessage extends MessageContent {
    public static final Creator<CSSuspendMessage> CREATOR = new CSSuspendMessage$1();
    private static final String TAG = "CSSuspendMessage";
    private String pid;
    private String sid;
    private String uid;

    public CSSuspendMessage(byte[] bArr) {
    }

    public static CSSuspendMessage obtain(String str, String str2, String str3) {
        CSSuspendMessage cSSuspendMessage = new CSSuspendMessage();
        cSSuspendMessage.sid = str;
        cSSuspendMessage.uid = str2;
        cSSuspendMessage.pid = str3;
        return cSSuspendMessage;
    }

    public CSSuspendMessage(Parcel parcel) {
        this.sid = ParcelUtils.readFromParcel(parcel);
        this.uid = ParcelUtils.readFromParcel(parcel);
        this.pid = ParcelUtils.readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.sid);
        ParcelUtils.writeToParcel(parcel, this.uid);
        ParcelUtils.writeToParcel(parcel, this.pid);
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", this.uid);
            jSONObject.put("sid", this.sid);
            jSONObject.put("pid", this.pid);
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
