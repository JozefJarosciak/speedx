package io.fabric.sdk.android.services.p094c;

import android.annotation.SuppressLint;

/* compiled from: PreferenceStoreStrategy */
/* renamed from: io.fabric.sdk.android.services.c.d */
public class C4863d<T> {
    /* renamed from: a */
    private final C4862b f17139a;
    /* renamed from: b */
    private final C4574e<T> f17140b;
    /* renamed from: c */
    private final String f17141c;

    public C4863d(C4862b c4862b, C4574e<T> c4574e, String str) {
        this.f17139a = c4862b;
        this.f17140b = c4574e;
        this.f17141c = str;
    }

    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: a */
    public void m19111a(T t) {
        this.f17139a.m19108a(this.f17139a.m19109b().putString(this.f17141c, this.f17140b.mo6121a(t)));
    }

    /* renamed from: a */
    public T m19110a() {
        return this.f17140b.mo6122b(this.f17139a.m19107a().getString(this.f17141c, null));
    }
}
