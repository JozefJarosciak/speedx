package com.tencent.mm.sdk.p198b;

import ch.qos.logback.core.CoreConstants;
import java.util.TimeZone;

/* renamed from: com.tencent.mm.sdk.b.h */
public final class C4497h {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final long[] bh = new long[]{300, 200, 300, 200};
    private static final long[] bi = new long[]{300, 50, 300, 50};
    private static final char[] bj = new char[]{'<', '>', CoreConstants.DOUBLE_QUOTE_CHAR, CoreConstants.SINGLE_QUOTE_CHAR, '&', '\r', '\n', ' ', '\t'};
    private static final String[] bk = new String[]{"&lt;", "&gt;", "&quot;", "&apos;", "&amp;", "&#x0D;", "&#x0A;", "&#x20;", "&#x09;"};

    /* renamed from: h */
    public static boolean m17842h(String str) {
        return str == null || str.length() <= 0;
    }

    /* renamed from: u */
    public static C4495f m17843u() {
        return new C4495f();
    }
}
