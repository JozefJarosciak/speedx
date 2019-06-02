package com.google.android.gms.internal;

class zzk$1 implements Runnable {
    final /* synthetic */ String zzap;
    final /* synthetic */ long zzaq;
    final /* synthetic */ zzk zzar;

    zzk$1(zzk zzk, String str, long j) {
        this.zzar = zzk;
        this.zzap = str;
        this.zzaq = j;
    }

    public void run() {
        zzk.zzd(this.zzar).zza(this.zzap, this.zzaq);
        zzk.zzd(this.zzar).zzd(toString());
    }
}
