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

@MessageTag("RC:ReadNtf")
public class ReadReceiptMessage extends MessageContent {
    public static final Creator<ReadReceiptMessage> CREATOR = new ReadReceiptMessage$1();
    private static final String TAG = "ReadReceiptMessage";
    private long lastMessageSendTime;
    private String messageUId;
    private int type;

    public ReadReceiptMessage(long j) {
        setLastMessageSendTime(j);
        setType(1);
    }

    public ReadReceiptMessage(long j, String str, int i) {
        setLastMessageSendTime(j);
        setMessageUId(str);
        setType(i);
    }

    public ReadReceiptMessage(Parcel parcel) {
        setLastMessageSendTime(ParcelUtils.readLongFromParcel(parcel).longValue());
        setMessageUId(ParcelUtils.readFromParcel(parcel));
        setType(ParcelUtils.readIntFromParcel(parcel).intValue());
    }

    public ReadReceiptMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            RLog.e(TAG, e.getMessage());
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("lastMessageSendTime")) {
                setLastMessageSendTime(jSONObject.getLong("lastMessageSendTime"));
            }
            if (jSONObject.has("messageUId")) {
                setMessageUId(jSONObject.getString("messageUId"));
            }
            if (jSONObject.has("type")) {
                setType(jSONObject.getInt("type"));
            }
        } catch (JSONException e2) {
            RLog.e(TAG, e2.getMessage());
        }
    }

    public long getLastMessageSendTime() {
        return this.lastMessageSendTime;
    }

    public void setLastMessageSendTime(long j) {
        this.lastMessageSendTime = j;
    }

    public String getMessageUId() {
        return this.messageUId;
    }

    public void setMessageUId(String str) {
        this.messageUId = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("lastMessageSendTime", getLastMessageSendTime());
            if (!TextUtils.isEmpty(getMessageUId())) {
                jSONObject.put("messageUId", getMessageUId());
            }
            jSONObject.put("type", getType());
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

    private ReadReceiptMessage() {
    }

    public static ReadReceiptMessage obtain(long j) {
        ReadReceiptMessage readReceiptMessage = new ReadReceiptMessage();
        readReceiptMessage.setLastMessageSendTime(j);
        readReceiptMessage.setType(1);
        return readReceiptMessage;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, Long.valueOf(getLastMessageSendTime()));
        ParcelUtils.writeToParcel(parcel, getMessageUId());
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getType()));
    }
}
