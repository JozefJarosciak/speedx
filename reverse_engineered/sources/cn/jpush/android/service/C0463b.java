package cn.jpush.android.service;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import cn.jpush.android.C0450g;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0437i;
import cn.jpush.android.data.C0447s;
import cn.jpush.android.util.C0498k;
import cn.jpush.android.util.C0503p;
import cn.jpush.android.util.C0505r;
import cn.jpush.android.util.C0506s;
import cn.jpush.android.util.ac;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.HttpStatus;

/* renamed from: cn.jpush.android.service.b */
public final class C0463b {
    /* renamed from: b */
    public static boolean f835b = true;
    /* renamed from: z */
    private static final String[] f836z;
    /* renamed from: a */
    public boolean f837a = false;
    /* renamed from: c */
    private C0464c f838c = null;
    /* renamed from: d */
    private long f839d = 0;
    /* renamed from: e */
    private long f840e = 0;
    /* renamed from: f */
    private Bundle f841f;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 22;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "C!Z^fL!\u000e\u0018{H!\u000e\n}P%B^~A*I\nz\u0004\"\\\u0011\u0004,Z\nb\u0004-]^\"\n";
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
            case 0: goto L_0x0113;
            case 1: goto L_0x0117;
            case 2: goto L_0x011b;
            case 3: goto L_0x011f;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 18;
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
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "g+@\nwJ0\u00032wJ#Z\u0016";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\bd]\u001fdA\u0002G\u0012wt%Z\u0016(";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "E'Z\u0017}J~J\u0011eJ(A\u001fv\u0004i\u000e\u000b`H~";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "J!Z\t}V/\u000e\u001d}J*K\u001df\u00047Z\u001ffQ7\u000e\u001d}@!\u000e\u000b|A<^\u001bqP!J^?\u0004";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "t%\\\u001f\u0004!\\\f}Vd\u000f_2Q6BD";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "W0A\u000e2@+Y\u0010~K%J^p]d[\rwVj";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0004\"G\u0012wj%C\u001b(";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\bdH\u0017~A\u0010A\nsH\bK\u0010uP,\u0014";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u00047O\bwB-B\u001bBE0FD";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "p,K^`A7A\u000b`G!\u000e\u001a}A7\u000e\u0010}PdK\u0006{W0\u000eS2";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\n%^\u0015";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\bdH\u0017~A\nO\u0013w\u001e";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "a<]\u0017f\u0004\"G\u0012w\u0004(K\u0010uP,\u0014";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "W0O\fft+]\n{K*\u0014^";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "F=Z\u001ba\u0019";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "g+@\u0010wG0G\u0011|";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "v%@\u0019w";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "g(A\rw";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "`+Y\u0010~K%J^sC%G\u0010>\u00040\\\u00072V!]\n2\td";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "I \u001b^qL!M\u00152A6\\\u0011`\bdZ\fk\u0004%I\u001f{Jd\u0003^";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "g+@\u0010wG0\u000e\n{I!\u000e\u0011gPh\u000e\n`]d\\\u001baPd\u0003^";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        f836z = r4;
        r0 = 1;
        f835b = r0;
        return;
    L_0x0113:
        r9 = 36;
        goto L_0x0020;
    L_0x0117:
        r9 = 68;
        goto L_0x0020;
    L_0x011b:
        r9 = 46;
        goto L_0x0020;
    L_0x011f:
        r9 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.b.<clinit>():void");
    }

    public C0463b(Context context, C0429c c0429c, Bundle bundle, C0465d c0465d, int i) {
        int i2 = 0;
        ac.m1576a();
        this.f837a = false;
        this.f841f = bundle;
        this.f838c = new C0464c(this, context.getMainLooper(), c0465d);
        this.f838c.sendEmptyMessageDelayed(0, 2000);
        while (f835b) {
            if (this.f837a) {
                ac.m1584c();
                this.f838c.removeCallbacksAndMessages(null);
                c0465d.mo2221a(1);
                return;
            } else if (c0429c.f636z == 0) {
                ac.m1586d();
                if (c0465d != null) {
                    this.f837a = true;
                    DownloadService.f789a.remove(c0429c);
                    this.f838c.removeCallbacksAndMessages(null);
                    c0465d.mo2221a(2);
                    return;
                }
                return;
            } else if (i2 >= 3) {
                ac.m1586d();
                if (c0465d != null) {
                    this.f837a = true;
                    DownloadService.f789a.remove(c0429c);
                    this.f838c.removeCallbacksAndMessages(null);
                    c0465d.mo2221a(2);
                    return;
                }
                return;
            } else {
                int a = m1481a(context, c0465d, c0429c);
                c0429c.f636z--;
                if (a == -1) {
                    new StringBuilder(f836z[21]).append(c0429c.f636z);
                    ac.m1581b();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }
                } else if (a == 0) {
                    new StringBuilder(f836z[19]).append(c0429c.f636z);
                    ac.m1581b();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e2) {
                    }
                } else if (a == 1) {
                    ac.m1581b();
                    this.f838c.removeCallbacksAndMessages(null);
                    this.f837a = true;
                    return;
                } else if (a == 2) {
                    new StringBuilder(f836z[20]).append(c0429c.f636z);
                    ac.m1581b();
                    i2++;
                } else if (a == -3) {
                    this.f837a = true;
                    DownloadService.f789a.remove(c0429c);
                    this.f838c.removeCallbacksAndMessages(null);
                    c0465d.mo2221a(3);
                    return;
                } else {
                    ac.m1581b();
                    this.f837a = true;
                    DownloadService.f789a.remove(c0429c);
                    this.f838c.removeCallbacksAndMessages(null);
                    c0465d.mo2221a(2);
                    return;
                }
            }
        }
        ac.m1584c();
        this.f838c.removeCallbacksAndMessages(null);
        this.f837a = true;
        c0465d.mo2221a(1);
    }

    /* renamed from: a */
    private static int m1480a(long j) {
        long j2 = j / SizeBasedTriggeringPolicy.DEFAULT_MAX_FILE_SIZE;
        int i = j2 < 1 ? 10 : j2 > 5 ? 50 : (int) (j2 * 10);
        return (int) (((double) i) * 1.1d);
    }

    /* renamed from: a */
    private int m1481a(Context context, C0465d c0465d, C0429c c0429c) {
        InputStream inputStream;
        BufferedInputStream bufferedInputStream;
        InputStream inputStream2;
        FileOutputStream fileOutputStream;
        InputStream inputStream3;
        Throwable th;
        OutputStream outputStream;
        String d = c0429c.m1278d();
        String str = "";
        if (c0429c.m1273a()) {
            str = ((C0437i) c0429c).f675M;
            str = (str == null || "".equals(str)) ? null : str.trim();
        } else if (c0429c.m1275b()) {
            str = ((C0447s) c0429c).f746H;
            str = (str == null || "".equals(str)) ? null : str.trim();
        }
        String str2 = "";
        String str3 = "";
        if (c0429c.m1273a()) {
            str2 = C0503p.m1778a(context);
            str3 = c0429c.f613c + f836z[11];
        } else if (c0429c.m1275b()) {
            str2 = C0503p.m1782b(context);
            str3 = c0429c.f613c;
        } else if (c0429c.m1279e()) {
            str2 = C0503p.m1783b(context, c0429c.f613c);
            str3 = c0429c.f613c + C0505r.m1794b(c0429c.m1278d());
        }
        if (TextUtils.isEmpty(d) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            new StringBuilder(f836z[5]).append(d).append(f836z[9]).append(str2).append(f836z[7]).append(str3);
            ac.m1588e();
            return -2;
        }
        new StringBuilder(f836z[3]).append(d).append(f836z[2]).append(str2).append(f836z[12]).append(str3);
        ac.m1584c();
        File file = new File(str2);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        long j = this.f841f.getLong(d, -1);
        long j2 = 0;
        InputStream inputStream4 = null;
        BufferedInputStream bufferedInputStream2 = null;
        FileOutputStream fileOutputStream2 = null;
        BufferedOutputStream bufferedOutputStream = null;
        InputStream inputStream5 = null;
        File file2;
        BufferedInputStream bufferedInputStream3;
        BufferedOutputStream bufferedOutputStream2;
        byte[] bArr;
        int read;
        long a;
        if (j <= 0) {
            file2 = new File(str2, str3);
            if (!file2.exists() || file2.length() <= 0) {
                ac.m1576a();
                HttpURLConnection a2 = C0463b.m1484a(d, -1);
                try {
                    inputStream = a2.getInputStream();
                    if (inputStream != null) {
                        try {
                            int responseCode = a2.getResponseCode();
                            if (responseCode == 200) {
                                long a3 = C0463b.m1483a(a2);
                                this.f841f.putLong(d, a3);
                                c0429c.f636z = C0463b.m1480a(a3);
                                if (inputStream != null) {
                                    OutputStream fileOutputStream3;
                                    try {
                                        bufferedInputStream = new BufferedInputStream(inputStream);
                                        try {
                                            file2.delete();
                                            file2.createNewFile();
                                            fileOutputStream3 = new FileOutputStream(file2);
                                        } catch (NumberFormatException e) {
                                            inputStream2 = inputStream;
                                            fileOutputStream = null;
                                            bufferedInputStream3 = bufferedInputStream;
                                            inputStream3 = inputStream;
                                            bufferedOutputStream2 = null;
                                            try {
                                                ac.m1593i();
                                                if (inputStream2 != null) {
                                                    try {
                                                        inputStream2.close();
                                                    } catch (IOException e2) {
                                                    }
                                                }
                                                C0463b.m1485a(inputStream3, bufferedInputStream3, fileOutputStream, bufferedOutputStream2, a2);
                                                return -2;
                                            } catch (Throwable th2) {
                                                bufferedOutputStream = bufferedOutputStream2;
                                                fileOutputStream2 = fileOutputStream;
                                                bufferedInputStream = bufferedInputStream3;
                                                inputStream4 = inputStream3;
                                                inputStream = inputStream2;
                                                th = th2;
                                                if (inputStream != null) {
                                                    try {
                                                        inputStream.close();
                                                    } catch (IOException e3) {
                                                    }
                                                }
                                                C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                                throw th;
                                            }
                                        } catch (ProtocolException e4) {
                                            inputStream4 = inputStream;
                                            try {
                                                ac.m1593i();
                                                if (inputStream != null) {
                                                    try {
                                                        inputStream.close();
                                                    } catch (IOException e5) {
                                                    }
                                                }
                                                C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                                return -2;
                                            } catch (Throwable th3) {
                                                th = th3;
                                                if (inputStream != null) {
                                                    inputStream.close();
                                                }
                                                C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                                throw th;
                                            }
                                        } catch (IllegalStateException e6) {
                                            inputStream4 = inputStream;
                                            ac.m1593i();
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (IOException e7) {
                                                }
                                            }
                                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                            return -2;
                                        } catch (FileNotFoundException e8) {
                                            inputStream4 = inputStream;
                                            ac.m1593i();
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (IOException e9) {
                                                }
                                            }
                                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                            return -2;
                                        } catch (IOException e10) {
                                            inputStream4 = inputStream;
                                            ac.m1591g();
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (IOException e11) {
                                                }
                                            }
                                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                            return -1;
                                        } catch (C0450g e12) {
                                            inputStream4 = inputStream;
                                            ac.m1592h();
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (IOException e13) {
                                                }
                                            }
                                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                            return -2;
                                        } catch (Throwable th4) {
                                            th = th4;
                                            inputStream4 = inputStream;
                                            if (inputStream != null) {
                                                inputStream.close();
                                            }
                                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                            throw th;
                                        }
                                    } catch (NumberFormatException e14) {
                                        inputStream2 = inputStream;
                                        fileOutputStream = null;
                                        bufferedInputStream3 = null;
                                        inputStream3 = inputStream;
                                        bufferedOutputStream2 = null;
                                        ac.m1593i();
                                        if (inputStream2 != null) {
                                            inputStream2.close();
                                        }
                                        C0463b.m1485a(inputStream3, bufferedInputStream3, fileOutputStream, bufferedOutputStream2, a2);
                                        return -2;
                                    } catch (ProtocolException e15) {
                                        bufferedInputStream = null;
                                        inputStream4 = inputStream;
                                        ac.m1593i();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        return -2;
                                    } catch (IllegalStateException e16) {
                                        bufferedInputStream = null;
                                        inputStream4 = inputStream;
                                        ac.m1593i();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        return -2;
                                    } catch (FileNotFoundException e17) {
                                        bufferedInputStream = null;
                                        inputStream4 = inputStream;
                                        ac.m1593i();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        return -2;
                                    } catch (IOException e18) {
                                        bufferedInputStream = null;
                                        inputStream4 = inputStream;
                                        ac.m1591g();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        return -1;
                                    } catch (C0450g e19) {
                                        bufferedInputStream = null;
                                        inputStream4 = inputStream;
                                        ac.m1592h();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        return -2;
                                    } catch (Throwable th5) {
                                        th = th5;
                                        bufferedInputStream = null;
                                        inputStream4 = inputStream;
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        throw th;
                                    }
                                    try {
                                        BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(fileOutputStream3);
                                        try {
                                            bArr = new byte[1024];
                                            while (true) {
                                                read = bufferedInputStream.read(bArr);
                                                if (read == -1) {
                                                    break;
                                                } else if (this.f837a) {
                                                    ac.m1586d();
                                                    throw new C0450g(f836z[6]);
                                                } else {
                                                    bufferedOutputStream3.write(bArr, 0, read);
                                                    j2 += (long) read;
                                                    this.f839d = j2;
                                                    this.f840e = a3;
                                                }
                                            }
                                            bufferedOutputStream3.flush();
                                            if (file2 != null && file2.length() == a3 && C0498k.m1759a(r4, file2)) {
                                                this.f841f.remove(d);
                                                if (c0465d != null) {
                                                    c0465d.mo2223a(file2.getAbsolutePath(), false);
                                                }
                                                if (inputStream != null) {
                                                    try {
                                                        inputStream.close();
                                                    } catch (IOException e20) {
                                                    }
                                                }
                                                C0463b.m1485a(inputStream, bufferedInputStream, fileOutputStream3, bufferedOutputStream3, a2);
                                                return 1;
                                            }
                                            ac.m1586d();
                                            if (file2.delete()) {
                                                if (inputStream != null) {
                                                    try {
                                                        inputStream.close();
                                                    } catch (IOException e21) {
                                                    }
                                                }
                                                C0463b.m1485a(inputStream, bufferedInputStream, fileOutputStream3, bufferedOutputStream3, a2);
                                                return 2;
                                            }
                                            ac.m1588e();
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (IOException e22) {
                                                }
                                            }
                                            C0463b.m1485a(inputStream, bufferedInputStream, fileOutputStream3, bufferedOutputStream3, a2);
                                            return -2;
                                        } catch (NumberFormatException e23) {
                                            inputStream2 = inputStream;
                                            inputStream3 = inputStream;
                                            bufferedOutputStream2 = bufferedOutputStream3;
                                            fileOutputStream = fileOutputStream3;
                                            bufferedInputStream3 = bufferedInputStream;
                                        } catch (ProtocolException e24) {
                                            bufferedOutputStream = bufferedOutputStream3;
                                            fileOutputStream2 = fileOutputStream3;
                                            inputStream4 = inputStream;
                                        } catch (IllegalStateException e25) {
                                            bufferedOutputStream = bufferedOutputStream3;
                                            outputStream = fileOutputStream3;
                                            inputStream4 = inputStream;
                                        } catch (FileNotFoundException e26) {
                                            bufferedOutputStream = bufferedOutputStream3;
                                            outputStream = fileOutputStream3;
                                            inputStream4 = inputStream;
                                        } catch (IOException e27) {
                                            bufferedOutputStream = bufferedOutputStream3;
                                            outputStream = fileOutputStream3;
                                            inputStream4 = inputStream;
                                        } catch (C0450g e28) {
                                            bufferedOutputStream = bufferedOutputStream3;
                                            outputStream = fileOutputStream3;
                                            inputStream4 = inputStream;
                                        } catch (Throwable th6) {
                                            th = th6;
                                            bufferedOutputStream = bufferedOutputStream3;
                                            outputStream = fileOutputStream3;
                                            inputStream4 = inputStream;
                                        }
                                    } catch (NumberFormatException e29) {
                                        inputStream2 = inputStream;
                                        OutputStream outputStream2 = fileOutputStream3;
                                        inputStream3 = inputStream;
                                        bufferedInputStream3 = bufferedInputStream;
                                        bufferedOutputStream2 = null;
                                        ac.m1593i();
                                        if (inputStream2 != null) {
                                            inputStream2.close();
                                        }
                                        C0463b.m1485a(inputStream3, bufferedInputStream3, fileOutputStream, bufferedOutputStream2, a2);
                                        return -2;
                                    } catch (ProtocolException e30) {
                                        outputStream = fileOutputStream3;
                                        inputStream4 = inputStream;
                                        ac.m1593i();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        return -2;
                                    } catch (IllegalStateException e31) {
                                        outputStream = fileOutputStream3;
                                        inputStream4 = inputStream;
                                        ac.m1593i();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        return -2;
                                    } catch (FileNotFoundException e32) {
                                        outputStream = fileOutputStream3;
                                        inputStream4 = inputStream;
                                        ac.m1593i();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        return -2;
                                    } catch (IOException e33) {
                                        outputStream = fileOutputStream3;
                                        inputStream4 = inputStream;
                                        ac.m1591g();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        return -1;
                                    } catch (C0450g e34) {
                                        outputStream = fileOutputStream3;
                                        inputStream4 = inputStream;
                                        ac.m1592h();
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        return -2;
                                    } catch (Throwable th7) {
                                        th = th7;
                                        outputStream = fileOutputStream3;
                                        inputStream4 = inputStream;
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                                        throw th;
                                    }
                                }
                                ac.m1586d();
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e35) {
                                    }
                                }
                                C0463b.m1485a(inputStream, null, null, null, a2);
                                return 0;
                            } else if (responseCode == 404) {
                                new StringBuilder(f836z[10]).append(d);
                                ac.m1581b();
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e36) {
                                    }
                                }
                                C0463b.m1485a(null, null, null, null, a2);
                                return -3;
                            } else {
                                new StringBuilder(f836z[4]).append(responseCode);
                                ac.m1586d();
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (IOException e37) {
                                    }
                                }
                                C0463b.m1485a(null, null, null, null, a2);
                                return -2;
                            }
                        } catch (NumberFormatException e38) {
                            inputStream2 = inputStream;
                            fileOutputStream = null;
                            bufferedInputStream3 = null;
                            inputStream3 = null;
                            bufferedOutputStream2 = null;
                            ac.m1593i();
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                            C0463b.m1485a(inputStream3, bufferedInputStream3, fileOutputStream, bufferedOutputStream2, a2);
                            return -2;
                        } catch (ProtocolException e39) {
                            bufferedInputStream = null;
                            ac.m1593i();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                            return -2;
                        } catch (IllegalStateException e40) {
                            bufferedInputStream = null;
                            ac.m1593i();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                            return -2;
                        } catch (FileNotFoundException e41) {
                            bufferedInputStream = null;
                            ac.m1593i();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                            return -2;
                        } catch (IOException e42) {
                            bufferedInputStream = null;
                            ac.m1591g();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                            return -1;
                        } catch (C0450g e43) {
                            bufferedInputStream = null;
                            ac.m1592h();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                            return -2;
                        } catch (Throwable th8) {
                            th = th8;
                            bufferedInputStream = null;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                            throw th;
                        }
                    }
                    ac.m1586d();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e44) {
                        }
                    }
                    C0463b.m1485a(null, null, null, null, a2);
                    return 0;
                } catch (NumberFormatException e45) {
                    inputStream2 = null;
                    bufferedOutputStream2 = null;
                    fileOutputStream = null;
                    bufferedInputStream3 = null;
                    inputStream3 = null;
                    ac.m1593i();
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    C0463b.m1485a(inputStream3, bufferedInputStream3, fileOutputStream, bufferedOutputStream2, a2);
                    return -2;
                } catch (ProtocolException e46) {
                    inputStream = null;
                    bufferedInputStream = null;
                    ac.m1593i();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                    return -2;
                } catch (IllegalStateException e47) {
                    inputStream = null;
                    bufferedInputStream = null;
                    ac.m1593i();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                    return -2;
                } catch (FileNotFoundException e48) {
                    inputStream = null;
                    bufferedInputStream = null;
                    ac.m1593i();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                    return -2;
                } catch (IOException e49) {
                    inputStream = null;
                    bufferedInputStream = null;
                    ac.m1591g();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                    return -1;
                } catch (C0450g e50) {
                    inputStream = null;
                    bufferedInputStream = null;
                    ac.m1592h();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                    return -2;
                } catch (Throwable th9) {
                    th = th9;
                    inputStream = null;
                    bufferedInputStream = null;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    C0463b.m1485a(inputStream4, bufferedInputStream, fileOutputStream2, bufferedOutputStream, a2);
                    throw th;
                }
            } else if (file2.length() > 0) {
                HttpURLConnection a4 = C0463b.m1484a(d, -1);
                try {
                    a4.getInputStream();
                    a = C0463b.m1483a(a4);
                    if (file2.length() == a && C0498k.m1759a(r4, file2)) {
                        ac.m1581b();
                        if (c0465d != null) {
                            c0465d.mo2223a(file2.getAbsolutePath(), true);
                        }
                        return 1;
                    }
                    new StringBuilder(f836z[13]).append(file2.length()).append(f836z[8]).append(a);
                    ac.m1584c();
                    if (file2.delete()) {
                        return 2;
                    }
                    ac.m1588e();
                    return -2;
                } catch (ProtocolException e51) {
                    ac.m1593i();
                    return -2;
                } catch (IOException e52) {
                    ac.m1591g();
                    return -1;
                } catch (C0450g e53) {
                    ac.m1592h();
                    return -2;
                }
            } else {
                ac.m1588e();
                return -2;
            }
        }
        ac.m1584c();
        file2 = new File(str2, str3);
        if (file2.exists()) {
            ac.m1576a();
            a = file2.length();
            j2 = a;
        } else {
            ac.m1576a();
            a = 0;
            try {
                file2.createNewFile();
            } catch (IOException e54) {
                ac.m1593i();
                return -2;
            }
        }
        new StringBuilder(f836z[14]).append(a);
        ac.m1584c();
        if (c0429c.f636z == -1) {
            ac.m1581b();
            c0429c.f636z = C0463b.m1480a(j);
        }
        HttpURLConnection a5 = C0463b.m1484a(d, a);
        try {
            inputStream5 = a5.getInputStream();
            if (inputStream5 != null) {
                int responseCode2 = a5.getResponseCode();
                if (responseCode2 == 200 || responseCode2 == 206) {
                    if (C0463b.m1483a(a5) + j2 != j) {
                        ac.m1588e();
                        this.f841f.remove(d);
                        if (!file2.delete()) {
                            ac.m1588e();
                        }
                        if (inputStream5 != null) {
                            try {
                                inputStream5.close();
                            } catch (IOException e55) {
                            }
                        }
                        C0463b.m1485a(null, null, null, null, a5);
                        return 0;
                    } else if (inputStream5 != null) {
                        try {
                            bufferedInputStream3 = new BufferedInputStream(inputStream5);
                            try {
                                outputStream2 = new FileOutputStream(file2, true);
                                try {
                                    bufferedOutputStream2 = new BufferedOutputStream(outputStream2);
                                    try {
                                        bArr = new byte[1024];
                                        while (true) {
                                            read = bufferedInputStream3.read(bArr);
                                            if (read == -1) {
                                                break;
                                            } else if (this.f837a) {
                                                ac.m1586d();
                                                throw new C0450g(f836z[6]);
                                            } else {
                                                bufferedOutputStream2.write(bArr, 0, read);
                                                j2 += (long) read;
                                                this.f839d = j2;
                                                this.f840e = j;
                                            }
                                        }
                                        bufferedOutputStream2.flush();
                                        ac.m1581b();
                                        if (file2 != null && file2.length() == j && C0498k.m1759a(r4, file2)) {
                                            this.f841f.remove(d);
                                            if (c0465d != null) {
                                                c0465d.mo2223a(file2.getAbsolutePath(), false);
                                            }
                                            if (inputStream5 != null) {
                                                try {
                                                    inputStream5.close();
                                                } catch (IOException e56) {
                                                }
                                            }
                                            C0463b.m1485a(inputStream5, bufferedInputStream3, outputStream2, bufferedOutputStream2, a5);
                                            return 1;
                                        }
                                        ac.m1586d();
                                        if (file2.delete()) {
                                            if (inputStream5 != null) {
                                                try {
                                                    inputStream5.close();
                                                } catch (IOException e57) {
                                                }
                                            }
                                            C0463b.m1485a(inputStream5, bufferedInputStream3, outputStream2, bufferedOutputStream2, a5);
                                            return 2;
                                        }
                                        ac.m1588e();
                                        if (inputStream5 != null) {
                                            try {
                                                inputStream5.close();
                                            } catch (IOException e58) {
                                            }
                                        }
                                        C0463b.m1485a(inputStream5, bufferedInputStream3, outputStream2, bufferedOutputStream2, a5);
                                        return -2;
                                    } catch (NumberFormatException e59) {
                                        bufferedOutputStream = bufferedOutputStream2;
                                        fileOutputStream2 = outputStream2;
                                        bufferedInputStream2 = bufferedInputStream3;
                                        inputStream4 = inputStream5;
                                    } catch (ProtocolException e60) {
                                        bufferedOutputStream = bufferedOutputStream2;
                                        fileOutputStream2 = outputStream2;
                                        bufferedInputStream2 = bufferedInputStream3;
                                        inputStream4 = inputStream5;
                                    } catch (IllegalStateException e61) {
                                        bufferedOutputStream = bufferedOutputStream2;
                                        outputStream = outputStream2;
                                        bufferedInputStream2 = bufferedInputStream3;
                                        inputStream4 = inputStream5;
                                    } catch (FileNotFoundException e62) {
                                        bufferedOutputStream = bufferedOutputStream2;
                                        outputStream = outputStream2;
                                        bufferedInputStream2 = bufferedInputStream3;
                                        inputStream4 = inputStream5;
                                    } catch (IOException e63) {
                                        bufferedOutputStream = bufferedOutputStream2;
                                        outputStream = outputStream2;
                                        bufferedInputStream2 = bufferedInputStream3;
                                        inputStream4 = inputStream5;
                                    } catch (C0450g e64) {
                                        bufferedOutputStream = bufferedOutputStream2;
                                        outputStream = outputStream2;
                                        bufferedInputStream2 = bufferedInputStream3;
                                        inputStream4 = inputStream5;
                                    } catch (Throwable th10) {
                                        th = th10;
                                        bufferedOutputStream = bufferedOutputStream2;
                                        outputStream = outputStream2;
                                        bufferedInputStream2 = bufferedInputStream3;
                                        inputStream4 = inputStream5;
                                    }
                                } catch (NumberFormatException e65) {
                                    outputStream = outputStream2;
                                    bufferedInputStream2 = bufferedInputStream3;
                                    inputStream4 = inputStream5;
                                    try {
                                        ac.m1593i();
                                        if (inputStream5 != null) {
                                            try {
                                                inputStream5.close();
                                            } catch (IOException e66) {
                                            }
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                        return -2;
                                    } catch (Throwable th11) {
                                        th = th11;
                                        if (inputStream5 != null) {
                                            try {
                                                inputStream5.close();
                                            } catch (IOException e67) {
                                            }
                                        }
                                        C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                        throw th;
                                    }
                                } catch (ProtocolException e68) {
                                    outputStream = outputStream2;
                                    bufferedInputStream2 = bufferedInputStream3;
                                    inputStream4 = inputStream5;
                                    ac.m1593i();
                                    if (inputStream5 != null) {
                                        try {
                                            inputStream5.close();
                                        } catch (IOException e69) {
                                        }
                                    }
                                    C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                    return -2;
                                } catch (IllegalStateException e70) {
                                    outputStream = outputStream2;
                                    bufferedInputStream2 = bufferedInputStream3;
                                    inputStream4 = inputStream5;
                                    ac.m1593i();
                                    if (inputStream5 != null) {
                                        try {
                                            inputStream5.close();
                                        } catch (IOException e71) {
                                        }
                                    }
                                    C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                    return -2;
                                } catch (FileNotFoundException e72) {
                                    outputStream = outputStream2;
                                    bufferedInputStream2 = bufferedInputStream3;
                                    inputStream4 = inputStream5;
                                    ac.m1593i();
                                    if (inputStream5 != null) {
                                        try {
                                            inputStream5.close();
                                        } catch (IOException e73) {
                                        }
                                    }
                                    C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                    return -2;
                                } catch (IOException e74) {
                                    outputStream = outputStream2;
                                    bufferedInputStream2 = bufferedInputStream3;
                                    inputStream4 = inputStream5;
                                    ac.m1591g();
                                    if (inputStream5 != null) {
                                        try {
                                            inputStream5.close();
                                        } catch (IOException e75) {
                                        }
                                    }
                                    C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                    return -1;
                                } catch (C0450g e76) {
                                    outputStream = outputStream2;
                                    bufferedInputStream2 = bufferedInputStream3;
                                    inputStream4 = inputStream5;
                                    ac.m1592h();
                                    if (inputStream5 != null) {
                                        try {
                                            inputStream5.close();
                                        } catch (IOException e77) {
                                        }
                                    }
                                    C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                    return -2;
                                } catch (Throwable th12) {
                                    th = th12;
                                    outputStream = outputStream2;
                                    bufferedInputStream2 = bufferedInputStream3;
                                    inputStream4 = inputStream5;
                                    if (inputStream5 != null) {
                                        inputStream5.close();
                                    }
                                    C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                    throw th;
                                }
                            } catch (NumberFormatException e78) {
                                bufferedInputStream2 = bufferedInputStream3;
                                inputStream4 = inputStream5;
                                ac.m1593i();
                                if (inputStream5 != null) {
                                    inputStream5.close();
                                }
                                C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                return -2;
                            } catch (ProtocolException e79) {
                                bufferedInputStream2 = bufferedInputStream3;
                                inputStream4 = inputStream5;
                                ac.m1593i();
                                if (inputStream5 != null) {
                                    inputStream5.close();
                                }
                                C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                return -2;
                            } catch (IllegalStateException e80) {
                                bufferedInputStream2 = bufferedInputStream3;
                                inputStream4 = inputStream5;
                                ac.m1593i();
                                if (inputStream5 != null) {
                                    inputStream5.close();
                                }
                                C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                return -2;
                            } catch (FileNotFoundException e81) {
                                bufferedInputStream2 = bufferedInputStream3;
                                inputStream4 = inputStream5;
                                ac.m1593i();
                                if (inputStream5 != null) {
                                    inputStream5.close();
                                }
                                C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                return -2;
                            } catch (IOException e82) {
                                bufferedInputStream2 = bufferedInputStream3;
                                inputStream4 = inputStream5;
                                ac.m1591g();
                                if (inputStream5 != null) {
                                    inputStream5.close();
                                }
                                C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                return -1;
                            } catch (C0450g e83) {
                                bufferedInputStream2 = bufferedInputStream3;
                                inputStream4 = inputStream5;
                                ac.m1592h();
                                if (inputStream5 != null) {
                                    inputStream5.close();
                                }
                                C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                return -2;
                            } catch (Throwable th13) {
                                th = th13;
                                bufferedInputStream2 = bufferedInputStream3;
                                inputStream4 = inputStream5;
                                if (inputStream5 != null) {
                                    inputStream5.close();
                                }
                                C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                                throw th;
                            }
                        } catch (NumberFormatException e84) {
                            inputStream4 = inputStream5;
                            ac.m1593i();
                            if (inputStream5 != null) {
                                inputStream5.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                            return -2;
                        } catch (ProtocolException e85) {
                            inputStream4 = inputStream5;
                            ac.m1593i();
                            if (inputStream5 != null) {
                                inputStream5.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                            return -2;
                        } catch (IllegalStateException e86) {
                            inputStream4 = inputStream5;
                            ac.m1593i();
                            if (inputStream5 != null) {
                                inputStream5.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                            return -2;
                        } catch (FileNotFoundException e87) {
                            inputStream4 = inputStream5;
                            ac.m1593i();
                            if (inputStream5 != null) {
                                inputStream5.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                            return -2;
                        } catch (IOException e88) {
                            inputStream4 = inputStream5;
                            ac.m1591g();
                            if (inputStream5 != null) {
                                inputStream5.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                            return -1;
                        } catch (C0450g e89) {
                            inputStream4 = inputStream5;
                            ac.m1592h();
                            if (inputStream5 != null) {
                                inputStream5.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                            return -2;
                        } catch (Throwable th14) {
                            th = th14;
                            inputStream4 = inputStream5;
                            if (inputStream5 != null) {
                                inputStream5.close();
                            }
                            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
                            throw th;
                        }
                    } else {
                        ac.m1586d();
                        if (inputStream5 != null) {
                            try {
                                inputStream5.close();
                            } catch (IOException e90) {
                            }
                        }
                        C0463b.m1485a(inputStream5, null, null, null, a5);
                        return 0;
                    }
                } else if (responseCode2 == HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE) {
                    ac.m1588e();
                    this.f841f.remove(d);
                    if (!file2.delete()) {
                        ac.m1588e();
                    }
                    if (inputStream5 != null) {
                        try {
                            inputStream5.close();
                        } catch (IOException e91) {
                        }
                    }
                    C0463b.m1485a(null, null, null, null, a5);
                    return 0;
                } else if (responseCode2 == 404) {
                    new StringBuilder(f836z[10]).append(d);
                    ac.m1581b();
                    if (inputStream5 != null) {
                        try {
                            inputStream5.close();
                        } catch (IOException e92) {
                        }
                    }
                    C0463b.m1485a(null, null, null, null, a5);
                    return -3;
                } else {
                    new StringBuilder(f836z[4]).append(responseCode2);
                    ac.m1586d();
                    if (inputStream5 != null) {
                        try {
                            inputStream5.close();
                        } catch (IOException e93) {
                        }
                    }
                    C0463b.m1485a(null, null, null, null, a5);
                    return -2;
                }
            }
            ac.m1586d();
            if (inputStream5 != null) {
                try {
                    inputStream5.close();
                } catch (IOException e94) {
                }
            }
            C0463b.m1485a(null, null, null, null, a5);
            return 0;
        } catch (NumberFormatException e95) {
            ac.m1593i();
            if (inputStream5 != null) {
                inputStream5.close();
            }
            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
            return -2;
        } catch (ProtocolException e96) {
            ac.m1593i();
            if (inputStream5 != null) {
                inputStream5.close();
            }
            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
            return -2;
        } catch (IllegalStateException e97) {
            ac.m1593i();
            if (inputStream5 != null) {
                inputStream5.close();
            }
            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
            return -2;
        } catch (FileNotFoundException e98) {
            ac.m1593i();
            if (inputStream5 != null) {
                inputStream5.close();
            }
            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
            return -2;
        } catch (IOException e99) {
            ac.m1591g();
            if (inputStream5 != null) {
                inputStream5.close();
            }
            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
            return -1;
        } catch (C0450g e100) {
            ac.m1592h();
            if (inputStream5 != null) {
                inputStream5.close();
            }
            C0463b.m1485a(inputStream4, bufferedInputStream2, fileOutputStream2, bufferedOutputStream, a5);
            return -2;
        }
    }

    /* renamed from: a */
    private static long m1483a(HttpURLConnection httpURLConnection) {
        long longValue = Long.valueOf(httpURLConnection.getHeaderField(f836z[1])).longValue();
        if (longValue > 0) {
            return longValue;
        }
        throw new C0450g(f836z[0]);
    }

    /* renamed from: a */
    private static HttpURLConnection m1484a(String str, long j) {
        HttpURLConnection httpURLConnection;
        IOException e;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setRequestProperty(f836z[16], f836z[18]);
                if (j >= 0) {
                    httpURLConnection.setRequestProperty(f836z[17], new StringBuilder(f836z[15]).append(j).append(HelpFormatter.DEFAULT_OPT_PREFIX).toString());
                }
                C0506s.m1799a(httpURLConnection, false);
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return httpURLConnection;
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            httpURLConnection = null;
            e = iOException;
            e.printStackTrace();
            return httpURLConnection;
        }
        return httpURLConnection;
    }

    /* renamed from: a */
    private static void m1485a(InputStream inputStream, BufferedInputStream bufferedInputStream, FileOutputStream fileOutputStream, BufferedOutputStream bufferedOutputStream, HttpURLConnection httpURLConnection) {
        if (bufferedOutputStream != null) {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e2) {
            }
        }
        if (bufferedInputStream != null) {
            try {
                bufferedInputStream.close();
            } catch (IOException e3) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e4) {
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    /* renamed from: a */
    public static boolean m1486a(int i) {
        return 2 == i || 3 == i;
    }
}
