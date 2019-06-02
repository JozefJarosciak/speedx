package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.FacebookException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: AttributionIdentifiers */
/* renamed from: com.facebook.internal.b */
public class C3000b {
    /* renamed from: a */
    private static final String f13536a = C3000b.class.getCanonicalName();
    /* renamed from: g */
    private static C3000b f13537g;
    /* renamed from: b */
    private String f13538b;
    /* renamed from: c */
    private String f13539c;
    /* renamed from: d */
    private String f13540d;
    /* renamed from: e */
    private boolean f13541e;
    /* renamed from: f */
    private long f13542f;

    /* compiled from: AttributionIdentifiers */
    /* renamed from: com.facebook.internal.b$a */
    private static final class C2998a implements IInterface {
        /* renamed from: a */
        private IBinder f13533a;

        C2998a(IBinder iBinder) {
            this.f13533a = iBinder;
        }

        public IBinder asBinder() {
            return this.f13533a;
        }

        /* renamed from: a */
        public String m14538a() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f13533a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        /* renamed from: b */
        public boolean m14539b() throws RemoteException {
            boolean z = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.f13533a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    /* compiled from: AttributionIdentifiers */
    /* renamed from: com.facebook.internal.b$b */
    private static final class C2999b implements ServiceConnection {
        /* renamed from: a */
        private AtomicBoolean f13534a;
        /* renamed from: b */
        private final BlockingQueue<IBinder> f13535b;

        private C2999b() {
            this.f13534a = new AtomicBoolean(false);
            this.f13535b = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f13535b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        /* renamed from: a */
        public IBinder m14540a() throws InterruptedException {
            if (!this.f13534a.compareAndSet(true, true)) {
                return (IBinder) this.f13535b.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    /* renamed from: b */
    private static C3000b m14543b(Context context) {
        C3000b c = C3000b.m14544c(context);
        if (c != null) {
            return c;
        }
        c = C3000b.m14545d(context);
        if (c == null) {
            return new C3000b();
        }
        return c;
    }

    /* renamed from: c */
    private static C3000b m14544c(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
            Method a = C3048s.m14739a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
            if (a == null) {
                return null;
            }
            Object a2 = C3048s.m14729a(null, a, context);
            if (!(a2 instanceof Integer) || ((Integer) a2).intValue() != 0) {
                return null;
            }
            a = C3048s.m14739a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
            if (a == null) {
                return null;
            }
            Object a3 = C3048s.m14729a(null, a, context);
            if (a3 == null) {
                return null;
            }
            a = C3048s.m14738a(a3.getClass(), "getId", new Class[0]);
            Method a4 = C3048s.m14738a(a3.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
            if (a == null || a4 == null) {
                return null;
            }
            C3000b c3000b = new C3000b();
            c3000b.f13539c = (String) C3048s.m14729a(a3, a, new Object[0]);
            c3000b.f13541e = ((Boolean) C3048s.m14729a(a3, a4, new Object[0])).booleanValue();
            return c3000b;
        } catch (Exception e) {
            C3048s.m14753a("android_id", e);
            return null;
        }
    }

    /* renamed from: d */
    private static C3000b m14545d(Context context) {
        ServiceConnection c2999b = new C2999b();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, c2999b, 1)) {
            C3000b c3000b;
            try {
                C2998a c2998a = new C2998a(c2999b.m14540a());
                c3000b = new C3000b();
                c3000b.f13539c = c2998a.m14538a();
                c3000b.f13541e = c2998a.m14539b();
                return c3000b;
            } catch (Exception e) {
                c3000b = e;
                C3048s.m14753a("android_id", (Exception) c3000b);
            } finally {
                context.unbindService(c2999b);
            }
        }
        return null;
    }

    /* renamed from: a */
    public static C3000b m14541a(Context context) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        if (f13537g != null && System.currentTimeMillis() - f13537g.f13542f < 3600000) {
            return f13537g;
        }
        C3000b b = C3000b.m14543b(context);
        Cursor query;
        try {
            Uri parse;
            String[] strArr = new String[]{"aid", "androidid", "limit_tracking"};
            if (context.getPackageManager().resolveContentProvider("com.facebook.katana.provider.AttributionIdProvider", 0) != null) {
                parse = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
            } else if (context.getPackageManager().resolveContentProvider("com.facebook.wakizashi.provider.AttributionIdProvider", 0) != null) {
                parse = Uri.parse("content://com.facebook.wakizashi.provider.AttributionIdProvider");
            } else {
                Object obj = cursor;
            }
            String e2 = C3000b.m14546e(context);
            if (e2 != null) {
                b.f13540d = e2;
            }
            C3000b a;
            if (parse == null) {
                a = C3000b.m14542a(b);
                if (cursor == null) {
                    return a;
                }
                cursor.close();
                return a;
            }
            query = context.getContentResolver().query(parse, strArr, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex("aid");
                        int columnIndex2 = query.getColumnIndex("androidid");
                        int columnIndex3 = query.getColumnIndex("limit_tracking");
                        b.f13538b = query.getString(columnIndex);
                        if (columnIndex2 > 0 && columnIndex3 > 0 && b.m14548b() == null) {
                            b.f13539c = query.getString(columnIndex2);
                            b.f13541e = Boolean.parseBoolean(query.getString(columnIndex3));
                        }
                        if (query != null) {
                            query.close();
                        }
                        return C3000b.m14542a(b);
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        Log.d(f13536a, "Caught unexpected exception in getAttributionId(): " + e.toString());
                        if (query != null) {
                            query.close();
                        }
                        return cursor;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
            a = C3000b.m14542a(b);
            if (query == null) {
                return a;
            }
            query.close();
            return a;
        } catch (Exception e4) {
            e = e4;
            query = cursor;
            Log.d(f13536a, "Caught unexpected exception in getAttributionId(): " + e.toString());
            if (query != null) {
                query.close();
            }
            return cursor;
        } catch (Throwable th3) {
            th = th3;
            query = cursor;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private static C3000b m14542a(C3000b c3000b) {
        c3000b.f13542f = System.currentTimeMillis();
        f13537g = c3000b;
        return c3000b;
    }

    /* renamed from: a */
    public String m14547a() {
        return this.f13538b;
    }

    /* renamed from: b */
    public String m14548b() {
        return this.f13539c;
    }

    /* renamed from: c */
    public String m14549c() {
        return this.f13540d;
    }

    /* renamed from: d */
    public boolean m14550d() {
        return this.f13541e;
    }

    @Nullable
    /* renamed from: e */
    private static String m14546e(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }
}
