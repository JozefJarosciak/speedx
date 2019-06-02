package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0861d;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 1, value = "RC:CmdNtf")
public class CommandNotificationMessage extends MessageContent {
    public static final Creator<CommandNotificationMessage> CREATOR = new CommandNotificationMessage$1();
    private static final String TAG = "CommandNotificationMessage";
    private String data;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public CommandNotificationMessage(Parcel parcel) {
        this.name = ParcelUtils.readFromParcel(parcel);
        this.data = ParcelUtils.readFromParcel(parcel);
        setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
    }

    public static CommandNotificationMessage obtain(String str, String str2) {
        CommandNotificationMessage commandNotificationMessage = new CommandNotificationMessage();
        commandNotificationMessage.name = str;
        commandNotificationMessage.data = str2;
        return commandNotificationMessage;
    }

    private CommandNotificationMessage() {
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.name);
            if (!TextUtils.isEmpty(this.data)) {
                jSONObject.put(C0861d.f2139k, this.data);
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

    public CommandNotificationMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            setName(jSONObject.optString("name"));
            setData(jSONObject.optString(C0861d.f2139k));
            if (jSONObject.has("user")) {
                setUserInfo(parseJsonToUserInfo(jSONObject.getJSONObject("user")));
            }
        } catch (JSONException e2) {
            RLog.e(TAG, "JSONException " + e2.getMessage());
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.name);
        ParcelUtils.writeToParcel(parcel, this.data);
        ParcelUtils.writeToParcel(parcel, getUserInfo());
    }

    public int describeContents() {
        return 0;
    }
}
