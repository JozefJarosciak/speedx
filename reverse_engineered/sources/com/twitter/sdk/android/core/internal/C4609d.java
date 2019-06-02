package com.twitter.sdk.android.core.internal;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4586l;
import java.util.List;

/* compiled from: SessionProvider */
/* renamed from: com.twitter.sdk.android.core.internal.d */
public abstract class C4609d {
    /* renamed from: a */
    private final List<C4586l<? extends C1469k>> f16261a;

    /* renamed from: a */
    public abstract void mo6175a(C4580e<C1469k> c4580e);

    public C4609d(List<C4586l<? extends C1469k>> list) {
        this.f16261a = list;
    }

    /* renamed from: a */
    public C1469k mo6174a() {
        C1469k c1469k = null;
        for (C4586l b : this.f16261a) {
            c1469k = b.mo6132b();
            if (c1469k != null) {
                break;
            }
        }
        return c1469k;
    }
}
