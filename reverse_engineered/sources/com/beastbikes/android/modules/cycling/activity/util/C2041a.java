package com.beastbikes.android.modules.cycling.activity.util;

/* compiled from: CalorieCalculator */
/* renamed from: com.beastbikes.android.modules.cycling.activity.util.a */
public final class C2041a {
    /* renamed from: a */
    public static final double m10515a(int i, double d, double d2, int i2, double d3, double d4) {
        double a = ((C2041a.m10514a(i, d, d2, i2) * C2041a.m10513a(d3)) / 24.0d) * d4;
        if (a < 0.0d) {
            return 0.0d;
        }
        return a;
    }

    /* renamed from: a */
    private static final double m10514a(int i, double d, double d2, int i2) {
        if (i > 1) {
            return -1.0d;
        }
        if (i < 0) {
            i = 1;
        }
        if (d < 0.0d) {
            return -2.0d;
        }
        if (d2 < 0.0d) {
            return -3.0d;
        }
        if (i2 <= 0) {
            return -4.0d;
        }
        switch (i) {
            case 0:
                return ((665.1d + (9.563d * d)) + (1.85d * d2)) - (4.676d * ((double) i2));
            case 1:
                return ((66.5d + (13.75d * d)) + (5.003d * d2)) - (6.755d * ((double) i2));
            default:
                return 0.0d;
        }
    }

    /* renamed from: a */
    private static final double m10513a(double d) {
        if (d < 0.0d) {
            return -1.0d;
        }
        double d2 = 0.62137d * d;
        if (d2 > 0.0d && d2 <= 5.5d) {
            return 3.5d;
        }
        if (d2 > 5.5d && d2 <= 9.4d) {
            return 5.8d;
        }
        if (d2 > 9.4d && d2 <= 11.9d) {
            return 6.8d;
        }
        if (d2 > 11.9d && d2 <= 13.9d) {
            return 8.0d;
        }
        if (d2 > 13.9d && d2 <= 15.9d) {
            return 10.0d;
        }
        if (d2 > 15.9d && d2 <= 20.0d) {
            return 12.0d;
        }
        if (d2 > 20.0d) {
            return 15.8d;
        }
        return 7.5d;
    }
}
