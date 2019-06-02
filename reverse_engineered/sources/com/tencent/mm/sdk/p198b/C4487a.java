package com.tencent.mm.sdk.p198b;

import android.os.Bundle;

/* renamed from: com.tencent.mm.sdk.b.a */
public final class C4487a {
    /* renamed from: a */
    public static int m17820a(Bundle bundle, String str) {
        int i = -1;
        if (bundle != null) {
            try {
                i = bundle.getInt(str, -1);
            } catch (Exception e) {
                C4489b.m17826a("MicroMsg.IntentUtil", "getIntExtra exception:%s", e.getMessage());
            }
        }
        return i;
    }

    /* renamed from: b */
    public static String m17821b(Bundle bundle, String str) {
        String str2 = null;
        if (bundle != null) {
            try {
                str2 = bundle.getString(str);
            } catch (Exception e) {
                C4489b.m17826a("MicroMsg.IntentUtil", "getStringExtra exception:%s", e.getMessage());
            }
        }
        return str2;
    }
}
