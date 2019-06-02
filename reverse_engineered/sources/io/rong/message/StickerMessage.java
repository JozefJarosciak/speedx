package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 3, value = "RC:StkMsg")
public class StickerMessage extends MessageContent {
    public static final Creator<StickerMessage> CREATOR = new StickerMessage$1();
    private static final String TAG = "StickerMessage";
    private String category;
    private boolean isInstalled;
    private String name;

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("category", this.category);
            jSONObject.put("name", this.name);
            jSONObject.optBoolean("isInstalled", this.isInstalled);
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

    private StickerMessage(StickerMessage$Builder stickerMessage$Builder) {
        this.category = StickerMessage$Builder.access$000(stickerMessage$Builder);
        this.name = StickerMessage$Builder.access$100(stickerMessage$Builder);
        this.isInstalled = StickerMessage$Builder.access$200(stickerMessage$Builder);
    }

    public StickerMessage(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            str = null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.category = jSONObject.optString("category");
            this.name = jSONObject.optString("name");
            this.isInstalled = jSONObject.optBoolean("isInstalled");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public int describeContents() {
        return 0;
    }

    public StickerMessage(Parcel parcel) {
        this.category = ParcelUtils.readFromParcel(parcel);
        this.name = ParcelUtils.readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, this.category);
        ParcelUtils.writeToParcel(parcel, this.name);
    }
}
