package cn.sharesdk.framework.p011b.p013b;

import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.tools.utils.Data;

/* compiled from: AuthEvent */
/* renamed from: cn.sharesdk.framework.b.b.b */
public class C0593b extends C0591c {
    /* renamed from: n */
    private static int f1286n;
    /* renamed from: o */
    private static long f1287o;
    /* renamed from: a */
    public int f1288a;
    /* renamed from: b */
    public String f1289b;
    /* renamed from: c */
    public String f1290c;
    /* renamed from: d */
    public String f1291d;

    /* renamed from: a */
    protected String mo2277a() {
        return "[AUT]";
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|').append(this.f1288a);
        stringBuilder.append('|').append(this.f1289b);
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.f1291d)) {
            try {
                String encodeToString = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.f1291d), 0);
                if (!TextUtils.isEmpty(encodeToString) && encodeToString.contains("\n")) {
                    encodeToString = encodeToString.replace("\n", "");
                }
                stringBuilder.append(encodeToString);
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.f1290c)) {
            stringBuilder.append(this.f1290c);
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    protected int mo2279b() {
        return 5000;
    }

    /* renamed from: c */
    protected int mo2280c() {
        return 5;
    }

    /* renamed from: d */
    protected long mo2281d() {
        return (long) f1286n;
    }

    /* renamed from: e */
    protected long mo2282e() {
        return f1287o;
    }

    /* renamed from: f */
    protected void mo2283f() {
        f1286n++;
    }

    /* renamed from: a */
    protected void mo2278a(long j) {
        f1287o = j;
    }
}
