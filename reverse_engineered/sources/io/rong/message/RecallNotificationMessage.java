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

@MessageTag(flag = 1, value = "RC:RcNtf")
public class RecallNotificationMessage extends MessageContent {
    public static final Creator<RecallNotificationMessage> CREATOR = new RecallNotificationMessage$1();
    private static final String TAG = "RecallNotificationMessage";
    private String mOperatorId;
    private String mOriginalObjectName;
    private long mRecallTime;

    public String getOperatorId() {
        return this.mOperatorId;
    }

    public long getRecallTime() {
        return this.mRecallTime;
    }

    public String getOriginalObjectName() {
        return this.mOriginalObjectName;
    }

    public RecallNotificationMessage(String str, long j, String str2) {
        this.mOperatorId = str;
        this.mRecallTime = j;
        this.mOriginalObjectName = str2;
    }

    public RecallNotificationMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            RLog.e(TAG, e.getMessage());
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("operatorId")) {
                this.mOperatorId = jSONObject.getString("operatorId");
            }
            if (jSONObject.has("recallTime")) {
                this.mRecallTime = jSONObject.getLong("recallTime");
            }
            if (jSONObject.has("originalObjectName")) {
                this.mOriginalObjectName = jSONObject.getString("originalObjectName");
            }
        } catch (JSONException e2) {
            RLog.e(TAG, e2.getMessage());
        }
    }

    public RecallNotificationMessage(Parcel parcel) {
        this.mOperatorId = ParcelUtils.readFromParcel(parcel);
        this.mRecallTime = ParcelUtils.readLongFromParcel(parcel).longValue();
        this.mOriginalObjectName = ParcelUtils.readFromParcel(parcel);
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(getOperatorId())) {
                jSONObject.put("operatorId", getOperatorId());
            }
            jSONObject.put("recallTime", getRecallTime());
            if (!TextUtils.isEmpty(getOriginalObjectName())) {
                jSONObject.put("originalObjectName", getOriginalObjectName());
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
        ParcelUtils.writeToParcel(parcel, getOperatorId());
        ParcelUtils.writeToParcel(parcel, Long.valueOf(getRecallTime()));
        ParcelUtils.writeToParcel(parcel, getOriginalObjectName());
    }
}
