package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzg$zza;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.signin.internal.SignInResponse;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public class zzqb implements zzqe {
    private final Context mContext;
    private final com.google.android.gms.common.api.Api.zza<? extends zzvx, zzvy> rY;
    private zzq tA;
    private boolean tB;
    private boolean tC;
    private final zzg tD;
    private final Map<Api<?>, Integer> tE;
    private ArrayList<Future<?>> tF = new ArrayList();
    private final Lock th;
    private final zzqf tm;
    private final com.google.android.gms.common.zzc tp;
    private ConnectionResult tq;
    private int tr;
    private int ts = 0;
    private int tt;
    private final Bundle tu = new Bundle();
    private final Set<com.google.android.gms.common.api.Api.zzc> tv = new HashSet();
    private zzvx tw;
    private int tx;
    private boolean ty;
    private boolean tz;

    /* renamed from: com.google.android.gms.internal.zzqb$1 */
    class C33851 implements Runnable {
        final /* synthetic */ zzqb tG;

        C33851(zzqb zzqb) {
            this.tG = zzqb;
        }

        public void run() {
            this.tG.tp.zzbp(this.tG.mContext);
        }
    }

    private static class zza implements com.google.android.gms.common.internal.zzd.zzf {
        private final Api<?> pD;
        private final int sV;
        private final WeakReference<zzqb> tH;

        public zza(zzqb zzqb, Api<?> api, int i) {
            this.tH = new WeakReference(zzqb);
            this.pD = api;
            this.sV = i;
        }

        public void zzh(@NonNull ConnectionResult connectionResult) {
            boolean z = false;
            zzqb zzqb = (zzqb) this.tH.get();
            if (zzqb != null) {
                if (Looper.myLooper() == zzqb.tm.sX.getLooper()) {
                    z = true;
                }
                zzab.zza(z, (Object) "onReportServiceBinding must be called on the GoogleApiClient handler thread");
                zzqb.th.lock();
                try {
                    if (zzqb.zzfg(0)) {
                        if (!connectionResult.isSuccess()) {
                            zzqb.zzb(connectionResult, this.pD, this.sV);
                        }
                        if (zzqb.zzapj()) {
                            zzqb.zzapk();
                        }
                        zzqb.th.unlock();
                    }
                } finally {
                    zzqb.th.unlock();
                }
            }
        }
    }

    private abstract class zzf implements Runnable {
        final /* synthetic */ zzqb tG;

        private zzf(zzqb zzqb) {
            this.tG = zzqb;
        }

        @WorkerThread
        public void run() {
            this.tG.th.lock();
            try {
                if (!Thread.interrupted()) {
                    zzapi();
                    this.tG.th.unlock();
                }
            } catch (RuntimeException e) {
                this.tG.tm.zza(e);
            } finally {
                this.tG.th.unlock();
            }
        }

        @WorkerThread
        protected abstract void zzapi();
    }

    private class zzb extends zzf {
        final /* synthetic */ zzqb tG;
        private final Map<com.google.android.gms.common.api.Api.zze, zza> tI;

        public zzb(zzqb zzqb, Map<com.google.android.gms.common.api.Api.zze, zza> map) {
            this.tG = zzqb;
            super();
            this.tI = map;
        }

        @WorkerThread
        public void zzapi() {
            int i;
            int i2 = 1;
            int i3 = 0;
            int i4 = 1;
            int i5 = 0;
            for (com.google.android.gms.common.api.Api.zze zze : this.tI.keySet()) {
                if (!zze.zzanr()) {
                    i = 0;
                    i4 = i5;
                } else if (((zza) this.tI.get(zze)).sV == 0) {
                    i = 1;
                    break;
                } else {
                    i = i4;
                    i4 = 1;
                }
                i5 = i4;
                i4 = i;
            }
            i2 = i5;
            i = 0;
            if (i2 != 0) {
                i3 = this.tG.tp.isGooglePlayServicesAvailable(this.tG.mContext);
            }
            if (i3 == 0 || (r0 == 0 && i4 == 0)) {
                if (this.tG.ty) {
                    this.tG.tw.connect();
                }
                for (com.google.android.gms.common.api.Api.zze zze2 : this.tI.keySet()) {
                    final com.google.android.gms.common.internal.zzd.zzf zzf = (com.google.android.gms.common.internal.zzd.zzf) this.tI.get(zze2);
                    if (!zze2.zzanr() || i3 == 0) {
                        zze2.zza(zzf);
                    } else {
                        this.tG.tm.zza(new zza(this, this.tG) {
                            final /* synthetic */ zzb tK;

                            public void zzapi() {
                                zzf.zzh(new ConnectionResult(16, null));
                            }
                        });
                    }
                }
                return;
            }
            final ConnectionResult connectionResult = new ConnectionResult(i3, null);
            this.tG.tm.zza(new zza(this, this.tG) {
                final /* synthetic */ zzb tK;

                public void zzapi() {
                    this.tK.tG.zzg(connectionResult);
                }
            });
        }
    }

    private class zzc extends zzf {
        final /* synthetic */ zzqb tG;
        private final ArrayList<com.google.android.gms.common.api.Api.zze> tM;

        public zzc(zzqb zzqb, ArrayList<com.google.android.gms.common.api.Api.zze> arrayList) {
            this.tG = zzqb;
            super();
            this.tM = arrayList;
        }

        @WorkerThread
        public void zzapi() {
            this.tG.tm.sX.tZ = this.tG.zzapp();
            Iterator it = this.tM.iterator();
            while (it.hasNext()) {
                ((com.google.android.gms.common.api.Api.zze) it.next()).zza(this.tG.tA, this.tG.tm.sX.tZ);
            }
        }
    }

    private static class zzd extends com.google.android.gms.signin.internal.zzb {
        private final WeakReference<zzqb> tH;

        zzd(zzqb zzqb) {
            this.tH = new WeakReference(zzqb);
        }

        @BinderThread
        public void zzb(final SignInResponse signInResponse) {
            final zzqb zzqb = (zzqb) this.tH.get();
            if (zzqb != null) {
                zzqb.tm.zza(new zza(this, zzqb) {
                    final /* synthetic */ zzd tP;

                    public void zzapi() {
                        zzqb.zza(signInResponse);
                    }
                });
            }
        }
    }

    private class zze implements ConnectionCallbacks, OnConnectionFailedListener {
        final /* synthetic */ zzqb tG;

        private zze(zzqb zzqb) {
            this.tG = zzqb;
        }

        public void onConnected(Bundle bundle) {
            this.tG.tw.zza(new zzd(this.tG));
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            this.tG.th.lock();
            try {
                if (this.tG.zzf(connectionResult)) {
                    this.tG.zzapn();
                    this.tG.zzapk();
                } else {
                    this.tG.zzg(connectionResult);
                }
                this.tG.th.unlock();
            } catch (Throwable th) {
                this.tG.th.unlock();
            }
        }

        public void onConnectionSuspended(int i) {
        }
    }

    public zzqb(zzqf zzqf, zzg zzg, Map<Api<?>, Integer> map, com.google.android.gms.common.zzc zzc, com.google.android.gms.common.api.Api.zza<? extends zzvx, zzvy> zza, Lock lock, Context context) {
        this.tm = zzqf;
        this.tD = zzg;
        this.tE = map;
        this.tp = zzc;
        this.rY = zza;
        this.th = lock;
        this.mContext = context;
    }

    private void zza(SignInResponse signInResponse) {
        if (zzfg(0)) {
            ConnectionResult zzatd = signInResponse.zzatd();
            if (zzatd.isSuccess()) {
                ResolveAccountResponse zzbzv = signInResponse.zzbzv();
                ConnectionResult zzatd2 = zzbzv.zzatd();
                if (zzatd2.isSuccess()) {
                    this.tz = true;
                    this.tA = zzbzv.zzatc();
                    this.tB = zzbzv.zzate();
                    this.tC = zzbzv.zzatf();
                    zzapk();
                    return;
                }
                String valueOf = String.valueOf(zzatd2);
                Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                zzg(zzatd2);
            } else if (zzf(zzatd)) {
                zzapn();
                zzapk();
            } else {
                zzg(zzatd);
            }
        }
    }

    private boolean zza(int i, int i2, ConnectionResult connectionResult) {
        return (i2 != 1 || zze(connectionResult)) ? this.tq == null || i < this.tr : false;
    }

    private boolean zzapj() {
        this.tt--;
        if (this.tt > 0) {
            return false;
        }
        if (this.tt < 0) {
            Log.i("GoogleApiClientConnecting", this.tm.sX.zzapv());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zzg(new ConnectionResult(8, null));
            return false;
        } else if (this.tq == null) {
            return true;
        } else {
            this.tm.uq = this.tr;
            zzg(this.tq);
            return false;
        }
    }

    private void zzapk() {
        if (this.tt == 0) {
            if (!this.ty || this.tz) {
                zzapl();
            }
        }
    }

    private void zzapl() {
        ArrayList arrayList = new ArrayList();
        this.ts = 1;
        this.tt = this.tm.tY.size();
        for (com.google.android.gms.common.api.Api.zzc zzc : this.tm.tY.keySet()) {
            if (!this.tm.un.containsKey(zzc)) {
                arrayList.add((com.google.android.gms.common.api.Api.zze) this.tm.tY.get(zzc));
            } else if (zzapj()) {
                zzapm();
            }
        }
        if (!arrayList.isEmpty()) {
            this.tF.add(zzqg.zzapz().submit(new zzc(this, arrayList)));
        }
    }

    private void zzapm() {
        this.tm.zzapx();
        zzqg.zzapz().execute(new C33851(this));
        if (this.tw != null) {
            if (this.tB) {
                this.tw.zza(this.tA, this.tC);
            }
            zzbl(false);
        }
        for (com.google.android.gms.common.api.Api.zzc zzc : this.tm.un.keySet()) {
            ((com.google.android.gms.common.api.Api.zze) this.tm.tY.get(zzc)).disconnect();
        }
        this.tm.ur.zzm(this.tu.isEmpty() ? null : this.tu);
    }

    private void zzapn() {
        this.ty = false;
        this.tm.sX.tZ = Collections.emptySet();
        for (com.google.android.gms.common.api.Api.zzc zzc : this.tv) {
            if (!this.tm.un.containsKey(zzc)) {
                this.tm.un.put(zzc, new ConnectionResult(17, null));
            }
        }
    }

    private void zzapo() {
        Iterator it = this.tF.iterator();
        while (it.hasNext()) {
            ((Future) it.next()).cancel(true);
        }
        this.tF.clear();
    }

    private Set<Scope> zzapp() {
        if (this.tD == null) {
            return Collections.emptySet();
        }
        Set<Scope> hashSet = new HashSet(this.tD.zzasf());
        Map zzash = this.tD.zzash();
        for (Api api : zzash.keySet()) {
            if (!this.tm.un.containsKey(api.zzanp())) {
                hashSet.addAll(((zzg$zza) zzash.get(api)).dY);
            }
        }
        return hashSet;
    }

    private void zzb(ConnectionResult connectionResult, Api<?> api, int i) {
        if (i != 2) {
            int priority = api.zzanm().getPriority();
            if (zza(priority, i, connectionResult)) {
                this.tq = connectionResult;
                this.tr = priority;
            }
        }
        this.tm.un.put(api.zzanp(), connectionResult);
    }

    private void zzbl(boolean z) {
        if (this.tw != null) {
            if (this.tw.isConnected() && z) {
                this.tw.zzbzk();
            }
            this.tw.disconnect();
            this.tA = null;
        }
    }

    private boolean zze(ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || this.tp.zzfa(connectionResult.getErrorCode()) != null;
    }

    private boolean zzf(ConnectionResult connectionResult) {
        return this.tx != 2 ? this.tx == 1 && !connectionResult.hasResolution() : true;
    }

    private boolean zzfg(int i) {
        if (this.ts == i) {
            return true;
        }
        Log.i("GoogleApiClientConnecting", this.tm.sX.zzapv());
        String valueOf = String.valueOf(this);
        Log.i("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Unexpected callback in ").append(valueOf).toString());
        Log.i("GoogleApiClientConnecting", "mRemainingConnections=" + this.tt);
        valueOf = String.valueOf(zzfh(this.ts));
        String valueOf2 = String.valueOf(zzfh(i));
        Log.wtf("GoogleApiClientConnecting", new StringBuilder((String.valueOf(valueOf).length() + 70) + String.valueOf(valueOf2).length()).append("GoogleApiClient connecting is in step ").append(valueOf).append(" but received callback for step ").append(valueOf2).toString(), new Exception());
        zzg(new ConnectionResult(8, null));
        return false;
    }

    private String zzfh(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    private void zzg(ConnectionResult connectionResult) {
        zzapo();
        zzbl(!connectionResult.hasResolution());
        this.tm.zzi(connectionResult);
        this.tm.ur.zzd(connectionResult);
    }

    public void begin() {
        this.tm.un.clear();
        this.ty = false;
        this.tq = null;
        this.ts = 0;
        this.tx = 2;
        this.tz = false;
        this.tB = false;
        Map hashMap = new HashMap();
        int i = 0;
        for (Api api : this.tE.keySet()) {
            com.google.android.gms.common.api.Api.zze zze = (com.google.android.gms.common.api.Api.zze) this.tm.tY.get(api.zzanp());
            int intValue = ((Integer) this.tE.get(api)).intValue();
            int i2 = (api.zzanm().getPriority() == 1 ? 1 : 0) | i;
            if (zze.zzafk()) {
                this.ty = true;
                if (intValue < this.tx) {
                    this.tx = intValue;
                }
                if (intValue != 0) {
                    this.tv.add(api.zzanp());
                }
            }
            hashMap.put(zze, new zza(this, api, intValue));
            i = i2;
        }
        if (i != 0) {
            this.ty = false;
        }
        if (this.ty) {
            this.tD.zzc(Integer.valueOf(this.tm.sX.getSessionId()));
            ConnectionCallbacks zze2 = new zze();
            this.tw = (zzvx) this.rY.zza(this.mContext, this.tm.sX.getLooper(), this.tD, this.tD.zzasl(), zze2, zze2);
        }
        this.tt = this.tm.tY.size();
        this.tF.add(zzqg.zzapz().submit(new zzb(this, hashMap)));
    }

    public void connect() {
    }

    public boolean disconnect() {
        zzapo();
        zzbl(true);
        this.tm.zzi(null);
        return true;
    }

    public void onConnected(Bundle bundle) {
        if (zzfg(1)) {
            if (bundle != null) {
                this.tu.putAll(bundle);
            }
            if (zzapj()) {
                zzapm();
            }
        }
    }

    public void onConnectionSuspended(int i) {
        zzg(new ConnectionResult(8, null));
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
        if (zzfg(1)) {
            zzb(connectionResult, api, i);
            if (zzapj()) {
                zzapm();
            }
        }
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends zzpr$zza<R, A>> T zzc(T t) {
        this.tm.sX.tS.add(t);
        return t;
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends zzpr$zza<? extends Result, A>> T zzd(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
