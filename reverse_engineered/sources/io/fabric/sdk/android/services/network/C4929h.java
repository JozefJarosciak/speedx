package io.fabric.sdk.android.services.network;

import android.text.TextUtils;
import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.TreeMap;

/* compiled from: UrlUtils */
/* renamed from: io.fabric.sdk.android.services.network.h */
public final class C4929h {
    /* renamed from: a */
    public static TreeMap<String, String> m19368a(URI uri, boolean z) {
        return C4929h.m19367a(uri.getRawQuery(), z);
    }

    /* renamed from: a */
    public static TreeMap<String, String> m19367a(String str, boolean z) {
        TreeMap<String, String> treeMap = new TreeMap();
        if (str != null) {
            for (String split : str.split(C0869a.f2161b)) {
                String[] split2 = split.split(SimpleComparison.EQUAL_TO_OPERATION);
                if (split2.length == 2) {
                    if (z) {
                        treeMap.put(C4929h.m19369b(split2[0]), C4929h.m19369b(split2[1]));
                    } else {
                        treeMap.put(split2[0], split2[1]);
                    }
                } else if (!TextUtils.isEmpty(split2[0])) {
                    if (z) {
                        treeMap.put(C4929h.m19369b(split2[0]), "");
                    } else {
                        treeMap.put(split2[0], "");
                    }
                }
            }
        }
        return treeMap;
    }

    /* renamed from: a */
    public static String m19366a(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF8");
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /* renamed from: b */
    public static String m19369b(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLDecoder.decode(str, "UTF8");
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /* renamed from: c */
    public static String m19370c(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        String a = C4929h.m19366a(str);
        int length = a.length();
        int i = 0;
        while (i < length) {
            char charAt = a.charAt(i);
            if (charAt == '*') {
                stringBuilder.append("%2A");
            } else if (charAt == '+') {
                stringBuilder.append("%20");
            } else if (charAt == CoreConstants.PERCENT_CHAR && i + 2 < length && a.charAt(i + 1) == '7' && a.charAt(i + 2) == 'E') {
                stringBuilder.append('~');
                i += 2;
            } else {
                stringBuilder.append(charAt);
            }
            i++;
        }
        return stringBuilder.toString();
    }
}
