package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzd<T extends IInterface> {
    public static final String[] xt = new String[]{"service_esmobile", "service_googleme"};
    private final Context mContext;
    final Handler mHandler;
    private final com.google.android.gms.common.zzc tp;
    private int xb;
    private long xc;
    private long xd;
    private int xe;
    private long xf;
    private final zzm xg;
    private final Object xh;
    private zzu xi;
    private zzf xj;
    private T xk;
    private final ArrayList<zze<?>> xl;
    private zzh xm;
    private int xn;
    private final zzb xo;
    private final zzc xp;
    private final int xq;
    private final String xr;
    protected AtomicInteger xs;
    private final Looper zzahv;
    private final Object zzail;

    protected abstract class zze<TListener> {
        private TListener mListener;
        final /* synthetic */ zzd xv;
        private boolean xw = false;

        public zze(zzd zzd, TListener tListener) {
            this.xv = zzd;
            this.mListener = tListener;
        }

        public void unregister() {
            zzasc();
            synchronized (this.xv.xl) {
                this.xv.xl.remove(this);
            }
        }

        protected abstract void zzasa();

        public void zzasb() {
            synchronized (this) {
                Object obj = this.mListener;
                if (this.xw) {
                    String valueOf = String.valueOf(this);
                    Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
                }
            }
            if (obj != null) {
                try {
                    zzx(obj);
                } catch (RuntimeException e) {
                    zzasa();
                    throw e;
                }
            }
            zzasa();
            synchronized (this) {
                this.xw = true;
            }
            unregister();
        }

        public void zzasc() {
            synchronized (this) {
                this.mListener = null;
            }
        }

        protected abstract void zzx(TListener tListener);
    }

    private abstract class zza extends zze<Boolean> {
        public final int statusCode;
        public final Bundle xu;
        final /* synthetic */ zzd xv;

        @BinderThread
        protected zza(zzd zzd, int i, Bundle bundle) {
            this.xv = zzd;
            super(zzd, Boolean.valueOf(true));
            this.statusCode = i;
            this.xu = bundle;
        }

        protected abstract boolean zzarz();

        protected void zzasa() {
        }

        protected void zzc(Boolean bool) {
            PendingIntent pendingIntent = null;
            if (bool == null) {
                this.xv.zzb(1, null);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    if (!zzarz()) {
                        this.xv.zzb(1, null);
                        zzl(new ConnectionResult(8, null));
                        return;
                    }
                    return;
                case 10:
                    this.xv.zzb(1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    this.xv.zzb(1, null);
                    if (this.xu != null) {
                        pendingIntent = (PendingIntent) this.xu.getParcelable("pendingIntent");
                    }
                    zzl(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }

        protected abstract void zzl(ConnectionResult connectionResult);

        protected /* synthetic */ void zzx(Object obj) {
            zzc((Boolean) obj);
        }
    }

    public interface zzb {
        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface zzc {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    final class zzd extends Handler {
        final /* synthetic */ zzd xv;

        public zzd(zzd zzd, Looper looper) {
            this.xv = zzd;
            super(looper);
        }

        private void zza(Message message) {
            zze zze = (zze) message.obj;
            zze.zzasa();
            zze.unregister();
        }

        private boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 5;
        }

        public void handleMessage(Message message) {
            PendingIntent pendingIntent = null;
            if (this.xv.xs.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 5) && !this.xv.isConnecting()) {
                zza(message);
            } else if (message.what == 3) {
                if (message.obj instanceof PendingIntent) {
                    pendingIntent = (PendingIntent) message.obj;
                }
                ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
                this.xv.xj.zzh(connectionResult);
                this.xv.onConnectionFailed(connectionResult);
            } else if (message.what == 4) {
                this.xv.zzb(4, null);
                if (this.xv.xo != null) {
                    this.xv.xo.onConnectionSuspended(message.arg2);
                }
                this.xv.onConnectionSuspended(message.arg2);
                this.xv.zza(4, 1, null);
            } else if (message.what == 2 && !this.xv.isConnected()) {
                zza(message);
            } else if (zzb(message)) {
                ((zze) message.obj).zzasb();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
            }
        }
    }

    public interface zzf {
        void zzh(@NonNull ConnectionResult connectionResult);
    }

    public static final class zzg extends com.google.android.gms.common.internal.zzt.zza {
        private zzd xx;
        private final int xy;

        public zzg(@NonNull zzd zzd, int i) {
            this.xx = zzd;
            this.xy = i;
        }

        private void zzasd() {
            this.xx = null;
        }

        @BinderThread
        public void zza(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            zzab.zzb(this.xx, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
            this.xx.zza(i, iBinder, bundle, this.xy);
            zzasd();
        }

        @BinderThread
        public void zzb(int i, @Nullable Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }
    }

    public final class zzh implements ServiceConnection {
        final /* synthetic */ zzd xv;
        private final int xy;

        public zzh(zzd zzd, int i) {
            this.xv = zzd;
            this.xy = i;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            zzab.zzb((Object) iBinder, (Object) "Expecting a valid IBinder");
            synchronized (this.xv.xh) {
                this.xv.xi = com.google.android.gms.common.internal.zzu.zza.zzdt(iBinder);
            }
            this.xv.zza(0, null, this.xy);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (this.xv.xh) {
                this.xv.xi = null;
            }
            this.xv.mHandler.sendMessage(this.xv.mHandler.obtainMessage(4, this.xy, 1));
        }
    }

    protected class zzi implements zzf {
        final /* synthetic */ zzd xv;

        public zzi(zzd zzd) {
            this.xv = zzd;
        }

        public void zzh(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                this.xv.zza(null, this.xv.zzary());
            } else if (this.xv.xp != null) {
                this.xv.xp.onConnectionFailed(connectionResult);
            }
        }
    }

    protected final class zzj extends zza {
        final /* synthetic */ zzd xv;
        public final IBinder xz;

        @BinderThread
        public zzj(zzd zzd, int i, IBinder iBinder, Bundle bundle) {
            this.xv = zzd;
            super(zzd, i, bundle);
            this.xz = iBinder;
        }

        protected boolean zzarz() {
            try {
                String interfaceDescriptor = this.xz.getInterfaceDescriptor();
                if (this.xv.zzrb().equals(interfaceDescriptor)) {
                    IInterface zzbb = this.xv.zzbb(this.xz);
                    if (zzbb == null || !this.xv.zza(2, 3, zzbb)) {
                        return false;
                    }
                    Bundle zzamc = this.xv.zzamc();
                    if (this.xv.xo != null) {
                        this.xv.xo.onConnected(zzamc);
                    }
                    return true;
                }
                String valueOf = String.valueOf(this.xv.zzrb());
                Log.e("GmsClient", new StringBuilder((String.valueOf(valueOf).length() + 34) + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(valueOf).append(" vs. ").append(interfaceDescriptor).toString());
                return false;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }

        protected void zzl(ConnectionResult connectionResult) {
            if (this.xv.xp != null) {
                this.xv.xp.onConnectionFailed(connectionResult);
            }
            this.xv.onConnectionFailed(connectionResult);
        }
    }

    protected final class zzk extends zza {
        final /* synthetic */ zzd xv;

        @BinderThread
        public zzk(zzd zzd, int i, @Nullable Bundle bundle) {
            this.xv = zzd;
            super(zzd, i, bundle);
        }

        protected boolean zzarz() {
            this.xv.xj.zzh(ConnectionResult.qR);
            return true;
        }

        protected void zzl(ConnectionResult connectionResult) {
            this.xv.xj.zzh(connectionResult);
            this.xv.onConnectionFailed(connectionResult);
        }
    }

    protected zzd(Context context, Looper looper, int i, zzb zzb, zzc zzc, String str) {
        this(context, looper, zzm.zzce(context), com.google.android.gms.common.zzc.zzand(), i, (zzb) zzab.zzaa(zzb), (zzc) zzab.zzaa(zzc), str);
    }

    protected zzd(Context context, Looper looper, zzm zzm, com.google.android.gms.common.zzc zzc, int i, zzb zzb, zzc zzc2, String str) {
        this.zzail = new Object();
        this.xh = new Object();
        this.xl = new ArrayList();
        this.xn = 1;
        this.xs = new AtomicInteger(0);
        this.mContext = (Context) zzab.zzb((Object) context, (Object) "Context must not be null");
        this.zzahv = (Looper) zzab.zzb((Object) looper, (Object) "Looper must not be null");
        this.xg = (zzm) zzab.zzb((Object) zzm, (Object) "Supervisor must not be null");
        this.tp = (com.google.android.gms.common.zzc) zzab.zzb((Object) zzc, (Object) "API availability must not be null");
        this.mHandler = new zzd(this, looper);
        this.xq = i;
        this.xo = zzb;
        this.xp = zzc2;
        this.xr = str;
    }

    private boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.zzail) {
            if (this.xn != i) {
                z = false;
            } else {
                zzb(i2, t);
                z = true;
            }
        }
        return z;
    }

    private void zzarr() {
        if (this.xm != null) {
            String valueOf = String.valueOf(zzra());
            String valueOf2 = String.valueOf(zzarp());
            Log.e("GmsClient", new StringBuilder((String.valueOf(valueOf).length() + 70) + String.valueOf(valueOf2).length()).append("Calling connect() while still connected, missing disconnect() for ").append(valueOf).append(" on ").append(valueOf2).toString());
            this.xg.zzb(zzra(), zzarp(), this.xm, zzarq());
            this.xs.incrementAndGet();
        }
        this.xm = new zzh(this, this.xs.get());
        if (!this.xg.zza(zzra(), zzarp(), this.xm, zzarq())) {
            valueOf = String.valueOf(zzra());
            valueOf2 = String.valueOf(zzarp());
            Log.e("GmsClient", new StringBuilder((String.valueOf(valueOf).length() + 34) + String.valueOf(valueOf2).length()).append("unable to connect to service: ").append(valueOf).append(" on ").append(valueOf2).toString());
            zza(16, null, this.xs.get());
        }
    }

    private void zzars() {
        if (this.xm != null) {
            this.xg.zzb(zzra(), zzarp(), this.xm, zzarq());
            this.xm = null;
        }
    }

    private void zzb(int i, T t) {
        boolean z = true;
        if ((i == 3) != (t != null)) {
            z = false;
        }
        zzab.zzbn(z);
        synchronized (this.zzail) {
            this.xn = i;
            this.xk = t;
            zzc(i, t);
            switch (i) {
                case 1:
                    zzars();
                    break;
                case 2:
                    zzarr();
                    break;
                case 3:
                    zza((IInterface) t);
                    break;
            }
        }
    }

    public void disconnect() {
        this.xs.incrementAndGet();
        synchronized (this.xl) {
            int size = this.xl.size();
            for (int i = 0; i < size; i++) {
                ((zze) this.xl.get(i)).zzasc();
            }
            this.xl.clear();
        }
        synchronized (this.xh) {
            this.xi = null;
        }
        zzb(1, null);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (this.zzail) {
            int i = this.xn;
            IInterface iInterface = this.xk;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("CONNECTING");
                break;
            case 3:
                printWriter.print("CONNECTED");
                break;
            case 4:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.println("null");
        } else {
            printWriter.append(zzrb()).append("@").println(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.xd > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.xd;
            String valueOf = String.valueOf(simpleDateFormat.format(new Date(this.xd)));
            append.println(new StringBuilder(String.valueOf(valueOf).length() + 21).append(j).append(" ").append(valueOf).toString());
        }
        if (this.xc > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.xb) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.xb));
                    break;
            }
            append = printWriter.append(" lastSuspendedTime=");
            j = this.xc;
            valueOf = String.valueOf(simpleDateFormat.format(new Date(this.xc)));
            append.println(new StringBuilder(String.valueOf(valueOf).length() + 21).append(j).append(" ").append(valueOf).toString());
        }
        if (this.xf > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.xe));
            append = printWriter.append(" lastFailedTime=");
            j = this.xf;
            String valueOf2 = String.valueOf(simpleDateFormat.format(new Date(this.xf)));
            append.println(new StringBuilder(String.valueOf(valueOf2).length() + 21).append(j).append(" ").append(valueOf2).toString());
        }
    }

    public Account getAccount() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzahv;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.zzail) {
            z = this.xn == 3;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzail) {
            z = this.xn == 2;
        }
        return z;
    }

    @CallSuper
    protected void onConnectionFailed(ConnectionResult connectionResult) {
        this.xe = connectionResult.getErrorCode();
        this.xf = System.currentTimeMillis();
    }

    @CallSuper
    protected void onConnectionSuspended(int i) {
        this.xb = i;
        this.xc = System.currentTimeMillis();
    }

    protected void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i2, -1, new zzk(this, i, bundle)));
    }

    @BinderThread
    protected void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzj(this, i, iBinder, bundle)));
    }

    @CallSuper
    protected void zza(@NonNull T t) {
        this.xd = System.currentTimeMillis();
    }

    public void zza(@NonNull zzf zzf) {
        this.xj = (zzf) zzab.zzb((Object) zzf, (Object) "Connection progress callbacks cannot be null.");
        zzb(2, null);
    }

    public void zza(zzf zzf, ConnectionResult connectionResult) {
        this.xj = (zzf) zzab.zzb((Object) zzf, (Object) "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.xs.get(), connectionResult.getErrorCode(), connectionResult.getResolution()));
    }

    @WorkerThread
    public void zza(zzq zzq, Set<Scope> set) {
        try {
            GetServiceRequest zzn = new GetServiceRequest(this.xq).zzhm(this.mContext.getPackageName()).zzn(zzaeu());
            if (set != null) {
                zzn.zzf(set);
            }
            if (zzafk()) {
                zzn.zzd(zzaru()).zzb(zzq);
            } else if (zzarx()) {
                zzn.zzd(getAccount());
            }
            synchronized (this.xh) {
                if (this.xi != null) {
                    this.xi.zza(new zzg(this, this.xs.get()), zzn);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            zzfy(1);
        } catch (Throwable e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        }
    }

    protected Bundle zzaeu() {
        return new Bundle();
    }

    public boolean zzafk() {
        return false;
    }

    public boolean zzafz() {
        return false;
    }

    public Intent zzaga() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public Bundle zzamc() {
        return null;
    }

    public boolean zzanr() {
        return true;
    }

    @Nullable
    public IBinder zzans() {
        IBinder iBinder;
        synchronized (this.xh) {
            if (this.xi == null) {
                iBinder = null;
            } else {
                iBinder = this.xi.asBinder();
            }
        }
        return iBinder;
    }

    protected String zzarp() {
        return "com.google.android.gms";
    }

    @Nullable
    protected final String zzarq() {
        return this.xr == null ? this.mContext.getClass().getName() : this.xr;
    }

    public void zzart() {
        int isGooglePlayServicesAvailable = this.tp.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            zzb(1, null);
            this.xj = new zzi(this);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.xs.get(), isGooglePlayServicesAvailable));
            return;
        }
        zza(new zzi(this));
    }

    public final Account zzaru() {
        return getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
    }

    protected final void zzarv() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T zzarw() throws DeadObjectException {
        T t;
        synchronized (this.zzail) {
            if (this.xn == 4) {
                throw new DeadObjectException();
            }
            zzarv();
            zzab.zza(this.xk != null, (Object) "Client is connected but service is null");
            t = this.xk;
        }
        return t;
    }

    public boolean zzarx() {
        return false;
    }

    protected Set<Scope> zzary() {
        return Collections.EMPTY_SET;
    }

    @Nullable
    protected abstract T zzbb(IBinder iBinder);

    void zzc(int i, T t) {
    }

    public void zzfy(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.xs.get(), i));
    }

    @NonNull
    protected abstract String zzra();

    @NonNull
    protected abstract String zzrb();
}
