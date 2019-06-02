package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0861d;
import com.avos.avoscloud.AVStatus;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 1, value = "RC:GrpNtf")
public class GroupNotificationMessage extends MessageContent {
    public static final Creator<GroupNotificationMessage> CREATOR = new GroupNotificationMessage$1();
    public static final String GROUP_OPERATION_ADD = "Add";
    public static final String GROUP_OPERATION_BULLETIN = "Bulletin";
    public static final String GROUP_OPERATION_KICKED = "Kicked";
    public static final String GROUP_OPERATION_QUIT = "Quit";
    public static final String GROUP_OPERATION_RENAME = "Rename";
    private static final String TAG = "GroupNotificationMessage";
    private String data;
    private String extra;
    private String message;
    private String operation;
    private String operatorUserId;

    public String getOperatorUserId() {
        return this.operatorUserId;
    }

    public void setOperatorUserId(String str) {
        this.operatorUserId = str;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String str) {
        this.operation = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
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

    public GroupNotificationMessage(Parcel parcel) {
        this.operatorUserId = ParcelUtils.readFromParcel(parcel);
        this.operation = ParcelUtils.readFromParcel(parcel);
        this.data = ParcelUtils.readFromParcel(parcel);
        this.message = ParcelUtils.readFromParcel(parcel);
        this.extra = ParcelUtils.readFromParcel(parcel);
        setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
    }

    public static GroupNotificationMessage obtain(String str, String str2, String str3, String str4) {
        GroupNotificationMessage groupNotificationMessage = new GroupNotificationMessage();
        groupNotificationMessage.operatorUserId = str;
        groupNotificationMessage.operation = str2;
        groupNotificationMessage.data = str3;
        groupNotificationMessage.message = str4;
        return groupNotificationMessage;
    }

    private GroupNotificationMessage() {
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("operatorUserId", this.operatorUserId);
            jSONObject.put("operation", this.operation);
            if (!TextUtils.isEmpty(this.data)) {
                jSONObject.put(C0861d.f2139k, this.data);
            }
            if (!TextUtils.isEmpty(this.message)) {
                jSONObject.put(AVStatus.MESSAGE_TAG, this.message);
            }
            if (!TextUtils.isEmpty(getExtra())) {
                jSONObject.put("extra", getExtra());
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

    public GroupNotificationMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            setOperatorUserId(jSONObject.optString("operatorUserId"));
            setOperation(jSONObject.optString("operation"));
            setData(jSONObject.optString(C0861d.f2139k));
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
        ParcelUtils.writeToParcel(parcel, this.operatorUserId);
        ParcelUtils.writeToParcel(parcel, this.operation);
        ParcelUtils.writeToParcel(parcel, this.data);
        ParcelUtils.writeToParcel(parcel, this.message);
        ParcelUtils.writeToParcel(parcel, this.extra);
        ParcelUtils.writeToParcel(parcel, getUserInfo());
    }

    public int describeContents() {
        return 0;
    }
}
