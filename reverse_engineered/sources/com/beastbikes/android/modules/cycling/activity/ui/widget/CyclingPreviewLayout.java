package com.beastbikes.android.modules.cycling.activity.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;

public class CyclingPreviewLayout extends LinearLayout {
    /* renamed from: a */
    private String[] f9234a;
    /* renamed from: b */
    private String[] f9235b;
    /* renamed from: c */
    private String[] f9236c;
    /* renamed from: d */
    private float f9237d = 1.0f;
    /* renamed from: e */
    private float f9238e = 9.6f;
    /* renamed from: f */
    private float f9239f = 6.0f;
    /* renamed from: g */
    private float f9240g = 64.0f;
    /* renamed from: h */
    private float f9241h = 35.0f;
    /* renamed from: i */
    private float f9242i = 28.0f;

    public CyclingPreviewLayout(Context context) {
        super(context);
        m10487a(context);
    }

    public CyclingPreviewLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m10487a(context);
    }

    public CyclingPreviewLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10487a(context);
    }

    /* renamed from: a */
    private void m10487a(Context context) {
        setOrientation(1);
        setPadding(0, getResources().getDimensionPixelSize(C1373R.dimen.dimen_10), 0, getResources().getDimensionPixelSize(C1373R.dimen.dimen_10));
        this.f9234a = getResources().getStringArray(C1373R.array.cycling_data_settings_desc);
        this.f9235b = getResources().getStringArray(C1373R.array.cycling_data_settings_format);
        if (C1849a.m9645b(context)) {
            this.f9236c = getResources().getStringArray(C1373R.array.cycling_data_settings_desc_unit_km);
        } else {
            this.f9236c = getResources().getStringArray(C1373R.array.cycling_data_settings_desc_unit_mi);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public void m10490a(java.util.ArrayList<java.lang.Integer> r15) {
        /*
        r14 = this;
        r1 = -1;
        r13 = 1069547520; // 0x3fc00000 float:1.5 double:5.28426686E-315;
        r2 = 1;
        r12 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = 0;
        r14.removeAllViews();
        if (r15 == 0) goto L_0x0012;
    L_0x000c:
        r0 = r15.size();
        if (r0 > 0) goto L_0x0013;
    L_0x0012:
        return;
    L_0x0013:
        r4 = new android.widget.LinearLayout$LayoutParams;
        r4.<init>(r1, r3, r12);
        r5 = new android.widget.LinearLayout$LayoutParams;
        r5.<init>(r3, r1, r12);
        r6 = r15.size();
        switch(r6) {
            case 1: goto L_0x0025;
            case 2: goto L_0x0025;
            case 3: goto L_0x0058;
            case 4: goto L_0x009b;
            case 5: goto L_0x0108;
            case 6: goto L_0x015d;
            case 7: goto L_0x01ce;
            case 8: goto L_0x0268;
            case 9: goto L_0x01ce;
            case 10: goto L_0x0268;
            default: goto L_0x0024;
        };
    L_0x0024:
        goto L_0x0012;
    L_0x0025:
        if (r3 >= r6) goto L_0x0012;
    L_0x0027:
        r0 = r15.get(r3);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r1 = new com.beastbikes.android.modules.cycling.activity.ui.widget.a;
        r2 = r14.getContext();
        r5 = r14.f9235b;
        r5 = r5[r0];
        r7 = r14.f9234a;
        r7 = r7[r0];
        r8 = r14.f9236c;
        r0 = r8[r0];
        r1.<init>(r2, r5, r7, r0);
        r0 = r14.f9238e;
        r2 = r14.f9241h;
        r5 = r14.f9238e;
        r1.m10500a(r0, r2, r5);
        r1.setLayoutParams(r4);
        r14.addView(r1);
        r3 = r3 + 1;
        goto L_0x0025;
    L_0x0058:
        if (r3 >= r6) goto L_0x0012;
    L_0x005a:
        r0 = r15.get(r3);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r1 = new com.beastbikes.android.modules.cycling.activity.ui.widget.a;
        r5 = r14.getContext();
        r7 = r14.f9235b;
        r7 = r7[r0];
        r8 = r14.f9234a;
        r8 = r8[r0];
        r9 = r14.f9236c;
        r0 = r9[r0];
        r1.<init>(r5, r7, r8, r0);
        r0 = r14.f9238e;
        r5 = r14.f9241h;
        r7 = r14.f9238e;
        r1.m10500a(r0, r5, r7);
        if (r3 != r2) goto L_0x0098;
    L_0x0084:
        r4.weight = r13;
        r0 = r14.f9238e;
        r5 = r14.f9240g;
        r7 = r14.f9238e;
        r1.m10500a(r0, r5, r7);
    L_0x008f:
        r1.setLayoutParams(r4);
        r14.addView(r1);
        r3 = r3 + 1;
        goto L_0x0058;
    L_0x0098:
        r4.weight = r12;
        goto L_0x008f;
    L_0x009b:
        r1 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r1.<init>(r0);
        r1.setOrientation(r3);
        r1.setLayoutParams(r4);
    L_0x00aa:
        if (r3 >= r6) goto L_0x0103;
    L_0x00ac:
        r0 = r15.get(r3);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r7 = new com.beastbikes.android.modules.cycling.activity.ui.widget.a;
        r8 = r14.getContext();
        r9 = r14.f9235b;
        r9 = r9[r0];
        r10 = r14.f9234a;
        r10 = r10[r0];
        r11 = r14.f9236c;
        r0 = r11[r0];
        r7.<init>(r8, r9, r10, r0);
        if (r3 == 0) goto L_0x00cf;
    L_0x00cd:
        if (r3 != r2) goto L_0x00f1;
    L_0x00cf:
        if (r3 != 0) goto L_0x00e5;
    L_0x00d1:
        r0 = r14.f9238e;
        r8 = r14.f9241h;
        r9 = r14.f9238e;
        r7.m10500a(r0, r8, r9);
        r4.weight = r12;
    L_0x00dc:
        r7.setLayoutParams(r4);
        r14.addView(r7);
    L_0x00e2:
        r3 = r3 + 1;
        goto L_0x00aa;
    L_0x00e5:
        r0 = r14.f9238e;
        r8 = r14.f9240g;
        r9 = r14.f9238e;
        r7.m10500a(r0, r8, r9);
        r4.weight = r13;
        goto L_0x00dc;
    L_0x00f1:
        r4.weight = r12;
        r0 = r14.f9238e;
        r8 = r14.f9241h;
        r9 = r14.f9238e;
        r7.m10500a(r0, r8, r9);
        r7.setLayoutParams(r5);
        r1.addView(r7);
        goto L_0x00e2;
    L_0x0103:
        r14.addView(r1);
        goto L_0x0012;
    L_0x0108:
        r1 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r1.<init>(r0);
        r1.setOrientation(r3);
        r1.setLayoutParams(r4);
    L_0x0117:
        if (r3 >= r6) goto L_0x0158;
    L_0x0119:
        r0 = r15.get(r3);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r7 = new com.beastbikes.android.modules.cycling.activity.ui.widget.a;
        r8 = r14.getContext();
        r9 = r14.f9235b;
        r9 = r9[r0];
        r10 = r14.f9234a;
        r10 = r10[r0];
        r11 = r14.f9236c;
        r0 = r11[r0];
        r7.<init>(r8, r9, r10, r0);
        r0 = r14.f9238e;
        r8 = r14.f9241h;
        r9 = r14.f9238e;
        r7.m10500a(r0, r8, r9);
        if (r3 == 0) goto L_0x0148;
    L_0x0143:
        if (r3 == r2) goto L_0x0148;
    L_0x0145:
        r0 = 2;
        if (r3 != r0) goto L_0x0151;
    L_0x0148:
        r7.setLayoutParams(r4);
        r14.addView(r7);
    L_0x014e:
        r3 = r3 + 1;
        goto L_0x0117;
    L_0x0151:
        r7.setLayoutParams(r5);
        r1.addView(r7);
        goto L_0x014e;
    L_0x0158:
        r14.addView(r1);
        goto L_0x0012;
    L_0x015d:
        r1 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r1.<init>(r0);
        r1.setOrientation(r3);
        r1.setLayoutParams(r4);
        r7 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r7.<init>(r0);
        r7.setOrientation(r3);
        r7.setLayoutParams(r4);
    L_0x017b:
        if (r3 >= r6) goto L_0x01c6;
    L_0x017d:
        r0 = r15.get(r3);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r8 = new com.beastbikes.android.modules.cycling.activity.ui.widget.a;
        r9 = r14.getContext();
        r10 = r14.f9235b;
        r10 = r10[r0];
        r11 = r14.f9234a;
        r11 = r11[r0];
        r12 = r14.f9236c;
        r0 = r12[r0];
        r8.<init>(r9, r10, r11, r0);
        r0 = r14.f9238e;
        r9 = r14.f9241h;
        r10 = r14.f9238e;
        r8.m10500a(r0, r9, r10);
        if (r3 == 0) goto L_0x01a9;
    L_0x01a7:
        if (r3 != r2) goto L_0x01b2;
    L_0x01a9:
        r8.setLayoutParams(r4);
        r14.addView(r8);
    L_0x01af:
        r3 = r3 + 1;
        goto L_0x017b;
    L_0x01b2:
        r0 = 2;
        if (r3 == r0) goto L_0x01b8;
    L_0x01b5:
        r0 = 3;
        if (r3 != r0) goto L_0x01bf;
    L_0x01b8:
        r8.setLayoutParams(r5);
        r1.addView(r8);
        goto L_0x01af;
    L_0x01bf:
        r8.setLayoutParams(r5);
        r7.addView(r8);
        goto L_0x01af;
    L_0x01c6:
        r14.addView(r1);
        r14.addView(r7);
        goto L_0x0012;
    L_0x01ce:
        r0 = 7;
        if (r6 != r0) goto L_0x0263;
    L_0x01d1:
        r0 = r14.f9238e;
        r1 = r0;
    L_0x01d4:
        r0 = r15.get(r3);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r7 = new com.beastbikes.android.modules.cycling.activity.ui.widget.a;
        r8 = r14.getContext();
        r9 = r14.f9235b;
        r9 = r9[r0];
        r10 = r14.f9234a;
        r10 = r10[r0];
        r11 = r14.f9236c;
        r0 = r11[r0];
        r7.<init>(r8, r9, r10, r0);
        r0 = r14.f9242i;
        r7.m10500a(r1, r0, r1);
        r14.addView(r7);
    L_0x01fb:
        if (r2 >= r6) goto L_0x0012;
    L_0x01fd:
        r7 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r7.<init>(r0);
        r7.setOrientation(r3);
        r7.setLayoutParams(r4);
        r0 = r15.get(r2);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r8 = new com.beastbikes.android.modules.cycling.activity.ui.widget.a;
        r9 = r14.getContext();
        r10 = r14.f9235b;
        r10 = r10[r0];
        r11 = r14.f9234a;
        r11 = r11[r0];
        r12 = r14.f9236c;
        r0 = r12[r0];
        r8.<init>(r9, r10, r11, r0);
        r0 = r14.f9242i;
        r8.m10500a(r1, r0, r1);
        r7.addView(r8, r5);
        r0 = r2 + 1;
        r0 = r15.get(r0);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r8 = new com.beastbikes.android.modules.cycling.activity.ui.widget.a;
        r9 = r14.getContext();
        r10 = r14.f9235b;
        r10 = r10[r0];
        r11 = r14.f9234a;
        r11 = r11[r0];
        r12 = r14.f9236c;
        r0 = r12[r0];
        r8.<init>(r9, r10, r11, r0);
        r0 = r14.f9242i;
        r8.m10500a(r1, r0, r1);
        r7.addView(r8, r5);
        r14.addView(r7);
        r0 = r2 + 2;
        r2 = r0;
        goto L_0x01fb;
    L_0x0263:
        r0 = r14.f9239f;
        r1 = r0;
        goto L_0x01d4;
    L_0x0268:
        r0 = 8;
        if (r6 != r0) goto L_0x02d8;
    L_0x026c:
        r0 = r14.f9238e;
        r1 = r0;
    L_0x026f:
        r2 = r3;
    L_0x0270:
        if (r2 >= r6) goto L_0x0012;
    L_0x0272:
        r7 = new android.widget.LinearLayout;
        r0 = r14.getContext();
        r7.<init>(r0);
        r7.setOrientation(r3);
        r7.setLayoutParams(r4);
        r0 = r15.get(r2);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r8 = new com.beastbikes.android.modules.cycling.activity.ui.widget.a;
        r9 = r14.getContext();
        r10 = r14.f9235b;
        r10 = r10[r0];
        r11 = r14.f9234a;
        r11 = r11[r0];
        r12 = r14.f9236c;
        r0 = r12[r0];
        r8.<init>(r9, r10, r11, r0);
        r0 = r14.f9242i;
        r8.m10500a(r1, r0, r1);
        r7.addView(r8, r5);
        r0 = r2 + 1;
        r0 = r15.get(r0);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r8 = new com.beastbikes.android.modules.cycling.activity.ui.widget.a;
        r9 = r14.getContext();
        r10 = r14.f9235b;
        r10 = r10[r0];
        r11 = r14.f9234a;
        r11 = r11[r0];
        r12 = r14.f9236c;
        r0 = r12[r0];
        r8.<init>(r9, r10, r11, r0);
        r0 = r14.f9242i;
        r8.m10500a(r1, r0, r1);
        r7.addView(r8, r5);
        r14.addView(r7);
        r0 = r2 + 2;
        r2 = r0;
        goto L_0x0270;
    L_0x02d8:
        r0 = r14.f9239f;
        r1 = r0;
        goto L_0x026f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.beastbikes.android.modules.cycling.activity.ui.widget.CyclingPreviewLayout.a(java.util.ArrayList):void");
    }

    /* renamed from: a */
    public void m10489a() {
        this.f9237d = 0.3f;
        m10488b();
    }

    /* renamed from: b */
    private void m10488b() {
        this.f9238e *= this.f9237d;
        this.f9239f *= this.f9237d;
        this.f9240g *= this.f9237d;
        this.f9241h *= this.f9237d;
        this.f9242i *= this.f9237d;
    }
}
