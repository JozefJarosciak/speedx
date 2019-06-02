package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final class zzn extends zzm implements Callback {
    private final Handler mHandler;
    private final HashMap<zza, zzb> yB = new HashMap();
    private final com.google.android.gms.common.stats.zzb yC;
    private final long yD;
    private final Context zzaqj;

    private static final class zza {
        private final String yE;
        private final ComponentName yF;
        private final String zzcvf;

        public zza(ComponentName componentName) {
            this.zzcvf = null;
            this.yE = null;
            this.yF = (ComponentName) zzab.zzaa(componentName);
        }

        public zza(String str, String str2) {
            this.zzcvf = zzab.zzhs(str);
            this.yE = zzab.zzhs(str2);
            this.yF = null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return zzaa.equal(this.zzcvf, zza.zzcvf) && zzaa.equal(this.yF, zza.yF);
        }

        public int hashCode() {
            return zzaa.hashCode(this.zzcvf, this.yF);
        }

        public String toString() {
            return this.zzcvf == null ? this.yF.flattenToString() : this.zzcvf;
        }

        public Intent zzasu() {
            return this.zzcvf != null ? new Intent(this.zzcvf).setPackage(this.yE) : new Intent().setComponent(this.yF);
        }
    }

    private final class zzb {
        private int mState = 2;
        private IBinder xA;
        private ComponentName yF;
        private final zza yG = new zza(this);
        private final Set<ServiceConnection> yH = new HashSet();
        private boolean yI;
        private final zza yJ;
        final /* synthetic */ zzn yK;

        public class zza implements ServiceConnection {
            final /* synthetic */ zzb yL;

            public zza(zzb zzb) {
                this.yL = zzb;
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (this.yL.yK.yB) {
                    this.yL.xA = iBinder;
                    this.yL.yF = componentName;
                    for (ServiceConnection onServiceConnected : this.yL.yH) {
                        onServiceConnected.onServiceConnected(componentName, iBinder);
                    }
                    this.yL.mState = 1;
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (this.yL.yK.yB) {
                    this.yL.xA = null;
                    this.yL.yF = componentName;
                    for (ServiceConnection onServiceDisconnected : this.yL.yH) {
                        onServiceDisconnected.onServiceDisconnected(componentName);
                    }
                    this.yL.mState = 2;
                }
            }
        }

        public zzb(zzn zzn, zza zza) {
            this.yK = zzn;
            this.yJ = zza;
        }

        public IBinder getBinder() {
            return this.xA;
        }

        public ComponentName getComponentName() {
            return this.yF;
        }

        public int getState() {
            return this.mState;
        }

        public boolean isBound() {
            return this.yI;
        }

        public void zza(ServiceConnection serviceConnection, String str) {
            this.yK.yC.zza(this.yK.zzaqj, serviceConnection, str, this.yJ.zzasu());
            this.yH.add(serviceConnection);
        }

        public boolean zza(ServiceConnection serviceConnection) {
            return this.yH.contains(serviceConnection);
        }

        public boolean zzasv() {
            return this.yH.isEmpty();
        }

        public void zzb(ServiceConnection serviceConnection, String str) {
            this.yK.yC.zzb(this.yK.zzaqj, serviceConnection);
            this.yH.remove(serviceConnection);
        }

        @TargetApi(14)
        public void zzhn(String str) {
            this.mState = 3;
            this.yI = this.yK.yC.zza(this.yK.zzaqj, str, this.yJ.zzasu(), this.yG, 129);
            if (!this.yI) {
                this.mState = 2;
                try {
                    this.yK.yC.zza(this.yK.zzaqj, this.yG);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        public void zzho(String str) {
            this.yK.yC.zza(this.yK.zzaqj, this.yG);
            this.yI = false;
            this.mState = 2;
        }
    }

    zzn(Context context) {
        this.zzaqj = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.yC = com.google.android.gms.common.stats.zzb.zzaut();
        this.yD = 5000;
    }

    private boolean zza(zza zza, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        zzab.zzb((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.yB) {
            zzb zzb = (zzb) this.yB.get(zza);
            if (zzb != null) {
                this.mHandler.removeMessages(0, zzb);
                if (!zzb.zza(serviceConnection)) {
                    zzb.zza(serviceConnection, str);
                    switch (zzb.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zzb.getComponentName(), zzb.getBinder());
                            break;
                        case 2:
                            zzb.zzhn(str);
                            break;
                        default:
                            break;
                    }
                }
                String valueOf = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
            }
            zzb = new zzb(this, zza);
            zzb.zza(serviceConnection, str);
            zzb.zzhn(str);
            this.yB.put(zza, zzb);
            isBound = zzb.isBound();
        }
        return isBound;
    }

    private void zzb(zza zza, ServiceConnection serviceConnection, String str) {
        zzab.zzb((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.yB) {
            zzb zzb = (zzb) this.yB.get(zza);
            String valueOf;
            if (zzb == null) {
                valueOf = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (zzb.zza(serviceConnection)) {
                zzb.zzb(serviceConnection, str);
                if (zzb.zzasv()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zzb), this.yD);
                }
            } else {
                valueOf = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf).toString());
            }
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                zzb zzb = (zzb) message.obj;
                synchronized (this.yB) {
                    if (zzb.zzasv()) {
                        if (zzb.isBound()) {
                            zzb.zzho("GmsClientSupervisor");
                        }
                        this.yB.remove(zzb.yJ);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    public boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return zza(new zza(componentName), serviceConnection, str);
    }

    public boolean zza(String str, String str2, ServiceConnection serviceConnection, String str3) {
        return zza(new zza(str, str2), serviceConnection, str3);
    }

    public void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zzb(new zza(componentName), serviceConnection, str);
    }

    public void zzb(String str, String str2, ServiceConnection serviceConnection, String str3) {
        zzb(new zza(str, str2), serviceConnection, str3);
    }
}
