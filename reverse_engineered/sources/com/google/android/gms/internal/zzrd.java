package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzd;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class zzrd {
    private static final zzpr$zza<?, ?>[] vt = new zzpr$zza[0];
    private final Map<zzc<?>, zze> tY;
    final Set<zzpr$zza<?, ?>> vu;
    private final zzb vv;

    interface zzb {
        void zzh(zzpr$zza<?, ?> zzpr_zza);
    }

    /* renamed from: com.google.android.gms.internal.zzrd$1 */
    class C33961 implements zzb {
        final /* synthetic */ zzrd vw;

        C33961(zzrd zzrd) {
            this.vw = zzrd;
        }

        public void zzh(zzpr$zza<?, ?> zzpr_zza) {
            this.vw.vu.remove(zzpr_zza);
            if (zzpr_zza.zzaog() != null && null != null) {
                null.remove(zzpr_zza.zzaog().intValue());
            }
        }
    }

    private static class zza implements DeathRecipient, zzb {
        private final WeakReference<zzpr$zza<?, ?>> vx;
        private final WeakReference<zzd> vy;
        private final WeakReference<IBinder> vz;

        private zza(zzpr$zza<?, ?> zzpr_zza, zzd zzd, IBinder iBinder) {
            this.vy = new WeakReference(zzd);
            this.vx = new WeakReference(zzpr_zza);
            this.vz = new WeakReference(iBinder);
        }

        private void zzaqd() {
            zzpr$zza zzpr_zza = (zzpr$zza) this.vx.get();
            zzd zzd = (zzd) this.vy.get();
            if (!(zzd == null || zzpr_zza == null)) {
                zzd.remove(zzpr_zza.zzaog().intValue());
            }
            IBinder iBinder = (IBinder) this.vz.get();
            if (this.vz != null) {
                iBinder.unlinkToDeath(this, 0);
            }
        }

        public void binderDied() {
            zzaqd();
        }

        public void zzh(zzpr$zza<?, ?> zzpr_zza) {
            zzaqd();
        }
    }

    public zzrd(zzc<?> zzc, zze zze) {
        this.vu = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
        this.vv = new C33961(this);
        this.tY = new ArrayMap();
        this.tY.put(zzc, zze);
    }

    public zzrd(Map<zzc<?>, zze> map) {
        this.vu = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
        this.vv = new C33961(this);
        this.tY = map;
    }

    private static void zza(zzpr$zza<?, ?> zzpr_zza, zzd zzd, IBinder iBinder) {
        if (zzpr_zza.isReady()) {
            zzpr_zza.zza(new zza(zzpr_zza, zzd, iBinder));
        } else if (iBinder == null || !iBinder.isBinderAlive()) {
            zzpr_zza.zza(null);
            zzpr_zza.cancel();
            zzd.remove(zzpr_zza.zzaog().intValue());
        } else {
            Object zza = new zza(zzpr_zza, zzd, iBinder);
            zzpr_zza.zza(zza);
            try {
                iBinder.linkToDeath(zza, 0);
            } catch (RemoteException e) {
                zzpr_zza.cancel();
                zzd.remove(zzpr_zza.zzaog().intValue());
            }
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.vu.size());
    }

    public void release() {
        for (zzpr$zza zzpr_zza : (zzpr$zza[]) this.vu.toArray(vt)) {
            zzpr_zza.zza(null);
            if (zzpr_zza.zzaog() != null) {
                zzpr_zza.zzaoo();
                zza(zzpr_zza, null, ((zze) this.tY.get(zzpr_zza.zzanp())).zzans());
                this.vu.remove(zzpr_zza);
            } else if (zzpr_zza.zzaos()) {
                this.vu.remove(zzpr_zza);
            }
        }
    }

    public void zzaqv() {
        for (zzpr$zza zzaa : (zzpr$zza[]) this.vu.toArray(vt)) {
            zzaa.zzaa(new Status(8, "The connection to Google Play services was lost"));
        }
    }

    public boolean zzaqw() {
        for (zzpr$zza isReady : (zzpr$zza[]) this.vu.toArray(vt)) {
            if (!isReady.isReady()) {
                return true;
            }
        }
        return false;
    }

    <A extends com.google.android.gms.common.api.Api.zzb> void zzg(zzpr$zza<? extends Result, A> zzpr_zza) {
        this.vu.add(zzpr_zza);
        zzpr_zza.zza(this.vv);
    }
}
