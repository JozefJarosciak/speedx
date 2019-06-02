package cn.sharesdk.facebook;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.core.CoreConstants;
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
import com.alipay.sdk.cons.C0845b;
import com.avos.avoscloud.AVStatus;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: FbHelper */
/* renamed from: cn.sharesdk.facebook.d */
public class C0566d extends C0565b {
    /* renamed from: b */
    private static final String[] f1196b = new String[]{"user_about_me", "user_birthday", "user_photos", "publish_actions", "user_friends"};
    /* renamed from: c */
    private static C0566d f1197c;
    /* renamed from: d */
    private String f1198d;
    /* renamed from: e */
    private String f1199e;
    /* renamed from: f */
    private long f1200f;
    /* renamed from: g */
    private String f1201g;
    /* renamed from: h */
    private C0574a f1202h = C0574a.m1988a();
    /* renamed from: i */
    private String[] f1203i;
    /* renamed from: j */
    private String f1204j;

    /* renamed from: a */
    public static C0566d m1952a(Platform platform) {
        if (f1197c == null) {
            f1197c = new C0566d(platform);
        }
        return f1197c;
    }

    private C0566d(Platform platform) {
        super(platform);
    }

    /* renamed from: a */
    public void m1958a(String str) {
        this.f1201g = str;
    }

    /* renamed from: a */
    public void m1959a(String str, String str2) {
        this.f1199e = str;
        if (str2 != null && !str2.equals("0")) {
            try {
                this.f1200f = System.currentTimeMillis() + ((long) (C4275R.parseInt(str2) * 1000));
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
    }

    /* renamed from: a */
    public boolean m1961a() {
        return this.f1199e != null && (this.f1200f == 0 || System.currentTimeMillis() < this.f1200f);
    }

    public String getAuthorizeUrl() {
        int i = 0;
        String str = "https://www.facebook.com/dialog/oauth";
        Bundle bundle = new Bundle();
        bundle.putString("display", "touch");
        bundle.putString("redirect_uri", this.f1204j);
        bundle.putString("type", C0845b.f2061b);
        String[] strArr = this.f1203i == null ? f1196b : this.f1203i;
        StringBuilder stringBuilder = new StringBuilder();
        int length = strArr.length;
        int i2 = 0;
        while (i < length) {
            String str2 = strArr[i];
            if (i2 > 0) {
                stringBuilder.append(CoreConstants.COMMA_CHAR);
            }
            stringBuilder.append(str2);
            i2++;
            i++;
        }
        bundle.putString(Action.SCOPE_ATTRIBUTE, stringBuilder.toString());
        bundle.putString("client_id", this.f1201g);
        bundle.putString("response_type", "code");
        this.f1198d = str + CallerData.NA + C4275R.encodeUrl(bundle);
        ShareSDK.logApiEvent("/dialog/oauth", m1951c());
        return this.f1198d;
    }

    /* renamed from: a */
    public void m1960a(String[] strArr) {
        this.f1203i = strArr;
    }

    public C0562b getAuthorizeWebviewClient(C0584e c0584e) {
        return new C0563c(c0584e);
    }

    public String getRedirectUri() {
        return this.f1204j;
    }

    /* renamed from: b */
    public boolean m1964b() {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
        intent.putExtra("client_id", this.f1201g);
        if (this.f1203i != null && this.f1203i.length > 0) {
            intent.putExtra(Action.SCOPE_ATTRIBUTE, TextUtils.join(",", this.f1203i));
        }
        ResolveInfo resolveActivity = getPlatform().getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return false;
        }
        try {
            for (Signature toCharsString : getPlatform().getContext().getPackageManager().getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures) {
                if ("30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2".equals(toCharsString.toCharsString())) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public C0558d getSSOProcessor(C0579c c0579c) {
        C0558d c0559b = new C0559b(c0579c);
        c0559b.m1937a(32525);
        c0559b.m1946a(this.f1201g, this.f1203i == null ? f1196b : this.f1203i);
        return c0559b;
    }

    /* renamed from: a */
    public void m1957a(final AuthorizeListener authorizeListener, boolean z) {
        if (z) {
            m1950b(authorizeListener);
        } else {
            m1949a(new SSOListener(this) {
                /* renamed from: b */
                final /* synthetic */ C0566d f1192b;

                public void onFailed(Throwable th) {
                    C0621d.m2279a().d(th);
                    this.f1192b.m1950b(authorizeListener);
                }

                public void onComplete(Bundle bundle) {
                    authorizeListener.onComplete(bundle);
                }

                public void onCancel() {
                    authorizeListener.onCancel();
                }
            });
        }
    }

    /* renamed from: a */
    public void m1956a(ShareParams shareParams, PlatformActionListener platformActionListener) throws Throwable {
        Object imageUrl = shareParams.getImageUrl();
        Object title = shareParams.getTitle();
        Object text = shareParams.getText();
        Object musicUrl = shareParams.getMusicUrl();
        Object url = shareParams.getUrl();
        String titleUrl = shareParams.getTitleUrl();
        if (!TextUtils.isEmpty(titleUrl)) {
            titleUrl = this.a.getShortLintk(titleUrl, false);
        } else if (!TextUtils.isEmpty(url)) {
            titleUrl = this.a.getShortLintk(url, false);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://www.facebook.com/dialog/feed?");
        stringBuilder.append("app_id=").append(this.f1201g);
        stringBuilder.append("&redirect_uri=fbconnect://success");
        stringBuilder.append("&link=").append(Data.urlEncode(titleUrl, "utf-8"));
        if (!TextUtils.isEmpty(imageUrl)) {
            stringBuilder.append("&picture=").append(Data.urlEncode(imageUrl, "utf-8"));
        }
        if (!TextUtils.isEmpty(title)) {
            stringBuilder.append("&caption=").append(Data.urlEncode(title, "utf-8"));
        }
        if (!TextUtils.isEmpty(text)) {
            stringBuilder.append("&description=").append(Data.urlEncode(text, "utf-8"));
        }
        if (!TextUtils.isEmpty(musicUrl)) {
            stringBuilder.append("&source=").append(Data.urlEncode(musicUrl, "utf-8"));
            if (!TextUtils.isEmpty(text)) {
                stringBuilder.append("&name=").append(Data.urlEncode(text, "utf-8"));
            }
        }
        C0570e c0570e = new C0570e();
        c0570e.m1973a(stringBuilder.toString());
        c0570e.m1972a(platformActionListener);
        c0570e.show(this.a.getContext(), null);
    }

    /* renamed from: b */
    public HashMap<String, Object> m1962b(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.f1199e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair(AVStatus.MESSAGE_TAG, str));
        String b = this.f1202h.m1997b("https://graph.facebook.com/v2.5/me/feed", arrayList, "/v2.5/me/feed", m1951c());
        if (b == null || b.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(b);
    }

    /* renamed from: b */
    public HashMap<String, Object> m1963b(String str, String str2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.f1199e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair("caption", str));
        String a = this.f1202h.m1990a("https://graph.facebook.com/v2.5/me/photos", arrayList, new KVPair(MapboxEvent.ATTRIBUTE_SOURCE, str2), "/v2.5/me/photos", m1951c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    /* renamed from: c */
    public HashMap<String, Object> m1965c(String str) throws Throwable {
        String str2 = "/me";
        if (str != null) {
            str2 = "/" + str;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.f1199e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair("fields", "id,name,first_name,middle_name,last_name,gender,locale,languages,link,age_range,third_party_id,installed,timezone,updated_time,verified,birthday,cover,currency,devices,education,email,hometown,interested_in,location,political,payment_pricepoints,favorite_athletes,favorite_teams,picture,quotes,relationship_status,religion,security_settings,significant_other,video_upload_limits,website,work"));
        str2 = this.f1202h.m1993a("https://graph.facebook.com/v2.5" + str2, arrayList, "get_user_info", m1951c());
        C0621d.m2279a().i("facebook helper getUser", new Object[0]);
        if (str2 == null || str2.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(str2);
    }

    /* renamed from: a */
    public HashMap<String, Object> m1954a(int i, int i2, String str) throws Throwable {
        String str2 = "me";
        if (str != null) {
            str2 = str;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.f1199e));
        arrayList.add(new KVPair("format", "json"));
        arrayList.add(new KVPair("limit", String.valueOf(i)));
        arrayList.add(new KVPair("offset", String.valueOf(i2)));
        arrayList.add(new KVPair("fields", "id,name,first_name,middle_name,last_name,gender,locale,languages,link,age_range,third_party_id,installed,timezone,updated_time,verified,birthday,cover,currency,devices,education,email,hometown,interested_in,location,political,payment_pricepoints,favorite_athletes,favorite_teams,picture,quotes,relationship_status,religion,security_settings,significant_other,video_upload_limits,website,work"));
        String str3 = "/friends";
        if (!TextUtils.isEmpty(str)) {
            str3 = "/taggable_friends";
        }
        str2 = this.f1202h.m1993a("https://graph.facebook.com/v2.5/" + str2 + str3, arrayList, "friends", m1951c());
        if (str2 == null || str2.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(str2);
    }

    /* renamed from: a */
    public HashMap<String, Object> m1955a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) throws Throwable {
        if (str2 == null) {
            return null;
        }
        KVPair kVPair;
        ArrayList arrayList = new ArrayList();
        if (hashMap != null && hashMap.size() > 0) {
            for (Entry entry : hashMap.entrySet()) {
                arrayList.add(new KVPair((String) entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        arrayList.add(new KVPair("access_token", this.f1199e));
        arrayList.add(new KVPair("format", "json"));
        if (hashMap2 == null || hashMap2.size() <= 0) {
            kVPair = null;
        } else {
            HashMap<String, Object> hashMap3 = null;
            for (Entry entry2 : hashMap2.entrySet()) {
                Object kVPair2 = new KVPair((String) entry2.getKey(), entry2.getValue());
            }
            kVPair = hashMap3;
        }
        String httpGet = "GET".equals(str2.toUpperCase()) ? this.f1202h.httpGet(str, arrayList, null, null) : "POST".equals(str2.toUpperCase()) ? this.f1202h.httpPost(str, arrayList, kVPair, null, null) : null;
        if (httpGet == null || httpGet.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(httpGet);
    }

    /* renamed from: d */
    public void m1966d(String str) {
        this.f1204j = str;
    }
}
