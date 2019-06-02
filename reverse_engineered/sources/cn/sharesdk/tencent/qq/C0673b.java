package cn.sharesdk.tencent.qq;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import ch.qos.logback.core.CoreConstants;
import cn.sharesdk.framework.C0565b;
import cn.sharesdk.framework.Platform;
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
import com.alipay.sdk.authjs.C0840a;
import com.alipay.sdk.sys.C0869a;
import com.alipay.sdk.util.C0880h;
import com.beastbikes.framework.ui.android.WebActivity;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: QQHelper */
/* renamed from: cn.sharesdk.tencent.qq.b */
public class C0673b extends C0565b {
    /* renamed from: b */
    private static final String[] f1591b = new String[]{"get_user_info", "get_simple_userinfo", "get_user_profile", "get_app_friends", "add_share", "list_album", "upload_pic", "add_album", "set_user_face", "get_vip_info", "get_vip_rich_info", "get_intimate_friends_weibo", "match_nick_tips_weibo", "add_t", "add_pic_t"};
    /* renamed from: c */
    private static C0673b f1592c;
    /* renamed from: d */
    private String f1593d;
    /* renamed from: e */
    private String[] f1594e;
    /* renamed from: f */
    private String f1595f;
    /* renamed from: g */
    private String f1596g;

    /* renamed from: a */
    public static C0673b m2597a(Platform platform) {
        if (f1592c == null) {
            f1592c = new C0673b(platform);
        }
        return f1592c;
    }

    private C0673b(Platform platform) {
        super(platform);
    }

    /* renamed from: a */
    public void m2603a(String str) {
        this.f1593d = str;
    }

    /* renamed from: a */
    public void m2605a(String[] strArr) {
        this.f1594e = strArr;
    }

    /* renamed from: a */
    public void m2602a(final AuthorizeListener authorizeListener, boolean z) {
        if (z) {
            m1950b(authorizeListener);
        } else {
            m1949a(new SSOListener(this) {
                /* renamed from: b */
                final /* synthetic */ C0673b f1590b;

                public void onFailed(Throwable th) {
                    this.f1590b.m1950b(authorizeListener);
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

    /* renamed from: b */
    public void m2607b(String str) {
        this.f1595f = str;
    }

    /* renamed from: c */
    public HashMap<String, Object> m2608c(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", str));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = C0574a.m1988a().m1994a("https://graph.qq.com/oauth2.0/me", arrayList, arrayList2, null, "/oauth2.0/me", m1951c());
        if (a.startsWith(C0840a.f2027c)) {
            while (!a.startsWith("{") && a.length() > 0) {
                a = a.substring(1);
            }
            while (!a.endsWith(C0880h.f2222d) && a.length() > 0) {
                a = a.substring(0, a.length() - 1);
            }
        }
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    /* renamed from: d */
    public void m2609d(String str) {
        this.f1596g = str;
    }

    public String getAuthorizeUrl() {
        String urlEncode;
        ShareSDK.logApiEvent("/oauth2.0/authorize", m1951c());
        String b = m2600b();
        try {
            urlEncode = Data.urlEncode(getRedirectUri(), "utf-8");
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            urlEncode = getRedirectUri();
        }
        return "https://graph.qq.com/oauth2.0/m_authorize?response_type=token&client_id=" + this.f1593d + C0869a.f2161b + "redirect_uri=" + urlEncode + C0869a.f2161b + "display=mobile&" + "scope=" + b;
    }

    public String getRedirectUri() {
        return "auth://tauth.qq.com/";
    }

    public C0562b getAuthorizeWebviewClient(C0584e c0584e) {
        return new C0671a(c0584e);
    }

    /* renamed from: b */
    private String m2600b() {
        int i = 0;
        String[] strArr = this.f1594e == null ? f1591b : this.f1594e;
        StringBuilder stringBuilder = new StringBuilder();
        int length = strArr.length;
        int i2 = 0;
        while (i < length) {
            String str = strArr[i];
            if (i2 > 0) {
                stringBuilder.append(CoreConstants.COMMA_CHAR);
            }
            stringBuilder.append(str);
            i2++;
            i++;
        }
        return stringBuilder.toString();
    }

    public C0558d getSSOProcessor(C0579c c0579c) {
        C0558d c0674c = new C0674c(c0579c);
        c0674c.m1937a(5656);
        c0674c.m2613a(this.f1593d, m2600b());
        return c0674c;
    }

    /* renamed from: e */
    public HashMap<String, Object> m2610e(String str) throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("access_token", this.f1596g));
        arrayList.add(new KVPair("oauth_consumer_key", this.f1593d));
        arrayList.add(new KVPair("openid", this.f1595f));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        String a = C0574a.m1988a().m1994a("https://graph.qq.com/user/get_simple_userinfo", arrayList, arrayList2, null, "/user/get_simple_userinfo", m1951c());
        if (a == null || a.length() <= 0) {
            return null;
        }
        return new Hashon().fromJson(a);
    }

    /* renamed from: a */
    public void m2604a(String str, String str2, String str3, String str4, String str5, String str6, boolean z, PlatformActionListener platformActionListener, boolean z2, int i) {
        if (z2) {
            m2601b(str, str2, str3, str4, str5, str6, platformActionListener);
        } else if (z && m2606a()) {
            if (!TextUtils.isEmpty(str4)) {
                File file = new File(str4);
                if (file.exists() && str4.startsWith("/data/")) {
                    String absolutePath = new File(C4275R.getCachePath(this.a.getContext(), "images"), System.currentTimeMillis() + file.getName()).getAbsolutePath();
                    if (C4275R.copyFile(str4, absolutePath)) {
                        str4 = absolutePath;
                    } else {
                        str4 = null;
                    }
                }
            }
            Intent intent = new Intent();
            intent.putExtra(WebActivity.EXTRA_TITLE, str);
            intent.putExtra("titleUrl", str2);
            intent.putExtra("summary", str3);
            intent.putExtra("imagePath", str4);
            intent.putExtra("imageUrl", str5);
            intent.putExtra("musicUrl", str6);
            intent.putExtra("appId", this.f1593d);
            intent.putExtra("hidden", i);
            C0677e c0677e = new C0677e();
            c0677e.m2621a(this.a, platformActionListener);
            c0677e.m2622a(this.f1593d);
            c0677e.show(this.a.getContext(), intent);
        } else {
            m2599a(str, str2, str3, str4, str5, str6, platformActionListener);
        }
    }

    /* renamed from: a */
    public boolean m2606a() {
        CharSequence charSequence = null;
        try {
            charSequence = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.mobileqq", 0).versionName;
        } catch (Throwable th) {
            try {
                charSequence = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.qqlite", 0).versionName;
            } catch (Throwable th2) {
                try {
                    charSequence = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.minihd.qq", 0).versionName;
                } catch (Throwable th3) {
                }
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private void m2599a(String str, String str2, String str3, String str4, String str5, String str6, PlatformActionListener platformActionListener) {
        if (str5 == null && str4 != null && new File(str4).exists()) {
            str5 = ((QQ) this.a).uploadImageToFileServer(str4);
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://openmobile.qq.com/api/check?");
            stringBuilder.append("page=shareindex.html&");
            stringBuilder.append("style=9&");
            stringBuilder.append("action=shareToQQ&");
            stringBuilder.append("sdkv=2.2.1&");
            stringBuilder.append("sdkp=a&");
            stringBuilder.append("appId=").append(this.f1593d).append(C0869a.f2161b);
            DeviceHelper instance = DeviceHelper.getInstance(this.a.getContext());
            stringBuilder.append("status_os=").append(Data.urlEncode(instance.getOSVersionName(), "utf-8")).append(C0869a.f2161b);
            stringBuilder.append("status_machine=").append(Data.urlEncode(instance.getModel(), "utf-8")).append(C0869a.f2161b);
            stringBuilder.append("status_version=").append(Data.urlEncode(String.valueOf(instance.getOSVersionInt()), "utf-8")).append(C0869a.f2161b);
            Object appName = instance.getAppName();
            if (!TextUtils.isEmpty(appName)) {
                stringBuilder.append("site=").append(Data.urlEncode(appName, "utf-8")).append(C0869a.f2161b);
            }
            if (!TextUtils.isEmpty(str)) {
                String str7;
                if (str.length() > 40) {
                    str7 = str.substring(40) + "...";
                } else {
                    str7 = str;
                }
                if (str7.length() > 80) {
                    str7 = str7.substring(80) + "...";
                }
                stringBuilder.append("title=").append(Data.urlEncode(str7, "utf-8")).append(C0869a.f2161b);
            }
            if (!TextUtils.isEmpty(str3)) {
                stringBuilder.append("summary=").append(Data.urlEncode(str3, "utf-8")).append(C0869a.f2161b);
            }
            if (!TextUtils.isEmpty(str2)) {
                stringBuilder.append("targeturl=").append(Data.urlEncode(str2, "utf-8")).append(C0869a.f2161b);
            }
            if (!TextUtils.isEmpty(str5)) {
                stringBuilder.append("imageUrl=").append(Data.urlEncode(str5, "utf-8")).append(C0869a.f2161b);
            }
            stringBuilder.append("type=1");
            C0681f c0681f = new C0681f();
            c0681f.m2635a(stringBuilder.toString());
            c0681f.m2634a(platformActionListener);
            c0681f.m2636b(this.f1593d);
            c0681f.show(this.a.getContext(), null);
        } catch (Throwable th) {
            if (platformActionListener != null) {
                platformActionListener.onError(this.a, 9, th);
            }
        }
    }

    /* renamed from: b */
    private void m2601b(String str, String str2, String str3, String str4, String str5, String str6, PlatformActionListener platformActionListener) {
        try {
            Object obj = (TextUtils.isEmpty(str4) && TextUtils.isEmpty(str5)) ? null : 1;
            String str7 = obj == null ? "/t/add_t" : "/t/add_pic_t";
            String str8 = "https://graph.qq.com" + str7;
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("oauth_consumer_key", this.f1593d));
            arrayList.add(new KVPair("access_token", this.f1596g));
            arrayList.add(new KVPair("openid", this.f1595f));
            arrayList.add(new KVPair("format", "json"));
            arrayList.add(new KVPair("content", str3));
            if (obj != null) {
                if (TextUtils.isEmpty(str4)) {
                    str4 = BitmapHelper.downloadBitmap(this.a.getContext(), str5);
                }
                str8 = C0574a.m1988a().m1990a(str8, arrayList, new KVPair("pic", str4), str7, m1951c());
            } else {
                str8 = C0574a.m1988a().m1997b(str8, arrayList, str7, m1951c());
            }
            if (str8 != null && str8.length() > 0 && platformActionListener != null) {
                HashMap fromJson = new Hashon().fromJson(str8);
                if (((Integer) fromJson.get("ret")).intValue() != 0) {
                    platformActionListener.onError(this.a, 9, new Exception(str8));
                } else {
                    platformActionListener.onComplete(this.a, 9, fromJson);
                }
            }
        } catch (Throwable th) {
            if (platformActionListener != null) {
                platformActionListener.onError(this.a, 9, th);
            }
        }
    }
}
