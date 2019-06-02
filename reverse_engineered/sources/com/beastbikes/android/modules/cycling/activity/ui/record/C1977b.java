package com.beastbikes.android.modules.cycling.activity.ui.record;

import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.p125f.C1988b;
import com.beastbikes.android.modules.cycling.activity.ui.record.p125f.C1989c;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.C2411a;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: ChartDataProvider */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.b */
public class C1977b {
    /* renamed from: q */
    private static C1977b f8873q;
    /* renamed from: a */
    private ArrayList<Entry> f8874a = new ArrayList();
    /* renamed from: b */
    private ArrayList<Entry> f8875b = new ArrayList();
    /* renamed from: c */
    private ArrayList<Entry> f8876c = new ArrayList();
    /* renamed from: d */
    private ArrayList<BarEntry> f8877d = new ArrayList();
    /* renamed from: e */
    private ArrayList<Entry> f8878e = new ArrayList();
    /* renamed from: f */
    private ArrayList<Entry> f8879f = new ArrayList();
    /* renamed from: g */
    private ArrayList<BarEntry> f8880g = new ArrayList();
    /* renamed from: h */
    private float f8881h;
    /* renamed from: i */
    private float f8882i;
    /* renamed from: j */
    private int f8883j;
    /* renamed from: k */
    private int f8884k;
    /* renamed from: l */
    private double f8885l;
    /* renamed from: m */
    private double f8886m;
    /* renamed from: n */
    private int[] f8887n = new int[]{C1373R.string.label_heart_rate_recovery_area, C1373R.string.label_heart_rate_burning_fat_area, C1373R.string.label_heart_rate_train_area, C1373R.string.label_heart_rate_anaerobic_area, C1373R.string.label_heart_rate_limit_area};
    /* renamed from: o */
    private double f8888o;
    /* renamed from: p */
    private int f8889p;
    /* renamed from: r */
    private ActivityDTO f8890r;
    /* renamed from: s */
    private ArrayList<C2411a> f8891s;

    private C1977b() {
    }

    /* renamed from: a */
    public static C1977b m10160a() {
        if (f8873q == null) {
            f8873q = new C1977b();
        }
        return f8873q;
    }

    /* renamed from: b */
    public void m10169b() {
        this.f8874a.clear();
        this.f8874a = null;
        this.f8875b.clear();
        this.f8875b = null;
        this.f8876c.clear();
        this.f8876c = null;
        this.f8877d.clear();
        this.f8877d = null;
        this.f8878e.clear();
        this.f8878e = null;
        this.f8879f.clear();
        this.f8879f = null;
        this.f8880g.clear();
        this.f8880g = null;
        this.f8887n = null;
        f8873q = null;
    }

    /* renamed from: a */
    public void m10165a(ActivityDTO activityDTO) {
        this.f8890r = activityDTO;
    }

    /* renamed from: c */
    public ActivityDTO m10171c() {
        return this.f8890r;
    }

    /* renamed from: d */
    public ArrayList<C2411a> m10173d() {
        return this.f8891s;
    }

    /* renamed from: a */
    public void m10166a(ArrayList<C2411a> arrayList) {
        this.f8891s = arrayList;
    }

    /* renamed from: a */
    public void m10168a(List<Double> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            this.f8874a.clear();
            for (int i = 0; i < size; i++) {
                this.f8874a.add(new Entry((float) i, ((Double) list.get(i)).floatValue()));
            }
        }
    }

    /* renamed from: b */
    public void m10170b(List<Double> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            this.f8875b.clear();
            float f = 0.0f;
            for (int i = 0; i < size; i++) {
                f += ((Double) list.get(i)).floatValue();
                this.f8875b.add(new Entry((float) i, ((Double) list.get(i)).floatValue()));
            }
            this.f8881h = f / ((float) size);
            double totalDistance = (this.f8890r.getTotalDistance() * 1000.0d) / ((double) size);
            this.f8885l = this.f8890r.getRiseTotal();
            this.f8886m = this.f8890r.getUphillDistance();
            int i2 = 1;
            double d = 0.0d;
            double d2 = 0.0d;
            while (i2 < size) {
                double doubleValue = ((Double) list.get(i2)).doubleValue() - ((Double) list.get(i2 - 1)).doubleValue();
                if (doubleValue > 0.0d) {
                    d2 += doubleValue;
                    doubleValue = d + totalDistance;
                    d = d2;
                } else {
                    doubleValue = d;
                    d = d2;
                }
                i2++;
                d2 = d;
                d = doubleValue;
            }
            if (this.f8885l <= 0.0d) {
                this.f8885l = d2;
            }
            if (this.f8886m <= 0.0d) {
                this.f8886m = d;
            }
        }
    }

    /* renamed from: c */
    public void m10172c(List<Double> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            this.f8876c.clear();
            float f = 0.0f;
            for (int i = 0; i < size; i++) {
                f += ((Double) list.get(i)).floatValue();
                this.f8876c.add(new Entry((float) i, ((Double) list.get(i)).floatValue()));
            }
            this.f8882i = f / ((float) size);
        }
    }

    /* renamed from: a */
    public void m10164a(long j, int i, List<Double> list) {
        if (list != null && !list.isEmpty()) {
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            if (i > 0) {
                i2 = (int) (((double) i) * 0.66d);
                i3 = (int) (((double) i) * 0.73d);
                i4 = (int) (((double) i) * 0.84d);
                i5 = (int) (((double) i) * 0.91d);
            } else {
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
            }
            for (Double doubleValue : list) {
                double doubleValue2 = doubleValue.doubleValue();
                if (doubleValue2 < ((double) i2)) {
                    i6 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7 + 1;
                } else if (doubleValue2 >= ((double) i2) && doubleValue2 < ((double) i3)) {
                    i6 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8 + 1;
                    i8 = i7;
                } else if (doubleValue2 >= ((double) i3) && doubleValue2 < ((double) i4)) {
                    i6 = i11;
                    i11 = i10;
                    i10 = i9 + 1;
                    i9 = i8;
                    i8 = i7;
                } else if (doubleValue2 >= ((double) i4) && doubleValue2 < ((double) i5)) {
                    i6 = i11;
                    i11 = i10 + 1;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                } else if (doubleValue2 >= ((double) i5)) {
                    i6 = i11 + 1;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                } else {
                    i6 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                }
                i7 = i8;
                i8 = i9;
                i9 = i10;
                i10 = i11;
                i11 = i6;
            }
            this.f8877d.clear();
            i4 = list.size();
            int[] iArr = new int[]{i7, i8, i9, i10, i11};
            i5 = 0;
            for (i6 = 0; i6 < 4; i6++) {
                float f = (((float) iArr[i6]) / ((float) i4)) * 100.0f;
                this.f8877d.add(new BarEntry((float) i6, f, new C1988b((long) ((((float) j) * f) / 100.0f), this.f8887n[i6], Math.round(f))));
                i5 = (int) (((float) i5) + f);
            }
            float f2 = (float) (100 - i5);
            this.f8877d.add(new BarEntry(4.0f, f2, new C1988b((long) ((((float) j) * f2) / 100.0f), this.f8887n[4], Math.round(f2))));
        }
    }

    /* renamed from: d */
    public void m10174d(List<Double> list) {
        if (list != null && !list.isEmpty()) {
            this.f8878e.clear();
            int size = list.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += ((Double) list.get(i2)).intValue();
                this.f8878e.add(new Entry((float) i2, ((Double) list.get(i2)).floatValue()));
            }
            this.f8883j = i / size;
        }
    }

    /* renamed from: a */
    public void m10167a(ArrayList<Double> arrayList, long j) {
        if (arrayList != null && !arrayList.isEmpty()) {
            this.f8879f.clear();
            this.f8880g.clear();
            int size = arrayList.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                i += ((Double) arrayList.get(i2)).intValue();
                this.f8879f.add(new Entry((float) i2, ((Double) arrayList.get(i2)).floatValue()));
            }
            this.f8884k = i / size;
            this.f8884k = this.f8884k < 0 ? 0 : this.f8884k;
            m10161b(arrayList, j);
            if (this.f8890r != null && this.f8890r.getAvgPower() <= 0.0d) {
                this.f8890r.setAvgPower((double) this.f8884k);
            }
        }
    }

    /* renamed from: b */
    private void m10161b(ArrayList<Double> arrayList, long j) {
        if (arrayList != null && !arrayList.isEmpty()) {
            int i;
            Iterator it = arrayList.iterator();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            while (it.hasNext()) {
                double doubleValue = ((Double) it.next()).doubleValue();
                if (doubleValue < 1.0d) {
                    i = i12;
                    i12 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    i4 = i3;
                    i3 = i2 + 1;
                } else if (doubleValue >= 1.0d && doubleValue < 51.0d) {
                    i = i12;
                    i12 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    i4 = i3 + 1;
                    i3 = i2;
                } else if (doubleValue >= 51.0d && doubleValue < 101.0d) {
                    i = i12;
                    i12 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4 + 1;
                    i4 = i3;
                    i3 = i2;
                } else if (doubleValue >= 101.0d && doubleValue < 151.0d) {
                    i = i12;
                    i12 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5 + 1;
                    i5 = i4;
                    i4 = i3;
                    i3 = i2;
                } else if (doubleValue >= 151.0d && doubleValue < 201.0d) {
                    i = i12;
                    i12 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6 + 1;
                    i6 = i5;
                    i5 = i4;
                    i4 = i3;
                    i3 = i2;
                } else if (doubleValue >= 201.0d && doubleValue < 251.0d) {
                    i = i12;
                    i12 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7 + 1;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    i4 = i3;
                    i3 = i2;
                } else if (doubleValue >= 251.0d && doubleValue < 301.0d) {
                    i = i12;
                    i12 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8 + 1;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    i4 = i3;
                    i3 = i2;
                } else if (doubleValue >= 301.0d && doubleValue < 351.0d) {
                    i = i12;
                    i12 = i11;
                    i11 = i10;
                    i10 = i9 + 1;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    i4 = i3;
                    i3 = i2;
                } else if (doubleValue >= 351.0d && doubleValue < 401.0d) {
                    i = i12;
                    i12 = i11;
                    i11 = i10 + 1;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    i4 = i3;
                    i3 = i2;
                } else if (doubleValue >= 401.0d && doubleValue < 451.0d) {
                    i = i12;
                    i12 = i11 + 1;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    i4 = i3;
                    i3 = i2;
                } else if (doubleValue >= 451.0d) {
                    i = i12 + 1;
                    i12 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    i4 = i3;
                    i3 = i2;
                } else {
                    i = i12;
                    i12 = i11;
                    i11 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    i4 = i3;
                    i3 = i2;
                }
                i2 = i3;
                i3 = i4;
                i4 = i5;
                i5 = i6;
                i6 = i7;
                i7 = i8;
                i8 = i9;
                i9 = i10;
                i10 = i11;
                i11 = i12;
                i12 = i;
            }
            this.f8880g.clear();
            int[] iArr = new int[]{i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12};
            i11 = arrayList.size();
            float f = 0.0f;
            for (i = 0; i < 10; i++) {
                float f2 = (((float) iArr[i]) / ((float) i11)) * 100.0f;
                this.f8880g.add(new BarEntry((float) i, f2, new C1989c((long) ((((float) j) * f2) / 100.0f), i, Math.round(f2))));
                f += f2;
            }
            float f3 = 100.0f - f;
            this.f8880g.add(new BarEntry(10.0f, f3, new C1989c((long) ((((float) j) * f3) / 100.0f), 10, Math.round(f3))));
        }
    }

    /* renamed from: e */
    public ArrayList<Entry> m10175e() {
        return this.f8874a;
    }

    /* renamed from: f */
    public ArrayList<Entry> m10176f() {
        return this.f8875b;
    }

    /* renamed from: g */
    public float m10177g() {
        return this.f8881h;
    }

    /* renamed from: h */
    public ArrayList<Entry> m10178h() {
        return this.f8876c;
    }

    /* renamed from: i */
    public float m10179i() {
        return this.f8882i;
    }

    /* renamed from: j */
    public ArrayList<BarEntry> m10180j() {
        return this.f8877d;
    }

    /* renamed from: k */
    public ArrayList<Entry> m10181k() {
        return this.f8878e;
    }

    /* renamed from: l */
    public ArrayList<Entry> m10182l() {
        return this.f8879f;
    }

    /* renamed from: m */
    public ArrayList<BarEntry> m10183m() {
        return this.f8880g;
    }

    /* renamed from: n */
    public float m10184n() {
        return (float) this.f8883j;
    }

    /* renamed from: o */
    public int m10185o() {
        return this.f8884k;
    }

    /* renamed from: p */
    public double m10186p() {
        return this.f8888o;
    }

    /* renamed from: a */
    public void m10162a(double d) {
        this.f8888o = d;
    }

    /* renamed from: q */
    public int m10187q() {
        return this.f8889p;
    }

    /* renamed from: a */
    public void m10163a(int i) {
        this.f8889p = i;
    }
}
