package com.beastbikes.framework.android.p088g;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/* compiled from: DimensionUtils */
/* renamed from: com.beastbikes.framework.android.g.d */
public class C2801d {
    /* renamed from: a */
    private static C2800a f13020a = null;

    /* compiled from: DimensionUtils */
    /* renamed from: com.beastbikes.framework.android.g.d$a */
    public static class C2800a {
        /* renamed from: a */
        public int f13018a;
        /* renamed from: b */
        public int f13019b;

        public C2800a(int i, int i2) {
            this.f13018a = i;
            this.f13019b = i2;
        }

        public String toString() {
            return "(" + this.f13018a + "," + this.f13019b + ")";
        }
    }

    /* renamed from: a */
    public static int m13756a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    /* renamed from: a */
    public static int m13755a(Context context) {
        C2801d.m13758c(context);
        if (f13020a != null) {
            return f13020a.f13018a;
        }
        return 0;
    }

    /* renamed from: b */
    public static int m13757b(Context context) {
        C2801d.m13758c(context);
        if (f13020a != null) {
            return f13020a.f13019b;
        }
        return 0;
    }

    /* renamed from: c */
    private static void m13758c(Context context) {
        if (f13020a == null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            f13020a = new C2800a(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
    }
}
