package com.beastbikes.android.modules.preferences.ui.offlineMap.p143d.p144a;

import com.beastbikes.android.modules.preferences.ui.offlineMap.p143d.p144a.C2324a.C2323a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: PinyinUtil */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.d.a.b */
public class C2325b {
    /* renamed from: a */
    public static String m11883a(String str) {
        ArrayList a = C2324a.m11880a().m11882a(str);
        StringBuilder stringBuilder = new StringBuilder();
        if (a != null && a.size() > 0) {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                C2323a c2323a = (C2323a) it.next();
                if (2 == c2323a.f11049a) {
                    stringBuilder.append(c2323a.f11051c);
                } else {
                    stringBuilder.append(c2323a.f11050b);
                }
            }
        }
        return stringBuilder.toString().toLowerCase(Locale.CHINA);
    }
}
