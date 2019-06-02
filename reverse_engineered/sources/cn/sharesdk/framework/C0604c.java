package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import io.rong.imlib.statistics.UserData;
import java.lang.reflect.Field;
import java.util.HashMap;

/* compiled from: PlatformImpl */
/* renamed from: cn.sharesdk.framework.c */
public class C0604c {
    /* renamed from: a */
    private Platform f1348a;
    /* renamed from: b */
    private Context f1349b;
    /* renamed from: c */
    private PlatformDb f1350c;
    /* renamed from: d */
    private C0575a f1351d;
    /* renamed from: e */
    private int f1352e;
    /* renamed from: f */
    private int f1353f;
    /* renamed from: g */
    private boolean f1354g;
    /* renamed from: h */
    private boolean f1355h = true;
    /* renamed from: i */
    private boolean f1356i;

    public C0604c(Platform platform, Context context) {
        this.f1348a = platform;
        this.f1349b = context;
        String name = platform.getName();
        this.f1350c = new PlatformDb(context, name, platform.getVersion());
        m2184a(name);
        this.f1351d = new C0575a();
    }

    /* renamed from: a */
    public void m2184a(String str) {
        try {
            this.f1352e = C4275R.parseInt(String.valueOf(ShareSDK.m1985b(str, "Id")).trim());
        } catch (Throwable th) {
            if (!(this.f1348a instanceof CustomPlatform)) {
                C0621d.m2279a().d(this.f1348a.getName() + " failed to parse Id, this will cause method getId() always returens 0", new Object[0]);
            }
        }
        try {
            this.f1353f = C4275R.parseInt(String.valueOf(ShareSDK.m1985b(str, "SortId")).trim());
        } catch (Throwable th2) {
            if (!(this.f1348a instanceof CustomPlatform)) {
                C0621d.m2279a().d(this.f1348a.getName() + " failed to parse SortId, this won't cause any problem, don't worry", new Object[0]);
            }
        }
        String b = ShareSDK.m1985b(str, "Enable");
        if (b == null) {
            this.f1356i = true;
            if (!(this.f1348a instanceof CustomPlatform)) {
                C0621d.m2279a().d(this.f1348a.getName() + " failed to parse Enable, this will cause platform always be enable", new Object[0]);
            }
        } else {
            this.f1356i = "true".equals(b.trim());
        }
        this.f1348a.initDevInfo(str);
    }

    /* renamed from: a */
    public int m2176a() {
        return this.f1352e;
    }

    /* renamed from: b */
    public int m2189b() {
        return this.f1353f;
    }

    /* renamed from: a */
    public void m2183a(PlatformActionListener platformActionListener) {
        this.f1351d.m2012a(platformActionListener);
    }

    /* renamed from: c */
    public PlatformActionListener m2192c() {
        return this.f1351d.m2009a();
    }

    /* renamed from: d */
    public boolean m2196d() {
        return this.f1350c.isValid();
    }

    /* renamed from: a */
    public void m2187a(boolean z) {
        this.f1354g = z;
    }

    /* renamed from: e */
    public boolean m2197e() {
        return this.f1354g;
    }

    /* renamed from: f */
    public boolean m2198f() {
        return this.f1356i;
    }

    /* renamed from: j */
    private boolean m2174j() {
        boolean z = false;
        String a;
        if (ShareSDK.m1983a()) {
            a = m2177a(this.f1348a.getPlatformId(), "covert_url", null);
            if (a != null) {
                a.trim();
            }
            if (!"false".equals(a)) {
                z = true;
            }
            this.f1355h = z;
            this.f1348a.setNetworkDevinfo();
            return true;
        }
        try {
            HashMap hashMap = new HashMap();
            if (!ShareSDK.m1984a(hashMap)) {
                return false;
            }
            if (ShareSDK.m1987b(hashMap)) {
                boolean z2;
                a = m2177a(this.f1348a.getPlatformId(), "covert_url", null);
                if (a != null) {
                    a.trim();
                }
                if ("false".equals(a)) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                this.f1355h = z2;
                this.f1348a.setNetworkDevinfo();
                return true;
            }
            C0621d.m2279a().i("Failed to parse network dev-info: " + new Hashon().fromHashMap(hashMap), new Object[0]);
            return false;
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
            return false;
        }
    }

    /* renamed from: a */
    public String m2177a(int i, String str, String str2) {
        String a = ShareSDK.m1977a(i, str);
        if (TextUtils.isEmpty(a) || "null".equals(a)) {
            return this.f1348a.getDevinfo(this.f1348a.getName(), str2);
        }
        return a;
    }

    /* renamed from: a */
    public void m2181a(int i, Object obj) {
        this.f1351d.m2010a(this.f1348a, i, obj);
    }

    /* renamed from: b */
    protected void m2190b(int i, Object obj) {
        Object[] objArr;
        switch (i) {
            case 1:
                if (this.f1351d != null) {
                    this.f1351d.onComplete(this.f1348a, 1, null);
                    return;
                }
                return;
            case 2:
                objArr = (Object[]) obj;
                this.f1348a.getFriendList(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), (String) objArr[2]);
                return;
            case 6:
                this.f1348a.follow((String) obj);
                return;
            case 7:
                objArr = (Object[]) obj;
                this.f1348a.timeline(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), (String) objArr[2]);
                return;
            case 8:
                Platform platform = this.f1348a;
                if (obj == null) {
                    obj = null;
                } else {
                    String str = (String) obj;
                }
                platform.userInfor(obj);
                return;
            case 9:
                ShareParams shareParams = (ShareParams) obj;
                HashMap toMap = shareParams.toMap();
                for (Field field : shareParams.getClass().getFields()) {
                    if (toMap.get(field.getName()) == null) {
                        Object obj2;
                        field.setAccessible(true);
                        try {
                            obj2 = field.get(shareParams);
                        } catch (Throwable th) {
                            C0621d.m2279a().w(th);
                            obj2 = null;
                        }
                        if (obj2 != null) {
                            toMap.put(field.getName(), obj2);
                        }
                    }
                }
                if (this.f1351d instanceof C0575a) {
                    this.f1351d.m2011a(this.f1348a, shareParams);
                }
                this.f1348a.doShare(shareParams);
                return;
            default:
                objArr = (Object[]) obj;
                this.f1348a.doCustomerProtocol(String.valueOf(objArr[0]), String.valueOf(objArr[1]), i, (HashMap) objArr[2], (HashMap) objArr[3]);
                return;
        }
    }

    /* renamed from: c */
    protected void m2193c(final int i, final Object obj) {
        new Thread(this) {
            /* renamed from: c */
            final /* synthetic */ C0604c f1345c;

            public void run() {
                try {
                    this.f1345c.m2174j();
                    if (this.f1345c.f1348a.checkAuthorize(i, obj)) {
                        this.f1345c.m2190b(i, obj);
                    }
                } catch (Throwable th) {
                    C0621d.m2279a().w(th);
                }
            }
        }.start();
    }

    /* renamed from: a */
    public void m2188a(final String[] strArr) {
        new Thread(this) {
            /* renamed from: b */
            final /* synthetic */ C0604c f1347b;

            public void run() {
                try {
                    this.f1347b.m2174j();
                    this.f1347b.f1348a.doAuthorize(strArr);
                } catch (Throwable th) {
                    C0621d.m2279a().w(th);
                }
            }
        }.start();
    }

    /* renamed from: a */
    public void m2182a(ShareParams shareParams) {
        if (shareParams != null) {
            m2193c(9, shareParams);
        } else if (this.f1351d != null) {
            this.f1351d.onError(this.f1348a, 9, new NullPointerException());
        }
    }

    /* renamed from: b */
    public void m2191b(String str) {
        m2193c(6, str);
    }

    /* renamed from: a */
    public void m2185a(String str, int i, int i2) {
        m2193c(7, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str});
    }

    /* renamed from: c */
    public void m2194c(String str) {
        m2193c(8, str);
    }

    /* renamed from: a */
    public void m2180a(int i, int i2, String str) {
        m2193c(2, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str});
    }

    /* renamed from: a */
    public void m2186a(String str, String str2, short s, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        m2193c(655360 | s, new Object[]{str, str2, hashMap, hashMap2});
    }

    /* renamed from: g */
    public PlatformDb m2199g() {
        return this.f1350c;
    }

    /* renamed from: h */
    public void m2200h() {
        this.f1350c.removeAccount();
    }

    /* renamed from: a */
    public String m2179a(String str, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.f1355h) {
            C0621d.m2279a().i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return str;
        } else if (TextUtils.isEmpty(str)) {
            C0621d.m2279a().i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return str;
        } else {
            str = ShareSDK.m1980a(str, z, this.f1348a.getPlatformId(), m2175k());
            C0621d.m2279a().i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return str;
        }
    }

    /* renamed from: k */
    private String m2175k() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if ("TencentWeibo".equals(this.f1348a.getName())) {
                C0621d.m2279a().i("user id %s ==>>", new Object[]{m2199g().getUserName()});
                stringBuilder.append(Data.urlEncode(m2199g().getUserName(), "utf-8"));
            } else {
                stringBuilder.append(Data.urlEncode(m2199g().getUserId(), "utf-8"));
            }
            stringBuilder.append("|").append(Data.urlEncode(m2199g().get("secretType"), "utf-8"));
            stringBuilder.append("|").append(Data.urlEncode(m2199g().get(UserData.GENDER_KEY), "utf-8"));
            stringBuilder.append("|").append(Data.urlEncode(m2199g().get("birthday"), "utf-8"));
            stringBuilder.append("|").append(Data.urlEncode(m2199g().get("educationJSONArrayStr"), "utf-8"));
            stringBuilder.append("|").append(Data.urlEncode(m2199g().get("workJSONArrayStr"), "utf-8"));
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
        }
        return stringBuilder.toString();
    }

    /* renamed from: i */
    protected PlatformActionListener m2201i() {
        return this.f1351d;
    }

    /* renamed from: d */
    public String m2195d(String str) {
        return ShareSDK.m1979a(str);
    }

    /* renamed from: a */
    public String m2178a(Bitmap bitmap) {
        return ShareSDK.m1978a(bitmap);
    }
}
