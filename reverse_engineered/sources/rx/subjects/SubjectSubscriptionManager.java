package rx.subjects;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.C5717a.C5695a;
import rx.C5719b;
import rx.C5721e;
import rx.internal.operators.NotificationLite;
import rx.p208a.C5694b;
import rx.p208a.C5711a;
import rx.p208a.C5714d;
import rx.subscriptions.Subscriptions;

final class SubjectSubscriptionManager<T> extends AtomicReference<C5837a<T>> implements C5695a<T> {
    private static final long serialVersionUID = 6035251036011671568L;
    /* renamed from: a */
    volatile Object f18541a;
    /* renamed from: b */
    boolean f18542b = true;
    /* renamed from: c */
    C5694b<C5838b<T>> f18543c = C5714d.m20795a();
    /* renamed from: d */
    C5694b<C5838b<T>> f18544d = C5714d.m20795a();
    /* renamed from: e */
    C5694b<C5838b<T>> f18545e = C5714d.m20795a();
    public final NotificationLite<T> nl = NotificationLite.m20864a();

    /* renamed from: rx.subjects.SubjectSubscriptionManager$a */
    protected static final class C5837a<T> {
        /* renamed from: c */
        static final C5838b[] f18531c = new C5838b[0];
        /* renamed from: d */
        static final C5837a f18532d = new C5837a(true, f18531c);
        /* renamed from: e */
        static final C5837a f18533e = new C5837a(false, f18531c);
        /* renamed from: a */
        final boolean f18534a;
        /* renamed from: b */
        final C5838b[] f18535b;

        public C5837a(boolean z, C5838b[] c5838bArr) {
            this.f18534a = z;
            this.f18535b = c5838bArr;
        }

        /* renamed from: a */
        public C5837a m21042a(C5838b c5838b) {
            int length = this.f18535b.length;
            Object obj = new C5838b[(length + 1)];
            System.arraycopy(this.f18535b, 0, obj, 0, length);
            obj[length] = c5838b;
            return new C5837a(this.f18534a, obj);
        }

        /* renamed from: b */
        public C5837a m21043b(C5838b c5838b) {
            C5838b[] c5838bArr = this.f18535b;
            int length = c5838bArr.length;
            if (length == 1 && c5838bArr[0] == c5838b) {
                return f18533e;
            }
            if (length == 0) {
                return this;
            }
            Object obj = new C5838b[(length - 1)];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3;
                C5838b c5838b2 = c5838bArr[i];
                if (c5838b2 == c5838b) {
                    i3 = i2;
                } else if (i2 == length - 1) {
                    return this;
                } else {
                    i3 = i2 + 1;
                    obj[i2] = c5838b2;
                }
                i++;
                i2 = i3;
            }
            if (i2 == 0) {
                return f18533e;
            }
            C5838b[] c5838bArr2;
            if (i2 < length - 1) {
                c5838bArr2 = new C5838b[i2];
                System.arraycopy(obj, 0, c5838bArr2, 0, i2);
            } else {
                Object obj2 = obj;
            }
            return new C5837a(this.f18534a, c5838bArr2);
        }
    }

    /* renamed from: rx.subjects.SubjectSubscriptionManager$b */
    protected static final class C5838b<T> implements C5719b<T> {
        /* renamed from: a */
        final C5721e<? super T> f18536a;
        /* renamed from: b */
        boolean f18537b = true;
        /* renamed from: c */
        boolean f18538c;
        /* renamed from: d */
        List<Object> f18539d;
        /* renamed from: e */
        boolean f18540e;

        public C5838b(C5721e<? super T> c5721e) {
            this.f18536a = c5721e;
        }

        /* renamed from: a */
        public void mo7150a(T t) {
            this.f18536a.mo7150a((Object) t);
        }

        /* renamed from: a */
        public void mo7151a(Throwable th) {
            this.f18536a.mo7151a(th);
        }

        /* renamed from: a */
        public void mo7149a() {
            this.f18536a.mo7149a();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        /* renamed from: a */
        protected void m21046a(java.lang.Object r2, rx.internal.operators.NotificationLite<T> r3) {
            /*
            r1 = this;
            r0 = r1.f18540e;
            if (r0 != 0) goto L_0x0022;
        L_0x0004:
            monitor-enter(r1);
            r0 = 0;
            r1.f18537b = r0;	 Catch:{ all -> 0x0028 }
            r0 = r1.f18538c;	 Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x001e;
        L_0x000c:
            r0 = r1.f18539d;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x0017;
        L_0x0010:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0028 }
            r0.<init>();	 Catch:{ all -> 0x0028 }
            r1.f18539d = r0;	 Catch:{ all -> 0x0028 }
        L_0x0017:
            r0 = r1.f18539d;	 Catch:{ all -> 0x0028 }
            r0.add(r2);	 Catch:{ all -> 0x0028 }
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
        L_0x001d:
            return;
        L_0x001e:
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            r0 = 1;
            r1.f18540e = r0;
        L_0x0022:
            r0 = r1.f18536a;
            r3.m20867a(r0, r2);
            goto L_0x001d;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.SubjectSubscriptionManager.b.a(java.lang.Object, rx.internal.operators.NotificationLite):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        /* renamed from: b */
        protected void m21049b(java.lang.Object r3, rx.internal.operators.NotificationLite<T> r4) {
            /*
            r2 = this;
            r0 = 0;
            monitor-enter(r2);
            r1 = r2.f18537b;	 Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x000a;
        L_0x0006:
            r1 = r2.f18538c;	 Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x000c;
        L_0x000a:
            monitor-exit(r2);	 Catch:{ all -> 0x001c }
        L_0x000b:
            return;
        L_0x000c:
            r1 = 0;
            r2.f18537b = r1;	 Catch:{ all -> 0x001c }
            if (r3 == 0) goto L_0x0012;
        L_0x0011:
            r0 = 1;
        L_0x0012:
            r2.f18538c = r0;	 Catch:{ all -> 0x001c }
            monitor-exit(r2);	 Catch:{ all -> 0x001c }
            if (r3 == 0) goto L_0x000b;
        L_0x0017:
            r0 = 0;
            r2.m21048a(r0, r3, r4);
            goto L_0x000b;
        L_0x001c:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x001c }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.SubjectSubscriptionManager.b.b(java.lang.Object, rx.internal.operators.NotificationLite):void");
        }

        /* renamed from: a */
        protected void m21048a(List<Object> list, Object obj, NotificationLite<T> notificationLite) {
            Object obj2 = 1;
            Object obj3 = null;
            Object obj4 = 1;
            while (true) {
                if (list != null) {
                    for (Object c : list) {
                        m21050c(c, notificationLite);
                    }
                }
                if (obj4 != null) {
                    try {
                        m21050c(obj, notificationLite);
                        obj4 = null;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                    }
                }
                synchronized (this) {
                    list = this.f18539d;
                    this.f18539d = null;
                    if (list == null) {
                        this.f18538c = false;
                        try {
                            return;
                        } catch (Throwable th3) {
                            th2 = th3;
                            try {
                                throw th2;
                            } catch (Throwable th4) {
                                th2 = th4;
                                obj3 = obj2;
                            }
                        }
                    } else {
                        try {
                        } catch (Throwable th5) {
                            th2 = th5;
                            obj2 = null;
                        }
                    }
                }
            }
            if (obj3 == null) {
                synchronized (this) {
                    this.f18538c = false;
                }
            }
            throw th2;
        }

        /* renamed from: c */
        protected void m21050c(Object obj, NotificationLite<T> notificationLite) {
            if (obj != null) {
                notificationLite.m20867a(this.f18536a, obj);
            }
        }
    }

    public SubjectSubscriptionManager() {
        super(C5837a.f18533e);
    }

    public void call(C5721e<? super T> c5721e) {
        C5838b c5838b = new C5838b(c5721e);
        m21053a(c5721e, c5838b);
        this.f18543c.call(c5838b);
        if (!c5721e.isUnsubscribed() && m21054a(c5838b) && c5721e.isUnsubscribed()) {
            m21055b(c5838b);
        }
    }

    /* renamed from: a */
    void m21053a(C5721e<? super T> c5721e, final C5838b<T> c5838b) {
        c5721e.m20818a(Subscriptions.create(new C5711a(this) {
            /* renamed from: b */
            final /* synthetic */ SubjectSubscriptionManager f18530b;

            public void call() {
                this.f18530b.m21055b(c5838b);
            }
        }));
    }

    /* renamed from: a */
    void m21052a(Object obj) {
        this.f18541a = obj;
    }

    /* renamed from: a */
    Object m21051a() {
        return this.f18541a;
    }

    /* renamed from: b */
    C5838b<T>[] m21056b() {
        return ((C5837a) get()).f18535b;
    }

    /* renamed from: a */
    boolean m21054a(C5838b<T> c5838b) {
        C5837a c5837a;
        do {
            c5837a = (C5837a) get();
            if (c5837a.f18534a) {
                this.f18545e.call(c5838b);
                return false;
            }
        } while (!compareAndSet(c5837a, c5837a.m21042a(c5838b)));
        this.f18544d.call(c5838b);
        return true;
    }

    /* renamed from: b */
    void m21055b(C5838b<T> c5838b) {
        C5837a c5837a;
        C5837a b;
        do {
            c5837a = (C5837a) get();
            if (!c5837a.f18534a) {
                b = c5837a.m21043b(c5838b);
                if (b == c5837a) {
                    return;
                }
            } else {
                return;
            }
        } while (!compareAndSet(c5837a, b));
    }

    /* renamed from: b */
    C5838b<T>[] m21057b(Object obj) {
        m21052a(obj);
        this.f18542b = false;
        if (((C5837a) get()).f18534a) {
            return C5837a.f18531c;
        }
        return ((C5837a) getAndSet(C5837a.f18532d)).f18535b;
    }
}
