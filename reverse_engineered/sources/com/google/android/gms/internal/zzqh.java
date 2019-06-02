package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzh;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzah;
import com.google.android.gms.common.internal.zzd.zzf;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class zzqh implements Callback {
    private static zzqh uw;
    private static final Object zzamp = new Object();
    private final Context mContext;
    private final Handler mHandler;
    private final GoogleApiAvailability rX;
    private long tU;
    private long tV;
    private final Map<zzpo<?>, zzc<?>> uA;
    private zzpw uB;
    private final Set<zzpo<?>> uC;
    private final ReferenceQueue<com.google.android.gms.common.api.zzc<?>> uD;
    private final SparseArray<zza> uE;
    private zzb uF;
    private long uv;
    private int ux;
    private final AtomicInteger uy;
    private final SparseArray<zzc<?>> uz;

    private final class zza extends PhantomReference<com.google.android.gms.common.api.zzc<?>> {
        private final int sn;
        final /* synthetic */ zzqh uG;

        public zza(zzqh zzqh, com.google.android.gms.common.api.zzc zzc, int i, ReferenceQueue<com.google.android.gms.common.api.zzc<?>> referenceQueue) {
            this.uG = zzqh;
            super(zzc, referenceQueue);
            this.sn = i;
        }

        public void zzaqd() {
            this.uG.mHandler.sendMessage(this.uG.mHandler.obtainMessage(2, this.sn, 2));
        }
    }

    private static final class zzb extends Thread {
        private final ReferenceQueue<com.google.android.gms.common.api.zzc<?>> uD;
        private final SparseArray<zza> uE;
        private final AtomicBoolean uH = new AtomicBoolean();

        public zzb(ReferenceQueue<com.google.android.gms.common.api.zzc<?>> referenceQueue, SparseArray<zza> sparseArray) {
            super("GoogleApiCleanup");
            this.uD = referenceQueue;
            this.uE = sparseArray;
        }

        public void run() {
            this.uH.set(true);
            Process.setThreadPriority(10);
            while (this.uH.get()) {
                try {
                    zza zza = (zza) this.uD.remove();
                    this.uE.remove(zza.sn);
                    zza.zzaqd();
                } catch (InterruptedException e) {
                } finally {
                    this.uH.set(false);
                }
            }
        }
    }

    private class zzc<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener {
        private final zzpo<O> rG;
        private boolean tT;
        final /* synthetic */ zzqh uG;
        private final Queue<zzpn> uI = new LinkedList();
        private final zze uJ;
        private final com.google.android.gms.common.api.Api.zzb uK;
        private final SparseArray<zzrd> uL = new SparseArray();
        private final Set<zzpq> uM = new HashSet();
        private final SparseArray<Map<Object, zzpr$zza>> uN = new SparseArray();
        private ConnectionResult uO = null;

        @WorkerThread
        public zzc(zzqh zzqh, com.google.android.gms.common.api.zzc<O> zzc) {
            this.uG = zzqh;
            this.uJ = zzb((com.google.android.gms.common.api.zzc) zzc);
            if (this.uJ instanceof zzah) {
                this.uK = ((zzah) this.uJ).zzatj();
            } else {
                this.uK = this.uJ;
            }
            this.rG = zzc.zzany();
        }

        @WorkerThread
        private void connect() {
            if (!this.uJ.isConnected() && !this.uJ.isConnecting()) {
                if (this.uJ.zzanr() && this.uG.ux != 0) {
                    this.uG.ux = this.uG.rX.isGooglePlayServicesAvailable(this.uG.mContext);
                    if (this.uG.ux != 0) {
                        onConnectionFailed(new ConnectionResult(this.uG.ux, null));
                        return;
                    }
                }
                this.uJ.zza(new zzd(this.uG, this.uJ, this.rG));
            }
        }

        @WorkerThread
        private void resume() {
            if (this.tT) {
                connect();
            }
        }

        @WorkerThread
        private void zzab(Status status) {
            for (zzpn zzx : this.uI) {
                zzx.zzx(status);
            }
            this.uI.clear();
        }

        @WorkerThread
        private void zzapr() {
            if (this.tT) {
                zzaqh();
                zzab(this.uG.rX.isGooglePlayServicesAvailable(this.uG.mContext) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.uJ.disconnect();
            }
        }

        @WorkerThread
        private void zzaqh() {
            if (this.tT) {
                this.uG.mHandler.removeMessages(9, this.rG);
                this.uG.mHandler.removeMessages(8, this.rG);
                this.tT = false;
            }
        }

        private void zzaqi() {
            this.uG.mHandler.removeMessages(10, this.rG);
            this.uG.mHandler.sendMessageDelayed(this.uG.mHandler.obtainMessage(10, this.rG), this.uG.uv);
        }

        private void zzaqj() {
            if (this.uJ.isConnected() && this.uN.size() == 0) {
                for (int i = 0; i < this.uL.size(); i++) {
                    if (((zzrd) this.uL.get(this.uL.keyAt(i))).zzaqw()) {
                        zzaqi();
                        return;
                    }
                }
                this.uJ.disconnect();
            }
        }

        @WorkerThread
        private zze zzb(com.google.android.gms.common.api.zzc zzc) {
            Api zzanw = zzc.zzanw();
            if (!zzanw.zzanq()) {
                return zzc.zzanw().zzann().zza(zzc.getApplicationContext(), this.uG.mHandler.getLooper(), zzg.zzcd(zzc.getApplicationContext()), zzc.zzanx(), this, this);
            }
            zzh zzano = zzanw.zzano();
            return new zzah(zzc.getApplicationContext(), this.uG.mHandler.getLooper(), zzano.zzant(), this, this, zzg.zzcd(zzc.getApplicationContext()), zzano.zzs(zzc.zzanx()));
        }

        @WorkerThread
        private void zzc(zzpn zzpn) {
            zzpn.zza(this.uL);
            Map map;
            if (zzpn.it == 3) {
                try {
                    Map map2;
                    map = (Map) this.uN.get(zzpn.sn);
                    if (map == null) {
                        ArrayMap arrayMap = new ArrayMap(1);
                        this.uN.put(zzpn.sn, arrayMap);
                        map2 = arrayMap;
                    } else {
                        map2 = map;
                    }
                    zzpr$zza zzpr_zza = ((com.google.android.gms.internal.zzpn.zza) zzpn).so;
                    map2.put(((zzqr) zzpr_zza).zzaqq(), zzpr_zza);
                } catch (ClassCastException e) {
                    throw new IllegalStateException("Listener registration methods must implement ListenerApiMethod");
                }
            } else if (zzpn.it == 4) {
                try {
                    map = (Map) this.uN.get(zzpn.sn);
                    zzqr zzqr = (zzqr) ((com.google.android.gms.internal.zzpn.zza) zzpn).so;
                    if (map != null) {
                        map.remove(zzqr.zzaqq());
                    } else {
                        Log.w("GoogleApiManager", "Received call to unregister a listener without a matching registration call.");
                    }
                } catch (ClassCastException e2) {
                    throw new IllegalStateException("Listener unregistration methods must implement ListenerApiMethod");
                }
            }
            try {
                zzpn.zzb(this.uK);
            } catch (DeadObjectException e3) {
                this.uJ.disconnect();
                onConnectionSuspended(1);
            }
        }

        @WorkerThread
        private void zzj(ConnectionResult connectionResult) {
            for (zzpq zza : this.uM) {
                zza.zza(this.rG, connectionResult);
            }
            this.uM.clear();
        }

        boolean isConnected() {
            return this.uJ.isConnected();
        }

        @WorkerThread
        public void onConnected(@Nullable Bundle bundle) {
            zzaqf();
            zzj(ConnectionResult.qR);
            zzaqh();
            for (int i = 0; i < this.uN.size(); i++) {
                for (zzpr$zza zzb : ((Map) this.uN.get(this.uN.keyAt(i))).values()) {
                    try {
                        zzb.zzb(this.uK);
                    } catch (DeadObjectException e) {
                        this.uJ.disconnect();
                        onConnectionSuspended(1);
                    }
                }
            }
            zzaqe();
            zzaqi();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        @android.support.annotation.WorkerThread
        public void onConnectionFailed(@android.support.annotation.NonNull com.google.android.gms.common.ConnectionResult r6) {
            /*
            r5 = this;
            r5.zzaqf();
            r0 = r5.uG;
            r1 = -1;
            r0.ux = r1;
            r5.zzj(r6);
            r0 = r5.uL;
            r1 = 0;
            r0 = r0.keyAt(r1);
            r1 = r5.uI;
            r1 = r1.isEmpty();
            if (r1 == 0) goto L_0x001e;
        L_0x001b:
            r5.uO = r6;
        L_0x001d:
            return;
        L_0x001e:
            r1 = com.google.android.gms.internal.zzqh.zzamp;
            monitor-enter(r1);
            r2 = r5.uG;	 Catch:{ all -> 0x0044 }
            r2 = null;	 Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0047;
        L_0x002b:
            r2 = r5.uG;	 Catch:{ all -> 0x0044 }
            r2 = r2.uC;	 Catch:{ all -> 0x0044 }
            r3 = r5.rG;	 Catch:{ all -> 0x0044 }
            r2 = r2.contains(r3);	 Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0047;
        L_0x0039:
            r2 = r5.uG;	 Catch:{ all -> 0x0044 }
            r2 = null;	 Catch:{ all -> 0x0044 }
            r2.zzb(r6, r0);	 Catch:{ all -> 0x0044 }
            monitor-exit(r1);	 Catch:{ all -> 0x0044 }
            goto L_0x001d;
        L_0x0044:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0044 }
            throw r0;
        L_0x0047:
            monitor-exit(r1);	 Catch:{ all -> 0x0044 }
            r1 = r5.uG;
            r0 = r1.zzc(r6, r0);
            if (r0 != 0) goto L_0x001d;
        L_0x0050:
            r0 = r6.getErrorCode();
            r1 = 18;
            if (r0 != r1) goto L_0x005b;
        L_0x0058:
            r0 = 1;
            r5.tT = r0;
        L_0x005b:
            r0 = r5.tT;
            if (r0 == 0) goto L_0x007d;
        L_0x005f:
            r0 = r5.uG;
            r0 = r0.mHandler;
            r1 = r5.uG;
            r1 = r1.mHandler;
            r2 = 8;
            r3 = r5.rG;
            r1 = android.os.Message.obtain(r1, r2, r3);
            r2 = r5.uG;
            r2 = r2.tV;
            r0.sendMessageDelayed(r1, r2);
            goto L_0x001d;
        L_0x007d:
            r0 = new com.google.android.gms.common.api.Status;
            r1 = 17;
            r2 = r5.rG;
            r2 = r2.zzaok();
            r2 = java.lang.String.valueOf(r2);
            r3 = new java.lang.StringBuilder;
            r4 = java.lang.String.valueOf(r2);
            r4 = r4.length();
            r4 = r4 + 38;
            r3.<init>(r4);
            r4 = "API: ";
            r3 = r3.append(r4);
            r2 = r3.append(r2);
            r3 = " is not available on this device.";
            r2 = r2.append(r3);
            r2 = r2.toString();
            r0.<init>(r1, r2);
            r5.zzab(r0);
            goto L_0x001d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqh.zzc.onConnectionFailed(com.google.android.gms.common.ConnectionResult):void");
        }

        @WorkerThread
        public void onConnectionSuspended(int i) {
            zzaqf();
            this.tT = true;
            this.uG.mHandler.sendMessageDelayed(Message.obtain(this.uG.mHandler, 8, this.rG), this.uG.tV);
            this.uG.mHandler.sendMessageDelayed(Message.obtain(this.uG.mHandler, 9, this.rG), this.uG.tU);
            this.uG.ux = -1;
        }

        @WorkerThread
        public void zzaqe() {
            while (this.uJ.isConnected() && !this.uI.isEmpty()) {
                zzc((zzpn) this.uI.remove());
            }
        }

        @WorkerThread
        public void zzaqf() {
            this.uO = null;
        }

        ConnectionResult zzaqg() {
            return this.uO;
        }

        @WorkerThread
        public void zzb(zzpn zzpn) {
            if (this.uJ.isConnected()) {
                zzc(zzpn);
                zzaqi();
                return;
            }
            this.uI.add(zzpn);
            if (this.uO == null || !this.uO.hasResolution()) {
                connect();
            } else {
                onConnectionFailed(this.uO);
            }
        }

        @WorkerThread
        public void zzb(zzpq zzpq) {
            this.uM.add(zzpq);
        }

        @WorkerThread
        public void zzf(int i, boolean z) {
            Iterator it = this.uI.iterator();
            while (it.hasNext()) {
                zzpn zzpn = (zzpn) it.next();
                if (zzpn.sn == i && zzpn.it != 1 && zzpn.cancel()) {
                    it.remove();
                }
            }
            ((zzrd) this.uL.get(i)).release();
            this.uN.delete(i);
            if (!z) {
                this.uL.remove(i);
                this.uG.uE.remove(i);
                if (this.uL.size() == 0 && this.uI.isEmpty()) {
                    zzaqh();
                    this.uJ.disconnect();
                    this.uG.uA.remove(this.rG);
                    synchronized (zzqh.zzamp) {
                        this.uG.uC.remove(this.rG);
                    }
                }
            }
        }

        @WorkerThread
        public void zzfk(int i) {
            this.uL.put(i, new zzrd(this.rG.zzanp(), this.uJ));
        }
    }

    private class zzd implements zzf {
        private final zzpo<?> rG;
        final /* synthetic */ zzqh uG;
        private final zze uJ;

        public zzd(zzqh zzqh, zze zze, zzpo<?> zzpo) {
            this.uG = zzqh;
            this.uJ = zze;
            this.rG = zzpo;
        }

        @WorkerThread
        public void zzh(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                this.uJ.zza(null, Collections.emptySet());
            } else {
                ((zzc) this.uG.uA.get(this.rG)).onConnectionFailed(connectionResult);
            }
        }
    }

    private zzqh(Context context) {
        this(context, GoogleApiAvailability.getInstance());
    }

    private zzqh(Context context, GoogleApiAvailability googleApiAvailability) {
        this.tV = 5000;
        this.tU = 120000;
        this.uv = AbstractComponentTracker.LINGERING_TIMEOUT;
        this.ux = -1;
        this.uy = new AtomicInteger(1);
        this.uz = new SparseArray();
        this.uA = new ConcurrentHashMap(5, 0.75f, 1);
        this.uB = null;
        this.uC = new com.google.android.gms.common.util.zza();
        this.uD = new ReferenceQueue();
        this.uE = new SparseArray();
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), this);
        this.rX = googleApiAvailability;
    }

    private int zza(com.google.android.gms.common.api.zzc<?> zzc) {
        int andIncrement = this.uy.getAndIncrement();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, andIncrement, 0, zzc));
        return andIncrement;
    }

    public static Pair<zzqh, Integer> zza(Context context, com.google.android.gms.common.api.zzc<?> zzc) {
        Pair<zzqh, Integer> create;
        synchronized (zzamp) {
            if (uw == null) {
                uw = new zzqh(context.getApplicationContext());
            }
            create = Pair.create(uw, Integer.valueOf(uw.zza((com.google.android.gms.common.api.zzc) zzc)));
        }
        return create;
    }

    @WorkerThread
    private void zza(com.google.android.gms.common.api.zzc<?> zzc, int i) {
        zzpo zzany = zzc.zzany();
        if (!this.uA.containsKey(zzany)) {
            this.uA.put(zzany, new zzc(this, zzc));
        }
        zzc zzc2 = (zzc) this.uA.get(zzany);
        zzc2.zzfk(i);
        this.uz.put(i, zzc2);
        zzc2.connect();
        this.uE.put(i, new zza(this, zzc, i, this.uD));
        if (this.uF == null || !this.uF.uH.get()) {
            this.uF = new zzb(this.uD, this.uE);
            this.uF.start();
        }
    }

    @WorkerThread
    private void zza(zzpn zzpn) {
        ((zzc) this.uz.get(zzpn.sn)).zzb(zzpn);
    }

    public static zzqh zzaqa() {
        zzqh zzqh;
        synchronized (zzamp) {
            zzqh = uw;
        }
        return zzqh;
    }

    @WorkerThread
    private void zzaqb() {
        for (zzc zzc : this.uA.values()) {
            zzc.zzaqf();
            zzc.connect();
        }
    }

    @WorkerThread
    private void zze(int i, boolean z) {
        zzc zzc = (zzc) this.uz.get(i);
        if (zzc != null) {
            if (!z) {
                this.uz.delete(i);
            }
            zzc.zzf(i, z);
            return;
        }
        Log.wtf("GoogleApiManager", "onRelease received for unknown instance: " + i, new Exception());
    }

    @WorkerThread
    public boolean handleMessage(Message message) {
        boolean z = false;
        switch (message.what) {
            case 1:
                zza((zzpq) message.obj);
                break;
            case 2:
            case 7:
                int i = message.arg1;
                if (message.arg2 == 1) {
                    z = true;
                }
                zze(i, z);
                break;
            case 3:
                zzaqb();
                break;
            case 4:
                zza((zzpn) message.obj);
                break;
            case 5:
                if (this.uz.get(message.arg1) != null) {
                    ((zzc) this.uz.get(message.arg1)).zzab(new Status(17, "Error resolution was canceled by the user."));
                    break;
                }
                break;
            case 6:
                zza((com.google.android.gms.common.api.zzc) message.obj, message.arg1);
                break;
            case 8:
                if (this.uA.containsKey(message.obj)) {
                    ((zzc) this.uA.get(message.obj)).resume();
                    break;
                }
                break;
            case 9:
                if (this.uA.containsKey(message.obj)) {
                    ((zzc) this.uA.get(message.obj)).zzapr();
                    break;
                }
                break;
            case 10:
                if (this.uA.containsKey(message.obj)) {
                    ((zzc) this.uA.get(message.obj)).zzaqj();
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
        return true;
    }

    public void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i, 0));
        }
    }

    public <O extends ApiOptions> void zza(com.google.android.gms.common.api.zzc<O> zzc, int i, zzpr$zza<? extends Result, com.google.android.gms.common.api.Api.zzb> zzpr_zza) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new com.google.android.gms.internal.zzpn.zza(zzc.getInstanceId(), i, zzpr_zza)));
    }

    public <O extends ApiOptions, TResult> void zza(com.google.android.gms.common.api.zzc<O> zzc, int i, zzrb<com.google.android.gms.common.api.Api.zzb, TResult> zzrb, TaskCompletionSource<TResult> taskCompletionSource) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new com.google.android.gms.internal.zzpn.zzb(zzc.getInstanceId(), i, zzrb, taskCompletionSource)));
    }

    @WorkerThread
    public void zza(zzpq zzpq) {
        for (zzpo zzpo : zzpq.zzaon()) {
            zzc zzc = (zzc) this.uA.get(zzpo);
            if (zzc == null) {
                zzpq.cancel();
                return;
            } else if (zzc.isConnected()) {
                zzpq.zza(zzpo, ConnectionResult.qR);
            } else if (zzc.zzaqg() != null) {
                zzpq.zza(zzpo, zzc.zzaqg());
            } else {
                zzc.zzb(zzpq);
            }
        }
    }

    public void zza(zzpw zzpw) {
        synchronized (zzamp) {
            if (zzpw == null) {
                this.uB = null;
                this.uC.clear();
            }
        }
    }

    public void zzaol() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
    }

    boolean zzc(ConnectionResult connectionResult, int i) {
        if (!connectionResult.hasResolution() && !this.rX.isUserResolvableError(connectionResult.getErrorCode())) {
            return false;
        }
        this.rX.zza(this.mContext, connectionResult, i);
        return true;
    }

    public void zzd(int i, boolean z) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i, z ? 1 : 2));
    }
}
