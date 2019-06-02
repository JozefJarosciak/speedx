package com.twitter.sdk.android.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class TwitterAuthToken$1 implements Creator<TwitterAuthToken> {
    TwitterAuthToken$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18134a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18135a(i);
    }

    /* renamed from: a */
    public TwitterAuthToken m18134a(Parcel parcel) {
        return new TwitterAuthToken(parcel, null);
    }

    /* renamed from: a */
    public TwitterAuthToken[] m18135a(int i) {
        return new TwitterAuthToken[i];
    }
}
