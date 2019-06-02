package com.beastbikes.android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.beastbikes.android.C1373R;

/* compiled from: TopDialog */
/* renamed from: com.beastbikes.android.dialog.l */
public class C1806l extends Dialog {
    /* renamed from: a */
    static final /* synthetic */ boolean f8212a = (!C1806l.class.desiredAssertionStatus());

    public C1806l(Context context) {
        super(context, C1373R.style.Theme.Light.Dialog);
        Window window = getWindow();
        if (f8212a || window != null) {
            window.setGravity(48);
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
            setCancelable(true);
            return;
        }
        throw new AssertionError();
    }
}
