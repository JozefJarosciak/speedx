package com.baidu.location.p042d;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: com.baidu.location.d.c */
public class C1092c {
    /* renamed from: c */
    static C1092c f2677c;
    /* renamed from: a */
    String f2678a = "firll.dat";
    /* renamed from: b */
    int f2679b = 3164;
    /* renamed from: d */
    int f2680d = 0;
    /* renamed from: e */
    int f2681e = 20;
    /* renamed from: f */
    int f2682f = 40;
    /* renamed from: g */
    int f2683g = 60;
    /* renamed from: h */
    int f2684h = 80;
    /* renamed from: i */
    int f2685i = 100;

    /* renamed from: a */
    private long m3996a(int i) {
        Throwable th;
        String g = C1100j.m4025g();
        if (g == null) {
            return -1;
        }
        String str = g + File.separator + this.f2678a;
        RandomAccessFile randomAccessFile = null;
        RandomAccessFile randomAccessFile2;
        try {
            randomAccessFile2 = new RandomAccessFile(str, "rw");
            try {
                randomAccessFile2.seek((long) i);
                int readInt = randomAccessFile2.readInt();
                long readLong = randomAccessFile2.readLong();
                if (readInt == randomAccessFile2.readInt()) {
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e) {
                        }
                    }
                    return readLong;
                } else if (randomAccessFile2 == null) {
                    return -1;
                } else {
                    try {
                        randomAccessFile2.close();
                        return -1;
                    } catch (IOException e2) {
                        return -1;
                    }
                }
            } catch (Exception e3) {
                randomAccessFile = randomAccessFile2;
                if (randomAccessFile != null) {
                    return -1;
                }
                try {
                    randomAccessFile.close();
                    return -1;
                } catch (IOException e4) {
                    return -1;
                }
            } catch (Throwable th2) {
                th = th2;
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e5) {
                    }
                }
                throw th;
            }
        } catch (Exception e6) {
            if (randomAccessFile != null) {
                return -1;
            }
            randomAccessFile.close();
            return -1;
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile2 = null;
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static C1092c m3997a() {
        if (f2677c == null) {
            f2677c = new C1092c();
        }
        return f2677c;
    }

    /* renamed from: a */
    private void m3998a(int i, long j) {
        String g = C1100j.m4025g();
        if (g != null) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(g + File.separator + this.f2678a, "rw");
                randomAccessFile.seek((long) i);
                randomAccessFile.writeInt(this.f2679b);
                randomAccessFile.writeLong(j);
                randomAccessFile.writeInt(this.f2679b);
                randomAccessFile.close();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    public void m3999a(long j) {
        m3998a(this.f2680d, j);
    }

    /* renamed from: b */
    public long m4000b() {
        return m3996a(this.f2680d);
    }
}
