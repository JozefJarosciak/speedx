package io.fabric.sdk.android.services.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import io.fabric.sdk.android.C1520c;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: AdvertisingInfoServiceStrategy */
/* renamed from: io.fabric.sdk.android.services.common.e */
class C4874e implements C4869f {
    /* renamed from: a */
    private final Context f17158a;

    /* compiled from: AdvertisingInfoServiceStrategy */
    /* renamed from: io.fabric.sdk.android.services.common.e$a */
    private static final class C4872a implements ServiceConnection {
        /* renamed from: a */
        private boolean f17155a;
        /* renamed from: b */
        private final LinkedBlockingQueue<IBinder> f17156b;

        private C4872a() {
            this.f17155a = false;
            this.f17156b = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f17156b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.f17156b.clear();
        }

        /* renamed from: a */
        public IBinder m19134a() {
            if (this.f17155a) {
                C1520c.h().mo6221d("Fabric", "getBinder already called");
            }
            this.f17155a = true;
            try {
                return (IBinder) this.f17156b.poll(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                return null;
            }
        }
    }

    /* compiled from: AdvertisingInfoServiceStrategy */
    /* renamed from: io.fabric.sdk.android.services.common.e$b */
    private static final class C4873b implements IInterface {
        /* renamed from: a */
        private final IBinder f17157a;

        /* renamed from: b */
        public boolean m19136b() throws android.os.RemoteException {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
            /*
            r7 = this;
            r0 = 1;
            r1 = 0;
            r2 = android.os.Parcel.obtain();
            r3 = android.os.Parcel.obtain();
            r4 = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r2.writeInterfaceToken(r4);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = 1;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r2.writeInt(r4);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = r7.f17157a;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r5 = 2;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r6 = 0;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4.transact(r5, r2, r3, r6);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r3.readException();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = r3.readInt();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            if (r4 == 0) goto L_0x002a;
        L_0x0023:
            r3.recycle();
            r2.recycle();
        L_0x0029:
            return r0;
        L_0x002a:
            r0 = r1;
            goto L_0x0023;
        L_0x002c:
            r0 = move-exception;
            r0 = io.fabric.sdk.android.C1520c.h();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = "Fabric";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r5 = "Could not get parcel from Google Play Service to capture Advertising limitAdTracking";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r0.mo6215a(r4, r5);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r3.recycle();
            r2.recycle();
            r0 = r1;
            goto L_0x0029;
        L_0x0040:
            r0 = move-exception;
            r3.recycle();
            r2.recycle();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.common.e.b.b():boolean");
        }

        public C4873b(IBinder iBinder) {
            this.f17157a = iBinder;
        }

        public IBinder asBinder() {
            return this.f17157a;
        }

        /* renamed from: a */
        public String m19135a() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            String str = null;
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f17157a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                str = obtain2.readString();
            } catch (Exception e) {
                C1520c.h().mo6215a("Fabric", "Could not get parcel from Google Play Service to capture AdvertisingId");
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return str;
        }
    }

    public C4874e(Context context) {
        this.f17158a = context.getApplicationContext();
    }

    /* renamed from: a */
    public C4865b mo6249a() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            C1520c.h().mo6215a("Fabric", "AdvertisingInfoServiceStrategy cannot be called on the main thread");
            return null;
        }
        try {
            this.f17158a.getPackageManager().getPackageInfo("com.android.vending", 0);
            ServiceConnection c4872a = new C4872a();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                if (this.f17158a.bindService(intent, c4872a, 1)) {
                    C4873b c4873b = new C4873b(c4872a.m19134a());
                    C4865b c4865b = new C4865b(c4873b.m19135a(), c4873b.m19136b());
                    this.f17158a.unbindService(c4872a);
                    return c4865b;
                }
                C1520c.h().mo6215a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId");
                return null;
            } catch (Throwable e) {
                C1520c.h().mo6220c("Fabric", "Exception in binding to Google Play Service to capture AdvertisingId", e);
                this.f17158a.unbindService(c4872a);
                return null;
            } catch (Throwable e2) {
                C1520c.h().mo6216a("Fabric", "Could not bind to Google Play Service to capture AdvertisingId", e2);
                return null;
            }
        } catch (NameNotFoundException e3) {
            C1520c.h().mo6215a("Fabric", "Unable to find Google Play Services package name");
            return null;
        } catch (Throwable e22) {
            C1520c.h().mo6216a("Fabric", "Unable to determine if Google Play Services is available", e22);
            return null;
        }
    }
}
