package io.rong.message;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import ch.qos.logback.core.joran.action.Action;
import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;

@MessageTag(flag = 3, messageHandler = ImageMessageHandler.class, value = "RC:ImgMsg")
public class ImageMessage extends MessageContent {
    public static final Creator<ImageMessage> CREATOR = new ImageMessage$1();
    protected String extra;
    private String mBase64;
    boolean mIsFull;
    private Uri mLocalUri;
    private Uri mRemoteUri;
    private Uri mThumUri;
    private boolean mUpLoadExp = false;

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public ImageMessage(byte[] bArr) {
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            if (jSONObject.has("imageUri")) {
                Object optString = jSONObject.optString("imageUri");
                if (!TextUtils.isEmpty(optString)) {
                    setRemoteUri(Uri.parse(optString));
                }
                if (!(getRemoteUri() == null || getRemoteUri().getScheme() == null || !getRemoteUri().getScheme().equals(Action.FILE_ATTRIBUTE))) {
                    setLocalUri(getRemoteUri());
                }
            }
            if (jSONObject.has("content")) {
                setBase64(jSONObject.optString("content"));
            }
            if (jSONObject.has("extra")) {
                setExtra(jSONObject.optString("extra"));
            }
            if (jSONObject.has("exp")) {
                setUpLoadExp(true);
            }
            if (jSONObject.has("isFull")) {
                setIsFull(jSONObject.optBoolean("isFull"));
            }
            if (jSONObject.has("user")) {
                setUserInfo(parseJsonToUserInfo(jSONObject.getJSONObject("user")));
            }
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
    }

    private ImageMessage(Uri uri, Uri uri2) {
        this.mThumUri = uri;
        this.mLocalUri = uri2;
    }

    private ImageMessage(Uri uri, Uri uri2, boolean z) {
        this.mThumUri = uri;
        this.mLocalUri = uri2;
        this.mIsFull = z;
    }

    public static ImageMessage obtain(Uri uri, Uri uri2) {
        return new ImageMessage(uri, uri2);
    }

    public static ImageMessage obtain(Uri uri, Uri uri2, boolean z) {
        return new ImageMessage(uri, uri2, z);
    }

    public static ImageMessage obtain() {
        return new ImageMessage();
    }

    public Uri getThumUri() {
        return this.mThumUri;
    }

    public boolean isFull() {
        return this.mIsFull;
    }

    public void setIsFull(boolean z) {
        this.mIsFull = z;
    }

    public void setThumUri(Uri uri) {
        this.mThumUri = uri;
    }

    public Uri getLocalUri() {
        return this.mLocalUri;
    }

    public void setLocalUri(Uri uri) {
        this.mLocalUri = uri;
    }

    public Uri getRemoteUri() {
        return this.mRemoteUri;
    }

    public void setRemoteUri(Uri uri) {
        this.mRemoteUri = uri;
    }

    public void setBase64(String str) {
        this.mBase64 = str;
    }

    public String getBase64() {
        return this.mBase64;
    }

    public boolean isUpLoadExp() {
        return this.mUpLoadExp;
    }

    public void setUpLoadExp(boolean z) {
        this.mUpLoadExp = z;
    }

    public byte[] encode() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.isEmpty(this.mBase64)) {
                Log.d("ImageMessage", "base64 is null");
            } else {
                jSONObject.put("content", this.mBase64);
            }
            if (this.mRemoteUri != null) {
                jSONObject.put("imageUri", this.mRemoteUri.toString());
            } else if (getLocalUri() != null) {
                jSONObject.put("imageUri", getLocalUri().toString());
            }
            if (this.mUpLoadExp) {
                jSONObject.put("exp", true);
            }
            jSONObject.put("isFull", this.mIsFull);
            if (!TextUtils.isEmpty(getExtra())) {
                jSONObject.put("extra", getExtra());
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

    public ImageMessage(Parcel parcel) {
        boolean z;
        setExtra(ParcelUtils.readFromParcel(parcel));
        this.mLocalUri = (Uri) ParcelUtils.readFromParcel(parcel, Uri.class);
        this.mRemoteUri = (Uri) ParcelUtils.readFromParcel(parcel, Uri.class);
        this.mThumUri = (Uri) ParcelUtils.readFromParcel(parcel, Uri.class);
        setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
        if (ParcelUtils.readIntFromParcel(parcel).intValue() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.mIsFull = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, getExtra());
        ParcelUtils.writeToParcel(parcel, this.mLocalUri);
        ParcelUtils.writeToParcel(parcel, this.mRemoteUri);
        ParcelUtils.writeToParcel(parcel, this.mThumUri);
        ParcelUtils.writeToParcel(parcel, getUserInfo());
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(this.mIsFull ? 1 : 0));
    }
}
