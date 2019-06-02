package com.twitter.sdk.android.core.internal.oauth;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.C1500b;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import java.util.HashMap;
import java.util.Map;

public class OAuth2Token extends C1500b implements Parcelable {
    public static final Creator<OAuth2Token> CREATOR = new OAuth2Token$1();
    @SerializedName("token_type")
    /* renamed from: b */
    private final String f7053b;
    @SerializedName("access_token")
    /* renamed from: c */
    private final String f7054c;

    public OAuth2Token(String str, String str2) {
        this.f7053b = str;
        this.f7054c = str2;
    }

    private OAuth2Token(Parcel parcel) {
        this.f7053b = parcel.readString();
        this.f7054c = parcel.readString();
    }

    /* renamed from: c */
    public String m8302c() {
        return this.f7053b;
    }

    /* renamed from: d */
    public String m8303d() {
        return this.f7054c;
    }

    /* renamed from: a */
    public boolean mo2982a() {
        return false;
    }

    /* renamed from: a */
    public Map<String, String> mo2983a(TwitterAuthConfig twitterAuthConfig, String str, String str2, Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("Authorization", OAuth2Service.a(this));
        return hashMap;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f7053b);
        parcel.writeString(this.f7054c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OAuth2Token oAuth2Token = (OAuth2Token) obj;
        if (this.f7054c == null ? oAuth2Token.f7054c != null : !this.f7054c.equals(oAuth2Token.f7054c)) {
            return false;
        }
        if (this.f7053b != null) {
            if (this.f7053b.equals(oAuth2Token.f7053b)) {
                return true;
            }
        } else if (oAuth2Token.f7053b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.f7053b != null) {
            hashCode = this.f7053b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.f7054c != null) {
            i = this.f7054c.hashCode();
        }
        return hashCode + i;
    }
}
