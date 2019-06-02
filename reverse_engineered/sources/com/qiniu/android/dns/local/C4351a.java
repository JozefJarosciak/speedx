package com.qiniu.android.dns.local;

import com.qiniu.android.dns.C4346b;
import com.qiniu.android.dns.C4347c;
import com.qiniu.android.dns.C4349f;
import com.qiniu.android.dns.NetworkInfo;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: AndroidDnsServer */
/* renamed from: com.qiniu.android.dns.local.a */
public final class C4351a {

    /* compiled from: AndroidDnsServer */
    /* renamed from: com.qiniu.android.dns.local.a$1 */
    static class C43501 implements C4347c {
        C43501() {
        }

        /* renamed from: a */
        public C4349f[] mo6027a(C4346b c4346b, NetworkInfo networkInfo) throws IOException {
            InetAddress[] a;
            InetAddress[] b = C4351a.m17157b();
            if (b == null) {
                a = C4351a.m17156a();
            } else {
                a = b;
            }
            if (a == null) {
                throw new IOException("cant get local dns server");
            }
            int i;
            int length;
            C4349f c4349f;
            C4349f[] a2 = new C4353c(new C4356e(a[0])).mo6027a(c4346b, networkInfo);
            if (c4346b.f15121b) {
                for (C4349f c4349f2 : a2) {
                    if (c4349f2.m17152a()) {
                        i = 1;
                        break;
                    }
                }
                i = 0;
                if (i == 0) {
                    throw new DnshijackingException(c4346b.f15120a, a[0].getHostAddress());
                }
            }
            if (c4346b.f15122c != 0) {
                length = a2.length;
                i = 0;
                while (i < length) {
                    c4349f2 = a2[i];
                    if (c4349f2.m17152a() || c4349f2.f15127c <= c4346b.f15122c) {
                        i++;
                    } else {
                        throw new DnshijackingException(c4346b.f15120a, a[0].getHostAddress(), c4349f2.f15127c);
                    }
                }
            }
            return a2;
        }
    }

    /* renamed from: a */
    public static InetAddress[] m17156a() {
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("getprop").getInputStream()));
            ArrayList arrayList = new ArrayList(5);
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                }
                int indexOf = readLine.indexOf("]: [");
                if (indexOf != -1) {
                    String substring = readLine.substring(1, indexOf);
                    readLine = readLine.substring(indexOf + 4, readLine.length() - 1);
                    if (substring.endsWith(".dns") || substring.endsWith(".dns1") || substring.endsWith(".dns2") || substring.endsWith(".dns3") || substring.endsWith(".dns4")) {
                        InetAddress byName = InetAddress.getByName(readLine);
                        if (byName != null) {
                            String hostAddress = byName.getHostAddress();
                            if (!(hostAddress == null || hostAddress.length() == 0)) {
                                arrayList.add(byName);
                            }
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
            }
        } catch (Throwable e) {
            Logger.getLogger("AndroidDnsServer").log(Level.WARNING, "Exception in findDNSByExec", e);
        }
        return null;
    }

    /* renamed from: b */
    public static InetAddress[] m17157b() {
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class});
            ArrayList arrayList = new ArrayList(5);
            int length = new String[]{"net.dns1", "net.dns2", "net.dns3", "net.dns4"}.length;
            for (int i = 0; i < length; i++) {
                String str = (String) method.invoke(null, new Object[]{r5[i]});
                if (!(str == null || str.length() == 0)) {
                    InetAddress byName = InetAddress.getByName(str);
                    if (byName != null) {
                        String hostAddress = byName.getHostAddress();
                        if (!(hostAddress == null || hostAddress.length() == 0 || arrayList.contains(byName))) {
                            arrayList.add(byName);
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
            }
        } catch (Throwable e) {
            Logger.getLogger("AndroidDnsServer").log(Level.WARNING, "Exception in findDNSByReflection", e);
        }
        return null;
    }

    /* renamed from: c */
    public static C4347c m17158c() {
        return new C43501();
    }
}
