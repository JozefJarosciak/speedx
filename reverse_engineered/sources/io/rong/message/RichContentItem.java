package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.beastbikes.framework.ui.android.WebActivity;
import io.rong.common.ParcelUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RichContentItem implements Parcelable {
    public static final Creator<RichContentItem> CREATOR = new C54051();
    private String digest;
    private String imageUrl;
    private String title;
    private String url;

    /* renamed from: io.rong.message.RichContentItem$1 */
    static class C54051 implements Creator<RichContentItem> {
        C54051() {
        }

        public RichContentItem createFromParcel(Parcel parcel) {
            return new RichContentItem(parcel);
        }

        public RichContentItem[] newArray(int i) {
            return new RichContentItem[0];
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.title);
        ParcelUtils.writeToParcel(parcel, this.digest);
        ParcelUtils.writeToParcel(parcel, this.imageUrl);
        ParcelUtils.writeToParcel(parcel, this.url);
    }

    public RichContentItem(Parcel parcel) {
        setTitle(ParcelUtils.readFromParcel(parcel));
        setDigest(ParcelUtils.readFromParcel(parcel));
        setImageUrl(ParcelUtils.readFromParcel(parcel));
        setUrl(ParcelUtils.readFromParcel(parcel));
    }

    public RichContentItem(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (jSONObject.has(WebActivity.EXTRA_TITLE)) {
                setTitle(jSONObject.optString(WebActivity.EXTRA_TITLE));
            }
            if (jSONObject.has("digest")) {
                setDigest(jSONObject.optString("digest"));
            }
            if (jSONObject.has("picurl")) {
                setImageUrl(jSONObject.optString("picurl"));
            }
            if (jSONObject.has("url")) {
                setUrl(jSONObject.optString("url"));
            }
        }
    }

    public RichContentItem(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(WebActivity.EXTRA_TITLE)) {
                setTitle(jSONObject.optString(WebActivity.EXTRA_TITLE));
            }
            if (jSONObject.has("digest")) {
                setDigest(jSONObject.optString("digest"));
            }
            if (jSONObject.has("picurl")) {
                setImageUrl(jSONObject.optString("picurl"));
            }
            if (jSONObject.has("url")) {
                setUrl(jSONObject.optString("url"));
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
    }

    public String getDigest() {
        return this.digest;
    }

    public void setDigest(String str) {
        this.digest = str;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
