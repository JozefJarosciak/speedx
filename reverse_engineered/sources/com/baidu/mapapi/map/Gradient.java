package com.baidu.mapapi.map;

import android.graphics.Color;
import java.util.HashMap;

public class Gradient {
    /* renamed from: a */
    private final int f2908a;
    /* renamed from: b */
    private final int[] f2909b;
    /* renamed from: c */
    private final float[] f2910c;

    /* renamed from: com.baidu.mapapi.map.Gradient$a */
    private class C1108a {
        /* renamed from: a */
        final /* synthetic */ Gradient f2904a;
        /* renamed from: b */
        private final int f2905b;
        /* renamed from: c */
        private final int f2906c;
        /* renamed from: d */
        private final float f2907d;

        private C1108a(Gradient gradient, int i, int i2, float f) {
            this.f2904a = gradient;
            this.f2905b = i;
            this.f2906c = i2;
            this.f2907d = f;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    private Gradient(int[] iArr, float[] fArr, int i) {
        if (iArr == null || fArr == null) {
            throw new IllegalArgumentException("colors and startPoints should not be null");
        } else if (iArr.length != fArr.length) {
            throw new IllegalArgumentException("colors and startPoints should be same length");
        } else if (iArr.length == 0) {
            throw new IllegalArgumentException("No colors have been defined");
        } else {
            for (int i2 = 1; i2 < fArr.length; i2++) {
                if (fArr[i2] <= fArr[i2 - 1]) {
                    throw new IllegalArgumentException("startPoints should be in increasing order");
                }
            }
            this.f2908a = i;
            this.f2909b = new int[iArr.length];
            this.f2910c = new float[fArr.length];
            System.arraycopy(iArr, 0, this.f2909b, 0, iArr.length);
            System.arraycopy(fArr, 0, this.f2910c, 0, fArr.length);
        }
    }

    /* renamed from: a */
    private static int m4101a(int i, int i2, float f) {
        int i3 = 0;
        int alpha = (int) ((((float) (Color.alpha(i2) - Color.alpha(i))) * f) + ((float) Color.alpha(i)));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), fArr2);
        if (fArr[0] - fArr2[0] > 180.0f) {
            fArr2[0] = fArr2[0] + 360.0f;
        } else if (fArr2[0] - fArr[0] > 180.0f) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr3 = new float[3];
        while (i3 < 3) {
            fArr3[i3] = ((fArr2[i3] - fArr[i3]) * f) + fArr[i3];
            i3++;
        }
        return Color.HSVToColor(alpha, fArr3);
    }

    /* renamed from: a */
    private HashMap<Integer, C1108a> m4102a() {
        HashMap<Integer, C1108a> hashMap = new HashMap();
        if (this.f2910c[0] != 0.0f) {
            int argb = Color.argb(0, Color.red(this.f2909b[0]), Color.green(this.f2909b[0]), Color.blue(this.f2909b[0]));
            hashMap.put(Integer.valueOf(0), new C1108a(argb, this.f2909b[0], this.f2910c[0] * ((float) this.f2908a)));
        }
        for (int i = 1; i < this.f2909b.length; i++) {
            hashMap.put(Integer.valueOf((int) (((float) this.f2908a) * this.f2910c[i - 1])), new C1108a(this.f2909b[i - 1], this.f2909b[i], (this.f2910c[i] - this.f2910c[i - 1]) * ((float) this.f2908a)));
        }
        if (this.f2910c[this.f2910c.length - 1] != 1.0f) {
            int length = this.f2910c.length - 1;
            hashMap.put(Integer.valueOf((int) (((float) this.f2908a) * this.f2910c[length])), new C1108a(this.f2909b[length], this.f2909b[length], ((float) this.f2908a) * (1.0f - this.f2910c[length])));
        }
        return hashMap;
    }

    /* renamed from: a */
    int[] m4103a(double d) {
        int i = 0;
        HashMap a = m4102a();
        int[] iArr = new int[this.f2908a];
        int i2 = 0;
        C1108a c1108a = (C1108a) a.get(Integer.valueOf(0));
        int i3 = 0;
        while (i2 < this.f2908a) {
            int i4;
            C1108a c1108a2;
            if (a.containsKey(Integer.valueOf(i2))) {
                i4 = i2;
                c1108a2 = (C1108a) a.get(Integer.valueOf(i2));
            } else {
                c1108a2 = c1108a;
                i4 = i3;
            }
            iArr[i2] = m4101a(c1108a2.f2905b, c1108a2.f2906c, ((float) (i2 - i4)) / c1108a2.f2907d);
            i2++;
            i3 = i4;
            c1108a = c1108a2;
        }
        if (d != 1.0d) {
            while (i < this.f2908a) {
                i3 = iArr[i];
                iArr[i] = Color.argb((int) (((double) Color.alpha(i3)) * d), Color.red(i3), Color.green(i3), Color.blue(i3));
                i++;
            }
        }
        return iArr;
    }
}
