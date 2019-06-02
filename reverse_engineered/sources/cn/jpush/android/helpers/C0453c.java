package cn.jpush.android.helpers;

import android.content.Context;
import android.os.RemoteException;
import cn.jpush.android.C0448e;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ak;

/* renamed from: cn.jpush.android.helpers.c */
public final class C0453c {
    /* renamed from: a */
    public static void m1372a(Context context, String str, int i) {
        try {
            C0448e.f763o.mo2214b(str, i);
        } catch (RemoteException e) {
            ac.m1586d();
        } catch (NullPointerException e2) {
            ac.m1576a();
            ak.m1640a(context, str, i);
        }
    }

    /* renamed from: a */
    public static void m1373a(Context context, String str, long j) {
        try {
            C0448e.f763o.mo2215b(str, j);
        } catch (RemoteException e) {
            ac.m1586d();
        } catch (NullPointerException e2) {
            ac.m1576a();
            ak.m1641a(context, str, j);
        }
    }

    /* renamed from: a */
    public static void m1374a(Context context, String str, String str2) {
        try {
            C0448e.f763o.mo2216b(str, str2);
        } catch (RemoteException e) {
            ac.m1586d();
        } catch (NullPointerException e2) {
            ac.m1576a();
            ak.m1642a(context, str, str2);
        }
    }

    /* renamed from: a */
    public static void m1375a(Context context, String str, boolean z) {
        try {
            C0448e.f763o.mo2217b(str, z);
        } catch (RemoteException e) {
            ac.m1586d();
        } catch (NullPointerException e2) {
            ac.m1576a();
            ak.m1643a(context, str, z);
        }
    }

    /* renamed from: b */
    public static int m1376b(Context context, String str, int i) {
        try {
            return C0448e.f763o.mo2207a(str, i);
        } catch (RemoteException e) {
            ac.m1586d();
            return i;
        } catch (NullPointerException e2) {
            ac.m1576a();
            return ak.m1645b(context, str, i);
        }
    }

    /* renamed from: b */
    public static long m1377b(Context context, String str, long j) {
        try {
            return C0448e.f763o.mo2208a(str, j);
        } catch (RemoteException e) {
            ac.m1586d();
            return j;
        } catch (NullPointerException e2) {
            ac.m1576a();
            return ak.m1646b(context, str, j);
        }
    }

    /* renamed from: b */
    public static String m1378b(Context context, String str, String str2) {
        try {
            return C0448e.f763o.mo2209a(str, str2);
        } catch (RemoteException e) {
            ac.m1586d();
            return str2;
        } catch (NullPointerException e2) {
            ac.m1576a();
            return ak.m1647b(context, str, str2);
        }
    }

    /* renamed from: b */
    public static boolean m1379b(Context context, String str, boolean z) {
        try {
            return C0448e.f763o.mo2212a(str, z);
        } catch (RemoteException e) {
            ac.m1586d();
            return z;
        } catch (NullPointerException e2) {
            ac.m1576a();
            return ak.m1649b(context, str, z);
        }
    }
}
