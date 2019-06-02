package com.twitter.sdk.android.core.internal.oauth;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.twitter.sdk.android.core.TwitterAuthToken;

public class OAuthResponse implements Parcelable {
    public static final Creator<OAuthResponse> CREATOR = new C46191();
    /* renamed from: a */
    public final TwitterAuthToken f16283a;
    /* renamed from: b */
    public final String f16284b;
    /* renamed from: c */
    public final long f16285c;

    /* renamed from: com.twitter.sdk.android.core.internal.oauth.OAuthResponse$1 */
    static class C46191 implements Creator<OAuthResponse> {
        C46191() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m18283a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m18284a(i);
        }

        /* renamed from: a */
        public OAuthResponse m18283a(Parcel parcel) {
            return new OAuthResponse(parcel);
        }

        /* renamed from: a */
        public OAuthResponse[] m18284a(int i) {
            return new OAuthResponse[i];
        }
    }

    public OAuthResponse(TwitterAuthToken twitterAuthToken, String str, long j) {
        this.f16283a = twitterAuthToken;
        this.f16284b = str;
        this.f16285c = j;
    }

    private OAuthResponse(Parcel parcel) {
        this.f16283a = (TwitterAuthToken) parcel.readParcelable(TwitterAuthToken.class.getClassLoader());
        this.f16284b = parcel.readString();
        this.f16285c = parcel.readLong();
    }

    public String toString() {
        return "authToken=" + this.f16283a + ",userName=" + this.f16284b + ",userId=" + this.f16285c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f16283a, i);
        parcel.writeString(this.f16284b);
        parcel.writeLong(this.f16285c);
    }
}
