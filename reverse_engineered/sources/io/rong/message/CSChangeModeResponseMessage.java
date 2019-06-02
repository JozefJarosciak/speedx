package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.alipay.sdk.packet.C0861d;
import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 0, value = "RC:CsChaR")
public class CSChangeModeResponseMessage extends MessageContent {
    public static final Creator<CSChangeModeResponseMessage> CREATOR = new CSChangeModeResponseMessage$1();
    private int code;
    private String errMsg;
    private int status;

    public int getResult() {
        return this.code;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public int getStatus() {
        return this.status;
    }

    public CSChangeModeResponseMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.code = jSONObject.optInt("code");
            this.errMsg = jSONObject.optString("msg");
            if (jSONObject.has(C0861d.f2139k)) {
                this.status = jSONObject.getJSONObject(C0861d.f2139k).getInt("status");
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static CSChangeModeResponseMessage obtain() {
        return new CSChangeModeResponseMessage();
    }

    public CSChangeModeResponseMessage(Parcel parcel) {
        this.code = ParcelUtils.readIntFromParcel(parcel).intValue();
        this.status = ParcelUtils.readIntFromParcel(parcel).intValue();
        this.errMsg = ParcelUtils.readFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.code));
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.status));
        ParcelUtils.writeToParcel(parcel, this.errMsg);
    }

    public byte[] encode() {
        return null;
    }
}
