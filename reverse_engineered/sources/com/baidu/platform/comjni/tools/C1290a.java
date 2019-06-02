package com.baidu.platform.comjni.tools;

import android.os.Bundle;
import com.baidu.mapapi.model.inner.C1145a;
import com.baidu.mapapi.model.inner.Point;
import java.util.ArrayList;

/* renamed from: com.baidu.platform.comjni.tools.a */
public class C1290a {
    /* renamed from: a */
    public static double m4963a(Point point, Point point2) {
        Bundle bundle = new Bundle();
        bundle.putDouble("x1", (double) point.f3293x);
        bundle.putDouble("y1", (double) point.f3294y);
        bundle.putDouble("x2", (double) point2.f3293x);
        bundle.putDouble("y2", (double) point2.f3294y);
        JNITools.GetDistanceByMC(bundle);
        return bundle.getDouble("distance");
    }

    /* renamed from: a */
    public static C1145a m4964a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        bundle2.putString("strkey", str);
        JNITools.TransGeoStr2ComplexPt(bundle2);
        C1145a c1145a = new C1145a();
        Bundle bundle3 = bundle2.getBundle("map_bound");
        if (bundle3 != null) {
            bundle = bundle3.getBundle("ll");
            if (bundle != null) {
                c1145a.f3296b = new Point((int) bundle.getDouble("ptx"), (int) bundle.getDouble("pty"));
            }
            bundle3 = bundle3.getBundle("ru");
            if (bundle3 != null) {
                c1145a.f3297c = new Point((int) bundle3.getDouble("ptx"), (int) bundle3.getDouble("pty"));
            }
        }
        ParcelItem[] parcelItemArr = (ParcelItem[]) bundle2.getParcelableArray("poly_line");
        for (ParcelItem bundle4 : parcelItemArr) {
            if (c1145a.f3298d == null) {
                c1145a.f3298d = new ArrayList();
            }
            bundle = bundle4.getBundle();
            if (bundle != null) {
                ParcelItem[] parcelItemArr2 = (ParcelItem[]) bundle.getParcelableArray("point_array");
                ArrayList arrayList = new ArrayList();
                for (ParcelItem bundle5 : parcelItemArr2) {
                    Bundle bundle6 = bundle5.getBundle();
                    if (bundle6 != null) {
                        arrayList.add(new Point((int) bundle6.getDouble("ptx"), (int) bundle6.getDouble("pty")));
                    }
                }
                arrayList.trimToSize();
                c1145a.f3298d.add(arrayList);
            }
        }
        c1145a.f3298d.trimToSize();
        c1145a.f3295a = (int) bundle2.getDouble("type");
        return c1145a;
    }

    /* renamed from: a */
    public static String m4965a() {
        return JNITools.GetToken();
    }

    /* renamed from: a */
    public static void m4966a(boolean z, int i) {
        JNITools.openLogEnable(z, i);
    }

    /* renamed from: b */
    public static void m4967b() {
        JNITools.initClass(new Bundle(), 0);
    }
}
