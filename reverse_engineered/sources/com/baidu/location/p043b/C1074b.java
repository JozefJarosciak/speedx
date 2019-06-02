package com.baidu.location.p043b;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.alipay.sdk.util.C0880h;
import com.baidu.location.C1102f;
import com.baidu.location.p042d.C1090a;
import com.baidu.location.p042d.C1100j;
import io.rong.imlib.statistics.UserData;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@SuppressLint({"NewApi"})
/* renamed from: com.baidu.location.b.b */
public class C1074b {
    /* renamed from: a */
    public static int f2568a = 0;
    /* renamed from: b */
    public static int f2569b = 0;
    /* renamed from: c */
    private static C1074b f2570c = null;
    /* renamed from: k */
    private static Method f2571k = null;
    /* renamed from: l */
    private static Method f2572l = null;
    /* renamed from: m */
    private static Method f2573m = null;
    /* renamed from: n */
    private static Method f2574n = null;
    /* renamed from: o */
    private static Method f2575o = null;
    /* renamed from: p */
    private static Class<?> f2576p = null;
    /* renamed from: d */
    private TelephonyManager f2577d = null;
    /* renamed from: e */
    private Object f2578e = null;
    /* renamed from: f */
    private C1072a f2579f = new C1072a();
    /* renamed from: g */
    private C1072a f2580g = null;
    /* renamed from: h */
    private List<C1072a> f2581h = null;
    /* renamed from: i */
    private C1073a f2582i = null;
    /* renamed from: j */
    private boolean f2583j = false;
    /* renamed from: q */
    private boolean f2584q = false;

    /* renamed from: com.baidu.location.b.b$a */
    private class C1073a extends PhoneStateListener {
        /* renamed from: a */
        final /* synthetic */ C1074b f2567a;

        public C1073a(C1074b c1074b) {
            this.f2567a = c1074b;
        }

        public void onCellLocationChanged(CellLocation cellLocation) {
            if (cellLocation != null) {
                try {
                    this.f2567a.m3873k();
                } catch (Exception e) {
                }
            }
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            if (this.f2567a.f2579f == null) {
                return;
            }
            if (this.f2567a.f2579f.f2565i == 'g') {
                this.f2567a.f2579f.f2564h = signalStrength.getGsmSignalStrength();
            } else if (this.f2567a.f2579f.f2565i == 'c') {
                this.f2567a.f2579f.f2564h = signalStrength.getCdmaDbm();
            }
        }
    }

    private C1074b() {
    }

    /* renamed from: a */
    private int m3861a(int i) {
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    /* renamed from: a */
    private CellLocation m3862a(List<?> list) {
        CellLocation cdmaCellLocation;
        if (list == null || list.isEmpty()) {
            return null;
        }
        int i;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        CellLocation cellLocation = null;
        int i2 = 0;
        CellLocation cellLocation2 = null;
        for (int i3 = 0; i3 < list.size(); i3++) {
            Object obj = list.get(i3);
            if (obj != null) {
                try {
                    Class loadClass = systemClassLoader.loadClass("android.telephony.CellInfoGsm");
                    Class loadClass2 = systemClassLoader.loadClass("android.telephony.CellInfoWcdma");
                    Class loadClass3 = systemClassLoader.loadClass("android.telephony.CellInfoLte");
                    Class loadClass4 = systemClassLoader.loadClass("android.telephony.CellInfoCdma");
                    i = loadClass.isInstance(obj) ? 1 : loadClass2.isInstance(obj) ? 2 : loadClass3.isInstance(obj) ? 3 : loadClass4.isInstance(obj) ? 4 : 0;
                    if (i > 0) {
                        Object obj2 = null;
                        if (i == 1) {
                            try {
                                obj2 = loadClass.cast(obj);
                            } catch (Exception e) {
                                i2 = i;
                            }
                        } else if (i == 2) {
                            obj2 = loadClass2.cast(obj);
                        } else if (i == 3) {
                            obj2 = loadClass3.cast(obj);
                        } else if (i == 4) {
                            obj2 = loadClass4.cast(obj);
                        }
                        obj = C1100j.m4009a(obj2, "getCellIdentity", new Object[0]);
                        if (obj == null) {
                            i2 = i;
                        } else if (i == 4) {
                            cdmaCellLocation = new CdmaCellLocation();
                            try {
                                cdmaCellLocation.setCellLocationData(C1100j.m4016b(obj, "getBasestationId", new Object[0]), C1100j.m4016b(obj, "getLatitude", new Object[0]), C1100j.m4016b(obj, "getLongitude", new Object[0]), C1100j.m4016b(obj, "getSystemId", new Object[0]), C1100j.m4016b(obj, "getNetworkId", new Object[0]));
                                cellLocation2 = cellLocation;
                                break;
                            } catch (Exception e2) {
                                cellLocation2 = cdmaCellLocation;
                                i2 = i;
                            }
                        } else if (i == 3) {
                            r3 = C1100j.m4016b(obj, "getTac", new Object[0]);
                            r2 = C1100j.m4016b(obj, "getCi", new Object[0]);
                            cdmaCellLocation = new GsmCellLocation();
                            try {
                                cdmaCellLocation.setLacAndCid(r3, r2);
                                r12 = cellLocation2;
                                cellLocation2 = cdmaCellLocation;
                                cdmaCellLocation = r12;
                                break;
                            } catch (Exception e3) {
                                cellLocation = cdmaCellLocation;
                                i2 = i;
                            }
                        } else {
                            r3 = C1100j.m4016b(obj, "getLac", new Object[0]);
                            r2 = C1100j.m4016b(obj, "getCid", new Object[0]);
                            cdmaCellLocation = new GsmCellLocation();
                            try {
                                cdmaCellLocation.setLacAndCid(r3, r2);
                                r12 = cellLocation2;
                                cellLocation2 = cdmaCellLocation;
                                cdmaCellLocation = r12;
                                break;
                            } catch (Exception e4) {
                                cellLocation = cdmaCellLocation;
                                i2 = i;
                            }
                        }
                    } else {
                        i2 = i;
                    }
                } catch (Exception e5) {
                }
            }
        }
        i = i2;
        cdmaCellLocation = cellLocation2;
        cellLocation2 = cellLocation;
        return i != 4 ? cellLocation2 : cdmaCellLocation;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private C1072a m3863a(CellInfo cellInfo) {
        Object obj = null;
        int i = -1;
        int intValue = Integer.valueOf(VERSION.SDK_INT).intValue();
        if (intValue < 17) {
            return null;
        }
        C1072a c1072a = new C1072a();
        if (cellInfo instanceof CellInfoGsm) {
            CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
            c1072a.f2559c = m3861a(cellIdentity.getMcc());
            c1072a.f2560d = m3861a(cellIdentity.getMnc());
            c1072a.f2557a = m3861a(cellIdentity.getLac());
            c1072a.f2558b = m3861a(cellIdentity.getCid());
            c1072a.f2565i = 'g';
            c1072a.f2564h = ((CellInfoGsm) cellInfo).getCellSignalStrength().getAsuLevel();
            obj = 1;
        } else if (cellInfo instanceof CellInfoCdma) {
            CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
            c1072a.f2561e = cellIdentity2.getLatitude();
            c1072a.f2562f = cellIdentity2.getLongitude();
            c1072a.f2560d = m3861a(cellIdentity2.getSystemId());
            c1072a.f2557a = m3861a(cellIdentity2.getNetworkId());
            c1072a.f2558b = m3861a(cellIdentity2.getBasestationId());
            c1072a.f2565i = 'c';
            c1072a.f2564h = ((CellInfoCdma) cellInfo).getCellSignalStrength().getCdmaDbm();
            if (this.f2579f == null || this.f2579f.f2559c <= 0) {
                try {
                    String networkOperator = this.f2577d.getNetworkOperator();
                    if (networkOperator != null && networkOperator.length() > 0 && networkOperator.length() >= 3) {
                        r2 = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                        if (r2 < 0) {
                            r2 = -1;
                        }
                        i = r2;
                    }
                } catch (Exception e) {
                }
                if (i > 0) {
                    c1072a.f2559c = i;
                }
            } else {
                c1072a.f2559c = this.f2579f.f2559c;
            }
            r2 = 1;
        } else if (cellInfo instanceof CellInfoLte) {
            CellIdentityLte cellIdentity3 = ((CellInfoLte) cellInfo).getCellIdentity();
            c1072a.f2559c = m3861a(cellIdentity3.getMcc());
            c1072a.f2560d = m3861a(cellIdentity3.getMnc());
            c1072a.f2557a = m3861a(cellIdentity3.getTac());
            c1072a.f2558b = m3861a(cellIdentity3.getCi());
            c1072a.f2565i = 'g';
            c1072a.f2564h = ((CellInfoLte) cellInfo).getCellSignalStrength().getAsuLevel();
            r2 = 1;
        }
        if (intValue >= 18 && r2 == null) {
            try {
                if (cellInfo instanceof CellInfoWcdma) {
                    CellIdentityWcdma cellIdentity4 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                    c1072a.f2559c = m3861a(cellIdentity4.getMcc());
                    c1072a.f2560d = m3861a(cellIdentity4.getMnc());
                    c1072a.f2557a = m3861a(cellIdentity4.getLac());
                    c1072a.f2558b = m3861a(cellIdentity4.getCid());
                    c1072a.f2565i = 'g';
                    c1072a.f2564h = ((CellInfoWcdma) cellInfo).getCellSignalStrength().getAsuLevel();
                }
            } catch (Exception e2) {
            }
        }
        try {
            c1072a.f2563g = System.currentTimeMillis() - ((SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000);
        } catch (Error e3) {
            c1072a.f2563g = System.currentTimeMillis();
        }
        return c1072a;
    }

    /* renamed from: a */
    private C1072a m3864a(CellLocation cellLocation) {
        return m3865a(cellLocation, false);
    }

    /* renamed from: a */
    private C1072a m3865a(CellLocation cellLocation, boolean z) {
        int i = 0;
        if (cellLocation == null || this.f2577d == null) {
            return null;
        }
        C1072a c1072a = new C1072a();
        if (z) {
            c1072a.m3859f();
        }
        c1072a.f2563g = System.currentTimeMillis();
        try {
            String networkOperator = this.f2577d.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                if (networkOperator.length() >= 3) {
                    int intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                    if (intValue < 0) {
                        intValue = this.f2579f.f2559c;
                    }
                    c1072a.f2559c = intValue;
                }
                String substring = networkOperator.substring(3);
                if (substring != null) {
                    char[] toCharArray = substring.toCharArray();
                    while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                        i++;
                    }
                }
                i = Integer.valueOf(substring.substring(0, i)).intValue();
                if (i < 0) {
                    i = this.f2579f.f2560d;
                }
                c1072a.f2560d = i;
            }
            f2568a = this.f2577d.getSimState();
        } catch (Exception e) {
            f2569b = 1;
        }
        if (cellLocation instanceof GsmCellLocation) {
            c1072a.f2557a = ((GsmCellLocation) cellLocation).getLac();
            c1072a.f2558b = ((GsmCellLocation) cellLocation).getCid();
            c1072a.f2565i = 'g';
        } else if (cellLocation instanceof CdmaCellLocation) {
            c1072a.f2565i = 'c';
            if (Integer.valueOf(VERSION.SDK_INT).intValue() < 5) {
                return c1072a;
            }
            if (f2576p == null) {
                try {
                    f2576p = Class.forName("android.telephony.cdma.CdmaCellLocation");
                    f2571k = f2576p.getMethod("getBaseStationId", new Class[0]);
                    f2572l = f2576p.getMethod("getNetworkId", new Class[0]);
                    f2573m = f2576p.getMethod("getSystemId", new Class[0]);
                    f2574n = f2576p.getMethod("getBaseStationLatitude", new Class[0]);
                    f2575o = f2576p.getMethod("getBaseStationLongitude", new Class[0]);
                } catch (Exception e2) {
                    f2576p = null;
                    f2569b = 2;
                    return c1072a;
                }
            }
            if (f2576p != null && f2576p.isInstance(cellLocation)) {
                try {
                    int intValue2 = ((Integer) f2573m.invoke(cellLocation, new Object[0])).intValue();
                    if (intValue2 < 0) {
                        intValue2 = this.f2579f.f2560d;
                    }
                    c1072a.f2560d = intValue2;
                    c1072a.f2558b = ((Integer) f2571k.invoke(cellLocation, new Object[0])).intValue();
                    c1072a.f2557a = ((Integer) f2572l.invoke(cellLocation, new Object[0])).intValue();
                    Object invoke = f2574n.invoke(cellLocation, new Object[0]);
                    if (((Integer) invoke).intValue() < Integer.MAX_VALUE) {
                        c1072a.f2561e = ((Integer) invoke).intValue();
                    }
                    invoke = f2575o.invoke(cellLocation, new Object[0]);
                    if (((Integer) invoke).intValue() < Integer.MAX_VALUE) {
                        c1072a.f2562f = ((Integer) invoke).intValue();
                    }
                } catch (Exception e3) {
                    f2569b = 3;
                    return c1072a;
                }
            }
        }
        m3869c(c1072a);
        return c1072a;
    }

    /* renamed from: a */
    public static synchronized C1074b m3866a() {
        C1074b c1074b;
        synchronized (C1074b.class) {
            if (f2570c == null) {
                f2570c = new C1074b();
            }
            c1074b = f2570c;
        }
        return c1074b;
    }

    /* renamed from: c */
    private void m3869c(C1072a c1072a) {
        if (!c1072a.m3855b()) {
            return;
        }
        if (this.f2579f == null || !this.f2579f.m3854a(c1072a)) {
            this.f2579f = c1072a;
            if (c1072a.m3855b()) {
                int size = this.f2581h.size();
                C1072a c1072a2 = size == 0 ? null : (C1072a) this.f2581h.get(size - 1);
                if (c1072a2 == null || c1072a2.f2558b != this.f2579f.f2558b || c1072a2.f2557a != this.f2579f.f2557a) {
                    this.f2581h.add(this.f2579f);
                    if (this.f2581h.size() > 3) {
                        this.f2581h.remove(0);
                    }
                    m3872j();
                    this.f2584q = false;
                }
            } else if (this.f2581h != null) {
                this.f2581h.clear();
            }
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: d */
    private String m3870d(C1072a c1072a) {
        StringBuilder stringBuilder = new StringBuilder();
        if (Integer.valueOf(VERSION.SDK_INT).intValue() >= 17) {
            try {
                List<CellInfo> allCellInfo = this.f2577d.getAllCellInfo();
                if (allCellInfo != null && allCellInfo.size() > 0) {
                    stringBuilder.append("&nc=");
                    for (CellInfo cellInfo : allCellInfo) {
                        if (!cellInfo.isRegistered()) {
                            C1072a a = m3863a(cellInfo);
                            if (!(a == null || a.f2557a == -1 || a.f2558b == -1)) {
                                if (c1072a.f2557a != a.f2557a) {
                                    stringBuilder.append(a.f2557a + "|" + a.f2558b + "|" + a.f2564h + C0880h.f2220b);
                                } else {
                                    stringBuilder.append("|" + a.f2558b + "|" + a.f2564h + C0880h.f2220b);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
            } catch (NoSuchMethodError e2) {
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: i */
    private void m3871i() {
        String f = C1100j.m4024f();
        if (f != null) {
            File file = new File(f + File.separator + "lcvif.dat");
            if (file.exists()) {
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(0);
                    if (System.currentTimeMillis() - randomAccessFile.readLong() > ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
                        randomAccessFile.close();
                        file.delete();
                        return;
                    }
                    randomAccessFile.readInt();
                    for (int i = 0; i < 3; i++) {
                        long readLong = randomAccessFile.readLong();
                        int readInt = randomAccessFile.readInt();
                        int readInt2 = randomAccessFile.readInt();
                        int readInt3 = randomAccessFile.readInt();
                        int readInt4 = randomAccessFile.readInt();
                        int readInt5 = randomAccessFile.readInt();
                        char c = '\u0000';
                        if (readInt5 == 1) {
                            c = 'g';
                        }
                        if (readInt5 == 2) {
                            c = 'c';
                        }
                        if (readLong != 0) {
                            C1072a c1072a = new C1072a(readInt3, readInt4, readInt, readInt2, 0, c);
                            c1072a.f2563g = readLong;
                            if (c1072a.m3855b()) {
                                this.f2584q = true;
                                this.f2581h.add(c1072a);
                            }
                        }
                    }
                    randomAccessFile.close();
                } catch (Exception e) {
                    file.delete();
                }
            }
        }
    }

    /* renamed from: j */
    private void m3872j() {
        int i = 0;
        if (this.f2581h != null || this.f2580g != null) {
            if (this.f2581h == null && this.f2580g != null) {
                this.f2581h = new LinkedList();
                this.f2581h.add(this.f2580g);
            }
            String f = C1100j.m4024f();
            if (f != null) {
                File file = new File(f + File.separator + "lcvif.dat");
                int size = this.f2581h.size();
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(0);
                    randomAccessFile.writeLong(((C1072a) this.f2581h.get(size - 1)).f2563g);
                    randomAccessFile.writeInt(size);
                    for (int i2 = 0; i2 < 3 - size; i2++) {
                        randomAccessFile.writeLong(0);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(-1);
                        randomAccessFile.writeInt(2);
                    }
                    while (i < size) {
                        randomAccessFile.writeLong(((C1072a) this.f2581h.get(i)).f2563g);
                        randomAccessFile.writeInt(((C1072a) this.f2581h.get(i)).f2559c);
                        randomAccessFile.writeInt(((C1072a) this.f2581h.get(i)).f2560d);
                        randomAccessFile.writeInt(((C1072a) this.f2581h.get(i)).f2557a);
                        randomAccessFile.writeInt(((C1072a) this.f2581h.get(i)).f2558b);
                        if (((C1072a) this.f2581h.get(i)).f2565i == 'g') {
                            randomAccessFile.writeInt(1);
                        } else if (((C1072a) this.f2581h.get(i)).f2565i == 'c') {
                            randomAccessFile.writeInt(2);
                        } else {
                            randomAccessFile.writeInt(3);
                        }
                        i++;
                    }
                    randomAccessFile.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /* renamed from: k */
    private void m3873k() {
        C1072a n = m3876n();
        if (n != null) {
            m3869c(n);
        }
        if (n == null || !n.m3855b()) {
            n = m3864a(this.f2577d.getCellLocation());
            if (n == null || !n.m3855b()) {
                CellLocation l = m3874l();
                if (l != null) {
                    Log.i(C1090a.f2659a, "cell sim2 cell is valid");
                    m3865a(l, true);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: l */
    private android.telephony.CellLocation m3874l() {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.f2578e;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r2 = r7.m3875m();	 Catch:{ Exception -> 0x0066 }
        r3 = r2.isInstance(r0);	 Catch:{ Exception -> 0x0066 }
        if (r3 == 0) goto L_0x0073;
    L_0x0010:
        r2 = r2.cast(r0);	 Catch:{ Exception -> 0x0066 }
        r3 = "getCellLocation";
        r0 = 0;
        r0 = new java.lang.Object[r0];	 Catch:{ NoSuchMethodException -> 0x005a, Exception -> 0x005d }
        r0 = com.baidu.location.p042d.C1100j.m4009a(r2, r3, r0);	 Catch:{ NoSuchMethodException -> 0x005a, Exception -> 0x005d }
    L_0x001d:
        if (r0 != 0) goto L_0x002e;
    L_0x001f:
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ NoSuchMethodException -> 0x006f, Exception -> 0x006d }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ NoSuchMethodException -> 0x006f, Exception -> 0x006d }
        r4[r5] = r6;	 Catch:{ NoSuchMethodException -> 0x006f, Exception -> 0x006d }
        r0 = com.baidu.location.p042d.C1100j.m4009a(r2, r3, r4);	 Catch:{ NoSuchMethodException -> 0x006f, Exception -> 0x006d }
    L_0x002e:
        if (r0 != 0) goto L_0x0041;
    L_0x0030:
        r3 = "getCellLocationGemini";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ NoSuchMethodException -> 0x006b, Exception -> 0x0069 }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ NoSuchMethodException -> 0x006b, Exception -> 0x0069 }
        r4[r5] = r6;	 Catch:{ NoSuchMethodException -> 0x006b, Exception -> 0x0069 }
        r0 = com.baidu.location.p042d.C1100j.m4009a(r2, r3, r4);	 Catch:{ NoSuchMethodException -> 0x006b, Exception -> 0x0069 }
    L_0x0041:
        if (r0 != 0) goto L_0x0054;
    L_0x0043:
        r0 = "getAllCellInfo";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ NoSuchMethodException -> 0x0060, Exception -> 0x0063 }
        r0 = com.baidu.location.p042d.C1100j.m4009a(r2, r0, r3);	 Catch:{ NoSuchMethodException -> 0x0060, Exception -> 0x0063 }
        r0 = (java.util.List) r0;	 Catch:{ NoSuchMethodException -> 0x0060, Exception -> 0x0063 }
    L_0x004e:
        r0 = r7.m3862a(r0);	 Catch:{ Exception -> 0x0066 }
        if (r0 == 0) goto L_0x0054;
    L_0x0054:
        if (r0 == 0) goto L_0x0071;
    L_0x0056:
        r0 = (android.telephony.CellLocation) r0;	 Catch:{ Exception -> 0x0066 }
    L_0x0058:
        r1 = r0;
        goto L_0x0005;
    L_0x005a:
        r0 = move-exception;
        r0 = r1;
        goto L_0x001d;
    L_0x005d:
        r0 = move-exception;
        r0 = r1;
        goto L_0x001d;
    L_0x0060:
        r0 = move-exception;
        r0 = r1;
        goto L_0x004e;
    L_0x0063:
        r0 = move-exception;
        r0 = r1;
        goto L_0x004e;
    L_0x0066:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0058;
    L_0x0069:
        r3 = move-exception;
        goto L_0x0041;
    L_0x006b:
        r3 = move-exception;
        goto L_0x0041;
    L_0x006d:
        r3 = move-exception;
        goto L_0x002e;
    L_0x006f:
        r3 = move-exception;
        goto L_0x002e;
    L_0x0071:
        r0 = r1;
        goto L_0x0058;
    L_0x0073:
        r0 = r1;
        goto L_0x0054;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.b.l():android.telephony.CellLocation");
    }

    /* renamed from: m */
    private Class<?> m3875m() {
        String str;
        Class<?> cls = null;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        switch (m3877o()) {
            case 0:
                str = "android.telephony.TelephonyManager";
                break;
            case 1:
                str = "android.telephony.MSimTelephonyManager";
                break;
            case 2:
                str = "android.telephony.TelephonyManager2";
                break;
            default:
                str = cls;
                break;
        }
        try {
            cls = systemClassLoader.loadClass(str);
        } catch (Exception e) {
        }
        return cls;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: n */
    private com.baidu.location.p043b.C1072a m3876n() {
        /*
        r5 = this;
        r1 = 0;
        r0 = android.os.Build.VERSION.SDK_INT;
        r0 = java.lang.Integer.valueOf(r0);
        r0 = r0.intValue();
        r2 = 17;
        if (r0 >= r2) goto L_0x0010;
    L_0x000f:
        return r1;
    L_0x0010:
        r0 = r5.f2577d;	 Catch:{ Exception -> 0x0048, NoSuchMethodError -> 0x0046 }
        r0 = r0.getAllCellInfo();	 Catch:{ Exception -> 0x0048, NoSuchMethodError -> 0x0046 }
        if (r0 == 0) goto L_0x000f;
    L_0x0018:
        r2 = r0.size();	 Catch:{ Exception -> 0x0048, NoSuchMethodError -> 0x0046 }
        if (r2 <= 0) goto L_0x000f;
    L_0x001e:
        r3 = r0.iterator();	 Catch:{ Exception -> 0x0048, NoSuchMethodError -> 0x0046 }
        r2 = r1;
    L_0x0023:
        r0 = r3.hasNext();	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x0046 }
        if (r0 == 0) goto L_0x0050;
    L_0x0029:
        r0 = r3.next();	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x0046 }
        r0 = (android.telephony.CellInfo) r0;	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x0046 }
        r4 = r0.isRegistered();	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x0046 }
        if (r4 == 0) goto L_0x0023;
    L_0x0035:
        r0 = r5.m3863a(r0);	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x0046 }
        if (r0 != 0) goto L_0x003d;
    L_0x003b:
        r2 = r0;
        goto L_0x0023;
    L_0x003d:
        r2 = r0.m3855b();	 Catch:{ Exception -> 0x004d, NoSuchMethodError -> 0x0046 }
        if (r2 != 0) goto L_0x0044;
    L_0x0043:
        r0 = r1;
    L_0x0044:
        r1 = r0;
        goto L_0x000f;
    L_0x0046:
        r0 = move-exception;
        goto L_0x000f;
    L_0x0048:
        r0 = move-exception;
        goto L_0x000f;
    L_0x004a:
        r0 = move-exception;
        r1 = r2;
        goto L_0x000f;
    L_0x004d:
        r1 = move-exception;
        r1 = r0;
        goto L_0x000f;
    L_0x0050:
        r1 = r2;
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.b.n():com.baidu.location.b.a");
    }

    /* renamed from: o */
    private int m3877o() {
        int i = 0;
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            i = 1;
        } catch (Exception e) {
        }
        if (i != 0) {
            return i;
        }
        try {
            Class.forName("android.telephony.TelephonyManager2");
            return 2;
        } catch (Exception e2) {
            return i;
        }
    }

    /* renamed from: a */
    public String m3878a(C1072a c1072a) {
        String str = "";
        try {
            str = m3870d(c1072a);
            if (str != null && !str.equals("") && !str.equals("&nc=")) {
                return str;
            }
            List<NeighboringCellInfo> neighboringCellInfo = this.f2577d.getNeighboringCellInfo();
            if (neighboringCellInfo != null && !neighboringCellInfo.isEmpty()) {
                String str2 = "&nc=";
                int i = 0;
                for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                    int lac = neighboringCellInfo2.getLac();
                    str = (lac == -1 || neighboringCellInfo2.getCid() == -1) ? str2 : c1072a.f2557a != lac ? str2 + lac + "|" + neighboringCellInfo2.getCid() + "|" + neighboringCellInfo2.getRssi() + C0880h.f2220b : str2 + "|" + neighboringCellInfo2.getCid() + "|" + neighboringCellInfo2.getRssi() + C0880h.f2220b;
                    int i2 = i + 1;
                    if (i2 >= 8) {
                        break;
                    }
                    i = i2;
                    str2 = str;
                }
                str = str2;
            }
            return (str == null || !str.equals("&nc=")) ? str : null;
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
    }

    /* renamed from: b */
    public String m3879b(C1072a c1072a) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw=");
        stringBuffer.append(c1072a.f2565i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d", new Object[]{Integer.valueOf(c1072a.f2559c), Integer.valueOf(c1072a.f2560d), Integer.valueOf(c1072a.f2557a), Integer.valueOf(c1072a.f2558b), Integer.valueOf(c1072a.f2564h)}));
        if (c1072a.f2561e < Integer.MAX_VALUE && c1072a.f2562f < Integer.MAX_VALUE) {
            stringBuffer.append(String.format(Locale.CHINA, "&cdmall=%.6f|%.6f", new Object[]{Double.valueOf(((double) c1072a.f2562f) / 14400.0d), Double.valueOf(((double) c1072a.f2561e) / 14400.0d)}));
        }
        stringBuffer.append("&cl_t=");
        stringBuffer.append(c1072a.f2563g);
        if (this.f2581h != null && this.f2581h.size() > 0) {
            int size = this.f2581h.size();
            stringBuffer.append("&clt=");
            for (int i = 0; i < size; i++) {
                C1072a c1072a2 = (C1072a) this.f2581h.get(i);
                if (c1072a2.f2559c != c1072a.f2559c) {
                    stringBuffer.append(c1072a2.f2559c);
                }
                stringBuffer.append("|");
                if (c1072a2.f2560d != c1072a.f2560d) {
                    stringBuffer.append(c1072a2.f2560d);
                }
                stringBuffer.append("|");
                if (c1072a2.f2557a != c1072a.f2557a) {
                    stringBuffer.append(c1072a2.f2557a);
                }
                stringBuffer.append("|");
                if (c1072a2.f2558b != c1072a.f2558b) {
                    stringBuffer.append(c1072a2.f2558b);
                }
                stringBuffer.append("|");
                stringBuffer.append((System.currentTimeMillis() - c1072a2.f2563g) / 1000);
                stringBuffer.append(C0880h.f2220b);
            }
        }
        if (f2568a > 100) {
            f2568a = 0;
        }
        stringBuffer.append("&cs=" + ((f2569b << 8) + f2568a));
        return stringBuffer.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    public synchronized void m3880b() {
        /*
        r3 = this;
        r1 = 1;
        monitor-enter(r3);
        r0 = r3.f2583j;	 Catch:{ all -> 0x0047 }
        if (r0 != r1) goto L_0x0008;
    L_0x0006:
        monitor-exit(r3);
        return;
    L_0x0008:
        r0 = com.baidu.location.C1102f.isServing;	 Catch:{ all -> 0x0047 }
        if (r0 == 0) goto L_0x0006;
    L_0x000c:
        r0 = com.baidu.location.C1102f.getServiceContext();	 Catch:{ all -> 0x0047 }
        r1 = "phone";
        r0 = r0.getSystemService(r1);	 Catch:{ all -> 0x0047 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ all -> 0x0047 }
        r3.f2577d = r0;	 Catch:{ all -> 0x0047 }
        r0 = new java.util.LinkedList;	 Catch:{ all -> 0x0047 }
        r0.<init>();	 Catch:{ all -> 0x0047 }
        r3.f2581h = r0;	 Catch:{ all -> 0x0047 }
        r0 = new com.baidu.location.b.b$a;	 Catch:{ all -> 0x0047 }
        r0.<init>(r3);	 Catch:{ all -> 0x0047 }
        r3.f2582i = r0;	 Catch:{ all -> 0x0047 }
        r3.m3871i();	 Catch:{ all -> 0x0047 }
        r0 = r3.f2577d;	 Catch:{ all -> 0x0047 }
        if (r0 == 0) goto L_0x0006;
    L_0x002f:
        r0 = r3.f2582i;	 Catch:{ all -> 0x0047 }
        if (r0 == 0) goto L_0x0006;
    L_0x0033:
        r0 = r3.f2577d;	 Catch:{ Exception -> 0x0076 }
        r1 = r3.f2582i;	 Catch:{ Exception -> 0x0076 }
        r2 = 272; // 0x110 float:3.81E-43 double:1.344E-321;
        r0.listen(r1, r2);	 Catch:{ Exception -> 0x0076 }
    L_0x003c:
        r0 = r3.m3877o();	 Catch:{ Throwable -> 0x0057 }
        switch(r0) {
            case 0: goto L_0x0069;
            case 1: goto L_0x004a;
            case 2: goto L_0x005c;
            default: goto L_0x0043;
        };
    L_0x0043:
        r0 = 1;
        r3.f2583j = r0;	 Catch:{ all -> 0x0047 }
        goto L_0x0006;
    L_0x0047:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x004a:
        r0 = com.baidu.location.C1102f.getServiceContext();	 Catch:{ Throwable -> 0x0057 }
        r1 = "phone_msim";
        r0 = com.baidu.location.p042d.C1100j.m4008a(r0, r1);	 Catch:{ Throwable -> 0x0057 }
        r3.f2578e = r0;	 Catch:{ Throwable -> 0x0057 }
        goto L_0x0043;
    L_0x0057:
        r0 = move-exception;
        r0 = 0;
        r3.f2578e = r0;	 Catch:{ all -> 0x0047 }
        goto L_0x0043;
    L_0x005c:
        r0 = com.baidu.location.C1102f.getServiceContext();	 Catch:{ Throwable -> 0x0057 }
        r1 = "phone2";
        r0 = com.baidu.location.p042d.C1100j.m4008a(r0, r1);	 Catch:{ Throwable -> 0x0057 }
        r3.f2578e = r0;	 Catch:{ Throwable -> 0x0057 }
        goto L_0x0043;
    L_0x0069:
        r0 = com.baidu.location.C1102f.getServiceContext();	 Catch:{ Throwable -> 0x0057 }
        r1 = "phone2";
        r0 = com.baidu.location.p042d.C1100j.m4008a(r0, r1);	 Catch:{ Throwable -> 0x0057 }
        r3.f2578e = r0;	 Catch:{ Throwable -> 0x0057 }
        goto L_0x0043;
    L_0x0076:
        r0 = move-exception;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.b.b():void");
    }

    /* renamed from: c */
    public synchronized void m3881c() {
        if (this.f2583j) {
            if (!(this.f2582i == null || this.f2577d == null)) {
                this.f2577d.listen(this.f2582i, 0);
            }
            this.f2582i = null;
            this.f2577d = null;
            this.f2581h.clear();
            this.f2581h = null;
            m3872j();
            this.f2583j = false;
        }
    }

    /* renamed from: d */
    public boolean m3882d() {
        return this.f2584q;
    }

    /* renamed from: e */
    public int m3883e() {
        int i = 0;
        if (this.f2577d != null) {
            try {
                i = this.f2577d.getNetworkType();
            } catch (Exception e) {
            }
        }
        return i;
    }

    /* renamed from: f */
    public C1072a m3884f() {
        if (!((this.f2579f != null && this.f2579f.m3853a() && this.f2579f.m3855b()) || this.f2577d == null)) {
            try {
                m3873k();
            } catch (Exception e) {
            }
        }
        if (this.f2579f.m3858e()) {
            this.f2580g = null;
            this.f2580g = new C1072a(this.f2579f.f2557a, this.f2579f.f2558b, this.f2579f.f2559c, this.f2579f.f2560d, this.f2579f.f2564h, this.f2579f.f2565i);
        }
        if (this.f2579f.m3857d() && this.f2580g != null && this.f2579f.f2565i == 'g') {
            this.f2579f.f2560d = this.f2580g.f2560d;
            this.f2579f.f2559c = this.f2580g.f2559c;
        }
        return this.f2579f;
    }

    /* renamed from: g */
    public String m3885g() {
        int i = -1;
        try {
            if (this.f2577d != null) {
                i = this.f2577d.getSimState();
            }
        } catch (Exception e) {
        }
        return "&sim=" + i;
    }

    /* renamed from: h */
    public int m3886h() {
        String subscriberId;
        try {
            subscriberId = ((TelephonyManager) C1102f.getServiceContext().getSystemService(UserData.PHONE_KEY)).getSubscriberId();
        } catch (Exception e) {
            subscriberId = null;
        }
        if (subscriberId != null) {
            if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002") || subscriberId.startsWith("46007")) {
                return 1;
            }
            if (subscriberId.startsWith("46001")) {
                return 2;
            }
            if (subscriberId.startsWith("46003")) {
                return 3;
            }
        }
        return 0;
    }
}
