package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Base64;
import com.tencent.bugly.C4402b;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.t */
public final class C4471t {
    /* renamed from: b */
    private static C4471t f15733b = null;
    /* renamed from: a */
    public boolean f15734a = true;
    /* renamed from: c */
    private final C4464o f15735c;
    /* renamed from: d */
    private final Context f15736d;
    /* renamed from: e */
    private Map<Integer, Long> f15737e = new HashMap();
    /* renamed from: f */
    private LinkedBlockingQueue<Runnable> f15738f = new LinkedBlockingQueue();
    /* renamed from: g */
    private LinkedBlockingQueue<Runnable> f15739g = new LinkedBlockingQueue();
    /* renamed from: h */
    private final Object f15740h = new Object();
    /* renamed from: i */
    private String f15741i = null;
    /* renamed from: j */
    private byte[] f15742j = null;
    /* renamed from: k */
    private long f15743k = 0;
    /* renamed from: l */
    private byte[] f15744l = null;
    /* renamed from: m */
    private long f15745m = 0;
    /* renamed from: n */
    private String f15746n = null;
    /* renamed from: o */
    private long f15747o = 0;
    /* renamed from: p */
    private final Object f15748p = new Object();
    /* renamed from: q */
    private boolean f15749q = false;
    /* renamed from: r */
    private final Object f15750r = new Object();
    /* renamed from: s */
    private int f15751s = 0;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.t$a */
    class C4470a implements Runnable {
        /* renamed from: a */
        private final Context f15729a;
        /* renamed from: b */
        private final Runnable f15730b;
        /* renamed from: c */
        private final long f15731c;
        /* renamed from: d */
        private /* synthetic */ C4471t f15732d;

        public C4470a(C4471t c4471t, Context context) {
            this.f15732d = c4471t;
            this.f15729a = context;
            this.f15730b = null;
            this.f15731c = 0;
        }

        public C4470a(C4471t c4471t, Context context, Runnable runnable, long j) {
            this.f15732d = c4471t;
            this.f15729a = context;
            this.f15730b = runnable;
            this.f15731c = j;
        }

        public final void run() {
            if (C4479y.m17790a(this.f15729a, "security_info", 30000)) {
                if (!this.f15732d.m17720e()) {
                    C4475w.m17752d("[UploadManager] Failed to load security info from database", new Object[0]);
                    this.f15732d.m17731b(false);
                }
                if (this.f15732d.f15746n != null) {
                    if (this.f15732d.m17732b()) {
                        C4475w.m17751c("[UploadManager] Sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                        if (this.f15730b != null) {
                            this.f15732d.m17708a(this.f15730b, this.f15731c);
                        }
                        this.f15732d.m17714c(0);
                        C4479y.m17807b(this.f15729a, "security_info");
                        synchronized (this.f15732d.f15750r) {
                            this.f15732d.f15749q = false;
                        }
                        return;
                    }
                    C4475w.m17747a("[UploadManager] Session ID is expired, drop it.", new Object[0]);
                    this.f15732d.m17731b(true);
                }
                byte[] a = C4479y.m17793a(128);
                if (a == null || (a.length << 3) != 128) {
                    C4475w.m17752d("[UploadManager] Failed to create AES key (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    this.f15732d.m17731b(false);
                    C4479y.m17807b(this.f15729a, "security_info");
                    synchronized (this.f15732d.f15750r) {
                        this.f15732d.f15749q = false;
                    }
                    return;
                }
                this.f15732d.f15744l = a;
                C4475w.m17751c("[UploadManager] Execute one upload task for requesting session ID (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (this.f15730b != null) {
                    this.f15732d.m17708a(this.f15730b, this.f15731c);
                    return;
                } else {
                    this.f15732d.m17714c(1);
                    return;
                }
            }
            C4475w.m17751c("[UploadManager] Sleep %d try to lock security file again (pid=%d | tid=%d)", Integer.valueOf(5000), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            C4479y.m17804b(5000);
            if (C4479y.m17784a((Runnable) this, "BUGLY_ASYNC_UPLOAD") == null) {
                C4475w.m17752d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new Object[0]);
                C4474v a2 = C4474v.m17740a();
                if (a2 != null) {
                    a2.m17741a(this);
                } else {
                    C4475w.m17753e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new Object[0]);
                }
            }
        }
    }

    /* renamed from: b */
    static /* synthetic */ int m17713b(C4471t c4471t) {
        int i = c4471t.f15751s - 1;
        c4471t.f15751s = i;
        return i;
    }

    private C4471t(Context context) {
        this.f15736d = context;
        this.f15735c = C4464o.m17672a();
        try {
            Class.forName("android.util.Base64");
        } catch (ClassNotFoundException e) {
            C4475w.m17747a("[UploadManager] Error: Can not find Base64 class, will not use stronger security way to upload", new Object[0]);
            this.f15734a = false;
        }
        if (this.f15734a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP9x32s5pPtZBXzJBz2GWM/sbTvVO2+RvW0PH01IdaBxc/").append("fB6fbHZocC9T3nl1+J5eAFjIRVuV8vHDky7Qo82Mnh0PVvcZIEQvMMVKU8dsMQopxgsOs2gkSHJwgWdinKNS8CmWobo6pFwPUW11lMv714jAUZRq2GBOqiO2vQI6iwIDAQAB");
            this.f15741i = stringBuilder.toString();
        }
    }

    /* renamed from: a */
    public static synchronized C4471t m17704a(Context context) {
        C4471t c4471t;
        synchronized (C4471t.class) {
            if (f15733b == null) {
                f15733b = new C4471t(context);
            }
            c4471t = f15733b;
        }
        return c4471t;
    }

    /* renamed from: a */
    public static synchronized C4471t m17703a() {
        C4471t c4471t;
        synchronized (C4471t.class) {
            c4471t = f15733b;
        }
        return c4471t;
    }

    /* renamed from: a */
    public final void m17725a(int i, al alVar, String str, String str2, C4406s c4406s, long j, boolean z) {
        try {
            m17709a(new C4472u(this.f15736d, i, alVar.f15586g, C4446a.m17530a(alVar), str, str2, c4406s, this.f15734a, z), true, true, j);
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public final void m17723a(int i, int i2, byte[] bArr, String str, String str2, C4406s c4406s, int i3, int i4, boolean z, Map<String, String> map) {
        try {
            m17709a(new C4472u(this.f15736d, i, i2, bArr, str, str2, c4406s, this.f15734a, i3, i4, false, map), z, false, 0);
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public final void m17726a(int i, al alVar, String str, String str2, C4406s c4406s, boolean z) {
        m17723a(i, alVar.f15586g, C4446a.m17530a(alVar), str, str2, c4406s, 0, 0, z, null);
    }

    /* renamed from: a */
    public final long m17722a(boolean z) {
        long j;
        long j2 = 0;
        long b = C4479y.m17800b();
        List a = this.f15735c.m17689a(z ? 5 : 3);
        if (a == null || a.size() <= 0) {
            j = 0;
        } else {
            try {
                C4466q c4466q = (C4466q) a.get(0);
                if (c4466q.f15719e >= b) {
                    j2 = C4479y.m17812c(c4466q.f15721g);
                    a.remove(c4466q);
                }
                j = j2;
            } catch (Throwable th) {
                Throwable th2 = th;
                j = 0;
                C4475w.m17748a(th2);
            }
            if (a.size() > 0) {
                this.f15735c.m17691a(a);
            }
        }
        C4475w.m17751c("[UploadManager] Local network consume: %d KB", Long.valueOf(j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
        return j;
    }

    /* renamed from: a */
    protected final synchronized void m17728a(long j, boolean z) {
        int i = z ? 5 : 3;
        C4466q c4466q = new C4466q();
        c4466q.f15716b = i;
        c4466q.f15719e = C4479y.m17800b();
        c4466q.f15717c = "";
        c4466q.f15718d = "";
        c4466q.f15721g = C4479y.m17814c(j);
        this.f15735c.m17695b(i);
        this.f15735c.m17694a(c4466q);
        C4475w.m17751c("[UploadManager] Network total consume: %d KB", Long.valueOf(j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
    }

    /* renamed from: a */
    public final synchronized void m17724a(int i, long j) {
        if (i >= 0) {
            this.f15737e.put(Integer.valueOf(i), Long.valueOf(j));
            C4466q c4466q = new C4466q();
            c4466q.f15716b = i;
            c4466q.f15719e = j;
            c4466q.f15717c = "";
            c4466q.f15718d = "";
            c4466q.f15721g = new byte[0];
            this.f15735c.m17695b(i);
            this.f15735c.m17694a(c4466q);
            C4475w.m17751c("[UploadManager] Uploading(ID:%d) time: %s", Integer.valueOf(i), C4479y.m17778a(j));
        } else {
            C4475w.m17753e("[UploadManager] Unknown uploading ID: %d", Integer.valueOf(i));
        }
    }

    /* renamed from: a */
    public final synchronized long m17721a(int i) {
        long j;
        j = 0;
        if (i >= 0) {
            Long l = (Long) this.f15737e.get(Integer.valueOf(i));
            if (l != null) {
                j = l.longValue();
            } else {
                List<C4466q> a = this.f15735c.m17689a(i);
                if (a != null && a.size() > 0) {
                    if (a.size() > 1) {
                        for (C4466q c4466q : a) {
                            long j2;
                            if (c4466q.f15719e > j) {
                                j2 = c4466q.f15719e;
                            } else {
                                j2 = j;
                            }
                            j = j2;
                        }
                        this.f15735c.m17695b(i);
                    } else {
                        try {
                            j = ((C4466q) a.get(0)).f15719e;
                        } catch (Throwable th) {
                            C4475w.m17748a(th);
                        }
                    }
                }
            }
        } else {
            C4475w.m17753e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i));
        }
        return j;
    }

    /* renamed from: b */
    public final boolean m17733b(int i) {
        if (C4402b.f15204c) {
            C4475w.m17751c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        C4475w.m17751c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", Long.valueOf((System.currentTimeMillis() - m17721a(i)) / 1000), Integer.valueOf(i));
        if (System.currentTimeMillis() - m17721a(i) >= 30000) {
            return true;
        }
        C4475w.m17747a("[UploadManager] Data only be uploaded once in %d seconds.", Long.valueOf(30));
        return false;
    }

    /* renamed from: c */
    private static boolean m17715c() {
        boolean z = false;
        C4475w.m17751c("[UploadManager] Drop security info of database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            C4464o a = C4464o.m17672a();
            if (a == null) {
                C4475w.m17752d("[UploadManager] Failed to get Database", new Object[0]);
            } else {
                z = a.m17692a(555, "security_info", null, true);
            }
        } catch (Throwable th) {
            C4475w.m17748a(th);
        }
        return z;
    }

    /* renamed from: d */
    private boolean m17718d() {
        C4475w.m17751c("[UploadManager] Record security info to database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            C4464o a = C4464o.m17672a();
            if (a == null) {
                C4475w.m17752d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (this.f15744l != null) {
                stringBuilder.append(Base64.encodeToString(this.f15744l, 0));
                stringBuilder.append("#");
                if (this.f15745m != 0) {
                    stringBuilder.append(Long.toString(this.f15745m));
                } else {
                    stringBuilder.append("null");
                }
                stringBuilder.append("#");
                if (this.f15746n != null) {
                    stringBuilder.append(this.f15746n);
                } else {
                    stringBuilder.append("null");
                }
                stringBuilder.append("#");
                if (this.f15747o != 0) {
                    stringBuilder.append(Long.toString(this.f15747o));
                } else {
                    stringBuilder.append("null");
                }
                a.m17693a(555, "security_info", stringBuilder.toString().getBytes(), null, true);
                return true;
            }
            C4475w.m17751c("[UploadManager] AES key is null, will not record", new Object[0]);
            return false;
        } catch (Throwable th) {
            C4475w.m17748a(th);
            C4471t.m17715c();
            return false;
        }
    }

    /* renamed from: e */
    private boolean m17720e() {
        C4475w.m17751c("[UploadManager] Load security info from database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        int i;
        try {
            C4464o a = C4464o.m17672a();
            if (a == null) {
                C4475w.m17752d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            Map a2 = a.m17690a(555, null, true);
            if (a2 != null && a2.containsKey("security_info")) {
                String[] split = new String((byte[]) a2.get("security_info")).split("#");
                if (split.length == 4) {
                    if (split[0].isEmpty() || split[0].equals("null")) {
                        i = 0;
                    } else {
                        this.f15744l = Base64.decode(split[0], 0);
                        i = 0;
                    }
                    if (i == 0) {
                        if (!(split[1].isEmpty() || split[1].equals("null"))) {
                            try {
                                this.f15745m = Long.parseLong(split[1]);
                            } catch (Throwable th) {
                                C4475w.m17748a(th);
                                i = 1;
                            }
                        }
                    }
                    if (i == 0) {
                        if (!(split[2].isEmpty() || split[2].equals("null"))) {
                            this.f15746n = split[2];
                        }
                    }
                    if (!(i != 0 || split[3].isEmpty() || split[3].equals("null"))) {
                        try {
                            this.f15747o = Long.parseLong(split[3]);
                        } catch (Throwable th2) {
                            C4475w.m17748a(th2);
                            i = 1;
                        }
                    }
                } else {
                    C4475w.m17747a("SecurityInfo = %s, Strings.length = %d", r3, Integer.valueOf(split.length));
                    i = 1;
                }
                if (i != 0) {
                    C4471t.m17715c();
                }
            }
            return true;
        } catch (Throwable th22) {
            C4475w.m17748a(th22);
            return false;
        }
    }

    /* renamed from: b */
    protected final boolean m17732b() {
        if (this.f15746n == null || this.f15747o == 0) {
            return false;
        }
        if (this.f15747o >= System.currentTimeMillis() + this.f15743k) {
            return true;
        }
        C4475w.m17751c("[UploadManager] Session ID expired time from server is: %d(%s), but now is: %d(%s)", Long.valueOf(this.f15747o), new Date(this.f15747o).toString(), Long.valueOf(System.currentTimeMillis() + this.f15743k), new Date(System.currentTimeMillis() + this.f15743k).toString());
        return false;
    }

    /* renamed from: b */
    protected final void m17731b(boolean z) {
        synchronized (this.f15748p) {
            C4475w.m17751c("[UploadManager] Clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            this.f15744l = null;
            this.f15746n = null;
            this.f15747o = 0;
        }
        if (z) {
            C4471t.m17715c();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: c */
    private void m17714c(int r15) {
        /*
        r14 = this;
        r13 = 3;
        r12 = 2;
        r11 = 1;
        r2 = 0;
        if (r15 >= 0) goto L_0x000e;
    L_0x0006:
        r0 = "[UploadManager] Number of task to execute should >= 0";
        r1 = new java.lang.Object[r2];
        com.tencent.bugly.proguard.C4475w.m17747a(r0, r1);
    L_0x000d:
        return;
    L_0x000e:
        r4 = com.tencent.bugly.proguard.C4474v.m17740a();
        r5 = new java.util.concurrent.LinkedBlockingQueue;
        r5.<init>();
        r6 = new java.util.concurrent.LinkedBlockingQueue;
        r6.<init>();
        r7 = r14.f15740h;
        monitor-enter(r7);
        r0 = "[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0057 }
        r3 = 0;
        r8 = android.os.Process.myPid();	 Catch:{ all -> 0x0057 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x0057 }
        r1[r3] = r8;	 Catch:{ all -> 0x0057 }
        r3 = 1;
        r8 = android.os.Process.myTid();	 Catch:{ all -> 0x0057 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ all -> 0x0057 }
        r1[r3] = r8;	 Catch:{ all -> 0x0057 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);	 Catch:{ all -> 0x0057 }
        r0 = r14.f15738f;	 Catch:{ all -> 0x0057 }
        r1 = r0.size();	 Catch:{ all -> 0x0057 }
        r0 = r14.f15739g;	 Catch:{ all -> 0x0057 }
        r0 = r0.size();	 Catch:{ all -> 0x0057 }
        if (r1 != 0) goto L_0x005a;
    L_0x004b:
        if (r0 != 0) goto L_0x005a;
    L_0x004d:
        r0 = "[UploadManager] There is no upload task in queue.";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0057 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);	 Catch:{ all -> 0x0057 }
        monitor-exit(r7);	 Catch:{ all -> 0x0057 }
        goto L_0x000d;
    L_0x0057:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x005a:
        if (r15 == 0) goto L_0x016b;
    L_0x005c:
        if (r15 >= r1) goto L_0x0081;
    L_0x005e:
        r0 = r2;
    L_0x005f:
        if (r4 == 0) goto L_0x0067;
    L_0x0061:
        r1 = r4.m17744c();	 Catch:{ all -> 0x0057 }
        if (r1 != 0) goto L_0x0168;
    L_0x0067:
        r1 = r2;
    L_0x0068:
        r3 = r2;
    L_0x0069:
        if (r3 >= r15) goto L_0x009a;
    L_0x006b:
        r0 = r14.f15738f;	 Catch:{ all -> 0x0057 }
        r0 = r0.peek();	 Catch:{ all -> 0x0057 }
        r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x0057 }
        if (r0 == 0) goto L_0x009a;
    L_0x0075:
        r5.put(r0);	 Catch:{ Throwable -> 0x0089 }
        r0 = r14.f15738f;	 Catch:{ Throwable -> 0x0089 }
        r0.poll();	 Catch:{ Throwable -> 0x0089 }
    L_0x007d:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x0069;
    L_0x0081:
        r3 = r1 + r0;
        if (r15 >= r3) goto L_0x016b;
    L_0x0085:
        r0 = r15 - r1;
        r15 = r1;
        goto L_0x005f;
    L_0x0089:
        r0 = move-exception;
        r8 = "[UploadManager] Failed to add upload task to temp urgent queue: %s";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ all -> 0x0057 }
        r10 = 0;
        r0 = r0.getMessage();	 Catch:{ all -> 0x0057 }
        r9[r10] = r0;	 Catch:{ all -> 0x0057 }
        com.tencent.bugly.proguard.C4475w.m17753e(r8, r9);	 Catch:{ all -> 0x0057 }
        goto L_0x007d;
    L_0x009a:
        r3 = r2;
    L_0x009b:
        if (r3 >= r1) goto L_0x00c4;
    L_0x009d:
        r0 = r14.f15739g;	 Catch:{ all -> 0x0057 }
        r0 = r0.peek();	 Catch:{ all -> 0x0057 }
        r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x0057 }
        if (r0 == 0) goto L_0x00c4;
    L_0x00a7:
        r6.put(r0);	 Catch:{ Throwable -> 0x00b3 }
        r0 = r14.f15739g;	 Catch:{ Throwable -> 0x00b3 }
        r0.poll();	 Catch:{ Throwable -> 0x00b3 }
    L_0x00af:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x009b;
    L_0x00b3:
        r0 = move-exception;
        r8 = "[UploadManager] Failed to add upload task to temp urgent queue: %s";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ all -> 0x0057 }
        r10 = 0;
        r0 = r0.getMessage();	 Catch:{ all -> 0x0057 }
        r9[r10] = r0;	 Catch:{ all -> 0x0057 }
        com.tencent.bugly.proguard.C4475w.m17753e(r8, r9);	 Catch:{ all -> 0x0057 }
        goto L_0x00af;
    L_0x00c4:
        monitor-exit(r7);	 Catch:{ all -> 0x0057 }
        if (r15 <= 0) goto L_0x00e8;
    L_0x00c7:
        r0 = "[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r13];
        r7 = java.lang.Integer.valueOf(r15);
        r3[r2] = r7;
        r7 = android.os.Process.myPid();
        r7 = java.lang.Integer.valueOf(r7);
        r3[r11] = r7;
        r7 = android.os.Process.myTid();
        r7 = java.lang.Integer.valueOf(r7);
        r3[r12] = r7;
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r3);
    L_0x00e8:
        r3 = r2;
    L_0x00e9:
        if (r3 >= r15) goto L_0x0139;
    L_0x00eb:
        r0 = r5.poll();
        r0 = (java.lang.Runnable) r0;
        if (r0 == 0) goto L_0x0139;
    L_0x00f3:
        r7 = r14.f15740h;
        monitor-enter(r7);
        r8 = r14.f15751s;	 Catch:{ all -> 0x012b }
        if (r8 < r12) goto L_0x0104;
    L_0x00fa:
        if (r4 == 0) goto L_0x0104;
    L_0x00fc:
        r4.m17741a(r0);	 Catch:{ all -> 0x012b }
        monitor-exit(r7);	 Catch:{ all -> 0x012b }
    L_0x0100:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x00e9;
    L_0x0104:
        monitor-exit(r7);
        r7 = "[UploadManager] Create and start a new thread to execute a upload task: %s";
        r8 = new java.lang.Object[r11];
        r9 = "BUGLY_ASYNC_UPLOAD";
        r8[r2] = r9;
        com.tencent.bugly.proguard.C4475w.m17747a(r7, r8);
        r7 = new com.tencent.bugly.proguard.t$1;
        r7.<init>(r14, r0);
        r8 = "BUGLY_ASYNC_UPLOAD";
        r7 = com.tencent.bugly.proguard.C4479y.m17784a(r7, r8);
        if (r7 == 0) goto L_0x012e;
    L_0x011d:
        r7 = r14.f15740h;
        monitor-enter(r7);
        r0 = r14.f15751s;	 Catch:{ all -> 0x0128 }
        r0 = r0 + 1;
        r14.f15751s = r0;	 Catch:{ all -> 0x0128 }
        monitor-exit(r7);	 Catch:{ all -> 0x0128 }
        goto L_0x0100;
    L_0x0128:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x012b:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x012e:
        r7 = "[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.";
        r8 = new java.lang.Object[r2];
        com.tencent.bugly.proguard.C4475w.m17752d(r7, r8);
        r14.m17711a(r0, r11);
        goto L_0x0100;
    L_0x0139:
        if (r1 <= 0) goto L_0x015c;
    L_0x013b:
        r0 = "[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r13];
        r5 = java.lang.Integer.valueOf(r1);
        r3[r2] = r5;
        r2 = android.os.Process.myPid();
        r2 = java.lang.Integer.valueOf(r2);
        r3[r11] = r2;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r3[r12] = r2;
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r3);
    L_0x015c:
        if (r4 == 0) goto L_0x000d;
    L_0x015e:
        r0 = new com.tencent.bugly.proguard.t$2;
        r0.<init>(r14, r1, r6);
        r4.m17741a(r0);
        goto L_0x000d;
    L_0x0168:
        r1 = r0;
        goto L_0x0068;
    L_0x016b:
        r15 = r1;
        goto L_0x005f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.t.c(int):void");
    }

    /* renamed from: a */
    private boolean m17711a(Runnable runnable, boolean z) {
        if (runnable == null) {
            C4475w.m17747a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            C4475w.m17751c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.f15740h) {
                if (z) {
                    this.f15738f.put(runnable);
                } else {
                    this.f15739g.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            C4475w.m17753e("[UploadManager] Failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    private void m17708a(Runnable runnable, long j) {
        if (runnable == null) {
            C4475w.m17752d("[UploadManager] Upload task should not be null", new Object[0]);
            return;
        }
        C4475w.m17751c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread a = C4479y.m17784a(runnable, "BUGLY_SYNC_UPLOAD");
        if (a == null) {
            C4475w.m17753e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
            m17711a(runnable, true);
            return;
        }
        try {
            a.join(j);
        } catch (Throwable th) {
            C4475w.m17753e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", th.getMessage());
            m17711a(runnable, true);
            m17714c(0);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    private void m17709a(java.lang.Runnable r7, boolean r8, boolean r9, long r10) {
        /*
        r6 = this;
        r5 = 2;
        r3 = 1;
        r4 = 0;
        if (r7 != 0) goto L_0x000c;
    L_0x0005:
        r0 = "[UploadManager] Upload task should not be null";
        r1 = new java.lang.Object[r4];
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r1);
    L_0x000c:
        r0 = "[UploadManager] Add upload task (pid=%d | tid=%d)";
        r1 = new java.lang.Object[r5];
        r2 = android.os.Process.myPid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r4] = r2;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r3] = r2;
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);
        r0 = r6.f15746n;
        if (r0 == 0) goto L_0x0077;
    L_0x002b:
        r0 = r6.m17732b();
        if (r0 == 0) goto L_0x0059;
    L_0x0031:
        r0 = "[UploadManager] Sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)";
        r1 = new java.lang.Object[r5];
        r2 = android.os.Process.myPid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r4] = r2;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r3] = r2;
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);
        if (r9 == 0) goto L_0x0052;
    L_0x004e:
        r6.m17708a(r7, r10);
    L_0x0051:
        return;
    L_0x0052:
        r6.m17711a(r7, r8);
        r6.m17714c(r4);
        goto L_0x0051;
    L_0x0059:
        r0 = "[UploadManager] Session ID is expired, drop it (pid=%d | tid=%d)";
        r1 = new java.lang.Object[r5];
        r2 = android.os.Process.myPid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r4] = r2;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r3] = r2;
        com.tencent.bugly.proguard.C4475w.m17747a(r0, r1);
        r6.m17731b(r4);
    L_0x0077:
        r1 = r6.f15750r;
        monitor-enter(r1);
        r0 = r6.f15749q;	 Catch:{ all -> 0x0083 }
        if (r0 == 0) goto L_0x0086;
    L_0x007e:
        r6.m17711a(r7, r8);	 Catch:{ all -> 0x0083 }
        monitor-exit(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x0051;
    L_0x0083:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x0086:
        r0 = 1;
        r6.f15749q = r0;	 Catch:{ all -> 0x0083 }
        monitor-exit(r1);	 Catch:{ all -> 0x0083 }
        r0 = "[UploadManager] Initialize security context now (pid=%d | tid=%d)";
        r1 = new java.lang.Object[r5];
        r2 = android.os.Process.myPid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r4] = r2;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r3] = r2;
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);
        if (r9 == 0) goto L_0x00b7;
    L_0x00a7:
        r0 = new com.tencent.bugly.proguard.t$a;
        r2 = r6.f15736d;
        r1 = r6;
        r3 = r7;
        r4 = r10;
        r0.<init>(r1, r2, r3, r4);
        r2 = 0;
        r6.m17708a(r0, r2);
        goto L_0x0051;
    L_0x00b7:
        r6.m17711a(r7, r8);
        r0 = new com.tencent.bugly.proguard.t$a;
        r1 = r6.f15736d;
        r0.<init>(r6, r1);
        r1 = "[UploadManager] Create and start a new thread to execute a task of initializing security context: %s";
        r2 = new java.lang.Object[r3];
        r3 = "BUGLY_ASYNC_UPLOAD";
        r2[r4] = r3;
        com.tencent.bugly.proguard.C4475w.m17747a(r1, r2);
        r1 = "BUGLY_ASYNC_UPLOAD";
        r1 = com.tencent.bugly.proguard.C4479y.m17784a(r0, r1);
        if (r1 != 0) goto L_0x0051;
    L_0x00d4:
        r1 = "[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.";
        r2 = new java.lang.Object[r4];
        com.tencent.bugly.proguard.C4475w.m17752d(r1, r2);
        r1 = com.tencent.bugly.proguard.C4474v.m17740a();
        if (r1 == 0) goto L_0x00e6;
    L_0x00e1:
        r1.m17741a(r0);
        goto L_0x0051;
    L_0x00e6:
        r0 = "[UploadManager] Asynchronous thread pool is unavailable now, try next time.";
        r1 = new java.lang.Object[r4];
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);
        r1 = r6.f15750r;
        monitor-enter(r1);
        r0 = 0;
        r6.f15749q = r0;	 Catch:{ all -> 0x00f6 }
        monitor-exit(r1);	 Catch:{ all -> 0x00f6 }
        goto L_0x0051;
    L_0x00f6:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.t.a(java.lang.Runnable, boolean, boolean, long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final void m17727a(int r9, com.tencent.bugly.proguard.am r10) {
        /*
        r8 = this;
        r4 = 2;
        r1 = 1;
        r2 = 0;
        r0 = r8.f15734a;
        if (r0 != 0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        if (r9 != r4) goto L_0x003e;
    L_0x000a:
        r0 = "[UploadManager] Session ID is invalid, will clear security context (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r4];
        r4 = android.os.Process.myPid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r2] = r4;
        r2 = android.os.Process.myTid();
        r2 = java.lang.Integer.valueOf(r2);
        r3[r1] = r2;
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r3);
        r8.m17731b(r1);
    L_0x0028:
        r1 = r8.f15750r;
        monitor-enter(r1);
        r0 = r8.f15749q;	 Catch:{ all -> 0x003b }
        if (r0 == 0) goto L_0x0039;
    L_0x002f:
        r0 = 0;
        r8.f15749q = r0;	 Catch:{ all -> 0x003b }
        r0 = r8.f15736d;	 Catch:{ all -> 0x003b }
        r2 = "security_info";
        com.tencent.bugly.proguard.C4479y.m17807b(r0, r2);	 Catch:{ all -> 0x003b }
    L_0x0039:
        monitor-exit(r1);	 Catch:{ all -> 0x003b }
        goto L_0x0007;
    L_0x003b:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x003e:
        r3 = r8.f15750r;
        monitor-enter(r3);
        r0 = r8.f15749q;	 Catch:{ all -> 0x0047 }
        if (r0 != 0) goto L_0x004a;
    L_0x0045:
        monitor-exit(r3);	 Catch:{ all -> 0x0047 }
        goto L_0x0007;
    L_0x0047:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x004a:
        monitor-exit(r3);
        if (r10 == 0) goto L_0x012f;
    L_0x004d:
        r0 = "[UploadManager] Record security context (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r4];
        r4 = android.os.Process.myPid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r2] = r4;
        r4 = android.os.Process.myTid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r1] = r4;
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r3);
        r3 = r10.f15612g;	 Catch:{ Throwable -> 0x0118 }
        if (r3 == 0) goto L_0x0102;
    L_0x006c:
        r0 = "S1";
        r0 = r3.containsKey(r0);	 Catch:{ Throwable -> 0x0118 }
        if (r0 == 0) goto L_0x0102;
    L_0x0074:
        r0 = "S2";
        r0 = r3.containsKey(r0);	 Catch:{ Throwable -> 0x0118 }
        if (r0 == 0) goto L_0x0102;
    L_0x007c:
        r4 = r10.f15610e;	 Catch:{ Throwable -> 0x0118 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0118 }
        r4 = r4 - r6;
        r8.f15743k = r4;	 Catch:{ Throwable -> 0x0118 }
        r0 = "[UploadManager] Time lag of server is: %d";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0118 }
        r5 = 0;
        r6 = r8.f15743k;	 Catch:{ Throwable -> 0x0118 }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Throwable -> 0x0118 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0118 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r4);	 Catch:{ Throwable -> 0x0118 }
        r0 = "S1";
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x0118 }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x0118 }
        r8.f15746n = r0;	 Catch:{ Throwable -> 0x0118 }
        r0 = "[UploadManager] Session ID from server is: %s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0118 }
        r5 = 0;
        r6 = r8.f15746n;	 Catch:{ Throwable -> 0x0118 }
        r4[r5] = r6;	 Catch:{ Throwable -> 0x0118 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r4);	 Catch:{ Throwable -> 0x0118 }
        r0 = r8.f15746n;	 Catch:{ Throwable -> 0x0118 }
        r0 = r0.length();	 Catch:{ Throwable -> 0x0118 }
        if (r0 <= 0) goto L_0x0126;
    L_0x00b5:
        r0 = "S2";
        r0 = r3.get(r0);	 Catch:{ NumberFormatException -> 0x0109 }
        r0 = (java.lang.String) r0;	 Catch:{ NumberFormatException -> 0x0109 }
        r4 = java.lang.Long.parseLong(r0);	 Catch:{ NumberFormatException -> 0x0109 }
        r8.f15747o = r4;	 Catch:{ NumberFormatException -> 0x0109 }
        r0 = "[UploadManager] Session expired time from server is: %d(%s)";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ NumberFormatException -> 0x0109 }
        r4 = 0;
        r6 = r8.f15747o;	 Catch:{ NumberFormatException -> 0x0109 }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ NumberFormatException -> 0x0109 }
        r3[r4] = r5;	 Catch:{ NumberFormatException -> 0x0109 }
        r4 = 1;
        r5 = new java.util.Date;	 Catch:{ NumberFormatException -> 0x0109 }
        r6 = r8.f15747o;	 Catch:{ NumberFormatException -> 0x0109 }
        r5.<init>(r6);	 Catch:{ NumberFormatException -> 0x0109 }
        r5 = r5.toString();	 Catch:{ NumberFormatException -> 0x0109 }
        r3[r4] = r5;	 Catch:{ NumberFormatException -> 0x0109 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r3);	 Catch:{ NumberFormatException -> 0x0109 }
        r4 = r8.f15747o;	 Catch:{ NumberFormatException -> 0x0109 }
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x00f7;
    L_0x00ea:
        r0 = "[UploadManager] Session expired time from server is less than 1 second, will set to default value";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ NumberFormatException -> 0x0109 }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r3);	 Catch:{ NumberFormatException -> 0x0109 }
        r4 = 259200000; // 0xf731400 float:1.1984677E-29 double:1.280618154E-315;
        r8.f15747o = r4;	 Catch:{ NumberFormatException -> 0x0109 }
    L_0x00f7:
        r0 = r8.m17718d();	 Catch:{ Throwable -> 0x0118 }
        if (r0 == 0) goto L_0x011d;
    L_0x00fd:
        r1 = r2;
    L_0x00fe:
        r0 = 0;
        r8.m17714c(r0);	 Catch:{ Throwable -> 0x0118 }
    L_0x0102:
        if (r1 == 0) goto L_0x0028;
    L_0x0104:
        r8.m17731b(r2);
        goto L_0x0028;
    L_0x0109:
        r0 = move-exception;
        r0 = "[UploadManager] Session expired time is invalid, will set to default value";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0118 }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r3);	 Catch:{ Throwable -> 0x0118 }
        r4 = 259200000; // 0xf731400 float:1.1984677E-29 double:1.280618154E-315;
        r8.f15747o = r4;	 Catch:{ Throwable -> 0x0118 }
        goto L_0x00f7;
    L_0x0118:
        r0 = move-exception;
        com.tencent.bugly.proguard.C4475w.m17748a(r0);
        goto L_0x0102;
    L_0x011d:
        r0 = "[UploadManager] Failed to record database";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0118 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r3);	 Catch:{ Throwable -> 0x0118 }
        goto L_0x00fe;
    L_0x0126:
        r0 = "[UploadManager] Session ID from server is invalid, try next time";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0118 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r3);	 Catch:{ Throwable -> 0x0118 }
        goto L_0x0102;
    L_0x012f:
        r0 = "[UploadManager] Fail to init security context and clear local info (pid=%d | tid=%d)";
        r3 = new java.lang.Object[r4];
        r4 = android.os.Process.myPid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r2] = r4;
        r4 = android.os.Process.myTid();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r1] = r4;
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r3);
        r8.m17731b(r2);
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.t.a(int, com.tencent.bugly.proguard.am):void");
    }

    /* renamed from: a */
    public final byte[] m17730a(byte[] bArr) {
        if (this.f15744l != null && (this.f15744l.length << 3) == 128) {
            return C4479y.m17794a(1, bArr, this.f15744l);
        }
        C4475w.m17752d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    /* renamed from: b */
    public final byte[] m17734b(byte[] bArr) {
        if (this.f15744l != null && (this.f15744l.length << 3) == 128) {
            return C4479y.m17794a(2, bArr, this.f15744l);
        }
        C4475w.m17752d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    /* renamed from: a */
    public final boolean m17729a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        C4475w.m17751c("[UploadManager] Integrate security to HTTP headers (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.f15746n != null) {
            map.put("secureSessionId", this.f15746n);
            return true;
        } else if (this.f15744l == null || (this.f15744l.length << 3) != 128) {
            C4475w.m17752d("[UploadManager] AES key is invalid", new Object[0]);
            return false;
        } else {
            if (this.f15742j == null) {
                this.f15742j = Base64.decode(this.f15741i, 0);
                if (this.f15742j == null) {
                    C4475w.m17752d("[UploadManager] Failed to decode RSA public key", new Object[0]);
                    return false;
                }
            }
            byte[] b = C4479y.m17808b(1, this.f15744l, this.f15742j);
            if (b == null) {
                C4475w.m17752d("[UploadManager] Failed to encrypt AES key", new Object[0]);
                return false;
            }
            String encodeToString = Base64.encodeToString(b, 0);
            if (encodeToString == null) {
                C4475w.m17752d("[UploadManager] Failed to encode AES key", new Object[0]);
                return false;
            }
            map.put("raKey", encodeToString);
            return true;
        }
    }
}
