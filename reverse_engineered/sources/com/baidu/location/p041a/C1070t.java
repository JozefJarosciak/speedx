package com.baidu.location.p041a;

import android.location.Location;
import com.baidu.location.Jni;
import com.baidu.location.p042d.C1041e;
import com.baidu.location.p042d.C1090a;
import com.baidu.location.p042d.C1099i;
import com.baidu.location.p042d.C1100j;
import com.baidu.location.p043b.C1072a;
import com.baidu.location.p043b.C1074b;
import com.baidu.location.p043b.C1079d;
import com.baidu.location.p043b.C1082g;
import com.baidu.location.p043b.C1085h;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* renamed from: com.baidu.location.a.t */
public class C1070t {
    /* renamed from: A */
    private static C1070t f2529A = null;
    /* renamed from: b */
    private static ArrayList<String> f2530b = new ArrayList();
    /* renamed from: c */
    private static ArrayList<String> f2531c = new ArrayList();
    /* renamed from: d */
    private static ArrayList<String> f2532d = new ArrayList();
    /* renamed from: e */
    private static String f2533e = (C1099i.f2702a + "/yo.dat");
    /* renamed from: f */
    private static final String f2534f = (C1099i.f2702a + "/yoh.dat");
    /* renamed from: g */
    private static final String f2535g = (C1099i.f2702a + "/yom.dat");
    /* renamed from: h */
    private static final String f2536h = (C1099i.f2702a + "/yol.dat");
    /* renamed from: i */
    private static final String f2537i = (C1099i.f2702a + "/yor.dat");
    /* renamed from: j */
    private static File f2538j = null;
    /* renamed from: k */
    private static int f2539k = 8;
    /* renamed from: l */
    private static int f2540l = 8;
    /* renamed from: m */
    private static int f2541m = 16;
    /* renamed from: n */
    private static int f2542n = 1024;
    /* renamed from: o */
    private static double f2543o = 0.0d;
    /* renamed from: p */
    private static double f2544p = 0.1d;
    /* renamed from: q */
    private static double f2545q = 30.0d;
    /* renamed from: r */
    private static double f2546r = 100.0d;
    /* renamed from: s */
    private static int f2547s = 0;
    /* renamed from: t */
    private static int f2548t = 64;
    /* renamed from: u */
    private static int f2549u = 128;
    /* renamed from: v */
    private static Location f2550v = null;
    /* renamed from: w */
    private static Location f2551w = null;
    /* renamed from: x */
    private static Location f2552x = null;
    /* renamed from: y */
    private static C1082g f2553y = null;
    /* renamed from: B */
    private int f2554B;
    /* renamed from: a */
    long f2555a;
    /* renamed from: z */
    private C1069a f2556z;

    /* renamed from: com.baidu.location.a.t$a */
    private class C1069a extends C1041e {
        /* renamed from: a */
        boolean f2524a;
        /* renamed from: b */
        int f2525b;
        /* renamed from: c */
        int f2526c;
        /* renamed from: d */
        final /* synthetic */ C1070t f2527d;
        /* renamed from: e */
        private ArrayList<String> f2528e;

        public C1069a(C1070t c1070t) {
            this.f2527d = c1070t;
            this.f2524a = false;
            this.f2525b = 0;
            this.f2526c = 0;
            this.f2528e = null;
            this.k = new HashMap();
        }

        /* renamed from: a */
        public void mo2597a() {
            this.h = C1100j.m4019c();
            this.i = 2;
            if (this.f2528e != null) {
                for (int i = 0; i < this.f2528e.size(); i++) {
                    if (this.f2525b == 1) {
                        this.k.put("cldc[" + i + "]", this.f2528e.get(i));
                    } else {
                        this.k.put("cltr[" + i + "]", this.f2528e.get(i));
                    }
                }
                this.k.put("trtm", String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(System.currentTimeMillis())}));
            }
        }

        /* renamed from: a */
        public void mo2598a(boolean z) {
            if (!(!z || this.j == null || this.f2528e == null)) {
                this.f2528e.clear();
            }
            if (this.k != null) {
                this.k.clear();
            }
            this.f2524a = false;
        }

        /* renamed from: b */
        public void mo2603b() {
            if (!this.f2524a) {
                if (o <= 4 || this.f2526c >= o) {
                    this.f2526c = 0;
                    this.f2524a = true;
                    this.f2525b = 0;
                    if (this.f2528e == null || this.f2528e.size() < 1) {
                        if (this.f2528e == null) {
                            this.f2528e = new ArrayList();
                        }
                        this.f2525b = 0;
                        int i = 0;
                        while (true) {
                            String b = this.f2525b < 2 ? C1070t.m3844b() : null;
                            if (b != null || this.f2525b == 1) {
                                this.f2525b = 1;
                            } else {
                                this.f2525b = 2;
                                try {
                                    b = C1049f.m3727b();
                                } catch (Exception e) {
                                    b = null;
                                }
                            }
                            if (b == null) {
                                break;
                            } else if (!b.contains("err!")) {
                                this.f2528e.add(b);
                                i += b.length();
                                if (i >= C1090a.f2667i) {
                                    break;
                                }
                            }
                        }
                    }
                    if (this.f2528e == null || this.f2528e.size() < 1) {
                        this.f2528e = null;
                        this.f2524a = false;
                        return;
                    }
                    m3672e();
                    return;
                }
                this.f2526c++;
            }
        }
    }

    private C1070t() {
        this.f2556z = null;
        this.f2554B = 0;
        this.f2555a = 0;
        this.f2556z = new C1069a(this);
        this.f2554B = 0;
    }

    /* renamed from: a */
    private static synchronized int m3833a(List<String> list, int i) {
        int i2;
        synchronized (C1070t.class) {
            if (list == null || i > 256 || i < 0) {
                i2 = -1;
            } else {
                try {
                    if (f2538j == null) {
                        f2538j = new File(f2533e);
                        if (!f2538j.exists()) {
                            f2538j = null;
                            i2 = -2;
                        }
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(f2538j, "rw");
                    if (randomAccessFile.length() < 1) {
                        randomAccessFile.close();
                        i2 = -3;
                    } else {
                        randomAccessFile.seek((long) i);
                        i2 = randomAccessFile.readInt();
                        int readInt = randomAccessFile.readInt();
                        int readInt2 = randomAccessFile.readInt();
                        int readInt3 = randomAccessFile.readInt();
                        long readLong = randomAccessFile.readLong();
                        if (!C1070t.m3839a(i2, readInt, readInt2, readInt3, readLong) || readInt < 1) {
                            randomAccessFile.close();
                            i2 = -4;
                        } else {
                            byte[] bArr = new byte[f2542n];
                            int i3 = readInt;
                            readInt = f2539k;
                            while (readInt > 0 && i3 > 0) {
                                randomAccessFile.seek(((long) ((((i2 + i3) - 1) % readInt2) * readInt3)) + readLong);
                                int readInt4 = randomAccessFile.readInt();
                                if (readInt4 > 0 && readInt4 < readInt3) {
                                    randomAccessFile.read(bArr, 0, readInt4);
                                    if (bArr[readInt4 - 1] == (byte) 0) {
                                        list.add(new String(bArr, 0, readInt4 - 1));
                                    }
                                }
                                readInt--;
                                i3--;
                            }
                            randomAccessFile.seek((long) i);
                            randomAccessFile.writeInt(i2);
                            randomAccessFile.writeInt(i3);
                            randomAccessFile.writeInt(readInt2);
                            randomAccessFile.writeInt(readInt3);
                            randomAccessFile.writeLong(readLong);
                            randomAccessFile.close();
                            i2 = f2539k - readInt;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    i2 = -5;
                }
            }
        }
        return i2;
    }

    /* renamed from: a */
    public static synchronized C1070t m3834a() {
        C1070t c1070t;
        synchronized (C1070t.class) {
            if (f2529A == null) {
                f2529A = new C1070t();
            }
            c1070t = f2529A;
        }
        return c1070t;
    }

    /* renamed from: a */
    public static String m3835a(int i) {
        String str;
        String str2 = null;
        String str3;
        if (i == 1) {
            str3 = f2534f;
            str = str3;
            List list = f2530b;
        } else if (i == 2) {
            str3 = f2535g;
            str = str3;
            r2 = f2531c;
        } else if (i == 3) {
            str3 = f2536h;
            str = str3;
            r2 = f2532d;
        } else if (i != 4) {
            return null;
        } else {
            str3 = f2537i;
            str = str3;
            r2 = f2532d;
        }
        if (list == null) {
            return null;
        }
        if (list.size() < 1) {
            C1070t.m3843a(str, list);
        }
        synchronized (C1070t.class) {
            int size = list.size();
            if (size > 0) {
                try {
                    str = (String) list.get(size - 1);
                    try {
                        list.remove(size - 1);
                    } catch (Exception e) {
                        str2 = str;
                        str = str2;
                        return str;
                    }
                } catch (Exception e2) {
                    str = str2;
                    return str;
                }
            }
            str = null;
        }
        return str;
    }

    /* renamed from: a */
    public static void m3836a(int i, boolean z) {
        String str;
        Object obj;
        String str2;
        if (i == 1) {
            str2 = f2534f;
            if (!z) {
                str = str2;
                List list = f2530b;
            } else {
                return;
            }
        } else if (i == 2) {
            str2 = f2535g;
            if (z) {
                str = str2;
                obj = f2530b;
            } else {
                str = str2;
                obj = f2531c;
            }
        } else if (i == 3) {
            str2 = f2536h;
            if (z) {
                str = str2;
                obj = f2531c;
            } else {
                str = str2;
                obj = f2532d;
            }
        } else if (i == 4) {
            str2 = f2537i;
            if (z) {
                str = str2;
                obj = f2532d;
            } else {
                return;
            }
        } else {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            C1070t.m3838a(str);
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(4);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            int readInt4 = randomAccessFile.readInt();
            int readInt5 = randomAccessFile.readInt();
            int size = list.size();
            int i2 = readInt5;
            while (size > f2540l) {
                readInt5 = z ? i2 + 1 : i2;
                byte[] bytes;
                if (readInt3 >= readInt) {
                    if (!z) {
                        obj = 1;
                        i2 = readInt5;
                        break;
                    }
                    randomAccessFile.seek((long) ((readInt4 * readInt2) + 128));
                    bytes = (((String) list.get(0)) + '\u0000').getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes, 0, bytes.length);
                    list.remove(0);
                    i2 = readInt4 + 1;
                    if (i2 > readInt3) {
                        i2 = 0;
                    }
                    readInt4 = readInt3;
                } else {
                    randomAccessFile.seek((long) ((readInt2 * readInt3) + 128));
                    bytes = (((String) list.get(0)) + '\u0000').getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes, 0, bytes.length);
                    list.remove(0);
                    int i3 = readInt4;
                    readInt4 = readInt3 + 1;
                    i2 = i3;
                }
                size--;
                readInt3 = readInt4;
                readInt4 = i2;
                i2 = readInt5;
            }
            obj = null;
            randomAccessFile.seek(12);
            randomAccessFile.writeInt(readInt3);
            randomAccessFile.writeInt(readInt4);
            randomAccessFile.writeInt(i2);
            randomAccessFile.close();
            if (obj != null && i < 4) {
                C1070t.m3836a(i + 1, true);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static void m3837a(C1072a c1072a, C1082g c1082g, Location location, String str) {
        C1082g c1082g2 = null;
        if ((C1100j.f2749t != 3 || C1070t.m3841a(location, c1082g) || C1070t.m3842a(location, false)) && c1072a != null && !c1072a.m3856c()) {
            String a;
            if (c1072a != null && c1072a.m3853a()) {
                if (!C1070t.m3841a(location, c1082g)) {
                    c1082g = null;
                }
                a = C1100j.m4011a(c1072a, c1082g, location, str, 1);
                if (a != null) {
                    C1070t.m3846c(Jni.encode(a));
                    f2551w = location;
                    f2550v = location;
                    if (c1082g != null) {
                        f2553y = c1082g;
                    }
                }
            } else if (c1082g != null && c1082g.m3958h() && C1070t.m3841a(location, c1082g)) {
                if (!C1070t.m3840a(location) && !C1074b.m3866a().m3882d()) {
                    str = "&cfr=1" + str;
                } else if (!C1070t.m3840a(location) && C1074b.m3866a().m3882d()) {
                    str = "&cfr=3" + str;
                } else if (C1074b.m3866a().m3882d()) {
                    str = "&cfr=2" + str;
                }
                a = C1100j.m4011a(c1072a, c1082g, location, str, 2);
                if (a != null) {
                    C1070t.m3848d(Jni.encode(a));
                    f2552x = location;
                    f2550v = location;
                    if (c1082g != null) {
                        f2553y = c1082g;
                    }
                }
            } else {
                if (!C1070t.m3840a(location) && !C1074b.m3866a().m3882d()) {
                    str = "&cfr=1" + str;
                } else if (!C1070t.m3840a(location) && C1074b.m3866a().m3882d()) {
                    str = "&cfr=3" + str;
                } else if (C1074b.m3866a().m3882d()) {
                    str = "&cfr=2" + str;
                }
                if (C1070t.m3841a(location, c1082g)) {
                    c1082g2 = c1082g;
                }
                if (c1072a != null || c1082g2 != null) {
                    String a2 = C1100j.m4011a(c1072a, c1082g2, location, str, 3);
                    if (a2 != null) {
                        C1070t.m3850e(Jni.encode(a2));
                        f2550v = location;
                        if (c1082g2 != null) {
                            f2553y = c1082g2;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m3838a(String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                File file2 = new File(C1099i.f2702a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    file = null;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(0);
                randomAccessFile.writeInt(32);
                randomAccessFile.writeInt(2048);
                randomAccessFile.writeInt(1040);
                randomAccessFile.writeInt(0);
                randomAccessFile.writeInt(0);
                randomAccessFile.writeInt(0);
                randomAccessFile.close();
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private static boolean m3839a(int i, int i2, int i3, int i4, long j) {
        return i >= 0 && i < i3 && i2 >= 0 && i2 <= i3 && i3 >= 0 && i3 <= 1024 && i4 >= 128 && i4 <= 1024;
    }

    /* renamed from: a */
    private static boolean m3840a(Location location) {
        if (location == null) {
            return false;
        }
        if (f2551w == null || f2550v == null) {
            f2551w = location;
            return true;
        }
        double distanceTo = (double) location.distanceTo(f2551w);
        return ((double) location.distanceTo(f2550v)) > ((distanceTo * ((double) C1100j.f2721R)) + ((((double) C1100j.f2720Q) * distanceTo) * distanceTo)) + ((double) C1100j.f2722S);
    }

    /* renamed from: a */
    private static boolean m3841a(Location location, C1082g c1082g) {
        if (location == null || c1082g == null || c1082g.f2633a == null || c1082g.f2633a.isEmpty() || c1082g.m3951b(f2553y)) {
            return false;
        }
        if (f2552x != null) {
            return true;
        }
        f2552x = location;
        return true;
    }

    /* renamed from: a */
    public static boolean m3842a(Location location, boolean z) {
        return C1079d.m3908a(f2550v, location, z);
    }

    /* renamed from: a */
    public static boolean m3843a(String str, List<String> list) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(8);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            byte[] bArr = new byte[f2542n];
            int i = readInt2;
            readInt2 = f2540l + 1;
            boolean z = false;
            while (readInt2 > 0 && i > 0) {
                if (i < readInt3) {
                    readInt3 = 0;
                }
                try {
                    randomAccessFile.seek((long) (((i - 1) * readInt) + 128));
                    int readInt4 = randomAccessFile.readInt();
                    if (readInt4 > 0 && readInt4 < readInt) {
                        randomAccessFile.read(bArr, 0, readInt4);
                        if (bArr[readInt4 - 1] == (byte) 0) {
                            list.add(0, new String(bArr, 0, readInt4 - 1));
                            z = true;
                        }
                    }
                    readInt2--;
                    i--;
                } catch (Exception e) {
                    return z;
                }
            }
            randomAccessFile.seek(12);
            randomAccessFile.writeInt(i);
            randomAccessFile.writeInt(readInt3);
            randomAccessFile.close();
            return z;
        } catch (Exception e2) {
            return false;
        }
    }

    /* renamed from: b */
    public static String m3844b() {
        return C1070t.m3847d();
    }

    /* renamed from: b */
    public static synchronized void m3845b(String str) {
        synchronized (C1070t.class) {
            if (!str.contains("err!")) {
                List list;
                int i = C1100j.f2744o;
                if (i == 1) {
                    list = f2530b;
                } else if (i == 2) {
                    list = f2531c;
                } else if (i == 3) {
                    list = f2532d;
                }
                if (list != null) {
                    if (list.size() <= f2541m) {
                        list.add(str);
                    }
                    if (list.size() >= f2541m) {
                        C1070t.m3836a(i, false);
                    }
                    while (list.size() > f2541m) {
                        list.remove(0);
                    }
                }
            }
        }
    }

    /* renamed from: c */
    private static void m3846c(String str) {
        C1070t.m3845b(str);
    }

    /* renamed from: d */
    public static String m3847d() {
        String str = null;
        for (int i = 1; i < 5; i++) {
            str = C1070t.m3835a(i);
            if (str != null) {
                return str;
            }
        }
        C1070t.m3833a(f2532d, f2548t);
        if (f2532d.size() > 0) {
            str = (String) f2532d.get(0);
            f2532d.remove(0);
        }
        if (str != null) {
            return str;
        }
        C1070t.m3833a(f2532d, f2547s);
        if (f2532d.size() > 0) {
            str = (String) f2532d.get(0);
            f2532d.remove(0);
        }
        if (str != null) {
            return str;
        }
        C1070t.m3833a(f2532d, f2549u);
        if (f2532d.size() <= 0) {
            return str;
        }
        str = (String) f2532d.get(0);
        f2532d.remove(0);
        return str;
    }

    /* renamed from: d */
    private static void m3848d(String str) {
        C1070t.m3845b(str);
    }

    /* renamed from: e */
    public static void m3849e() {
        f2540l = 0;
        C1070t.m3836a(1, false);
        C1070t.m3836a(2, false);
        C1070t.m3836a(3, false);
        f2540l = 8;
    }

    /* renamed from: e */
    private static void m3850e(String str) {
        C1070t.m3845b(str);
    }

    /* renamed from: f */
    public static String m3851f() {
        RandomAccessFile randomAccessFile;
        int readInt;
        File file = new File(f2535g);
        if (file.exists()) {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(20);
                readInt = randomAccessFile.readInt();
                if (readInt > 128) {
                    String str = "&p1=" + readInt;
                    randomAccessFile.seek(20);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.close();
                    return str;
                }
                randomAccessFile.close();
            } catch (Exception e) {
            }
        }
        file = new File(f2536h);
        if (file.exists()) {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(20);
                readInt = randomAccessFile.readInt();
                if (readInt > 256) {
                    str = "&p2=" + readInt;
                    randomAccessFile.seek(20);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.close();
                    return str;
                }
                randomAccessFile.close();
            } catch (Exception e2) {
            }
        }
        file = new File(f2537i);
        if (!file.exists()) {
            return null;
        }
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(20);
            readInt = randomAccessFile.readInt();
            if (readInt > 512) {
                str = "&p3=" + readInt;
                randomAccessFile.seek(20);
                randomAccessFile.writeInt(0);
                randomAccessFile.close();
                return str;
            }
            randomAccessFile.close();
            return null;
        } catch (Exception e3) {
            return null;
        }
    }

    /* renamed from: c */
    public void m3852c() {
        if (C1085h.m3963h()) {
            this.f2556z.mo2603b();
        }
    }
}
