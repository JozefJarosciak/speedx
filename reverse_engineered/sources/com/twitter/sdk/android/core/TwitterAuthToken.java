package com.twitter.sdk.android.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.internal.oauth.C4621c;
import java.util.HashMap;
import java.util.Map;

public class TwitterAuthToken extends C1500b implements Parcelable {
    public static final Creator<TwitterAuthToken> CREATOR = new TwitterAuthToken$1();
    @SerializedName("token")
    /* renamed from: b */
    public final String f7051b;
    @SerializedName("secret")
    /* renamed from: c */
    public final String f7052c;

    public TwitterAuthToken(String str, String str2) {
        this.f7051b = str;
        this.f7052c = str2;
    }

    private TwitterAuthToken(Parcel parcel) {
        this.f7051b = parcel.readString();
        this.f7052c = parcel.readString();
    }

    /* renamed from: a */
    public boolean mo2982a() {
        return false;
    }

    /* renamed from: a */
    public Map<String, String> m8298a(TwitterAuthConfig twitterAuthConfig, String str, String str2, Map<String, String> map) {
        Map<String, String> hashMap = new HashMap(1);
        hashMap.put("Authorization", new C4621c().a(twitterAuthConfig, this, null, str, str2, map));
        return hashMap;
    }

    public String toString() {
        return "token=" + this.f7051b + ",secret=" + this.f7052c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f7051b);
        parcel.writeString(this.f7052c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TwitterAuthToken)) {
            return false;
        }
        TwitterAuthToken twitterAuthToken = (TwitterAuthToken) obj;
        if (this.f7052c == null ? twitterAuthToken.f7052c != null : !this.f7052c.equals(twitterAuthToken.f7052c)) {
            return false;
        }
        if (this.f7051b != null) {
            if (this.f7051b.equals(twitterAuthToken.f7051b)) {
                return true;
            }
        } else if (twitterAuthToken.f7051b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.f7051b != null) {
            hashCode = this.f7051b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.f7052c != null) {
            i = this.f7052c.hashCode();
        }
        return hashCode + i;
    }
}
