package com.beastbikes.android.utils;

import rx.C5717a;
import rx.subjects.C5840c;
import rx.subjects.C5841a;
import rx.subjects.C5843b;

/* compiled from: RxBus */
/* renamed from: com.beastbikes.android.utils.s */
public class C2574s {
    /* renamed from: a */
    private static volatile C2574s f12050a;
    /* renamed from: b */
    private final C5840c<Object, Object> f12051b = new C5843b(C5841a.b());

    private C2574s() {
    }

    /* renamed from: a */
    public static C2574s m12893a() {
        if (f12050a == null) {
            synchronized (C2574s.class) {
                if (f12050a == null) {
                    f12050a = new C2574s();
                }
            }
        }
        return f12050a;
    }

    /* renamed from: a */
    public void m12895a(Object obj) {
        this.f12051b.a(obj);
    }

    /* renamed from: a */
    public <T> C5717a<T> m12894a(Class<T> cls) {
        return this.f12051b.b(cls);
    }
}
