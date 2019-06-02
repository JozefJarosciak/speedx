package com.digits.sdk.android;

import android.content.Context;
import android.content.res.TypedArray;
import java.lang.reflect.Field;

/* compiled from: ActivityClassManagerFactory */
/* renamed from: com.digits.sdk.android.b */
class C2898b {

    /* compiled from: ActivityClassManagerFactory */
    /* renamed from: com.digits.sdk.android.b$a */
    static class C2897a {
        /* renamed from: a */
        private final int[] f13217a;
        /* renamed from: b */
        private final int f13218b;

        public C2897a() throws Exception {
            Class cls = Class.forName("android.support.v7.appcompat.R$styleable");
            Field field = cls.getField("Theme");
            this.f13217a = (int[]) field.get(field.getType());
            field = cls.getField("Theme_windowActionBar");
            this.f13218b = ((Integer) field.get(field.getType())).intValue();
        }
    }

    C2898b() {
    }

    /* renamed from: a */
    C2877a m14027a(Context context, int i) {
        try {
            Class.forName("android.support.v7.app.ActionBarActivity");
            if (m14026a(context, i, new C2897a())) {
                return new C2915e();
            }
            return new C2913c();
        } catch (Exception e) {
            return new C2913c();
        }
    }

    /* renamed from: a */
    private boolean m14026a(Context context, int i, C2897a c2897a) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, c2897a.f13217a);
        boolean hasValue = obtainStyledAttributes.hasValue(c2897a.f13218b);
        obtainStyledAttributes.recycle();
        return hasValue;
    }
}
