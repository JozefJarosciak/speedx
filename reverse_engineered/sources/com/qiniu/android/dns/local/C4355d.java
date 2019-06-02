package com.qiniu.android.dns.local;

import com.qiniu.android.dns.C4346b;
import com.qiniu.android.dns.NetworkInfo;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/* compiled from: Hosts */
/* renamed from: com.qiniu.android.dns.local.d */
public final class C4355d {
    /* renamed from: a */
    private final Hashtable<String, ArrayList<C4354a>> f15132a = new Hashtable();

    /* compiled from: Hosts */
    /* renamed from: com.qiniu.android.dns.local.d$a */
    public static class C4354a {
        /* renamed from: a */
        public final String f15130a;
        /* renamed from: b */
        public final int f15131b;

        public C4354a(String str, int i) {
            this.f15130a = str;
            this.f15131b = i;
        }

        public C4354a(String str) {
            this(str, 0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof C4354a)) {
                return false;
            }
            C4354a c4354a = (C4354a) obj;
            if (this.f15130a.equals(c4354a.f15130a) && this.f15131b == c4354a.f15131b) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: a */
    public String[] m17172a(C4346b c4346b, NetworkInfo networkInfo) {
        ArrayList arrayList = (ArrayList) this.f15132a.get(c4346b.f15120a);
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return m17173a(m17169a(arrayList, networkInfo));
    }

    /* renamed from: a */
    private ArrayList<C4354a> m17169a(ArrayList<C4354a> arrayList, NetworkInfo networkInfo) {
        ArrayList<C4354a> arrayList2 = new ArrayList();
        ArrayList<C4354a> arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C4354a c4354a = (C4354a) it.next();
            if (c4354a.f15131b == 0) {
                arrayList2.add(c4354a);
            }
            if (networkInfo.f15105c != 0 && c4354a.f15131b == networkInfo.f15105c) {
                arrayList3.add(c4354a);
            }
        }
        if (arrayList3.size() != 0) {
            return arrayList3;
        }
        return arrayList2;
    }

    /* renamed from: a */
    public String[] m17173a(ArrayList<C4354a> arrayList) {
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = ((C4354a) arrayList.get(i)).f15130a;
        }
        return strArr;
    }

    /* renamed from: a */
    public C4355d m17170a(String str, C4354a c4354a) {
        ArrayList arrayList = (ArrayList) this.f15132a.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        arrayList.add(c4354a);
        this.f15132a.put(str, arrayList);
        return this;
    }

    /* renamed from: a */
    public C4355d m17171a(String str, String str2) {
        m17170a(str, new C4354a(str2));
        return this;
    }
}
