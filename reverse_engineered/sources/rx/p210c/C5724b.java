package rx.p210c;

import rx.C5719b;
import rx.exceptions.C5736a;
import rx.internal.operators.NotificationLite;

/* compiled from: SerializedObserver */
/* renamed from: rx.c.b */
public class C5724b<T> implements C5719b<T> {
    /* renamed from: a */
    private final C5719b<? super T> f18280a;
    /* renamed from: b */
    private boolean f18281b;
    /* renamed from: c */
    private volatile boolean f18282c;
    /* renamed from: d */
    private C5723a f18283d;
    /* renamed from: e */
    private final NotificationLite<T> f18284e = NotificationLite.m20864a();

    /* compiled from: SerializedObserver */
    /* renamed from: rx.c.b$a */
    static final class C5723a {
        /* renamed from: a */
        Object[] f18278a;
        /* renamed from: b */
        int f18279b;

        C5723a() {
        }

        /* renamed from: a */
        public void m20824a(Object obj) {
            Object[] objArr;
            int i = this.f18279b;
            Object obj2 = this.f18278a;
            if (obj2 == null) {
                objArr = new Object[16];
                this.f18278a = objArr;
            } else if (i == obj2.length) {
                objArr = new Object[((i >> 2) + i)];
                System.arraycopy(obj2, 0, objArr, 0, i);
                this.f18278a = objArr;
            } else {
                Object obj3 = obj2;
            }
            objArr[i] = obj;
            this.f18279b = i + 1;
        }
    }

    public C5724b(C5719b<? super T> c5719b) {
        this.f18280a = c5719b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public void mo7150a(T r10) {
        /*
        r9 = this;
        r1 = 0;
        r8 = 1;
        r0 = r9.f18282c;
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        monitor-enter(r9);
        r0 = r9.f18282c;	 Catch:{ all -> 0x000e }
        if (r0 == 0) goto L_0x0011;
    L_0x000c:
        monitor-exit(r9);	 Catch:{ all -> 0x000e }
        goto L_0x0006;
    L_0x000e:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x000e }
        throw r0;
    L_0x0011:
        r0 = r9.f18281b;	 Catch:{ all -> 0x000e }
        if (r0 == 0) goto L_0x002b;
    L_0x0015:
        r0 = r9.f18283d;	 Catch:{ all -> 0x000e }
        if (r0 != 0) goto L_0x0020;
    L_0x0019:
        r0 = new rx.c.b$a;	 Catch:{ all -> 0x000e }
        r0.<init>();	 Catch:{ all -> 0x000e }
        r9.f18283d = r0;	 Catch:{ all -> 0x000e }
    L_0x0020:
        r1 = r9.f18284e;	 Catch:{ all -> 0x000e }
        r1 = r1.m20865a(r10);	 Catch:{ all -> 0x000e }
        r0.m20824a(r1);	 Catch:{ all -> 0x000e }
        monitor-exit(r9);	 Catch:{ all -> 0x000e }
        goto L_0x0006;
    L_0x002b:
        r0 = 1;
        r9.f18281b = r0;	 Catch:{ all -> 0x000e }
        monitor-exit(r9);	 Catch:{ all -> 0x000e }
        r0 = r9.f18280a;	 Catch:{ Throwable -> 0x0046 }
        r0.mo7150a(r10);	 Catch:{ Throwable -> 0x0046 }
    L_0x0034:
        r2 = r1;
    L_0x0035:
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        if (r2 >= r0) goto L_0x0034;
    L_0x0039:
        monitor-enter(r9);
        r0 = r9.f18283d;	 Catch:{ all -> 0x0043 }
        if (r0 != 0) goto L_0x004f;
    L_0x003e:
        r0 = 0;
        r9.f18281b = r0;	 Catch:{ all -> 0x0043 }
        monitor-exit(r9);	 Catch:{ all -> 0x0043 }
        goto L_0x0006;
    L_0x0043:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x0043 }
        throw r0;
    L_0x0046:
        r0 = move-exception;
        r9.f18282c = r8;
        r1 = r9.f18280a;
        rx.exceptions.C5736a.m20861a(r0, r1, r10);
        goto L_0x0006;
    L_0x004f:
        r3 = 0;
        r9.f18283d = r3;	 Catch:{ all -> 0x0043 }
        monitor-exit(r9);	 Catch:{ all -> 0x0043 }
        r3 = r0.f18278a;
        r4 = r3.length;
        r0 = r1;
    L_0x0057:
        if (r0 >= r4) goto L_0x005d;
    L_0x0059:
        r5 = r3[r0];
        if (r5 != 0) goto L_0x0061;
    L_0x005d:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0035;
    L_0x0061:
        r6 = r9.f18284e;	 Catch:{ Throwable -> 0x006f }
        r7 = r9.f18280a;	 Catch:{ Throwable -> 0x006f }
        r5 = r6.m20867a(r7, r5);	 Catch:{ Throwable -> 0x006f }
        if (r5 == 0) goto L_0x007f;
    L_0x006b:
        r0 = 1;
        r9.f18282c = r0;	 Catch:{ Throwable -> 0x006f }
        goto L_0x0006;
    L_0x006f:
        r0 = move-exception;
        r9.f18282c = r8;
        rx.exceptions.C5736a.m20858a(r0);
        r1 = r9.f18280a;
        r0 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r0, r10);
        r1.mo7151a(r0);
        goto L_0x0006;
    L_0x007f:
        r0 = r0 + 1;
        goto L_0x0057;
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.c.b.a(java.lang.Object):void");
    }

    /* renamed from: a */
    public void mo7151a(Throwable th) {
        C5736a.m20858a(th);
        if (!this.f18282c) {
            synchronized (this) {
                if (this.f18282c) {
                    return;
                }
                this.f18282c = true;
                if (this.f18281b) {
                    C5723a c5723a = this.f18283d;
                    if (c5723a == null) {
                        c5723a = new C5723a();
                        this.f18283d = c5723a;
                    }
                    c5723a.m20824a(this.f18284e.m20866a(th));
                    return;
                }
                this.f18281b = true;
                this.f18280a.mo7151a(th);
            }
        }
    }

    /* renamed from: a */
    public void mo7149a() {
        if (!this.f18282c) {
            synchronized (this) {
                if (this.f18282c) {
                    return;
                }
                this.f18282c = true;
                if (this.f18281b) {
                    C5723a c5723a = this.f18283d;
                    if (c5723a == null) {
                        c5723a = new C5723a();
                        this.f18283d = c5723a;
                    }
                    c5723a.m20824a(this.f18284e.m20868b());
                    return;
                }
                this.f18281b = true;
                this.f18280a.mo7149a();
            }
        }
    }
}
