package rx.subscriptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rx.C5720f;
import rx.exceptions.C5736a;

public final class CompositeSubscription implements C5720f {
    private Set<C5720f> subscriptions;
    private volatile boolean unsubscribed;

    public CompositeSubscription(C5720f... c5720fArr) {
        this.subscriptions = new HashSet(Arrays.asList(c5720fArr));
    }

    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void add(C5720f c5720f) {
        if (!c5720f.isUnsubscribed()) {
            if (!this.unsubscribed) {
                synchronized (this) {
                    if (!this.unsubscribed) {
                        if (this.subscriptions == null) {
                            this.subscriptions = new HashSet(4);
                        }
                        this.subscriptions.add(c5720f);
                        return;
                    }
                }
            }
            c5720f.unsubscribe();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void remove(rx.C5720f r2) {
        /*
        r1 = this;
        r0 = r1.unsubscribed;
        if (r0 != 0) goto L_0x000e;
    L_0x0004:
        monitor-enter(r1);
        r0 = r1.unsubscribed;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000d;
    L_0x0009:
        r0 = r1.subscriptions;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
    L_0x000e:
        return;
    L_0x000f:
        r0 = r1.subscriptions;	 Catch:{ all -> 0x001c }
        r0 = r0.remove(r2);	 Catch:{ all -> 0x001c }
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        if (r0 == 0) goto L_0x000e;
    L_0x0018:
        r2.unsubscribe();
        goto L_0x000e;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.subscriptions.CompositeSubscription.remove(rx.f):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clear() {
        /*
        r2 = this;
        r0 = r2.unsubscribed;
        if (r0 != 0) goto L_0x000e;
    L_0x0004:
        monitor-enter(r2);
        r0 = r2.unsubscribed;	 Catch:{ all -> 0x0019 }
        if (r0 != 0) goto L_0x000d;
    L_0x0009:
        r0 = r2.subscriptions;	 Catch:{ all -> 0x0019 }
        if (r0 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r2);	 Catch:{ all -> 0x0019 }
    L_0x000e:
        return;
    L_0x000f:
        r0 = r2.subscriptions;	 Catch:{ all -> 0x0019 }
        r1 = 0;
        r2.subscriptions = r1;	 Catch:{ all -> 0x0019 }
        monitor-exit(r2);	 Catch:{ all -> 0x0019 }
        unsubscribeFromAll(r0);
        goto L_0x000e;
    L_0x0019:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0019 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.subscriptions.CompositeSubscription.clear():void");
    }

    public void unsubscribe() {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (this.unsubscribed) {
                    return;
                }
                this.unsubscribed = true;
                Collection collection = this.subscriptions;
                this.subscriptions = null;
                unsubscribeFromAll(collection);
            }
        }
    }

    private static void unsubscribeFromAll(Collection<C5720f> collection) {
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

    public boolean hasSubscriptions() {
        boolean z = false;
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!(this.unsubscribed || this.subscriptions == null || this.subscriptions.isEmpty())) {
                    z = true;
                }
            }
        }
        return z;
    }
}
