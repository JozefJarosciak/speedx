package com.beastbikes.android.modules.cycling.p111a;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/* compiled from: AbstractSimplify */
/* renamed from: com.beastbikes.android.modules.cycling.a.a */
abstract class C1885a<T> {
    /* renamed from: a */
    private T[] f8448a;

    /* compiled from: AbstractSimplify */
    /* renamed from: com.beastbikes.android.modules.cycling.a.a$a */
    private static class C1884a {
        /* renamed from: a */
        int f8446a;
        /* renamed from: b */
        int f8447b;

        private C1884a(int i, int i2) {
            this.f8446a = i;
            this.f8447b = i2;
        }
    }

    /* renamed from: a */
    public abstract double mo3267a(T t, T t2);

    /* renamed from: a */
    public abstract double mo3268a(T t, T t2, T t3);

    protected C1885a(T[] tArr) {
        this.f8448a = tArr;
    }

    /* renamed from: a */
    public T[] mo3269a(T[] tArr, double d, boolean z) {
        double d2 = d * d;
        if (!z) {
            tArr = m9752a((Object[]) tArr, d2);
        }
        return m9754b(tArr, d2);
    }

    /* renamed from: a */
    T[] m9752a(T[] tArr, double d) {
        Object obj = tArr[0];
        List arrayList = new ArrayList();
        arrayList.add(obj);
        Object obj2 = obj;
        obj = null;
        for (int i = 1; i < tArr.length; i++) {
            obj = tArr[i];
            if (mo3267a(obj, obj2) > d) {
                arrayList.add(obj);
                obj2 = obj;
            }
        }
        if (obj2 != obj) {
            arrayList.add(obj);
        }
        return arrayList.toArray(this.f8448a);
    }

    /* renamed from: b */
    T[] m9754b(T[] tArr, double d) {
        BitSet bitSet = new BitSet(tArr.length);
        bitSet.set(0);
        bitSet.set(tArr.length - 1);
        List arrayList = new ArrayList();
        arrayList.add(new C1884a(0, tArr.length - 1));
        while (!arrayList.isEmpty()) {
            C1884a c1884a = (C1884a) arrayList.remove(arrayList.size() - 1);
            int i = -1;
            double d2 = 0.0d;
            for (int i2 = c1884a.f8446a + 1; i2 < c1884a.f8447b; i2++) {
                double a = mo3268a(tArr[i2], tArr[c1884a.f8446a], tArr[c1884a.f8447b]);
                if (a > d2) {
                    d2 = a;
                    i = i2;
                }
            }
            if (d2 > d) {
                bitSet.set(i);
                arrayList.add(new C1884a(c1884a.f8446a, i));
                arrayList.add(new C1884a(i, c1884a.f8447b));
            }
        }
        List arrayList2 = new ArrayList(bitSet.cardinality());
        for (int nextSetBit = bitSet.nextSetBit(0); nextSetBit >= 0; nextSetBit = bitSet.nextSetBit(nextSetBit + 1)) {
            arrayList2.add(tArr[nextSetBit]);
        }
        return arrayList2.toArray(this.f8448a);
    }
}
