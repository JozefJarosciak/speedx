package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.p010a.C0574a;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.cons.C0844a;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;
import io.rong.imlib.statistics.UserData;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: WXAuthHelper */
/* renamed from: cn.sharesdk.wechat.utils.g */
public class C0701g {
    /* renamed from: a */
    private String f1680a;
    /* renamed from: b */
    private String f1681b;
    /* renamed from: c */
    private C0574a f1682c = C0574a.m1988a();
    /* renamed from: d */
    private Platform f1683d;
    /* renamed from: e */
    private int f1684e;

    public C0701g(Platform platform, int i) {
        this.f1683d = platform;
        this.f1684e = i;
    }

    /* renamed from: a */
    public void m2742a(String str, String str2) {
        this.f1680a = str;
        this.f1681b = str2;
    }

    /* renamed from: a */
    public void m2739a(Bundle bundle, AuthorizeListener authorizeListener) {
        String string = bundle.getString("_wxapi_sendauth_resp_url");
        if (!TextUtils.isEmpty(string)) {
            int indexOf = string.indexOf("://oauth?");
            if (indexOf >= 0) {
                string = string.substring(indexOf + 1);
            }
            try {
                m2741a(C4275R.urlToBundle(string).getString("code"), authorizeListener);
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
                if (authorizeListener != null) {
                    authorizeListener.onError(th);
                }
            }
        } else if (authorizeListener != null) {
            authorizeListener.onError(null);
        }
    }

    /* renamed from: a */
    public void m2741a(final String str, final AuthorizeListener authorizeListener) throws Throwable {
        C0621d.m2279a().d("getAuthorizeToken ==>> " + str, new Object[0]);
        new Thread(this) {
            /* renamed from: c */
            final /* synthetic */ C0701g f1677c;

            public void run() {
                try {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new KVPair("appid", this.f1677c.f1680a));
                    arrayList.add(new KVPair("secret", this.f1677c.f1681b));
                    arrayList.add(new KVPair("code", str));
                    arrayList.add(new KVPair("grant_type", "authorization_code"));
                    String a = this.f1677c.f1682c.m1993a("https://api.weixin.qq.com/sns/oauth2/access_token", arrayList, "/sns/oauth2/access_token", this.f1677c.f1684e);
                    if (TextUtils.isEmpty(a)) {
                        authorizeListener.onError(new Throwable("Authorize token is empty"));
                    } else if (!a.contains("errcode")) {
                        this.f1677c.m2734a(a);
                        authorizeListener.onComplete(null);
                    } else if (authorizeListener != null) {
                        authorizeListener.onError(new Throwable(a));
                    }
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                }
            }
        }.start();
    }

    /* renamed from: a */
    public boolean m2743a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appid", this.f1680a));
        arrayList.add(new KVPair("refresh_token", this.f1683d.getDb().get("refresh_token")));
        arrayList.add(new KVPair("grant_type", "refresh_token"));
        try {
            String a = this.f1682c.m1993a("https://api.weixin.qq.com/sns/oauth2/refresh_token", arrayList, "/sns/oauth2/refresh_token", this.f1684e);
            if (TextUtils.isEmpty(a) || a.contains("errcode")) {
                return false;
            }
            m2734a(a);
            return true;
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return false;
        }
    }

    /* renamed from: a */
    public void m2740a(final PlatformActionListener platformActionListener) throws Throwable {
        new Thread(this) {
            /* renamed from: b */
            final /* synthetic */ C0701g f1679b;

            public void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new KVPair("access_token", this.f1679b.f1683d.getDb().getToken()));
                arrayList.add(new KVPair("openid", this.f1679b.f1683d.getDb().get("openid")));
                String a = this.f1679b.f1682c.m1993a("https://api.weixin.qq.com/sns/userinfo", arrayList, "/sns/userinfo", this.f1679b.f1684e);
                if (!TextUtils.isEmpty(a)) {
                    C0621d.m2279a().d("getUserInfo ==>>" + a, new Object[0]);
                    HashMap fromJson = new Hashon().fromJson(a);
                    if (!fromJson.containsKey("errcode") || ((Integer) fromJson.get("errcode")).intValue() == 0) {
                        String valueOf;
                        String valueOf2;
                        int parseInt;
                        try {
                            valueOf = String.valueOf(fromJson.get("openid"));
                            valueOf2 = String.valueOf(fromJson.get("nickname"));
                            parseInt = C4275R.parseInt(String.valueOf(fromJson.get("sex")));
                        } catch (Throwable th) {
                            C0621d.m2279a().d(th);
                            return;
                        }
                        String valueOf3 = String.valueOf(fromJson.get("province"));
                        String valueOf4 = String.valueOf(fromJson.get("city"));
                        String valueOf5 = String.valueOf(fromJson.get(GeocodingCriteria.TYPE_COUNTRY));
                        String valueOf6 = String.valueOf(fromJson.get("headimgurl"));
                        String valueOf7 = String.valueOf(fromJson.get("unionid"));
                        this.f1679b.f1683d.getDb().put("nickname", valueOf2);
                        if (parseInt == 1) {
                            this.f1679b.f1683d.getDb().put(UserData.GENDER_KEY, "0");
                        } else if (parseInt == 2) {
                            this.f1679b.f1683d.getDb().put(UserData.GENDER_KEY, C0844a.f2048d);
                        } else {
                            this.f1679b.f1683d.getDb().put(UserData.GENDER_KEY, "2");
                        }
                        this.f1679b.f1683d.getDb().putUserId(valueOf);
                        this.f1679b.f1683d.getDb().put("icon", valueOf6);
                        this.f1679b.f1683d.getDb().put("province", valueOf3);
                        this.f1679b.f1683d.getDb().put("city", valueOf4);
                        this.f1679b.f1683d.getDb().put(GeocodingCriteria.TYPE_COUNTRY, valueOf5);
                        this.f1679b.f1683d.getDb().put("openid", valueOf);
                        this.f1679b.f1683d.getDb().put("unionid", valueOf7);
                        platformActionListener.onComplete(this.f1679b.f1683d, 8, fromJson);
                    } else if (platformActionListener != null) {
                        platformActionListener.onError(this.f1679b.f1683d, 8, new Throwable(new Hashon().fromHashMap(fromJson)));
                    }
                } else if (platformActionListener != null) {
                    platformActionListener.onError(this.f1679b.f1683d, 8, new Throwable());
                }
            }
        }.start();
    }

    /* renamed from: a */
    private void m2734a(String str) {
        C0621d.m2279a().d("wechat getAuthorizeToken ==>>" + str, new Object[0]);
        HashMap fromJson = new Hashon().fromJson(str);
        String valueOf = String.valueOf(fromJson.get("access_token"));
        String valueOf2 = String.valueOf(fromJson.get("refresh_token"));
        String valueOf3 = String.valueOf(fromJson.get("expires_in"));
        this.f1683d.getDb().put("openid", String.valueOf(fromJson.get("openid")));
        this.f1683d.getDb().putExpiresIn(Long.valueOf(valueOf3).longValue());
        this.f1683d.getDb().putToken(valueOf);
        this.f1683d.getDb().put("refresh_token", valueOf2);
    }
}
