package com.qiniu.android.p110b;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.core.joran.action.Action;
import com.qiniu.android.http.C4314e;
import com.qiniu.android.http.C4316b;
import com.qiniu.android.http.C4366a;
import com.qiniu.android.http.C4370d;
import com.qiniu.android.http.C4372g;
import com.qiniu.android.p189c.C4335b;
import com.qiniu.android.p189c.C4337c;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import org.json.JSONObject;

/* compiled from: FormUploader */
/* renamed from: com.qiniu.android.b.b */
final class C4319b {
    /* renamed from: a */
    static void m17068a(C4366a c4366a, C4313a c4313a, File file, String str, C4327i c4327i, C1873g c1873g, C4333k c4333k) {
        C4319b.m17069a(null, file, str, c4327i, c1873g, c4333k, c4366a, c4313a);
    }

    /* renamed from: a */
    private static void m17069a(byte[] bArr, File file, final String str, C4327i c4327i, C1873g c1873g, C4333k c4333k, C4366a c4366a, C4313a c4313a) {
        C4337c c4337c = new C4337c();
        final C4370d c4370d = new C4370d();
        if (str != null) {
            c4337c.m17122a(Action.KEY_ATTRIBUTE, str);
            c4370d.f15166d = str;
        } else {
            c4370d.f15166d = CallerData.NA;
        }
        if (file != null) {
            c4370d.f15166d = file.getName();
        }
        c4337c.m17122a("token", c4327i.f15084a);
        final C4333k a = c4333k != null ? c4333k : C4333k.m17114a();
        c4337c.m17123a(a.f15097a);
        if (a.f15099c) {
            long j = 0;
            if (file != null) {
                try {
                    j = C4335b.m17118a(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                j = C4335b.m17119a(bArr);
            }
            c4337c.m17122a("crc32", "" + j);
        }
        final C4314e c43151 = new C4314e() {
            /* renamed from: a */
            public void mo6023a(int i, int i2) {
                double d = 0.95d;
                double d2 = ((double) i) / ((double) i2);
                if (d2 <= 0.95d) {
                    d = d2;
                }
                a.f15100d.mo3262a(str, d);
            }
        };
        c4370d.f15163a = bArr;
        c4370d.f15164b = file;
        c4370d.f15167e = a.f15098b;
        c4370d.f15165c = c4337c;
        final String str2 = str;
        final C1873g c1873g2 = c1873g;
        final C4327i c4327i2 = c4327i;
        final C4313a c4313a2 = c4313a;
        final C4366a c4366a2 = c4366a;
        c4366a.m17186a(c4313a.f15034a.f15015a.toString(), c4370d, c43151, (C4316b) new C4316b() {

            /* compiled from: FormUploader */
            /* renamed from: com.qiniu.android.b.b$2$1 */
            class C43171 implements C4316b {
                /* renamed from: a */
                final /* synthetic */ C43182 f15048a;

                C43171(C43182 c43182) {
                    this.f15048a = c43182;
                }

                /* renamed from: a */
                public void mo6024a(C4372g c4372g, JSONObject jSONObject) {
                    if (c4372g.m17200d()) {
                        a.f15100d.mo3262a(str2, 1.0d);
                    }
                    c1873g2.mo3261a(str2, c4372g, jSONObject);
                }
            }

            /* renamed from: a */
            public void mo6024a(C4372g c4372g, JSONObject jSONObject) {
                if (c4372g.m17200d()) {
                    a.f15100d.mo3262a(str2, 1.0d);
                    c1873g2.mo3261a(str2, c4372g, jSONObject);
                } else if (a.f15101e.mo6025a()) {
                    c1873g2.mo3261a(str2, C4372g.m17197b(), null);
                } else if (c4372g.m17204h() || (c4372g.m17205i() && !c4327i2.m17108a())) {
                    URI uri;
                    C4316b c43171 = new C43171(this);
                    URI uri2 = c4313a2.f15034a.f15015a;
                    if (c4372g.m17203g() || c4372g.m17205i()) {
                        uri = c4313a2.f15035b.f15015a;
                    } else {
                        uri = uri2;
                    }
                    c4366a2.m17186a(uri.toString(), c4370d, c43151, c43171, a.f15101e);
                } else {
                    c1873g2.mo3261a(str2, c4372g, jSONObject);
                }
            }
        }, a.f15101e);
    }
}
