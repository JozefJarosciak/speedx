package com.beastbikes.android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.beastbikes.android.C1373R;

/* compiled from: FullScreenDialog */
/* renamed from: com.beastbikes.android.dialog.g */
public class C1797g extends Dialog {
    /* renamed from: a */
    static final /* synthetic */ boolean f8190a = (!C1797g.class.desiredAssertionStatus());

    public C1797g(Context context) {
        super(context, C1373R.style.Theme.Light.Dialog.Translucent);
        Window window = getWindow();
        if (f8190a || window != null) {
            window.setGravity(0);
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -1;
            window.setAttributes(attributes);
            setCancelable(false);
            return;
        }
        throw new AssertionError();
    }
}
