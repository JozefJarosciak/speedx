package com.beastbikes.android.modules.cycling.p111a;

import com.baidu.mapapi.model.LatLng;
import com.beastbikes.android.modules.user.dto.C2411a;
import com.beastbikes.framework.business.BusinessException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SimplifyUtil */
/* renamed from: com.beastbikes.android.modules.cycling.a.g */
public class C1892g {
    /* renamed from: a */
    private static final float[] f8455a = new float[]{0.003f, 0.001f, 9.0E-4f, 6.0E-4f, 3.0E-4f, 1.0E-4f, 9.0E-5f, 6.0E-5f, 3.0E-5f, 1.0E-5f};

    /* renamed from: a */
    public static List<LatLng> m9770a(int i, boolean z, List<LatLng> list) throws BusinessException {
        int i2;
        int i3 = 9;
        if (i < 0) {
            i2 = 0;
        } else {
            i2 = i;
        }
        if (i2 <= 9) {
            i3 = i2;
        }
        return C1892g.m9772a((C1886b[]) new C1891f(new C1887c[0]).mo3269a(C1892g.m9775b((List) list), (double) f8455a[i3], z));
    }

    /* renamed from: a */
    public static List<C2411a> m9769a(float f, boolean z, List<C2411a> list) {
        return C1892g.m9776c((C1886b[]) new C1891f(new C2411a[0]).mo3269a(C1892g.m9778d(list), (double) f, z));
    }

    /* renamed from: a */
    public static List<LatLng> m9771a(List<LatLng> list) throws BusinessException {
        return C1892g.m9772a((C1886b[]) new C1891f(new C1887c[0]).mo3269a(C1892g.m9775b((List) list), (double) f8455a[5], true));
    }

    /* renamed from: a */
    public static List<LatLng> m9768a(float f, List<LatLng> list) throws BusinessException {
        return C1892g.m9772a((C1886b[]) new C1891f(new C1887c[0]).mo3269a(C1892g.m9775b((List) list), (double) f, true));
    }

    /* renamed from: b */
    public static List<com.google.android.gms.maps.model.LatLng> m9773b(float f, List<com.google.android.gms.maps.model.LatLng> list) throws BusinessException {
        return C1892g.m9774b((C1886b[]) new C1891f(new C1888d[0]).mo3269a(C1892g.m9777c((List) list), (double) f, true));
    }

    /* renamed from: b */
    private static C1886b[] m9775b(List<LatLng> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        List arrayList = new ArrayList();
        for (LatLng c1887c : list) {
            arrayList.add(new C1887c(c1887c));
        }
        return (C1886b[]) arrayList.toArray(new C1887c[arrayList.size()]);
    }

    /* renamed from: c */
    private static C1886b[] m9777c(List<com.google.android.gms.maps.model.LatLng> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        List arrayList = new ArrayList();
        for (com.google.android.gms.maps.model.LatLng c1888d : list) {
            arrayList.add(new C1888d(c1888d));
        }
        return (C1886b[]) arrayList.toArray(new C1888d[arrayList.size()]);
    }

    /* renamed from: d */
    private static C1886b[] m9778d(List<C2411a> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        List arrayList = new ArrayList();
        for (C2411a add : list) {
            arrayList.add(add);
        }
        return (C1886b[]) arrayList.toArray(new C2411a[arrayList.size()]);
    }

    /* renamed from: a */
    private static List<LatLng> m9772a(C1886b[] c1886bArr) {
        if (c1886bArr == null || c1886bArr.length < 1) {
            return null;
        }
        List<LatLng> arrayList = new ArrayList();
        for (C1886b c1886b : c1886bArr) {
            C1887c c1887c = (C1887c) c1886b;
            arrayList.add(new LatLng(c1887c.mo3264b(), c1887c.mo3263a()));
        }
        return arrayList;
    }

    /* renamed from: b */
    private static List<com.google.android.gms.maps.model.LatLng> m9774b(C1886b[] c1886bArr) {
        if (c1886bArr == null || c1886bArr.length < 1) {
            return null;
        }
        List<com.google.android.gms.maps.model.LatLng> arrayList = new ArrayList();
        for (C1886b c1886b : c1886bArr) {
            C1888d c1888d = (C1888d) c1886b;
            arrayList.add(new com.google.android.gms.maps.model.LatLng(c1888d.mo3264b(), c1888d.mo3263a()));
        }
        return arrayList;
    }

    /* renamed from: c */
    private static List<C2411a> m9776c(C1886b[] c1886bArr) {
        if (c1886bArr == null || c1886bArr.length < 1) {
            return null;
        }
        List<C2411a> arrayList = new ArrayList();
        for (C1886b c1886b : c1886bArr) {
            arrayList.add((C2411a) c1886b);
        }
        return arrayList;
    }
}
