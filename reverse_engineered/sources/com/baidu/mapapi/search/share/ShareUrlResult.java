package com.baidu.mapapi.search.share;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;

public class ShareUrlResult extends SearchResult implements Parcelable {
    public static final Creator<ShareUrlResult> CREATOR = new C1188a();
    /* renamed from: a */
    private String f3502a;
    /* renamed from: b */
    private int f3503b;

    protected ShareUrlResult(Parcel parcel) {
        this.f3502a = parcel.readString();
        this.f3503b = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public String getUrl() {
        return this.f3502a;
    }

    public void setType(int i) {
        this.f3503b = i;
    }

    public void setUrl(String str) {
        this.f3502a = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3502a);
        parcel.writeInt(this.f3503b);
    }
}
