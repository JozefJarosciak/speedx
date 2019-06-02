package io.rong.imlib.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import io.rong.common.ParcelUtils;
import io.rong.imlib.model.Conversation.ConversationType;
import org.json.JSONException;
import org.json.JSONObject;

public class PublicServiceProfile implements Parcelable {
    public static final Creator<PublicServiceProfile> CREATOR = new C53801();
    private String introduction;
    private boolean isFollowed;
    private boolean isGlobal;
    private PublicServiceMenu menu;
    private String name;
    private Uri portraitUri;
    private String publicServiceId;
    private ConversationType publicServiceType;

    /* renamed from: io.rong.imlib.model.PublicServiceProfile$1 */
    static class C53801 implements Creator<PublicServiceProfile> {
        C53801() {
        }

        public PublicServiceProfile createFromParcel(Parcel parcel) {
            return new PublicServiceProfile(parcel);
        }

        public PublicServiceProfile[] newArray(int i) {
            return new PublicServiceProfile[i];
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        ParcelUtils.writeToParcel(parcel, this.name);
        ParcelUtils.writeToParcel(parcel, this.portraitUri);
        ParcelUtils.writeToParcel(parcel, this.publicServiceId);
        if (this.publicServiceType != null) {
            ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.publicServiceType.getValue()));
        } else {
            ParcelUtils.writeToParcel(parcel, Integer.valueOf(0));
        }
        ParcelUtils.writeToParcel(parcel, this.introduction);
        if (this.isFollowed) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(i2));
        if (!this.isGlobal) {
            i3 = 0;
        }
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(i3));
        ParcelUtils.writeToParcel(parcel, this.menu);
    }

    public void setExtra(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("introduction")) {
                    setIntroduction(jSONObject.optString("introduction"));
                }
                if (jSONObject.has("follow")) {
                    setIsFollow(jSONObject.optBoolean("follow"));
                }
                if (jSONObject.has("isGlobal")) {
                    setIsGlobal(jSONObject.optBoolean("isGlobal"));
                }
                if (jSONObject.has("menu") && jSONObject.getJSONArray("menu") != null) {
                    try {
                        this.menu = new PublicServiceMenu(jSONObject.getJSONArray("menu"));
                    } catch (Exception e) {
                        Log.e("DecodePSMenu", e.getMessage());
                    }
                }
            }
        } catch (JSONException e2) {
            Log.e("JSONException", e2.getMessage());
        }
    }

    public PublicServiceProfile(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.name = ParcelUtils.readFromParcel(parcel);
        this.portraitUri = (Uri) ParcelUtils.readFromParcel(parcel, Uri.class);
        this.publicServiceId = ParcelUtils.readFromParcel(parcel);
        this.publicServiceType = ConversationType.setValue(ParcelUtils.readIntFromParcel(parcel).intValue());
        this.introduction = ParcelUtils.readFromParcel(parcel);
        if (ParcelUtils.readIntFromParcel(parcel).intValue() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.isFollowed = z;
        if (ParcelUtils.readIntFromParcel(parcel).intValue() != 1) {
            z2 = false;
        }
        this.isGlobal = z2;
        this.menu = (PublicServiceMenu) ParcelUtils.readFromParcel(parcel, PublicServiceMenu.class);
    }

    public void setIsGlobal(boolean z) {
        this.isGlobal = z;
    }

    public Uri getPortraitUri() {
        return this.portraitUri;
    }

    public void setPortraitUri(Uri uri) {
        this.portraitUri = uri;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getTargetId() {
        return this.publicServiceId;
    }

    public void setTargetId(String str) {
        this.publicServiceId = str;
    }

    public void setIntroduction(String str) {
        this.introduction = str;
    }

    public boolean isFollow() {
        return this.isFollowed;
    }

    public void setIsFollow(boolean z) {
        this.isFollowed = z;
    }

    public ConversationType getConversationType() {
        return this.publicServiceType;
    }

    public void setPublicServiceType(ConversationType conversationType) {
        this.publicServiceType = conversationType;
    }

    public boolean isGlobal() {
        return this.isGlobal;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public PublicServiceMenu getMenu() {
        return this.menu;
    }
}
