package com.umeng.analytics;

import android.content.Context;
import p203u.aly.ah;

public class MobclickAgent {
    /* renamed from: a */
    private static final C4752f f7155a = new C4752f();

    /* renamed from: com.umeng.analytics.MobclickAgent$a */
    public static class C1519a {
        /* renamed from: a */
        public String f7150a;
        /* renamed from: b */
        public String f7151b;
        /* renamed from: c */
        public boolean f7152c;
        /* renamed from: d */
        public MobclickAgent$EScenarioType f7153d;
        /* renamed from: e */
        public Context f7154e;

        private C1519a() {
            this.f7150a = null;
            this.f7151b = null;
            this.f7152c = true;
            this.f7153d = MobclickAgent$EScenarioType.E_UM_NORMAL;
            this.f7154e = null;
        }

        public C1519a(Context context, String str, String str2) {
            this(context, str, str2, null, true);
        }

        public C1519a(Context context, String str, String str2, MobclickAgent$EScenarioType mobclickAgent$EScenarioType, boolean z) {
            this.f7150a = null;
            this.f7151b = null;
            this.f7152c = true;
            this.f7153d = MobclickAgent$EScenarioType.E_UM_NORMAL;
            this.f7154e = null;
            this.f7154e = context;
            this.f7150a = str;
            this.f7151b = str2;
            this.f7152c = z;
            if (mobclickAgent$EScenarioType != null) {
                this.f7153d = mobclickAgent$EScenarioType;
                return;
            }
            switch (C4731a.d(context)) {
                case 0:
                    this.f7153d = MobclickAgent$EScenarioType.E_UM_NORMAL;
                    return;
                case 1:
                    this.f7153d = MobclickAgent$EScenarioType.E_UM_GAME;
                    return;
                case 224:
                    this.f7153d = MobclickAgent$EScenarioType.E_UM_ANALYTICS_OEM;
                    return;
                case 225:
                    this.f7153d = MobclickAgent$EScenarioType.E_UM_GAME_OEM;
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public static void m8336a(C1519a c1519a) {
        if (c1519a != null) {
            f7155a.a(c1519a);
        }
    }

    /* renamed from: a */
    public static void m8334a(Context context) {
        f7155a.b(context);
    }

    /* renamed from: b */
    public static void m8337b(Context context) {
        if (context == null) {
            ah.d("unexpected null context in onResume");
        } else {
            f7155a.a(context);
        }
    }

    /* renamed from: a */
    public static void m8335a(Context context, String str) {
        f7155a.a(context, str, null, -1, 1);
    }
}
