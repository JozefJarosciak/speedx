package com.beastbikes.android.modules.cycling.activity.util;

import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.android.utils.C2569o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: SamplesEngine */
/* renamed from: com.beastbikes.android.modules.cycling.activity.util.c */
public class C2044c {
    /* renamed from: a */
    private C2043a f9312a = new C2043a();
    /* renamed from: b */
    private ArrayList<LocalActivitySample> f9313b = new ArrayList();

    /* compiled from: SamplesEngine */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.util.c$a */
    private class C2043a implements Comparator<LocalActivitySample> {
        /* renamed from: a */
        final /* synthetic */ C2044c f9311a;

        private C2043a(C2044c c2044c) {
            this.f9311a = c2044c;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m10516a((LocalActivitySample) obj, (LocalActivitySample) obj2);
        }

        /* renamed from: a */
        public int m10516a(LocalActivitySample localActivitySample, LocalActivitySample localActivitySample2) {
            double distance = localActivitySample.getDistance() - localActivitySample2.getDistance();
            if (distance > 0.0d) {
                return 1;
            }
            if (distance == 0.0d) {
                return 0;
            }
            return -1;
        }
    }

    /* renamed from: a */
    public LocalActivitySample m10518a(LocalActivitySample localActivitySample) {
        this.f9313b.add(localActivitySample);
        if (this.f9313b.size() < 5) {
            return null;
        }
        this.f9313b.remove(0);
        return m10517a();
    }

    /* renamed from: a */
    private LocalActivitySample m10517a() {
        Iterator it = this.f9313b.iterator();
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        while (it.hasNext()) {
            LocalActivitySample localActivitySample = (LocalActivitySample) it.next();
            if (!(TextUtils.isEmpty(localActivitySample.getLatitude1()) || TextUtils.isEmpty(localActivitySample.getLongitude1()))) {
                double velocity;
                double parseDouble = Double.parseDouble(localActivitySample.getLatitude1()) + d4;
                d3 += Double.parseDouble(localActivitySample.getLongitude1());
                d4 = localActivitySample.getVelocity() + d2;
                if (d < localActivitySample.getVelocity()) {
                    velocity = localActivitySample.getVelocity();
                } else {
                    velocity = d;
                }
                d = velocity;
                d2 = d4;
                d4 = parseDouble;
            }
        }
        int size = this.f9313b.size();
        d4 /= (double) size;
        d3 /= (double) size;
        double d5 = (1.0d * d2) * 2.0d;
        Iterator it2 = this.f9313b.iterator();
        while (it2.hasNext()) {
            LocalActivitySample localActivitySample2 = (LocalActivitySample) it2.next();
            if (!(TextUtils.isEmpty(localActivitySample2.getLatitude1()) || TextUtils.isEmpty(localActivitySample2.getLongitude1()))) {
                localActivitySample2.setDistance(C2569o.m12882a(Double.parseDouble(localActivitySample2.getLatitude1()), Double.parseDouble(localActivitySample2.getLongitude1()), d4, d3));
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f9313b);
        Iterator it3 = arrayList.iterator();
        if (!it3.hasNext()) {
            return null;
        }
        localActivitySample = (LocalActivitySample) it3.next();
        if (d5 <= 5.0d || localActivitySample.getDistance() <= d5) {
            localActivitySample = new LocalActivitySample();
            localActivitySample.setLatitude1(String.valueOf(d4));
            localActivitySample.setLongitude1(String.valueOf(d3));
            localActivitySample.setVelocity(d2 / ((double) this.f9313b.size()));
            return localActivitySample;
        }
        this.f9313b.remove(Collections.max(this.f9313b, this.f9312a));
        return m10517a();
    }
}
