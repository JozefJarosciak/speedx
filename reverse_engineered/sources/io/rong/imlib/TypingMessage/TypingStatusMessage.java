package io.rong.imlib.TypingMessage;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0861d;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.message.StatusMessage;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 16, value = "RC:TypSts")
public class TypingStatusMessage extends StatusMessage {
    public static final Creator<TypingStatusMessage> CREATOR = new TypingStatusMessage$1();
    private static final String TAG = "TypingStatusMessage";
    private String data;
    private String typingContentType;

    public String getTypingContentType() {
        return this.typingContentType;
    }

    public void setTypingContentType(String str) {
        this.typingContentType = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public TypingStatusMessage(String str, String str2) {
        setTypingContentType(str);
        setData(str2);
    }

    public TypingStatusMessage(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            String str;
            try {
                str = new String(bArr, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                RLog.e(TAG, "TypingStatusMessage " + e.getMessage());
                str = null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("typingContentType")) {
                    setTypingContentType(jSONObject.getString("typingContentType"));
                }
                if (jSONObject.has(C0861d.f2139k)) {
                    setData(jSONObject.getString(C0861d.f2139k));
                }
            } catch (JSONException e2) {
                RLog.e(TAG, "TypingStatusMessage " + e2.getMessage());
            }
        }
    }

    public TypingStatusMessage(Parcel parcel) {
        setTypingContentType(ParcelUtils.readFromParcel(parcel));
        setData(ParcelUtils.readFromParcel(parcel));
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("typingContentType", getTypingContentType());
            if (!TextUtils.isEmpty(getData())) {
                jSONObject.put(C0861d.f2139k, getData());
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, getTypingContentType());
        ParcelUtils.writeToParcel(parcel, getData());
    }
}
