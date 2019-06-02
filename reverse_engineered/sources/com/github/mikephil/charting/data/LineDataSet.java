package com.github.mikephil.charting.data;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;
import com.avos.avoscloud.AVException;
import com.github.mikephil.charting.p089e.p090b.C1477f;
import com.github.mikephil.charting.p121c.C3190e;
import com.github.mikephil.charting.p121c.C3191b;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.ArrayList;
import java.util.List;

public class LineDataSet extends C3240m<Entry> implements C1477f {
    /* renamed from: B */
    private float f6996B = 0.2f;
    /* renamed from: C */
    private DashPathEffect f6997C = null;
    /* renamed from: D */
    private C3190e f6998D = new C3191b();
    /* renamed from: E */
    private boolean f6999E = true;
    /* renamed from: F */
    private boolean f7000F = true;
    /* renamed from: n */
    private LineDataSet$Mode f7001n = LineDataSet$Mode.LINEAR;
    /* renamed from: o */
    private List<Integer> f7002o = null;
    /* renamed from: p */
    private int f7003p = -1;
    /* renamed from: q */
    private float f7004q = 8.0f;
    /* renamed from: r */
    private float f7005r = 4.0f;

    public LineDataSet(List<Entry> list, String str) {
        super(list, str);
        if (this.f7002o == null) {
            this.f7002o = new ArrayList();
        }
        this.f7002o.clear();
        this.f7002o.add(Integer.valueOf(Color.rgb(AVException.EXCEEDED_QUOTA, 234, 255)));
    }

    /* renamed from: a */
    public LineDataSet$Mode mo2823a() {
        return this.f7001n;
    }

    /* renamed from: a */
    public void m8239a(LineDataSet$Mode lineDataSet$Mode) {
        this.f7001n = lineDataSet$Mode;
    }

    /* renamed from: b */
    public float mo2824b() {
        return this.f6996B;
    }

    /* renamed from: b */
    public void m8241b(float f) {
        if (f >= 1.0f) {
            this.f7004q = C3283i.a(f);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 1");
        }
    }

    /* renamed from: c */
    public float mo2825c() {
        return this.f7004q;
    }

    /* renamed from: c */
    public void m8243c(float f) {
        if (f >= 0.5f) {
            this.f7005r = C3283i.a(f);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 0.5");
        }
    }

    /* renamed from: d */
    public float mo2826d() {
        return this.f7005r;
    }

    /* renamed from: e */
    public boolean mo2827e() {
        return this.f6997C != null;
    }

    /* renamed from: f */
    public DashPathEffect mo2829f() {
        return this.f6997C;
    }

    /* renamed from: c */
    public void m8244c(boolean z) {
        this.f6999E = z;
    }

    /* renamed from: g */
    public boolean mo2830g() {
        return this.f6999E;
    }

    @Deprecated
    /* renamed from: B */
    public boolean mo2818B() {
        return this.f7001n == LineDataSet$Mode.STEPPED;
    }

    /* renamed from: f */
    public int mo2828f(int i) {
        return ((Integer) this.f7002o.get(i)).intValue();
    }

    /* renamed from: C */
    public int mo2819C() {
        return this.f7002o.size();
    }

    /* renamed from: h */
    public void m8251h(int i) {
        m8234D();
        this.f7002o.add(Integer.valueOf(i));
    }

    /* renamed from: D */
    public void m8234D() {
        if (this.f7002o == null) {
            this.f7002o = new ArrayList();
        }
        this.f7002o.clear();
    }

    /* renamed from: i */
    public void m8252i(int i) {
        this.f7003p = i;
    }

    /* renamed from: L */
    public int mo2820L() {
        return this.f7003p;
    }

    /* renamed from: d */
    public void m8246d(boolean z) {
        this.f7000F = z;
    }

    /* renamed from: M */
    public boolean mo2821M() {
        return this.f7000F;
    }

    /* renamed from: N */
    public C3190e mo2822N() {
        return this.f6998D;
    }
}
