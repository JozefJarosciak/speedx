package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public class zzg extends Thread {
    private final zzb zzi;
    private final zzn zzj;
    private volatile boolean zzk = false;
    private final BlockingQueue<zzk<?>> zzx;
    private final zzf zzy;

    public zzg(BlockingQueue<zzk<?>> blockingQueue, zzf zzf, zzb zzb, zzn zzn) {
        super("VolleyNetworkDispatcher");
        this.zzx = blockingQueue;
        this.zzy = zzf;
        this.zzi = zzb;
        this.zzj = zzn;
    }

    @TargetApi(14)
    private void zzb(zzk<?> zzk) {
        if (VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(zzk.zzf());
        }
    }

    private void zzb(zzk<?> zzk, zzr zzr) {
        this.zzj.zza((zzk) zzk, zzk.zzb(zzr));
    }

    public void quit() {
        this.zzk = true;
        interrupt();
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                zzk zzk = (zzk) this.zzx.take();
                try {
                    zzk.zzc("network-queue-take");
                    if (zzk.isCanceled()) {
                        zzk.zzd("network-discard-cancelled");
                    } else {
                        zzb(zzk);
                        zzi zza = this.zzy.zza(zzk);
                        zzk.zzc("network-http-complete");
                        if (zza.zzaa && zzk.zzv()) {
                            zzk.zzd("not-modified");
                        } else {
                            zzm zza2 = zzk.zza(zza);
                            zzk.zzc("network-parse-complete");
                            if (zzk.zzq() && zza2.zzbf != null) {
                                this.zzi.zza(zzk.zzg(), zza2.zzbf);
                                zzk.zzc("network-cache-written");
                            }
                            zzk.zzu();
                            this.zzj.zza(zzk, zza2);
                        }
                    }
                } catch (zzr e) {
                    e.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    zzb(zzk, e);
                } catch (Throwable e2) {
                    zzs.zza(e2, "Unhandled exception %s", e2.toString());
                    zzr zzr = new zzr(e2);
                    zzr.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.zzj.zza(zzk, zzr);
                }
            } catch (InterruptedException e3) {
                if (this.zzk) {
                    return;
                }
            }
        }
    }
}
