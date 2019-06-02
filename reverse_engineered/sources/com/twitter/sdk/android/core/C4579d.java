package com.twitter.sdk.android.core;

import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.twitter.sdk.android.core.internal.C4612g;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import javax.net.ssl.SSLSocketFactory;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.FormUrlEncodedTypedOutput;
import retrofit.mime.TypedOutput;

/* compiled from: AuthenticatedClient */
/* renamed from: com.twitter.sdk.android.core.d */
public class C4579d extends C4578f {
    /* renamed from: c */
    private final C1469k f16207c;
    /* renamed from: d */
    private final TwitterAuthConfig f16208d;

    public C4579d(TwitterAuthConfig twitterAuthConfig, C1469k c1469k, SSLSocketFactory sSLSocketFactory) {
        super(sSLSocketFactory);
        this.f16208d = twitterAuthConfig;
        this.f16207c = c1469k;
    }

    public Response execute(Request request) throws IOException {
        return this.a.execute(new Request(request.getMethod(), request.getUrl(), m18148a(request), request.getBody()));
    }

    /* renamed from: a */
    protected List<Header> m18148a(Request request) throws IOException {
        C4612g c4612g = new C4612g(request.getMethod(), request.getUrl(), this.f16208d, this.f16207c, null, m18150b(request));
        List<Header> arrayList = new ArrayList(request.getHeaders());
        for (Entry entry : c4612g.m18250a().entrySet()) {
            arrayList.add(new Header((String) entry.getKey(), (String) entry.getValue()));
        }
        return arrayList;
    }

    /* renamed from: b */
    protected Map<String, String> m18150b(Request request) throws IOException {
        Map<String, String> treeMap = new TreeMap();
        if ("POST".equals(request.getMethod().toUpperCase(Locale.US))) {
            TypedOutput body = request.getBody();
            if (body instanceof FormUrlEncodedTypedOutput) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                body.writeTo(byteArrayOutputStream);
                String byteArrayOutputStream2 = byteArrayOutputStream.toString("UTF-8");
                if (byteArrayOutputStream2.length() > 0) {
                    treeMap.putAll(m18149a(byteArrayOutputStream2));
                }
            }
        }
        return treeMap;
    }

    /* renamed from: a */
    protected Map<String, String> m18149a(String str) {
        Map hashMap = new HashMap();
        Scanner useDelimiter = new Scanner(str).useDelimiter(C0869a.f2161b);
        while (useDelimiter.hasNext()) {
            String[] split = useDelimiter.next().split(SimpleComparison.EQUAL_TO_OPERATION);
            if (split.length == 0 || split.length > 2) {
                throw new IllegalArgumentException("bad parameter");
            }
            String a = m18147a(split[0], "UTF-8");
            Object obj = "";
            if (split.length == 2) {
                obj = m18147a(split[1], "UTF-8");
            }
            hashMap.put(a, obj);
        }
        return Collections.unmodifiableMap(hashMap);
    }

    /* renamed from: a */
    protected String m18147a(String str, String str2) {
        try {
            return URLDecoder.decode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("bad parameter encoding");
        }
    }
}
