package io.rong.message;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 3, value = "RC:PSMultiImgTxtMsg")
public class PublicServiceMultiRichContentMessage extends MessageContent {
    public static final Creator<PublicServiceMultiRichContentMessage> CREATOR = new PublicServiceMultiRichContentMessage$1();
    private ArrayList<RichContentItem> mRichContentItems = new ArrayList();
    private UserInfo mUser;

    public PublicServiceMultiRichContentMessage(byte[] bArr) {
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            JSONArray jSONArray = (JSONArray) jSONObject.get("articles");
            JSONObject jSONObject2 = jSONObject;
            for (int i = 0; i < jSONArray.length(); i++) {
                jSONObject2 = (JSONObject) jSONArray.get(i);
                this.mRichContentItems.add(new RichContentItem(jSONObject2));
            }
            JSONObject jSONObject3 = (JSONObject) jSONObject2.get("user");
            if (jSONObject2.has("portrait")) {
                this.mUser = new UserInfo(jSONObject2.optString("id"), jSONObject2.optString("name"), Uri.parse(jSONObject2.optString("portrait")));
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
    }

    public byte[] encode() {
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeListToParcel(parcel, this.mRichContentItems);
    }

    public ArrayList<RichContentItem> getMessages() {
        return this.mRichContentItems;
    }

    public UserInfo getPublicServiceUserInfo() {
        return this.mUser;
    }

    public PublicServiceMultiRichContentMessage(Parcel parcel) {
        this.mRichContentItems = ParcelUtils.readListFromParcel(parcel, RichContentItem.class);
    }
}
