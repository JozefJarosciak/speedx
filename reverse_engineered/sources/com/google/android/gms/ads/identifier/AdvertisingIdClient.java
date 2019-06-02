package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.alipay.sdk.util.C0880h;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.stats.zzb;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzcc;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AdvertisingIdClient {
    private final Context mContext;
    com.google.android.gms.common.zza zzajb;
    zzcc zzajc;
    boolean zzajd;
    Object zzaje;
    zza zzajf;
    final long zzajg;

    public static final class Info {
        private final String zzajl;
        private final boolean zzajm;

        public Info(String str, boolean z) {
            this.zzajl = str;
            this.zzajm = z;
        }

        public String getId() {
            return this.zzajl;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.zzajm;
        }

        public String toString() {
            String str = this.zzajl;
            return new StringBuilder(String.valueOf(str).length() + 7).append("{").append(str).append(C0880h.f2222d).append(this.zzajm).toString();
        }
    }

    static class zza extends Thread {
        private WeakReference<AdvertisingIdClient> zzajh;
        private long zzaji;
        CountDownLatch zzajj = new CountDownLatch(1);
        boolean zzajk = false;

        public zza(AdvertisingIdClient advertisingIdClient, long j) {
            this.zzajh = new WeakReference(advertisingIdClient);
            this.zzaji = j;
            start();
        }

        private void disconnect() {
            AdvertisingIdClient advertisingIdClient = (AdvertisingIdClient) this.zzajh.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.zzajk = true;
            }
        }

        public void cancel() {
            this.zzajj.countDown();
        }

        public void run() {
            try {
                if (!this.zzajj.await(this.zzaji, TimeUnit.MILLISECONDS)) {
                    disconnect();
                }
            } catch (InterruptedException e) {
                disconnect();
            }
        }

        public boolean zzdk() {
            return this.zzajk;
        }
    }

    public AdvertisingIdClient(Context context) {
        this(context, 30000);
    }

    public AdvertisingIdClient(Context context, long j) {
        this.zzaje = new Object();
        zzab.zzaa(context);
        this.mContext = context;
        this.zzajd = false;
        this.zzajg = j;
    }

    public static Info getAdvertisingIdInfo(Context context) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1);
        try {
            advertisingIdClient.zze(false);
            Info info = advertisingIdClient.getInfo();
            return info;
        } finally {
            advertisingIdClient.finish();
        }
    }

    public static void setShouldSkipGmsCoreVersionCheck(boolean z) {
    }

    static zzcc zza(Context context, com.google.android.gms.common.zza zza) throws IOException {
        try {
            return com.google.android.gms.internal.zzcc.zza.zzf(zza.zza(AbstractComponentTracker.LINGERING_TIMEOUT, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new IOException("Interrupted exception");
        } catch (Throwable th) {
            IOException iOException = new IOException(th);
        }
    }

    private void zzdj() {
        synchronized (this.zzaje) {
            if (this.zzajf != null) {
                this.zzajf.cancel();
                try {
                    this.zzajf.join();
                } catch (InterruptedException e) {
                }
            }
            if (this.zzajg > 0) {
                this.zzajf = new zza(this, this.zzajg);
            }
        }
    }

    static com.google.android.gms.common.zza zzh(Context context) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            switch (zzc.zzand().isGooglePlayServicesAvailable(context)) {
                case 0:
                case 2:
                    ServiceConnection zza = new com.google.android.gms.common.zza();
                    Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                    intent.setPackage("com.google.android.gms");
                    try {
                        if (zzb.zzaut().zza(context, intent, zza, 1)) {
                            return zza;
                        }
                        throw new IOException("Connection failure");
                    } catch (Throwable th) {
                        IOException iOException = new IOException(th);
                    }
                default:
                    throw new IOException("Google Play services not available");
            }
        } catch (NameNotFoundException e) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
    }

    protected void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void finish() {
        /*
        r3 = this;
        r0 = "Calling this from your main thread can lead to deadlock";
        com.google.android.gms.common.internal.zzab.zzhk(r0);
        monitor-enter(r3);
        r0 = r3.mContext;	 Catch:{ all -> 0x002a }
        if (r0 == 0) goto L_0x000e;
    L_0x000a:
        r0 = r3.zzajb;	 Catch:{ all -> 0x002a }
        if (r0 != 0) goto L_0x0010;
    L_0x000e:
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
    L_0x000f:
        return;
    L_0x0010:
        r0 = r3.zzajd;	 Catch:{ IllegalArgumentException -> 0x002d }
        if (r0 == 0) goto L_0x001f;
    L_0x0014:
        r0 = com.google.android.gms.common.stats.zzb.zzaut();	 Catch:{ IllegalArgumentException -> 0x002d }
        r1 = r3.mContext;	 Catch:{ IllegalArgumentException -> 0x002d }
        r2 = r3.zzajb;	 Catch:{ IllegalArgumentException -> 0x002d }
        r0.zza(r1, r2);	 Catch:{ IllegalArgumentException -> 0x002d }
    L_0x001f:
        r0 = 0;
        r3.zzajd = r0;	 Catch:{ all -> 0x002a }
        r0 = 0;
        r3.zzajc = r0;	 Catch:{ all -> 0x002a }
        r0 = 0;
        r3.zzajb = r0;	 Catch:{ all -> 0x002a }
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
        goto L_0x000f;
    L_0x002a:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
        throw r0;
    L_0x002d:
        r0 = move-exception;
        r1 = "AdvertisingIdClient";
        r2 = "AdvertisingIdClient unbindService failed.";
        android.util.Log.i(r1, r2, r0);	 Catch:{ all -> 0x002a }
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.identifier.AdvertisingIdClient.finish():void");
    }

    public Info getInfo() throws IOException {
        Info info;
        zzab.zzhk("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (!this.zzajd) {
                synchronized (this.zzaje) {
                    if (this.zzajf == null || !this.zzajf.zzdk()) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    zze(false);
                    if (!this.zzajd) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                } catch (Throwable e) {
                    Log.i("AdvertisingIdClient", "GMS remote exception ", e);
                    throw new IOException("Remote exception");
                } catch (Throwable e2) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                }
            }
            zzab.zzaa(this.zzajb);
            zzab.zzaa(this.zzajc);
            info = new Info(this.zzajc.getId(), this.zzajc.zzf(true));
        }
        zzdj();
        return info;
    }

    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zze(true);
    }

    protected void zze(boolean z) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        zzab.zzhk("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzajd) {
                finish();
            }
            this.zzajb = zzh(this.mContext);
            this.zzajc = zza(this.mContext, this.zzajb);
            this.zzajd = true;
            if (z) {
                zzdj();
            }
        }
    }
}
