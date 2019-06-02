package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/* compiled from: RecordBase */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.d */
public abstract class C1964d<K> extends LinearLayout {
    /* renamed from: a */
    private K f8798a;
    /* renamed from: b */
    protected Context f8799b;

    public abstract int getLayRes();

    public C1964d(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8799b = context;
        m10112b();
    }

    public C1964d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8799b = context;
        m10112b();
    }

    public C1964d(Context context) {
        super(context);
        this.f8799b = context;
        m10112b();
    }

    /* renamed from: a */
    public void mo3331a() {
    }

    /* renamed from: a */
    public void mo3332a(K k) {
        this.f8798a = k;
    }

    /* renamed from: b */
    private void m10112b() {
        LayoutInflater.from(getContext()).inflate(getLayRes(), this);
        mo3331a();
    }
}
