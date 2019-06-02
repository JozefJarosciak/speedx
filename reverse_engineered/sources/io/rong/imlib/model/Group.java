package io.rong.imlib.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import io.rong.common.ParcelUtils;

public class Group implements Parcelable {
    public static final Creator<Group> CREATOR = new C53761();
    private String id;
    private String name;
    private Uri portraitUri;

    /* renamed from: io.rong.imlib.model.Group$1 */
    static class C53761 implements Creator<Group> {
        C53761() {
        }

        public Group createFromParcel(Parcel parcel) {
            return new Group(parcel);
        }

        public Group[] newArray(int i) {
            return new Group[i];
        }
    }

    public Group(Parcel parcel) {
        this(ParcelUtils.readFromParcel(parcel), ParcelUtils.readFromParcel(parcel), (Uri) ParcelUtils.readFromParcel(parcel, Uri.class));
    }

    public Group(String str, String str2, Uri uri) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new RuntimeException("groupId or name is null");
        }
        this.id = str;
        this.name = str2;
        this.portraitUri = uri;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
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
        ParcelUtils.writeToParcel(parcel, getId());
        ParcelUtils.writeToParcel(parcel, getName());
        ParcelUtils.writeToParcel(parcel, getPortraitUri());
    }
}
