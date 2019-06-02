package com.twitter.sdk.android.core.internal.oauth;

import android.net.Uri;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.C4611f;
import io.fabric.sdk.android.services.network.C4929h;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;
import javax.net.ssl.SSLSocketFactory;
import retrofit.client.Response;

public class OAuth1aService extends C4616e {
    /* renamed from: a */
    OAuth1aService$OAuthApi f16277a = ((OAuth1aService$OAuthApi) m18263f().create(OAuth1aService$OAuthApi.class));

    public OAuth1aService(C4655n c4655n, SSLSocketFactory sSLSocketFactory, C4611f c4611f) {
        super(c4655n, sSLSocketFactory, c4611f);
    }

    /* renamed from: a */
    public void m18268a(C4580e<OAuthResponse> c4580e) {
        TwitterAuthConfig b = m18260c().m18387b();
        this.f16277a.getTempToken(new C4621c().m18286a(b, null, m18266a(b), "POST", m18265a(), null), "", m18270b(c4580e));
    }

    /* renamed from: a */
    String m18265a() {
        return m18261d().m18249a() + "/oauth/request_token";
    }

    /* renamed from: a */
    public String m18266a(TwitterAuthConfig twitterAuthConfig) {
        return Uri.parse("twittersdk://callback").buildUpon().appendQueryParameter(MapboxEvent.ATTRIBUTE_VERSION, m18260c().m18388c()).appendQueryParameter("app", twitterAuthConfig.a()).build().toString();
    }

    /* renamed from: a */
    public void m18269a(C4580e<OAuthResponse> c4580e, TwitterAuthToken twitterAuthToken, String str) {
        TwitterAuthToken twitterAuthToken2 = twitterAuthToken;
        this.f16277a.getAccessToken(new C4621c().m18286a(m18260c().m18387b(), twitterAuthToken2, null, "POST", m18271b(), null), str, "", m18270b(c4580e));
    }

    /* renamed from: b */
    String m18271b() {
        return m18261d().m18249a() + "/oauth/access_token";
    }

    /* renamed from: a */
    public String m18267a(TwitterAuthToken twitterAuthToken) {
        return m18261d().m18248a("oauth", "authorize").appendQueryParameter("oauth_token", twitterAuthToken.f7051b).build().toString();
    }

    /* renamed from: a */
    public static OAuthResponse m18264a(String str) {
        long parseLong;
        TreeMap a = C4929h.m19367a(str, false);
        String str2 = (String) a.get("oauth_token");
        String str3 = (String) a.get("oauth_token_secret");
        String str4 = (String) a.get("screen_name");
        if (a.containsKey("user_id")) {
            parseLong = Long.parseLong((String) a.get("user_id"));
        } else {
            parseLong = 0;
        }
        if (str2 == null || str3 == null) {
            return null;
        }
        return new OAuthResponse(new TwitterAuthToken(str2, str3), str4, parseLong);
    }

    /* renamed from: b */
    C4580e<Response> m18270b(final C4580e<OAuthResponse> c4580e) {
        return new C4580e<Response>(this) {
            /* renamed from: b */
            final /* synthetic */ OAuth1aService f16272b;

            /* renamed from: a */
            public void mo6128a(C4645j<Response> c4645j) {
                Throwable th;
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader;
                try {
                    String readLine;
                    bufferedReader = new BufferedReader(new InputStreamReader(((Response) c4645j.f16364a).getBody().in()));
                    while (true) {
                        try {
                            readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuilder.append(readLine);
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    readLine = stringBuilder.toString();
                    OAuthResponse a = OAuth1aService.m18264a(readLine);
                    if (a == null) {
                        c4580e.mo6127a(new TwitterAuthException("Failed to parse auth response: " + readLine));
                    } else {
                        c4580e.mo6128a(new C4645j(a, null));
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th4) {
                            c4580e.mo6127a(new TwitterAuthException(th4.getMessage(), th4));
                            return;
                        }
                    }
                    throw th4;
                }
            }

            /* renamed from: a */
            public void mo6127a(TwitterException twitterException) {
                c4580e.mo6127a(twitterException);
            }
        };
    }
}
