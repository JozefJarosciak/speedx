package cn.sharesdk.twitter;

import android.text.TextUtils;
import ch.qos.logback.core.CoreConstants;
import cn.sharesdk.framework.C0565b;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.C0562b;
import cn.sharesdk.framework.authorize.C0584e;
import cn.sharesdk.framework.p010a.C0574a;
import cn.sharesdk.framework.utils.C0616a;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.sys.C0869a;
import com.avos.avoscloud.AVStatus;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.http.HttpHost;

/* compiled from: TwitterHelper */
/* renamed from: cn.sharesdk.twitter.b */
public class C0686b extends C0565b {
    /* renamed from: b */
    private static C0686b f1635b;
    /* renamed from: c */
    private C0616a f1636c = new C0616a();
    /* renamed from: d */
    private C0574a f1637d = C0574a.m1988a();

    /* renamed from: a */
    public static C0686b m2647a(Platform platform) {
        if (f1635b == null) {
            f1635b = new C0686b(platform);
        }
        return f1635b;
    }

    private C0686b(Platform platform) {
        super(platform);
    }

    /* renamed from: a */
    public void m2653a(String str, String str2, String str3) {
        this.f1636c.m2266a(str, str2, str3);
    }

    /* renamed from: a */
    public void m2652a(String str, String str2) {
        this.f1636c.m2265a(str, str2);
    }

    public String getRedirectUri() {
        return this.f1636c.m2260a().f1394e;
    }

    public String getAuthorizeUrl() {
        try {
            String str = "https://api.twitter.com/oauth/request_token";
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("oauth_callback", getRedirectUri()));
            m2652a(null, null);
            String a = this.f1637d.m1992a(str, arrayList, null, this.f1636c.m2264a(this.f1636c.m2262a(str, arrayList)), "/oauth/request_token", m1951c());
            if (a == null) {
                return null;
            }
            String[] split = a.split(C0869a.f2161b);
            HashMap hashMap = new HashMap();
            for (String str2 : split) {
                if (str2 != null) {
                    String[] split2 = str2.split(SimpleComparison.EQUAL_TO_OPERATION);
                    if (split2.length >= 2) {
                        hashMap.put(split2[0], split2[1]);
                    }
                }
            }
            if (hashMap.containsKey("oauth_token")) {
                a = (String) hashMap.get("oauth_token");
                m2652a(a, (String) hashMap.get("oauth_token_secret"));
                ShareSDK.logApiEvent("/oauth/authorize", m1951c());
                return "https://api.twitter.com/oauth/authorize?oauth_token=" + a;
            }
            return null;
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
    }

    public C0562b getAuthorizeWebviewClient(C0584e c0584e) {
        return new C0685a(c0584e);
    }

    /* renamed from: a */
    public void m2651a(AuthorizeListener authorizeListener) {
        m1950b(authorizeListener);
    }

    /* renamed from: a */
    public String m2648a(String str) {
        try {
            String str2 = "https://api.twitter.com/oauth/access_token";
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("oauth_verifier", str));
            return this.f1637d.m1992a(str2, arrayList, null, this.f1636c.m2264a(this.f1636c.m2262a(str2, arrayList)), "/oauth/access_token", m1951c());
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return null;
        }
    }

    /* renamed from: b */
    public HashMap<String, Object> m2654b(String str) throws Throwable {
        String str2 = "https://api.twitter.com/1.1/users/show.json";
        ArrayList arrayList = new ArrayList();
        long j = 0;
        try {
            j = C4275R.parseLong(str);
        } catch (Throwable th) {
            str = null;
        }
        arrayList.add(new KVPair("user_id", str == null ? this.a.getDb().getUserId() : String.valueOf(j)));
        String a = this.f1637d.m1994a(str2, arrayList, this.f1636c.m2264a(this.f1636c.m2267b(str2, arrayList)), null, "/1.1/users/show.json", m1951c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    /* renamed from: b */
    public HashMap<String, Object> m2655b(String str, String str2) throws Throwable {
        String str3 = "https://api.twitter.com/1.1/friends/list.json";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("nextCursor", str2));
        Object obj = 1;
        try {
            C4275R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("user_id", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        String a = this.f1637d.m1994a(str3, arrayList, this.f1636c.m2264a(this.f1636c.m2267b(str3, arrayList)), null, "/1.1/friends/list.json", m1951c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    /* renamed from: c */
    public HashMap<String, Object> m2657c(String str, String str2) throws Throwable {
        String str3 = "https://api.twitter.com/1.1/followers/list.json";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("nextCursor", str2));
        Object obj = 1;
        try {
            C4275R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("user_id", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        String a = this.f1637d.m1994a(str3, arrayList, this.f1636c.m2264a(this.f1636c.m2267b(str3, arrayList)), null, "/1.1/followers/list.json", m1951c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    /* renamed from: c */
    public HashMap<String, Object> m2656c(String str) throws Throwable {
        return m2658d(str, null);
    }

    /* renamed from: d */
    public HashMap<String, Object> m2658d(String str, String str2) throws Throwable {
        String str3 = "https://api.twitter.com/1.1/statuses/update.json";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("status", str));
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(new KVPair("media_ids", str2));
        }
        String a = this.f1637d.m1992a(str3, arrayList, null, this.f1636c.m2264a(this.f1636c.m2262a(str3, arrayList)), "/1.1/statuses/update.json", m1951c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    /* renamed from: a */
    public HashMap<String, Object> m2650a(String str, String[] strArr) throws Throwable {
        int i = 0;
        String str2 = "https://upload.twitter.com/1.1/media/upload.json";
        ArrayList arrayList = new ArrayList();
        ArrayList a = this.f1636c.m2264a(this.f1636c.m2262a(str2, arrayList));
        a.remove(1);
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < strArr.length && (arrayList2 == null || arrayList2.size() <= 3); i2++) {
            try {
                Object obj = strArr[i2];
                if (obj.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    obj = BitmapHelper.downloadBitmap(this.a.getContext(), obj);
                } else {
                    if (!TextUtils.isEmpty(obj)) {
                        if (!new File(obj).exists()) {
                        }
                    }
                }
                String a2 = this.f1637d.m1992a(str2, arrayList, new KVPair("media", obj), a, "/1.1/media/upload.json", m1951c());
                stringBuilder.append(strArr[i2]).append(": ").append(a2).append("\n");
                if (a2 != null && a2.length() > 0) {
                    arrayList2.add(new Hashon().fromJson(a2));
                }
            } catch (Exception e) {
                C0621d.m2279a().d(stringBuilder.toString(), new Object[0]);
            }
        }
        stringBuilder.setLength(0);
        while (i < arrayList2.size()) {
            if (((HashMap) arrayList2.get(i)).containsKey(AVStatus.IMAGE_TAG)) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(CoreConstants.COMMA_CHAR);
                }
                stringBuilder.append(String.valueOf(((HashMap) arrayList2.get(i)).get("media_id")));
            }
            i++;
        }
        return m2658d(str, stringBuilder.toString());
    }

    /* renamed from: e */
    public HashMap<String, Object> m2659e(String str, String str2) throws Throwable {
        String str3 = "https://api.twitter.com/1.1/statuses/update_with_media.json";
        ArrayList arrayList = new ArrayList();
        ArrayList a = this.f1636c.m2264a(this.f1636c.m2262a(str3, arrayList));
        a.remove(1);
        arrayList.add(new KVPair("status", str));
        String a2 = this.f1637d.m1992a(str3, arrayList, new KVPair("media[]", str2), a, "/1.1/statuses/update_with_media.json", m1951c());
        if (a2 == null || a2.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a2);
    }

    /* renamed from: a */
    public HashMap<String, Object> m2649a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) throws Throwable {
        if (str2 == null) {
            return null;
        }
        KVPair kVPair;
        String httpGet;
        ArrayList arrayList = new ArrayList();
        if (hashMap != null && hashMap.size() > 0) {
            for (Entry entry : hashMap.entrySet()) {
                arrayList.add(new KVPair((String) entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        if (hashMap2 == null || hashMap2.size() <= 0) {
            kVPair = null;
        } else {
            HashMap<String, Object> hashMap3 = null;
            for (Entry entry2 : hashMap2.entrySet()) {
                Object kVPair2 = new KVPair((String) entry2.getKey(), entry2.getValue());
            }
            kVPair = hashMap3;
        }
        if ("GET".equals(str2.toUpperCase())) {
            httpGet = this.f1637d.httpGet(str, arrayList, this.f1636c.m2264a(this.f1636c.m2267b(str, arrayList)), null);
        } else if ("POST".equals(str2.toUpperCase())) {
            ArrayList a;
            if (hashMap2 == null || hashMap2.size() <= 0) {
                a = this.f1636c.m2264a(this.f1636c.m2262a(str, arrayList));
            } else {
                a = this.f1636c.m2264a(this.f1636c.m2262a(str, new ArrayList()));
                a.remove(1);
            }
            httpGet = this.f1637d.httpPost(str, arrayList, kVPair, a, null);
        } else {
            httpGet = null;
        }
        if (httpGet == null || httpGet.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(httpGet);
    }
}
