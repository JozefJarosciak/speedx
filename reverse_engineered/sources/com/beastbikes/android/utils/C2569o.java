package com.beastbikes.android.utils;

/* compiled from: Maths */
/* renamed from: com.beastbikes.android.utils.o */
public class C2569o {
    /* renamed from: a */
    public static double m12882a(double d, double d2, double d3, double d4) {
        double toRadians = Math.toRadians(Math.abs(d - d3));
        double toRadians2 = Math.toRadians(Math.abs(d2 - d4));
        double toRadians3 = Math.toRadians(d);
        double toRadians4 = Math.toRadians(d3);
        toRadians = Math.pow(Math.sin(toRadians / 2.0d), 2.0d) + (Math.pow(Math.sin(toRadians2 / 2.0d), 2.0d) * (Math.cos(toRadians3) * Math.cos(toRadians4)));
        return ((Math.atan2(Math.sqrt(toRadians), Math.sqrt(1.0d - toRadians)) * 2.0d) * 6371.0d) * 1000.0d;
    }
}
