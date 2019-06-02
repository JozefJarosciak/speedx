package com.twitter.sdk.android.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TwitterAuthConfig implements Parcelable {
    public static final Creator<TwitterAuthConfig> CREATOR = new TwitterAuthConfig$1();
    /* renamed from: a */
    private final String f7048a;
    /* renamed from: b */
    private final String f7049b;

    public TwitterAuthConfig(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("TwitterAuthConfig must not be created with null consumer key or secret.");
        }
        this.f7048a = m8294a(str);
        this.f7049b = m8294a(str2);
    }

    private TwitterAuthConfig(Parcel parcel) {
        this.f7048a = parcel.readString();
        this.f7049b = parcel.readString();
    }

    /* renamed from: a */
    public String m8295a() {
        return this.f7048a;
    }

    /* renamed from: b */
    public String m8296b() {
        return this.f7049b;
    }

    /* renamed from: a */
    static String m8294a(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f7048a);
        parcel.writeString(this.f7049b);
    }
}
