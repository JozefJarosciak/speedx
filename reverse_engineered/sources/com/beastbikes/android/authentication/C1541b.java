package com.beastbikes.android.authentication;

import android.content.Context;
import cn.sharesdk.facebook.Facebook;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.google.GooglePlus;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.twitter.Twitter;
import cn.sharesdk.wechat.friends.Wechat;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: AuthenticationFactory */
/* renamed from: com.beastbikes.android.authentication.b */
public class C1541b {
    /* renamed from: a */
    private static C1539a f7241a = new C1539a();
    /* renamed from: b */
    private static C1540b f7242b;
    /* renamed from: c */
    private static final Logger f7243c = LoggerFactory.getLogger(C1541b.class);
    /* renamed from: d */
    private static String f7244d;

    /* compiled from: AuthenticationFactory */
    /* renamed from: com.beastbikes.android.authentication.b$a */
    private static class C1539a implements PlatformActionListener {
        private C1539a() {
        }

        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            C1541b.f7243c.info("MyPlatformActionListener onComplete");
            if (platform == null || platform.getDb() == null) {
                C1541b.f7242b.mo3123a(null);
                return;
            }
            PlatformDb db = platform.getDb();
            String token = db.getToken();
            String userId = db.getUserId();
            String str = db.get("nickname");
            String tokenSecret = db.getTokenSecret();
            C1541b.f7243c.info("accessToken:" + token);
            C1541b.f7243c.info("openId:" + userId);
            C1541b.f7243c.info("nickname:" + str);
            C1541b.f7243c.info("tokenSecret:" + tokenSecret);
            C1537a c1537a = new C1537a(token, userId, str, tokenSecret, C1541b.f7244d);
            try {
                if (C1541b.f7242b != null) {
                    C1541b.f7242b.mo3123a(c1537a);
                }
            } catch (Exception e) {
                C1541b.f7243c.error("MyPlatformActionListener onComplete catch Exception e=" + e.toString());
            }
        }

        public void onError(Platform platform, int i, Throwable th) {
            C1541b.f7243c.error("MyPlatformActionListener onError");
            C1541b.f7243c.error("MyPlatformActionListener onError" + th.getMessage());
            try {
                if (C1541b.f7242b != null) {
                    C1541b.f7242b.mo3123a(null);
                }
            } catch (Exception e) {
                C1541b.f7243c.error("MyPlatformActionListener onError catch Exception");
            }
        }

        public void onCancel(Platform platform, int i) {
            C1541b.f7243c.error("MyPlatformActionListener onCancel");
            try {
                C1541b.f7242b.mo3123a(null);
            } catch (Exception e) {
                C1541b.f7243c.error("MyPlatformActionListener onCancel catch Exception");
            }
        }
    }

    /* compiled from: AuthenticationFactory */
    /* renamed from: com.beastbikes.android.authentication.b$b */
    public interface C1540b {
        /* renamed from: a */
        void mo3123a(C1537a c1537a);
    }

    /* renamed from: a */
    public static void m8484a(Context context, String str, C1540b c1540b) {
        try {
            ShareSDK.initSDK(context);
            f7244d = str;
            Context context2 = (Context) new WeakReference(context).get();
            f7242b = (C1540b) new WeakReference(c1540b).get();
            Platform platform = ShareSDK.getPlatform(context2, str);
            if (platform != null) {
                if (platform.isValid()) {
                    platform.removeAccount(true);
                }
                platform.setPlatformActionListener(f7241a);
                platform.showUser(null);
            }
        } catch (RuntimeException e) {
            f7243c.error("ShareSDK Exception e=" + e.getMessage());
        }
    }

    /* renamed from: b */
    public static void m8486b(Context context, String str, C1540b c1540b) {
        try {
            ShareSDK.initSDK(context);
            f7244d = str;
            Context context2 = (Context) new WeakReference(context).get();
            f7242b = (C1540b) new WeakReference(c1540b).get();
            Platform platform = ShareSDK.getPlatform(context2, str);
            platform.setPlatformActionListener(f7241a);
            platform.showUser(null);
        } catch (RuntimeException e) {
            f7243c.error("ShareSDK Exception e=" + e.getMessage());
        }
    }

    /* renamed from: a */
    public static void m8482a(Context context, int i) {
        try {
            ShareSDK.initSDK(context);
            switch (i) {
                case 4:
                    C1541b.m8483a(context, SinaWeibo.NAME);
                    return;
                case 8:
                    C1541b.m8483a(context, QQ.NAME);
                    return;
                case 16:
                    C1541b.m8483a(context, Wechat.NAME);
                    return;
                case 32:
                    C1541b.m8483a(context, Twitter.NAME);
                    return;
                case 64:
                    C1541b.m8483a(context, Facebook.NAME);
                    return;
                case 128:
                    C1541b.m8483a(context, GooglePlus.NAME);
                    return;
                default:
                    return;
            }
        } catch (RuntimeException e) {
            f7243c.error("ShareSDK Exception e=" + e.getMessage());
        }
    }

    /* renamed from: a */
    public static void m8483a(Context context, String str) {
        try {
            ShareSDK.initSDK(context);
            Platform platform = ShareSDK.getPlatform(context, str);
            if (platform != null) {
                platform.removeAccount();
            }
        } catch (RuntimeException e) {
            f7243c.error("ShareSDK Exception e=" + e.getMessage());
        }
    }

    /* renamed from: a */
    public static void m8481a(Context context) {
        try {
            ShareSDK.initSDK(context);
            Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
            if (platform != null) {
                platform.removeAccount();
                platform.removeAccount(true);
            }
            platform = ShareSDK.getPlatform(QQ.NAME);
            if (platform != null) {
                platform.removeAccount();
                platform.removeAccount(true);
            }
            platform = ShareSDK.getPlatform(Wechat.NAME);
            if (platform != null) {
                platform.removeAccount();
                platform.removeAccount(true);
            }
            platform = ShareSDK.getPlatform(Twitter.NAME);
            if (platform != null) {
                platform.removeAccount();
                platform.removeAccount(true);
            }
            platform = ShareSDK.getPlatform(Facebook.NAME);
            if (platform != null) {
                platform.removeAccount();
                platform.removeAccount(true);
            }
            platform = ShareSDK.getPlatform(GooglePlus.NAME);
            if (platform != null) {
                platform.removeAccount();
                platform.removeAccount(true);
            }
            ShareSDK.removeCookieOnAuthorize(true);
        } catch (RuntimeException e) {
            f7243c.error("ShareSDK Exception e=" + e.getMessage());
        }
    }
}
