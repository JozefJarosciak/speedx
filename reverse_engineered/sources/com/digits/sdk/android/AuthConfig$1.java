package com.digits.sdk.android;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class AuthConfig$1 implements Creator<AuthConfig> {
    AuthConfig$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m13834a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m13835a(i);
    }

    /* renamed from: a */
    public AuthConfig m13834a(Parcel parcel) {
        return new AuthConfig(parcel);
    }

    /* renamed from: a */
    public AuthConfig[] m13835a(int i) {
        return new AuthConfig[i];
    }
}
