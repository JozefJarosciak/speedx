package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.avos.avoscloud.AVStatus;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 1, value = "RC:ContactNtf")
public class ContactNotificationMessage extends MessageContent {
    public static final String CONTACT_OPERATION_ACCEPT_RESPONSE = "AcceptResponse";
    public static final String CONTACT_OPERATION_REJECT_RESPONSE = "RejectResponse";
    public static final String CONTACT_OPERATION_REQUEST = "Request";
    public static final Creator<ContactNotificationMessage> CREATOR = new ContactNotificationMessage$1();
    private static final String TAG = "ContactNotificationMessage";
    private String extra;
    private String message;
    private String operation;
    private String sourceUserId;
    private String targetUserId;

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String str) {
        this.operation = str;
    }

    public String getSourceUserId() {
        return this.sourceUserId;
    }

    public void setSourceUserId(String str) {
        this.sourceUserId = str;
    }

    public String getTargetUserId() {
        return this.targetUserId;
    }

    public void setTargetUserId(String str) {
        this.targetUserId = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public ContactNotificationMessage(Parcel parcel) {
        this.operation = ParcelUtils.readFromParcel(parcel);
        this.sourceUserId = ParcelUtils.readFromParcel(parcel);
        this.targetUserId = ParcelUtils.readFromParcel(parcel);
        this.message = ParcelUtils.readFromParcel(parcel);
        this.extra = ParcelUtils.readFromParcel(parcel);
        setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
    }

    public static ContactNotificationMessage obtain(String str, String str2, String str3, String str4) {
        ContactNotificationMessage contactNotificationMessage = new ContactNotificationMessage();
        contactNotificationMessage.operation = str;
        contactNotificationMessage.sourceUserId = str2;
        contactNotificationMessage.targetUserId = str3;
        contactNotificationMessage.message = str4;
        return contactNotificationMessage;
    }

    private ContactNotificationMessage() {
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("operation", this.operation);
            jSONObject.putOpt("sourceUserId", this.sourceUserId);
            jSONObject.putOpt("targetUserId", this.targetUserId);
            if (!TextUtils.isEmpty(this.message)) {
                jSONObject.putOpt(AVStatus.MESSAGE_TAG, this.message);
            }
            if (!TextUtils.isEmpty(getExtra())) {
                jSONObject.putOpt("extra", getExtra());
            }
            if (getJSONUserInfo() != null) {
                jSONObject.putOpt("user", getJSONUserInfo());
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

    public ContactNotificationMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            setOperation(jSONObject.optString("operation"));
            setSourceUserId(jSONObject.optString("sourceUserId"));
            setTargetUserId(jSONObject.optString("targetUserId"));
            setMessage(jSONObject.optString(AVStatus.MESSAGE_TAG));
            setExtra(jSONObject.optString("extra"));
            if (jSONObject.has("user")) {
                setUserInfo(parseJsonToUserInfo(jSONObject.getJSONObject("user")));
            }
        } catch (JSONException e2) {
            RLog.e(TAG, "JSONException " + e2.getMessage());
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.operation);
        ParcelUtils.writeToParcel(parcel, this.sourceUserId);
        ParcelUtils.writeToParcel(parcel, this.targetUserId);
        ParcelUtils.writeToParcel(parcel, this.message);
        ParcelUtils.writeToParcel(parcel, this.extra);
        ParcelUtils.writeToParcel(parcel, getUserInfo());
    }

    public int describeContents() {
        return 0;
    }
}
