package io.rong.message;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 3, value = "RC:PSImgTxtMsg")
public class PublicServiceRichContentMessage extends MessageContent implements Parcelable {
    public static final Creator<PublicServiceRichContentMessage> CREATOR = new PublicServiceRichContentMessage$1();
    private RichContentItem mRichContentItem;
    private UserInfo mUser;

    public PublicServiceRichContentMessage(byte[] bArr) {
        try {
            JSONObject jSONObject = (JSONObject) ((JSONArray) new JSONObject(new String(bArr)).get("articles")).get(0);
            this.mRichContentItem = new RichContentItem(jSONObject);
            JSONObject jSONObject2 = (JSONObject) jSONObject.get("user");
            if (jSONObject.has("portrait")) {
                this.mUser = new UserInfo(jSONObject.optString("id"), jSONObject.optString("name"), Uri.parse(jSONObject.optString("portrait")));
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
    }

    public RichContentItem getMessage() {
        return this.mRichContentItem;
    }

    public UserInfo getPublicServiceUserInfo() {
        return this.mUser;
    }

    public byte[] encode() {
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.mRichContentItem);
    }

    public PublicServiceRichContentMessage(Parcel parcel) {
        this.mRichContentItem = (RichContentItem) ParcelUtils.readFromParcel(parcel, RichContentItem.class);
    }
}
