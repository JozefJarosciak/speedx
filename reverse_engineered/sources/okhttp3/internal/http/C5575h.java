package okhttp3.internal.http;

/* compiled from: HttpMethod */
/* renamed from: okhttp3.internal.http.h */
public final class C5575h {
    /* renamed from: a */
    public static boolean m20262a(String str) {
        return str.equals("POST") || str.equals("PATCH") || str.equals("PUT") || str.equals("DELETE") || str.equals("MOVE");
    }

    /* renamed from: b */
    public static boolean m20263b(String str) {
        return str.equals("POST") || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    /* renamed from: c */
    public static boolean m20264c(String str) {
        if (C5575h.m20263b(str) || str.equals("OPTIONS") || str.equals("DELETE") || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK")) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    public static boolean m20265d(String str) {
        return !str.equals("PROPFIND");
    }
}
