package com.github.mikephil.charting.data;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import com.avos.avoscloud.AVException;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p121c.C3192f;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BaseDataSet */
/* renamed from: com.github.mikephil.charting.data.e */
public abstract class C3221e<T extends Entry> implements C3220e<T> {
    /* renamed from: a */
    private String f13994a;
    /* renamed from: b */
    protected List<Integer> f13995b;
    /* renamed from: c */
    protected List<Integer> f13996c;
    /* renamed from: d */
    protected AxisDependency f13997d;
    /* renamed from: e */
    protected boolean f13998e;
    /* renamed from: f */
    protected transient C3192f f13999f;
    /* renamed from: g */
    protected Typeface f14000g;
    /* renamed from: h */
    protected boolean f14001h;
    /* renamed from: i */
    protected boolean f14002i;
    /* renamed from: j */
    protected C3279e f14003j;
    /* renamed from: k */
    protected float f14004k;
    /* renamed from: l */
    protected boolean f14005l;
    /* renamed from: m */
    private LegendForm f14006m;
    /* renamed from: n */
    private float f14007n;
    /* renamed from: o */
    private float f14008o;
    /* renamed from: p */
    private DashPathEffect f14009p;

    public C3221e() {
        this.f13995b = null;
        this.f13996c = null;
        this.f13994a = "DataSet";
        this.f13997d = AxisDependency.LEFT;
        this.f13998e = true;
        this.f14006m = LegendForm.DEFAULT;
        this.f14007n = Float.NaN;
        this.f14008o = Float.NaN;
        this.f14009p = null;
        this.f14001h = true;
        this.f14002i = true;
        this.f14003j = new C3279e();
        this.f14004k = 17.0f;
        this.f14005l = true;
        this.f13995b = new ArrayList();
        this.f13996c = new ArrayList();
        this.f13995b.add(Integer.valueOf(Color.rgb(AVException.EXCEEDED_QUOTA, 234, 255)));
        this.f13996c.add(Integer.valueOf(ViewCompat.MEASURED_STATE_MASK));
    }

    public C3221e(String str) {
        this();
        this.f13994a = str;
    }

    /* renamed from: i */
    public void m15511i() {
        mo3937E();
    }

    /* renamed from: j */
    public List<Integer> mo3921j() {
        return this.f13995b;
    }

    /* renamed from: k */
    public int mo3922k() {
        return ((Integer) this.f13995b.get(0)).intValue();
    }

    /* renamed from: b */
    public int mo3917b(int i) {
        return ((Integer) this.f13995b.get(i % this.f13995b.size())).intValue();
    }

    /* renamed from: a */
    public void m15504a(List<Integer> list) {
        this.f13995b = list;
    }

    /* renamed from: c */
    public void m15508c(int i) {
        m15514l();
        this.f13995b.add(Integer.valueOf(i));
    }

    /* renamed from: l */
    public void m15514l() {
        if (this.f13995b == null) {
            this.f13995b = new ArrayList();
        }
        this.f13995b.clear();
    }

    /* renamed from: a */
    public void m15503a(String str) {
        this.f13994a = str;
    }

    /* renamed from: m */
    public String mo3923m() {
        return this.f13994a;
    }

    /* renamed from: a */
    public void mo3916a(boolean z) {
        this.f13998e = z;
    }

    /* renamed from: n */
    public boolean mo3924n() {
        return this.f13998e;
    }

    /* renamed from: a */
    public void mo3915a(C3192f c3192f) {
        if (c3192f != null) {
            this.f13999f = c3192f;
        }
    }

    /* renamed from: o */
    public C3192f mo3925o() {
        if (mo3926p()) {
            return C3283i.m15932a();
        }
        return this.f13999f;
    }

    /* renamed from: p */
    public boolean mo3926p() {
        return this.f13999f == null;
    }

    /* renamed from: d */
    public void mo3919d(int i) {
        this.f13996c.clear();
        this.f13996c.add(Integer.valueOf(i));
    }

    /* renamed from: a */
    public void mo3914a(float f) {
        this.f14004k = C3283i.m15928a(f);
    }

    /* renamed from: e */
    public int mo3920e(int i) {
        return ((Integer) this.f13996c.get(i % this.f13996c.size())).intValue();
    }

    /* renamed from: q */
    public Typeface mo3927q() {
        return this.f14000g;
    }

    /* renamed from: r */
    public float mo3928r() {
        return this.f14004k;
    }

    /* renamed from: s */
    public LegendForm mo3929s() {
        return this.f14006m;
    }

    /* renamed from: t */
    public float mo3930t() {
        return this.f14007n;
    }

    /* renamed from: u */
    public float mo3931u() {
        return this.f14008o;
    }

    /* renamed from: v */
    public DashPathEffect mo3932v() {
        return this.f14009p;
    }

    /* renamed from: b */
    public void mo3918b(boolean z) {
        this.f14001h = z;
    }

    /* renamed from: w */
    public boolean mo3933w() {
        return this.f14001h;
    }

    /* renamed from: x */
    public boolean mo3934x() {
        return this.f14002i;
    }

    /* renamed from: y */
    public C3279e mo3935y() {
        return this.f14003j;
    }

    /* renamed from: z */
    public boolean mo3936z() {
        return this.f14005l;
    }

    /* renamed from: A */
    public AxisDependency mo3913A() {
        return this.f13997d;
    }

    /* renamed from: a */
    public void m15502a(AxisDependency axisDependency) {
        this.f13997d = axisDependency;
    }
}
