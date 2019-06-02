package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.beastbikes.framework.ui.android.WebActivity;
import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 3, value = "RC:ImgTextMsg")
public class RichContentMessage extends MessageContent implements Parcelable {
    public static final Creator<RichContentMessage> CREATOR = new RichContentMessage$1();
    private String content;
    private String extra = "";
    private String imgUrl;
    private String title;
    private String url = "";

    public RichContentMessage(String str, String str2, String str3) {
        this.title = str;
        this.content = str2;
        this.imgUrl = str3;
    }

    public RichContentMessage(String str, String str2, String str3, String str4) {
        this.title = str;
        this.content = str2;
        this.imgUrl = str3;
        this.url = str4;
    }

    public static RichContentMessage obtain(String str, String str2, String str3) {
        return new RichContentMessage(str, str2, str3);
    }

    public static RichContentMessage obtain(String str, String str2, String str3, String str4) {
        return new RichContentMessage(str, str2, str3, str4);
    }

    protected RichContentMessage(Parcel parcel) {
        this.title = ParcelUtils.readFromParcel(parcel);
        this.content = ParcelUtils.readFromParcel(parcel);
        this.imgUrl = ParcelUtils.readFromParcel(parcel);
        this.url = ParcelUtils.readFromParcel(parcel);
        this.extra = ParcelUtils.readFromParcel(parcel);
        setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
    }

    public RichContentMessage(byte[] bArr) {
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            this.title = jSONObject.optString(WebActivity.EXTRA_TITLE);
            this.content = jSONObject.optString("content");
            this.imgUrl = jSONObject.optString("imageUri");
            this.url = jSONObject.optString("url");
            this.extra = jSONObject.optString("extra");
            if (jSONObject.has("user")) {
                setUserInfo(parseJsonToUserInfo(jSONObject.getJSONObject("user")));
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(WebActivity.EXTRA_TITLE, getExpression(getTitle()));
            jSONObject.put("content", getExpression(getContent()));
            jSONObject.put("imageUri", getImgUrl());
            jSONObject.put("url", getUrl());
            jSONObject.put("extra", getExtra());
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
            return new byte[0];
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.title);
        ParcelUtils.writeToParcel(parcel, this.content);
        ParcelUtils.writeToParcel(parcel, this.imgUrl);
        ParcelUtils.writeToParcel(parcel, this.url);
        ParcelUtils.writeToParcel(parcel, this.extra);
        ParcelUtils.writeToParcel(parcel, getUserInfo());
    }

    private String getExpression(String str) {
        Matcher matcher = Pattern.compile("\\[/u([0-9A-Fa-f]+)\\]").matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, toExpressionChar(matcher.group(1)));
        }
        matcher.appendTail(stringBuffer);
        Log.d("getExpression--", stringBuffer.toString());
        return stringBuffer.toString();
    }

    private String toExpressionChar(String str) {
        return String.valueOf(Character.toChars(Integer.parseInt(str, 16)));
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }
}
