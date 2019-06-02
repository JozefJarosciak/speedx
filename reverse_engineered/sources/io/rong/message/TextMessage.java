package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 3, value = "RC:TxtMsg")
public class TextMessage extends MessageContent {
    public static final Creator<TextMessage> CREATOR = new TextMessage$1();
    private static final String TAG = "TextMessage";
    private String content;
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
            jSONObject.put("content", getEmotion(getContent()));
            if (!TextUtils.isEmpty(getExtra())) {
                jSONObject.put("extra", getExtra());
            }
            if (getJSONUserInfo() != null) {
                jSONObject.putOpt("user", getJSONUserInfo());
            }
            if (getJsonMentionInfo() != null) {
                jSONObject.putOpt("mentionedInfo", getJsonMentionInfo());
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

    private String getEmotion(String str) {
        Matcher matcher = Pattern.compile("\\[/u([0-9A-Fa-f]+)\\]").matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, String.valueOf(Character.toChars(Integer.parseInt(matcher.group(1), 16))));
        }
        matcher.appendTail(stringBuffer);
        RLog.d(TAG, "getEmotion--" + stringBuffer.toString());
        return stringBuffer.toString();
    }

    protected TextMessage() {
    }

    public static TextMessage obtain(String str) {
        TextMessage textMessage = new TextMessage();
        textMessage.setContent(str);
        return textMessage;
    }

    public TextMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("content")) {
                setContent(jSONObject.optString("content"));
            }
            if (jSONObject.has("extra")) {
                setExtra(jSONObject.optString("extra"));
            }
            if (jSONObject.has("user")) {
                setUserInfo(parseJsonToUserInfo(jSONObject.getJSONObject("user")));
            }
            if (jSONObject.has("mentionedInfo")) {
                setMentionedInfo(parseJsonToMentionInfo(jSONObject.getJSONObject("mentionedInfo")));
            }
        } catch (JSONException e2) {
            RLog.e(TAG, "JSONException " + e2.getMessage());
        }
    }

    public void setContent(String str) {
        this.content = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, getExtra());
        ParcelUtils.writeToParcel(parcel, this.content);
        ParcelUtils.writeToParcel(parcel, getUserInfo());
        ParcelUtils.writeToParcel(parcel, getMentionedInfo());
    }

    public TextMessage(Parcel parcel) {
        setExtra(ParcelUtils.readFromParcel(parcel));
        setContent(ParcelUtils.readFromParcel(parcel));
        setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
        setMentionedInfo((MentionedInfo) ParcelUtils.readFromParcel(parcel, MentionedInfo.class));
    }

    public TextMessage(String str) {
        setContent(str);
    }

    public String getContent() {
        return this.content;
    }
}
