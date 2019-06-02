package com.baidu.location.p041a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.location.Location;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.cons.C0844a;
import com.baidu.location.C1102f;
import com.baidu.location.Jni;
import com.baidu.location.p042d.C1041e;
import com.baidu.location.p042d.C1091b;
import com.baidu.location.p042d.C1099i;
import com.baidu.location.p042d.C1100j;
import com.baidu.location.p043b.C1074b;
import com.baidu.location.p043b.C1075c;
import com.google.common.primitives.UnsignedBytes;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.location.a.c */
public class C1046c {
    /* renamed from: f */
    public static String f2370f = "0";
    /* renamed from: j */
    private static C1046c f2371j = null;
    /* renamed from: A */
    private C1045a f2372A;
    /* renamed from: B */
    private boolean f2373B;
    /* renamed from: C */
    private boolean f2374C;
    /* renamed from: D */
    private int f2375D;
    /* renamed from: E */
    private float f2376E;
    /* renamed from: F */
    private float f2377F;
    /* renamed from: G */
    private long f2378G;
    /* renamed from: H */
    private int f2379H;
    /* renamed from: I */
    private Handler f2380I;
    /* renamed from: J */
    private byte[] f2381J;
    /* renamed from: K */
    private byte[] f2382K;
    /* renamed from: L */
    private int f2383L;
    /* renamed from: M */
    private List<Byte> f2384M;
    /* renamed from: N */
    private boolean f2385N;
    /* renamed from: a */
    long f2386a;
    /* renamed from: b */
    Location f2387b;
    /* renamed from: c */
    Location f2388c;
    /* renamed from: d */
    StringBuilder f2389d;
    /* renamed from: e */
    long f2390e;
    /* renamed from: g */
    int f2391g;
    /* renamed from: h */
    double f2392h;
    /* renamed from: i */
    double f2393i;
    /* renamed from: k */
    private int f2394k;
    /* renamed from: l */
    private double f2395l;
    /* renamed from: m */
    private String f2396m;
    /* renamed from: n */
    private int f2397n;
    /* renamed from: o */
    private int f2398o;
    /* renamed from: p */
    private int f2399p;
    /* renamed from: q */
    private int f2400q;
    /* renamed from: r */
    private double f2401r;
    /* renamed from: s */
    private double f2402s;
    /* renamed from: t */
    private double f2403t;
    /* renamed from: u */
    private int f2404u;
    /* renamed from: v */
    private int f2405v;
    /* renamed from: w */
    private int f2406w;
    /* renamed from: x */
    private int f2407x;
    /* renamed from: y */
    private int f2408y;
    /* renamed from: z */
    private long f2409z;

    /* renamed from: com.baidu.location.a.c$a */
    class C1045a extends C1041e {
        /* renamed from: a */
        String f2368a;
        /* renamed from: b */
        final /* synthetic */ C1046c f2369b;

        public C1045a(C1046c c1046c) {
            this.f2369b = c1046c;
            this.f2368a = null;
            this.k = new HashMap();
        }

        /* renamed from: a */
        public void mo2597a() {
            this.h = "http://loc.map.baidu.com/cc.php";
            String encode = Jni.encode(this.f2368a);
            this.f2368a = null;
            this.k.put("q", encode);
        }

        /* renamed from: a */
        public void m3691a(String str) {
            this.f2368a = str;
            m3672e();
        }

        /* renamed from: a */
        public void mo2598a(boolean z) {
            if (z && this.j != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.j);
                    jSONObject.put("prod", C1091b.f2668d);
                    jSONObject.put("uptime", System.currentTimeMillis());
                    this.f2369b.m3712e(jSONObject.toString());
                } catch (Exception e) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    private C1046c() {
        this.f2394k = 1;
        this.f2395l = 0.699999988079071d;
        this.f2396m = "3G|4G";
        this.f2397n = 1;
        this.f2398o = 307200;
        this.f2399p = 15;
        this.f2400q = 1;
        this.f2401r = 3.5d;
        this.f2402s = 3.0d;
        this.f2403t = 0.5d;
        this.f2404u = 300;
        this.f2405v = 60;
        this.f2406w = 0;
        this.f2407x = 60;
        this.f2408y = 0;
        this.f2409z = 0;
        this.f2372A = null;
        this.f2373B = false;
        this.f2374C = false;
        this.f2375D = 0;
        this.f2376E = 0.0f;
        this.f2377F = 0.0f;
        this.f2378G = 0;
        this.f2379H = 500;
        this.f2386a = 0;
        this.f2387b = null;
        this.f2388c = null;
        this.f2389d = null;
        this.f2390e = 0;
        this.f2380I = null;
        this.f2381J = new byte[4];
        this.f2382K = null;
        this.f2383L = 0;
        this.f2384M = null;
        this.f2385N = false;
        this.f2391g = 0;
        this.f2392h = 116.22345545d;
        this.f2393i = 40.245667323d;
        this.f2380I = new Handler();
    }

    /* renamed from: a */
    public static C1046c m3693a() {
        if (f2371j == null) {
            f2371j = new C1046c();
        }
        return f2371j;
    }

    /* renamed from: a */
    private String m3695a(File file, String str) {
        String uuid = UUID.randomUUID().toString();
        String str2 = HelpFormatter.DEFAULT_LONG_OPT_PREFIX;
        String str3 = "\r\n";
        String str4 = "multipart/form-data";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Charset", "utf-8");
            httpURLConnection.setRequestProperty("connection", "close");
            httpURLConnection.setRequestProperty("Content-Type", str4 + ";boundary=" + uuid);
            if (file != null && file.exists()) {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str2);
                stringBuffer.append(uuid);
                stringBuffer.append(str3);
                stringBuffer.append("Content-Disposition: form-data; name=\"location_dat\"; filename=\"" + file.getName() + "\"" + str3);
                stringBuffer.append("Content-Type: application/octet-stream; charset=utf-8" + str3);
                stringBuffer.append(str3);
                dataOutputStream.write(stringBuffer.toString().getBytes());
                InputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    dataOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
                dataOutputStream.write(str3.getBytes());
                dataOutputStream.write((str2 + uuid + str2 + str3).getBytes());
                dataOutputStream.flush();
                int responseCode = httpURLConnection.getResponseCode();
                outputStream.close();
                httpURLConnection.disconnect();
                this.f2408y += HttpStatus.SC_BAD_REQUEST;
                m3705c(this.f2408y);
                if (responseCode == 200) {
                    return C0844a.f2048d;
                }
            }
        } catch (MalformedURLException e) {
        } catch (IOException e2) {
        }
        return "0";
    }

    /* renamed from: a */
    private boolean m3698a(String str, Context context) {
        boolean z = false;
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    boolean z2;
                    if (runningAppProcessInfo.processName.equals(str)) {
                        int i = runningAppProcessInfo.importance;
                        if (i == 200 || i == 100) {
                            z2 = true;
                            z = z2;
                        }
                    }
                    z2 = z;
                    z = z2;
                }
            }
        } catch (Exception e) {
        }
        return z;
    }

    /* renamed from: a */
    private byte[] m3699a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((ViewCompat.MEASURED_STATE_MASK & i) >> 24)};
    }

    /* renamed from: a */
    private byte[] m3700a(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        byte nextInt = (byte) new Random().nextInt(255);
        byte nextInt2 = (byte) new Random().nextInt(255);
        byte[] bArr = new byte[(bytes.length + 2)];
        int length = bytes.length;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (bytes[i] ^ nextInt);
            i++;
            i2 = i3;
        }
        i = i2 + 1;
        bArr[i2] = nextInt;
        i2 = i + 1;
        bArr[i] = nextInt2;
        return bArr;
    }

    /* renamed from: b */
    private String m3701b(String str) {
        Calendar instance = Calendar.getInstance();
        return String.format(str, new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5))});
    }

    /* renamed from: b */
    private void m3702b(int i) {
        byte[] a = m3699a(i);
        for (int i2 = 0; i2 < 4; i2++) {
            this.f2384M.add(Byte.valueOf(a[i2]));
        }
    }

    /* renamed from: b */
    private void m3703b(Location location) {
        m3706c(location);
        m3716h();
    }

    /* renamed from: c */
    private void m3704c() {
        if (!this.f2385N) {
            this.f2385N = true;
            m3710d(C1091b.f2668d);
            m3718j();
            m3708d();
        }
    }

    /* renamed from: c */
    private void m3705c(int i) {
        if (i != 0) {
            try {
                RandomAccessFile randomAccessFile;
                File file = new File(C1099i.f2702a + "/grtcf.dat");
                if (!file.exists()) {
                    File file2 = new File(C1099i.f2702a);
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    if (file.createNewFile()) {
                        randomAccessFile = new RandomAccessFile(file, "rw");
                        randomAccessFile.seek(2);
                        randomAccessFile.writeInt(0);
                        randomAccessFile.seek(8);
                        byte[] bytes = "1980_01_01:0".getBytes();
                        randomAccessFile.writeInt(bytes.length);
                        randomAccessFile.write(bytes);
                        randomAccessFile.seek(200);
                        randomAccessFile.writeBoolean(false);
                        randomAccessFile.seek(800);
                        randomAccessFile.writeBoolean(false);
                        randomAccessFile.close();
                    } else {
                        return;
                    }
                }
                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(8);
                byte[] bytes2 = (m3701b("%d_%02d_%02d") + ":" + i).getBytes();
                randomAccessFile.writeInt(bytes2.length);
                randomAccessFile.write(bytes2);
                randomAccessFile.close();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: c */
    private void m3706c(Location location) {
        if (System.currentTimeMillis() - this.f2386a >= ((long) this.f2379H) && location != null) {
            if (location != null && location.hasSpeed() && location.getSpeed() > this.f2376E) {
                this.f2376E = location.getSpeed();
            }
            try {
                if (this.f2384M == null) {
                    this.f2384M = new ArrayList();
                    m3717i();
                    m3709d(location);
                } else {
                    m3711e(location);
                }
            } catch (Exception e) {
            }
            this.f2383L++;
        }
    }

    /* renamed from: c */
    private void m3707c(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("on")) {
                    this.f2394k = jSONObject.getInt("on");
                }
                if (jSONObject.has("bash")) {
                    this.f2395l = jSONObject.getDouble("bash");
                }
                if (jSONObject.has(C0825c.f1951a)) {
                    this.f2396m = jSONObject.getString(C0825c.f1951a);
                }
                if (jSONObject.has("tcon")) {
                    this.f2397n = jSONObject.getInt("tcon");
                }
                if (jSONObject.has("tcsh")) {
                    this.f2398o = jSONObject.getInt("tcsh");
                }
                if (jSONObject.has("per")) {
                    this.f2399p = jSONObject.getInt("per");
                }
                if (jSONObject.has("chdron")) {
                    this.f2400q = jSONObject.getInt("chdron");
                }
                if (jSONObject.has("spsh")) {
                    this.f2401r = jSONObject.getDouble("spsh");
                }
                if (jSONObject.has("acsh")) {
                    this.f2402s = jSONObject.getDouble("acsh");
                }
                if (jSONObject.has("stspsh")) {
                    this.f2403t = jSONObject.getDouble("stspsh");
                }
                if (jSONObject.has("drstsh")) {
                    this.f2404u = jSONObject.getInt("drstsh");
                }
                if (jSONObject.has("stper")) {
                    this.f2405v = jSONObject.getInt("stper");
                }
                if (jSONObject.has("nondron")) {
                    this.f2406w = jSONObject.getInt("nondron");
                }
                if (jSONObject.has("nondrper")) {
                    this.f2407x = jSONObject.getInt("nondrper");
                }
                if (jSONObject.has("uptime")) {
                    this.f2409z = jSONObject.getLong("uptime");
                }
                m3719k();
            } catch (JSONException e) {
            }
        }
    }

    /* renamed from: d */
    private void m3708d() {
        String str = null;
        if (null == null) {
            str = "7.1.2";
        }
        String[] split = str.split("\\.");
        int length = split.length;
        this.f2381J[0] = (byte) 0;
        this.f2381J[1] = (byte) 0;
        this.f2381J[2] = (byte) 0;
        this.f2381J[3] = (byte) 0;
        if (length >= 4) {
            length = 4;
        }
        int i = 0;
        while (i < length) {
            try {
                this.f2381J[i] = (byte) (Integer.valueOf(split[i]).intValue() & 255);
                i++;
            } catch (Exception e) {
            }
        }
        this.f2382K = m3700a(C1091b.f2668d + ":" + C1091b.m3989a().f2674b);
    }

    /* renamed from: d */
    private void m3709d(Location location) {
        Object obj = null;
        this.f2390e = System.currentTimeMillis();
        m3702b((int) (location.getTime() / 1000));
        m3702b((int) (location.getLongitude() * 1000000.0d));
        m3702b((int) (location.getLatitude() * 1000000.0d));
        if (location.hasBearing()) {
            Object obj2 = null;
        } else {
            int i = 1;
        }
        if (!location.hasSpeed()) {
            int i2 = 1;
        }
        if (obj2 > null) {
            this.f2384M.add(Byte.valueOf((byte) 32));
        } else {
            this.f2384M.add(Byte.valueOf((byte) (((byte) (((int) (location.getBearing() / 15.0f)) & 255)) & -33)));
        }
        if (obj > null) {
            this.f2384M.add(Byte.valueOf(UnsignedBytes.MAX_POWER_OF_TWO));
        } else {
            this.f2384M.add(Byte.valueOf((byte) (((byte) (((int) ((((double) location.getSpeed()) * 3.6d) / 4.0d)) & 255)) & 127)));
        }
        this.f2387b = location;
    }

    /* renamed from: d */
    private void m3710d(String str) {
        int i = 1;
        try {
            File file = new File(C1099i.f2702a + "/grtcf.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2);
                int readInt = randomAccessFile.readInt();
                randomAccessFile.seek(8);
                int readInt2 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile.read(bArr, 0, readInt2);
                String str2 = new String(bArr);
                if (str2.contains(m3701b("%d_%02d_%02d")) && str2.contains(":")) {
                    try {
                        String[] split = str2.split(":");
                        if (split.length > 1) {
                            this.f2408y = Integer.valueOf(split[1]).intValue();
                        }
                    } catch (Exception e) {
                    }
                }
                while (i <= readInt) {
                    randomAccessFile.seek((long) (i * 2048));
                    readInt2 = randomAccessFile.readInt();
                    bArr = new byte[readInt2];
                    randomAccessFile.read(bArr, 0, readInt2);
                    str2 = new String(bArr);
                    if (str != null && str2.contains(str)) {
                        m3707c(str2);
                        break;
                    }
                    i++;
                }
                randomAccessFile.close();
            }
        } catch (Exception e2) {
        }
    }

    /* renamed from: e */
    private void m3711e(Location location) {
        if (location != null) {
            Object obj;
            Object obj2;
            Object obj3;
            Object obj4;
            int longitude = (int) ((location.getLongitude() - this.f2387b.getLongitude()) * 1000000.0d);
            int latitude = (int) ((location.getLatitude() - this.f2387b.getLatitude()) * 1000000.0d);
            if (location.hasBearing()) {
                obj = null;
            } else {
                int i = 1;
            }
            if (location.hasSpeed()) {
                obj2 = null;
            } else {
                int i2 = 1;
            }
            if (longitude > 0) {
                obj3 = null;
            } else {
                int i3 = 1;
            }
            int abs = Math.abs(longitude);
            if (latitude > 0) {
                obj4 = null;
            } else {
                int i4 = 1;
            }
            int abs2 = Math.abs(latitude);
            if (this.f2383L > 1) {
                this.f2388c = null;
                this.f2388c = this.f2387b;
            }
            this.f2387b = location;
            if (this.f2387b != null && this.f2388c != null && this.f2387b.getTime() > this.f2388c.getTime() && this.f2387b.getTime() - this.f2388c.getTime() < 5000) {
                long time = this.f2387b.getTime() - this.f2388c.getTime();
                float[] fArr = new float[2];
                Location.distanceBetween(this.f2387b.getAltitude(), this.f2387b.getLongitude(), this.f2388c.getLatitude(), this.f2388c.getLongitude(), fArr);
                double speed = (double) ((2.0f * (fArr[0] - (this.f2388c.getSpeed() * ((float) time)))) / ((float) (time * time)));
                if (speed > ((double) this.f2377F)) {
                    this.f2377F = (float) speed;
                }
            }
            this.f2384M.add(Byte.valueOf((byte) (abs & 255)));
            this.f2384M.add(Byte.valueOf((byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & abs) >> 8)));
            this.f2384M.add(Byte.valueOf((byte) (abs2 & 255)));
            this.f2384M.add(Byte.valueOf((byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & abs2) >> 8)));
            byte b;
            if (obj > null) {
                b = (byte) 32;
                if (obj4 > null) {
                    b = (byte) 96;
                }
                if (obj3 > null) {
                    b = (byte) (b | -128);
                }
                this.f2384M.add(Byte.valueOf(b));
            } else {
                b = (byte) (((byte) (((int) (location.getBearing() / 15.0f)) & 255)) & 31);
                if (obj4 > null) {
                    b = (byte) (b | 64);
                }
                if (obj3 > null) {
                    b = (byte) (b | -128);
                }
                this.f2384M.add(Byte.valueOf(b));
            }
            if (obj2 > null) {
                this.f2384M.add(Byte.valueOf(UnsignedBytes.MAX_POWER_OF_TWO));
                return;
            }
            this.f2384M.add(Byte.valueOf((byte) (((byte) (((int) ((((double) location.getSpeed()) * 3.6d) / 4.0d)) & 255)) & 127)));
        }
    }

    /* renamed from: e */
    private void m3712e(String str) {
        try {
            File file = new File(C1099i.f2702a + "/grtcf.dat");
            if (!file.exists()) {
                File file2 = new File(C1099i.f2702a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (file.createNewFile()) {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(2);
                    randomAccessFile.writeInt(0);
                    randomAccessFile.seek(8);
                    byte[] bytes = "1980_01_01:0".getBytes();
                    randomAccessFile.writeInt(bytes.length);
                    randomAccessFile.write(bytes);
                    randomAccessFile.seek(200);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.seek(800);
                    randomAccessFile.writeBoolean(false);
                    randomAccessFile.close();
                } else {
                    return;
                }
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(2);
            int readInt = randomAccessFile2.readInt();
            int i = 1;
            while (i <= readInt) {
                randomAccessFile2.seek((long) (i * 2048));
                int readInt2 = randomAccessFile2.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile2.read(bArr, 0, readInt2);
                if (new String(bArr).contains(C1091b.f2668d)) {
                    break;
                }
                i++;
            }
            if (i >= readInt) {
                randomAccessFile2.seek(2);
                randomAccessFile2.writeInt(i);
            }
            randomAccessFile2.seek((long) (i * 2048));
            byte[] bytes2 = str.getBytes();
            randomAccessFile2.writeInt(bytes2.length);
            randomAccessFile2.write(bytes2);
            randomAccessFile2.close();
        } catch (Exception e) {
        }
    }

    /* renamed from: e */
    private boolean m3713e() {
        Throwable th;
        FileLock fileLock = null;
        FileChannel fileChannel = null;
        FileLock fileLock2 = null;
        boolean z = false;
        RandomAccessFile randomAccessFile;
        try {
            File file = new File(C1100j.m4024f() + File.separator + "gflk.dat");
            if (!file.exists()) {
                file.createNewFile();
            }
            Object obj;
            if (fileLock == null) {
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    fileChannel = randomAccessFile.getChannel();
                    try {
                        fileLock = fileChannel.tryLock();
                    } catch (Exception e) {
                        z = true;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileLock != null) {
                            try {
                                fileLock2.release();
                            } catch (Exception e2) {
                                throw th;
                            }
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    if (fileLock != null) {
                        try {
                            fileLock2.release();
                        } catch (Exception e4) {
                        }
                    }
                    if (fileLock != null) {
                        fileChannel.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    return z;
                } catch (Throwable th3) {
                    th = th3;
                    obj = fileLock;
                    if (fileLock != null) {
                        fileLock2.release();
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            }
            Object obj2 = fileLock;
            obj = fileLock;
            if (fileLock != null) {
                try {
                    fileLock.release();
                } catch (Exception e5) {
                }
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (Exception e6) {
            randomAccessFile = fileLock;
            if (fileLock != null) {
                fileLock2.release();
            }
            if (fileLock != null) {
                fileChannel.close();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return z;
        } catch (Throwable th4) {
            th = th4;
            randomAccessFile = fileLock;
            fileChannel = fileLock;
            if (fileLock != null) {
                fileLock2.release();
            }
            if (fileChannel != null) {
                fileChannel.close();
            }
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
        return z;
    }

    /* renamed from: f */
    private boolean m3714f() {
        if (this.f2373B) {
            if (this.f2374C) {
                if (((double) this.f2376E) < this.f2403t) {
                    this.f2375D += this.f2399p;
                    if (this.f2375D <= this.f2404u || System.currentTimeMillis() - this.f2378G > ((long) (this.f2405v * 1000))) {
                        return true;
                    }
                }
                this.f2375D = 0;
                this.f2374C = false;
                return true;
            } else if (((double) this.f2376E) >= this.f2403t) {
                return true;
            } else {
                this.f2374C = true;
                this.f2375D = 0;
                this.f2375D += this.f2399p;
                return true;
            }
        } else if (((double) this.f2376E) >= this.f2401r || ((double) this.f2377F) >= this.f2402s) {
            this.f2373B = true;
            return true;
        } else if (this.f2406w == 1 && System.currentTimeMillis() - this.f2378G > ((long) (this.f2407x * 1000))) {
            return true;
        }
        return false;
    }

    /* renamed from: g */
    private void m3715g() {
        this.f2384M = null;
        this.f2390e = 0;
        this.f2383L = 0;
        this.f2387b = null;
        this.f2388c = null;
        this.f2376E = 0.0f;
        this.f2377F = 0.0f;
    }

    /* renamed from: h */
    private void m3716h() {
        if (this.f2390e != 0 && System.currentTimeMillis() - this.f2390e >= ((long) (this.f2399p * 1000))) {
            if (C1102f.getServiceContext().getSharedPreferences("loc_navi_mode", 4).getBoolean("is_navi_on", false)) {
                m3715g();
            } else if (this.f2397n != 1 || m3714f()) {
                if (C1091b.f2668d.equals("com.ubercab.driver")) {
                    if (m3713e()) {
                        m3715g();
                        return;
                    }
                } else if (!m3698a(C1091b.f2668d, C1102f.getServiceContext())) {
                    m3715g();
                    return;
                }
                if (this.f2384M != null) {
                    int size = this.f2384M.size();
                    this.f2384M.set(0, Byte.valueOf((byte) (size & 255)));
                    this.f2384M.set(1, Byte.valueOf((byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & size) >> 8)));
                    this.f2384M.set(3, Byte.valueOf((byte) (this.f2383L & 255)));
                    byte[] bArr = new byte[size];
                    for (int i = 0; i < size; i++) {
                        bArr[i] = ((Byte) this.f2384M.get(i)).byteValue();
                    }
                    if (Environment.getExternalStorageState().equals("mounted")) {
                        File file = new File(Environment.getExternalStorageDirectory(), "baidu/tempdata");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        if (file.exists()) {
                            File file2 = new File(file, "intime.dat");
                            if (file2.exists()) {
                                file2.delete();
                            }
                            try {
                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                                bufferedOutputStream.write(bArr);
                                bufferedOutputStream.flush();
                                bufferedOutputStream.close();
                                new C1048e(this).start();
                            } catch (Exception e) {
                            }
                        }
                    }
                    m3715g();
                    this.f2378G = System.currentTimeMillis();
                }
            } else {
                m3715g();
            }
        }
    }

    /* renamed from: i */
    private void m3717i() {
        int i = 0;
        this.f2384M.add(Byte.valueOf((byte) 0));
        this.f2384M.add(Byte.valueOf((byte) 0));
        if (f2370f.equals("0")) {
            this.f2384M.add(Byte.valueOf((byte) -82));
        } else {
            this.f2384M.add(Byte.valueOf((byte) -66));
        }
        this.f2384M.add(Byte.valueOf((byte) 0));
        this.f2384M.add(Byte.valueOf(this.f2381J[0]));
        this.f2384M.add(Byte.valueOf(this.f2381J[1]));
        this.f2384M.add(Byte.valueOf(this.f2381J[2]));
        this.f2384M.add(Byte.valueOf(this.f2381J[3]));
        int length = this.f2382K.length;
        this.f2384M.add(Byte.valueOf((byte) ((length + 1) & 255)));
        while (i < length) {
            this.f2384M.add(Byte.valueOf(this.f2382K[i]));
            i++;
        }
    }

    /* renamed from: j */
    private void m3718j() {
        if (System.currentTimeMillis() - this.f2409z > 86400000) {
            if (this.f2372A == null) {
                this.f2372A = new C1045a(this);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(C1091b.m3989a().m3990a(false));
            stringBuffer.append(C1038a.m3645a().m3654c());
            this.f2372A.m3691a(stringBuffer.toString());
        }
        m3719k();
    }

    /* renamed from: k */
    private void m3719k() {
    }

    /* renamed from: a */
    public void m3720a(Location location) {
        if (!this.f2385N) {
            m3704c();
        }
        if (this.f2394k != 1 || !this.f2396m.contains(C1075c.m3887a(C1074b.m3866a().m3883e()))) {
            return;
        }
        if (this.f2397n != 1 || this.f2408y <= this.f2398o) {
            this.f2380I.post(new C1047d(this, location));
        }
    }

    /* renamed from: b */
    public void m3721b() {
        if (this.f2385N) {
            this.f2385N = false;
            m3715g();
        }
    }
}
