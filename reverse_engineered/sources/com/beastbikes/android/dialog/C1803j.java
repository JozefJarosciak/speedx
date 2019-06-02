package com.beastbikes.android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.view.C2526a;
import com.beastbikes.framework.android.p088g.C2801d;

/* compiled from: RecordFilterDialog */
/* renamed from: com.beastbikes.android.dialog.j */
public class C1803j extends Dialog implements OnClickListener {
    /* renamed from: a */
    static final /* synthetic */ boolean f8208a = (!C1803j.class.desiredAssertionStatus());
    /* renamed from: b */
    private C2526a f8209b;

    public C1803j(Context context, C2526a c2526a) {
        super(context, C1373R.style.Theme.Light.Dialog);
        Window window = getWindow();
        if (f8208a || window != null) {
            window.setGravity(53);
            window.getDecorView().setPadding(0, C2801d.m13756a(context, 50.0f), C2801d.m13756a(context, 12.0f), 0);
            LayoutParams attributes = window.getAttributes();
            attributes.width = -2;
            attributes.height = -2;
            window.setAttributes(attributes);
            setCancelable(true);
            this.f8209b = c2526a;
            return;
        }
        throw new AssertionError();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.dialog_record_filter);
        TextView textView = (TextView) findViewById(C1373R.id.record_filter_by_source_view);
        TextView textView2 = (TextView) findViewById(C1373R.id.record_filter_by_time_view);
        ((TextView) findViewById(C1373R.id.record_filter_all_data_view)).setOnClickListener(this);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.record_filter_all_data_view:
                this.f8209b.m12680b();
                break;
            case C1373R.id.record_filter_by_source_view:
                this.f8209b.m12681c();
                break;
            case C1373R.id.record_filter_by_time_view:
                this.f8209b.m12682d();
                break;
        }
        dismiss();
    }
}
