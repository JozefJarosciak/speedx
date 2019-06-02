package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzg;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzqf implements zzqm {
    private final Context mContext;
    final com.google.android.gms.common.api.Api.zza<? extends zzvx, zzvy> rY;
    final zzqd sX;
    final zzg tD;
    final Map<Api<?>, Integer> tE;
    final Map<zzc<?>, zze> tY;
    private final Lock th;
    private final com.google.android.gms.common.zzc tp;
    private final Condition ul;
    private final zzb um;
    final Map<zzc<?>, ConnectionResult> un = new HashMap();
    private volatile zzqe uo;
    private ConnectionResult up = null;
    int uq;
    final com.google.android.gms.internal.zzqm.zza ur;

    static abstract class zza {
        private final zzqe us;

        protected zza(zzqe zzqe) {
            this.us = zzqe;
        }

        protected abstract void zzapi();

        public final void zzd(zzqf zzqf) {
            zzqf.th.lock();
            try {
                if (zzqf.uo == this.us) {
                    zzapi();
                    zzqf.th.unlock();
                }
            } finally {
                zzqf.th.unlock();
            }
        }
    }

    final class zzb extends Handler {
        final /* synthetic */ zzqf ut;

        zzb(zzqf zzqf, Looper looper) {
            this.ut = zzqf;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    ((zza) message.obj).zzd(this.ut);
                    return;
                case 2:
                    throw ((RuntimeException) message.obj);
                default:
                    Log.w("GACStateManager", "Unknown message id: " + message.what);
                    return;
            }
        }
    }

    public zzqf(Context context, zzqd zzqd, Lock lock, Looper looper, com.google.android.gms.common.zzc zzc, Map<zzc<?>, zze> map, zzg zzg, Map<Api<?>, Integer> map2, com.google.android.gms.common.api.Api.zza<? extends zzvx, zzvy> zza, ArrayList<zzpu> arrayList, com.google.android.gms.internal.zzqm.zza zza2) {
        this.mContext = context;
        this.th = lock;
        this.tp = zzc;
        this.tY = map;
        this.tD = zzg;
        this.tE = map2;
        this.rY = zza;
        this.sX = zzqd;
        this.ur = zza2;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((zzpu) it.next()).zza(this);
        }
        this.um = new zzb(this, looper);
        this.ul = lock.newCondition();
        this.uo = new zzqc(this);
    }

    public ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.ul.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.qR : this.up != null ? this.up : new ConnectionResult(13, null);
    }

    public ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long toNanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (toNanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            }
            toNanos = this.ul.awaitNanos(toNanos);
        }
        return isConnected() ? ConnectionResult.qR : this.up != null ? this.up : new ConnectionResult(13, null);
    }

    public void connect() {
        this.uo.connect();
    }

    public void disconnect() {
        if (this.uo.disconnect()) {
            this.un.clear();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append(str).append("mState=").println(this.uo);
        for (Api api : this.tE.keySet()) {
            printWriter.append(str).append(api.getName()).println(":");
            ((zze) this.tY.get(api.zzanp())).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        zzc zzanp = api.zzanp();
        if (this.tY.containsKey(zzanp)) {
            if (((zze) this.tY.get(zzanp)).isConnected()) {
                return ConnectionResult.qR;
            }
            if (this.un.containsKey(zzanp)) {
                return (ConnectionResult) this.un.get(zzanp);
            }
        }
        return null;
    }

    public boolean isConnected() {
        return this.uo instanceof zzqa;
    }

    public boolean isConnecting() {
        return this.uo instanceof zzqb;
    }

    public void onConnected(@Nullable Bundle bundle) {
        this.th.lock();
        try {
            this.uo.onConnected(bundle);
        } finally {
            this.th.unlock();
        }
    }

    public void onConnectionSuspended(int i) {
        this.th.lock();
        try {
            this.uo.onConnectionSuspended(i);
        } finally {
            this.th.unlock();
        }
    }

    public void zza(@NonNull ConnectionResult connectionResult, @NonNull Api<?> api, int i) {
        this.th.lock();
        try {
            this.uo.zza(connectionResult, api, i);
        } finally {
            this.th.unlock();
        }
    }

    void zza(zza zza) {
        this.um.sendMessage(this.um.obtainMessage(1, zza));
    }

    void zza(RuntimeException runtimeException) {
        this.um.sendMessage(this.um.obtainMessage(2, runtimeException));
    }

    public boolean zza(zzqy zzqy) {
        return false;
    }

    public void zzaoc() {
    }

    public void zzaoy() {
        if (isConnected()) {
            ((zzqa) this.uo).zzaph();
        }
    }

    void zzapw() {
        this.th.lock();
        try {
            this.uo = new zzqb(this, this.tD, this.tE, this.tp, this.rY, this.th, this.mContext);
            this.uo.begin();
            this.ul.signalAll();
        } finally {
            this.th.unlock();
        }
    }

    void zzapx() {
        this.th.lock();
        try {
            this.sX.zzapt();
            this.uo = new zzqa(this);
            this.uo.begin();
            this.ul.signalAll();
        } finally {
            this.th.unlock();
        }
    }

    void zzapy() {
        for (zze disconnect : this.tY.values()) {
            disconnect.disconnect();
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends zzpr$zza<R, A>> T zzc(@NonNull T t) {
        t.zzaot();
        return this.uo.zzc(t);
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends zzpr$zza<? extends Result, A>> T zzd(@NonNull T t) {
        t.zzaot();
        return this.uo.zzd(t);
    }

    void zzi(ConnectionResult connectionResult) {
        this.th.lock();
        try {
            this.up = connectionResult;
            this.uo = new zzqc(this);
            this.uo.begin();
            this.ul.signalAll();
        } finally {
            this.th.unlock();
        }
    }
}
