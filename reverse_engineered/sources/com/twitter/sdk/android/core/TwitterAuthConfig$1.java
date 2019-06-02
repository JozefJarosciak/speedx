package com.twitter.sdk.android.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class TwitterAuthConfig$1 implements Creator<TwitterAuthConfig> {
    TwitterAuthConfig$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18132a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18133a(i);
    }

    /* renamed from: a */
    public TwitterAuthConfig m18132a(Parcel parcel) {
        return new TwitterAuthConfig(parcel, null);
    }

    /* renamed from: a */
    public TwitterAuthConfig[] m18133a(int i) {
        return new TwitterAuthConfig[i];
    }
}
