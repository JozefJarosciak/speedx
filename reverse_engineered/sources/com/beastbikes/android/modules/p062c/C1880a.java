package com.beastbikes.android.modules.p062c;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.qiniu.android.http.C4372g;
import com.qiniu.android.p110b.C1873g;
import com.qiniu.android.p110b.C1876h;
import com.qiniu.android.p110b.C4330j;
import com.qiniu.android.p110b.C4333k;
import java.io.File;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: QiNiuManager */
/* renamed from: com.beastbikes.android.modules.c.a */
public class C1880a extends C1397a implements C1371a {
    /* renamed from: A */
    private final String f8410A = "testerRouteMap/";
    /* renamed from: B */
    private final String f8411B = "routeMap/";
    /* renamed from: C */
    private final String f8412C = "testerClubFeedAlbum/";
    /* renamed from: D */
    private final String f8413D = "clubFeedAlbum/";
    /* renamed from: E */
    private final String f8414E = "testerClubActivity/";
    /* renamed from: F */
    private final String f8415F = "clubActivity/";
    /* renamed from: G */
    private final String f8416G = "testerFeedbackImage/";
    /* renamed from: H */
    private final String f8417H = "feedbackImage/";
    /* renamed from: a */
    C4333k f8418a = new C4333k(null, null, false, new C18773(this), null);
    /* renamed from: b */
    private Context f8419b;
    /* renamed from: c */
    private C1395c f8420c;
    /* renamed from: d */
    private final String f8421d = "http://bazaar.speedx.com/";
    /* renamed from: e */
    private final int f8422e = -5;
    /* renamed from: f */
    private final int f8423f = HttpStatus.SC_UNAUTHORIZED;
    /* renamed from: g */
    private final int f8424g = -4;
    /* renamed from: h */
    private final int f8425h = -1004;
    /* renamed from: i */
    private int f8426i = 0;
    /* renamed from: j */
    private final int f8427j = 1;
    /* renamed from: k */
    private final int f8428k = 2;
    /* renamed from: l */
    private int f8429l = 1;
    /* renamed from: m */
    private Logger f8430m = LoggerFactory.getLogger(C1880a.class);
    /* renamed from: n */
    private C1879a f8431n;
    /* renamed from: o */
    private String f8432o;
    /* renamed from: p */
    private String f8433p = "";
    /* renamed from: q */
    private String f8434q;
    /* renamed from: r */
    private File f8435r;
    /* renamed from: s */
    private final String f8436s = "bazaar:";
    /* renamed from: t */
    private String f8437t = "";
    /* renamed from: u */
    private C1882d f8438u;
    /* renamed from: v */
    private C1881b f8439v;
    /* renamed from: w */
    private final String f8440w = "testerAvatar/";
    /* renamed from: x */
    private final String f8441x = "avatar/";
    /* renamed from: y */
    private final String f8442y = "testerClubLogo/";
    /* renamed from: z */
    private final String f8443z = "clubLogo/";

    /* compiled from: QiNiuManager */
    /* renamed from: com.beastbikes.android.modules.c.a$1 */
    class C18741 implements C1873g {
        /* renamed from: a */
        final /* synthetic */ C1880a f8405a;

        C18741(C1880a c1880a) {
            this.f8405a = c1880a;
        }

        /* renamed from: a */
        public void mo3261a(String str, C4372g c4372g, JSONObject jSONObject) {
            this.f8405a.m9727a(str, c4372g, jSONObject);
        }
    }

    /* compiled from: QiNiuManager */
    /* renamed from: com.beastbikes.android.modules.c.a$2 */
    class C18752 implements C1873g {
        /* renamed from: a */
        final /* synthetic */ C1880a f8406a;

        C18752(C1880a c1880a) {
            this.f8406a = c1880a;
        }

        /* renamed from: a */
        public void mo3261a(String str, C4372g c4372g, JSONObject jSONObject) {
            this.f8406a.m9727a(str, c4372g, jSONObject);
        }
    }

    /* compiled from: QiNiuManager */
    /* renamed from: com.beastbikes.android.modules.c.a$3 */
    class C18773 implements C1876h {
        /* renamed from: a */
        final /* synthetic */ C1880a f8407a;

        C18773(C1880a c1880a) {
            this.f8407a = c1880a;
        }

        /* renamed from: a */
        public void mo3262a(String str, double d) {
            if (this.f8407a.f8439v != null) {
                this.f8407a.f8439v.m9747a(str, d);
            }
        }
    }

    /* compiled from: QiNiuManager */
    /* renamed from: com.beastbikes.android.modules.c.a$a */
    private class C1879a extends Thread {
        /* renamed from: a */
        final /* synthetic */ C1880a f8409a;

        /* compiled from: QiNiuManager */
        /* renamed from: com.beastbikes.android.modules.c.a$a$1 */
        class C18781 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C1879a f8408a;

            C18781(C1879a c1879a) {
                this.f8408a = c1879a;
            }

            public void run() {
                switch (this.f8408a.f8409a.f8429l) {
                    case 1:
                        this.f8408a.f8409a.m9733h();
                        return;
                    case 2:
                        this.f8408a.f8409a.m9734i();
                        return;
                    default:
                        return;
                }
            }
        }

        private C1879a(C1880a c1880a) {
            this.f8409a = c1880a;
        }

        public void run() {
            this.f8409a.m9736a();
            new Handler(this.f8409a.f8419b.getMainLooper()).post(new C18781(this));
        }
    }

    public C1880a(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f8419b = context;
        this.f8420c = (C1395c) new C1772d(context).m9399a(C1395c.class, C1768c.f8075a, C1768c.m9391a(context));
    }

    /* renamed from: a */
    public String m9736a() {
        try {
            JSONObject a = this.f8420c.a("bazaar:" + this.f8437t);
            if (a == null) {
                return "";
            }
            if (a.optInt("code") == 0) {
                String optString = a.optString(C0882j.f2229c);
                if (TextUtils.isEmpty(optString)) {
                    return optString;
                }
                this.f8433p = optString;
                return optString;
            }
            return "";
        } catch (Exception e) {
            this.f8430m.error("e", e.getMessage());
        }
    }

    /* renamed from: a */
    public void m9740a(String str, String str2, String str3) {
        this.f8437t = str3;
        this.f8429l = 1;
        this.f8434q = str2;
        this.f8426i = 0;
        this.f8432o = str;
        m9735k();
    }

    /* renamed from: a */
    public void m9738a(String str, File file, String str2) {
        this.f8437t = str2;
        this.f8429l = 2;
        this.f8426i = 0;
        this.f8435r = file;
        this.f8432o = str;
        m9735k();
    }

    /* renamed from: a */
    public void m9739a(String str, File file, String str2, C1882d c1882d) {
        this.f8438u = c1882d;
        this.f8437t = str2;
        this.f8429l = 2;
        this.f8426i = 0;
        this.f8435r = file;
        this.f8432o = str;
        m9735k();
    }

    /* renamed from: a */
    public void m9737a(C1882d c1882d) {
        this.f8438u = c1882d;
    }

    /* renamed from: h */
    private void m9733h() {
        C4330j b = m9729b(this.f8438u);
        if (b == null) {
            this.f8438u.mo3361a();
        } else {
            b.m17111a(this.f8434q, this.f8432o, this.f8433p, new C18741(this), this.f8418a);
        }
    }

    /* renamed from: i */
    private void m9734i() {
        C4330j b = m9729b(this.f8438u);
        if (b == null) {
            this.f8438u.mo3361a();
        } else {
            b.m17110a(this.f8435r, this.f8432o, this.f8433p, new C18752(this), this.f8418a);
        }
    }

    /* renamed from: b */
    private C4330j m9729b(C1882d c1882d) {
        this.f8426i++;
        if (this.f8426i <= 3) {
            return new C4330j();
        }
        c1882d.mo3361a();
        return null;
    }

    /* renamed from: a */
    private void m9727a(String str, C4372g c4372g, JSONObject jSONObject) {
        if (!TextUtils.isEmpty(c4372g.f15172e)) {
            this.f8430m.error("uploadManager error ", c4372g.f15172e);
            Toasts.show(this.f8419b, c4372g.f15172e);
        }
        if (-5 == c4372g.f15168a || HttpStatus.SC_UNAUTHORIZED == c4372g.f15168a || -4 == c4372g.f15168a) {
            this.f8430m.error("statusCode", c4372g.f15168a + "");
            m9735k();
        } else if (-1004 == c4372g.f15168a) {
            this.f8438u.mo3361a();
        } else if (c4372g.m17200d() && this.f8438u != null) {
            this.f8438u.mo3362a("http://bazaar.speedx.com/" + str);
        }
    }

    /* renamed from: k */
    private void m9735k() {
        this.f8431n = new C1879a();
        this.f8431n.start();
    }

    /* renamed from: b */
    public String m9741b() {
        return "avatar/";
    }

    /* renamed from: c */
    public String m9742c() {
        return "clubLogo/";
    }

    /* renamed from: d */
    public String m9743d() {
        return "routeMap/";
    }

    /* renamed from: e */
    public String m9744e() {
        return "clubFeedAlbum/";
    }

    /* renamed from: f */
    public String m9745f() {
        return "clubActivity/";
    }

    /* renamed from: g */
    public String m9746g() {
        return "feedbackImage/";
    }
}
