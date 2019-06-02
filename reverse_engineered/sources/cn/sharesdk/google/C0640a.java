package cn.sharesdk.google;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import ch.qos.logback.core.joran.action.Action;
import cn.sharesdk.framework.C0565b;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.C0562b;
import cn.sharesdk.framework.authorize.C0584e;
import cn.sharesdk.framework.p010a.C0574a;
import com.alipay.sdk.packet.C0861d;
import com.google.android.gms.common.Scopes;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: GoogleHelper */
/* renamed from: cn.sharesdk.google.a */
public class C0640a extends C0565b {
    /* renamed from: b */
    private static C0640a f1483b;
    /* renamed from: c */
    private C0574a f1484c = C0574a.m1988a();
    /* renamed from: d */
    private C0652g f1485d = new C0652g();
    /* renamed from: e */
    private String f1486e;
    /* renamed from: f */
    private String f1487f;
    /* renamed from: g */
    private String f1488g;
    /* renamed from: h */
    private String[] f1489h = new String[]{Scopes.PLUS_ME, Scopes.PLUS_LOGIN};

    /* renamed from: a */
    public static synchronized C0640a m2397a(Platform platform) {
        C0640a c0640a;
        synchronized (C0640a.class) {
            if (f1483b == null) {
                f1483b = new C0640a(platform);
            }
            c0640a = f1483b;
        }
        return c0640a;
    }

    private C0640a(Platform platform) {
        super(platform);
    }

    /* renamed from: a */
    public void m2401a(String str) {
        this.f1486e = str;
    }

    /* renamed from: b */
    public void m2407b(String str) {
        this.f1487f = str;
    }

    /* renamed from: a */
    public void m2404a(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            this.f1489h = strArr;
        }
    }

    public String getAuthorizeUrl() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("response_type", "code"));
        arrayList.add(new KVPair("client_id", this.f1486e));
        arrayList.add(new KVPair("redirect_uri", this.f1487f));
        if (this.f1489h != null && this.f1489h.length > 0) {
            arrayList.add(new KVPair(Action.SCOPE_ATTRIBUTE, TextUtils.join(" ", this.f1489h)));
        }
        return "https://accounts.google.com/o/oauth2/v2/auth?" + C4275R.encodeUrl(arrayList);
    }

    public C0562b getAuthorizeWebviewClient(C0584e c0584e) {
        return new C0643b(c0584e);
    }

    public String getRedirectUri() {
        return this.f1487f;
    }

    /* renamed from: a */
    public void m2400a(AuthorizeListener authorizeListener) {
        m1950b(authorizeListener);
    }

    /* renamed from: a */
    public String m2398a(Context context, String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("code", str));
        arrayList.add(new KVPair("client_id", this.f1486e));
        arrayList.add(new KVPair("redirect_uri", this.f1487f));
        arrayList.add(new KVPair("grant_type", "authorization_code"));
        return this.f1484c.m1997b("https://www.googleapis.com/oauth2/v4/token", arrayList, "/oauth2/v4/token", m1951c());
    }

    /* renamed from: c */
    public void m2408c(String str) {
        this.f1488g = str;
    }

    /* renamed from: d */
    public HashMap<String, Object> m2409d(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        if (this.f1488g != null) {
            arrayList.add(new KVPair("access_token", this.f1488g));
        }
        String a = this.f1484c.m1993a("https://www.googleapis.com/plus/v1/people/" + str, arrayList, "/plus/v1/people/", m1951c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    /* renamed from: e */
    public HashMap<String, Object> m2410e(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        if (this.f1488g != null) {
            arrayList.add(new KVPair("access_token", this.f1488g));
        }
        String a = this.f1484c.m1993a("https://www.googleapis.com/plus/v1/people/" + str + "/people/visible", arrayList, "/people/visible", m1951c());
        if (a != null) {
            return new Hashon().fromJson(a);
        }
        return null;
    }

    /* renamed from: a */
    public void m2403a(String str, String str2, String str3, PlatformActionListener platformActionListener) throws Throwable {
        if (C0648c.m2423a(this.a.getContext()) != 0) {
            throw new GooglePlusClientNotExistException();
        }
        Intent intent = new Intent();
        intent.putExtra(DirectionsCriteria.INSTRUCTIONS_TEXT, str);
        intent.putExtra("imageUrl", str2);
        intent.putExtra("imagePath", str3);
        intent.putExtra(C0861d.f2143o, 1);
        this.f1485d.m2490a(this.a, platformActionListener, null);
        this.f1485d.show(this.a.getContext(), intent);
    }

    /* renamed from: a */
    public void m2402a(String str, String str2, PlatformActionListener platformActionListener) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://plus.google.com/share?");
            stringBuilder.append("text=").append(Data.urlEncode(str, "utf-8"));
            if (!TextUtils.isEmpty(str2)) {
                stringBuilder.append("&url=").append(Data.urlEncode(str2, "utf-8"));
            }
            C0656h c0656h = new C0656h();
            c0656h.m2495a(stringBuilder.toString());
            c0656h.m2494a(platformActionListener);
            c0656h.show(this.a.getContext(), null);
        } catch (Throwable th) {
            if (platformActionListener != null) {
                platformActionListener.onError(this.a, 9, th);
            }
        }
    }

    /* renamed from: a */
    public void m2405a(String[] strArr, PlatformActionListener platformActionListener, PlatformDb platformDb) {
        Intent intent = new Intent();
        intent.putExtra(C0861d.f2143o, 0);
        this.f1485d.m2490a(this.a, platformActionListener, platformDb);
        this.f1485d.show(this.a.getContext(), intent);
    }

    /* renamed from: a */
    public void m2399a() {
        Intent intent = new Intent();
        intent.putExtra(C0861d.f2143o, 2);
        this.f1485d.show(this.a.getContext(), intent);
    }

    /* renamed from: b */
    public void m2406b() {
        if (this.f1485d != null) {
            this.f1485d.finish();
        }
    }
}
