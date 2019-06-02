package com.beastbikes.android.widget.stickylistlibrary.stickylistheaders;

import android.content.Context;
import android.widget.Checkable;

/* compiled from: CheckableWrapperView */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.b */
class C2784b extends C2783e implements Checkable {
    public C2784b(Context context) {
        super(context);
    }

    public boolean isChecked() {
        return ((Checkable) this.a).isChecked();
    }

    public void setChecked(boolean z) {
        ((Checkable) this.a).setChecked(z);
    }

    public void toggle() {
        setChecked(!isChecked());
    }
}
