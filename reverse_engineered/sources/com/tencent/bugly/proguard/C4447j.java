package com.tencent.bugly.proguard;

import java.io.Serializable;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.j */
public abstract class C4447j implements Serializable {
    /* renamed from: a */
    public abstract void mo6070a(C4455h c4455h);

    /* renamed from: a */
    public abstract void mo6071a(C4456i c4456i);

    /* renamed from: a */
    public abstract void mo6072a(StringBuilder stringBuilder, int i);

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        mo6072a(stringBuilder, 0);
        return stringBuilder.toString();
    }
}
