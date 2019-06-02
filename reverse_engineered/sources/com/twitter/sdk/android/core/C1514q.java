package com.twitter.sdk.android.core;

import com.google.gson.annotations.SerializedName;

/* compiled from: TwitterSession */
/* renamed from: com.twitter.sdk.android.core.q */
public class C1514q extends C1469k<TwitterAuthToken> {
    @SerializedName("user_name")
    /* renamed from: a */
    private final String f7120a;

    public C1514q(TwitterAuthToken twitterAuthToken, long j, String str) {
        super(twitterAuthToken, j);
        if (twitterAuthToken == null) {
            throw new IllegalArgumentException("AuthToken must not be null.");
        }
        this.f7120a = str;
    }

    /* renamed from: a */
    public String m8311a() {
        return this.f7120a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        C1514q c1514q = (C1514q) obj;
        if (this.f7120a != null) {
            if (this.f7120a.equals(c1514q.f7120a)) {
                return true;
            }
        } else if (c1514q.f7120a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.f7120a != null ? this.f7120a.hashCode() : 0) + (super.hashCode() * 31);
    }
}
