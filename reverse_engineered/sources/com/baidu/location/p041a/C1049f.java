package com.baidu.location.p041a;

import android.location.Location;
import com.baidu.location.Jni;
import com.baidu.location.p042d.C1091b;
import com.baidu.location.p042d.C1099i;
import com.baidu.location.p042d.C1100j;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;

/* renamed from: com.baidu.location.a.f */
public class C1049f {
    /* renamed from: a */
    private static C1049f f2413a = null;
    /* renamed from: b */
    private static String f2414b = "Temp_in.dat";
    /* renamed from: c */
    private static File f2415c = new File(C1099i.f2702a, f2414b);
    /* renamed from: d */
    private static StringBuffer f2416d = null;
    /* renamed from: e */
    private static boolean f2417e = true;
    /* renamed from: f */
    private static int f2418f = 0;
    /* renamed from: g */
    private static int f2419g = 0;
    /* renamed from: h */
    private static long f2420h = 0;
    /* renamed from: i */
    private static long f2421i = 0;
    /* renamed from: j */
    private static long f2422j = 0;
    /* renamed from: k */
    private static double f2423k = 0.0d;
    /* renamed from: l */
    private static double f2424l = 0.0d;
    /* renamed from: m */
    private static int f2425m = 0;
    /* renamed from: n */
    private static int f2426n = 0;
    /* renamed from: o */
    private static int f2427o = 0;
    /* renamed from: p */
    private String f2428p = null;
    /* renamed from: q */
    private boolean f2429q = true;

    private C1049f(String str) {
        if (str == null) {
            str = "";
        } else if (str.length() > 100) {
            str = str.substring(0, 100);
        }
        this.f2428p = str;
    }

    /* renamed from: a */
    public static C1049f m3722a() {
        if (f2413a == null) {
            f2413a = new C1049f(C1091b.m3989a().m3995c());
        }
        return f2413a;
    }

    /* renamed from: a */
    private String m3723a(int i) {
        if (!f2415c.exists()) {
            return null;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(f2415c, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            if (!C1049f.m3724a(readInt, randomAccessFile.readInt(), randomAccessFile.readInt())) {
                randomAccessFile.close();
                C1049f.m3729d();
                return null;
            } else if (i == 0 || i == readInt + 1) {
                randomAccessFile.close();
                return null;
            } else {
                long j = (12 + 0) + ((long) ((i - 1) * 1024));
                randomAccessFile.seek(j);
                int readInt2 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile.seek(j + 4);
                for (readInt = 0; readInt < readInt2; readInt++) {
                    bArr[readInt] = randomAccessFile.readByte();
                }
                randomAccessFile.close();
                return new String(bArr);
            }
        } catch (IOException e) {
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m3724a(int i, int i2, int i3) {
        return (i < 0 || i > C1100j.ac) ? false : (i2 < 0 || i2 > i + 1) ? false : i3 >= 1 && i3 <= i + 1 && i3 <= C1100j.ac;
    }

    /* renamed from: a */
    private boolean m3725a(Location location, int i, int i2) {
        if (location == null || !C1100j.f2728Y || !this.f2429q) {
            return false;
        }
        if (C1100j.aa < 5) {
            C1100j.aa = 5;
        } else if (C1100j.aa > 1000) {
            C1100j.aa = 1000;
        }
        if (C1100j.ab < 5) {
            C1100j.ab = 5;
        } else if (C1100j.ab > 3600) {
            C1100j.ab = 3600;
        }
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        long time = location.getTime() / 1000;
        if (f2417e) {
            f2418f = 1;
            f2416d = new StringBuffer("");
            f2416d.append(String.format(Locale.CHINA, "&nr=%s&traj=%d,%.5f,%.5f|", new Object[]{this.f2428p, Long.valueOf(time), Double.valueOf(longitude), Double.valueOf(latitude)}));
            f2419g = f2416d.length();
            f2420h = time;
            f2423k = longitude;
            f2424l = latitude;
            f2421i = (long) Math.floor((longitude * 100000.0d) + 0.5d);
            f2422j = (long) Math.floor((latitude * 100000.0d) + 0.5d);
            f2417e = false;
            return true;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(latitude, longitude, f2424l, f2423k, fArr);
        long j = time - f2420h;
        if (fArr[0] < ((float) C1100j.aa) && j < ((long) C1100j.ab)) {
            return false;
        }
        if (f2416d == null) {
            f2418f++;
            f2419g = 0;
            f2416d = new StringBuffer("");
            f2416d.append(String.format(Locale.CHINA, "&nr=%s&traj=%d,%.5f,%.5f|", new Object[]{this.f2428p, Long.valueOf(time), Double.valueOf(longitude), Double.valueOf(latitude)}));
            f2419g = f2416d.length();
            f2420h = time;
            f2423k = longitude;
            f2424l = latitude;
            f2421i = (long) Math.floor((longitude * 100000.0d) + 0.5d);
            f2422j = (long) Math.floor((latitude * 100000.0d) + 0.5d);
        } else {
            f2423k = longitude;
            f2424l = latitude;
            long floor = (long) Math.floor((longitude * 100000.0d) + 0.5d);
            long floor2 = (long) Math.floor((latitude * 100000.0d) + 0.5d);
            f2425m = (int) (time - f2420h);
            f2426n = (int) (floor - f2421i);
            f2427o = (int) (floor2 - f2422j);
            f2416d.append(String.format(Locale.CHINA, "%d,%d,%d|", new Object[]{Integer.valueOf(f2425m), Integer.valueOf(f2426n), Integer.valueOf(f2427o)}));
            f2419g = f2416d.length();
            f2420h = time;
            f2421i = floor;
            f2422j = floor2;
        }
        if (f2419g + 15 > 750) {
            m3726a(f2416d.toString());
            f2416d = null;
        }
        if (f2418f >= C1100j.ac) {
            this.f2429q = false;
        }
        return true;
    }

    /* renamed from: a */
    private boolean m3726a(String str) {
        if (str == null || !str.startsWith("&nr")) {
            return false;
        }
        if (!f2415c.exists() && !C1049f.m3729d()) {
            return false;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(f2415c, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            if (C1049f.m3724a(readInt, readInt2, readInt3)) {
                if (C1100j.f2729Z) {
                    if (readInt == C1100j.ac) {
                        if (str.equals(m3723a(readInt3 == 1 ? C1100j.ac : readInt3 - 1))) {
                            randomAccessFile.close();
                            return false;
                        }
                    } else if (readInt3 > 1 && str.equals(m3723a(readInt3 - 1))) {
                        randomAccessFile.close();
                        return false;
                    }
                }
                randomAccessFile.seek(((long) (((readInt3 - 1) * 1024) + 12)) + 0);
                if (str.length() > 750) {
                    randomAccessFile.close();
                    return false;
                }
                String encode = Jni.encode(str);
                int length = encode.length();
                if (length > 1020) {
                    randomAccessFile.close();
                    return false;
                }
                randomAccessFile.writeInt(length);
                randomAccessFile.writeBytes(encode);
                if (readInt == 0) {
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(1);
                    randomAccessFile.writeInt(1);
                    randomAccessFile.writeInt(2);
                } else if (readInt < C1100j.ac - 1) {
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(readInt + 1);
                    randomAccessFile.seek(8);
                    randomAccessFile.writeInt(readInt + 2);
                } else if (readInt == C1100j.ac - 1) {
                    randomAccessFile.seek(0);
                    randomAccessFile.writeInt(C1100j.ac);
                    if (readInt2 == 0 || readInt2 == 1) {
                        randomAccessFile.writeInt(2);
                    }
                    randomAccessFile.seek(8);
                    randomAccessFile.writeInt(1);
                } else if (readInt3 == readInt2) {
                    readInt = readInt3 == C1100j.ac ? 1 : readInt3 + 1;
                    r2 = readInt == C1100j.ac ? 1 : readInt + 1;
                    randomAccessFile.seek(4);
                    randomAccessFile.writeInt(r2);
                    randomAccessFile.writeInt(readInt);
                } else {
                    readInt = readInt3 == C1100j.ac ? 1 : readInt3 + 1;
                    if (readInt == readInt2) {
                        r2 = readInt == C1100j.ac ? 1 : readInt + 1;
                        randomAccessFile.seek(4);
                        randomAccessFile.writeInt(r2);
                    }
                    randomAccessFile.seek(8);
                    randomAccessFile.writeInt(readInt);
                }
                randomAccessFile.close();
                return true;
            }
            randomAccessFile.close();
            C1049f.m3729d();
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    /* renamed from: b */
    public static String m3727b() {
        if (f2415c == null) {
            return null;
        }
        if (!f2415c.exists()) {
            return null;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(f2415c, "rw");
            randomAccessFile.seek(0);
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            if (!C1049f.m3724a(readInt, readInt2, readInt3)) {
                randomAccessFile.close();
                C1049f.m3729d();
                return null;
            } else if (readInt2 == 0 || readInt2 == readInt3) {
                randomAccessFile.close();
                return null;
            } else {
                long j = 0 + ((long) (((readInt2 - 1) * 1024) + 12));
                randomAccessFile.seek(j);
                int readInt4 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt4];
                randomAccessFile.seek(j + 4);
                for (readInt3 = 0; readInt3 < readInt4; readInt3++) {
                    bArr[readInt3] = randomAccessFile.readByte();
                }
                String str = new String(bArr);
                readInt3 = readInt < C1100j.ac ? readInt2 + 1 : readInt2 == C1100j.ac ? 1 : readInt2 + 1;
                randomAccessFile.seek(4);
                randomAccessFile.writeInt(readInt3);
                randomAccessFile.close();
                return str;
            }
        } catch (IOException e) {
            return null;
        }
    }

    /* renamed from: c */
    private static void m3728c() {
        f2417e = true;
        f2416d = null;
        f2418f = 0;
        f2419g = 0;
        f2420h = 0;
        f2421i = 0;
        f2422j = 0;
        f2423k = 0.0d;
        f2424l = 0.0d;
        f2425m = 0;
        f2426n = 0;
        f2427o = 0;
    }

    /* renamed from: d */
    private static boolean m3729d() {
        if (f2415c.exists()) {
            f2415c.delete();
        }
        if (!f2415c.getParentFile().exists()) {
            f2415c.getParentFile().mkdirs();
        }
        try {
            f2415c.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(f2415c, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(1);
            randomAccessFile.close();
            C1049f.m3728c();
            return f2415c.exists();
        } catch (IOException e) {
            return false;
        }
    }

    /* renamed from: a */
    public boolean m3730a(Location location) {
        return m3725a(location, C1100j.aa, C1100j.ab);
    }
}
