package com.tencent.mm.sdk.p198b;

import android.os.Looper;
import com.tencent.mm.sdk.p198b.C4494e.C4491a;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.tencent.mm.sdk.b.d */
public final class C4492d implements C4491a {
    private C4494e aJ;
    private ConcurrentHashMap<Runnable, WeakReference<C4496g>> aK;
    private int aL;
    private LinkedList<WeakReference<C4496g>> aM;

    public C4492d() {
        this.aK = new ConcurrentHashMap();
        this.aM = new LinkedList();
        this.aJ = new C4494e(this);
        if (this.aJ.getLooper().getThread().getName().equals("initThread")) {
            C4489b.m17826a("MicroMsg.MMHandler", "MMHandler can not init handler with initThread looper, stack %s", C4497h.m17843u());
        }
    }

    public C4492d(Looper looper) {
        this.aK = new ConcurrentHashMap();
        this.aM = new LinkedList();
        this.aJ = new C4494e(looper, this);
        if (looper.getThread().getName().equals("initThread")) {
            C4489b.m17826a("MicroMsg.MMHandler", "MMHandler can not init handler with initThread looper, stack %s", C4497h.m17843u());
        }
    }

    /* renamed from: a */
    public final void mo6083a(Runnable runnable, C4496g c4496g) {
        this.aK.put(runnable, new WeakReference(c4496g));
    }

    /* renamed from: b */
    public final void mo6084b(Runnable runnable, C4496g c4496g) {
        WeakReference weakReference = (WeakReference) this.aK.get(runnable);
        if (weakReference != null && weakReference.get() != null && weakReference.get() == c4496g) {
            this.aK.remove(runnable);
            if (this.aL > 0) {
                if (this.aM.size() == this.aL) {
                    this.aM.pop();
                }
                this.aM.add(weakReference);
            }
        }
    }

    public final boolean post(Runnable runnable) {
        return this.aJ.post(runnable);
    }

    public final String toString() {
        return "MMHandler(" + getClass().getName() + ")";
    }
}
