package com.qiniu.android.p110b;

import com.qiniu.android.http.C4366a;
import com.qiniu.android.http.C4372g;
import com.qiniu.android.p110b.C4313a.C4312a;
import com.qiniu.android.p189c.C4334a;
import java.io.File;

/* compiled from: UploadManager */
/* renamed from: com.qiniu.android.b.j */
public final class C4330j {
    /* renamed from: a */
    private final C4313a f15093a;
    /* renamed from: b */
    private final C4366a f15094b;

    public C4330j() {
        this(new C4312a().m17060a());
    }

    public C4330j(C4313a c4313a) {
        this.f15093a = c4313a;
        this.f15094b = new C4366a(c4313a.f15038e, c4313a.f15041h, c4313a.f15042i, c4313a.f15044k, c4313a.f15045l);
    }

    /* renamed from: a */
    private static boolean m17109a(final String str, byte[] bArr, File file, String str2, final C1873g c1873g) {
        C4372g c4372g = null;
        if (c1873g == null) {
            throw new IllegalArgumentException("no UpCompletionHandler");
        }
        String str3;
        if (file == null && bArr == null) {
            str3 = "no input data";
        } else if (str2 == null || str2.equals("")) {
            str3 = "no token";
        } else {
            str3 = null;
        }
        if (str3 != null) {
            c4372g = C4372g.m17196a(str3);
        }
        if ((file != null && file.length() == 0) || (bArr != null && bArr.length == 0)) {
            c4372g = C4372g.m17194a();
        }
        if (c4372g == null) {
            return false;
        }
        C4334a.m17117a(new Runnable() {
            public void run() {
                c1873g.mo3261a(str, c4372g, null);
            }
        });
        return true;
    }

    /* renamed from: a */
    public void m17111a(String str, String str2, String str3, C1873g c1873g, C4333k c4333k) {
        m17110a(new File(str), str2, str3, c1873g, c4333k);
    }

    /* renamed from: a */
    public void m17110a(File file, String str, String str2, C1873g c1873g, C4333k c4333k) {
        if (!C4330j.m17109a(str, null, file, str2, c1873g)) {
            C4327i a = C4327i.m17107a(str2);
            if (a == null) {
                final C4372g b = C4372g.m17198b("invalid token");
                final C1873g c1873g2 = c1873g;
                final String str3 = str;
                C4334a.m17117a(new Runnable(this) {
                    /* renamed from: d */
                    final /* synthetic */ C4330j f15092d;

                    public void run() {
                        c1873g2.mo3261a(str3, b, null);
                    }
                });
            } else if (file.length() <= ((long) this.f15093a.f15040g)) {
                C4319b.m17068a(this.f15094b, this.f15093a, file, str, a, c1873g, c4333k);
            } else {
                C4334a.m17117a(new C4325e(this.f15094b, this.f15093a, file, str, a, c1873g, c4333k, this.f15093a.f15037d.mo6022a(str, file)));
            }
        }
    }
}
