package io.rong.imlib.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import io.rong.common.ParcelUtils;

public class UserInfo implements Parcelable {
    public static final Creator<UserInfo> CREATOR = new C53901();
    private String id;
    private String name;
    private Uri portraitUri;

    /* renamed from: io.rong.imlib.model.UserInfo$1 */
    static class C53901 implements Creator<UserInfo> {
        C53901() {
        }

        public UserInfo createFromParcel(Parcel parcel) {
            return new UserInfo(parcel);
        }

        public UserInfo[] newArray(int i) {
            return new UserInfo[i];
        }
    }

    public UserInfo(Parcel parcel) {
        setUserId(ParcelUtils.readFromParcel(parcel));
        setName(ParcelUtils.readFromParcel(parcel));
        setPortraitUri((Uri) ParcelUtils.readFromParcel(parcel, Uri.class));
    }

    public UserInfo(String str, String str2, Uri uri) {
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("userId is null");
        }
        this.id = str;
        this.name = str2;
        this.portraitUri = uri;
    }

    public String getUserId() {
        if (!TextUtils.isEmpty(this.id)) {
            return this.id;
        }
        throw new NullPointerException("userId  is null");
    }

    public void setUserId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Uri getPortraitUri() {
        return this.portraitUri;
    }

    public void setPortraitUri(Uri uri) {
        this.portraitUri = uri;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, getUserId());
        ParcelUtils.writeToParcel(parcel, getName());
        ParcelUtils.writeToParcel(parcel, getPortraitUri());
    }
}
