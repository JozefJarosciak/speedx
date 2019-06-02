package com.beastbikes.android.utils.polyline;

import java.util.ArrayList;
import java.util.List;

/* compiled from: PolylineDecoder */
/* renamed from: com.beastbikes.android.utils.polyline.a */
public class C2571a {
    /* renamed from: a */
    public List<Point> m12884a(String str) {
        return m12885a(str, 100000.0d);
    }

    /* renamed from: a */
    public List<Point> m12885a(String str, double d) {
        List<Point> arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < str.length()) {
            int i4 = 0;
            int i5 = i;
            i = 0;
            while (true) {
                int i6 = i5 + 1;
                i5 = str.charAt(i5) - 63;
                i |= (i5 & 31) << i4;
                i4 += 5;
                if (i5 < 32) {
                    break;
                }
                i5 = i6;
            }
            i2 += (i & 1) != 0 ? (i >> 1) ^ -1 : i >> 1;
            i5 = 0;
            i4 = 0;
            while (true) {
                i = i6 + 1;
                i6 = str.charAt(i6) - 63;
                i4 |= (i6 & 31) << i5;
                i5 += 5;
                if (i6 < 32) {
                    break;
                }
                i6 = i;
            }
            i3 += (i4 & 1) != 0 ? (i4 >> 1) ^ -1 : i4 >> 1;
            arrayList.add(new Point(((double) i2) / d, ((double) i3) / d));
        }
        return arrayList;
    }
}
