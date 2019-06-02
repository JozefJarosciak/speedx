package io.fabric.sdk.android;

import android.os.SystemClock;
import android.text.TextUtils;
import io.fabric.sdk.android.services.common.C4877i;
import java.io.Closeable;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: FabricKitsFinder */
/* renamed from: io.fabric.sdk.android.e */
class C4839e implements Callable<Map<String, C4847j>> {
    /* renamed from: a */
    final String f17088a;

    public /* synthetic */ Object call() throws Exception {
        return m19014a();
    }

    C4839e(String str) {
        this.f17088a = str;
    }

    /* renamed from: a */
    public Map<String, C4847j> m19014a() throws Exception {
        Map<String, C4847j> hashMap = new HashMap();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        ZipFile b = m19015b();
        Enumeration entries = b.entries();
        int i = 0;
        while (entries.hasMoreElements()) {
            int i2 = i + 1;
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.getName().startsWith("fabric/") && zipEntry.getName().length() > "fabric/".length()) {
                C4847j a = m19013a(zipEntry, b);
                if (a != null) {
                    hashMap.put(a.m19056a(), a);
                    C1520c.h().mo6218b("Fabric", String.format("Found kit:[%s] version:[%s]", new Object[]{a.m19056a(), a.m19057b()}));
                }
            }
            i = i2;
        }
        if (b != null) {
            try {
                b.close();
            } catch (IOException e) {
            }
        }
        C1520c.h().mo6218b("Fabric", "finish scanning in " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " reading:" + i);
        return hashMap;
    }

    /* renamed from: a */
    private C4847j m19013a(ZipEntry zipEntry, ZipFile zipFile) {
        Throwable e;
        Closeable inputStream;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                Properties properties = new Properties();
                properties.load(inputStream);
                Object property = properties.getProperty("fabric-identifier");
                Object property2 = properties.getProperty("fabric-version");
                String property3 = properties.getProperty("fabric-build-type");
                if (TextUtils.isEmpty(property) || TextUtils.isEmpty(property2)) {
                    throw new IllegalStateException("Invalid format of fabric file," + zipEntry.getName());
                }
                C4847j c4847j = new C4847j(property, property2, property3);
                C4877i.m19159a(inputStream);
                return c4847j;
            } catch (IOException e2) {
                e = e2;
                try {
                    C1520c.h().mo6222d("Fabric", "Error when parsing fabric properties " + zipEntry.getName(), e);
                    C4877i.m19159a(inputStream);
                    return null;
                } catch (Throwable th) {
                    e = th;
                    C4877i.m19159a(inputStream);
                    throw e;
                }
            }
        } catch (IOException e3) {
            e = e3;
            inputStream = null;
            C1520c.h().mo6222d("Fabric", "Error when parsing fabric properties " + zipEntry.getName(), e);
            C4877i.m19159a(inputStream);
            return null;
        } catch (Throwable th2) {
            e = th2;
            inputStream = null;
            C4877i.m19159a(inputStream);
            throw e;
        }
    }

    /* renamed from: b */
    protected ZipFile m19015b() throws IOException {
        return new ZipFile(this.f17088a);
    }
}
