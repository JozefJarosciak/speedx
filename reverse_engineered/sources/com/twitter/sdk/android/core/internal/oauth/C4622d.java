package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.network.C4929h;
import io.fabric.sdk.android.services.network.HttpRequest.C4918a;
import java.net.URI;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: OAuth1aParameters */
/* renamed from: com.twitter.sdk.android.core.internal.oauth.d */
class C4622d {
    /* renamed from: a */
    private static final SecureRandom f16286a = new SecureRandom();
    /* renamed from: b */
    private final TwitterAuthConfig f16287b;
    /* renamed from: c */
    private final TwitterAuthToken f16288c;
    /* renamed from: d */
    private final String f16289d;
    /* renamed from: e */
    private final String f16290e;
    /* renamed from: f */
    private final String f16291f;
    /* renamed from: g */
    private final Map<String, String> f16292g;

    public C4622d(TwitterAuthConfig twitterAuthConfig, TwitterAuthToken twitterAuthToken, String str, String str2, String str3, Map<String, String> map) {
        this.f16287b = twitterAuthConfig;
        this.f16288c = twitterAuthToken;
        this.f16289d = str;
        this.f16290e = str2;
        this.f16291f = str3;
        this.f16292g = map;
    }

    /* renamed from: a */
    public String m18293a() {
        String b = m18290b();
        String c = m18291c();
        return m18296a(b, c, m18294a(m18295a(b, c)));
    }

    /* renamed from: b */
    private String m18290b() {
        return String.valueOf(System.nanoTime()) + String.valueOf(Math.abs(f16286a.nextLong()));
    }

    /* renamed from: c */
    private String m18291c() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /* renamed from: a */
    String m18295a(String str, String str2) {
        URI create = URI.create(this.f16291f);
        TreeMap a = C4929h.m19368a(create, true);
        if (this.f16292g != null) {
            a.putAll(this.f16292g);
        }
        if (this.f16289d != null) {
            a.put("oauth_callback", this.f16289d);
        }
        a.put("oauth_consumer_key", this.f16287b.a());
        a.put("oauth_nonce", str);
        a.put("oauth_signature_method", "HMAC-SHA1");
        a.put("oauth_timestamp", str2);
        if (!(this.f16288c == null || this.f16288c.f7051b == null)) {
            a.put("oauth_token", this.f16288c.f7051b);
        }
        a.put("oauth_version", "1.0");
        return this.f16290e.toUpperCase(Locale.ENGLISH) + '&' + C4929h.m19370c(create.getScheme() + "://" + create.getHost() + create.getPath()) + '&' + m18288a(a);
    }

    /* renamed from: a */
    private String m18288a(TreeMap<String, String> treeMap) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = treeMap.size();
        int i = 0;
        for (Entry entry : treeMap.entrySet()) {
            stringBuilder.append(C4929h.m19370c(C4929h.m19370c((String) entry.getKey()))).append("%3D").append(C4929h.m19370c(C4929h.m19370c((String) entry.getValue())));
            int i2 = i + 1;
            if (i2 < size) {
                stringBuilder.append("%26");
            }
            i = i2;
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    String m18294a(String str) {
        try {
            String d = m18292d();
            byte[] bytes = str.getBytes("UTF8");
            Key secretKeySpec = new SecretKeySpec(d.getBytes("UTF8"), "HmacSHA1");
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            bytes = instance.doFinal(bytes);
            return new String(C4918a.m19292b(bytes, 0, bytes.length), "UTF8");
        } catch (Throwable e) {
            C1520c.h().mo6222d("Twitter", "Failed to calculate signature", e);
            return "";
        } catch (Throwable e2) {
            C1520c.h().mo6222d("Twitter", "Failed to calculate signature", e2);
            return "";
        } catch (Throwable e22) {
            C1520c.h().mo6222d("Twitter", "Failed to calculate signature", e22);
            return "";
        }
    }

    /* renamed from: d */
    private String m18292d() {
        return C4929h.m19366a(this.f16287b.b()) + '&' + C4929h.m19366a(this.f16288c != null ? this.f16288c.f7052c : null);
    }

    /* renamed from: a */
    String m18296a(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder("OAuth");
        m18289a(stringBuilder, "oauth_callback", this.f16289d);
        m18289a(stringBuilder, "oauth_consumer_key", this.f16287b.a());
        m18289a(stringBuilder, "oauth_nonce", str);
        m18289a(stringBuilder, "oauth_signature", str3);
        m18289a(stringBuilder, "oauth_signature_method", "HMAC-SHA1");
        m18289a(stringBuilder, "oauth_timestamp", str2);
        m18289a(stringBuilder, "oauth_token", this.f16288c != null ? this.f16288c.f7051b : null);
        m18289a(stringBuilder, "oauth_version", "1.0");
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    /* renamed from: a */
    private void m18289a(StringBuilder stringBuilder, String str, String str2) {
        if (str2 != null) {
            stringBuilder.append(' ').append(C4929h.m19370c(str)).append("=\"").append(C4929h.m19370c(str2)).append("\",");
        }
    }
}
