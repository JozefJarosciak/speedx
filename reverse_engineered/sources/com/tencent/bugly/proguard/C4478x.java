package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.C4417a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.x */
public final class C4478x {
    /* renamed from: a */
    public static boolean f15783a = true;
    /* renamed from: b */
    private static SimpleDateFormat f15784b;
    /* renamed from: c */
    private static int f15785c = 5120;
    /* renamed from: d */
    private static StringBuilder f15786d;
    /* renamed from: e */
    private static StringBuilder f15787e;
    /* renamed from: f */
    private static boolean f15788f;
    /* renamed from: g */
    private static C4477a f15789g;
    /* renamed from: h */
    private static String f15790h;
    /* renamed from: i */
    private static String f15791i;
    /* renamed from: j */
    private static Context f15792j;
    /* renamed from: k */
    private static String f15793k;
    /* renamed from: l */
    private static boolean f15794l;
    /* renamed from: m */
    private static int f15795m;
    /* renamed from: n */
    private static Object f15796n = new Object();

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.x$a */
    public static class C4477a {
        /* renamed from: a */
        private boolean f15778a;
        /* renamed from: b */
        private File f15779b;
        /* renamed from: c */
        private String f15780c;
        /* renamed from: d */
        private long f15781d;
        /* renamed from: e */
        private long f15782e = 30720;

        public C4477a(String str) {
            if (str != null && !str.equals("")) {
                this.f15780c = str;
                this.f15778a = m17755a();
            }
        }

        /* renamed from: a */
        private synchronized boolean m17755a() {
            boolean z = false;
            synchronized (this) {
                try {
                    this.f15779b = new File(this.f15780c);
                    if (!this.f15779b.exists() || this.f15779b.delete()) {
                        if (!this.f15779b.createNewFile()) {
                            this.f15778a = false;
                        }
                        z = true;
                    } else {
                        this.f15778a = false;
                    }
                } catch (Throwable th) {
                    this.f15778a = false;
                }
            }
            return z;
        }

        /* renamed from: a */
        public final synchronized boolean m17759a(String str) {
            FileOutputStream fileOutputStream;
            Throwable th;
            boolean z = false;
            synchronized (this) {
                if (this.f15778a) {
                    FileOutputStream fileOutputStream2;
                    try {
                        fileOutputStream2 = new FileOutputStream(this.f15779b, true);
                        try {
                            byte[] bytes = str.getBytes("UTF-8");
                            fileOutputStream2.write(bytes);
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                            this.f15781d += (long) bytes.length;
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e) {
                            }
                            z = true;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream2 = null;
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        throw th;
                    }
                }
            }
            return z;
        }
    }

    static {
        f15784b = null;
        try {
            f15784b = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private static boolean m17768b(String str, String str2, String str3) {
        try {
            C4417a b = C4417a.m17304b();
            if (!(b == null || b.f15263C == null)) {
                return b.f15263C.appendLogToNative(str, str2, str3);
            }
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
        }
        return false;
    }

    /* renamed from: a */
    public static synchronized void m17763a(Context context) {
        synchronized (C4478x.class) {
            if (!(f15794l || context == null || !f15783a)) {
                try {
                    f15787e = new StringBuilder(0);
                    f15786d = new StringBuilder(0);
                    f15792j = context;
                    C4417a a = C4417a.m17303a(context);
                    f15790h = a.f15289d;
                    a.getClass();
                    f15791i = "";
                    f15793k = f15792j.getFilesDir().getPath() + "/buglylog_" + f15790h + "_" + f15791i + ".txt";
                    f15795m = Process.myPid();
                } catch (Throwable th) {
                }
                f15794l = true;
            }
        }
    }

    /* renamed from: a */
    public static void m17762a(int i) {
        synchronized (f15796n) {
            f15785c = i;
            if (i < 0) {
                f15785c = 0;
            } else if (i > 10240) {
                f15785c = 10240;
            }
        }
    }

    /* renamed from: a */
    public static void m17765a(String str, String str2, Throwable th) {
        if (th != null) {
            String message = th.getMessage();
            if (message == null) {
                message = "";
            }
            C4478x.m17764a(str, str2, message + '\n' + C4479y.m17801b(th));
        }
    }

    /* renamed from: a */
    public static synchronized void m17764a(String str, String str2, String str3) {
        synchronized (C4478x.class) {
            if (f15794l && f15783a) {
                C4478x.m17768b(str, str2, str3);
                long myTid = (long) Process.myTid();
                f15786d.setLength(0);
                if (str3.length() > 30720) {
                    str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
                }
                Date date = new Date();
                f15786d.append(f15784b != null ? f15784b.format(date) : date.toString()).append(" ").append(f15795m).append(" ").append(myTid).append(" ").append(str).append(" ").append(str2).append(": ").append(str3).append("\u0001\r\n");
                final String stringBuilder = f15786d.toString();
                synchronized (f15796n) {
                    f15787e.append(stringBuilder);
                    if (f15787e.length() <= f15785c) {
                    } else if (f15788f) {
                    } else {
                        f15788f = true;
                        C4474v.m17740a().m17741a(new Runnable() {
                            public final void run() {
                                synchronized (C4478x.f15796n) {
                                    try {
                                        if (C4478x.f15789g == null) {
                                            C4478x.f15789g = new C4477a(C4478x.f15793k);
                                        } else if (C4478x.f15789g.f15779b == null || C4478x.f15789g.f15779b.length() + ((long) C4478x.f15787e.length()) > C4478x.f15789g.f15782e) {
                                            C4478x.f15789g.m17755a();
                                        }
                                        if (C4478x.f15789g.f15778a) {
                                            C4478x.f15789g.m17759a(C4478x.f15787e.toString());
                                            C4478x.f15787e.setLength(0);
                                        } else {
                                            C4478x.f15787e.setLength(0);
                                            C4478x.f15787e.append(stringBuilder);
                                        }
                                        C4478x.f15788f = false;
                                    } catch (Throwable th) {
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static byte[] m17766a(boolean z) {
        byte[] bArr = null;
        if (f15783a) {
            synchronized (f15796n) {
                File a;
                if (z) {
                    try {
                        if (f15789g != null && f15789g.f15778a) {
                            a = f15789g.f15779b;
                            if (f15787e.length() == 0 || a != null) {
                                bArr = C4479y.m17796a(a, f15787e.toString());
                            }
                        }
                    } catch (Throwable th) {
                    }
                }
                a = bArr;
                if (f15787e.length() == 0) {
                }
                bArr = C4479y.m17796a(a, f15787e.toString());
            }
        }
        return bArr;
    }
}
