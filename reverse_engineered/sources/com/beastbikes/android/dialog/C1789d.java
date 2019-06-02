package com.beastbikes.android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.beastbikes.android.C1373R;

/* compiled from: BottomDialog */
/* renamed from: com.beastbikes.android.dialog.d */
public class C1789d extends Dialog {
    /* renamed from: a */
    static final /* synthetic */ boolean f8173a = (!C1789d.class.desiredAssertionStatus());

    public C1789d(Context context) {
        super(context, C1373R.style.Theme.Light.Dialog);
        Window window = getWindow();
        if (f8173a || window != null) {
            window.setGravity(80);
            window.setWindowAnimations(C1373R.style.AnimBottom);
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
            setCancelable(false);
            return;
        }
        throw new AssertionError();
    }
}
