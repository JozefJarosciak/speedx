package cn.sharesdk.framework.utils;

import android.util.Base64;
import ch.qos.logback.core.CoreConstants;
import com.mob.tools.network.KVPair;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: Oauth1Signer */
/* renamed from: cn.sharesdk.framework.utils.a */
public class C0616a {
    /* renamed from: a */
    private C0615b f1395a = new C0615b();
    /* renamed from: b */
    private C0618b f1396b = new C0618b("-._~", false);

    /* compiled from: Oauth1Signer */
    /* renamed from: cn.sharesdk.framework.utils.a$a */
    public enum C0614a {
        HMAC_SHA1,
        PLAINTEXT
    }

    /* compiled from: Oauth1Signer */
    /* renamed from: cn.sharesdk.framework.utils.a$b */
    public static class C0615b {
        /* renamed from: a */
        public String f1390a;
        /* renamed from: b */
        public String f1391b;
        /* renamed from: c */
        public String f1392c;
        /* renamed from: d */
        public String f1393d;
        /* renamed from: e */
        public String f1394e;
    }

    /* renamed from: a */
    public void m2266a(String str, String str2, String str3) {
        this.f1395a.f1390a = str;
        this.f1395a.f1391b = str2;
        this.f1395a.f1394e = str3;
    }

    /* renamed from: a */
    public C0615b m2260a() {
        return this.f1395a;
    }

    /* renamed from: a */
    public ArrayList<KVPair<String>> m2262a(String str, ArrayList<KVPair<String>> arrayList) throws Throwable {
        return m2263a(str, (ArrayList) arrayList, C0614a.HMAC_SHA1);
    }

    /* renamed from: a */
    public ArrayList<KVPair<String>> m2263a(String str, ArrayList<KVPair<String>> arrayList, C0614a c0614a) throws Throwable {
        return m2258a(str, "POST", arrayList, c0614a);
    }

    /* renamed from: b */
    public ArrayList<KVPair<String>> m2267b(String str, ArrayList<KVPair<String>> arrayList) throws Throwable {
        return m2268b(str, arrayList, C0614a.HMAC_SHA1);
    }

    /* renamed from: b */
    public ArrayList<KVPair<String>> m2268b(String str, ArrayList<KVPair<String>> arrayList, C0614a c0614a) throws Throwable {
        return m2258a(str, "GET", arrayList, c0614a);
    }

    /* renamed from: c */
    public ArrayList<KVPair<String>> m2269c(String str, ArrayList<KVPair<String>> arrayList, C0614a c0614a) throws Throwable {
        return m2258a(str, "PUT", arrayList, c0614a);
    }

    /* renamed from: a */
    public void m2265a(String str, String str2) {
        this.f1395a.f1392c = str;
        this.f1395a.f1393d = str2;
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m2258a(String str, String str2, ArrayList<KVPair<String>> arrayList, C0614a c0614a) throws Throwable {
        Object trim;
        String str3 = null;
        long currentTimeMillis = System.currentTimeMillis();
        switch (c0614a) {
            case HMAC_SHA1:
                str3 = "HMAC-SHA1";
                Key secretKeySpec = new SecretKeySpec((m2261a(this.f1395a.f1391b) + '&' + m2261a(this.f1395a.f1393d)).getBytes("utf-8"), "HMAC-SHA1");
                Mac instance = Mac.getInstance("HMAC-SHA1");
                instance.init(secretKeySpec);
                trim = new String(Base64.encode(instance.doFinal((str2 + '&' + m2261a(str) + '&' + m2261a(m2259b(m2257a(currentTimeMillis, (ArrayList) arrayList, str3)))).getBytes("utf-8")), 0)).trim();
                break;
            case PLAINTEXT:
                str3 = "PLAINTEXT";
                trim = m2261a(this.f1395a.f1391b) + '&' + m2261a(this.f1395a.f1393d);
                break;
            default:
                trim = null;
                break;
        }
        ArrayList<KVPair<String>> a = m2256a(currentTimeMillis, str3);
        a.add(new KVPair("oauth_signature", trim));
        return a;
    }

    /* renamed from: a */
    public String m2261a(String str) {
        if (str == null) {
            return "";
        }
        return this.f1396b.escape(str);
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m2257a(long j, ArrayList<KVPair<String>> arrayList, String str) {
        Iterator it;
        int i = 0;
        HashMap hashMap = new HashMap();
        if (arrayList != null) {
            it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair kVPair = (KVPair) it.next();
                hashMap.put(m2261a(kVPair.name), m2261a((String) kVPair.value));
            }
        }
        ArrayList a = m2256a(j, str);
        if (a != null) {
            it = a.iterator();
            while (it.hasNext()) {
                kVPair = (KVPair) it.next();
                hashMap.put(m2261a(kVPair.name), m2261a((String) kVPair.value));
            }
        }
        String[] strArr = new String[hashMap.size()];
        int i2 = 0;
        for (Entry key : hashMap.entrySet()) {
            strArr[i2] = (String) key.getKey();
            i2++;
        }
        Arrays.sort(strArr);
        ArrayList<KVPair<String>> arrayList2 = new ArrayList();
        i2 = strArr.length;
        while (i < i2) {
            String str2 = strArr[i];
            arrayList2.add(new KVPair(str2, hashMap.get(str2)));
            i++;
        }
        return arrayList2;
    }

    /* renamed from: b */
    private String m2259b(ArrayList<KVPair<String>> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KVPair kVPair = (KVPair) it.next();
            if (i > 0) {
                stringBuilder.append('&');
            }
            stringBuilder.append(kVPair.name).append('=').append((String) kVPair.value);
            i++;
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m2256a(long j, String str) {
        ArrayList<KVPair<String>> arrayList = new ArrayList();
        arrayList.add(new KVPair("oauth_consumer_key", this.f1395a.f1390a));
        arrayList.add(new KVPair("oauth_signature_method", str));
        arrayList.add(new KVPair("oauth_timestamp", String.valueOf(j / 1000)));
        arrayList.add(new KVPair("oauth_nonce", String.valueOf(j)));
        arrayList.add(new KVPair("oauth_version", "1.0"));
        String str2 = this.f1395a.f1392c;
        if (str2 != null && str2.length() > 0) {
            arrayList.add(new KVPair("oauth_token", str2));
        }
        return arrayList;
    }

    /* renamed from: a */
    public ArrayList<KVPair<String>> m2264a(ArrayList<KVPair<String>> arrayList) {
        StringBuilder stringBuilder = new StringBuilder("OAuth ");
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KVPair kVPair = (KVPair) it.next();
            if (i > 0) {
                stringBuilder.append(CoreConstants.COMMA_CHAR);
            }
            stringBuilder.append(kVPair.name).append("=\"").append(m2261a((String) kVPair.value)).append("\"");
            i++;
        }
        ArrayList<KVPair<String>> arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("Authorization", stringBuilder.toString()));
        arrayList2.add(new KVPair("Content-Type", "application/x-www-form-urlencoded"));
        return arrayList2;
    }
}
