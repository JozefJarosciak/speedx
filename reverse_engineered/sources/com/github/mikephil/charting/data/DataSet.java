package com.github.mikephil.charting.data;

import java.util.ArrayList;
import java.util.List;

public abstract class DataSet<T extends Entry> extends C3221e<T> {
    /* renamed from: s */
    protected List<T> f14010s = null;
    /* renamed from: t */
    protected float f14011t = -3.4028235E38f;
    /* renamed from: u */
    protected float f14012u = Float.MAX_VALUE;
    /* renamed from: v */
    protected float f14013v = -3.4028235E38f;
    /* renamed from: w */
    protected float f14014w = Float.MAX_VALUE;

    public enum Rounding {
        UP,
        DOWN,
        CLOSEST
    }

    public DataSet(List<T> list, String str) {
        super(str);
        this.f14010s = list;
        if (this.f14010s == null) {
            this.f14010s = new ArrayList();
        }
        mo3937E();
    }

    /* renamed from: E */
    public void mo3937E() {
        if (this.f14010s != null && !this.f14010s.isEmpty()) {
            this.f14011t = -3.4028235E38f;
            this.f14012u = Float.MAX_VALUE;
            this.f14013v = -3.4028235E38f;
            this.f14014w = Float.MAX_VALUE;
            for (Entry a : this.f14010s) {
                mo3953a(a);
            }
        }
    }

    /* renamed from: a */
    public void mo3944a(float f, float f2) {
        if (this.f14010s != null && !this.f14010s.isEmpty()) {
            this.f14011t = -3.4028235E38f;
            this.f14012u = Float.MAX_VALUE;
            int b = m15539b(f, Float.NaN, Rounding.DOWN);
            int b2 = m15539b(f2, Float.NaN, Rounding.UP);
            for (int i = b; i <= b2; i++) {
                mo3978b((Entry) this.f14010s.get(i));
            }
        }
    }

    /* renamed from: a */
    protected void mo3953a(T t) {
        if (t != null) {
            m15543c(t);
            mo3978b((Entry) t);
        }
    }

    /* renamed from: c */
    protected void m15543c(T t) {
        if (t.m15450i() < this.f14014w) {
            this.f14014w = t.m15450i();
        }
        if (t.m15450i() > this.f14013v) {
            this.f14013v = t.m15450i();
        }
    }

    /* renamed from: b */
    protected void mo3978b(T t) {
        if (t.mo3912b() < this.f14012u) {
            this.f14012u = t.mo3912b();
        }
        if (t.mo3912b() > this.f14011t) {
            this.f14011t = t.mo3912b();
        }
    }

    /* renamed from: F */
    public int mo3938F() {
        return this.f14010s.size();
    }

    /* renamed from: b */
    public void m15542b(List<T> list) {
        this.f14010s = list;
        m15511i();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(m15531G());
        for (int i = 0; i < this.f14010s.size(); i++) {
            stringBuffer.append(((Entry) this.f14010s.get(i)).toString() + " ");
        }
        return stringBuffer.toString();
    }

    /* renamed from: G */
    public String m15531G() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DataSet, label: " + (mo3923m() == null ? "" : mo3923m()) + ", entries: " + this.f14010s.size() + "\n");
        return stringBuffer.toString();
    }

    /* renamed from: H */
    public float mo3939H() {
        return this.f14012u;
    }

    /* renamed from: I */
    public float mo3940I() {
        return this.f14011t;
    }

    /* renamed from: J */
    public float mo3941J() {
        return this.f14014w;
    }

    /* renamed from: K */
    public float mo3942K() {
        return this.f14013v;
    }

    /* renamed from: d */
    public int mo3946d(Entry entry) {
        return this.f14010s.indexOf(entry);
    }

    /* renamed from: a */
    public T mo3943a(float f, float f2, Rounding rounding) {
        int b = m15539b(f, f2, rounding);
        if (b > -1) {
            return (Entry) this.f14010s.get(b);
        }
        return null;
    }

    /* renamed from: b */
    public T mo3945b(float f, float f2) {
        return mo3943a(f, f2, Rounding.CLOSEST);
    }

    /* renamed from: g */
    public T mo3948g(int i) {
        return (Entry) this.f14010s.get(i);
    }

    /* renamed from: b */
    public int m15539b(float f, float f2, Rounding rounding) {
        if (this.f14010s == null || this.f14010s.isEmpty()) {
            return -1;
        }
        int i;
        int i2 = 0;
        int size = this.f14010s.size() - 1;
        int i3 = size;
        while (i2 < i3) {
            size = (i2 + i3) / 2;
            float i4 = ((Entry) this.f14010s.get(size)).m15450i() - f;
            float i5 = ((Entry) this.f14010s.get(size + 1)).m15450i() - f;
            float abs = Math.abs(i4);
            i5 = Math.abs(i5);
            int i6;
            if (i5 < abs) {
                i6 = i3;
                i3 = size + 1;
                i = i6;
            } else if (abs < i5) {
                i = size;
                i3 = i2;
            } else if (((double) i4) >= 0.0d) {
                i = size;
                i3 = i2;
            } else if (((double) i4) < 0.0d) {
                i6 = i3;
                i3 = size + 1;
                i = i6;
            } else {
                i = i3;
                i3 = i2;
            }
            size = i;
            i2 = i3;
            i3 = i;
        }
        if (size == -1) {
            return size;
        }
        float i7 = ((Entry) this.f14010s.get(size)).m15450i();
        if (rounding == Rounding.UP) {
            if (i7 < f && size < this.f14010s.size() - 1) {
                i = size + 1;
            }
            i = size;
        } else {
            if (rounding == Rounding.DOWN && i7 > f && size > 0) {
                i = size - 1;
            }
            i = size;
        }
        if (Float.isNaN(f2)) {
            return i;
        }
        i3 = i;
        while (i3 > 0 && ((Entry) this.f14010s.get(i3 - 1)).m15450i() == i7) {
            i3--;
        }
        float b = ((Entry) this.f14010s.get(i3)).mo3912b();
        i = i3;
        while (true) {
            i2 = i + 1;
            if (i2 < this.f14010s.size()) {
                Entry entry = (Entry) this.f14010s.get(i2);
                if (entry.m15450i() != i7) {
                    break;
                }
                float f3;
                if (Math.abs(entry.mo3912b() - f2) < Math.abs(b - f2)) {
                    i = i2;
                    f3 = f2;
                } else {
                    i = i3;
                    f3 = b;
                }
                b = f3;
                i3 = i;
                i = i2;
            } else {
                break;
            }
        }
        return i3;
    }

    /* renamed from: d */
    public List<T> mo3947d(float f) {
        List<T> arrayList = new ArrayList();
        int i = 0;
        int size = this.f14010s.size() - 1;
        while (i <= size) {
            int i2 = (size + i) / 2;
            Entry entry = (Entry) this.f14010s.get(i2);
            if (f == entry.m15450i()) {
                size = i2;
                while (size > 0 && ((Entry) this.f14010s.get(size - 1)).m15450i() == f) {
                    size--;
                }
                i = this.f14010s.size();
                while (size < i) {
                    entry = (Entry) this.f14010s.get(size);
                    if (entry.m15450i() != f) {
                        break;
                    }
                    arrayList.add(entry);
                    size++;
                }
                return arrayList;
            }
            int i3;
            if (f > entry.m15450i()) {
                int i4 = size;
                size = i2 + 1;
                i3 = i4;
            } else {
                i3 = i2 - 1;
                size = i;
            }
            i = size;
            size = i3;
        }
        return arrayList;
    }
}
