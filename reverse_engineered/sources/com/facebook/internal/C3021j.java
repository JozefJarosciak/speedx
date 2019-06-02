package com.facebook.internal;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

/* compiled from: FragmentWrapper */
/* renamed from: com.facebook.internal.j */
public class C3021j {
    /* renamed from: a */
    private Fragment f13572a;
    /* renamed from: b */
    private android.app.Fragment f13573b;

    /* renamed from: a */
    public void m14612a(Intent intent, int i) {
        if (this.f13572a != null) {
            this.f13572a.startActivityForResult(intent, i);
        } else {
            this.f13573b.startActivityForResult(intent, i);
        }
    }

    /* renamed from: a */
    public final Activity m14611a() {
        if (this.f13572a != null) {
            return this.f13572a.getActivity();
        }
        return this.f13573b.getActivity();
    }
}
