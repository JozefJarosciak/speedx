package com.umeng.analytics;

import android.content.Context;
import p203u.aly.C5959x;
import p203u.aly.aa;
import p203u.aly.af;

/* compiled from: ReportPolicy */
/* renamed from: com.umeng.analytics.b */
public class C4742b {

    /* compiled from: ReportPolicy */
    /* renamed from: com.umeng.analytics.b$h */
    public static class C4732h {
        /* renamed from: a */
        public boolean mo6177a(boolean z) {
            return true;
        }

        /* renamed from: a */
        public boolean mo6178a() {
            return true;
        }
    }

    /* compiled from: ReportPolicy */
    /* renamed from: com.umeng.analytics.b$a */
    public static class C4733a extends C4732h {
        /* renamed from: a */
        private final long f16621a = 15000;
        /* renamed from: b */
        private C5959x f16622b;

        public C4733a(C5959x c5959x) {
            this.f16622b = c5959x;
        }

        /* renamed from: a */
        public boolean mo6177a(boolean z) {
            if (System.currentTimeMillis() - this.f16622b.f19111c >= 15000) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: ReportPolicy */
    /* renamed from: com.umeng.analytics.b$b */
    public static class C4734b extends C4732h {
        /* renamed from: a */
        private aa f16623a;
        /* renamed from: b */
        private C5959x f16624b;

        public C4734b(C5959x c5959x, aa aaVar) {
            this.f16624b = c5959x;
            this.f16623a = aaVar;
        }

        /* renamed from: a */
        public boolean mo6177a(boolean z) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f16624b.f19111c >= this.f16623a.m21085a()) {
                return true;
            }
            return false;
        }

        /* renamed from: a */
        public boolean mo6178a() {
            return this.f16623a.m21090b();
        }
    }

    /* compiled from: ReportPolicy */
    /* renamed from: com.umeng.analytics.b$c */
    public static class C4735c extends C4732h {
        /* renamed from: a */
        private long f16625a;
        /* renamed from: b */
        private long f16626b = 0;

        public C4735c(int i) {
            this.f16625a = (long) i;
            this.f16626b = System.currentTimeMillis();
        }

        /* renamed from: a */
        public boolean mo6177a(boolean z) {
            if (System.currentTimeMillis() - this.f16626b >= this.f16625a) {
                return true;
            }
            return false;
        }

        /* renamed from: a */
        public boolean mo6178a() {
            return System.currentTimeMillis() - this.f16626b < this.f16625a;
        }
    }

    /* compiled from: ReportPolicy */
    /* renamed from: com.umeng.analytics.b$d */
    public static class C4736d extends C4732h {
        /* renamed from: a */
        public boolean mo6177a(boolean z) {
            return z;
        }
    }

    /* compiled from: ReportPolicy */
    /* renamed from: com.umeng.analytics.b$e */
    public static class C4737e extends C4732h {
        /* renamed from: a */
        private static long f16627a = 90000;
        /* renamed from: b */
        private static long f16628b = 86400000;
        /* renamed from: c */
        private long f16629c;
        /* renamed from: d */
        private C5959x f16630d;

        public C4737e(C5959x c5959x, long j) {
            this.f16630d = c5959x;
            m18631a(j);
        }

        /* renamed from: a */
        public boolean mo6177a(boolean z) {
            if (System.currentTimeMillis() - this.f16630d.f19111c >= this.f16629c) {
                return true;
            }
            return false;
        }

        /* renamed from: a */
        public void m18631a(long j) {
            if (j < f16627a || j > f16628b) {
                this.f16629c = f16627a;
            } else {
                this.f16629c = j;
            }
        }
    }

    /* compiled from: ReportPolicy */
    /* renamed from: com.umeng.analytics.b$f */
    public static class C4738f extends C4732h {
        /* renamed from: a */
        private long f16631a = 86400000;
        /* renamed from: b */
        private C5959x f16632b;

        public C4738f(C5959x c5959x) {
            this.f16632b = c5959x;
        }

        /* renamed from: a */
        public boolean mo6177a(boolean z) {
            if (System.currentTimeMillis() - this.f16632b.f19111c >= this.f16631a) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: ReportPolicy */
    /* renamed from: com.umeng.analytics.b$g */
    public static class C4739g extends C4732h {
        /* renamed from: a */
        public boolean mo6177a(boolean z) {
            return true;
        }
    }

    /* compiled from: ReportPolicy */
    /* renamed from: com.umeng.analytics.b$i */
    public static class C4740i extends C4732h {
        /* renamed from: a */
        private Context f16633a = null;

        public C4740i(Context context) {
            this.f16633a = context;
        }

        /* renamed from: a */
        public boolean mo6177a(boolean z) {
            return af.m21127i(this.f16633a);
        }
    }

    /* compiled from: ReportPolicy */
    /* renamed from: com.umeng.analytics.b$j */
    public static class C4741j extends C4732h {
        /* renamed from: a */
        private final long f16634a = 10800000;
        /* renamed from: b */
        private C5959x f16635b;

        public C4741j(C5959x c5959x) {
            this.f16635b = c5959x;
        }

        /* renamed from: a */
        public boolean mo6177a(boolean z) {
            if (System.currentTimeMillis() - this.f16635b.f19111c >= 10800000) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m18637a(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
                return true;
            default:
                return false;
        }
    }
}
