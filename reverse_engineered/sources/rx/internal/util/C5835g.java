package rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import rx.C5720f;
import rx.exceptions.C5736a;

/* compiled from: SubscriptionList */
/* renamed from: rx.internal.util.g */
public final class C5835g implements C5720f {
    /* renamed from: a */
    private LinkedList<C5720f> f18527a;
    /* renamed from: b */
    private volatile boolean f18528b;

    public C5835g(C5720f... c5720fArr) {
        this.f18527a = new LinkedList(Arrays.asList(c5720fArr));
    }

    public C5835g(C5720f c5720f) {
        this.f18527a = new LinkedList();
        this.f18527a.add(c5720f);
    }

    public boolean isUnsubscribed() {
        return this.f18528b;
    }

    /* renamed from: a */
    public void m21040a(C5720f c5720f) {
        if (!c5720f.isUnsubscribed()) {
            if (!this.f18528b) {
                synchronized (this) {
                    if (!this.f18528b) {
                        LinkedList linkedList = this.f18527a;
                        if (linkedList == null) {
                            linkedList = new LinkedList();
                            this.f18527a = linkedList;
                        }
                        linkedList.add(c5720f);
                        return;
                    }
                }
            }
            c5720f.unsubscribe();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    public void m21041b(rx.C5720f r3) {
        /*
        r2 = this;
        r0 = r2.f18528b;
        if (r0 != 0) goto L_0x000e;
    L_0x0004:
        monitor-enter(r2);
        r0 = r2.f18527a;	 Catch:{ all -> 0x001a }
        r1 = r2.f18528b;	 Catch:{ all -> 0x001a }
        if (r1 != 0) goto L_0x000d;
    L_0x000b:
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r2);	 Catch:{ all -> 0x001a }
    L_0x000e:
        return;
    L_0x000f:
        r0 = r0.remove(r3);	 Catch:{ all -> 0x001a }
        monitor-exit(r2);	 Catch:{ all -> 0x001a }
        if (r0 == 0) goto L_0x000e;
    L_0x0016:
        r3.unsubscribe();
        goto L_0x000e;
    L_0x001a:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001a }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.util.g.b(rx.f):void");
    }

    public void unsubscribe() {
        if (!this.f18528b) {
            synchronized (this) {
                if (this.f18528b) {
                    return;
                }
                this.f18528b = true;
                Collection collection = this.f18527a;
                this.f18527a = null;
                C5835g.m21039a(collection);
            }
        }
    }

    /* renamed from: a */
    private static void m21039a(Collection<C5720f> collection) {
        if (collection != null) {
            List list = null;
            for (C5720f unsubscribe : collection) {
                try {
                    unsubscribe.unsubscribe();
                } catch (Throwable th) {
                    List arrayList;
                    if (list == null) {
                        arrayList = new ArrayList();
                    } else {
                        arrayList = list;
                    }
                    arrayList.add(th);
                    list = arrayList;
                }
            }
            C5736a.m20862a(list);
        }
    }
}
