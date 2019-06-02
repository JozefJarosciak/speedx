package okhttp3.internal.http;

import okhttp3.C5607q;
import okhttp3.C5621w;
import okhttp3.C5627y;
import okhttp3.internal.C5481j;
import org.apache.http.protocol.HTTP;

/* compiled from: OkHeaders */
/* renamed from: okhttp3.internal.http.j */
public final class C5576j {
    /* renamed from: a */
    static final String f17969a = C5481j.m19770c().m19777d();
    /* renamed from: b */
    public static final String f17970b = (f17969a + "-Sent-Millis");
    /* renamed from: c */
    public static final String f17971c = (f17969a + "-Received-Millis");
    /* renamed from: d */
    public static final String f17972d = (f17969a + "-Selected-Protocol");
    /* renamed from: e */
    public static final String f17973e = (f17969a + "-Response-Source");

    /* renamed from: a */
    public static long m20267a(C5621w c5621w) {
        return C5576j.m20266a(c5621w.m20521c());
    }

    /* renamed from: a */
    public static long m20268a(C5627y c5627y) {
        return C5576j.m20266a(c5627y.m20574f());
    }

    /* renamed from: a */
    public static long m20266a(C5607q c5607q) {
        return C5576j.m20270b(c5607q.m20413a("Content-Length"));
    }

    /* renamed from: b */
    private static long m20270b(String str) {
        long j = -1;
        if (str != null) {
            try {
                j = Long.parseLong(str);
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    /* renamed from: a */
    static boolean m20269a(String str) {
        if ("Connection".equalsIgnoreCase(str) || HTTP.CONN_KEEP_ALIVE.equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }
}
