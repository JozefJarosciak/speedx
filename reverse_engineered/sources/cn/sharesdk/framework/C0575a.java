package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.p011b.C0601d;
import cn.sharesdk.framework.p011b.p013b.C0591c;
import cn.sharesdk.framework.p011b.p013b.C0593b;
import cn.sharesdk.framework.p011b.p013b.C0597f;
import cn.sharesdk.framework.p011b.p013b.C0597f.C0596a;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import io.rong.imlib.statistics.UserData;
import java.util.HashMap;

/* compiled from: InnerPlatformActionListener */
/* renamed from: cn.sharesdk.framework.a */
public class C0575a implements PlatformActionListener {
    /* renamed from: a */
    private PlatformActionListener f1236a;
    /* renamed from: b */
    private HashMap<Platform, ShareParams> f1237b = new HashMap();

    C0575a() {
    }

    /* renamed from: a */
    void m2012a(PlatformActionListener platformActionListener) {
        this.f1236a = platformActionListener;
    }

    /* renamed from: a */
    PlatformActionListener m2009a() {
        return this.f1236a;
    }

    /* renamed from: a */
    public void m2011a(Platform platform, ShareParams shareParams) {
        this.f1237b.put(platform, shareParams);
    }

    public void onError(Platform platform, int i, Throwable th) {
        if (this.f1236a != null) {
            this.f1236a.onError(platform, i, th);
            this.f1236a = null;
        }
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (!(platform instanceof CustomPlatform)) {
            switch (i) {
                case 1:
                    m2005a(platform, i, (HashMap) hashMap);
                    return;
                case 9:
                    m2008b(platform, i, hashMap);
                    return;
                default:
                    if (this.f1236a != null) {
                        this.f1236a.onComplete(platform, i, hashMap);
                        if (!"Wechat".equals(platform.getName())) {
                            this.f1236a = null;
                            return;
                        }
                        return;
                    }
                    return;
            }
        } else if (this.f1236a != null) {
            this.f1236a.onComplete(platform, i, hashMap);
            this.f1236a = null;
        }
    }

    public void onCancel(Platform platform, int i) {
        if (this.f1236a != null) {
            this.f1236a.onCancel(platform, i);
            this.f1236a = null;
        }
    }

    /* renamed from: a */
    private void m2005a(Platform platform, final int i, final HashMap<String, Object> hashMap) {
        final PlatformActionListener platformActionListener = this.f1236a;
        this.f1236a = new PlatformActionListener(this) {
            /* renamed from: d */
            final /* synthetic */ C0575a f1230d;

            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                this.f1230d.f1236a = platformActionListener;
                if (this.f1230d.f1236a != null) {
                    this.f1230d.f1236a.onComplete(platform, i, hashMap);
                    if (!"Wechat".equals(platform.getName())) {
                        this.f1230d.f1236a = null;
                    }
                }
                C0591c c0593b = new C0593b();
                c0593b.f1288a = platform.getPlatformId();
                c0593b.f1289b = "TencentWeibo".equals(platform.getName()) ? platform.getDb().get("name") : platform.getDb().getUserId();
                c0593b.f1290c = new Hashon().fromHashMap(hashMap);
                c0593b.f1291d = this.f1230d.m2001a(platform);
                C0601d a = C0601d.m2163a(platform.getContext(), c0593b.g);
                if (a != null) {
                    a.m2170a(c0593b);
                }
                this.f1230d.m2004a(1, platform);
            }

            public void onError(Platform platform, int i, Throwable th) {
                C0621d.m2279a().w(th);
                this.f1230d.f1236a = platformActionListener;
                if (this.f1230d.f1236a != null) {
                    this.f1230d.f1236a.onComplete(platform, i, hashMap);
                    this.f1230d.f1236a = null;
                }
            }

            public void onCancel(Platform platform, int i) {
                this.f1230d.f1236a = platformActionListener;
                if (this.f1230d.f1236a != null) {
                    this.f1230d.f1236a.onComplete(platform, i, hashMap);
                    this.f1230d.f1236a = null;
                }
            }
        };
        platform.showUser(null);
    }

    /* renamed from: b */
    private void m2008b(Platform platform, int i, HashMap<String, Object> hashMap) {
        ShareParams shareParams;
        ShareParams shareParams2 = (ShareParams) this.f1237b.remove(platform);
        if (hashMap != null) {
            shareParams = (ShareParams) hashMap.remove("ShareParams");
        } else {
            shareParams = shareParams2;
        }
        try {
            HashMap hashMap2 = (HashMap) hashMap.clone();
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            HashMap<String, Object> hashMap3 = hashMap;
        }
        if (shareParams != null) {
            C0591c c0597f = new C0597f();
            c0597f.f1314o = shareParams.getCustomFlag();
            String userId = platform.getDb().getUserId();
            if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(userId)) {
                Platform platform2 = ShareSDK.getPlatform("Wechat");
                if (platform2 != null) {
                    userId = platform2.getDb().getUserId();
                }
            } else if ("TencentWeibo".equals(platform.getName())) {
                userId = platform.getDb().get("name");
            }
            c0597f.f1310b = userId;
            c0597f.f1309a = platform.getPlatformId();
            C0596a filterShareContent = platform.filterShareContent(shareParams, hashMap2);
            if (filterShareContent != null) {
                c0597f.f1311c = filterShareContent.f1300a;
                c0597f.f1312d = filterShareContent;
            }
            c0597f.f1313n = m2007b(platform);
            C0601d a = C0601d.m2163a(platform.getContext(), c0597f.g);
            if (a != null) {
                a.m2170a(c0597f);
            }
        }
        if (this.f1236a != null) {
            try {
                this.f1236a.onComplete(platform, i, hashMap);
                this.f1236a = null;
            } catch (Throwable th2) {
                C0621d.m2279a().d(th2);
            }
        }
        m2004a(9, platform);
    }

    /* renamed from: a */
    private void m2004a(int i, Platform platform) {
    }

    /* renamed from: a */
    void m2010a(Platform platform, final int i, final Object obj) {
        final PlatformActionListener platformActionListener = this.f1236a;
        this.f1236a = new PlatformActionListener(this) {
            /* renamed from: d */
            final /* synthetic */ C0575a f1234d;

            public void onError(Platform platform, int i, Throwable th) {
                this.f1234d.f1236a = platformActionListener;
                if (this.f1234d.f1236a != null) {
                    this.f1234d.f1236a.onError(platform, i, th);
                    this.f1234d.f1236a = null;
                }
            }

            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                this.f1234d.f1236a = platformActionListener;
                platform.afterRegister(i, obj);
            }

            public void onCancel(Platform platform, int i) {
                this.f1234d.f1236a = platformActionListener;
                if (this.f1234d.f1236a != null) {
                    this.f1234d.f1236a.onCancel(platform, i);
                    this.f1234d.f1236a = null;
                }
            }
        };
        platform.doAuthorize(null);
    }

    /* renamed from: a */
    private String m2001a(Platform platform) {
        try {
            return m2002a(platform.getDb(), new String[]{"nickname", "icon", UserData.GENDER_KEY, "snsUserUrl", "resume", "secretType", "secret", "birthday", "followerCount", "favouriteCount", "shareCount", "snsregat", "snsUserLevel", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
            return null;
        }
    }

    /* renamed from: b */
    private String m2007b(Platform platform) {
        PlatformDb db = platform.getDb();
        if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(db.getUserGender())) {
            Platform platform2 = ShareSDK.getPlatform("Wechat");
            if (platform2 != null) {
                db = platform2.getDb();
            }
        }
        try {
            return m2002a(db, new String[]{UserData.GENDER_KEY, "birthday", "secretType", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
            return null;
        }
    }

    /* renamed from: a */
    private String m2002a(PlatformDb platformDb, String[] strArr) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            if (i > 0) {
                stringBuilder2.append('|');
                stringBuilder.append('|');
            }
            i++;
            String str2 = platformDb.get(str2);
            if (!TextUtils.isEmpty(str2)) {
                stringBuilder.append(str2);
                stringBuilder2.append(Data.urlEncode(str2, "utf-8"));
            }
        }
        C0621d.m2279a().i("======UserData: " + stringBuilder.toString(), new Object[0]);
        return stringBuilder2.toString();
    }
}
