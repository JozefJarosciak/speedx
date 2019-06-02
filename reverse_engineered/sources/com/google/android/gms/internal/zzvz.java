package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.common.util.zzz;

public class zzvz {
    private static boolean DEBUG = false;
    private static String TAG = "WakeLock";
    private static String auz = "*gcore*:";
    private final String AA;
    private final String Ay;
    private WorkSource acv;
    private final WakeLock auA;
    private final int auB;
    private final String auC;
    private boolean auD;
    private int auE;
    private int auF;
    private final Context mContext;

    public zzvz(Context context, int i, String str) {
        this(context, i, str, null, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zzvz(Context context, int i, String str, String str2, String str3) {
        this(context, i, str, str2, str3, null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zzvz(Context context, int i, String str, String str2, String str3, String str4) {
        this.auD = true;
        zzab.zzh(str, "Wake lock name can NOT be empty");
        this.auB = i;
        this.auC = str2;
        this.AA = str4;
        this.mContext = context.getApplicationContext();
        if ("com.google.android.gms".equals(context.getPackageName())) {
            this.Ay = str;
        } else {
            String valueOf = String.valueOf(auz);
            String valueOf2 = String.valueOf(str);
            this.Ay = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        this.auA = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (zzz.zzco(this.mContext)) {
            if (zzw.zzic(str3)) {
                str3 = context.getPackageName();
            }
            this.acv = zzz.zzr(context, str3);
            zzc(this.acv);
        }
    }

    private void zzd(WorkSource workSource) {
        try {
            this.auA.setWorkSource(workSource);
        } catch (IllegalArgumentException e) {
            Log.wtf(TAG, e.toString());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzk(java.lang.String r13, long r14) {
        /*
        r12 = this;
        r0 = r12.zznj(r13);
        r6 = r12.zzp(r13, r0);
        monitor-enter(r12);
        r1 = r12.auD;	 Catch:{ all -> 0x0044 }
        if (r1 == 0) goto L_0x0017;
    L_0x000d:
        r1 = r12.auE;	 Catch:{ all -> 0x0044 }
        r2 = r1 + 1;
        r12.auE = r2;	 Catch:{ all -> 0x0044 }
        if (r1 == 0) goto L_0x001f;
    L_0x0015:
        if (r0 != 0) goto L_0x001f;
    L_0x0017:
        r0 = r12.auD;	 Catch:{ all -> 0x0044 }
        if (r0 != 0) goto L_0x0042;
    L_0x001b:
        r0 = r12.auF;	 Catch:{ all -> 0x0044 }
        if (r0 != 0) goto L_0x0042;
    L_0x001f:
        r1 = com.google.android.gms.common.stats.zzh.zzave();	 Catch:{ all -> 0x0044 }
        r2 = r12.mContext;	 Catch:{ all -> 0x0044 }
        r0 = r12.auA;	 Catch:{ all -> 0x0044 }
        r3 = com.google.android.gms.common.stats.zzf.zza(r0, r6);	 Catch:{ all -> 0x0044 }
        r4 = 7;
        r5 = r12.Ay;	 Catch:{ all -> 0x0044 }
        r7 = r12.AA;	 Catch:{ all -> 0x0044 }
        r8 = r12.auB;	 Catch:{ all -> 0x0044 }
        r0 = r12.acv;	 Catch:{ all -> 0x0044 }
        r9 = com.google.android.gms.common.util.zzz.zzb(r0);	 Catch:{ all -> 0x0044 }
        r10 = r14;
        r1.zza(r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ all -> 0x0044 }
        r0 = r12.auF;	 Catch:{ all -> 0x0044 }
        r0 = r0 + 1;
        r12.auF = r0;	 Catch:{ all -> 0x0044 }
    L_0x0042:
        monitor-exit(r12);	 Catch:{ all -> 0x0044 }
        return;
    L_0x0044:
        r0 = move-exception;
        monitor-exit(r12);	 Catch:{ all -> 0x0044 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzvz.zzk(java.lang.String, long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzni(java.lang.String r10) {
        /*
        r9 = this;
        r0 = r9.zznj(r10);
        r5 = r9.zzp(r10, r0);
        monitor-enter(r9);
        r1 = r9.auD;	 Catch:{ all -> 0x0045 }
        if (r1 == 0) goto L_0x0017;
    L_0x000d:
        r1 = r9.auE;	 Catch:{ all -> 0x0045 }
        r1 = r1 + -1;
        r9.auE = r1;	 Catch:{ all -> 0x0045 }
        if (r1 == 0) goto L_0x0020;
    L_0x0015:
        if (r0 != 0) goto L_0x0020;
    L_0x0017:
        r0 = r9.auD;	 Catch:{ all -> 0x0045 }
        if (r0 != 0) goto L_0x0043;
    L_0x001b:
        r0 = r9.auF;	 Catch:{ all -> 0x0045 }
        r1 = 1;
        if (r0 != r1) goto L_0x0043;
    L_0x0020:
        r0 = com.google.android.gms.common.stats.zzh.zzave();	 Catch:{ all -> 0x0045 }
        r1 = r9.mContext;	 Catch:{ all -> 0x0045 }
        r2 = r9.auA;	 Catch:{ all -> 0x0045 }
        r2 = com.google.android.gms.common.stats.zzf.zza(r2, r5);	 Catch:{ all -> 0x0045 }
        r3 = 8;
        r4 = r9.Ay;	 Catch:{ all -> 0x0045 }
        r6 = r9.AA;	 Catch:{ all -> 0x0045 }
        r7 = r9.auB;	 Catch:{ all -> 0x0045 }
        r8 = r9.acv;	 Catch:{ all -> 0x0045 }
        r8 = com.google.android.gms.common.util.zzz.zzb(r8);	 Catch:{ all -> 0x0045 }
        r0.zza(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x0045 }
        r0 = r9.auF;	 Catch:{ all -> 0x0045 }
        r0 = r0 + -1;
        r9.auF = r0;	 Catch:{ all -> 0x0045 }
    L_0x0043:
        monitor-exit(r9);	 Catch:{ all -> 0x0045 }
        return;
    L_0x0045:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x0045 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzvz.zzni(java.lang.String):void");
    }

    private boolean zznj(String str) {
        return (TextUtils.isEmpty(str) || str.equals(this.auC)) ? false : true;
    }

    private String zzp(String str, boolean z) {
        return this.auD ? z ? str : this.auC : this.auC;
    }

    public void acquire(long j) {
        if (!zzs.zzavm() && this.auD) {
            String str = TAG;
            String str2 = "Do not acquire with timeout on reference counted WakeLocks before ICS. wakelock: ";
            String valueOf = String.valueOf(this.Ay);
            Log.wtf(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        zzk(null, j);
        this.auA.acquire(j);
    }

    public boolean isHeld() {
        return this.auA.isHeld();
    }

    public void release() {
        zzni(null);
        this.auA.release();
    }

    public void setReferenceCounted(boolean z) {
        this.auA.setReferenceCounted(z);
        this.auD = z;
    }

    public void zzc(WorkSource workSource) {
        if (workSource != null && zzz.zzco(this.mContext)) {
            if (this.acv != null) {
                this.acv.add(workSource);
            } else {
                this.acv = workSource;
            }
            zzd(this.acv);
        }
    }
}
