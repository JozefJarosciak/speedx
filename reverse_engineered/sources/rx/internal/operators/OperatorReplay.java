package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.C5717a;
import rx.C5717a.C5695a;
import rx.C5720f;
import rx.C5721e;
import rx.C5725c;
import rx.C5733d;
import rx.internal.util.C5828c;
import rx.p208a.C5711a;
import rx.p208a.C5715e;
import rx.p209b.C5718a;
import rx.p212e.C5734a;
import rx.subscriptions.Subscriptions;

public final class OperatorReplay<T> extends C5718a<T> {
    /* renamed from: f */
    static final C5715e f18348f = new C57391();
    /* renamed from: c */
    final C5717a<? extends T> f18349c;
    /* renamed from: d */
    final AtomicReference<C5745b<T>> f18350d;
    /* renamed from: e */
    final C5715e<? extends C5743a<T>> f18351e;

    /* renamed from: rx.internal.operators.OperatorReplay$1 */
    static class C57391 implements C5715e {
        C57391() {
        }

        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    }

    /* renamed from: rx.internal.operators.OperatorReplay$a */
    interface C5743a<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerProducer<T> innerProducer);
    }

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements C5743a<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        /* renamed from: a */
        final NotificationLite<T> f18311a = NotificationLite.m20864a();
        /* renamed from: b */
        Node f18312b;
        /* renamed from: c */
        int f18313c;
        /* renamed from: d */
        long f18314d;

        public BoundedReplayBuffer() {
            Node node = new Node(null, 0);
            this.f18312b = node;
            set(node);
        }

        /* renamed from: a */
        final void m20877a(Node node) {
            this.f18312b.set(node);
            this.f18312b = node;
            this.f18313c++;
        }

        /* renamed from: a */
        final void m20876a() {
            Node node = (Node) ((Node) get()).get();
            if (node == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.f18313c--;
            m20880b(node);
        }

        /* renamed from: b */
        final void m20880b(Node node) {
            set(node);
        }

        public final void next(T t) {
            Object a = mo7160a(this.f18311a.m20865a((Object) t));
            long j = this.f18314d + 1;
            this.f18314d = j;
            m20877a(new Node(a, j));
            mo7162b();
        }

        public final void error(Throwable th) {
            Object a = mo7160a(this.f18311a.m20866a(th));
            long j = this.f18314d + 1;
            this.f18314d = j;
            m20877a(new Node(a, j));
            mo7163c();
        }

        public final void complete() {
            Object a = mo7160a(this.f18311a.m20868b());
            long j = this.f18314d + 1;
            this.f18314d = j;
            m20877a(new Node(a, j));
            mo7163c();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void replay(rx.internal.operators.OperatorReplay.InnerProducer<T> r11) {
            /*
            r10 = this;
            monitor-enter(r11);
            r0 = r11.f18319e;	 Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r11.f18320f = r0;	 Catch:{ all -> 0x0090 }
            monitor-exit(r11);	 Catch:{ all -> 0x0090 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = 1;
            r11.f18319e = r0;	 Catch:{ all -> 0x0090 }
            monitor-exit(r11);	 Catch:{ all -> 0x0090 }
        L_0x000e:
            r0 = r11.isUnsubscribed();
            if (r0 != 0) goto L_0x0009;
        L_0x0014:
            r4 = r11.get();
            r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
            if (r0 != 0) goto L_0x0093;
        L_0x0021:
            r0 = 1;
            r1 = r0;
        L_0x0023:
            r2 = 0;
            r0 = r11.m20882a();
            r0 = (rx.internal.operators.OperatorReplay.Node) r0;
            if (r0 != 0) goto L_0x003a;
        L_0x002d:
            r0 = r10.get();
            r0 = (rx.internal.operators.OperatorReplay.Node) r0;
            r11.f18317c = r0;
            r6 = r0.f18322b;
            r11.m20883a(r6);
        L_0x003a:
            r6 = r11.isUnsubscribed();
            if (r6 != 0) goto L_0x0009;
        L_0x0040:
            r6 = r4;
            r4 = r2;
            r2 = r0;
        L_0x0043:
            r8 = 0;
            r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
            if (r0 == 0) goto L_0x00a7;
        L_0x0049:
            r0 = r2.get();
            r0 = (rx.internal.operators.OperatorReplay.Node) r0;
            if (r0 == 0) goto L_0x00a7;
        L_0x0051:
            r2 = r0.f18321a;
            r2 = r10.mo7161b(r2);
            r3 = r10.f18311a;	 Catch:{ Throwable -> 0x0065 }
            r8 = r11.f18316b;	 Catch:{ Throwable -> 0x0065 }
            r3 = r3.m20867a(r8, r2);	 Catch:{ Throwable -> 0x0065 }
            if (r3 == 0) goto L_0x0096;
        L_0x0061:
            r0 = 0;
            r11.f18317c = r0;	 Catch:{ Throwable -> 0x0065 }
            goto L_0x0009;
        L_0x0065:
            r0 = move-exception;
            r1 = 0;
            r11.f18317c = r1;
            rx.exceptions.C5736a.m20858a(r0);
            r11.unsubscribe();
            r1 = r10.f18311a;
            r1 = r1.m20870c(r2);
            if (r1 != 0) goto L_0x0009;
        L_0x0077:
            r1 = r10.f18311a;
            r1 = r1.m20869b(r2);
            if (r1 != 0) goto L_0x0009;
        L_0x007f:
            r1 = r11.f18316b;
            r3 = r10.f18311a;
            r2 = r3.m20871d(r2);
            r0 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r0, r2);
            r1.mo7151a(r0);
            goto L_0x0009;
        L_0x0090:
            r0 = move-exception;
            monitor-exit(r11);	 Catch:{ all -> 0x0090 }
            throw r0;
        L_0x0093:
            r0 = 0;
            r1 = r0;
            goto L_0x0023;
        L_0x0096:
            r2 = 1;
            r2 = r2 + r4;
            r4 = 1;
            r4 = r6 - r4;
            r6 = r11.isUnsubscribed();
            if (r6 != 0) goto L_0x0009;
        L_0x00a3:
            r6 = r4;
            r4 = r2;
            r2 = r0;
            goto L_0x0043;
        L_0x00a7:
            r6 = 0;
            r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r0 == 0) goto L_0x00b4;
        L_0x00ad:
            r11.f18317c = r2;
            if (r1 != 0) goto L_0x00b4;
        L_0x00b1:
            r11.produced(r4);
        L_0x00b4:
            monitor-enter(r11);
            r0 = r11.f18320f;	 Catch:{ all -> 0x00bf }
            if (r0 != 0) goto L_0x00c2;
        L_0x00b9:
            r0 = 0;
            r11.f18319e = r0;	 Catch:{ all -> 0x00bf }
            monitor-exit(r11);	 Catch:{ all -> 0x00bf }
            goto L_0x0009;
        L_0x00bf:
            r0 = move-exception;
            monitor-exit(r11);	 Catch:{ all -> 0x00bf }
            throw r0;
        L_0x00c2:
            r0 = 0;
            r11.f18320f = r0;	 Catch:{ all -> 0x00bf }
            monitor-exit(r11);	 Catch:{ all -> 0x00bf }
            goto L_0x000e;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.BoundedReplayBuffer.replay(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }

        /* renamed from: a */
        Object mo7160a(Object obj) {
            return obj;
        }

        /* renamed from: b */
        Object mo7161b(Object obj) {
            return obj;
        }

        /* renamed from: b */
        void mo7162b() {
        }

        /* renamed from: c */
        void mo7163c() {
        }
    }

    static final class InnerProducer<T> extends AtomicLong implements C5725c, C5720f {
        private static final long serialVersionUID = -4453897557930727610L;
        /* renamed from: a */
        final C5745b<T> f18315a;
        /* renamed from: b */
        final C5721e<? super T> f18316b;
        /* renamed from: c */
        Object f18317c;
        /* renamed from: d */
        final AtomicLong f18318d = new AtomicLong();
        /* renamed from: e */
        boolean f18319e;
        /* renamed from: f */
        boolean f18320f;

        public InnerProducer(C5745b<T> c5745b, C5721e<? super T> c5721e) {
            this.f18315a = c5745b;
            this.f18316b = c5721e;
        }

        public void request(long j) {
            if (j >= 0) {
                long j2;
                long j3;
                do {
                    j2 = get();
                    if (j2 == Long.MIN_VALUE) {
                        return;
                    }
                    if (j2 < 0 || j != 0) {
                        j3 = j2 + j;
                        if (j3 < 0) {
                            j3 = Long.MAX_VALUE;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j3));
                m20883a(j);
                this.f18315a.m20897c(this);
                this.f18315a.f18332a.replay(this);
            }
        }

        /* renamed from: a */
        void m20883a(long j) {
            long j2;
            long j3;
            do {
                j2 = this.f18318d.get();
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!this.f18318d.compareAndSet(j2, j3));
        }

        public long produced(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            long j2;
            long j3;
            do {
                j3 = get();
                if (j3 == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                j2 = j3 - j;
                if (j2 < 0) {
                    throw new IllegalStateException("More produced (" + j + ") than requested (" + j3 + ")");
                }
            } while (!compareAndSet(j3, j2));
            return j2;
        }

        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        public void unsubscribe() {
            if (get() != Long.MIN_VALUE && getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.f18315a.m20895b(this);
                this.f18315a.m20897c(this);
            }
        }

        /* renamed from: a */
        <U> U m20882a() {
            return this.f18317c;
        }
    }

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        /* renamed from: a */
        final Object f18321a;
        /* renamed from: b */
        final long f18322b;

        public Node(Object obj, long j) {
            this.f18321a = obj;
            this.f18322b = j;
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        /* renamed from: e */
        final C5733d f18323e;
        /* renamed from: f */
        final long f18324f;
        /* renamed from: g */
        final int f18325g;

        public SizeAndTimeBoundReplayBuffer(int i, long j, C5733d c5733d) {
            this.f18323e = c5733d;
            this.f18325g = i;
            this.f18324f = j;
        }

        /* renamed from: a */
        Object mo7160a(Object obj) {
            return new C5734a(this.f18323e.m20847b(), obj);
        }

        /* renamed from: b */
        Object mo7161b(Object obj) {
            return ((C5734a) obj).m20849b();
        }

        /* renamed from: b */
        void mo7162b() {
            long b = this.f18323e.m20847b() - this.f18324f;
            Node node = (Node) get();
            Node node2 = node;
            int i = 0;
            Node node3 = (Node) node.get();
            while (node3 != null) {
                if (this.c <= this.f18325g) {
                    if (((C5734a) node3.f18321a).m20848a() > b) {
                        break;
                    }
                    i++;
                    this.c--;
                    node2 = node3;
                    node3 = (Node) node3.get();
                } else {
                    i++;
                    this.c--;
                    node2 = node3;
                    node3 = (Node) node3.get();
                }
            }
            if (i != 0) {
                m20880b(node2);
            }
        }

        /* renamed from: c */
        void mo7163c() {
            long b = this.f18323e.m20847b() - this.f18324f;
            Node node = (Node) get();
            Node node2 = node;
            int i = 0;
            Node node3 = (Node) node.get();
            while (node3 != null && this.c > 1 && ((C5734a) node3.f18321a).m20848a() <= b) {
                i++;
                this.c--;
                node2 = node3;
                node3 = (Node) node3.get();
            }
            if (i != 0) {
                m20880b(node2);
            }
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        /* renamed from: e */
        final int f18326e;

        public SizeBoundReplayBuffer(int i) {
            this.f18326e = i;
        }

        /* renamed from: b */
        void mo7162b() {
            if (this.c > this.f18326e) {
                m20876a();
            }
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements C5743a<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        /* renamed from: a */
        final NotificationLite<T> f18327a = NotificationLite.m20864a();
        /* renamed from: b */
        volatile int f18328b;

        public UnboundedReplayBuffer(int i) {
            super(i);
        }

        public void next(T t) {
            add(this.f18327a.m20865a((Object) t));
            this.f18328b++;
        }

        public void error(Throwable th) {
            add(this.f18327a.m20866a(th));
            this.f18328b++;
        }

        public void complete() {
            add(this.f18327a.m20868b());
            this.f18328b++;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay(rx.internal.operators.OperatorReplay.InnerProducer<T> r12) {
            /*
            r11 = this;
            monitor-enter(r12);
            r0 = r12.f18319e;	 Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r12.f18320f = r0;	 Catch:{ all -> 0x0050 }
            monitor-exit(r12);	 Catch:{ all -> 0x0050 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = 1;
            r12.f18319e = r0;	 Catch:{ all -> 0x0050 }
            monitor-exit(r12);	 Catch:{ all -> 0x0050 }
        L_0x000e:
            r0 = r12.isUnsubscribed();
            if (r0 != 0) goto L_0x0009;
        L_0x0014:
            r7 = r11.f18328b;
            r0 = r12.m20882a();
            r0 = (java.lang.Integer) r0;
            if (r0 == 0) goto L_0x0053;
        L_0x001e:
            r0 = r0.intValue();
        L_0x0022:
            r4 = r12.get();
            r2 = 0;
            r6 = r0;
            r0 = r2;
            r2 = r4;
        L_0x002b:
            r8 = 0;
            r8 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r8 == 0) goto L_0x007c;
        L_0x0031:
            if (r6 >= r7) goto L_0x007c;
        L_0x0033:
            r8 = r11.get(r6);
            r9 = r11.f18327a;	 Catch:{ Throwable -> 0x0055 }
            r10 = r12.f18316b;	 Catch:{ Throwable -> 0x0055 }
            r8 = r9.m20867a(r10, r8);	 Catch:{ Throwable -> 0x0055 }
            if (r8 != 0) goto L_0x0009;
        L_0x0041:
            r8 = r12.isUnsubscribed();
            if (r8 != 0) goto L_0x0009;
        L_0x0047:
            r6 = r6 + 1;
            r8 = 1;
            r2 = r2 - r8;
            r8 = 1;
            r0 = r0 + r8;
            goto L_0x002b;
        L_0x0050:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x0050 }
            throw r0;
        L_0x0053:
            r0 = 0;
            goto L_0x0022;
        L_0x0055:
            r0 = move-exception;
            rx.exceptions.C5736a.m20858a(r0);
            r12.unsubscribe();
            r1 = r11.f18327a;
            r1 = r1.m20870c(r8);
            if (r1 != 0) goto L_0x0009;
        L_0x0064:
            r1 = r11.f18327a;
            r1 = r1.m20869b(r8);
            if (r1 != 0) goto L_0x0009;
        L_0x006c:
            r1 = r12.f18316b;
            r2 = r11.f18327a;
            r2 = r2.m20871d(r8);
            r0 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r0, r2);
            r1.mo7151a(r0);
            goto L_0x0009;
        L_0x007c:
            r2 = 0;
            r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r2 == 0) goto L_0x0094;
        L_0x0082:
            r2 = java.lang.Integer.valueOf(r6);
            r12.f18317c = r2;
            r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
            if (r2 == 0) goto L_0x0094;
        L_0x0091:
            r12.produced(r0);
        L_0x0094:
            monitor-enter(r12);
            r0 = r12.f18320f;	 Catch:{ all -> 0x009f }
            if (r0 != 0) goto L_0x00a2;
        L_0x0099:
            r0 = 0;
            r12.f18319e = r0;	 Catch:{ all -> 0x009f }
            monitor-exit(r12);	 Catch:{ all -> 0x009f }
            goto L_0x0009;
        L_0x009f:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x009f }
            throw r0;
        L_0x00a2:
            r0 = 0;
            r12.f18320f = r0;	 Catch:{ all -> 0x009f }
            monitor-exit(r12);	 Catch:{ all -> 0x009f }
            goto L_0x000e;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.UnboundedReplayBuffer.replay(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }
    }

    /* renamed from: rx.internal.operators.OperatorReplay$b */
    static final class C5745b<T> extends C5721e<T> implements C5720f {
        /* renamed from: d */
        static final InnerProducer[] f18330d = new InnerProducer[0];
        /* renamed from: e */
        static final InnerProducer[] f18331e = new InnerProducer[0];
        /* renamed from: a */
        final C5743a<T> f18332a;
        /* renamed from: b */
        final NotificationLite<T> f18333b = NotificationLite.m20864a();
        /* renamed from: c */
        boolean f18334c;
        /* renamed from: f */
        volatile boolean f18335f;
        /* renamed from: g */
        final C5828c<InnerProducer<T>> f18336g = new C5828c();
        /* renamed from: h */
        InnerProducer<T>[] f18337h = f18330d;
        /* renamed from: i */
        volatile long f18338i;
        /* renamed from: j */
        long f18339j;
        /* renamed from: k */
        final AtomicBoolean f18340k = new AtomicBoolean();
        /* renamed from: l */
        boolean f18341l;
        /* renamed from: m */
        boolean f18342m;
        /* renamed from: n */
        long f18343n;
        /* renamed from: o */
        long f18344o;
        /* renamed from: p */
        volatile C5725c f18345p;
        /* renamed from: q */
        List<InnerProducer<T>> f18346q;
        /* renamed from: r */
        boolean f18347r;

        /* renamed from: rx.internal.operators.OperatorReplay$b$1 */
        class C57441 implements C5711a {
            /* renamed from: a */
            final /* synthetic */ C5745b f18329a;

            C57441(C5745b c5745b) {
                this.f18329a = c5745b;
            }

            public void call() {
                if (!this.f18329a.f18335f) {
                    synchronized (this.f18329a.f18336g) {
                        if (!this.f18329a.f18335f) {
                            this.f18329a.f18336g.m21021a();
                            C5745b c5745b = this.f18329a;
                            c5745b.f18338i++;
                            this.f18329a.f18335f = true;
                        }
                    }
                }
            }
        }

        public C5745b(AtomicReference<C5745b<T>> atomicReference, C5743a<T> c5743a) {
            this.f18332a = c5743a;
            m20816a(0);
        }

        /* renamed from: c */
        void m20896c() {
            m20818a(Subscriptions.create(new C57441(this)));
        }

        /* renamed from: a */
        boolean m20894a(InnerProducer<T> innerProducer) {
            if (innerProducer == null) {
                throw new NullPointerException();
            } else if (this.f18335f) {
                return false;
            } else {
                synchronized (this.f18336g) {
                    if (this.f18335f) {
                        return false;
                    }
                    this.f18336g.m21023a((Object) innerProducer);
                    this.f18338i++;
                    return true;
                }
            }
        }

        /* renamed from: b */
        void m20895b(InnerProducer<T> innerProducer) {
            if (!this.f18335f) {
                synchronized (this.f18336g) {
                    if (this.f18335f) {
                        return;
                    }
                    this.f18336g.m21025b(innerProducer);
                    this.f18338i++;
                }
            }
        }

        /* renamed from: a */
        public void mo7164a(C5725c c5725c) {
            if (this.f18345p != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.f18345p = c5725c;
            m20897c(null);
            m20899e();
        }

        /* renamed from: a */
        public void mo7150a(T t) {
            if (!this.f18334c) {
                this.f18332a.next(t);
                m20899e();
            }
        }

        /* renamed from: a */
        public void mo7151a(Throwable th) {
            if (!this.f18334c) {
                this.f18334c = true;
                try {
                    this.f18332a.error(th);
                    m20899e();
                } finally {
                    unsubscribe();
                }
            }
        }

        /* renamed from: a */
        public void mo7149a() {
            if (!this.f18334c) {
                this.f18334c = true;
                try {
                    this.f18332a.complete();
                    m20899e();
                } finally {
                    unsubscribe();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        /* renamed from: c */
        void m20897c(rx.internal.operators.OperatorReplay.InnerProducer<T> r11) {
            /*
            r10 = this;
            r6 = 0;
            r0 = r10.isUnsubscribed();
            if (r0 == 0) goto L_0x0008;
        L_0x0007:
            return;
        L_0x0008:
            monitor-enter(r10);
            r0 = r10.f18341l;	 Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0029;
        L_0x000d:
            if (r11 == 0) goto L_0x0025;
        L_0x000f:
            r0 = r10.f18346q;	 Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x001a;
        L_0x0013:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0022 }
            r0.<init>();	 Catch:{ all -> 0x0022 }
            r10.f18346q = r0;	 Catch:{ all -> 0x0022 }
        L_0x001a:
            r0.add(r11);	 Catch:{ all -> 0x0022 }
        L_0x001d:
            r0 = 1;
            r10.f18342m = r0;	 Catch:{ all -> 0x0022 }
            monitor-exit(r10);	 Catch:{ all -> 0x0022 }
            goto L_0x0007;
        L_0x0022:
            r0 = move-exception;
            monitor-exit(r10);	 Catch:{ all -> 0x0022 }
            throw r0;
        L_0x0025:
            r0 = 1;
            r10.f18347r = r0;	 Catch:{ all -> 0x0022 }
            goto L_0x001d;
        L_0x0029:
            r0 = 1;
            r10.f18341l = r0;	 Catch:{ all -> 0x0022 }
            monitor-exit(r10);	 Catch:{ all -> 0x0022 }
            r4 = r10.f18343n;
            if (r11 == 0) goto L_0x0051;
        L_0x0031:
            r0 = r11.f18318d;
            r0 = r0.get();
            r0 = java.lang.Math.max(r4, r0);
        L_0x003b:
            r10.m20890a(r0, r4);
        L_0x003e:
            r0 = r10.isUnsubscribed();
            if (r0 != 0) goto L_0x0007;
        L_0x0044:
            monitor-enter(r10);
            r0 = r10.f18342m;	 Catch:{ all -> 0x004e }
            if (r0 != 0) goto L_0x006b;
        L_0x0049:
            r0 = 0;
            r10.f18341l = r0;	 Catch:{ all -> 0x004e }
            monitor-exit(r10);	 Catch:{ all -> 0x004e }
            goto L_0x0007;
        L_0x004e:
            r0 = move-exception;
            monitor-exit(r10);	 Catch:{ all -> 0x004e }
            throw r0;
        L_0x0051:
            r3 = r10.m20898d();
            r7 = r3.length;
            r2 = r6;
            r0 = r4;
        L_0x0058:
            if (r2 >= r7) goto L_0x003b;
        L_0x005a:
            r8 = r3[r2];
            if (r8 == 0) goto L_0x0068;
        L_0x005e:
            r8 = r8.f18318d;
            r8 = r8.get();
            r0 = java.lang.Math.max(r0, r8);
        L_0x0068:
            r2 = r2 + 1;
            goto L_0x0058;
        L_0x006b:
            r0 = 0;
            r10.f18342m = r0;	 Catch:{ all -> 0x004e }
            r0 = r10.f18346q;	 Catch:{ all -> 0x004e }
            r1 = 0;
            r10.f18346q = r1;	 Catch:{ all -> 0x004e }
            r7 = r10.f18347r;	 Catch:{ all -> 0x004e }
            r1 = 0;
            r10.f18347r = r1;	 Catch:{ all -> 0x004e }
            monitor-exit(r10);	 Catch:{ all -> 0x004e }
            r4 = r10.f18343n;
            if (r0 == 0) goto L_0x00ba;
        L_0x007d:
            r8 = r0.iterator();
            r2 = r4;
        L_0x0082:
            r0 = r8.hasNext();
            if (r0 == 0) goto L_0x009a;
        L_0x0088:
            r0 = r8.next();
            r0 = (rx.internal.operators.OperatorReplay.InnerProducer) r0;
            r0 = r0.f18318d;
            r0 = r0.get();
            r0 = java.lang.Math.max(r2, r0);
            r2 = r0;
            goto L_0x0082;
        L_0x009a:
            r0 = r2;
        L_0x009b:
            if (r7 == 0) goto L_0x00b6;
        L_0x009d:
            r3 = r10.m20898d();
            r7 = r3.length;
            r2 = r6;
        L_0x00a3:
            if (r2 >= r7) goto L_0x00b6;
        L_0x00a5:
            r8 = r3[r2];
            if (r8 == 0) goto L_0x00b3;
        L_0x00a9:
            r8 = r8.f18318d;
            r8 = r8.get();
            r0 = java.lang.Math.max(r0, r8);
        L_0x00b3:
            r2 = r2 + 1;
            goto L_0x00a3;
        L_0x00b6:
            r10.m20890a(r0, r4);
            goto L_0x003e;
        L_0x00ba:
            r0 = r4;
            goto L_0x009b;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.b.c(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }

        /* renamed from: d */
        InnerProducer<T>[] m20898d() {
            Object obj;
            synchronized (this.f18336g) {
                Object c = this.f18336g.m21026c();
                int length = c.length;
                obj = new InnerProducer[length];
                System.arraycopy(c, 0, obj, 0, length);
            }
            return obj;
        }

        /* renamed from: a */
        void m20890a(long j, long j2) {
            long j3 = this.f18344o;
            C5725c c5725c = this.f18345p;
            long j4 = j - j2;
            if (j4 != 0) {
                this.f18343n = j;
                if (c5725c == null) {
                    j3 += j4;
                    if (j3 < 0) {
                        j3 = Long.MAX_VALUE;
                    }
                    this.f18344o = j3;
                } else if (j3 != 0) {
                    this.f18344o = 0;
                    c5725c.request(j3 + j4);
                } else {
                    c5725c.request(j4);
                }
            } else if (j3 != 0 && c5725c != null) {
                this.f18344o = 0;
                c5725c.request(j3);
            }
        }

        /* renamed from: e */
        void m20899e() {
            InnerProducer[] innerProducerArr = this.f18337h;
            if (this.f18339j != this.f18338i) {
                synchronized (this.f18336g) {
                    innerProducerArr = this.f18337h;
                    Object c = this.f18336g.m21026c();
                    int length = c.length;
                    if (innerProducerArr.length != length) {
                        innerProducerArr = new InnerProducer[length];
                        this.f18337h = innerProducerArr;
                    }
                    System.arraycopy(c, 0, innerProducerArr, 0, length);
                    this.f18339j = this.f18338i;
                }
            }
            C5743a c5743a = this.f18332a;
            for (InnerProducer innerProducer : r0) {
                if (innerProducer != null) {
                    c5743a.replay(innerProducer);
                }
            }
        }
    }

    /* renamed from: a */
    public static <T> C5718a<T> m20900a(C5717a<? extends T> c5717a) {
        return m20904a((C5717a) c5717a, f18348f);
    }

    /* renamed from: a */
    public static <T> C5718a<T> m20901a(C5717a<? extends T> c5717a, final int i) {
        if (i == Integer.MAX_VALUE) {
            return m20900a(c5717a);
        }
        return m20904a((C5717a) c5717a, new C5715e<C5743a<T>>() {
            public /* synthetic */ Object call() {
                return m20872a();
            }

            /* renamed from: a */
            public C5743a<T> m20872a() {
                return new SizeBoundReplayBuffer(i);
            }
        });
    }

    /* renamed from: a */
    public static <T> C5718a<T> m20902a(C5717a<? extends T> c5717a, long j, TimeUnit timeUnit, C5733d c5733d) {
        return m20903a(c5717a, j, timeUnit, c5733d, Integer.MAX_VALUE);
    }

    /* renamed from: a */
    public static <T> C5718a<T> m20903a(C5717a<? extends T> c5717a, long j, TimeUnit timeUnit, final C5733d c5733d, final int i) {
        final long toMillis = timeUnit.toMillis(j);
        return m20904a((C5717a) c5717a, new C5715e<C5743a<T>>() {
            public /* synthetic */ Object call() {
                return m20873a();
            }

            /* renamed from: a */
            public C5743a<T> m20873a() {
                return new SizeAndTimeBoundReplayBuffer(i, toMillis, c5733d);
            }
        });
    }

    /* renamed from: a */
    static <T> C5718a<T> m20904a(C5717a<? extends T> c5717a, final C5715e<? extends C5743a<T>> c5715e) {
        final AtomicReference atomicReference = new AtomicReference();
        return new OperatorReplay(new C5695a<T>() {
            public /* synthetic */ void call(Object obj) {
                m20874a((C5721e) obj);
            }

            /* renamed from: a */
            public void m20874a(C5721e<? super T> c5721e) {
                C5745b c5745b;
                C5745b c5745b2;
                do {
                    c5745b = (C5745b) atomicReference.get();
                    if (c5745b != null) {
                        break;
                    }
                    c5745b2 = new C5745b(atomicReference, (C5743a) c5715e.call());
                    c5745b2.m20896c();
                } while (!atomicReference.compareAndSet(c5745b, c5745b2));
                c5745b = c5745b2;
                C5725c innerProducer = new InnerProducer(c5745b, c5721e);
                c5745b.m20894a((InnerProducer) innerProducer);
                c5721e.m20818a((C5720f) innerProducer);
                c5745b.f18332a.replay(innerProducer);
                c5721e.mo7164a(innerProducer);
            }
        }, c5717a, atomicReference, c5715e);
    }

    private OperatorReplay(C5695a<T> c5695a, C5717a<? extends T> c5717a, AtomicReference<C5745b<T>> atomicReference, C5715e<? extends C5743a<T>> c5715e) {
        super(c5695a);
        this.f18349c = c5717a;
        this.f18350d = atomicReference;
        this.f18351e = c5715e;
    }
}
