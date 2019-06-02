package com.twitter.sdk.android.core.internal.scribe;

import android.os.Build.VERSION;
import android.text.TextUtils;
import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4655n;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.services.common.C4882k;
import io.fabric.sdk.android.services.common.C4883l;
import io.fabric.sdk.android.services.settings.C4951q;
import io.fabric.sdk.android.services.settings.C4952s;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: DefaultScribeClient */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.a */
public class C4629a extends C4628d {
    /* renamed from: b */
    private static volatile ScheduledExecutorService f16320b;
    /* renamed from: c */
    private final C1468h f16321c;
    /* renamed from: d */
    private final List<C4586l<? extends C1469k>> f16322d;
    /* renamed from: e */
    private final String f16323e;

    public C4629a(C1468h c1468h, String str, List<C4586l<? extends C1469k>> list, C4883l c4883l) {
        this(c1468h, str, C4629a.m18317b(), list, c4883l);
    }

    public C4629a(C1468h c1468h, String str, Gson gson, List<C4586l<? extends C1469k>> list, C4883l c4883l) {
        super(c1468h, C4629a.m18318c(), C4629a.m18314a(C4951q.m19411a().m19414b(), C4629a.m18315a(str, c1468h)), new f$a(gson), C4655n.m18381a().m18387b(), list, C4655n.m18381a().m18390e(), c4883l);
        this.f16322d = list;
        this.f16321c = c1468h;
        this.f16323e = c4883l.m19192i();
    }

    /* renamed from: a */
    public void m18324a(C1502c... c1502cArr) {
        for (C1502c a : c1502cArr) {
            m18322a(a, Collections.emptyList());
        }
    }

    /* renamed from: a */
    public void m18322a(C1502c c1502c, List<C1506j> list) {
        String language;
        if (this.f16321c.q() != null) {
            language = this.f16321c.q().getResources().getConfiguration().locale.getLanguage();
        } else {
            language = "";
        }
        m18323a(C4637g.m18349a(c1502c, System.currentTimeMillis(), language, this.f16323e, list));
    }

    /* renamed from: a */
    public void m18323a(C1503f c1503f) {
        super.m18311a(c1503f, m18320a(m18321a()));
    }

    /* renamed from: a */
    C1469k m18321a() {
        C1469k c1469k = null;
        for (C4586l b : this.f16322d) {
            c1469k = b.mo6132b();
            if (c1469k != null) {
                break;
            }
        }
        return c1469k;
    }

    /* renamed from: a */
    long m18320a(C1469k c1469k) {
        if (c1469k != null) {
            return c1469k.e();
        }
        return 0;
    }

    /* renamed from: b */
    private static Gson m18317b() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    /* renamed from: c */
    private static ScheduledExecutorService m18318c() {
        if (f16320b == null) {
            synchronized (C4629a.class) {
                if (f16320b == null) {
                    f16320b = C4882k.m19177a("scribe");
                }
            }
        }
        return f16320b;
    }

    /* renamed from: a */
    static C4635e m18314a(C4952s c4952s, String str) {
        int i;
        int i2;
        if (c4952s == null || c4952s.f17345e == null) {
            i = 100;
            i2 = 600;
        } else {
            i = c4952s.f17345e.f17280e;
            i2 = c4952s.f17345e.f17277b;
        }
        return new C4635e(C4629a.m18319d(), C4629a.m18316a("https://syndication.twitter.com", ""), IntegerTokenConverter.CONVERTER_KEY, "sdk", "", str, i, i2);
    }

    /* renamed from: d */
    private static boolean m18319d() {
        return !"release".equals("debug");
    }

    /* renamed from: a */
    static String m18315a(String str, C1468h c1468h) {
        return "Fabric/" + c1468h.r().b() + " (Android " + VERSION.SDK_INT + ") " + str + "/" + c1468h.c();
    }

    /* renamed from: a */
    static String m18316a(String str, String str2) {
        return !TextUtils.isEmpty(str2) ? str2 : str;
    }
}
