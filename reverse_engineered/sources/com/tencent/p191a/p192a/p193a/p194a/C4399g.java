package com.tencent.p191a.p192a.p193a.p194a;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.tencent.a.a.a.a.g */
public final class C4399g {
    /* renamed from: V */
    private static C4399g f15190V = null;
    /* renamed from: U */
    private Map<Integer, C4394f> f15191U = null;
    /* renamed from: b */
    private int f15192b = 0;
    /* renamed from: c */
    private Context f15193c = null;

    private C4399g(Context context) {
        this.f15193c = context.getApplicationContext();
        this.f15191U = new HashMap(3);
        this.f15191U.put(Integer.valueOf(1), new C4398e(context));
        this.f15191U.put(Integer.valueOf(2), new C4395b(context));
        this.f15191U.put(Integer.valueOf(4), new C4397d(context));
    }

    /* renamed from: E */
    public static synchronized C4399g m17233E(Context context) {
        C4399g c4399g;
        synchronized (C4399g.class) {
            if (f15190V == null) {
                f15190V = new C4399g(context);
            }
            c4399g = f15190V;
        }
        return c4399g;
    }

    /* renamed from: b */
    private C4396c m17234b(List<Integer> list) {
        if (list.size() >= 0) {
            for (Integer num : list) {
                C4394f c4394f = (C4394f) this.f15191U.get(num);
                if (c4394f != null) {
                    C4396c o = c4394f.m17220o();
                    if (o != null && C4400h.m17243c(o.f15189c)) {
                        return o;
                    }
                }
            }
        }
        return new C4396c();
    }

    /* renamed from: a */
    public final void m17235a(String str) {
        C4396c p = m17236p();
        p.f15189c = str;
        if (!C4400h.m17242b(p.f15187a)) {
            p.f15187a = C4400h.m17237a(this.f15193c);
        }
        if (!C4400h.m17242b(p.f15188b)) {
            p.f15188b = C4400h.m17241b(this.f15193c);
        }
        p.f15186T = System.currentTimeMillis();
        for (Entry value : this.f15191U.entrySet()) {
            ((C4394f) value.getValue()).m17216a(p);
        }
    }

    /* renamed from: p */
    public final C4396c m17236p() {
        return m17234b(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(4)})));
    }
}
