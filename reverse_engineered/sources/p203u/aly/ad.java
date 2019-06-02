package p203u.aly;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: AdvertisingId */
/* renamed from: u.aly.ad */
public class ad {

    /* compiled from: AdvertisingId */
    /* renamed from: u.aly.ad$a */
    private static final class C5848a {
        /* renamed from: a */
        private final String f18573a;
        /* renamed from: b */
        private final boolean f18574b;

        C5848a(String str, boolean z) {
            this.f18573a = str;
            this.f18574b = z;
        }

        /* renamed from: a */
        private String m21100a() {
            return this.f18573a;
        }
    }

    /* compiled from: AdvertisingId */
    /* renamed from: u.aly.ad$b */
    private static final class C5849b implements ServiceConnection {
        /* renamed from: a */
        boolean f18575a;
        /* renamed from: b */
        private final LinkedBlockingQueue<IBinder> f18576b;

        private C5849b() {
            this.f18575a = false;
            this.f18576b = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f18576b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        /* renamed from: a */
        public IBinder m21102a() throws InterruptedException {
            if (this.f18575a) {
                throw new IllegalStateException();
            }
            this.f18575a = true;
            return (IBinder) this.f18576b.take();
        }
    }

    /* compiled from: AdvertisingId */
    /* renamed from: u.aly.ad$c */
    private static final class C5850c implements IInterface {
        /* renamed from: a */
        private IBinder f18577a;

        public C5850c(IBinder iBinder) {
            this.f18577a = iBinder;
        }

        public IBinder asBinder() {
            return this.f18577a;
        }

        /* renamed from: a */
        public String m21103a() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f18577a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        /* renamed from: a */
        public boolean m21104a(boolean z) throws RemoteException {
            boolean z2 = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(z ? 1 : 0);
                this.f18577a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z2 = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z2;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    /* renamed from: a */
    public static String m21105a(Context context) {
        String str = null;
        try {
            C5848a b = ad.m21106b(context);
            if (b != null) {
                str = b.m21100a();
            }
        } catch (Exception e) {
        }
        return str;
    }

    /* renamed from: b */
    private static C5848a m21106b(Context context) throws Exception {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            ServiceConnection c5849b = new C5849b();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            if (context.bindService(intent, c5849b, 1)) {
                try {
                    C5850c c5850c = new C5850c(c5849b.m21102a());
                    C5848a c5848a = new C5848a(c5850c.m21103a(), c5850c.m21104a(true));
                    context.unbindService(c5849b);
                    return c5848a;
                } catch (Exception e) {
                    throw e;
                } catch (Throwable th) {
                    context.unbindService(c5849b);
                }
            } else {
                throw new IOException("Google Play connection failed");
            }
        } catch (Exception e2) {
            throw e2;
        }
    }
}
