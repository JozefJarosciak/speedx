package io.rong.message;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 3, messageHandler = VoiceMessageHandler.class, value = "RC:VcMsg")
public class VoiceMessage extends MessageContent {
    public static final Creator<VoiceMessage> CREATOR = new VoiceMessage$1();
    protected String extra;
    private String mBase64;
    private int mDuration;
    private Uri mUri;

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public VoiceMessage(Parcel parcel) {
        setExtra(ParcelUtils.readFromParcel(parcel));
        this.mUri = (Uri) ParcelUtils.readFromParcel(parcel, Uri.class);
        this.mDuration = ParcelUtils.readIntFromParcel(parcel).intValue();
        setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
    }

    public VoiceMessage(byte[] bArr) {
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            if (jSONObject.has("duration")) {
                setDuration(jSONObject.optInt("duration"));
            }
            if (jSONObject.has("content")) {
                setBase64(jSONObject.optString("content"));
            }
            if (jSONObject.has("extra")) {
                setExtra(jSONObject.optString("extra"));
            }
            if (jSONObject.has("user")) {
                setUserInfo(parseJsonToUserInfo(jSONObject.getJSONObject("user")));
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
    }

    private VoiceMessage(Uri uri, int i) {
        this.mUri = uri;
        this.mDuration = i;
    }

    public static VoiceMessage obtain(Uri uri, int i) {
        return new VoiceMessage(uri, i);
    }

    public Uri getUri() {
        return this.mUri;
    }

    public void setUri(Uri uri) {
        this.mUri = uri;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public String getBase64() {
        return this.mBase64;
    }

    public void setBase64(String str) {
        this.mBase64 = str;
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content", this.mBase64);
            jSONObject.put("duration", this.mDuration);
            if (!TextUtils.isEmpty(getExtra())) {
                jSONObject.put("extra", this.extra);
            }
            if (getJSONUserInfo() != null) {
                jSONObject.putOpt("user", getJSONUserInfo());
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
        this.mBase64 = null;
        return jSONObject.toString().getBytes();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.extra);
        ParcelUtils.writeToParcel(parcel, this.mUri);
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.mDuration));
        ParcelUtils.writeToParcel(parcel, getUserInfo());
    }
}
