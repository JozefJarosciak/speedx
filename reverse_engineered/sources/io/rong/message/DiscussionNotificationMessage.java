package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.UserInfo;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 1, value = "RC:DizNtf")
public class DiscussionNotificationMessage extends NotificationMessage {
    public static final Creator<DiscussionNotificationMessage> CREATOR = new DiscussionNotificationMessage$1();
    private String extension;
    private boolean hasReceived;
    private String operator;
    private int type;

    public DiscussionNotificationMessage(Parcel parcel) {
        this.type = ParcelUtils.readIntFromParcel(parcel).intValue();
        this.extension = ParcelUtils.readFromParcel(parcel);
        this.operator = ParcelUtils.readFromParcel(parcel);
        setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
    }

    public DiscussionNotificationMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            setType(jSONObject.optInt("type"));
            setExtension(jSONObject.optString("extension"));
            setOperator(jSONObject.optString("operator"));
            if (jSONObject.has("user")) {
                setUserInfo(parseJsonToUserInfo(jSONObject.getJSONObject("user")));
            }
        } catch (JSONException e2) {
            Log.e("JSONException", e2.getMessage());
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.type));
        ParcelUtils.writeToParcel(parcel, this.extension);
        ParcelUtils.writeToParcel(parcel, this.operator);
        ParcelUtils.writeToParcel(parcel, getUserInfo());
    }

    public boolean isHasReceived() {
        return this.hasReceived;
    }

    public void setHasReceived(boolean z) {
        this.hasReceived = z;
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.type);
            jSONObject.put("extension", this.extension);
            if (getJSONUserInfo() != null) {
                jSONObject.putOpt("user", getJSONUserInfo());
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
        try {
            return jSONObject.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String str) {
        this.extension = str;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String str) {
        this.operator = str;
    }
}
