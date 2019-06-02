package com.beastbikes.android.widget.materialdesign.mdswitch;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.SparseArray;
import com.beastbikes.android.R$styleable;

/* compiled from: ThemeManager */
/* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.d */
public class C2670d {
    /* renamed from: a */
    private static volatile C2670d f12517a;
    /* renamed from: b */
    private Context f12518b;
    /* renamed from: c */
    private SparseArray<int[]> f12519c = new SparseArray();
    /* renamed from: d */
    private int f12520d;
    /* renamed from: e */
    private C2668a f12521e;

    /* compiled from: ThemeManager */
    /* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.d$c */
    public interface C2660c {
    }

    /* compiled from: ThemeManager */
    /* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.d$a */
    public interface C2668a {
        /* renamed from: a */
        void m13265a(C2660c c2660c);

        /* renamed from: b */
        void m13266b(C2660c c2660c);
    }

    /* compiled from: ThemeManager */
    /* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.d$b */
    public static class C2669b {
    }

    /* renamed from: a */
    public static int m13267a(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ThemableView, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    /* renamed from: a */
    public static C2670d m13268a() {
        if (f12517a == null) {
            synchronized (C2670d.class) {
                if (f12517a == null) {
                    f12517a = new C2670d();
                }
            }
        }
        return f12517a;
    }

    /* renamed from: a */
    private int[] m13269a(Context context, int i) {
        if (context == null) {
            return null;
        }
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(i);
        int[] iArr = new int[obtainTypedArray.length()];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = obtainTypedArray.getResourceId(i2, 0);
        }
        obtainTypedArray.recycle();
        return iArr;
    }

    /* renamed from: b */
    private int[] m13270b(int i) {
        if (this.f12519c == null) {
            return null;
        }
        int[] iArr = (int[]) this.f12519c.get(i);
        if (iArr != null) {
            return iArr;
        }
        iArr = m13269a(this.f12518b, i);
        this.f12519c.put(i, iArr);
        return iArr;
    }

    /* renamed from: a */
    public int m13271a(int i) {
        return m13272a(i, this.f12520d);
    }

    /* renamed from: a */
    public int m13272a(int i, int i2) {
        int[] b = m13270b(i);
        return b == null ? 0 : b[i2];
    }

    /* renamed from: a */
    public void m13273a(@NonNull C2660c c2660c) {
        if (this.f12521e != null) {
            this.f12521e.m13265a(c2660c);
        }
    }

    /* renamed from: b */
    public void m13274b(@NonNull C2660c c2660c) {
        if (this.f12521e != null) {
            this.f12521e.m13266b(c2660c);
        }
    }
}
