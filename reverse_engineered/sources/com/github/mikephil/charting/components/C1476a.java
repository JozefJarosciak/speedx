package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.util.Log;
import com.github.mikephil.charting.p121c.C1968d;
import com.github.mikephil.charting.p121c.C3189a;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AxisBase */
/* renamed from: com.github.mikephil.charting.components.a */
public abstract class C1476a extends C3206b {
    /* renamed from: B */
    private int f6968B;
    /* renamed from: C */
    private float f6969C;
    /* renamed from: D */
    private int f6970D;
    /* renamed from: E */
    private float f6971E;
    /* renamed from: F */
    private int f6972F;
    /* renamed from: G */
    private DashPathEffect f6973G;
    /* renamed from: H */
    private DashPathEffect f6974H;
    /* renamed from: a */
    protected C1968d f6975a;
    /* renamed from: b */
    public float[] f6976b;
    /* renamed from: c */
    public float[] f6977c;
    /* renamed from: d */
    public int f6978d;
    /* renamed from: e */
    public int f6979e;
    /* renamed from: f */
    protected float f6980f;
    /* renamed from: g */
    protected boolean f6981g;
    /* renamed from: h */
    protected boolean f6982h;
    /* renamed from: i */
    protected boolean f6983i;
    /* renamed from: j */
    protected boolean f6984j;
    /* renamed from: k */
    protected boolean f6985k;
    /* renamed from: l */
    protected boolean f6986l;
    /* renamed from: m */
    protected List<LimitLine> f6987m;
    /* renamed from: n */
    protected boolean f6988n;
    /* renamed from: o */
    protected float f6989o;
    /* renamed from: p */
    protected float f6990p;
    /* renamed from: q */
    protected boolean f6991q;
    /* renamed from: r */
    protected boolean f6992r;
    /* renamed from: s */
    public float f6993s;
    /* renamed from: t */
    public float f6994t;
    /* renamed from: u */
    public float f6995u;

    public C1476a() {
        this.f6968B = -7829368;
        this.f6969C = 1.0f;
        this.f6970D = -7829368;
        this.f6971E = 1.0f;
        this.f6976b = new float[0];
        this.f6977c = new float[0];
        this.f6972F = 6;
        this.f6980f = 1.0f;
        this.f6981g = false;
        this.f6982h = false;
        this.f6983i = true;
        this.f6984j = true;
        this.f6985k = true;
        this.f6986l = false;
        this.f6973G = null;
        this.f6974H = null;
        this.f6988n = false;
        this.f6989o = 0.0f;
        this.f6990p = 0.0f;
        this.f6991q = false;
        this.f6992r = false;
        this.f6993s = 0.0f;
        this.f6994t = 0.0f;
        this.f6995u = 0.0f;
        this.z = C3283i.a(10.0f);
        this.w = C3283i.a(5.0f);
        this.x = C3283i.a(5.0f);
        this.f6987m = new ArrayList();
    }

    /* renamed from: a */
    public void m8183a(boolean z) {
        this.f6983i = z;
    }

    /* renamed from: a */
    public boolean m8184a() {
        return this.f6983i;
    }

    /* renamed from: b */
    public void m8187b(boolean z) {
        this.f6984j = z;
    }

    /* renamed from: b */
    public boolean m8188b() {
        return this.f6984j;
    }

    /* renamed from: c */
    public boolean m8192c() {
        return this.f6986l && this.f6978d > 0;
    }

    /* renamed from: a */
    public void m8179a(int i) {
        this.f6968B = i;
    }

    /* renamed from: d */
    public int m8193d() {
        return this.f6968B;
    }

    /* renamed from: a */
    public void m8176a(float f) {
        this.f6971E = C3283i.a(f);
    }

    /* renamed from: e */
    public float m8197e() {
        return this.f6971E;
    }

    /* renamed from: b */
    public void m8185b(float f) {
        this.f6969C = C3283i.a(f);
    }

    /* renamed from: f */
    public float m8199f() {
        return this.f6969C;
    }

    /* renamed from: b */
    public void m8186b(int i) {
        this.f6970D = i;
    }

    /* renamed from: g */
    public int m8201g() {
        return this.f6970D;
    }

    /* renamed from: c */
    public void m8191c(boolean z) {
        this.f6985k = z;
    }

    /* renamed from: h */
    public boolean m8204h() {
        return this.f6985k;
    }

    /* renamed from: c */
    public void m8190c(int i) {
        int i2 = 25;
        int i3 = 2;
        if (i <= 25) {
            i2 = i;
        }
        if (i2 >= 2) {
            i3 = i2;
        }
        this.f6972F = i3;
        this.f6982h = false;
    }

    /* renamed from: a */
    public void m8180a(int i, boolean z) {
        m8190c(i);
        this.f6982h = z;
    }

    /* renamed from: i */
    public boolean m8206i() {
        return this.f6982h;
    }

    /* renamed from: j */
    public int m8207j() {
        return this.f6972F;
    }

    /* renamed from: k */
    public boolean m8208k() {
        return this.f6981g;
    }

    /* renamed from: l */
    public float m8209l() {
        return this.f6980f;
    }

    /* renamed from: c */
    public void m8189c(float f) {
        this.f6980f = f;
        this.f6981g = true;
    }

    /* renamed from: a */
    public void m8182a(LimitLine limitLine) {
        this.f6987m.add(limitLine);
        if (this.f6987m.size() > 6) {
            Log.e("MPAndroiChart", "Warning! You have more than 6 LimitLines on your axis, do you really want that?");
        }
    }

    /* renamed from: m */
    public void m8210m() {
        this.f6987m.clear();
    }

    /* renamed from: n */
    public List<LimitLine> m8211n() {
        return this.f6987m;
    }

    /* renamed from: d */
    public void m8196d(boolean z) {
        this.f6988n = z;
    }

    /* renamed from: o */
    public boolean m8212o() {
        return this.f6988n;
    }

    /* renamed from: p */
    public String m8213p() {
        String str = "";
        int i = 0;
        while (i < this.f6976b.length) {
            String d = m8194d(i);
            if (d == null || str.length() >= d.length()) {
                d = str;
            }
            i++;
            str = d;
        }
        return str;
    }

    /* renamed from: d */
    public String m8194d(int i) {
        if (i < 0 || i >= this.f6976b.length) {
            return "";
        }
        return m8214q().a(this.f6976b[i], this);
    }

    /* renamed from: a */
    public void m8181a(C1968d c1968d) {
        if (c1968d == null) {
            this.f6975a = new C3189a(this.f6979e);
        } else {
            this.f6975a = c1968d;
        }
    }

    /* renamed from: q */
    public C1968d m8214q() {
        if (this.f6975a == null || ((this.f6975a instanceof C3189a) && ((C3189a) this.f6975a).a() != this.f6979e)) {
            this.f6975a = new C3189a(this.f6979e);
        }
        return this.f6975a;
    }

    /* renamed from: a */
    public void m8178a(float f, float f2, float f3) {
        this.f6974H = new DashPathEffect(new float[]{f, f2}, f3);
    }

    /* renamed from: r */
    public DashPathEffect m8215r() {
        return this.f6974H;
    }

    /* renamed from: s */
    public void m8216s() {
        this.f6973G = null;
    }

    /* renamed from: t */
    public DashPathEffect m8217t() {
        return this.f6973G;
    }

    /* renamed from: u */
    public float m8218u() {
        return this.f6993s;
    }

    /* renamed from: d */
    public void m8195d(float f) {
        this.f6991q = true;
        this.f6994t = f;
        this.f6995u = Math.abs(this.f6993s - f);
    }

    @Deprecated
    /* renamed from: e */
    public void m8198e(float f) {
        m8195d(f);
    }

    /* renamed from: f */
    public void m8200f(float f) {
        this.f6992r = true;
        this.f6993s = f;
        this.f6995u = Math.abs(f - this.f6994t);
    }

    @Deprecated
    /* renamed from: g */
    public void m8202g(float f) {
        m8200f(f);
    }

    /* renamed from: a */
    public void m8177a(float f, float f2) {
        float f3 = this.f6991q ? this.f6994t : f - this.f6989o;
        float f4 = this.f6992r ? this.f6993s : this.f6990p + f2;
        if (Math.abs(f4 - f3) == 0.0f) {
            f4 += 1.0f;
            f3 -= 1.0f;
        }
        this.f6994t = f3;
        this.f6993s = f4;
        this.f6995u = Math.abs(f4 - f3);
    }

    /* renamed from: h */
    public void m8203h(float f) {
        this.f6989o = f;
    }

    /* renamed from: i */
    public void m8205i(float f) {
        this.f6990p = f;
    }
}
