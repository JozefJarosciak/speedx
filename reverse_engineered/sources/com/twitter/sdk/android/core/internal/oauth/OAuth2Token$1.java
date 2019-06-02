package com.twitter.sdk.android.core.internal.oauth;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class OAuth2Token$1 implements Creator<OAuth2Token> {
    OAuth2Token$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18281a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18282a(i);
    }

    /* renamed from: a */
    public OAuth2Token m18281a(Parcel parcel) {
        return new OAuth2Token(parcel, null);
    }

    /* renamed from: a */
    public OAuth2Token[] m18282a(int i) {
        return new OAuth2Token[i];
    }
}
