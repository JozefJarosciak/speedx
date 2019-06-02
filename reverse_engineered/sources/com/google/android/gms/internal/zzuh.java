package com.google.android.gms.internal;

public final class zzuh {
    private static zzuh Qr;
    private final zzue Qs = new zzue();
    private final zzuf Qt = new zzuf();

    static {
        zza(new zzuh());
    }

    private zzuh() {
    }

    protected static void zza(zzuh zzuh) {
        synchronized (zzuh.class) {
            Qr = zzuh;
        }
    }

    private static zzuh zzbfq() {
        zzuh zzuh;
        synchronized (zzuh.class) {
            zzuh = Qr;
        }
        return zzuh;
    }

    public static zzue zzbfr() {
        return zzbfq().Qs;
    }

    public static zzuf zzbfs() {
        return zzbfq().Qt;
    }
}
