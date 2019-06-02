package com.qiniu.android.dns;

import ch.qos.logback.core.CoreConstants;
import com.qiniu.android.dns.local.C4355d;
import com.qiniu.android.dns.p190a.C4344b;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: DnsManager */
/* renamed from: com.qiniu.android.dns.a */
public final class C4345a {
    /* renamed from: a */
    private final C4347c[] f15114a;
    /* renamed from: b */
    private final C4344b<String, C4349f[]> f15115b;
    /* renamed from: c */
    private final C4355d f15116c;
    /* renamed from: d */
    private final C4341d f15117d;
    /* renamed from: e */
    private volatile NetworkInfo f15118e;
    /* renamed from: f */
    private volatile int f15119f;

    /* compiled from: DnsManager */
    /* renamed from: com.qiniu.android.dns.a$a */
    private static class C4342a implements C4341d {
        /* renamed from: a */
        private AtomicInteger f15109a;

        private C4342a() {
            this.f15109a = new AtomicInteger();
        }

        /* renamed from: a */
        public String[] mo6026a(String[] strArr) {
            if (strArr == null || strArr.length <= 1) {
                return strArr;
            }
            int andIncrement = this.f15109a.getAndIncrement() & 255;
            String[] strArr2 = new String[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                strArr2[i] = strArr[(i + andIncrement) % strArr.length];
            }
            return strArr2;
        }
    }

    public C4345a(NetworkInfo networkInfo, C4347c[] c4347cArr) {
        this(networkInfo, c4347cArr, null);
    }

    public C4345a(NetworkInfo networkInfo, C4347c[] c4347cArr, C4341d c4341d) {
        this.f15116c = new C4355d();
        this.f15118e = null;
        this.f15119f = 0;
        if (networkInfo == null) {
            networkInfo = NetworkInfo.f15104b;
        }
        this.f15118e = networkInfo;
        this.f15114a = (C4347c[]) c4347cArr.clone();
        this.f15115b = new C4344b();
        if (c4341d == null) {
            c4341d = new C4342a();
        }
        this.f15117d = c4341d;
    }

    /* renamed from: a */
    private static C4349f[] m17142a(C4349f[] c4349fArr) {
        ArrayList arrayList = new ArrayList(c4349fArr.length);
        for (C4349f c4349f : c4349fArr) {
            if (c4349f != null && c4349f.f15126b == 1) {
                arrayList.add(c4349f);
            }
        }
        return (C4349f[]) arrayList.toArray(new C4349f[arrayList.size()]);
    }

    /* renamed from: b */
    private static String[] m17143b(C4349f[] c4349fArr) {
        if (c4349fArr == null || c4349fArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(c4349fArr.length);
        for (C4349f c4349f : c4349fArr) {
            arrayList.add(c4349f.f15125a);
        }
        if (arrayList.size() != 0) {
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m17141a(String str) {
        if (str == null || str.length() < 7 || str.length() > 15 || str.contains(HelpFormatter.DEFAULT_OPT_PREFIX)) {
            return false;
        }
        try {
            int indexOf = str.indexOf(46);
            if (indexOf != -1 && Integer.parseInt(str.substring(0, indexOf)) > 255) {
                return false;
            }
            indexOf++;
            int indexOf2 = str.indexOf(46, indexOf);
            if (indexOf2 != -1 && Integer.parseInt(str.substring(indexOf, indexOf2)) > 255) {
                return false;
            }
            indexOf2++;
            indexOf = str.indexOf(46, indexOf2);
            if (indexOf == -1 || Integer.parseInt(str.substring(indexOf2, indexOf)) <= 255 || Integer.parseInt(str.substring(indexOf + 1, str.length() - 1)) <= 255 || str.charAt(str.length() - 1) == CoreConstants.DOT) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /* renamed from: a */
    public String[] m17147a(C4346b c4346b) throws IOException {
        if (c4346b == null) {
            throw new IOException("null domain");
        } else if (c4346b.f15120a == null || c4346b.f15120a.trim().length() == 0) {
            throw new IOException("empty domain " + c4346b.f15120a);
        } else if (C4345a.m17141a(c4346b.f15120a)) {
            return new String[]{c4346b.f15120a};
        } else {
            String[] c = m17144c(c4346b);
            return (c == null || c.length <= 1) ? c : this.f15117d.mo6026a(c);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: c */
    private java.lang.String[] m17144c(com.qiniu.android.dns.C4346b r10) throws java.io.IOException {
        /*
        r9 = this;
        r2 = 0;
        r1 = 0;
        r0 = r10.f15123d;
        if (r0 == 0) goto L_0x0014;
    L_0x0006:
        r0 = r9.f15116c;
        r3 = r9.f15118e;
        r0 = r0.m17172a(r10, r3);
        if (r0 == 0) goto L_0x0014;
    L_0x0010:
        r3 = r0.length;
        if (r3 == 0) goto L_0x0014;
    L_0x0013:
        return r0;
    L_0x0014:
        r3 = r9.f15115b;
        monitor-enter(r3);
        r0 = r9.f15118e;	 Catch:{ all -> 0x0087 }
        r4 = com.qiniu.android.dns.NetworkInfo.f15104b;	 Catch:{ all -> 0x0087 }
        r0 = r0.equals(r4);	 Catch:{ all -> 0x0087 }
        if (r0 == 0) goto L_0x008a;
    L_0x0021:
        r0 = com.qiniu.android.dns.C4348e.m17151b();	 Catch:{ all -> 0x0087 }
        if (r0 == 0) goto L_0x008a;
    L_0x0027:
        r0 = r9.f15115b;	 Catch:{ all -> 0x0087 }
        r0.m17139a();	 Catch:{ all -> 0x0087 }
        r4 = r9.f15114a;	 Catch:{ all -> 0x0087 }
        monitor-enter(r4);	 Catch:{ all -> 0x0087 }
        r0 = 0;
        r9.f15119f = r0;	 Catch:{ all -> 0x0084 }
        monitor-exit(r4);	 Catch:{ all -> 0x0084 }
        r0 = r2;
    L_0x0034:
        monitor-exit(r3);	 Catch:{ all -> 0x0087 }
        r3 = r9.f15119f;
        r8 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r8;
    L_0x003b:
        r4 = r9.f15114a;
        r4 = r4.length;
        if (r2 >= r4) goto L_0x00b3;
    L_0x0040:
        r4 = r3 + r2;
        r5 = r9.f15114a;
        r5 = r5.length;
        r4 = r4 % r5;
        r5 = r9.f15118e;
        r6 = com.qiniu.android.dns.C4348e.m17150a();
        r7 = r9.f15114a;	 Catch:{ DomainNotOwn -> 0x00fa, IOException -> 0x00ab }
        r4 = r7[r4];	 Catch:{ DomainNotOwn -> 0x00fa, IOException -> 0x00ab }
        r7 = r9.f15118e;	 Catch:{ DomainNotOwn -> 0x00fa, IOException -> 0x00ab }
        r1 = r4.mo6027a(r10, r7);	 Catch:{ DomainNotOwn -> 0x00fa, IOException -> 0x00ab }
    L_0x0056:
        r4 = com.qiniu.android.dns.C4348e.m17150a();
        r7 = r9.f15118e;
        if (r7 != r5) goto L_0x00b3;
    L_0x005e:
        if (r1 == 0) goto L_0x0063;
    L_0x0060:
        r5 = r1.length;
        if (r5 != 0) goto L_0x00b3;
    L_0x0063:
        r4 = r6.equals(r4);
        if (r4 == 0) goto L_0x00b3;
    L_0x0069:
        r4 = r9.f15114a;
        monitor-enter(r4);
        r5 = r9.f15119f;	 Catch:{ all -> 0x00b0 }
        if (r5 != r3) goto L_0x0080;
    L_0x0070:
        r5 = r9.f15119f;	 Catch:{ all -> 0x00b0 }
        r5 = r5 + 1;
        r9.f15119f = r5;	 Catch:{ all -> 0x00b0 }
        r5 = r9.f15119f;	 Catch:{ all -> 0x00b0 }
        r6 = r9.f15114a;	 Catch:{ all -> 0x00b0 }
        r6 = r6.length;	 Catch:{ all -> 0x00b0 }
        if (r5 != r6) goto L_0x0080;
    L_0x007d:
        r5 = 0;
        r9.f15119f = r5;	 Catch:{ all -> 0x00b0 }
    L_0x0080:
        monitor-exit(r4);	 Catch:{ all -> 0x00b0 }
    L_0x0081:
        r2 = r2 + 1;
        goto L_0x003b;
    L_0x0084:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0084 }
        throw r0;	 Catch:{ all -> 0x0087 }
    L_0x0087:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0087 }
        throw r0;
    L_0x008a:
        r0 = r9.f15115b;	 Catch:{ all -> 0x0087 }
        r4 = r10.f15120a;	 Catch:{ all -> 0x0087 }
        r0 = r0.m17138a(r4);	 Catch:{ all -> 0x0087 }
        r0 = (com.qiniu.android.dns.C4349f[]) r0;	 Catch:{ all -> 0x0087 }
        if (r0 == 0) goto L_0x0034;
    L_0x0096:
        r4 = r0.length;	 Catch:{ all -> 0x0087 }
        if (r4 == 0) goto L_0x0034;
    L_0x0099:
        r4 = 0;
        r4 = r0[r4];	 Catch:{ all -> 0x0087 }
        r4 = r4.m17154b();	 Catch:{ all -> 0x0087 }
        if (r4 != 0) goto L_0x00a9;
    L_0x00a2:
        r0 = com.qiniu.android.dns.C4345a.m17143b(r0);	 Catch:{ all -> 0x0087 }
        monitor-exit(r3);	 Catch:{ all -> 0x0087 }
        goto L_0x0013;
    L_0x00a9:
        r0 = r2;
        goto L_0x0034;
    L_0x00ab:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0056;
    L_0x00b0:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x00b0 }
        throw r0;
    L_0x00b3:
        r8 = r0;
        r0 = r1;
        r1 = r8;
        if (r0 == 0) goto L_0x00bb;
    L_0x00b8:
        r2 = r0.length;
        if (r2 != 0) goto L_0x00d7;
    L_0x00bb:
        r0 = r10.f15123d;
        if (r0 != 0) goto L_0x00cc;
    L_0x00bf:
        r0 = r9.f15116c;
        r2 = r9.f15118e;
        r0 = r0.m17172a(r10, r2);
        if (r0 == 0) goto L_0x00cc;
    L_0x00c9:
        r2 = r0.length;
        if (r2 != 0) goto L_0x0013;
    L_0x00cc:
        if (r1 == 0) goto L_0x00cf;
    L_0x00ce:
        throw r1;
    L_0x00cf:
        r0 = new java.net.UnknownHostException;
        r1 = r10.f15120a;
        r0.<init>(r1);
        throw r0;
    L_0x00d7:
        r0 = com.qiniu.android.dns.C4345a.m17142a(r0);
        r1 = r0.length;
        if (r1 != 0) goto L_0x00e6;
    L_0x00de:
        r0 = new java.net.UnknownHostException;
        r1 = "no A records";
        r0.<init>(r1);
        throw r0;
    L_0x00e6:
        r1 = r9.f15115b;
        monitor-enter(r1);
        r2 = r9.f15115b;	 Catch:{ all -> 0x00f7 }
        r3 = r10.f15120a;	 Catch:{ all -> 0x00f7 }
        r2.m17137a(r3, r0);	 Catch:{ all -> 0x00f7 }
        monitor-exit(r1);	 Catch:{ all -> 0x00f7 }
        r0 = com.qiniu.android.dns.C4345a.m17143b(r0);
        goto L_0x0013;
    L_0x00f7:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00f7 }
        throw r0;
    L_0x00fa:
        r4 = move-exception;
        goto L_0x0081;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qiniu.android.dns.a.c(com.qiniu.android.dns.b):java.lang.String[]");
    }

    /* renamed from: b */
    public InetAddress[] m17148b(C4346b c4346b) throws IOException {
        String[] a = m17147a(c4346b);
        InetAddress[] inetAddressArr = new InetAddress[a.length];
        for (int i = 0; i < a.length; i++) {
            inetAddressArr[i] = InetAddress.getByName(a[i]);
        }
        return inetAddressArr;
    }

    /* renamed from: a */
    public void m17146a(NetworkInfo networkInfo) {
        m17140a();
        if (networkInfo == null) {
            networkInfo = NetworkInfo.f15104b;
        }
        this.f15118e = networkInfo;
        synchronized (this.f15114a) {
            this.f15119f = 0;
        }
    }

    /* renamed from: a */
    private void m17140a() {
        synchronized (this.f15115b) {
            this.f15115b.m17139a();
        }
    }

    /* renamed from: a */
    public C4345a m17145a(String str, String str2) {
        this.f15116c.m17171a(str, str2);
        return this;
    }
}
