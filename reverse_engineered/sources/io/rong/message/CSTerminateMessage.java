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

@MessageTag(flag = 0, value = "RC:CsEnd")
public class CSTerminateMessage extends MessageContent {
    public static final Creator<CSTerminateMessage> CREATOR = new CSTerminateMessage$1();
    private static final String TAG = "CSTerminateMessage";
    private int code;
    private String msg;
    private String sid;

    public CSTerminateMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.code = jSONObject.optInt("code");
            this.msg = jSONObject.optString("msg");
            this.sid = jSONObject.optString("sid");
        } catch (JSONException e2) {
            RLog.e(TAG, "JSONException " + e2.getMessage());
        }
    }

    public static CSTerminateMessage obtain() {
        return new CSTerminateMessage();
    }

    public CSTerminateMessage(Parcel parcel) {
        this.code = ParcelUtils.readIntFromParcel(parcel).intValue();
        this.msg = ParcelUtils.readFromParcel(parcel);
        this.sid = ParcelUtils.readFromParcel(parcel);
    }

    public String getsid() {
        return this.sid;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.code));
        ParcelUtils.writeToParcel(parcel, this.msg);
        ParcelUtils.writeToParcel(parcel, this.sid);
    }

    public byte[] encode() {
        return null;
    }
}
