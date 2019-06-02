package com.google.android.gms.internal;

import java.io.IOException;

public final class zzapf {
    public static final int[] bim = new int[0];
    public static final long[] bin = new long[0];
    public static final float[] bio = new float[0];
    public static final double[] bip = new double[0];
    public static final boolean[] biq = new boolean[0];
    public static final String[] bir = new String[0];
    public static final byte[][] bis = new byte[0][];
    public static final byte[] bit = new byte[0];

    static int zzaez(int i) {
        return i & 7;
    }

    public static int zzafa(int i) {
        return i >>> 3;
    }

    public static int zzaj(int i, int i2) {
        return (i << 3) | i2;
    }

    public static boolean zzb(zzaou zzaou, int i) throws IOException {
        return zzaou.zzaeg(i);
    }

    public static final int zzc(zzaou zzaou, int i) throws IOException {
        int i2 = 1;
        int position = zzaou.getPosition();
        zzaou.zzaeg(i);
        while (zzaou.m16039J() == i) {
            zzaou.zzaeg(i);
            i2++;
        }
        zzaou.zzaek(position);
        return i2;
    }
}
