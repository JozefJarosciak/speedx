package com.beastbikes.android.modules.cycling.activity.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: VirtualPowerUtils */
/* renamed from: com.beastbikes.android.modules.cycling.activity.util.d */
public class C2045d {
    /* renamed from: a */
    public static final Logger f9314a = LoggerFactory.getLogger(C2045d.class);

    /* renamed from: a */
    private static double m10521a(double d, double d2, double d3) {
        return (((0.6023928876002046d * C2045d.m10519a(d)) * 0.0276d) * ((Math.pow(d2 / 100.0d, 0.575d) * Math.pow(d3, 0.425d)) + 0.1126d)) * Math.pow(d / 3.6d, 3.0d);
    }

    /* renamed from: a */
    private static double m10520a(double d, double d2) {
        return ((0.010999999940395355d * (8.0d + d)) * 9.800000190734863d) * (d2 / 3.6d);
    }

    /* renamed from: b */
    private static double m10523b(double d, double d2) {
        f9314a.trace("elevation = " + d2);
        return (((8.0d + d) * 9.800000190734863d) * d2) / 25.0d;
    }

    /* renamed from: b */
    private static double m10524b(double d, double d2, double d3) {
        return ((((d2 / 3.6d) * ((8.0d + d) + 1.0d)) * d3) / 3.6d) / 9.0d;
    }

    /* renamed from: a */
    public static int m10522a(double d, double d2, double d3, double d4, double d5) {
        double b = C2045d.m10523b(d3, d4);
        double b2 = C2045d.m10524b(d3, d, d5) + ((C2045d.m10521a(d, d2, d3) + C2045d.m10520a(d3, d)) + b);
        f9314a.trace("功率， WeightPower = " + b);
        if (b2 < 0.0d) {
            f9314a.info("计算的虚拟功率值：" + b2);
            return 0;
        } else if (b2 <= 1000.0d) {
            return (int) b2;
        } else {
            f9314a.info("计算的虚拟功率值：" + b2);
            return 1000;
        }
    }

    /* renamed from: a */
    private static double m10519a(double d) {
        if (d <= 25.0d) {
            return 1.15d;
        }
        if (d > 25.0d && d <= 30.0d) {
            return 0.034499999999999975d * (d - 25.0d);
        }
        if (d > 30.0d && d <= 40.0d) {
            return 1.0d - (0.012d * (d - 30.0d));
        }
        if (d > 40.0d && d <= 45.0d) {
            return 0.88d - (0.03600000000000001d * (d - 40.0d));
        }
        if (d > 45.0d) {
            return 0.7d;
        }
        return 1.15d;
    }
}
