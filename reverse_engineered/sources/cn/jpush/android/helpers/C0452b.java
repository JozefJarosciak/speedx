package cn.jpush.android.helpers;

import android.os.RemoteException;
import cn.jpush.android.C0448e;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.helpers.b */
public final class C0452b {
    /* renamed from: a */
    public static int m1368a() {
        try {
            return C0448e.f763o.mo2206a();
        } catch (RemoteException e) {
            ac.m1586d();
            e.printStackTrace();
            return 0;
        } catch (NullPointerException e2) {
            ac.m1576a();
            return C0455e.m1393a();
        }
    }

    /* renamed from: a */
    public static boolean m1369a(int i) {
        try {
            return C0448e.f763o.mo2211a(i);
        } catch (RemoteException e) {
            ac.m1586d();
            e.printStackTrace();
            return false;
        } catch (NullPointerException e2) {
            ac.m1576a();
            return C0455e.m1394a(i);
        }
    }

    /* renamed from: b */
    public static int m1370b() {
        try {
            return C0448e.f763o.mo2213b();
        } catch (RemoteException e) {
            ac.m1586d();
            e.printStackTrace();
            return 0;
        } catch (NullPointerException e2) {
            ac.m1576a();
            return C0455e.m1395b();
        }
    }

    /* renamed from: b */
    public static boolean m1371b(int i) {
        try {
            return C0448e.f763o.mo2218b(i);
        } catch (RemoteException e) {
            ac.m1586d();
            e.printStackTrace();
            return false;
        } catch (NullPointerException e2) {
            ac.m1576a();
            return C0455e.m1396b(i);
        }
    }
}
