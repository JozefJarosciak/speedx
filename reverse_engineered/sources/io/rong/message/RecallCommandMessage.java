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

@MessageTag("RC:RcCmd")
public class RecallCommandMessage extends MessageContent {
    public static final Creator<RecallCommandMessage> CREATOR = new RecallCommandMessage$1();
    private static final String TAG = "RecallCommandMessage";
    private String extra;
    private String messageUId;

    public String getMessageUId() {
        return this.messageUId;
    }

    public void setMessageUId(String str) {
        this.messageUId = str;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public RecallCommandMessage(String str) {
        setMessageUId(str);
    }

    public RecallCommandMessage(String str, String str2) {
        setMessageUId(str);
        setExtra(str2);
    }

    public RecallCommandMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            RLog.e(TAG, e.getMessage());
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("messageUId")) {
                setMessageUId(jSONObject.getString("messageUId"));
            }
            if (jSONObject.has("extra")) {
                setExtra(jSONObject.getString("extra"));
            }
        } catch (JSONException e2) {
            RLog.e(TAG, e2.getMessage());
        }
    }

    public RecallCommandMessage(Parcel parcel) {
        setMessageUId(ParcelUtils.readFromParcel(parcel));
        setExtra(ParcelUtils.readFromParcel(parcel));
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(getMessageUId())) {
                jSONObject.put("messageUId", getMessageUId());
            }
            if (!TextUtils.isEmpty(getExtra())) {
                jSONObject.put("extra", getExtra());
            }
        } catch (JSONException e) {
            RLog.e(TAG, e.getMessage());
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
        ParcelUtils.writeToParcel(parcel, getMessageUId());
        ParcelUtils.writeToParcel(parcel, getExtra());
    }
}
