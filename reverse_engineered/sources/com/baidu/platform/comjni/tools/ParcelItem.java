package com.baidu.platform.comjni.tools;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ParcelItem implements Parcelable {
    public static final Creator<ParcelItem> CREATOR = new C1291b();
    /* renamed from: a */
    private Bundle f3943a;

    public int describeContents() {
        return 0;
    }

    public Bundle getBundle() {
        return this.f3943a;
    }

    public void setBundle(Bundle bundle) {
        this.f3943a = bundle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.f3943a);
    }
}
