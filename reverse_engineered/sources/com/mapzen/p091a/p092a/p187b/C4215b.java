package com.mapzen.p091a.p092a.p187b;

import android.content.Context;
import android.location.Location;
import com.mapzen.p091a.p092a.p093a.C1486c;
import com.mapzen.p091a.p092a.p093a.C4207a;
import com.mapzen.p091a.p092a.p093a.C4209d;
import com.mapzen.p091a.p092a.p187b.C4216e.C4214a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: FusedLocationProviderApiImpl */
/* renamed from: com.mapzen.a.a.b.b */
public class C4215b implements C4207a, C4214a {
    /* renamed from: a */
    private final Context f14849a;
    /* renamed from: b */
    private HashMap<C4209d, C4216e> f14850b = new HashMap();
    /* renamed from: c */
    private HashMap<C4216e, List<C1486c>> f14851c = new HashMap();
    /* renamed from: d */
    private C4216e f14852d;
    /* renamed from: e */
    private boolean f14853e;
    /* renamed from: f */
    private File f14854f;
    /* renamed from: g */
    private Location f14855g;

    public C4215b(Context context) {
        this.f14849a = context;
        this.f14852d = new C4217c(context, null);
    }

    /* renamed from: a */
    public Location mo5984a() {
        return this.f14852d.mo5988a();
    }

    /* renamed from: a */
    public void mo5986a(C4209d c4209d, C1486c c1486c) {
        C4216e c4216e = (C4216e) this.f14850b.get(c4209d);
        C4216e a = m16723a(c4209d);
        m16725a(a, c1486c);
        if (c4216e == null) {
            a.m16734a(c4209d);
        }
    }

    /* renamed from: a */
    public void mo5985a(C1486c c1486c) {
        C4216e b = m16726b(c1486c);
        if (b != null) {
            m16724a(b);
        }
    }

    /* renamed from: a */
    public void mo5987a(C4216e c4216e, Location location) {
        List<C1486c> list = (List) this.f14851c.get(c4216e);
        if (list != null) {
            for (C1486c onLocationChanged : list) {
                onLocationChanged.onLocationChanged(location);
            }
        }
    }

    /* renamed from: b */
    public void m16732b() {
        m16727c();
    }

    /* renamed from: a */
    private C4216e m16723a(C4209d c4209d) {
        C4216e c4216e = (C4216e) this.f14850b.get(c4209d);
        if (c4216e != null) {
            return c4216e;
        }
        C4216e c4222g;
        if (this.f14853e) {
            c4222g = new C4222g(this.f14849a, this);
            C4222g c4222g2 = (C4222g) c4222g;
            c4222g2.m16759a(this.f14854f);
            if (this.f14855g != null) {
                c4222g2.m16758a(this.f14855g);
            }
        } else {
            c4222g = new C4217c(this.f14849a, this);
        }
        this.f14850b.put(c4209d, c4222g);
        return c4222g;
    }

    /* renamed from: a */
    private void m16724a(C4216e c4216e) {
        if (this.f14851c.get(c4216e) == null) {
            c4216e.m16734a(null);
        }
    }

    /* renamed from: a */
    private void m16725a(C4216e c4216e, C1486c c1486c) {
        List list = (List) this.f14851c.get(c4216e);
        if (list == null) {
            list = new ArrayList();
            this.f14851c.put(c4216e, list);
        }
        list.add(c1486c);
    }

    /* renamed from: b */
    private C4216e m16726b(C1486c c1486c) {
        for (C4216e c4216e : this.f14851c.keySet()) {
            List list = (List) this.f14851c.get(c4216e);
            if (list.contains(c1486c)) {
                list.remove(c1486c);
                if (!list.isEmpty()) {
                    return c4216e;
                }
                this.f14851c.remove(c4216e);
                return c4216e;
            }
        }
        return null;
    }

    /* renamed from: c */
    private void m16727c() {
        for (C4216e c4216e : this.f14850b.values()) {
            this.f14851c.remove(c4216e);
            c4216e.m16734a(null);
        }
        this.f14850b.clear();
    }
}
