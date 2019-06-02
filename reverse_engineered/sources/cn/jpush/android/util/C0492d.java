package cn.jpush.android.util;

import android.content.Context;
import cn.jpush.android.service.C0479r;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.util.d */
public final class C0492d extends Thread {
    /* renamed from: c */
    private static Object f1014c = new Object();
    /* renamed from: d */
    private static Object f1015d = new Object();
    /* renamed from: z */
    private static final String[] f1016z;
    /* renamed from: a */
    private String f1017a;
    /* renamed from: b */
    private Context f1018b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 17;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u001c\u0010YX\u0013SNYu\u001e\u0018\r^|\u0019SLA`]\u0003LR{\u001c\u0014H\u0011|\u0014\u0000Y\u000b";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x00e7;
            case 1: goto L_0x00eb;
            case 2: goto L_0x00ef;
            case 3: goto L_0x00f3;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 16;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u001e\u001bHR{(\u0000HCQ\r\u0003^\u0011u\u0005\u0016NAd\u0014\u001cC\u000b";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u000e\u0007BCu]\u0012]A@\u001c\u0010FPw\u0018=L\\u\u000eI";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "[U";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u001c\u0003]aq\u001e\u0018LVu3\u0012@Tc";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u000e\u0012[T0\u0011\u001cJ\u0011y\u0013SZCy\t\u0016eXc\t\u001c_H\\\u0012\u0014\u0017;";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = ";\u001aAT`\u001c\u0007E\u0011u\u000f\u0001BC0\u0012\u0015\rjq\r\u0003}Ps\u0016\u0012JT^\u001c\u001eHBM]_\rVy\u000b\u0016\rD`]\u0000LGu]I";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "('k\u001c(";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u001e\u0012C\u0016d]\u0004_Xd\u0018SLA`-\u0012NZq\u001a\u0016cP}\u0018\u0000\r\u001d0\u001a\u001a[T0\b\u0003\rBq\u000b\u0016\r\u000b";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u001e\u0012C\u0016d]\u0016CR\u0019\u001aCV0\u001c\u0003]aq\u001e\u0018LVu3\u0012@Tc]_\rVy\u000b\u0016\rD`]\u0000LGu]I";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u001e\u0012C\u0016d]\u001c]T~]\u0012]A@\u001c\u0010FPw\u0018=L\\u\u000eSBDd\r\u0006Ybd\u000f\u0016L\\<]\u0014DGu]\u0006]\u0011c\u001c\u0005H\u0011*";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u001e\u0012C\u0016d]\u001c]T~]\u0012]A@\u001c\u0010FPw\u0018=L\\u\u000eSD_`\b\u0007~Eb\u0018\u0012@\u001d0\u001a\u001a[T0\b\u0003\rCu\u001c\u0017\r\u0011*";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u001e\u0012C\u0016d]\u0016CR\u0019\u001aCV0\u001c\u0003]aq\u001e\u0018LVu3\u0012@TcQSJXf\u0018SXA0\u000f\u0016LU0G";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u001e\u0012C\u0016d]\u0001HPt]\u0012]A@\u001c\u0010FPw\u0018=L\\u\u000e_\rVy\u000b\u0016\rD`]\u0001HPt]I";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "wSD_c\t\u0012A]u\u0019SLA`-\u0012NZq\u001a\u0016cP}\u0018I";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "/\u0016]^b\t&^Tb(\u0003IPd\u00182]Ac]\u001cAUQ\r\u0003AXc\t]^Xj\u0018[\u0004\u000b";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\b\u001dD_c\t\u0012A]u\u0019SLA`-\u0012NZq\u001a\u0016cP}\u0018I";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        f1016z = r4;
        r0 = new java.lang.Object;
        r0.<init>();
        f1014c = r0;
        r0 = new java.lang.Object;
        r0.<init>();
        f1015d = r0;
        return;
    L_0x00e7:
        r9 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        goto L_0x0020;
    L_0x00eb:
        r9 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        goto L_0x0020;
    L_0x00ef:
        r9 = 45;
        goto L_0x0020;
    L_0x00f3:
        r9 = 49;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.d.<clinit>():void");
    }

    public C0492d(Context context, String str) {
        this.f1018b = context;
        this.f1017a = str;
    }

    /* renamed from: a */
    private static String m1744a(Context context) {
        FileNotFoundException e;
        Throwable th;
        IOException e2;
        if (context == null) {
            ac.m1586d();
            return null;
        }
        Closeable openFileInput;
        try {
            openFileInput = context.openFileInput(f1016z[4]);
            try {
                byte[] bArr = new byte[(openFileInput.available() + 1)];
                openFileInput.read(bArr);
                ah.m1624a(openFileInput);
                try {
                    String str = new String(bArr, f1016z[7]);
                    if (!an.m1657a(str)) {
                        return str;
                    }
                    ac.m1581b();
                    return null;
                } catch (UnsupportedEncodingException e3) {
                    new StringBuilder(f1016z[12]).append(e3.getMessage());
                    ac.m1581b();
                    return null;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                try {
                    new StringBuilder(f1016z[11]).append(e.getMessage());
                    ac.m1581b();
                    ah.m1624a(openFileInput);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    ah.m1624a(openFileInput);
                    throw th;
                }
            } catch (IOException e5) {
                e2 = e5;
                new StringBuilder(f1016z[13]).append(e2.getMessage());
                ac.m1581b();
                ah.m1624a(openFileInput);
                return null;
            }
        } catch (FileNotFoundException e6) {
            e = e6;
            openFileInput = null;
            new StringBuilder(f1016z[11]).append(e.getMessage());
            ac.m1581b();
            ah.m1624a(openFileInput);
            return null;
        } catch (IOException e7) {
            e2 = e7;
            openFileInput = null;
            new StringBuilder(f1016z[13]).append(e2.getMessage());
            ac.m1581b();
            ah.m1624a(openFileInput);
            return null;
        } catch (Throwable th3) {
            openFileInput = null;
            th = th3;
            ah.m1624a(openFileInput);
            throw th;
        }
    }

    /* renamed from: a */
    private static HashSet<String> m1745a(String str) {
        if (str == null) {
            ac.m1576a();
        }
        String[] split = str.replace("\u0000", "").split(f1016z[3]);
        HashSet<String> hashSet = new HashSet();
        for (Object add : split) {
            hashSet.add(add);
        }
        return hashSet;
    }

    /* renamed from: a */
    private void m1746a(ArrayList<ad> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            ac.m1576a();
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            stringBuilder.append(((ad) arrayList.get(i)).f949b);
            if (i != arrayList.size() - 1) {
                stringBuilder.append(f1016z[3]);
            }
        }
        new StringBuilder(f1016z[2]).append(stringBuilder.toString());
        ac.m1581b();
        if (C0492d.m1748a(this.f1018b, stringBuilder.toString())) {
            ac.m1581b();
        }
    }

    /* renamed from: a */
    private void m1747a(HashSet<String> hashSet) {
        if (this.f1018b == null) {
            ac.m1586d();
        } else if (hashSet == null || hashSet.isEmpty()) {
            ac.m1586d();
        } else {
            JSONObject b;
            new StringBuilder(f1016z[15]).append(hashSet.size());
            ac.m1581b();
            JSONArray jSONArray = new JSONArray();
            ArrayList a = C0508v.m1809a(this.f1018b, true);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a);
            Iterator it = a.iterator();
            while (it.hasNext()) {
                ad adVar = (ad) it.next();
                if (hashSet.remove(adVar.f949b)) {
                    arrayList.remove(adVar);
                }
                if (!an.m1657a(this.f1017a) && adVar.f949b.equals(this.f1017a)) {
                    arrayList.remove(adVar);
                }
            }
            if (!an.m1657a(this.f1017a)) {
                hashSet.remove(this.f1017a);
            }
            new StringBuilder(f1016z[16]).append(hashSet.toString()).append(f1016z[14]).append(arrayList.toString());
            ac.m1581b();
            it = hashSet.iterator();
            while (it.hasNext()) {
                b = C0479r.m1535b((String) it.next());
                if (b != null) {
                    jSONArray.put(b);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                b = C0479r.m1533a(((ad) it2.next()).f949b);
                if (b != null) {
                    jSONArray.put(b);
                }
            }
            if (jSONArray.length() > 0) {
                ah.m1620a(this.f1018b, jSONArray);
            }
            if (jSONArray.length() > 0 || !an.m1657a(this.f1017a)) {
                m1746a(a);
            }
        }
    }

    /* renamed from: a */
    private static boolean m1748a(Context context, String str) {
        if (context == null) {
            ac.m1586d();
            return false;
        }
        synchronized (f1015d) {
            if (str != null) {
                try {
                    new StringBuilder(f1016z[5]).append(str);
                    ac.m1576a();
                } catch (Exception e) {
                    ac.m1592h();
                }
            }
            Closeable closeable = null;
            try {
                closeable = context.openFileOutput(f1016z[4], 0);
                closeable.write(str.getBytes(f1016z[7]));
            } catch (FileNotFoundException e2) {
                new StringBuilder(f1016z[10]).append(e2.getMessage());
                ac.m1581b();
                return false;
            } catch (UnsupportedEncodingException e3) {
                new StringBuilder(f1016z[9]).append(e3.getMessage());
                ac.m1581b();
                return false;
            } catch (IOException e4) {
                new StringBuilder(f1016z[8]).append(e4.getMessage());
                ac.m1581b();
                return false;
            } catch (NullPointerException e5) {
                new StringBuilder(f1016z[6]).append(e5.getMessage());
                ac.m1581b();
                return false;
            } finally {
                ah.m1624a(closeable);
            }
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r6 = this;
        r5 = 1;
        r1 = f1014c;	 Catch:{ Exception -> 0x0034 }
        monitor-enter(r1);	 Catch:{ Exception -> 0x0034 }
        r0 = r6.f1018b;	 Catch:{ all -> 0x0031 }
        if (r0 != 0) goto L_0x000d;
    L_0x0008:
        cn.jpush.android.util.ac.m1586d();	 Catch:{ all -> 0x0031 }
        monitor-exit(r1);	 Catch:{ all -> 0x0031 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = r6.f1018b;	 Catch:{ all -> 0x0031 }
        r0 = cn.jpush.android.util.C0492d.m1744a(r0);	 Catch:{ all -> 0x0031 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0031 }
        r3 = f1016z;	 Catch:{ all -> 0x0031 }
        r4 = 0;
        r3 = r3[r4];	 Catch:{ all -> 0x0031 }
        r2.<init>(r3);	 Catch:{ all -> 0x0031 }
        r2.append(r0);	 Catch:{ all -> 0x0031 }
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x0031 }
        if (r0 != 0) goto L_0x004c;
    L_0x0025:
        r0 = r6.f1018b;	 Catch:{ all -> 0x0031 }
        r2 = 1;
        r0 = cn.jpush.android.util.C0508v.m1809a(r0, r2);	 Catch:{ all -> 0x0031 }
        r6.m1746a(r0);	 Catch:{ all -> 0x0031 }
    L_0x002f:
        monitor-exit(r1);	 Catch:{ all -> 0x0031 }
        goto L_0x000c;
    L_0x0031:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0031 }
        throw r0;	 Catch:{ Exception -> 0x0034 }
    L_0x0034:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = new java.lang.StringBuilder;
        r2 = f1016z;
        r2 = r2[r5];
        r1.<init>(r2);
        r0 = r0.getMessage();
        r1.append(r0);
        cn.jpush.android.util.ac.m1581b();
        goto L_0x000c;
    L_0x004c:
        r2 = cn.jpush.android.C0404a.m1145y();	 Catch:{ all -> 0x0031 }
        if (r2 != 0) goto L_0x0054;
    L_0x0052:
        monitor-exit(r1);	 Catch:{ all -> 0x0031 }
        goto L_0x000c;
    L_0x0054:
        cn.jpush.android.C0404a.m1099e();	 Catch:{ all -> 0x0031 }
        r0 = cn.jpush.android.util.C0492d.m1745a(r0);	 Catch:{ all -> 0x0031 }
        r6.m1747a(r0);	 Catch:{ all -> 0x0031 }
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.d.run():void");
    }
}
