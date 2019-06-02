package com.digits.sdk.android;

import android.annotation.SuppressLint;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.p094c.C1521c;
import io.fabric.sdk.android.services.p094c.C4862b;

/* compiled from: ContactsPreferenceManager */
/* renamed from: com.digits.sdk.android.u */
class C2933u {
    /* renamed from: a */
    private final C4862b f13348a = new C1521c(C1520c.a(aa.class));

    C2933u() {
    }

    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: a */
    protected void m14251a() {
        this.f13348a.a(this.f13348a.b().putBoolean("CONTACTS_IMPORT_PERMISSION", true));
    }

    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: a */
    protected void m14253a(long j) {
        this.f13348a.a(this.f13348a.b().putLong("CONTACTS_READ_TIMESTAMP", j));
    }

    @SuppressLint({"CommitPrefEdits"})
    /* renamed from: a */
    protected void m14252a(int i) {
        this.f13348a.a(this.f13348a.b().putInt("CONTACTS_CONTACTS_UPLOADED", i));
    }
}
