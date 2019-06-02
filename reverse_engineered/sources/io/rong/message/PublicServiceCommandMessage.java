package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0861d;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceMenuItem;
import io.rong.imlib.model.UserInfo;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag("RC:PSCmd")
public class PublicServiceCommandMessage extends MessageContent {
    public static final Creator<PublicServiceCommandMessage> CREATOR = new PublicServiceCommandMessage$1();
    private static final String TAG = "PublicServiceCommandMessage";
    private String command;
    private String data;
    protected String extra;

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cmd", this.command);
            jSONObject.put(C0861d.f2139k, this.data);
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

    public PublicServiceCommandMessage(byte[] bArr) {
    }

    public static PublicServiceCommandMessage obtain(PublicServiceMenuItem publicServiceMenuItem) {
        PublicServiceCommandMessage publicServiceCommandMessage = new PublicServiceCommandMessage();
        if (publicServiceMenuItem.getType() != null) {
            publicServiceCommandMessage.command = publicServiceMenuItem.getType().getMessage();
            publicServiceCommandMessage.data = publicServiceMenuItem.getId();
        }
        return publicServiceCommandMessage;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.command);
        ParcelUtils.writeToParcel(parcel, this.data);
        ParcelUtils.writeToParcel(parcel, getExtra());
        ParcelUtils.writeToParcel(parcel, getUserInfo());
    }

    public PublicServiceCommandMessage(Parcel parcel) {
        this.command = ParcelUtils.readFromParcel(parcel);
        this.data = ParcelUtils.readFromParcel(parcel);
        setExtra(ParcelUtils.readFromParcel(parcel));
        setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
    }
}
