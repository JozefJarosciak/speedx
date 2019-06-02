package com.beastbikes.android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;

/* compiled from: BaseDialog */
/* renamed from: com.beastbikes.android.dialog.c */
public class C1788c extends Dialog implements OnClickListener {
    /* renamed from: a */
    static final /* synthetic */ boolean f8171a = (!C1788c.class.desiredAssertionStatus());
    /* renamed from: b */
    private LinearLayout f8172b;

    public C1788c(Context context) {
        super(context, C1373R.style.picker_dialog);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setCancelable(false);
        super.setContentView(C1373R.layout.dialog_base);
        this.f8172b = (LinearLayout) findViewById(C1373R.id.base_dialog_content_view);
        ((TextView) findViewById(C1373R.id.base_dialog_ok)).setOnClickListener(this);
        ((TextView) findViewById(C1373R.id.base_dialog_cancel)).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.base_dialog_ok:
                mo3244a();
                return;
            case C1373R.id.base_dialog_cancel:
                dismiss();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m9478a(@NonNull View view) {
        this.f8172b.removeAllViews();
        this.f8172b.addView(view);
        Window window = getWindow();
        if (f8171a || window != null) {
            LayoutParams attributes = window.getAttributes();
            attributes.width = DensityUtil.getWidth(getContext()) - DensityUtil.dip2px(getContext(), 40.0f);
            attributes.height = -2;
            window.setAttributes(attributes);
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public void mo3244a() {
    }
}
