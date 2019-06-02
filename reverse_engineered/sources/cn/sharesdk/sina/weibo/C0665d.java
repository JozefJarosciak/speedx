package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import ch.qos.logback.core.joran.action.Action;
import cn.sharesdk.framework.C0565b;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.C0558d;
import cn.sharesdk.framework.authorize.C0562b;
import cn.sharesdk.framework.authorize.C0579c;
import cn.sharesdk.framework.authorize.C0584e;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.p010a.C0574a;
import cn.sharesdk.framework.utils.C0621d;
import com.baidu.mapapi.SDKInitializer;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: Weibo */
/* renamed from: cn.sharesdk.sina.weibo.d */
public class C0665d extends C0565b {
    /* renamed from: b */
    private static C0665d f1566b;
    /* renamed from: c */
    private String f1567c;
    /* renamed from: d */
    private String f1568d;
    /* renamed from: e */
    private String f1569e;
    /* renamed from: f */
    private String f1570f;
    /* renamed from: g */
    private String[] f1571g = new String[]{"follow_app_official_microblog"};
    /* renamed from: h */
    private C0574a f1572h = C0574a.m1988a();
    /* renamed from: i */
    private Context f1573i;

    /* renamed from: a */
    public static synchronized C0665d m2538a(Platform platform) {
        C0665d c0665d;
        synchronized (C0665d.class) {
            if (f1566b == null) {
                f1566b = new C0665d(platform);
            }
            c0665d = f1566b;
        }
        return c0665d;
    }

    private C0665d(Platform platform) {
        super(platform);
        this.f1573i = platform.getContext();
    }

    /* renamed from: a */
    public void m2553a(String str, String str2) {
        this.f1567c = str;
        this.f1568d = str2;
    }

    /* renamed from: a */
    public void m2552a(String str) {
        this.f1569e = str;
    }

    /* renamed from: a */
    public void m2554a(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            this.f1571g = strArr;
        }
    }

    public C0562b getAuthorizeWebviewClient(C0584e c0584e) {
        return new C0661b(c0584e);
    }

    public C0558d getSSOProcessor(C0579c c0579c) {
        C0558d c0662c = new C0662c(c0579c);
        c0662c.m1937a(32973);
        c0662c.m2536a(this.f1567c, this.f1569e, this.f1571g);
        return c0662c;
    }

    /* renamed from: a */
    public void m2551a(final AuthorizeListener authorizeListener, boolean z) {
        if (z) {
            m1950b(authorizeListener);
        } else {
            m1949a(new SSOListener(this) {
                /* renamed from: b */
                final /* synthetic */ C0665d f1562b;

                public void onFailed(Throwable th) {
                    C0621d.m2279a().d(th);
                    this.f1562b.m1950b(authorizeListener);
                }

                public void onComplete(Bundle bundle) {
                    try {
                        C4275R.parseLong(bundle.getString("expires_in"));
                        authorizeListener.onComplete(bundle);
                    } catch (Throwable th) {
                        onFailed(th);
                    }
                }

                public void onCancel() {
                    authorizeListener.onCancel();
                }
            });
        }
    }

    public String getAuthorizeUrl() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("client_id", this.f1567c));
        arrayList.add(new KVPair("response_type", "code"));
        arrayList.add(new KVPair("redirect_uri", this.f1569e));
        if (this.f1571g != null && this.f1571g.length > 0) {
            arrayList.add(new KVPair(Action.SCOPE_ATTRIBUTE, TextUtils.join(",", this.f1571g)));
        }
        arrayList.add(new KVPair("display", "mobile"));
        String str = "https://open.weibo.cn/oauth2/authorize?" + C4275R.encodeUrl(arrayList);
        ShareSDK.logApiEvent("/oauth2/authorize", m1951c());
        return str;
    }

    public String getRedirectUri() {
        return TextUtils.isEmpty(this.f1569e) ? "https://api.weibo.com/oauth2/default.html" : this.f1569e;
    }

    /* renamed from: a */
    public String m2546a(Context context, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("client_id", this.f1567c));
        arrayList.add(new KVPair("client_secret", this.f1568d));
        arrayList.add(new KVPair("redirect_uri", this.f1569e));
        arrayList.add(new KVPair("grant_type", "authorization_code"));
        arrayList.add(new KVPair("code", str));
        String b = this.f1572h.m1997b("https://api.weibo.com/oauth2/access_token", arrayList, "/oauth2/access_token", m1951c());
        ShareSDK.logApiEvent("/oauth2/access_token", m1951c());
        return b;
    }

    /* renamed from: a */
    public boolean m2555a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("client_id", this.f1567c));
        arrayList.add(new KVPair("client_secret", this.f1568d));
        arrayList.add(new KVPair("redirect_uri", this.f1569e));
        arrayList.add(new KVPair("grant_type", "refresh_token"));
        arrayList.add(new KVPair("refresh_token", this.a.getDb().get("refresh_token")));
        try {
            String b = this.f1572h.m1997b("https://api.weibo.com/oauth2/access_token", arrayList, "/oauth2/access_token", m1951c());
            if (TextUtils.isEmpty(b) || b.contains("error") || b.contains(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE)) {
                return false;
            }
            m2545e(b);
            return true;
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return false;
        }
    }

    /* renamed from: b */
    public void m2557b(String str) {
        this.f1570f = str;
    }

    /* renamed from: c */
    public HashMap<String, Object> m2560c(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, this.f1567c));
        if (this.f1570f != null) {
            arrayList.add(new KVPair("access_token", this.f1570f));
        }
        Object obj = 1;
        try {
            C4275R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        String a = this.f1572h.m1993a("https://api.weibo.com/2/users/show.json", arrayList, "/2/users/show.json", m1951c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    /* renamed from: b */
    public boolean m2558b() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage("com.sina.weibo");
        intent.setType("image/*");
        ResolveInfo resolveActivity = this.a.getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            intent = new Intent("android.intent.action.SEND");
            intent.setPackage("com.sina.weibog3");
            intent.setType("image/*");
            resolveActivity = this.a.getContext().getPackageManager().resolveActivity(intent, 0);
        }
        if (resolveActivity != null) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void m2550a(final ShareParams shareParams, final PlatformActionListener platformActionListener) {
        if (shareParams.getImageData() == null && TextUtils.isEmpty(shareParams.getImagePath()) && !TextUtils.isEmpty(shareParams.getImageUrl())) {
            try {
                File file = new File(BitmapHelper.downloadBitmap(this.f1573i, shareParams.getImageUrl()));
                if (file.exists()) {
                    shareParams.setImagePath(file.getAbsolutePath());
                }
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        Object text = shareParams.getText();
        if (!TextUtils.isEmpty(text)) {
            shareParams.setText(getPlatform().getShortLintk(text, false));
        }
        AuthorizeListener c06642 = new AuthorizeListener(this) {
            /* renamed from: c */
            final /* synthetic */ C0665d f1565c;

            public void onError(Throwable th) {
                if (platformActionListener != null) {
                    platformActionListener.onError(this.f1565c.a, 9, th);
                }
            }

            public void onComplete(Bundle bundle) {
                if (platformActionListener != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("ShareParams", shareParams);
                    platformActionListener.onComplete(this.f1565c.a, 9, hashMap);
                }
            }

            public void onCancel() {
                if (platformActionListener != null) {
                    platformActionListener.onCancel(this.f1565c.a, 9);
                }
            }
        };
        C0659a c0659a = new C0659a();
        c0659a.m2523a(this.f1567c);
        c0659a.m2521a(shareParams);
        c0659a.m2522a(c06642);
        c0659a.show(this.f1573i, null, true);
    }

    /* renamed from: a */
    public HashMap<String, Object> m2548a(String str, String str2, String str3, float f, float f2) throws Throwable {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            throw new Throwable("weibo content can not be null!");
        } else if (!TextUtils.isEmpty(str3)) {
            return m2543b(str3, str, f, f2);
        } else {
            if (TextUtils.isEmpty(str2)) {
                return m2539a(str, f, f2);
            }
            return m2540a(str, str2, f, f2);
        }
    }

    /* renamed from: a */
    private HashMap<String, Object> m2540a(String str, String str2, float f, float f2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, this.f1567c));
        arrayList.add(new KVPair("access_token", this.f1570f));
        arrayList.add(new KVPair("status", str));
        arrayList.add(new KVPair("long", String.valueOf(f)));
        arrayList.add(new KVPair(MapboxEvent.KEY_LATITUDE, String.valueOf(f2)));
        arrayList.add(new KVPair("url", str2));
        String b = this.f1572h.m1997b("https://api.weibo.com/2/statuses/upload_url_text.json", arrayList, "/2/statuses/upload_url_text.json", m1951c());
        if (b != null) {
            return new Hashon().fromJson(b);
        }
        return null;
    }

    /* renamed from: b */
    private HashMap<String, Object> m2543b(String str, String str2, float f, float f2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, this.f1567c));
        arrayList.add(new KVPair("access_token", this.f1570f));
        arrayList.add(new KVPair("status", str2));
        arrayList.add(new KVPair("long", String.valueOf(f)));
        arrayList.add(new KVPair(MapboxEvent.KEY_LATITUDE, String.valueOf(f2)));
        KVPair kVPair = new KVPair("pic", str);
        String a = this.f1572h.m1990a("https://api.weibo.com/2/statuses/upload.json", arrayList, kVPair, "/2/statuses/upload.json", m1951c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    /* renamed from: a */
    private HashMap<String, Object> m2539a(String str, float f, float f2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, this.f1567c));
        arrayList.add(new KVPair("access_token", this.f1570f));
        arrayList.add(new KVPair("status", str));
        arrayList.add(new KVPair("long", String.valueOf(f)));
        arrayList.add(new KVPair(MapboxEvent.KEY_LATITUDE, String.valueOf(f2)));
        String b = this.f1572h.m1997b("https://api.weibo.com/2/statuses/update.json", arrayList, "/2/statuses/update.json", m1951c());
        if (b != null) {
            return new Hashon().fromJson(b);
        }
        return null;
    }

    /* renamed from: d */
    public HashMap<String, Object> m2562d(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, this.f1567c));
        arrayList.add(new KVPair("access_token", this.f1570f));
        Object obj = 1;
        try {
            C4275R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        String b = this.f1572h.m1997b("https://api.weibo.com/2/friendships/create.json", arrayList, "/2/friendships/create.json", m1951c());
        if (b != null) {
            return new Hashon().fromJson(b);
        }
        return null;
    }

    /* renamed from: a */
    public HashMap<String, Object> m2547a(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, this.f1567c));
        Object obj = 1;
        try {
            C4275R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair("count", String.valueOf(i)));
        arrayList.add(new KVPair("page", String.valueOf(i2)));
        String a = this.f1572h.m1993a("https://api.weibo.com/2/statuses/user_timeline.json", arrayList, "/2/statuses/user_timeline.json", m1951c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    /* renamed from: b */
    public HashMap<String, Object> m2556b(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, this.f1567c));
        if (this.f1570f != null) {
            arrayList.add(new KVPair("access_token", this.f1570f));
        }
        Object obj = 1;
        try {
            C4275R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair("count", String.valueOf(i)));
        arrayList.add(new KVPair("cursor", String.valueOf(i2)));
        String a = this.f1572h.m1993a("https://api.weibo.com/2/friendships/friends.json", arrayList, "/2/friendships/friends.json", m1951c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    /* renamed from: c */
    public HashMap<String, Object> m2559c(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, this.f1567c));
        if (this.f1570f != null) {
            arrayList.add(new KVPair("access_token", this.f1570f));
        }
        Object obj = 1;
        try {
            C4275R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair("count", String.valueOf(i)));
        arrayList.add(new KVPair("page", String.valueOf(i2)));
        String a = this.f1572h.m1993a("https://api.weibo.com/2/friendships/friends/bilateral.json", arrayList, "/2/friendships/friends/bilateral.json", m1951c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    /* renamed from: d */
    public HashMap<String, Object> m2561d(int i, int i2, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, this.f1567c));
        if (this.f1570f != null) {
            arrayList.add(new KVPair("access_token", this.f1570f));
        }
        Object obj = 1;
        try {
            C4275R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair("count", String.valueOf(i)));
        arrayList.add(new KVPair("cursor", String.valueOf(i2)));
        String a = this.f1572h.m1993a("https://api.weibo.com/2/friendships/followers.json", arrayList, "/2/friendships/followers.json", m1951c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    /* renamed from: a */
    public HashMap<String, Object> m2549a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
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
        arrayList.add(new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, this.f1567c));
        if (this.f1570f != null) {
            arrayList.add(new KVPair("access_token", this.f1570f));
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
        try {
            if ("GET".equals(str2.toUpperCase())) {
                httpGet = new NetworkHelper().httpGet(str, arrayList, null, null);
            } else {
                if ("POST".equals(str2.toUpperCase())) {
                    httpGet = new NetworkHelper().httpPost(str, arrayList, kVPair, null, null);
                }
                httpGet = null;
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
        if (httpGet == null || httpGet.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(httpGet);
    }

    /* renamed from: e */
    private void m2545e(String str) {
        HashMap fromJson = new Hashon().fromJson(str);
        String valueOf = String.valueOf(fromJson.get("uid"));
        String valueOf2 = String.valueOf(fromJson.get("expires_in"));
        String valueOf3 = String.valueOf(fromJson.get("access_token"));
        String valueOf4 = String.valueOf(fromJson.get("refresh_token"));
        String valueOf5 = String.valueOf(fromJson.get("remind_in"));
        this.a.getDb().putUserId(valueOf);
        this.a.getDb().putExpiresIn(Long.valueOf(valueOf2).longValue());
        this.a.getDb().putToken(valueOf3);
        this.a.getDb().put("refresh_token", valueOf4);
        this.a.getDb().put("remind_in", valueOf5);
    }
}
