package com.tencent.mm.sdk.p199c;

import android.net.Uri;
import android.provider.BaseColumns;
import com.tencent.mm.sdk.p198b.C4489b;

/* renamed from: com.tencent.mm.sdk.c.a */
public final class C4500a {

    /* renamed from: com.tencent.mm.sdk.c.a$a */
    public static final class C4498a {
        /* renamed from: a */
        public static Object m17844a(int i, String str) {
            switch (i) {
                case 1:
                    return Integer.valueOf(str);
                case 2:
                    return Long.valueOf(str);
                case 3:
                    return str;
                case 4:
                    return Boolean.valueOf(str);
                case 5:
                    return Float.valueOf(str);
                case 6:
                    return Double.valueOf(str);
                default:
                    try {
                        C4489b.m17827b("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
            }
        }
    }

    /* renamed from: com.tencent.mm.sdk.c.a$b */
    public static final class C4499b implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
    }
}
