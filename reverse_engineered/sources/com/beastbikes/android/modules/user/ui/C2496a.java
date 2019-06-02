package com.beastbikes.android.modules.user.ui;

import android.app.ProgressDialog;
import android.content.Context;

/* compiled from: ProgressDialog */
/* renamed from: com.beastbikes.android.modules.user.ui.a */
public class C2496a extends ProgressDialog {
    /* renamed from: a */
    private final Context f11844a;

    public C2496a(Context context) {
        super(context);
        this.f11844a = context;
    }

    /* renamed from: a */
    public void m12572a(int i) {
        super.setMessage(this.f11844a.getString(i));
    }
}
