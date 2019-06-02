package okhttp3.internal.http;

import java.net.Proxy.Type;
import okhttp3.C5621w;
import okhttp3.HttpUrl;

/* compiled from: RequestLine */
/* renamed from: okhttp3.internal.http.l */
public final class C5578l {
    /* renamed from: a */
    static String m20272a(C5621w c5621w, Type type) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c5621w.m20520b());
        stringBuilder.append(' ');
        if (C5578l.m20273b(c5621w, type)) {
            stringBuilder.append(c5621w.m20519a());
        } else {
            stringBuilder.append(C5578l.m20271a(c5621w.m20519a()));
        }
        stringBuilder.append(" HTTP/1.1");
        return stringBuilder.toString();
    }

    /* renamed from: b */
    private static boolean m20273b(C5621w c5621w, Type type) {
        return !c5621w.m20526h() && type == Type.HTTP;
    }

    /* renamed from: a */
    public static String m20271a(HttpUrl httpUrl) {
        String h = httpUrl.m19678h();
        String k = httpUrl.m19681k();
        return k != null ? h + '?' + k : h;
    }
}
