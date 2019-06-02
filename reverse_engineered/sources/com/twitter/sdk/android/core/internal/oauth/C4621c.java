package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import java.util.Map;

/* compiled from: OAuth1aHeaders */
/* renamed from: com.twitter.sdk.android.core.internal.oauth.c */
public class C4621c {
    /* renamed from: a */
    public String m18286a(TwitterAuthConfig twitterAuthConfig, TwitterAuthToken twitterAuthToken, String str, String str2, String str3, Map<String, String> map) {
        return m18287b(twitterAuthConfig, twitterAuthToken, str, str2, str3, map).m18293a();
    }

    /* renamed from: b */
    C4622d m18287b(TwitterAuthConfig twitterAuthConfig, TwitterAuthToken twitterAuthToken, String str, String str2, String str3, Map<String, String> map) {
        return new C4622d(twitterAuthConfig, twitterAuthToken, str, str2, str3, map);
    }
}
