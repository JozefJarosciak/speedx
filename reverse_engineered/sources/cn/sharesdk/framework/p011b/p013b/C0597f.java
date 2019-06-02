package cn.sharesdk.framework.p011b.p013b;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.utils.C0621d;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: ShareEvent */
/* renamed from: cn.sharesdk.framework.b.b.f */
public class C0597f extends C0591c {
    /* renamed from: p */
    private static int f1307p;
    /* renamed from: q */
    private static long f1308q;
    /* renamed from: a */
    public int f1309a;
    /* renamed from: b */
    public String f1310b;
    /* renamed from: c */
    public String f1311c;
    /* renamed from: d */
    public C0596a f1312d = new C0596a();
    /* renamed from: n */
    public String f1313n;
    /* renamed from: o */
    public String[] f1314o;

    /* compiled from: ShareEvent */
    /* renamed from: cn.sharesdk.framework.b.b.f$a */
    public static class C0596a {
        /* renamed from: a */
        public String f1300a = "";
        /* renamed from: b */
        public String f1301b;
        /* renamed from: c */
        public ArrayList<String> f1302c = new ArrayList();
        /* renamed from: d */
        public ArrayList<String> f1303d = new ArrayList();
        /* renamed from: e */
        public ArrayList<String> f1304e = new ArrayList();
        /* renamed from: f */
        public ArrayList<Bitmap> f1305f = new ArrayList();
        /* renamed from: g */
        public HashMap<String, Object> f1306g;

        public String toString() {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(this.f1301b)) {
                this.f1301b = this.f1301b.trim().replaceAll("\r", "");
                this.f1301b = this.f1301b.trim().replaceAll("\n", "");
                this.f1301b = this.f1301b.trim().replaceAll("\r\n", "");
            }
            hashMap.put(DirectionsCriteria.INSTRUCTIONS_TEXT, this.f1301b);
            hashMap.put("url", this.f1302c);
            if (this.f1303d != null && this.f1303d.size() > 0) {
                hashMap.put("imgs", this.f1303d);
            }
            if (this.f1306g != null) {
                hashMap.put("attch", new Hashon().fromHashMap(this.f1306g));
            }
            return new Hashon().fromHashMap(hashMap);
        }
    }

    /* renamed from: a */
    protected String mo2277a() {
        return "[SHR]";
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|').append(this.f1309a);
        stringBuilder.append('|').append(this.f1310b);
        stringBuilder.append('|').append(TextUtils.isEmpty(this.f1311c) ? "" : this.f1311c);
        String str = "";
        if (this.f1314o != null && this.f1314o.length > 0) {
            str = "[\"" + TextUtils.join("\",\"", this.f1314o) + "\"]";
        }
        stringBuilder.append('|').append(str);
        stringBuilder.append('|');
        if (this.f1312d != null) {
            try {
                str = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.f1312d.toString()), 0);
                if (str.contains("\n")) {
                    str = str.replace("\n", "");
                }
                stringBuilder.append(str);
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.f1313n)) {
            try {
                str = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.f1313n), 0);
                if (!TextUtils.isEmpty(str) && str.contains("\n")) {
                    str = str.replace("\n", "");
                }
                stringBuilder.append(str);
            } catch (Throwable th2) {
                C0621d.m2279a().w(th2);
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    protected int mo2279b() {
        return 5000;
    }

    /* renamed from: c */
    protected int mo2280c() {
        return 30;
    }

    /* renamed from: d */
    protected long mo2281d() {
        return (long) f1307p;
    }

    /* renamed from: e */
    protected long mo2282e() {
        return f1308q;
    }

    /* renamed from: f */
    protected void mo2283f() {
        f1307p++;
    }

    /* renamed from: a */
    protected void mo2278a(long j) {
        f1308q = j;
    }
}
