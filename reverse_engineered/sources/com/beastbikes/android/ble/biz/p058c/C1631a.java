package com.beastbikes.android.ble.biz.p058c;

/* compiled from: BLECSCUtil */
/* renamed from: com.beastbikes.android.ble.biz.c.a */
public class C1631a {
    /* renamed from: a */
    private static int f7407a;
    /* renamed from: b */
    private static int f7408b;
    /* renamed from: c */
    private static int f7409c;
    /* renamed from: d */
    private static double f7410d;
    /* renamed from: e */
    private static int f7411e;
    /* renamed from: f */
    private static int f7412f;

    /* renamed from: a */
    public static double m8807a(int i, int i2, float f) {
        float f2 = 0.0f;
        if (f7407a < 0) {
            f7407a = i;
        }
        if (f7408b == i2) {
            return (double) null;
        }
        if (f7409c >= 0) {
            if (i2 < f7408b) {
                f2 = ((float) ((65535 + i2) - f7408b)) / 1024.0f;
            } else {
                f2 = ((float) (i2 - f7408b)) / 1024.0f;
            }
            float f3 = (((float) i) * f) / 1000.0f;
            f3 = (((float) (i - f7407a)) * f) / 1000.0f;
            float f4 = ((((float) (i - f7409c)) * f) / 1000.0f) / f2;
            f7410d = (double) ((((float) (i - f7409c)) * 60.0f) / f2);
            f2 = f4;
        }
        f7409c = i;
        f7408b = i2;
        return ((double) f2) * 3.6d;
    }

    /* renamed from: a */
    public static int m8808a(int i, int i2) {
        int i3 = 0;
        if (f7411e != i2) {
            if (f7412f >= 0) {
                float f;
                if (i2 < f7411e) {
                    f = ((float) ((65535 + i2) - f7411e)) / 1024.0f;
                } else {
                    f = ((float) (i2 - f7411e)) / 1024.0f;
                }
                i3 = (int) ((((float) (i - f7412f)) * 60.0f) / f);
                if (i3 > 0) {
                    double d = f7410d / ((double) i3);
                }
            }
            f7412f = i;
            f7411e = i2;
        }
        return i3;
    }
}
